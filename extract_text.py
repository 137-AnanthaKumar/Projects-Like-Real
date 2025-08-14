#!/usr/bin/env python3
import argparse
import io
import os
import re
import shutil
import subprocess
import sys
import tempfile
from pathlib import Path
from typing import Callable, Dict, Iterable, List, Optional, Tuple

# Lightweight, local-only dependencies (installed via requirements.txt)
from pdfminer.high_level import extract_text as pdf_extract_text
from docx import Document
from pptx import Presentation
from bs4 import BeautifulSoup
import chardet
import openpyxl
import xlrd
import contextlib

SUPPORTED_EXTENSIONS = {
	".txt",
	".pdf",
	".doc",
	".docx",
	".ppt",
	".pptx",
	".html",
	".htm",
	".xls",
	".xlsx",
}


class ExtractionError(Exception):
	pass


def detect_file_encoding(binary_bytes: bytes, default: str = "utf-8") -> str:
	"""Best-effort encoding detection for plain text/HTML files."""
	try:
		result = chardet.detect(binary_bytes)
		encoding = result.get("encoding") or default
		# Some encodings are detected as ASCII though content may be UTF-8 compatible
		if encoding.lower() == "ascii":
			return default
		return encoding
	except Exception:
		return default


def normalize_whitespace(text: str) -> str:
	"""Collapse excessive blank lines and trim trailing spaces."""
	# Replace Windows newlines
	text = text.replace("\r\n", "\n").replace("\r", "\n")
	# Remove trailing spaces on each line
	text = "\n".join(line.rstrip() for line in text.split("\n"))
	# Collapse 3+ blank lines down to 2
	text = re.sub(r"\n{3,}", "\n\n", text)
	return text.strip()


# ------------------------
# Per-format extractors
# ------------------------

def extract_txt(file_path: Path) -> str:
	with open(file_path, "rb") as f:
		data = f.read()
	encoding = detect_file_encoding(data)
	return normalize_whitespace(data.decode(encoding, errors="ignore"))


def extract_pdf(file_path: Path) -> str:
	text = pdf_extract_text(str(file_path)) or ""
	return normalize_whitespace(text)


def extract_docx(file_path: Path) -> str:
	doc = Document(str(file_path))
	parts: List[str] = []
	# Paragraphs
	for paragraph in doc.paragraphs:
		if paragraph.text:
			parts.append(paragraph.text)
	# Tables
	for table in doc.tables:
		for row in table.rows:
			cells = [cell.text.strip() for cell in row.cells]
			parts.append("\t".join(cells))
	return normalize_whitespace("\n".join(parts))


def extract_pptx(file_path: Path) -> str:
	prs = Presentation(str(file_path))
	parts: List[str] = []
	for slide_idx, slide in enumerate(prs.slides, start=1):
		parts.append(f"--- Slide {slide_idx} ---")
		for shape in slide.shapes:
			# Text frames (including placeholders)
			if getattr(shape, "has_text_frame", False):
				text = shape.text
				if text:
					parts.append(text)
			# Tables
			if getattr(shape, "has_table", False):
				table = shape.table
				for row in table.rows:
					cells = [cell.text.strip() for cell in row.cells]
					parts.append("\t".join(cells))
	return normalize_whitespace("\n".join(parts))


def extract_html(file_path: Path) -> str:
	with open(file_path, "rb") as f:
		html_bytes = f.read()
	encoding = detect_file_encoding(html_bytes)
	soup = BeautifulSoup(html_bytes.decode(encoding, errors="ignore"), "lxml")
	# Optionally remove script/style elements
	for tag in soup(["script", "style", "noscript"]):
		tag.decompose()
	text = soup.get_text(separator="\n")
	return normalize_whitespace(text)


def extract_xlsx(file_path: Path) -> str:
	wb = openpyxl.load_workbook(str(file_path), read_only=True, data_only=True)
	parts: List[str] = []
	for ws in wb.worksheets:
		parts.append(f"--- Sheet: {ws.title} ---")
		for row in ws.iter_rows(values_only=True):
			line = "\t".join("" if v is None else str(v) for v in row)
			parts.append(line)
	return normalize_whitespace("\n".join(parts))


def extract_xls(file_path: Path) -> str:
	book = xlrd.open_workbook(str(file_path))
	parts: List[str] = []
	for sheet in book.sheets():
		parts.append(f"--- Sheet: {sheet.name} ---")
		for r in range(sheet.nrows):
			row_vals = sheet.row_values(r)
			line = "\t".join("" if v is None else str(v) for v in row_vals)
			parts.append(line)
	return normalize_whitespace("\n".join(parts))


# ------------------------
# Legacy formats (.doc, .ppt) via local tools when available
# ------------------------

def run_command(cmd: List[str]) -> Tuple[int, str, str]:
	proc = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	return proc.returncode, proc.stdout.decode(errors="ignore"), proc.stderr.decode(errors="ignore")


def try_antiword_doc(file_path: Path) -> Optional[str]:
	"""Use 'antiword' if installed to extract text from .doc."""
	if shutil.which("antiword") is None:
		return None
	code, out, err = run_command(["antiword", "-w", "0", "-t", str(file_path)])
	if code == 0 and out:
		return normalize_whitespace(out)
	return None


def try_catdoc_doc(file_path: Path) -> Optional[str]:
	"""Use 'catdoc' if installed to extract text from .doc."""
	if shutil.which("catdoc") is None:
		return None
	# Force UTF-8 output when possible
	code, out, err = run_command(["catdoc", "-d", "utf-8", str(file_path)])
	if code == 0 and out:
		return normalize_whitespace(out)
	return None


def try_soffice_convert(file_path: Path, target_ext: str) -> Optional[Path]:
	"""Use LibreOffice's soffice to convert legacy formats to OOXML (docx/pptx)."""
	if shutil.which("soffice") is None:
		return None
	tmpdir = Path(tempfile.mkdtemp(prefix="extract_text_"))
	# Basic convert-to targets ('docx' and 'pptx') work in most LibreOffice builds
	cmd = [
		"soffice",
		"--headless",
		"--convert-to",
		target_ext,
		"--outdir",
		str(tmpdir),
		str(file_path),
	]
	code, out, err = run_command(cmd)
	if code != 0:
		return None
	candidate = tmpdir / (file_path.stem + f".{target_ext}")
	if candidate.exists():
		return candidate
	# Fallback: search temp dir for a file that starts with the same stem and ends with the desired ext
	for p in tmpdir.iterdir():
		if p.is_file() and p.suffix.lower() == f".{target_ext}" and p.stem.startswith(file_path.stem):
			return p
	return None


def extract_doc(file_path: Path) -> str:
	# 1) Try antiword
	text = try_antiword_doc(file_path)
	if text:
		return text
	# 1b) Try catdoc
	text = try_catdoc_doc(file_path)
	if text:
		return text
	# 2) Try LibreOffice conversion to docx
	converted = try_soffice_convert(file_path, "docx")
	if converted and converted.exists():
		try:
			return extract_docx(converted)
		finally:
			# Best-effort cleanup
			with contextlib.suppress(Exception):
				converted.unlink(missing_ok=True)  # type: ignore[arg-type]
	# 3) Give actionable error
	raise ExtractionError(
		".doc extraction requires either 'antiword' or LibreOffice ('soffice') installed."
	)


def extract_ppt(file_path: Path) -> str:
	# 1) Try catppt
	text = try_catppt_ppt(file_path)
	if text:
		return text
	# 2) Try LibreOffice conversion to pptx
	converted = try_soffice_convert(file_path, "pptx")
	if converted and converted.exists():
		try:
			return extract_pptx(converted)
		finally:
			with contextlib.suppress(Exception):
				converted.unlink(missing_ok=True)  # type: ignore[arg-type]
	# 3) Give actionable error
	raise ExtractionError(
		".ppt extraction requires either 'catppt' or LibreOffice ('soffice') installed."
	)


# ------------------------
# Dispatcher
# ------------------------

EXTRACTORS: Dict[str, Callable[[Path], str]] = {
	".txt": extract_txt,
	".pdf": extract_pdf,
	".docx": extract_docx,
	".pptx": extract_pptx,
	".html": extract_html,
	".htm": extract_html,
	".xls": extract_xls,
	".xlsx": extract_xlsx,
	# legacy formats handled separately (.doc, .ppt)
}


def extract_text_from_file(file_path: Path) -> str:
	ext = file_path.suffix.lower()
	if ext not in SUPPORTED_EXTENSIONS:
		raise ExtractionError(f"Unsupported file extension: {ext}")
	if ext in EXTRACTORS:
		return EXTRACTORS[ext](file_path)
	if ext == ".doc":
		return extract_doc(file_path)
	if ext == ".ppt":
		return extract_ppt(file_path)
	raise ExtractionError(f"No extractor implemented for: {ext}")


# ------------------------
# CLI
# ------------------------

def iter_supported_files(inputs: List[Path], recursive: bool) -> Iterable[Path]:
	for p in inputs:
		if p.is_file():
			if p.suffix.lower() in SUPPORTED_EXTENSIONS:
				yield p
			continue
		if p.is_dir():
			glob = "**/*" if recursive else "*"
			for child in p.glob(glob):
				if child.is_file() and child.suffix.lower() in SUPPORTED_EXTENSIONS:
					yield child


def write_output(text: str, output: Optional[Path]) -> None:
	if output is None:
		print(text)
		return
	output.parent.mkdir(parents=True, exist_ok=True)
	with open(output, "w", encoding="utf-8") as f:
		f.write(text)


def default_out_path(in_file: Path, out_dir: Optional[Path]) -> Path:
	base_name = in_file.stem + ".txt"
	return (out_dir or in_file.parent) / base_name


def main(argv: Optional[List[str]] = None) -> int:
	parser = argparse.ArgumentParser(
		description="Extract plain text from common document formats without external APIs."
	)
	parser.add_argument("inputs", nargs="+", help="Files or directories to process")
	parser.add_argument("--recursive", "-r", action="store_true", help="Recurse into directories")
	parser.add_argument("--stdout", action="store_true", help="Print to stdout instead of writing .txt files")
	parser.add_argument("--out-dir", type=str, default=None, help="Directory to write output .txt files")
	args = parser.parse_args(argv)

	inputs = [Path(p).expanduser().resolve() for p in args.inputs]
	out_dir = Path(args.out_dir).expanduser().resolve() if args.out_dir else None

	errors: List[Tuple[Path, str]] = []
	multiple_inputs = sum(1 for _ in iter_supported_files(inputs, args.recursive)) > 1

	for in_file in iter_supported_files(inputs, args.recursive):
		try:
			text = extract_text_from_file(in_file)
			if args.stdout and not multiple_inputs:
				# If single input and --stdout, print text directly
				print(text)
			else:
				out_path = default_out_path(in_file, out_dir)
				write_output(text, out_path)
				print(f"Wrote: {out_path}")
		except Exception as e:
			errors.append((in_file, str(e)))

	if errors:
		print("\nSome files could not be processed:", file=sys.stderr)
		for p, err in errors:
			print(f" - {p}: {err}", file=sys.stderr)
		return 1
	return 0


if __name__ == "__main__":
	sys.exit(main())
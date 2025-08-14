from __future__ import annotations

import logging
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any, Dict, List, Optional, Union

# Local extractor (no external APIs)
from extract_text import extract_text_from_file, SUPPORTED_EXTENSIONS

# Optional integration with LlamaIndex-style interfaces
try:  # pragma: no cover - optional
	from llama_index.core.readers.base import BaseReader  # type: ignore
	from llama_index.core.schema import Document  # type: ignore
	export_llamaindex_types = True
except Exception:  # pragma: no cover - optional
	export_llamaindex_types = False

	class BaseReader:  # minimal fallback
		pass

	@dataclass
	class Document:  # minimal fallback
		text: str
		metadata: Dict[str, Any] = field(default_factory=dict)


logger = logging.getLogger(__name__)


class CustomReader(BaseReader):
	"""Custom parser that extracts text locally across many formats without external APIs.

	Supported extensions: .pdf, .txt, .doc, .docx, .ppt, .pptx, .html/.htm, .xls, .xlsx
	Legacy formats .doc and .ppt require either 'antiword'/'catdoc'/'catppt' or LibreOffice's 'soffice' locally.
	"""

	def __init__(self, return_full_document: Optional[bool] = False) -> None:
		self.return_full_document = bool(return_full_document)

	def load_data(
		self,
		file: Union[str, Path],
		extra_info: Optional[Dict[str, Any]] = None,
		fs: Optional[Any] = None,
	) -> List[Document]:
		"""Extract text from a single file and return a list with one Document.

		- No external API calls are made
		- Works locally across the supported formats
		"""
		path = Path(file).expanduser().resolve()
		if not path.exists():
			raise FileNotFoundError(f"File not found: {path}")

		ext = path.suffix.lower()
		if ext not in SUPPORTED_EXTENSIONS:
			raise ValueError(f"Unsupported file extension: {ext}")

		logger.info("Extracting text from %s", path)
		text = extract_text_from_file(path)
		logger.info("Extracted %d characters from %s", len(text), path)

		metadata: Dict[str, Any] = {
			"file_path": str(path),
			"file_name": path.name,
			"extension": ext,
		}
		if extra_info:
			metadata.update(extra_info)

		return [Document(text=text, metadata=metadata)]
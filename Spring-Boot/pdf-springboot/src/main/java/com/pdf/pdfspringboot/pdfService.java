package com.pdf.pdfspringboot;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import com.lowagie.text.Phrase;
@Service
public class pdfService {
	
	 public void setVehicleList(List<Entity> vehicleList) {
		this.vehicleList = vehicleList;
	}

	private List<Entity> vehicleList;
	
	public void generate(HttpServletResponse response) throws DocumentException, IOException {
		  // Create the Object of Document
	
		  Document document = new Document(PageSize.A3);
		  // get the document and write the response to output stream
		  PdfWriter.getInstance(document, response.getOutputStream());
		  document.open();
		  // Add Font
		  Font fontTiltle = FontFactory.getFont(FontFactory.COURIER_BOLD);
		  fontTiltle.setSize(20);
		                // Create Object of Paragraph
		  Paragraph paragraph = new Paragraph("Vehicle List", fontTiltle);
		  paragraph.setAlignment(Paragraph.ALIGN_LEFT);
		 // paragraph.
		  // Add to the document
		  document.add(paragraph);
		  PdfPTable table = new PdfPTable(5);
		  table.setWidthPercentage(50f);
		  table.setWidths(new int[] { 1, 3, 3, 2, 4 });
		  table.setSpacingBefore(5);
		  // Create Table Header
		  PdfPCell cell = new PdfPCell();
		  cell.setBackgroundColor(Color.GRAY);
		  cell.setPadding(5);
		  // Add FontFontFactory
		
		  Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		  font.setColor(Color.WHITE);
		  cell.setPhrase(new Phrase("ID", font));
		  table.addCell(cell);
		  cell.setPhrase(new Phrase("Vehicle Name", font));
		  table.addCell(cell);
		  cell.setPhrase(new Phrase("Model Name", font));
		  table.addCell(cell);
		  cell.setPhrase(new Phrase("Brand", font));
		  table.addCell(cell);
		  cell.setPhrase(new Phrase("Vehicle Number", font));
		  table.addCell(cell);
		  for (Entity vehicle : vehicleList) {
		   table.addCell(String.valueOf(vehicle.getId()));
		   table.addCell(vehicle.getVehicleName());
		   table.addCell(vehicle.getModelName());
		   table.addCell(vehicle.getBrand());
		   table.addCell(vehicle.getVehicleNo());
		  }
		  // Add table to document
		  document.add(table);
		  document.close();
		 }

	public List<Entity> getAllUser() {
	List<Entity> lists=new ArrayList<Entity>();
		Entity obj= new Entity(1,"ANANTHA","AKILA","gh","hdjd");
		Entity obj1= new Entity(2,"Wel","fg","gh","hdjd");
		Entity obj2= new Entity(3,"Wel","fg","gh","hdjd");
		Entity obj3= new Entity(4,"Wel","fg","gh","hdjd");
		Entity obj4= new Entity(5,"Wel","fg","gh","hdjd");
		lists.add(obj1);
		lists.add(obj2);
		lists.add(obj4);
		lists.add(obj);
		lists.add(obj3);
	//	lists.add(obj);
		
		return lists;
	}
		}



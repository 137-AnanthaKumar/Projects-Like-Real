package com.pdf.pdfspringboot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TestController {
	@Autowired
	 private pdfService vehicleService;
	 @GetMapping("/pdf/vehicle")
	 public void generator(HttpServletResponse response) throws DocumentException, IOException {
	  response.setContentType("application/pdf");
	  DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	  String currentDateTime = dateFormat.format(new Date());
	  String headerkey = "Content-Disposition";
	  String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
	  response.setHeader(headerkey, headervalue);
	  List<Entity> vehicleList = vehicleService.getAllUser();
	  pdfService generetorUser = new pdfService();
	  generetorUser.setVehicleList(vehicleList);
	  generetorUser.generate(response);
	 }
	}


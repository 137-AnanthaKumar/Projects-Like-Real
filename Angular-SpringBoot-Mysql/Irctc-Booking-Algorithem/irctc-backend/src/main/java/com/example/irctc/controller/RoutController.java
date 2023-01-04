package com.example.irctc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irctc.dto.request.RouteRequest;
import com.example.irctc.services.RouteService;

@RestController
@RequestMapping("/route")
public class RoutController {

	
	@Autowired
	private RouteService routeService;
	
	@PostMapping("/newRoute")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNewRoute(@RequestBody RouteRequest rotes) {
		
		String response=routeService.addNewRoute(rotes);
		
		return response;
		
	}
	
	
}

package com.dream11.fantasy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
	@PostMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String user() {
		return "hi user";
	}
	@PostMapping("admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		return "hi admin";
	}

}

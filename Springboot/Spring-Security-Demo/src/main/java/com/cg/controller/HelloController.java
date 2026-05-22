package com.cg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

	@GetMapping("/hi")
	public String sayHello(HttpServletRequest request) {
		return "Hello - " + request.getSession().getId();
	}

	@GetMapping("/about")
	public String about() {
		return "Telusko";
	}
}

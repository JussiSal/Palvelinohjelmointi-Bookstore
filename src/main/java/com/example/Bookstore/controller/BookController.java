package com.example.Bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BookController {
	
	@GetMapping("/index")
	public String indexGet() {
		return "index";
	}
}

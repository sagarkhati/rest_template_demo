package com.ssk.rest_template_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostsController {

	@GetMapping("/testing")
	public String testController() {
		return "This is Posts Controller...";
	}
}

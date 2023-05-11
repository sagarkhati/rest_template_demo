package com.ssk.rest_template_demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ssk.rest_template_demo.models.Posts;

@RestController
@RequestMapping("/api")
public class PostsController {

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/testing")
	public String testController() {
		return "This is Posts Controller...";
	}

	@GetMapping("/posts/{id}")
	public Posts getPost(@PathVariable int id) {
		ResponseEntity<Posts> responseEntity = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/posts/{id}", Posts.class, id);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-using-map/{id}")
	public Posts getPostUsingMap(@PathVariable int id) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		ResponseEntity<Posts> responseEntity = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/posts/{id}", Posts.class, uriVariables);

		return responseEntity.getBody();
	}
}

package com.ssk.rest_template_demo.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

	@GetMapping("/posts")
	public Posts[] getAllPosts() {
		ResponseEntity<Posts[]> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts",
				Posts[].class);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-object/{id}")
	public Posts getPost2(@PathVariable int id) {
		Posts object = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/{id}", Posts.class, id);

		return object;
	}

	@GetMapping("/posts-object-using-map/{id}")
	public Posts getPostUsingMap2(@PathVariable int id) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		Posts object = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/{id}", Posts.class,
				uriVariables);

		return object;
	}

	@GetMapping("/posts-object")
	public Posts[] getAllPosts2() {
		Posts[] object = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Posts[].class);

		return object;
	}

	@GetMapping("/posts-exchange-object/{id}")
	public Posts getPostUsingExchange(@PathVariable int id) {
		ResponseEntity<Posts> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/{id}",
				HttpMethod.GET, HttpEntity.EMPTY, Posts.class, id);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-map/{id}")
	public Posts getPostUsingExchangeWithMap(@PathVariable int id) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);
		ResponseEntity<Posts> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/{id}",
				HttpMethod.GET, HttpEntity.EMPTY, Posts.class, uriVariables);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-object-typeref/{id}")
	public Posts getPostUsingExchangeWithObjectWithParamClassRef(@PathVariable int id) {
		ResponseEntity<Posts> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/{id}",
				HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Posts>() {
				}, id);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-map-typeref/{id}")
	public Posts getPostUsingExchangeWithMapWithParamClassRef(@PathVariable int id) {
		ParameterizedTypeReference<Posts> typeReference = new ParameterizedTypeReference<Posts>() {
		};

		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		ResponseEntity<Posts> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/{id}",
				HttpMethod.GET, HttpEntity.EMPTY, typeReference, uriVariables);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange")
	public Posts[] getAllPosts3() {
		ResponseEntity<Posts[]> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
				HttpMethod.GET, HttpEntity.EMPTY, Posts[].class);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-typeref-list")
	public List<Posts> getAllPosts4() {
		ResponseEntity<List<Posts>> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
				HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Posts>>() {
				});

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-typeref-array")
	public Posts[] getAllPosts5() {
		ResponseEntity<Posts[]> responseEntity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
				HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Posts[]>() {
				});

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-req-entity/{id}")
	public Posts getPostExchangeWithRequestEntity(@PathVariable int id) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", id);

		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com/posts/{id}")
				.buildAndExpand(uriVariables).toUri();

		RequestEntity<Posts> entity = new RequestEntity<>(HttpMethod.GET, uri);

		ResponseEntity<Posts> responseEntity = restTemplate.exchange(entity, Posts.class);

		return responseEntity.getBody();
	}

	@GetMapping("/posts-exchange-req-entity")
	public Posts[] getAllPosts6() {
		RequestEntity<Posts[]> entity = null;
		try {
			entity = new RequestEntity<>(HttpMethod.GET, new URI("https://jsonplaceholder.typicode.com/posts"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Posts[]> responseEntity = restTemplate.exchange(entity, Posts[].class);

		return responseEntity.getBody();
	}
}

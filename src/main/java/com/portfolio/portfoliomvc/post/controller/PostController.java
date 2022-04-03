package com.portfolio.portfoliomvc.post.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfoliomvc.post.entity.Post;
import com.portfolio.portfoliomvc.post.service.IPostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	private static Logger log = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private IPostService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/saludo")
	public String saludo() {
		return "Funciona";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<Post>> getAllPost() {
		List<Post> postResponseList = this.service.getAllPost();
		return new ResponseEntity<List<Post>>(postResponseList, HttpStatus.OK); //status 200
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
		Optional<Post> postResponse = this.service.getPostById(id);
		if(postResponse.isPresent()) {
			return new ResponseEntity<Post>(postResponse.get(), HttpStatus.OK); //status 200	
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //status 404
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/post")
	public ResponseEntity<Post> setPost(@RequestBody Post post) {
		Optional<Post> postResponse = this.service.createPost(post);
		if(postResponse.isPresent()) {
			return new ResponseEntity<Post>(postResponse.get(), HttpStatus.CREATED); //status 201
		} else {
			return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR); //status 500
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Post> putPost(@RequestBody Post post) {
		Optional<Post> postResponse = this.service.putPost(post);
		if(postResponse.isPresent()) {
			return new ResponseEntity<Post>(postResponse.get(), HttpStatus.OK); //status 200
		} else {
			return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR); //status 500
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable("id") Long id){
		Optional<Post> postDeleteResponse = this.service.deletePost(id);
		if(postDeleteResponse.isEmpty()) {
			return new ResponseEntity<Post>(HttpStatus.OK); //status 200
		} else {
			return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR); //status 500
		}
	}
}

package com.portfolio.portfoliomvc.post.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfoliomvc.post.entity.Post;
import com.portfolio.portfoliomvc.post.repository.PostRepository;
import com.portfolio.portfoliomvc.post.service.IPostService;


@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private PostRepository repository;
	
	@Override
	public List<Post> getAllPost() {  // TODOS LOS POST
		// TODO Auto-generated method stub
		return this.repository.findAll();
	}

	@Override
	public Optional<Post> getPostById(Long id) {  // UN POST
		// TODO Auto-generated method stub
		return this.repository.findById(id);
	}

	@Override
	public Optional<Post> createPost(Post post) {  // CREATE
		// TODO Auto-generated method stub
		return Optional.of(this.repository.save(post));
	}

	@Override
	public Optional<Post> putPost(Post post) {  // ACTUALIZAR
		// TODO Auto-generated method stub
		return Optional.of(this.repository.save(post));
	}

	@Override
	public Optional<Post> deletePost(Long id) {  // DELETE
		// TODO Auto-generated method stub
		this.repository.deleteById(id);
		return this.repository.findById(id);
	}

}

package com.portfolio.portfoliomvc.post.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.portfoliomvc.post.entity.Post;

public interface IPostService {
	
	public abstract List<Post> getAllPost();
	
	public abstract Optional<Post> getPostById(Long id);
	
	public abstract Optional<Post> createPost(Post post);
	
	public abstract Optional<Post> putPost(Post post);
	
	public abstract Optional<Post> deletePost(Long id);

}


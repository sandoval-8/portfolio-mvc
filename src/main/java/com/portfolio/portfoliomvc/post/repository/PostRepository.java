package com.portfolio.portfoliomvc.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.portfoliomvc.post.entity.Post;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	
	public Optional<Post> findById(Long id);
	
	public List<Post> findAll();
	
	public Post save(Post post);
	
	public void deleteById(Long id);
}
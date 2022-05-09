package com.portfolio.portfoliomvc.photo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.post.entity.Post;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	@Query(value = "select * from photo where post_id = ?1", nativeQuery = true)
	public List<Photo> searchByPost(Long postId);
	
	public Optional<Photo> findById(Long photoId);
	
	public List<Photo> findByPost(Post post);

}

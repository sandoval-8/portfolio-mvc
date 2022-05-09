package com.portfolio.portfoliomvc.photo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.post.entity.Post;

public interface IPhotoService {
	
	public abstract Photo getImage(Long imageId);
	
	public abstract List<Photo> findByForPost(Post post);
	
	public abstract List<Photo> getAllImages(Long postId);
	
	public abstract Photo saveImage(Photo photo);
	
	public abstract void deleteImage();
	
	public abstract Photo editImage(Photo photo);

}

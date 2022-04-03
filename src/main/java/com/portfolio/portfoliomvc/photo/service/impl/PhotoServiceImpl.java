package com.portfolio.portfoliomvc.photo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.photo.repository.PhotoRepository;
import com.portfolio.portfoliomvc.photo.service.IPhotoService;

@Service
public class PhotoServiceImpl implements IPhotoService{
	
	@Autowired
	private PhotoRepository repository;

	@Override
	public Photo getImage(Long imageId) {
		// TODO Auto-generated method stub
		Optional<Photo> photo =  this.repository.findById(imageId);
		if( photo.isPresent()) {
			return photo.get();
		}
		return null; 
		
	}

	@Override
	public List<Photo> getAllImages(Long postId) {
		// TODO Auto-generated method stub
		List<Photo> result = this.repository.searchByPost(postId);
		return result;
	}

	@Override
	public Photo saveImage(Photo photo) {
		// TODO Auto-generated method stub
		Photo savedPhoto = this.repository.save(photo);		
		return savedPhoto;
	}

	@Override
	public void deleteImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Photo editImage(Photo photo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
 	
}

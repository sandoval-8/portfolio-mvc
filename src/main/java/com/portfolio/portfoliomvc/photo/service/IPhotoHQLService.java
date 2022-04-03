package com.portfolio.portfoliomvc.photo.service;

import java.util.List;

import com.portfolio.portfoliomvc.photo.entity.Photo;

public interface IPhotoHQLService {
	
	public abstract List<Photo> searchForPost(Long postId);
}

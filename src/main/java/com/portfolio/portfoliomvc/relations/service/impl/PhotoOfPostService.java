package com.portfolio.portfoliomvc.relations.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfoliomvc.relations.PhotoOfPost;
import com.portfolio.portfoliomvc.relations.repository.IPhotoPostRepository;
import com.portfolio.portfoliomvc.relations.service.IPhotoPostService;

@Service
public class PhotoOfPostService implements IPhotoPostService {
	
	@Autowired
	private IPhotoPostRepository photoPostService;

	@Override
	public PhotoOfPost guardar(PhotoOfPost entity) {
		// TODO Auto-generated method stub
		return photoPostService.save(entity);
	}

}

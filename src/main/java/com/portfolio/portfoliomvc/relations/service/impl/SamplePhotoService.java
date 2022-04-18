package com.portfolio.portfoliomvc.relations.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.portfoliomvc.relations.SamplePhoto;
import com.portfolio.portfoliomvc.relations.repository.IPhotoPostRepository;
import com.portfolio.portfoliomvc.relations.service.IPhotoPostService;

@Service
public class SamplePhotoService implements IPhotoPostService {
	
	@Autowired
	private IPhotoPostRepository photoPostService;

	@Override
	public SamplePhoto guardar(SamplePhoto entity) {
		// TODO Auto-generated method stub
		return photoPostService.save(entity);
	}

}

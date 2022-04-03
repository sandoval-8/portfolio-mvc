package com.portfolio.portfoliomvc.photo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.photo.service.IPhotoHQLService;

@Service
public class PhotoHQLServiceImpl implements IPhotoHQLService{
	
	@PersistenceContext
	private EntityManager entityPersistence;
	
	private static String query = "select * from Photo p where p.s...";

	@Override
	public List<Photo> searchForPost(Long postId) {
		// TODO Auto-generated method stub
		List<Photo> result = this.entityPersistence.createQuery("").getResultList();
		return result;
	}

}

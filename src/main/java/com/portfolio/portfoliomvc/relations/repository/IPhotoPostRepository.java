package com.portfolio.portfoliomvc.relations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.portfoliomvc.relations.PhotoOfPost;

public interface IPhotoPostRepository extends JpaRepository<PhotoOfPost, Long> {
	
	public PhotoOfPost save(PhotoOfPost tableRelation);

}

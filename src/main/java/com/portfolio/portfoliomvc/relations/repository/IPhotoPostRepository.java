package com.portfolio.portfoliomvc.relations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.portfoliomvc.relations.SamplePhoto;

public interface IPhotoPostRepository extends JpaRepository<SamplePhoto, Long> {
	
	public SamplePhoto save(SamplePhoto tableRelation);

}

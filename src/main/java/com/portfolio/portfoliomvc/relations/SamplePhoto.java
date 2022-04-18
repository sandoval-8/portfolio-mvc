package com.portfolio.portfoliomvc.relations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "samplePhoto")
public class SamplePhoto {
	
	@Id
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "photo_id", unique = false)
	private Long photoId;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	
}
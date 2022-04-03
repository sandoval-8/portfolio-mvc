package com.portfolio.portfoliomvc.photo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.portfolio.portfoliomvc.post.entity.Post;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String path;
	
/*	@ManyToOne
	private Post post; */ 
	
	/*=====================================
	 *========= SETTER and GETTER =========
	 *===================================== 
	 */
/*
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	} */


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	} 
	
	
}
package com.portfolio.portfoliomvc.photo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.portfolio.portfoliomvc.path.Path;
import com.portfolio.portfoliomvc.post.entity.Post;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	@Column(name = "name_resource")
	private String nameResource;
	
	@OneToOne
	private Path path;
	
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

	public String getNameResource() {
		return nameResource;
	}

	public void setNameResource(String nameResource) {
		this.nameResource = nameResource;
	}
	
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
	
}
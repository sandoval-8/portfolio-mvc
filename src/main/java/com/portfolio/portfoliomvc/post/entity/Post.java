package com.portfolio.portfoliomvc.post.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.portfolio.portfoliomvc.photo.entity.Photo;
import com.portfolio.portfoliomvc.profile.entity.SamplePhoto;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String presentation;

	private String description;

	// En JSON -> "visible"
	private boolean isVisible;

	// En JSON -> "enabled"
	private boolean isEnabled;

//	@OneToOne(fetch=FetchType.LAZY)
//	private Photo samplePhoto;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
	private List<Photo> bookPhotos = new ArrayList<>();

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

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}



	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

/*	public List<Photo> getBookPhotos() {
		return bookPhotos;
	}

	public void setBookPhotos(List<Photo> bookPhotos) {
		this.bookPhotos = bookPhotos;
	} */

}
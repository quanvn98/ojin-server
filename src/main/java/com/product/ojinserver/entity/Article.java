package com.product.ojinserver.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {

	@Id
	private UUID id;

	private String title;

	private String description;

	public Article() {
		super();
	}

	public Article(UUID id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

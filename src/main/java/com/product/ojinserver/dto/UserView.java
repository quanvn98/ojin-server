package com.product.ojinserver.dto;

import java.util.UUID;

import com.product.ojinserver.entity.User;

public class UserView {

	private UUID id;

	private String username;

	private String fullname;

	public UserView() {

	}

	public UserView(UUID id, String username, String fullname) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
	}

	public UserView(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.fullname = user.getFullname();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}

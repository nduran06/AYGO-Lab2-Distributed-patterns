package eci.aygo.dist.patts.nodeStorageApp.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import eci.aygo.dist.patts.nodeStorageApp.util.Parser;

public class User {

	private String id;
	private String name;
	private String email;
	private String password;
	private String timestamp;

	public User(String id, String name, String password, String email, String timestamp) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.timestamp = timestamp;
	}

	// getters and setters
	public String getId() {

		return this.id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getEmail() {

		return this.email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getPassword() {

		return this.password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getTimestamp() {

		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {

		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		try {
			return Parser.objectToJson(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
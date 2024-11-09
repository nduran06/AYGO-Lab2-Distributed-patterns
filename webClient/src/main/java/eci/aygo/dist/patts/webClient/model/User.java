package eci.aygo.dist.patts.webClient.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import eci.aygo.dist.patts.webClient.util.Parser;

public class User {

	private String name;
	private String email;
	private String password;

	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	// getters and setters
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
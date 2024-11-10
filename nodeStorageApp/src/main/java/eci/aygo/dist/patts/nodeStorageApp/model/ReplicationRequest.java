package eci.aygo.dist.patts.nodeStorageApp.model;

public class ReplicationRequest {

	private String userId;
	private User user;

	public ReplicationRequest(String userId, User user) {

		this.userId = userId;
		this.user = user;
	}

	// getters and setters
	public String getUserId() {

		return this.userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}
	
	public User getUser() {

		return this.user;
	}

	public void setUser(User user) {

		this.user = user;
	}

}

package net.bcarlso.tdd;

import java.io.Serializable;

public class Post implements Serializable {

	private final User user;
	private final String message;

	public Post(User user, String message) {
		this.user = user;
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}
}

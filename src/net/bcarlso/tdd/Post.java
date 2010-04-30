package net.bcarlso.tdd;

public class Post {

	private final User user;
	private final String message;

	public Post(User user, String message) {
		this.user = user;
		this.message = message;
	}

	public User user() {
		return user;
	}

	public String message() {
		return message;
	}

}

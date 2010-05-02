package net.bcarlso.tdd;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
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

	public boolean mentions(User user) {
		Pattern compile = Pattern.compile("(@bcarlso)( |$)");
		Matcher matcher = compile.matcher(message);
		return matcher.find();
	}
}

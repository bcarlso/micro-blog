package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MicroBlog {

	private List<String> posts;

	public MicroBlog() {
		posts = new ArrayList<String>();
	}

	public Post post(User user, String message) {
		posts.add(message);
		return new Post(user, message);
	}

	public List<String> timeline() {
		return posts;
	}

}

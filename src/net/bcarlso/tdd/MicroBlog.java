package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.List;

public class MicroBlog {

	private List<Post> posts;

	public MicroBlog() {
		posts = new ArrayList<Post>();
	}

	public Post post(User user, String message) {
		Post post = new Post(user, message);
		posts.add(post);
		return post;
	}

	public List<Post> postTimeline() {
		return posts;
	}

}

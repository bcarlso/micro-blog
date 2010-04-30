package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.List;

public class MicroBlog {

	private List<Post> posts;

	public MicroBlog() {
		posts = new ArrayList<Post>();
	}

	public Post post(User user, String message) {
		if(user == null) throw new IllegalArgumentException("User is required");
		if(message == null) throw new IllegalArgumentException("Message is required");
		
		String validatedMessage = message;
		
		if( isTooLong(message) ) {
			validatedMessage = truncate(message);
		}

		Post post = new Post(user, validatedMessage);
		posts.add(post);
		return post;
	}

	private String truncate(String message) {
		return message.substring(0, 17) + "...";
	}

	private boolean isTooLong(String message) {
		return message.length() > 20;
	}

	public List<Post> timeline() {
		return (posts.size() > 10) ? posts.subList(0, 10) : posts;
	}

}

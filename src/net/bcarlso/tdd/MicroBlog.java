package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MicroBlog {

	private List<Post> posts;
	private PostsRepository repository;
	private List<Post> newPosts;

	public MicroBlog(PostsRepository repository) {
		this.repository = repository;
		posts = this.repository.load();
		newPosts = this.repository.load();
	}

	public Post post(User user, String message) {
		if (user == null)
			throw new IllegalArgumentException("Null user passed in");
		if (message == null)
			throw new IllegalArgumentException("Null message passed in");

		String validatedMessage = message;

		if (isTooLong(message))
			validatedMessage = truncate(message);

		Post post = new Post(user, validatedMessage);
		posts.add(post);
		newPosts.add(0, post);

		repository.save(newPosts);

		return post;
	}

	private String truncate(String message) {
		return message.substring(0, 17) + "...";
	}

	private boolean isTooLong(String message) {
		return message.length() > 20;
	}

	public List<Post> timeline() {
		return firstPageOf(newPosts);
	}

	public List<Post> timeline(User currentUser) {
		List<Post> personalizedTimeline = new ArrayList<Post>();
		for(int i = 0; i < newPosts.size(); i++) {
			Post currentPost = newPosts.get(i);
			if(currentPost.mentions(currentUser) || currentUser.isFollowing(currentPost.getUser())) {
				personalizedTimeline.add(currentPost);
			}
		}
		return firstPageOf(personalizedTimeline);
	}

	private List<Post> firstPageOf(List<Post> personalizedTimeline) {
		return (personalizedTimeline.size() > 10) ? personalizedTimeline.subList(0, 10) : personalizedTimeline;
	}
}

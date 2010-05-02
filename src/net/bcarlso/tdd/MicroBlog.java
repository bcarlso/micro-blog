package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.List;

public class MicroBlog {

	private PostsRepository repository;
	private List<Post> posts;

	public MicroBlog(PostsRepository repository) {
		this.repository = repository;
		posts = this.repository.load();
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
		posts.add(0, post);

		repository.save(posts);

		return post;
	}

	private String truncate(String message) {
		return message.substring(0, 17) + "...";
	}

	public List<Post> timeline() {
		return firstPageOf(posts);
	}

	public List<Post> timeline(User currentUser) {
		List<Post> personalizedTimeline = new ArrayList<Post>();
		for(int i = 0; i < posts.size(); i++) {
			Post currentPost = posts.get(i);
			if(currentPost.mentions(currentUser) || currentUser.isFollowing(currentPost.getUser())) {
				personalizedTimeline.add(currentPost);
			}
		}
		return firstPageOf(personalizedTimeline);
	}

	private List<Post> firstPageOf(List<Post> timeline) {
		return (timeline.size() > 10) ? timeline.subList(0, 10) : timeline;
	}
	
	private boolean isTooLong(String message) {
		return message.length() > 20;
	}
}

package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MicroBlog {

	private List<Post> posts;
	private PostsRepository repository;

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
		posts.add(post);

		repository.save(posts);

		return post;
	}

	private String truncate(String message) {
		return message.substring(0, 17) + "...";
	}

	private boolean isTooLong(String message) {
		return message.length() > 20;
	}

	public List<Post> timeline() {
		ArrayList<Post> reverseChronologicalOrderedPosts = new ArrayList<Post>(posts);
		Collections.reverse(reverseChronologicalOrderedPosts);
		return (reverseChronologicalOrderedPosts.size() > 10) ? reverseChronologicalOrderedPosts.subList(0, 10) : reverseChronologicalOrderedPosts;
	}

	public List<Post> timeline(User currentUser) {
		List<Post> personalizedTimeline = new ArrayList<Post>();
		for(int i = 0; i < posts.size(); i++) {
			Post currentPost = posts.get(i);
			if(currentUser.isFollowing(currentPost.getUser())) {
				personalizedTimeline.add(currentPost);
			}
		}
		return personalizedTimeline;
	}
}

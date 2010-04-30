package net.bcarlso.tdd;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MicroBlog {

	private List<Post> posts;
	private PostsFile postsRepository;

	public MicroBlog() {
		postsRepository = new PostsFile("./posts.microblog");
		posts = postsRepository.load();
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

		postsRepository.save(posts);

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

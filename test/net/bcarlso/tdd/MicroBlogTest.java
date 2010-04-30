package net.bcarlso.tdd;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MicroBlogTest {
	private MicroBlog blog;
	private User user;

	@Before
	public void setUp() {
		blog = new MicroBlog();
		user = new User("bcarlso");
	}

	@Test
	public void shouldBeAbleToPost() throws Exception {
		Post post = blog.post(user, "Message");
		List<Post> expectedTimeline = Arrays.asList(post);
		
		assertEquals(user, post.getUser());
		assertEquals("Message", post.getMessage());
		assertEquals(expectedTimeline, blog.timeline());
	}

	@Test
	public void shouldTruncateLongMessages() throws Exception {
		Post post = blog.post(user, "Very loooooooooooooooooooooooonnnnnnnnnnnngggggggggg meeeeesssssaaaagggeeee");
		assertEquals("Very looooooooooo...", post.getMessage());
	}
	
	
	@Test
	public void shouldLimitTimeline() throws Exception {
		addPosts(11);
		assertEquals(10, blog.timeline().size());
	}

	private void addPosts(int numberOfPosts) {
		for(int i = 0; i < numberOfPosts; i++) {
			blog.post(user, String.valueOf(i));
		}
	}
}

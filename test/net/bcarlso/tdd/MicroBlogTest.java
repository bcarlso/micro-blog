package net.bcarlso.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MicroBlogTest {
	private MicroBlog blog;
	private User currentUser;

	@Before
	public void setUp() {
		blog = new MicroBlog(new DummyRepository());
		currentUser = new User("bcarlso");
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldRequireUser() throws Exception {
		blog.post(null, "Anything");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRequireMessage() throws Exception {
		blog.post(currentUser, null);
	}
	
	@Test
	public void shouldBeAbleToPost() throws Exception {
		Post post = blog.post(currentUser, "Message");
		List<Post> expectedTimeline = Arrays.asList(post);
		
		assertEquals(currentUser, post.getUser());
		assertEquals("Message", post.getMessage());
		assertEquals(expectedTimeline, blog.timeline());
	}

	@Test
	public void shouldTruncateLongMessages() throws Exception {
		Post post = blog.post(currentUser, "Very loooooooooooooooooooooooonnnnnnnnnnnngggggggggg meeeeesssssaaaagggeeee");
		assertEquals("Very looooooooooo...", post.getMessage());
	}
	
	@Test
	public void shouldShowTimelineNewestPostFirst() throws Exception {
		blog.post(currentUser, "First message");
		blog.post(currentUser, "Second message");
		
		List<Post> timeline = blog.timeline();
		
		assertEquals("Second message", timeline.get(0).getMessage());
		assertEquals("First message", timeline.get(1).getMessage());
	}
	
	@Test
	public void shouldLimitTimeline() throws Exception {
		addPosts(11);
		assertEquals(10, blog.timeline().size());
	}

	@Test
	public void shouldSavePostsAcrossSessions() throws Exception {
		MockRepository repository = new MockRepository();
		blog = new MicroBlog(repository);
		blog.post(currentUser, "Saved Message");
		repository.verify();
	}
	
	@Test
	public void shouldFilterUserTimelineByFollowing() throws Exception {
		blog = new MicroBlog(new StubbedRepositoryWithPostsFromKentAndAplusk());
		User kent = new User("kentbeck");
		currentUser.follow(kent);
		assertOnlyPostsFrom(kent, blog.timeline(currentUser));
	}
	
	private void assertOnlyPostsFrom(User user, List<Post> timeline) {
		assertFalse(timeline.isEmpty());
		for(Post post : timeline) {
			assertEquals(user, post.getUser());
		}
	}

	private void addPosts(int numberOfPosts) {
		for(int i = 0; i < numberOfPosts; i++) {
			blog.post(currentUser, String.valueOf(i));
		}
	}
	
	public static class StubbedRepositoryWithPostsFromKentAndAplusk extends DummyRepository {
		@Override
		public List<Post> load() {
			return Arrays.asList(new Post(new User("kentbeck"), "New JUnit released"),
					new Post(new User("aplusk"), "You've been punked!"),
					new Post(new User("aplusk"), "Blah Blah"));
		}
	}
}

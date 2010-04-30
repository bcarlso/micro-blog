package net.bcarlso.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
	public void userShouldBeAbleToFollowOtherUsers() throws Exception {
		User userToFollow = new User("toranb");
		blog.follow(currentUser, userToFollow);
		assertEquals(1, currentUser.followingCount());
		assertTrue(currentUser.isFollowing(userToFollow));
	}
	
	@Test
	public void userShouldReflectBeingFollowed() throws Exception {
		User userToFollow = new User("toranb");
		blog.follow(currentUser, userToFollow);
		assertEquals(1, userToFollow.followerCount());
		assertTrue(userToFollow.isFollowedBy(currentUser));
	}
	
	private void addPosts(int numberOfPosts) {
		for(int i = 0; i < numberOfPosts; i++) {
			blog.post(currentUser, String.valueOf(i));
		}
	}
	
	private static class DummyRepository implements PostsRepository {

		@Override
		public List<Post> load() {
			return new ArrayList<Post>();
		}

		@Override
		public void save(List<Post> posts) {
		}
	}


	public static class MockRepository extends DummyRepository {

		private List<Post> saveWasCalledWith;

		public void verify() {
			assertNotNull(saveWasCalledWith);
		}
		
		@Override
		public List<Post> load() {
			return new ArrayList<Post>();
		}

		@Override
		public void save(List<Post> posts) {
			saveWasCalledWith = posts;
		}
	}
}

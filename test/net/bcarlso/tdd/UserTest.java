package net.bcarlso.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class UserTest {
	private User currentUser;

	@Before
	public void setUp() {
		currentUser = new User("bcarlso");
	}
	
	@Test
	public void userShouldBeAbleToFollowOtherUsers() throws Exception {
		User userToFollow = new User("toranb");
		currentUser.follow(userToFollow);
		
		assertEquals(1, currentUser.followingCount());
		assertTrue(currentUser.isFollowing(userToFollow));

		assertEquals(1, userToFollow.followerCount());
		assertTrue(userToFollow.isFollowedBy(currentUser));
	}
	
	@Test
	public void shouldSilentlyIgnoreFollowingSomeoneMoreThanOnce() throws Exception {
		User userToFollow = new User("toranb");
		currentUser.follow(userToFollow);
		currentUser.follow(userToFollow);
		assertEquals(1, currentUser.followingCount());
	}

	@Test
	public void shouldBeConsideredEqualWhenUsernamesMatch() throws Exception {
		assertTrue(currentUser.equals(new User("bcarlso")));
	}

	@Test
	public void shouldBeConsideredNotEqualWhenComparedAgainstOtherObjects() throws Exception {
		assertFalse(currentUser.equals("A string")); 
	}
}

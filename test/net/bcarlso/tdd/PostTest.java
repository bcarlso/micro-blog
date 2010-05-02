package net.bcarlso.tdd;

import static org.junit.Assert.*;

import org.junit.Test;


public class PostTest {
	@Test
	public void shouldRecognizeMentionsInPosts() throws Exception {
		Post post = new Post(new User("somebody"), "Mentioning @bcarlso");
		assertTrue(post.mentions(new User("bcarlso")));
	}
	
	@Test
	public void shouldOnlyRecogizeUsernamesAsMentionsWhenPrefixed() throws Exception {
		Post post = new Post(new User("somebody"), "I loathe bcarlso");
		assertFalse(post.mentions(new User("bcarlso")));
	}

	
	@Test
	public void shouldRequireExactUsernameToBeConsideredMentioned() throws Exception {
		Post post = new Post(new User("somebody"), "@bcarlson");
		assertFalse(post.mentions(new User("bcarlso")));
	}
}

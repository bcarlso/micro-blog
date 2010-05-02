package net.bcarlso.tdd;

import static org.junit.Assert.*;

import org.junit.Test;


public class PostTest {
	private static final User MICROBLOG_USER = new User("somebody");
	private static final User BCARLSO = new User("bcarlso");

	@Test
	public void shouldRecognizeMentionsInPosts() throws Exception {
		assertTrue(postContaining("Mentioning @bcarlso").mentions(BCARLSO));
	}

	@Test
	public void shouldRecognizeMentionsInTheMiddleOfPosts() throws Exception {
		assertTrue(postContaining("Mention @bcarlso here").mentions(BCARLSO));
	}
	
	@Test
	public void shouldOnlyRecogizeUsernamesAsMentionsWhenPrefixed() throws Exception {
		assertFalse(postContaining("I loathe bcarlso").mentions(BCARLSO));
	}

	@Test
	public void shouldRequireExactUsernameToBeConsideredMentioned() throws Exception {
		assertFalse(postContaining("@bcarlson").mentions(BCARLSO));
	}

	private Post postContaining(String message) {
		return new Post(MICROBLOG_USER, message);
	}
}

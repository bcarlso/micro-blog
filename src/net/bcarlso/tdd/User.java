package net.bcarlso.tdd;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<User> following;
	private ArrayList<User> followers;

	public User(String username) {
		following = new ArrayList<User>();
		followers = new ArrayList<User>();
	}

	public int followingCount() {
		return following.size();
	}

	public boolean isFollowing(User user) {
		return following.contains(user);
	}

	public void follow(User userToFollow) {
		following.add(userToFollow);
	}

	public int followerCount() {
		return followers.size();
	}

	public boolean isFollowedBy(User user) {
		return followers.contains(user);
	}

	public void addFollower(User currentUser) {
		followers.add(currentUser);
	}

}

package net.bcarlso.tdd;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<User> following;
	private ArrayList<User> followers;

	private final String username;

	public User(String username) {
		this.username = username;
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
		userToFollow.addFollower(this);
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

	public String getName() {
		return username;
	}

	public boolean equals(Object other) {
		if (other instanceof User)
			return ((User) other).getName().equals(getName());
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
}

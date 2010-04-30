package net.bcarlso.tdd;

import java.util.List;

public interface PostsRepository {

	public List<Post> load();

	public void save(List<Post> posts);

}
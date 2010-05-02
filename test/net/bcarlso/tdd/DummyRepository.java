/**
 * 
 */
package net.bcarlso.tdd;

import java.util.ArrayList;
import java.util.List;

public class DummyRepository implements PostsRepository {

	@Override
	public List<Post> load() {
		return new ArrayList<Post>();
	}

	@Override
	public void save(List<Post> posts) {
	}
}
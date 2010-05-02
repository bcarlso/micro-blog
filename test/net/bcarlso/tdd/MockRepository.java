/**
 * 
 */
package net.bcarlso.tdd;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class MockRepository extends DummyRepository {

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
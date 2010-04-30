package net.bcarlso.tdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PostsFile implements PostsRepository {

	String filename;

	public PostsFile(String filename) {
		this.filename = filename;
	}
	
	public List<Post> load() {
		List<Post> posts = new ArrayList<Post>();
		try {
			File f = new File(filename);
			if (f.exists()) {
				FileInputStream fos = new FileInputStream(f);
				ObjectInputStream out = new ObjectInputStream(fos);

				posts = (List<Post>) out.readObject();
				out.close();
			}
			return posts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void save(List<Post> posts) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(posts);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}

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
	
	@SuppressWarnings("unchecked")
	public List<Post> load() {
		List<Post> posts = new ArrayList<Post>();
		try {
			File f = new File(filename);
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fis);

				posts = (List<Post>) in.readObject();
				in.close();
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

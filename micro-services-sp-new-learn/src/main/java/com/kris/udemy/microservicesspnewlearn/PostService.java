package com.kris.udemy.microservicesspnewlearn;

import java.util.List;
import java.util.Optional;

public interface PostService {
	
	List<Post> findAllPosts();
	Optional<Post> findPostDetails(int id);
	Post createPost(Post post);

}

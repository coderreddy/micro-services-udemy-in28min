package com.kris.udemy.microservicesspnewlearn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Post> findAllPosts() {
		List<Post> postList = postRepo.findAll();
		if(postList.isEmpty())
		{
			throw new ResourceNotFoundException("Posts Not found");
		}
		return postList;
	}
	
	@Override
	public Optional<Post> findPostDetails(int id) {
		Optional<Post> post = postRepo.findById(id);
		if(!post.isPresent())
		{
			throw new ResourceNotFoundException("ID "+id);
		}
		return post;
	}

	@Override
	public Post createPost(Post post) {
		return postRepo.save(post);
	}
	
	

}

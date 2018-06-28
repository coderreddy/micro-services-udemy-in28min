package com.kris.udemy.microservicesspnewlearn;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Controller {
	
	@Autowired
	private MessageSource messagesource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping(value="/getUsers")
	public List<User> getUsers()
	{
		List<User> userlist = userService.findAll();
		//MappingJacksonValue mjv = filterUsers(null,userlist,"dob","firstname");
		return userlist;
	}
	
	//HATEOAS implementation where this method returns the link to getUsers()
	@GetMapping(value="/findUser/{id}")
	public Resource<User> findUser(@PathVariable int id)
	{
		User user = userService.findUser(id);
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	//used to filter the fields to be sent in response
	//Note: If the entity is annotated with @JsonFilter, 
	//then all the resources using that object needs to have filter logic
	private MappingJacksonValue filterUsers(User user,List<User> userlist, String field1,String field2) {
		MappingJacksonValue mjv = null;
		SimpleBeanPropertyFilter smbf = SimpleBeanPropertyFilter.filterOutAllExcept(field1,field2);
		FilterProvider fmb = new SimpleFilterProvider().addFilter("Filter", smbf);
		if(user != null)
		{
		 mjv = new MappingJacksonValue(user);
		}else{
			mjv = new MappingJacksonValue(userlist);
		}
		mjv.setFilters(fmb);
		return mjv;
	}
	
	@PostMapping(value="/createUser")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	public ResponseEntity<Object> createUsers(@Valid @RequestBody User user)
	{
		User usr = userService.createUser(user);
		//Return the response code as 201 created along with URL link to the user id.
		URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usr.getID()).toUri();
		return ResponseEntity.created(loc).build();
	}
	
	@DeleteMapping(value="/deleteUserById/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable int id)
	{
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
	
	//Manage the messages based on the request location.
	@GetMapping(value="/getInternationalizeMessage")
	public String getInternationalizeMessage()
	{
		return messagesource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping(value="/getPosts/{id}")
	public List<Post> getPosts(@PathVariable int id)
	{
		User user = userService.findUser(id);
		return user.getPost();
	}
	
	@GetMapping(value="/getPostsByID/{id}")
	public Optional<Post> getPostsByID(@PathVariable int id)
	{
		return postService.findPostDetails(id);
	}
	
	@PostMapping(value="/createPost/{id}")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post)
	{
		User user = userService.findUser(id);
		if(user != null){
		post.setUser(user);
		}
		Post res = postService.createPost(post);
		URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(res.getId()).toUri();
		return ResponseEntity.created(loc).build();
	}
}

package com.kris.udemy.microservicesspnewlearn;

import java.util.List;
import java.util.Optional;

public interface UserService {
	
	List<User> findAll();
	User findUser(int id);
	User createUser(User user);
	void deleteUser(int id);
	
}

package com.kris.udemy.microservicesspnewlearn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository myRepoDao;

	@Override
	public List<User> findAll() {
		List<User> userlist = myRepoDao.findAll();
		if(userlist.isEmpty())
		{
			throw new ResourceNotFoundException("No Users found");
		}
		return userlist;
	}

	@Override
	public User findUser(int id) {
		User user = null;
		Optional<User> usr = myRepoDao.findById(id);
		if(!usr.isPresent())
		{
			throw new ResourceNotFoundException("id "+id);
		}else{
			user = usr.get();
		}
		return user;
	}

	@Override
	public User createUser(User user) {
		 return myRepoDao.save(user);
	}

	@Override
	public void deleteUser(int id) {
		myRepoDao.deleteById(id);
	}

}

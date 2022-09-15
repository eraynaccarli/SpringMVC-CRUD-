package com.mycompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.entities.User;
import com.mycompany.exceptions.UserNotFoundException;
import com.mycompany.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return this.repo.findAll();
	}

	public void save(User user) {
		this.repo.save(user);
	}
	
	public User findByUserId(Integer id) throws UserNotFoundException {
		Optional<User> user = this.repo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserNotFoundException("Could not find any users with ID " + id);
	}
	
	public void deleteUser(Integer id) throws UserNotFoundException {
		Long count = this.repo.countById(id);
		if(count == null || count == 0) {
			throw new UserNotFoundException("Could not find any users with ID " + id);
		}
		this.repo.deleteById(id);
	}
}

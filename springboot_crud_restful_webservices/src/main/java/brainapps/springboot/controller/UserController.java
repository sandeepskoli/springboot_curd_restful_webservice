package brainapps.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import brainapps.springboot.entity.User;
import brainapps.springboot.exception.ResourceNotFoundException;
import brainapps.springboot.repository.UserRepository;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping
	public List<User> getAllUser(){
		return userRepository.findAll();	
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value="id") long userId) {
		return userRepository.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User Not Found for given Id:"+userId));
	} 
	
	
	//Create user
	@PostMapping
	public User CreateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable(value="id")long userId) {
	    User existing = getUserById(userId);
	    existing.setFirstName(user.getFirstName());
	    existing.setLastName(user.getLastName());
	    existing.setEmail(user.getEmail());
	    
	    return userRepository.save(existing);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value="id")long userId) {
		
		User existing = getUserById(userId);
		
		userRepository.delete(existing);
		return ResponseEntity.ok().build();
		
	}
}

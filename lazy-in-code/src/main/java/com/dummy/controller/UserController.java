package com.dummy.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.Model.User;
import com.dummy.repository.UserRepository;

@RestController
@RequestMapping("api/v2/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "signup",method = RequestMethod.POST)
	@CrossOrigin
	public User signup(@RequestBody User user) {
		System.out.println("signup");
		List<User> dataList=userRepository.findByEmail(user.getEmail());
		if(dataList.size()==0)
			userRepository.save(user);
		return user;
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	@CrossOrigin
	public User login(@RequestBody HashMap<String, String> user) throws Exception {
		System.out.println("login");
		List<User> dataList=userRepository.findByEmail(user.get("email"));
		if(dataList.size()==0)
			return null;
		if(dataList.get(0).getPassword().equals(user.get("password")))
			return dataList.get(0);
		else
			return null;
	}

}

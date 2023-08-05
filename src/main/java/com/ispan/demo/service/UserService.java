package com.ispan.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.demo.model.User;
import com.ispan.demo.model.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	// 新增
	public User insertUser(User user) {
		return userRepository.save(user);
			
	}

	public User findUserById(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public User updateUserById(User user,Integer id) {
		Optional<User> option = userRepository.findById(id);
		System.out.println(user);
		if (option.isEmpty()) {
			
			return null;
		}
		 User getUser = option.get();
		 
		 getUser.setUserName(user.getUserName());
		 getUser.setNickName(user.getNickName());
		 getUser.setPhoto(user.getPhoto());
		 getUser.setGender(user.getGender());
		 getUser.setEmail(user.getEmail());
		 getUser.setBirthday(user.getBirthday());
		 
		 userRepository.save(getUser);
		return getUser;
	}

	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	public User checkLogin(String userName, String pwd) {
		User user = userRepository.findByUserName(userName);

		if (user!=null &&user.getUserName().equals(userName) && user.getPassWord().equals(pwd)) {
			return user;
		}
		return null;
	}

}

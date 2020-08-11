package com.hellokoding.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hellokoding.springboot.entity.UserLogin;
import com.hellokoding.springboot.repository.FileHashRepository;
import com.hellokoding.springboot.repository.UserLoginRepository;

/**
 * @author suvrat.aggarwal
 *
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	public JavaMailSender emailSender;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.service.UserLoginService#registerUser(com.example.demo.model
	 * .UserLogin)
	 */
	@Override
	public boolean registerUser(UserLogin userLogin) {
		userLoginRepository.save(userLogin);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userLogin.getEmail());
		message.setSubject("Welcome To Our User Management Application");
		message.setText(
				"Welcome dear user to our application. Thank you for registering with us. Please login and enjoy working on the application :)");
		//emailSender.send(message);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserLoginService#findUsername(java.lang.String)
	 */
	@Override
	public UserLogin findUsername(String username) {
		UserLogin userName = userLoginRepository.findUsername(username);
		return userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.service.UserLoginService#checkPassword(java.lang.String)
	 */
	@Override
	public UserLogin checkPassword(String password) {
		return userLoginRepository.findPassword(password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserLoginService#add(com.example.demo.model.
	 * UserLogin)
	 */
	@Override
	public boolean add(UserLogin userLogin) {
		userLoginRepository.save(userLogin);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserLoginService#deleteUser(java.lang.String)
	 */
	@Override
	public int deleteUser(String username) {
		userLoginRepository.deleteUser(username);
		return 1;
	}
}

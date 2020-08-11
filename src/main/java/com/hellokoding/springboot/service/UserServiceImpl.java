package com.hellokoding.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellokoding.springboot.entity.FileHashDetails;
import com.hellokoding.springboot.entity.User;
import com.hellokoding.springboot.repository.FileHashRepository;
import com.hellokoding.springboot.repository.UserRepository;

/**
 * @author suvrat.aggarwal
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public FileHashRepository fileHashRepository;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		List<User> user = new ArrayList<>();
		user = (List<User>) userRepository.findAll();
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserService#find(java.lang.String)
	 */
	@Override
	public List<User> find(String username) {
		return userRepository.findUser(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserService#add(com.example.demo.model.User)
	 */
	@Override
	public boolean add(User user) {
		userRepository.save(user);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserService#delete(java.lang.Long)
	 */
	@Override
	public boolean delete(Long userId) {
		userRepository.delete(userId);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserService#updateUser(java.lang.Long,
	 * com.example.demo.model.User)
	 */
	@Override
	public User updateUser(Long userId, User updateUserDetails) {
		User user = userRepository.getOne(userId);
		user.setFirstName(updateUserDetails.getFirstName());
		user.setLastName(updateUserDetails.getLastName());
		user.setAddress(updateUserDetails.getAddress());
		user.setUsername(updateUserDetails.getUsername());
		user.setAge(updateUserDetails.getAge());
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.service.UserService#updateAdmin(java.lang.Long,
	 * com.example.demo.model.User)
	 */
	@Override
	public User updateAdmin(Long userId, User updateUserDetails) {
		User user = userRepository.getOne(userId);
		user.setEmployeeCode(updateUserDetails.getEmployeeCode());
		user.setEmployeeStatus(updateUserDetails.getEmployeeStatus());
		user.setEmployeeReport(updateUserDetails.getEmployeeReport());
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	/* (non-Javadoc)
	 * @see com.example.demo.service.UserService#update(java.lang.Long, com.example.demo.model.User)
	 */
	@Override
	public User update(Long userId, User updateUserDetails) {
		User user = userRepository.getOne(userId);
		user.setAddress(updateUserDetails.getAddress());
		user.setAge(updateUserDetails.getAge());
		user.setEmployeeCode(updateUserDetails.getEmployeeCode());
		user.setEmployeeStatus(updateUserDetails.getEmployeeStatus());
		user.setEmployeeReport(updateUserDetails.getEmployeeReport());
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Override
	public boolean saveHashDetails(FileHashDetails fileHashDetails) {
		// TODO Auto-generated method stub
		fileHashRepository.save(fileHashDetails);
		return true;
	}

	@Override
	public String fetchHashDetails(String username, String fileName) {
		// TODO Auto-generated method stub
		FileHashDetails fileHashDetails = fileHashRepository.findByUserNameAndFileHash(username, fileName);
		return fileHashDetails == null ? "":fileHashDetails.getFileHash();
		//return "";
	}
}

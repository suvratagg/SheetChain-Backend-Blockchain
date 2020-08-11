package com.hellokoding.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hellokoding.springboot.entity.FileHashDetails;
import com.hellokoding.springboot.entity.User;
import com.hellokoding.springboot.entity.UserLogin;
import com.hellokoding.springboot.service.UserLoginService;
import com.hellokoding.springboot.service.UserService;


/**
 * @author suvrat.aggarwal
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userServiceImpl;

	@Autowired
	private UserLoginService userLoginServiceImpl;

	/**
	 * @return
	 */
	@RequestMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public List<User> findAll() {
		List<User> user = new ArrayList<>();
		user = (List<User>) userServiceImpl.findAll();
		return user;
	}

	/**
	 * @param username
	 * @param session
	 * @param request
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/find", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public List<User> find(@Param("username") String username, HttpSession session, HttpServletRequest request) {
		return userServiceImpl.find(username);
	}

	/**
	 * @param user
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public boolean add(@RequestBody User user) {
		userServiceImpl.add(user);
		UserLogin userLogin = new UserLogin();
		userLogin.setFirstName(user.getFirstName());
		userLogin.setLastName(user.getLastName());
		userLogin.setUsername(user.getUsername());
		userLogin.setUserType(user.getUserType());
		userLogin.setPassword(user.getPassword());
		userLogin.setEmail(user.getEmail());
		userLoginServiceImpl.add(userLogin);
		return true;
	}

	/**
	 * @param userId
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable(value = "id") Long userId) {
		userServiceImpl.delete(userId);
		return true;
	}

	/**
	 * @param userId
	 * @param updateUserDetails
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
	public User updateUser(@PathVariable(value = "id") Long userId, @RequestBody User updateUserDetails) {
		User updatedUser = userServiceImpl.updateUser(userId, updateUserDetails);
		return updatedUser;
	}

	/**
	 * @param userId
	 * @param updateUserDetails
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateAdmin/{id}", method = RequestMethod.PUT)
	public User updateAdmin(@PathVariable(value = "id") Long userId, @RequestBody User updateUserDetails) {
		User updatedUser = userServiceImpl.updateAdmin(userId, updateUserDetails);
		return updatedUser;
	}

	/**
	 * @param userRegister
	 * @return
	 * @throws MessagingException
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public boolean registerUser(@RequestBody UserLogin userRegister) {
		userLoginServiceImpl.registerUser(userRegister);		
		return true;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/saveHashDetails", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public boolean registerUser(@RequestBody FileHashDetails fileHashDetails) {
		//userLoginServiceImpl.save(fileHashDetails);
		userServiceImpl.saveHashDetails(fileHashDetails);
		return true;
	}

	/**
	 * @param username
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/findUsername", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public UserLogin findUsername(@Param("username") String username) {
		return userLoginServiceImpl.findUsername(username);
	}

	/**
	 * @param password
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/checkPassword", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public UserLogin findPassword(@RequestBody String password) {
		return userLoginServiceImpl.checkPassword(password);
	}

	/**
	 * @param request
	 * @param session
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return "Logout";
	}

	/**
	 * @param username
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteLoginUser/{username}", method = RequestMethod.DELETE)
	public int deleteUser(@PathVariable(value = "username") String username) {
		userLoginServiceImpl.deleteUser(username);
		return 1;
	}

	/**
	 * @param userId
	 * @param updateUserDetails
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public User update(@PathVariable(value = "id") Long userId, @RequestBody User updateUserDetails) {
		User updatedUser = userServiceImpl.update(userId, updateUserDetails);
		return updatedUser;
	}
}

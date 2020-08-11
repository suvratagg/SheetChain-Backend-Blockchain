package com.hellokoding.springboot.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hellokoding.springboot.entity.UserLogin;


/**
 * @author suvrat.aggarwal
 *
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	/**
	 * @param username
	 * @return
	 */
	@Query("FROM login u where u.username = ?1")
	UserLogin findUsername(String username);

	/**
	 * @param password
	 * @return
	 */
	@Query("FROM login u where u.password = ?1")
	UserLogin findPassword(String password);

	/**
	 * @param username
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("Delete FROM login u where u.username = ?1")
	int deleteUser(String username);
}
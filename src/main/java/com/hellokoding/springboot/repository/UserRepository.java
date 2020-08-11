package com.hellokoding.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hellokoding.springboot.entity.User;

/**
 * @author suvrat.aggarwal
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param username
	 * @return
	 */
	@Query("FROM user u where u.username like ?1%")
	List<User> findUser(String username);
}

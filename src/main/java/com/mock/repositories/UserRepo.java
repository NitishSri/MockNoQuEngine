package com.mock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysql.entities.User;


public interface UserRepo extends JpaRepository<User, Long> {

	/*@Query("select u from User u where LOWER(username) = Lower(:username)")
	List<User> findByUsernameIgnoreCase(@Param("username") String username);
	*/
	
	List<User> findByUsername(String username);
	
}

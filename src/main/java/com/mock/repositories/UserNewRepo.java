package com.mock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mysql.entities.UserNew;

public interface UserNewRepo extends CrudRepository<UserNew, Long> {

	//Native SQL method.
	@Query(value = "SELECT * FROM Users WHERE username  = :username", nativeQuery = true)
	List<UserNew> findByUsername(@Param("username") String username);

	
}

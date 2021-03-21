package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.User;


public interface IUserDao extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.email=:em and u.password=:pass")
	Optional<User> authenticateUser(@Param("em")String em,@Param("pass") String pwd);

	@Query("select u from User u where u.email=:em")
	Optional<User> updatePassword(@Param("em")String em);
	
	@Query("select u from User u where u.id=:id")
	Optional<User> getUserById(@Param("id")int id);
	

   
}

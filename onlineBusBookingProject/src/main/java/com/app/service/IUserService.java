package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {

	User authenticateUser(String email, String password);
	
    void updateUser(User user, int userId);
    
    List<User> listOfUser();
    
    public User registerUser(User u);
    
    User updatePassword(String email, String password);
    
    User getUserById(int id);

}

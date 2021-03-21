package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_excs.UserHandlingException;
import com.app.dao.IUserDao;
import com.app.pojos.RoleType;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
    
//--------------------------------------Authenticate User---------------------------------------------------------------------------------------------

	@Override
	public User authenticateUser(String email, String pwd) {
		return userDao.authenticateUser(email, pwd).
				orElseThrow(() -> new UserHandlingException("Invalid Credentials!!!!"));
	}

//-----------------------------------------Update User---------------------------------------------------------------------------------------------
	@Override
	public void updateUser(User user, int userId) 
	{ 
		User u = userDao.findById(userId).get();
		user.setUserId(userId);
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setRole(u.getRole());
		user.setSex(u.getSex());
		user.setContactNo(u.getContactNo());
		userDao.save(user);
	}
	
//------------------------------------------List Of User-------------------------------------------------------------------------------------------------------
    	
	@Override
	public List<User> listOfUser() {		
		return userDao.findAll();
	}
	
//------------------------------------------Register User------------------------------------------------------------------------------------------------------
    
	@Override
	public User registerUser(User u)
	{
		u.setRole(RoleType.USER);
		return userDao.save(u);
	}

	
//------------------------------------------Change Password-----------------------------------------------------------------------------------------------------

	@Override
	public User updatePassword(String email, String newPassword) {
		User u = userDao.updatePassword(email).orElseThrow(() -> new UserHandlingException("Invalid email!!!"));
		u.setPassword(newPassword);
		return userDao.save(u);
	}

//-------------------------------------------Get User by id----------------------------------------------------------------------------------------------------
	
	@Override
	public User getUserById(int id) {		
		return userDao.getUserById(id).get();
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
}





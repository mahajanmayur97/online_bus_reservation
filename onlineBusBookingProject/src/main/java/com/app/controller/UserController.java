package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in home controller");
	}
//-----------------------------Login------------------------------------------------------------------------------------------------------------------

	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody User u) {
		System.out.println("in valditae user" + u.getEmail() + "," + u.getPassword());
		try {
			return new ResponseEntity<>(userService.authenticateUser(u.getEmail(), u.getPassword()), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Invalid Password or email...Please enter valid data", HttpStatus.OK);
		}
	}
//----------------------------Registration-------------------------------------------------------------------------------------------------------------------

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u) {
		System.out.println("in register user");
		try {
			userService.registerUser(u);
			return new ResponseEntity<>("Registeration Done Succssfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Registeration Fail....", HttpStatus.OK);
		}
	}
//------------------------------Edit Profile-----------------------------------------------------------------------------------------------------------------

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int userId) {
		System.out.println("in update user" + user);
		System.out.println("in update user" + userId);
		try {
			userService.updateUser(user, userId);
			return ResponseEntity.ok().body(user);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
//-----------------------------Change Password------------------------------------------------------------------------------------------------------------------

	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody User u) {
		System.out.println("In change password");
		try {
			userService.updatePassword(u.getEmail(), u.getPassword());
			return new ResponseEntity<String>("Password updated successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("updation failed", HttpStatus.OK);
		}
	}

//-------------------------------------Get User by id----------------------------------------------------------------------------------------------------------

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) {

		try {
			return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------

}

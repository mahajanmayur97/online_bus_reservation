package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Bus;
import com.app.pojos.Routes;
import com.app.service.IBusService;
import com.app.service.IServiceRoutes;
import com.app.service.IUserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private IServiceRoutes routes;
	@Autowired
	private IBusService busService;
	@Autowired
	private IUserService userService;

	@DeleteMapping("/routes/{id}")
	public ResponseEntity<?> deleteRoutes(@PathVariable int id) {
		System.out.println("in delete routes ");
		try {
			routes.deleteRoutes(id);
			return new ResponseEntity<>("routes delete successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Invalid  routes id", HttpStatus.OK);
		}
	}

	@GetMapping("/getroutes")
	public ResponseEntity<?> getAllRoutes() {
		System.out.println("in get all routes ");
		try {
			return new ResponseEntity<>(routes.getAllRoutes(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Empty routes", HttpStatus.OK);
		}
	}

	@PostMapping("/routes")
	public ResponseEntity<?> addRoutes(@RequestBody Routes r) {
		System.out.println("in add routes ");
		try {
			routes.addRoutes(r);
			return new ResponseEntity<>("routes is added successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("routes is not added", HttpStatus.OK);
		}
	}

	@PostMapping("/bus/{id}")
	public ResponseEntity<?> addBus(@PathVariable int id, @RequestBody Bus b) {
		System.out.println("in addbus "+id);
		try {
			busService.addBus(id, b);
			return new ResponseEntity<>("bus is added successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("bus is not added", HttpStatus.OK);
		}
	}

	@GetMapping("/bus")
	public ResponseEntity<?> getAllBus()
	{
		System.out.println("in bus routes ");
		try {
			return new ResponseEntity<>(busService.getAllBuses(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Empty bus list", HttpStatus.OK);
		}
	}

	@DeleteMapping("/bus/{id}")
	public ResponseEntity<?> deleteBus(@PathVariable int id) {
		System.out.println("in delete bus ");
		try {
			busService.deleteBus(id);
			return new ResponseEntity<>("bus delete successfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Invalid  bus id", HttpStatus.OK);
		}
	}
	
	@GetMapping("/userlist")
	public ResponseEntity<?> getAllUser() {
		System.out.println("in get all users ");
		try {
			return new ResponseEntity<>(userService.listOfUser(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Empty user list", HttpStatus.OK);
		}
	}	
	
	
}

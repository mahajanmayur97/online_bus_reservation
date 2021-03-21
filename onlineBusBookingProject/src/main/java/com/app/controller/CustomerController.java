package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Bus;
import com.app.pojos.Routes;
import com.app.pojos.Seats;
import com.app.pojos.Tickets;
import com.app.service.IBusService;
import com.app.service.ISeatsService;
import com.app.service.ITicketService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	IBusService busService;

	@Autowired
	ITicketService ticketService;

	@Autowired
	ISeatsService seatService;

	@PostConstruct
	public void init() {
		System.out.println("Customer controller");
	}
//------------------------------------search Bus--------------------------------------------------------
	
	@PostMapping("/searchBus/{date}")
	public ResponseEntity<?> searchBus(@RequestBody Routes r,
			@PathVariable String date) {
		System.out.println("searchBus");
		try {
			return new ResponseEntity<List<Bus>>(busService.getBusByRoutes(r.getSource(), r.getDestination(), date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bus cannot be searched", HttpStatus.OK);
		}
	}

//-------------------------------------get Seats By Bus--------------------------------------------------------	

	@GetMapping("/viewSeats/{busId}/{bookDate}")
	public ResponseEntity<?> getSeatsByBus(@PathVariable String bookDate, @PathVariable int busId) {
		System.out.println("In get seats by bys" +bookDate);
		try {
			return new ResponseEntity<Seats>(seatService.getSeatsByBusId(busId, bookDate), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be searched", HttpStatus.OK);
		}
		
	}
	
//----------------------------------book Seats By Bus-------------------------------------------------------	
	
	@GetMapping("/bookSeats")
	public ResponseEntity<?> bookSeatsByBus(@RequestParam String date, int busId, int noOfSeats)
	{
		System.out.println("bookSeatsByBus");
		try {
			return new ResponseEntity<String>(seatService.bookSeatsByBus(busId, date, noOfSeats), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be booked", HttpStatus.OK);
		}
	}
	
//--------------------------------------add Seats By Bus------------------------------------------------------	
	
	@GetMapping("/addSeats/{busId}/{date}/{noOfSeats}")
	public ResponseEntity<?> addSeatsByBus(@PathVariable String date,@PathVariable int busId,@PathVariable int noOfSeats) {
		System.out.println("addSeatsByBus");
		try {
			return new ResponseEntity<String>(seatService.addSeatsByBus(busId, date, noOfSeats), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be added", HttpStatus.OK);
		}
	}
	
//---------------------------------------remove Seats By Bus------------------------------------------------------	

	@GetMapping("/removeSeats/{date}/{busId}")
	public ResponseEntity<?> removeSeatsByBus(@PathVariable String date, @PathVariable int busId) {
		System.out.println("removeSeatsByBus");
		try {
			return new ResponseEntity<String>(seatService.removeSeatsByBus(busId, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be removed", HttpStatus.OK);
		}
	}
	
//----------------------------------------book Ticket-----------------------------------------------------	

	@PostMapping("/bookTicket/{userId}/{busId}")
	public ResponseEntity<?> bookTicket(@RequestBody Tickets t, @PathVariable int userId, @PathVariable int busId)
	{
		System.out.println("bookTicket");
		try {
			return new ResponseEntity<Tickets>(ticketService.bookTickets(t, busId, userId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Seats for selected bus cannot be booked", HttpStatus.OK);
		}
	}
	
//------------------------------------cancel Ticket---------------------------------------------------	

	@DeleteMapping("/cancelTicket/{ticketId}")
	public ResponseEntity<?> cancelTicket(@PathVariable int ticketId)
	{
		System.out.println("cancelTicket");
		try {
			return new ResponseEntity<Tickets>(ticketService.cancelTickets(ticketId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tickets cannot be cancelled", HttpStatus.OK);
		}
	}

//--------------------------------get Tickets of User-------------------------------------------------------	

	@GetMapping("/getUserTickets/{userId}")
	public ResponseEntity<?> getTicketsofUser(@PathVariable int userId)
	{
		System.out.println("getTicketsofUser");
		try {
			return new ResponseEntity<List<Tickets>>(ticketService.getTicketsOfUser(userId), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tickets cannot be fetched for user", HttpStatus.OK);
		}
	}
	
//--------------------------------------get Tickets of Bus------------------------------------------------------	
	
	@GetMapping("/getBusTickets/{busId}/{date}")
	public ResponseEntity<?> getTicketsofBus(@PathVariable int busId, @PathVariable String date)
	{
		System.out.println("getTicketsofBus"+busId+date);
		try {
			return new ResponseEntity<List<Tickets>>(ticketService.getTicketsOfBus(busId, date), HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Tickets cannot be fetched for Bus", HttpStatus.OK);
		}
	}
	
//---------------------------------------------------------------------------------------------	

}

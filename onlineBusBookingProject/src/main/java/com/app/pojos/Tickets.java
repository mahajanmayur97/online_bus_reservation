package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tickets {
	private Integer id;
	private Date bookDate;
	private String ownerName;
	private String phoneNo;
	private double fare;
	private int noOfSeats;
	private Bus busId;
	private User userId;
	List<Passenger> passengers = new ArrayList<Passenger>();

	public Tickets() {
		System.out.println("in tickets ctr ..");
	}

	public Tickets(Date bookDate, String ownerName, String phoneNo, double fare, int noOfSeats) {
		super();
		this.bookDate = bookDate;
		this.ownerName = ownerName;
		this.phoneNo = phoneNo;
		this.fare = fare;
		this.noOfSeats = noOfSeats;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	@Column(length = 30, nullable = false)
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(nullable = false)
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(nullable = false)
	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Column(nullable = false)
	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@ManyToOne
	@JoinColumn(name = "busId")
	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "ticketId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void addPassenger(Passenger p) {
		this.passengers.add(p);
		p.setTicketId(this);
	}

	public void removePassenger(Passenger p) {
		this.passengers.remove(p);
		p.setTicketId(null);
	}

	@Override
	public String toString() {
		return "Tickets [id=" + id + ", bookDate=" + bookDate + ", ownerName=" + ownerName + ", phoneNo=" + phoneNo
				+ ", fare=" + fare + ", noOfSeats=" + noOfSeats + "]";
	}

}

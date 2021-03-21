package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bus {
	private Integer id;
	private String busNo;
	private int capacity;
	private BusType type;
	private Date arrivalDate;
	private Date destinationDate;
	private double price;
	private Routes routeId;
	private DayWhenRuns frequency;
	private List<Seats> seats = new ArrayList<>();
	private List<Tickets> tickets = new ArrayList<Tickets>();

	public Bus() {
		System.out.println("in bus pojo ctr..");
	}

	public Bus(String busNo, int capacity, BusType type, Date arrivalDate, Date destinationDate, double price,
			DayWhenRuns frequency) {
		super();
		this.busNo = busNo;
		this.capacity = capacity;
		this.type = type;
		this.arrivalDate = arrivalDate;
		this.destinationDate = destinationDate;
		this.price = price;
		this.frequency = frequency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 30, nullable = false)
	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	@Column(nullable = false)
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	public BusType getType() {
		return type;
	}

	public void setType(BusType type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "Asia/Kolkata")
	@Column(nullable = false)
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@OneToOne(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
	public DayWhenRuns getFrequency() {
		return frequency;
	}

	public void addSeats(Seats seats) {
		this.seats.add(seats);
		seats.setBusId(this);
	}

	public void removeSeats(Seats seats) {
		this.seats.remove(seats);
		seats.setBusId(null);
	}
	
	public void setFrequency(DayWhenRuns frequency) {
		this.frequency = frequency;
	}
	
	public void addFrequency(DayWhenRuns f) {
		this.frequency = f;
		f.setBusId(this);
	}

	public void removeFrequency(DayWhenRuns f) {
		this.frequency = null;
		f.setBusId(null);
	}


	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "Asia/Kolkata")
	@Column(nullable = false)
	public Date getDestinationDate() {
		return destinationDate;
	}

	public void setDestinationDate(Date destinationDate) {
		this.destinationDate = destinationDate;
	}

	@Column(nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "routeId")
	@JsonBackReference
	public Routes getRouteId() {
		return routeId;
	}

	public void setRouteId(Routes routeId) {
		this.routeId = routeId;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "busId", cascade = CascadeType.ALL)
	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "busId", cascade = CascadeType.ALL)
	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	public void addTickets(Tickets tickets) {
		this.tickets.add(tickets);
		tickets.setBusId(this);
	}

	public void removeTickets(Tickets tickets) {
		this.tickets.remove(tickets);
		tickets.setBusId(null);
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", busNo=" + busNo + ", capacity=" + capacity + ", type=" + type + ", arrivalDate="
				+ arrivalDate + ", destinationDate=" + destinationDate + ", price=" + price + "]";
	}

}

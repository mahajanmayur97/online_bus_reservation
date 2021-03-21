package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Seats {
	private Integer id;
	private int availableSeats;
	private LocalDate bookDate;
	private Bus busId;

	public Seats() {
		System.out.println("in seats ctr...");
	}

	public Seats(int availableSeats, LocalDate bookDate) {
		super();
		this.availableSeats = availableSeats;
		this.bookDate = bookDate;
	}

	public Seats(LocalDate bookDate, Bus busId) {
		super();
		this.bookDate = bookDate;
		this.busId = busId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
     
	
	@Column(nullable = false)
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Column(nullable = false) 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="busId")
	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}

	@Override
	public String toString() {
		return "Seats [id=" + id + ", availableSeats=" + availableSeats + ", bookDate=" + bookDate + "]";
	}

}

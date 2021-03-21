package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Passenger {
	private Integer id;
	private String name;
	private int age;
	private Tickets ticketId;

	public Passenger() {
		System.out.println("In passager ctr..");
	}

	public Passenger(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@ManyToOne
	@JoinColumn(name = "ticketId")
	public Tickets getTicketId() {
		return ticketId;
	}

	public void setTicketId(Tickets ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public String toString() {
		return "Passanger [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}

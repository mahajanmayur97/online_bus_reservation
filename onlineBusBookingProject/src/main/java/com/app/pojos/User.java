package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private long contactNo;
	private String city;
	private int pin;
	private SexType sex;
	private int age;
	private RoleType role;
	private List<Tickets> tickets = new ArrayList<Tickets>();

	public User() {
		System.out.println("in user ctr..");
	}

	public User(String name, String email, String password, long contactNo, String city, int pin, SexType sex, int age,
			RoleType role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
		this.city = city;
		this.pin = pin;
		this.sex = sex;
		this.age = age;
		this.role = role;
	}

	public User(String email, long contactNo) {
		super();
		this.email = email;
		this.contactNo = contactNo;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20, nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
  //  @JsonIgnore
	@Column(length = 30, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 12)
	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	@Column(length = 30, nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(nullable = false)
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	public void addTickets(Tickets tickets) {
		this.tickets.add(tickets);
		tickets.setUserId(this);
	}

	public void removeTickets(Tickets tickets) {
		this.tickets.remove(tickets);
		tickets.setUserId(null);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo + ", city=" + city + ", pin=" + pin + ", age=" + age + "]";
	}

}

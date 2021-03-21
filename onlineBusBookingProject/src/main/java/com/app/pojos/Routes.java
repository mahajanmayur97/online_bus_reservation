package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Routes {
	private Integer id;
	private String source;
	private String destination;
	private List<Bus> buses = new ArrayList<>();

	public Routes() {
		System.out.println("in routes ctr..");
	}

	public Routes(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 25, nullable = false)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(length = 25, nullable = false)
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "routeId", orphanRemoval = true)
	@JsonManagedReference
	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public void addBus(Bus b) {
		System.out.println("in routes add bus");
		buses.add(b);
		b.setRouteId(this);
		System.out.println(b.getRouteId());
	}

	public void removeBus(Bus b) {
		buses.remove(b);
		b.setRouteId(null);
	}

	@Override
	public String toString() {
		return "Routes [id=" + id + ", source=" + source + ", destination=" + destination + "]";
	}

}

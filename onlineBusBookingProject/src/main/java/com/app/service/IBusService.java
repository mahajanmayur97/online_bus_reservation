package com.app.service;

import java.util.List;

import com.app.pojos.Bus;

public interface IBusService {

	void addBus(int id, Bus b);

	List<Bus> getAllBuses();

	void deleteBus(int id);

	List<Bus> getBusByRoutes(String source, String destination, String date);
}

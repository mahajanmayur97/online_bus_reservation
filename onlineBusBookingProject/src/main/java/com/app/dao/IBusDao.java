package com.app.dao;

import java.util.List;

import com.app.pojos.Bus;

public interface IBusDao {

	void addBus(int id, Bus b);

	List<Bus> getAllBuses();

	void deleteBus(int id);

	List<Bus> getBusByRoutes(String source, String destination, String date);
}

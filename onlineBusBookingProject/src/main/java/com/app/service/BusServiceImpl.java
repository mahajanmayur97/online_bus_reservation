package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IBusDao;
import com.app.pojos.Bus;

@Service
@Transactional
public class BusServiceImpl implements IBusService {
	@Autowired
	private IBusDao dao;

	@Override
	public void addBus(int id, Bus b) {
		dao.addBus(id, b);
	}

	@Override
	public List<Bus> getAllBuses() {

		return dao.getAllBuses();
	}

	@Override
	public void deleteBus(int id) {
		dao.deleteBus(id);
	}

	@Override
	public List<Bus> getBusByRoutes(String source, String destination, String date) {
		return dao.getBusByRoutes(source, destination, date);
	}

}

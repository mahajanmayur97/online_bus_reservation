package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.BusType;
import com.app.pojos.DayFromDate;
import com.app.pojos.Routes;

@Repository
public class BusDaoImpl implements IBusDao {
	@Autowired
	private EntityManager mgr;

	@Override
	public void addBus(int id, Bus b) {
		String jpql = "select r from Routes  r where r.id=:i";
		Routes r = mgr.createQuery(jpql, Routes.class).setParameter("i", id).getSingleResult();
		b.setType(BusType.SEATER_NAC);
		b.setRouteId(r);
		mgr.persist(b);
	}

	@Override
	public List<Bus> getAllBuses() {
		String jpql = "select r from Bus r";
		return mgr.createQuery(jpql, Bus.class).getResultList();
	}

	@Override
	public void deleteBus(int id) {
		String jpql = "select r from Bus r where r.id=:i";
		Bus r = mgr.createQuery(jpql, Bus.class).setParameter("i", id).getSingleResult();
		mgr.remove(r);
	}

	@Override
	public List<Bus> getBusByRoutes(String source, String destination, String date) {
		System.out.println("in get bus by routes");
		System.out.println(source);
		System.out.println(destination);
		System.out.println(date);
		int day = DayFromDate.getDayInt(date);
		List<Bus> buses = new ArrayList<Bus>();
		String jpql = "select r from Routes r where r.source=:source and r.destination=:destination";
		Routes r = mgr.createQuery(jpql, Routes.class).setParameter("source", source)
				.setParameter("destination", destination).getSingleResult();
		System.out.println(r);
		int route_id = r.getId();
		System.out.println(route_id);
		String jpql2 = "select b from Bus b where b.routeId = :routeId";
		List<Bus> list = mgr.createQuery(jpql2, Bus.class).setParameter("routeId", r).getResultList();
		System.out.println(list);
		System.out.println("listing");
		for (Bus b : list) {
			System.out.println(b);
			b.toString();

			buses.add(b);
		}
		return buses;
	}

}

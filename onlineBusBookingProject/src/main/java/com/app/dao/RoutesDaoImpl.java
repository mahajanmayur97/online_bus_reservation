package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Routes;

@Repository
public class RoutesDaoImpl implements IRoutesDao {
	@Autowired
	private EntityManager mgr;

	@Override
	public void addRoutes(Routes u) {
		mgr.persist(u);
	}

	@Override
	public List<Routes> getAllRoutes() {
		String jpql = "select r from Routes r";
		return mgr.createQuery(jpql, Routes.class).getResultList();
	}

	@Override
	public void deleteRoutes(int id) {
		String jpql = "select r from Routes r where r.id=:i";
		Routes r=mgr.createQuery(jpql, Routes.class).setParameter("i", id).getSingleResult();
		mgr.remove(r);
	}

}

package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IRoutesDao;
import com.app.pojos.Routes;

@Service
@Transactional
public class RoutesServiceimpl implements IServiceRoutes {
	@Autowired
	private IRoutesDao routesDao;

	@Override
	public void addRoutes(Routes u) {
		routesDao.addRoutes(u);
	}

	@Override
	public List<Routes> getAllRoutes() {
		
		return routesDao.getAllRoutes();
	}

	@Override
	public void deleteRoutes(int id) {
		routesDao.deleteRoutes(id);

	}

	

}

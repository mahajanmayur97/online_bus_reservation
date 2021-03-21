package com.app.service;

import java.util.List;

import com.app.pojos.Routes;

public interface IServiceRoutes {
	void addRoutes(Routes u);

	List<Routes> getAllRoutes();

	void deleteRoutes(int id);

}

package com.app.dao;

import java.util.List;

import com.app.pojos.Routes;

public interface IRoutesDao {

	void addRoutes(Routes u);

	List<Routes> getAllRoutes();

	void deleteRoutes(int id);

}

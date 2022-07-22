package com.qa.logincontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Create, Read, Update and Delete controller. Takes in inputs for each action
 * to be sent to a service class
 */
public interface CrudController<T> {

	List<T> readAll();

	T read(Long id);

	T create();

	T update();

	int delete(Long id);

	T modelFromResultSet(ResultSet resultSet) throws SQLException;

}

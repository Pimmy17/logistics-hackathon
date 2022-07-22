package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrdersAssignment;
import com.qa.logisticshackathon.utils.DBUtils;

public class OrdersAssignmentsDAO implements Dao<OrdersAssignment> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrdersAssignment modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderproduct_id = resultSet.getLong("orderproduct_id");
		Long fk_order_id = resultSet.getLong("order_id");
		Long fk_product_id = resultSet.getLong("product_id");
		Long fk_user_id = resultSet.getLong("user_id");
		String product_name = resultSet.getString("product_name");
		String customer_name = resultSet.getString("customer_name");
		Integer quantity = resultSet.getInt("quantity");
		Boolean delivery_status = resultSet.getBoolean("delivery_status");
		return new OrdersAssignment(orderproduct_id, fk_order_id, fk_product_id, fk_user_id, product_name, quantity,
				delivery_status, customer_name);
	}

	/**
	 * Reads all order assignments from the database
	 * 
	 * @return A list of assignments
	 */
	@Override
	public List<OrdersAssignment> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM orderassignment JOIN orders ON orders.order_id = orderassignment.fk_order_id JOIN products ON products.product_id = orderassignment.fk_product_id JOIN users ON users.user_id = orderassignment.fk_user_id");) {
			List<OrdersAssignment> orderAssignments = new ArrayList<>();
			while (resultSet.next()) {
				orderAssignments.add(modelFromResultSet(resultSet));
			}
			return orderAssignments;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrdersAssignment readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM orderassignment JOIN orders ON orders.order_id = orderassignment.fk_order_id JOIN products ON products.product_id = orderassignment.fk_product_id JOIN users ON users.user_id = orderassignment.fk_user_id ORDER BY orderproduct_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a product in the database
	 * 
	 * @param product - takes in a product object. id will be ignored
	 */
	@Override
	public OrdersAssignment create(OrdersAssignment ordersAssignment) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderassignment(fk_order_id fk_user_id) VALUES (?, ?)");) {
			statement.setLong(1, ordersAssignment.getFk_order_id());
			statement.setLong(2, ordersAssignment.getFk_user_id());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrdersAssignment read(Long orderproduct_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM orderassignment JOIN orders ON orders.order_id = orderassignment.fk_order_id JOIN products ON products.product_id = orderassignment.fk_product_id JOIN users ON users.user_id = orderassignment.fk_user_id WHERE orderproduct_id = ?");) {
			statement.setLong(1, orderproduct_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an order assignment in the database
	 * 
	 * @param order assignment - takes in an assignment object, the id field will be
	 *              used to update that assignment in the database
	 * @return
	 */

	@Override
	public OrdersAssignment update(OrdersAssignment ordersAssignment) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE orderassignment SET fk_user_id = ? WHERE orderproduct_id = ? AND fk_order_id = ?");) {
			statement.setLong(1, ordersAssignment.getFk_user_id());
			statement.setLong(2, ordersAssignment.getOrderproduct_id());
			statement.setLong(3, ordersAssignment.getFk_order_id());
			statement.executeUpdate();
			return read(ordersAssignment.getOrderproduct_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrdersAssignment addOrder(OrdersAssignment ordersAssignment) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orderassignment (fk_orders_id) VALUES (SELECT order_id FROM orders WHERE order_id = ?);");) {
			statement.setLong(1, ordersAssignment.getFk_order_id());
			statement.executeUpdate();
			return read(ordersAssignment.getFk_order_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrdersAssignment removeOrder(OrdersAssignment ordersAssignment) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statementOne = connection.prepareStatement(
						"UPDATE orderassignment JOIN orders ON orders.order_id = orderassignment.fk_order_id SET orders.quantity = 0 WHERE orders.order_id = ? && orderproduct_id = ?");
				PreparedStatement statementTwo = connection.prepareStatement(
						"DELETE FROM orderassignment JOIN orders ON orders.order_id = orderassignment.fk_order_id WHERE orders.quantity = 0");) {
			statementOne.setLong(1, ordersAssignment.getFk_order_id());
			statementOne.setLong(2, ordersAssignment.getOrderproduct_id());
			statementOne.executeUpdate();
			statementTwo.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrdersAssignment deliverOrder(OrdersAssignment ordersAssignment) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE orderassignment SET delivery_status = ? WHERE orderproduct_id = ? AND fk_order_id = ?");) {
			statement.setBoolean(1, ordersAssignment.getDelivery_status());
			statement.setLong(2, ordersAssignment.getOrderproduct_id());
			statement.setLong(3, ordersAssignment.getFk_order_id());
			statement.executeUpdate();
			return read(ordersAssignment.getOrderproduct_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an order assignment in the database
	 * 
	 * @param id - id of the assignment
	 */
	@Override
	public int delete(long orderproduct_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orderassignment WHERE orderproduct_id = ?");) {
			statement.setLong(1, orderproduct_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}

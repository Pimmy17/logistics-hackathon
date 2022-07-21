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
import com.qa.ims.utils.DBUtils;

public class OrdersAssignmentsDAO implements Dao<OrdersAssignment> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrdersAssignment modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderproduct_id = resultSet.getLong("orderproduct_id");
		Long fk_order_id = resultSet.getLong("order_id");
		Long fk_product_id = resultSet.getLong("product_id");
		Integer quantity = resultSet.getInt("quantity");
		return new OrdersAssignment(orderproduct_id, fk_order_id, fk_product_id, quantity);
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderassignment");) {
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
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderassignment ORDER BY orderproduct_id DESC LIMIT 1");) {
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
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orderassignment(fk_order_id, fk_product_id, fk_user_id, quantity) VALUES (?, ?, ?, ?)");) {
			statement.setLong(1, ordersAssignment.getFk_order_id());
			statement.setLong(2, ordersAssignment.getFk_product_id());
			statement.setLong(3, ordersAssignment.getFk_user_id());
			statement.setInt(4, ordersAssignment.getQuantity());

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
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orderassignment WHERE orderproduct_id = ?");) {
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

	// ************CURRENTLY HERE*******************
	@Override
	public OrdersAssignment update(OrdersAssignment ordersAssignment) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orderassignment SET quantity = ? WHERE orderproduct_id = ? AND ");) {
			statement.setString(1, ordersAssignment.getQuantity());
			statement.setLong(2, ordersAssignment.getOrderproduct_id());
			statement.executeUpdate();
			return read(ordersAssignment.getOrderproduct_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a product in the database
	 * 
	 * @param id - id of the product
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}

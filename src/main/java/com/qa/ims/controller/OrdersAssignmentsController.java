package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersAssignmentsDAO;
import com.qa.ims.persistence.domain.OrdersAssignment;
import com.qa.ims.utils.Utils;

public class OrdersAssignmentsController implements CrudController<OrdersAssignment> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrdersAssignmentsDAO ordersAssignmentsDAO;
	private Utils utils;

	public OrdersAssignmentsController(OrdersAssignmentsDAO ordersAssignmentsDAO, Utils utils) {
		super();
		this.ordersAssignmentsDAO = ordersAssignmentsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all order assignments to the logger
	 */
	@Override
	public List<OrdersAssignment> readAll() {
		List<OrdersAssignment> ordersAssignments = ordersAssignmentsDAO.readAll();
		for (OrdersAssignment ordersAssignment : ordersAssignments) {
			LOGGER.info(ordersAssignment);
		}
		return ordersAssignments;
	}

	/**
	 * Creates an order assignment by taking in user input
	 */
	@Override
	public OrdersAssignment create() {
		LOGGER.info("Please enter an Order ID");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter a product ID");
		Long productID = utils.getLong();
		LOGGER.info("Please enter a quantity ID");
		Integer quantity = utils.getInt();
		LOGGER.info("Please enter a driver ID for delivery");
		Long userID = utils.getLong();
		OrdersAssignment ordersAssignment = ordersAssignmentsDAO
				.create(new OrdersAssignment(orderID, productID, quantity, userID));
		LOGGER.info("Order Assignment created");
		return ordersAssignment;
	}

	/**
	 * Updates an existing order assignment by taking in user input
	 */
	@Override
	public OrdersAssignment update() {
		LOGGER.info("Please enter the id of the assignment you would like to update");
		Long orderproduct_id = utils.getLong();
		LOGGER.info("Please enter an order ID");
		Long fk_order_id = utils.getLong();
		LOGGER.info("Please enter a product ID");
		Long fk_product_id = utils.getLong();
		LOGGER.info("Please enter the new quantity");
		Integer quantity = utils.getInt();
		LOGGER.info("Please enter a driver ID for delivery");
		Long fk_user_id = utils.getLong();
		OrdersAssignment ordersAssignment = ordersAssignmentsDAO
				.update(new OrdersAssignment(orderproduct_id, fk_order_id, fk_product_id, quantity, fk_user_id));
		LOGGER.info("Order Assignment Updated");
		return ordersAssignment;
	}

//	@Override
//	public OrdersAssignment delivery() {
//		LOGGER.info("Please enter the ");
//	}

	/**
	 * Deletes an existing order assignment by the id of the assignment
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the assignment you would like to delete");
		Long id = utils.getLong();
		return ordersAssignmentsDAO.delete(id);
	}

}

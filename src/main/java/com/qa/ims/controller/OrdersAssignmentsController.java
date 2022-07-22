package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersAssignmentsDAO;
import com.qa.ims.persistence.domain.OrdersAssignment;
import com.qa.logisticshackathon.utils.Utils;

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
		LOGGER.info("Please enter a driver ID for delivery");
		Long userID = utils.getLong();
		OrdersAssignment ordersAssignment = ordersAssignmentsDAO.create(new OrdersAssignment(orderID, userID));
		LOGGER.info("Order Assignment created");
		return ordersAssignment;
	}

	/**
	 * Updates an existing order assignment by taking in user input
	 */
	@Override
	public OrdersAssignment update() {
		LOGGER.info("Please enter your role");
		String role = utils.getString();
		role = role.toLowerCase();
		if (role.equals("manager")) {
			LOGGER.info("Please enter the id of the assignment you would like to update");
			Long orderproduct_id = utils.getLong();

			LOGGER.info(
					"Would you like to add or remove an order to an assignment or update a driver? \n Please Enter Add, Remove or Update");
			String addRemove = utils.getString();
			addRemove = addRemove.toLowerCase();

			if (addRemove == "add") {
				LOGGER.info("Please enter the ID of the order you want to add to the assignment");
				Long order_id = utils.getLong();
				Orders order = new Orders(order_id);
				OrderDAO orderDAO = new OrderDAO();
				order = orderDAO.read(order.getOrder_id());
				OrdersAssignment ordersAssignment = ordersAssignmentsDAO
						.addOrder(new OrdersAssignment(orderproduct_id, orders.order_id));
				LOGGER.info("Order Added to Assignment");
				return order;
			} else if (addRemove == "remove") {
				LOGGER.info("Please enter the id of the order you wish to remove from the assignment");
				Long order_id = utils.getLong();
				OrdersAssignment ordersAssignment = new OrdersAssignment();
				ordersAssignment.setOrderproduct_id(orderproduct_id);
				ordersAssignment.setFk_order_id(order_id);
				ordersAssignmentsDAO.removeOrder(ordersAssignment);
				LOGGER.info("Order Removed from Assignment");
				return ordersAssignment;
			} else if (addRemove == "update") {
				LOGGER.info("Please enter the ID of the assignment you want to change the driver for");
				Long order_id = utils.getLong();
				LOGGER.info("Please enter a driver ID for delivery");
				Long fk_user_id = utils.getLong();
				OrdersAssignment ordersAssignment = ordersAssignmentsDAO
						.update(new OrdersAssignment(orderproduct_id, orders.order_id, fk_user_id));
				LOGGER.info("Order Assignment Updated");
				return ordersAssignment;
			}
			return null;
		} else if (role.equals("driver")) {
			LOGGER.info("Has this order been delivered? True/False");
			Boolean delivery_status = utils.getBoolean();
			OrdersAssignment ordersAssignment = ordersAssignmentsDAO
					.deliverOrder(new OrdersAssignment(delivery_status));
			LOGGER.info("Order Assignment Updated");
			return ordersAssignment;
		}
		return null;
	}

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

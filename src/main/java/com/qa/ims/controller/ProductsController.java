package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ProductsDAO;
import com.qa.ims.persistence.domain.Product;
import com.qa.logisticshackathon.utils.Utils;

public class ProductsController implements CrudController<Product> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ProductsDAO productDAO;
	private Utils utils;

	public ProductsController(ProductsDAO productDAO, Utils utils) {
		super();
		this.productDAO = productDAO;
		this.utils = utils;
	}

	/**
	 * Reads all products to the logger
	 */
	@Override
	public List<Product> readAll() {
		List<Product> products = productDAO.readAll();
		for (Product product : products) {
			LOGGER.info(product);
		}
		return products;
	}

	/**
	 * Creates a product by taking in user input
	 */
	@Override
	public Product create() {
		LOGGER.info("Please enter an product name");
		String product_name= utils.getString();
		Product product = productDAO.create(new Product(product_name));
		LOGGER.info("Product created");
		return product;
	}

	/**
	 * Updates an existing product by taking in user input
	 */
	@Override
	public Product update() {
		LOGGER.info("Please enter the ID of the product you would like to update");
		Long product_id = utils.getLong();
		LOGGER.info("Please enter an product name");
		String product_name = utils.getString();
		Product product = productDAO.update(new Product(product_id, product_name));
		LOGGER.info("Product Updated");
		return product;
	}

	/**
	 * Deletes an existing product by the id of the product
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the product you would like to delete");
		Long product_id = utils.getLong();
		return productDAO.delete(product_id);
	}

}

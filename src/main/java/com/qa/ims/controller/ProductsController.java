package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ProductsDAO;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;

/**
 * Takes in product details for CRUD functionality
 *
 */
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
		LOGGER.info("Please enter an item name");
		String productName = utils.getString();

		Product product = productDAO.create(new Product(productName));
		LOGGER.info("Product created");
		return product;
	}

	/**
	 * Updates an existing product by taking in user input
	 */
	@Override
	public Product update() {
		LOGGER.info("Please enter the id of the product you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter an item name");
		String productName = utils.getString();
		Product product = productDAO.update(new Product(id, productName));
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
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long id = utils.getLong();
		return productDAO.delete(id);
	}

}

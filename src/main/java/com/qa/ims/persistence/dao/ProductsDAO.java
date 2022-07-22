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

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;

public class ProductsDAO implements Dao<Product> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Product modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long product_id = resultSet.getLong("product_id");
		String product_name = resultSet.getString("product_name");
		return new Product(product_id, product_name);
	}

	/**
	 * Reads all products from the database
	 * 
	 * @return A list of products
	 */
	@Override
	public List<Product> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products");) {
			List<Product> products = new ArrayList<>();
			while (resultSet.next()) {
				products.add(modelFromResultSet(resultSet));
			}
			return products;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Product readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products ORDER BY product_id DESC LIMIT 1");) {
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
	public Product create(Product product) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO products(product_name) VALUES (?)");) {
			statement.setString(1, product.getProductName());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Product read(Long product_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");) {
			statement.setLong(1, product_id);
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
	 * Updates a product in the database
	 * 
	 * @param product - takes in a product object, the id field will be used to
	 *                update that product in the database
	 * @return
	 */
	@Override
	public Product update(Product product) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE products SET product_name = ? WHERE product_id = ?");) {
			statement.setString(1, product.getProductName());
			statement.setLong(2, product.getProduct_id());
			statement.executeUpdate();
			return read(product.getProduct_id());
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
	public int delete(Long product_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?");) {
			statement.setLong(1, product_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}

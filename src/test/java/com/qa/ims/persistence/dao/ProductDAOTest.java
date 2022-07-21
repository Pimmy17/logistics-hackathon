package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;

public class ProductDAOTest {

	private final ProductsDAO DAO = new ProductsDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Product created = new Product(2L, "chris");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Product> expected = new ArrayList<>();
		expected.add(new Product(1L, "jordan"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Product(1L, "jordan"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Product(ID, "jordan"), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Product updated = new Product(1L, "chris");
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}

package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long order_id;
	private Long product_id;
	private String product_name;
	private List<Product> product_list;
	private String customer_name;
	private String customer_address;
	
	public Order(String customer_name) {
		this.customer_name = customer_name;
	}
	
	public Order(Long order_id, Long product_id) {
		this.order_id = order_id;
		this.product_id = product_id;
	}
	
	public Order(Long order_id, String customer_name, String customer_address) {
		this.order_id = order_id;
		this.customer_name = customer_name;
		this.customer_address = customer_address;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public List<Product> getProducts() {
		return product_list;
	}
	
	public void setProducts(List<Product> product_list) {
		this.product_list = product_list;
	}
	
	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	@Override
	public String toString() {
		return "Order ID: " + order_id + " Products: " + product_name + " Customer Name: " + customer_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

}
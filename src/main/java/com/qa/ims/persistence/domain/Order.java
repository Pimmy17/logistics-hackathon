package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long order_id;
	private Long product_id;
	private String product_name;
	private List<Product> product_list;
	
	public Order(Long order_id) {
		this.order_id = order_id;
	}
	
	public Order(Long order_id, List<Product> product_list) {
		this.order_id = order_id;
		this.product_list = product_list;
	}
	
	public Order(Long product_id) {
		
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
	

	@Override
	public String toString() {
		return "Order ID: " + order_id + " Products: " + product_list;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

}
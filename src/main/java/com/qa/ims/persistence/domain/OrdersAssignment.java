package com.qa.ims.persistence.domain;

public class OrdersAssignment {

	private Long orderproduct_id;
	private Long fk_product_id;
	private Long fk_order_id;
	private Long fk_user_id;
	private Boolean delivery_status;
	private Integer quantity;
	private String product_name;
	private String customer_name;

	public OrdersAssignment(Long orderproduct_id, Long fk_order_id, Long fk_product_id, Long fk_user_id,
			String product_name, Integer quantity, Boolean delivery_status, String customer_name) {
		this.setDelivery_status(delivery_status);
		this.setFk_order_id(fk_order_id);
		this.setFk_product_id(fk_product_id);
		this.setOrderproduct_id(orderproduct_id);
		this.setFk_user_id(fk_user_id);
		this.setQuantity(quantity);
		this.setCustomer_name(customer_name);
		this.setProduct_name(product_name);
	}

	public OrdersAssignment(Long orderID, Long productID, Integer quantity, Long userID) {
		this.setFk_order_id(fk_order_id);
		this.setFk_product_id(fk_product_id);
		this.setQuantity(quantity);
		this.setFk_user_id(fk_user_id);
	}

	public OrdersAssignment(Long orderproduct_id, Long fk_order_id, Long fk_product_id, Integer quantity,
			Long fk_user_id) {
		this.setOrderproduct_id(orderproduct_id);
		this.setFk_order_id(fk_order_id);
		this.setFk_product_id(fk_product_id);
		this.setQuantity(quantity);
		this.setFk_user_id(fk_user_id);
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Long getFk_user_id() {
		return fk_user_id;
	}

	public Long getOrderproduct_id() {
		return orderproduct_id;
	}

	public void setOrderproduct_id(Long orderproduct_id) {
		this.orderproduct_id = orderproduct_id;
	}

	public Long getFk_product_id() {
		return fk_product_id;
	}

	public void setFk_product_id(Long fk_product_id) {
		this.fk_product_id = fk_product_id;
	}

	public Long getFk_order_id() {
		return fk_order_id;
	}

	public void setFk_order_id(Long fk_order_id) {
		this.fk_order_id = fk_order_id;
	}

	public Bool getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(Bool delivery_status) {
		this.delivery_status = delivery_status;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void getFk_user_id(Long fk_user_id) {
		return fk_user_id;
	}

	public void setFk_user_id(Long fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

	@Override
	public String toString() {
		return "Assignment ID: " + orderproduct_id + ", Customer: " + customer_name + "Product: " + fk_product_id
				+ ", Quantity: " + quantity + ", Delivery Status: " + delivery_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderproduct_id == null) ? 0 : orderproduct_id.hashCode());
		result = prime * result + ((fk_product_id == null) ? 0 : fk_product_id.hashCode());
		result = prime * result + ((fk_order_id == null) ? 0 : fk_order_id.hashCode());
		result = prime * result + ((fk_user_id == null) ? 0 : fk_user_id.hashCode());
		result = prime * result + ((delivery_status == null) ? 0 : delivery_status.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdersAssignment other = (OrdersAssignment) obj;
		if (getProduct_name() == null) {
			if (other.getProduct_name() != null)
				return false;
		} else if (!getProduct_name().equals(other.getProduct_name()))
			return false;
		if (orderproduct_id == null) {
			if (other.orderproduct_id != null)
				return false;
		} else if (!orderproduct_id.equals(other.orderproduct_id))
			return false;

		if (fk_product_id == null) {
			if (other.fk_product_id != null)
				return false;
		} else if (!fk_product_id.equals(other.fk_product_id))
			return false;

		if (fk_order_id == null) {
			if (other.fk_order_id != null)
				return false;
		} else if (!fk_order_id.equals(other.fk_order_id))
			return false;

		if (fk_user_id == null) {
			if (other.fk_user_id != null)
				return false;
		} else if (!fk_user_id.equals(other.fk_user_id))
			return false;

		if (delivery_status == null) {
			if (other.delivery_status != null)
				return false;
		} else if (!delivery_status.equals(other.delivery_status))
			return false;

		if (customer_name == null) {
			if (other.customer_name != null)
				return false;
		} else if (!customer_name.equals(other.customer_name))
			return false;

		return true;
	}

}

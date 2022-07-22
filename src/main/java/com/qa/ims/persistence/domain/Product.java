package com.qa.ims.persistence.domain;

public class Product {

	private Long product_id;
	private String product_name;

	public Product(String product_name) {
		this.setProductName(product_name);
	}

	public Product(Long product_id, String product_name) {
		this.setProduct_id(product_id);
		this.setProductName(product_name);
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return product_name;
	}

	public void setProductName(String product_name) {
		this.product_name = product_name;
	}

	@Override
	public String toString() {
		return "ID: " + product_id + " Product: " + product_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
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
		Product other = (Product) obj;
		if (getProductName() == null) {
			if (other.getProductName() != null)
				return false;
		} else if (!getProductName().equals(other.getProductName()))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		return true;
	}

}

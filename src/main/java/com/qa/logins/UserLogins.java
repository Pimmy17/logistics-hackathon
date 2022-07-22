package com.qa.logins;

import java.util.Objects;

public class UserLogins {
	
	private Long userId;
	private String role;
	
	public  UserLogins(String role) {
		this.setRole(role);
		
	}
	
	public UserLogins(Long userId, String role) {
		this.setUserId(userId);
		this.setRole(role);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Logins [userId=" + userId + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(role, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogins other = (UserLogins) obj;
		return Objects.equals(role, other.role) && Objects.equals(userId, other.userId);
	}
	
	

}

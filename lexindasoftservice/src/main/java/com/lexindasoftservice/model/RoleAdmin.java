package com.lexindasoftservice.model;

public class RoleAdmin {
	private int id;
	private int roleId;
	private int adminId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "RoleAdmin [id=" + id + ", roleId=" + roleId + ", adminId="
				+ adminId + "]";
	}
	
}

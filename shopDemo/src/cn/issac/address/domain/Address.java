package cn.issac.address.domain;

import cn.issac.user.domain.User;

public class Address {
	private String aid;
	private String name;
	private String phone;
	private String addressName;
	private boolean isDefault;
	private User user;
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Address [aid=" + aid + ", name=" + name + ", phone=" + phone
				+ ", addressName=" + addressName + ", isDefault=" + isDefault
				+ ", user=" + user + "]";
	}
	
	
	 
}

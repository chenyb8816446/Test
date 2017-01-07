package com.cyb.create.bean;

public class Car {
	
	private String name;
	
	private String LoginName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public Car(String name, String loginName) {
		super();
		this.name = name;
		LoginName = loginName;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", LoginName=" + LoginName + "]";
	}
	
	public Car() {
		System.out.println("≥ı ºªØ°£°£");
	}

}

package com.cyb.create.bean;

public class User {
	
	private String name;
	
	private int ega;
	
	private Car car;
	
	
	

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User(){
		System.out.println("我是无参构造器。。。。");
	}
	
	public User(String name, int ega) {
		super();
		this.name = name;
		this.ega = ega;
		
		System.out.println("我是有参的构造器。。。。");
	}
	
	public void setMyUser(String[] userBean){
		System.out.println(userBean);
	}
	


	@Override
	public String toString() {
		return "User [name=" + name + ", ega=" + ega + ", car=" + car + "]";
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public int getEga() {
		return ega;
	}


	public void setEga(int ega) {
		this.ega = ega;
	}
	
	

}

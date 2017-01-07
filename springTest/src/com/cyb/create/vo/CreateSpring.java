package com.cyb.create.vo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyb.create.bean.Car;
import com.cyb.create.bean.User;

public class CreateSpring {
	
	public static void main(String[] args) {
		
		ApplicationContext cxr = new ClassPathXmlApplicationContext("applictionContext.xml");
		
		
//		User user = (User) cxr.getBean("User");
//		
//		System.out.println(user.toString());
		
//		User user = (User) cxr.getBean("User");
//		
//		System.out.println(user.toString());
		
		
		Car car = (Car) cxr.getBean("Car");
		
		Car car2 = (Car) cxr.getBean("Car");
		
		System.out.println(car == car2);
	}

}

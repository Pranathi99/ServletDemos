package com.hibernateDemo;

import org.hibernate.Session;

import com.hibernateDemo.domain.Address;
import com.hibernateDemo.domain.Person;

public class HibernateDemo {

	public static void main(String[] args) {
		
		Session session=HibernateDemoUtil.getSessionfactory().openSession();
		session.beginTransaction();
		
		Address add1=new Address("1234 Home street","Hyderabad","500085");
		
		Address add2=new Address("5678 Home street","Mumbai","800035");
		
		Person person=new Person("Ben",add1,add2);
		
		session.save(person);
		session.getTransaction().commit();
		session.close();
	}

}

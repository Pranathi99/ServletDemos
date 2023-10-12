package com.hibernateDemo;

import org.hibernate.Session;

import com.hibernateDemo.domain.Message;

public class HibernateDemo {

	public static void main(String[] args) {
		
		Session session=HibernateDemoUtil.getSessionfactory().openSession();
		session.beginTransaction();
		Message msg=new Message("Hello World from Hibernate using Annotations");
		session.save(msg);
		session.getTransaction().commit();
		session.close();
	}

}

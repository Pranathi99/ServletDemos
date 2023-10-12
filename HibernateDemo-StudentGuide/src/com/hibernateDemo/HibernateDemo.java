package com.hibernateDemo;

import org.hibernate.Session;

import com.hibernateDemo.domain.Guide;
import com.hibernateDemo.domain.Student;


public class HibernateDemo {

	public static void main(String[] args) {
		
		demo3();
		//demo2();
		//demo1();
	}

	private static void demo3() {
		Session session=HibernateDemoUtil.getSessionfactory().openSession();
		session.beginTransaction();
		
		Guide guide = session.get(Guide.class,1L);
		
		System.out.println("Number of students for this guide ="+guide.getStudents().size());
		
		session.getTransaction().commit();
		session.close();
	}

	private static void demo1() {
		Session session=HibernateDemoUtil.getSessionfactory().openSession();
		session.beginTransaction();
		
		Guide guide = new Guide("ABC24532423","Mike Lawson",200000);
		Student std1=new Student("STU001","Max",guide);
		Student std2=new Student("STU002","Ben",guide);
		
		session.save(guide);
		session.save(std1);
		session.save(std2);
		
		session.getTransaction().commit();
		session.close();
	}

	private static void demo2() {
		Session session=HibernateDemoUtil.getSessionfactory().openSession();
		session.beginTransaction();
		
		Guide guide = new Guide("ABC24532446","Roy Wilson",200000);
		Student std1=new Student("STU003","Lisa",guide);
		
		session.persist(std1);
		
		session.getTransaction().commit();
		session.close();
	}

}

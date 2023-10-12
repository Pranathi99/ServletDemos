package com.studentWebApp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.Hibernate.util.HibernateDemoUtil;
import com.studentWebApp.model.Student;

public class StudentDataUtil {
	DataSource ds;
	Session session;
	Criteria criteria;
	
	public StudentDataUtil(DataSource ds) 
	{
		this.ds=ds;
		session=HibernateDemoUtil.getSessionfactory().openSession();
	}
	
	public List<Student> getStudents()
	{	
		List<Student>student_list=new ArrayList<Student>();
		session.beginTransaction();
		criteria= session.createCriteria(Student.class);
		student_list=criteria.list();
		session.getTransaction().commit();
		System.out.println(student_list);
		return student_list;
	}

	public void deleteStudent(int id) 
	{	
		try 
		{
			session.beginTransaction();
			Student std=session.load(Student.class, id);
			session.delete(std);
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void updateStudent(int id,String fname,String lname,String email) 
	{	
		session.beginTransaction();
		Student student=session.load(Student.class, id);
		student.setFname(fname);
		student.setLname(lname);
		student.setEmail(email);
		session.update(student);
		session.getTransaction().commit();
	}

	public Student getStudent(int id) 
	{
		session.beginTransaction();
		//System.out.println("id->"+id);
		Student std=session.load(Student.class,id);
		session.getTransaction().commit();
		return std;		
	}

	public void addStudent(String fname, String lname, String email) 
	{	
		session.beginTransaction();
		Student std=new Student(fname,lname,email);
		session.save(std);
		session.getTransaction().commit();
	}

}

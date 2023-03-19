package com.studentWebApp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.studentWebApp.model.Student;

public class StudentDataUtil {
	DataSource ds;
	
	public StudentDataUtil(DataSource ds) {
		this.ds=ds;
	}
	
	public List<Student> getStudents(){
		List<Student>student_list=new ArrayList<Student>();
		Connection conn=null; 
		Statement st=null;
		ResultSet rs=null;
		try {
			conn=ds.getConnection();
			st=conn.createStatement();
			ResultSet resultSet=st.executeQuery("select * from student order by id");
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String fname=resultSet.getString("first_name");
				String lname=resultSet.getString("last_name");
				String email=resultSet.getString("email");
				student_list.add(new Student(id,fname,lname,email));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally {
			close(conn,st,rs);
		}
		return student_list;
	}
	
	private void close(Connection conn,Statement st, ResultSet rs)
	{
		try {
			if(st!=null)
				st.close();
			if(conn!=null)
				conn.close();
			if(rs!=null)
				rs.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {
		Connection conn=null; 
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			conn=ds.getConnection();
			String sql="delete from student where id=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, id);
			st.execute();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally {
			close(conn,st,null);
		}
	}

	public void updateStudent(int id,String fname,String lname,String email) {
		Connection conn=null; 
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			conn=ds.getConnection();
			String sql="update student set first_name=?,last_name=?,email=? where id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, fname);
			st.setString(2, lname);
			st.setString(3, email);
			st.setInt(4, id);
			st.execute();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally {
			close(conn,st,null);
		}
	}

	public Student getStudent(int id) {
		Connection conn=null; 
		PreparedStatement st=null;
		ResultSet rs=null;
		Student std=null;
		try {
			conn=ds.getConnection();
			String sql="select * from student where id=?";
			st=conn.prepareStatement(sql);
			st.setInt(1, id);
			rs=st.executeQuery();
			while(rs.next())
			{
				System.out.println("here");
				int std_id=rs.getInt("id");
				String std_fname=rs.getString("first_name");
				String std_lname=rs.getString("last_name");
				String email=rs.getString("email");
				std=new Student(std_id,std_fname,std_lname,email);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally {
			close(conn,st,rs);
		}
		return std;		
	}

}

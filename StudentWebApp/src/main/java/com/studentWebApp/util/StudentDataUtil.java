package com.studentWebApp.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.studentWebApp.model.Student;

public class StudentDataUtil {
	List<Student>student_list=new ArrayList<Student>();
	
	Connection conn=null; 
	Statement st=null;
	ResultSet rs=null;
	
	public StudentDataUtil(Connection conn) {
		this.conn=conn;
	}
	
	public List<Student> getStudents(){
		try {
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
}

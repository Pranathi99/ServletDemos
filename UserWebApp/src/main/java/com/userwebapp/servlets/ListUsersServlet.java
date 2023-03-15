package com.userwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listUsersServlet")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
    
    public ListUsersServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root1234");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (Statement st=conn.createStatement();
			 ResultSet rs=st.executeQuery("select * from user");){
			
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>FirstName</th>");
			out.println("<th>LastName</th>");
			out.println("<th>Email ID</th>");
			out.println("</tr>");
			while(rs.next())
			{
				String fname=rs.getString(1);
				String lname=rs.getString(2);
				String email=rs.getString(3);
				out.println("<tr>");
				out.println("<td>"+fname+"</td>");
				out.println("<td>"+lname+"</td>");
				out.println("<td>"+email+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<a href='index.html'>Home</a>");
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		try {
			if(conn!=null)
				conn.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

}

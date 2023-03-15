package com.userwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    
    public UpdateUserServlet() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId=request.getParameter("emailId");
		String password=request.getParameter("password");
		
		try (Statement st=conn.createStatement();){
			int result=st.executeUpdate("update user set password= '"+password+"' where email= '"+emailId+"'");
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			if(result>0)
			{
				out.println("<h1>User password updated in DB</h1>");
			}
			else
			{
				out.println("<h1>Error updating user password</h1>");
			}
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
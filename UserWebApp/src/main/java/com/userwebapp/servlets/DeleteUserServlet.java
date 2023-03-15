package com.userwebapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private Connection conn;
    
    public DeleteUserServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ServletContext context=config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String dbuser = context.getInitParameter("dbuser");
			String dbpassword = context.getInitParameter("dbpassword");
			conn=DriverManager.getConnection(dburl,dbuser,dbpassword);
			} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId=request.getParameter("emailId");
		String password=request.getParameter("password");
		
		try (Statement st=conn.createStatement();){
			int result=st.executeUpdate("delete from user where password= '"+password+"' and email= '"+emailId+"'");
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			if(result>0)
			{
				out.println("<h1>User deleted from DB</h1>");
			}
			else
			{
				out.println("<h1>Error deleting user</h1>");
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

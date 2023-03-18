package com.Demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    private PreparedStatement ps;
	    
	    public LoginServlet() {
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
				ps=conn.prepareStatement("select * from user where email=?and password=?");
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			if(!isValidInput(email,false) || !isValidInput(password,false))
			{
				out.println("<h1>Please enter valid input</h1>");
				return;
			}			
			try {
				ps.setString(1,email);
				ps.setString(2,password);
				boolean result=ps.execute();
				ResultSet resultSet=null;
				if(result)
					resultSet=ps.getResultSet();
				if(resultSet.next())
				{
					out.println("User found!");
					String welcomeMessage="Welcome to servlet communication demo - "+email+"!!";
					request.setAttribute("message", welcomeMessage);
					RequestDispatcher rd=request.getRequestDispatcher("homeServlet");
					rd.include(request, response);
				}
				else
				{
					out.println("User not found!");
					RequestDispatcher rd=request.getRequestDispatcher("login.html");
					rd.include(request, response);
				}
			}
			catch(SQLException ex)
			{
				out.println("Error creating product. Error msg :"+ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		private boolean isValidInput(String inputVal,boolean isNumber) {
			if(inputVal==null || inputVal.isBlank() || inputVal.isEmpty())
				return false;
			else if(isNumber)
			{
				try {
					Integer.parseInt(inputVal);
					return true;
				}
				catch(NumberFormatException ex)
				{
					return false;
				}
			}
			else
				return true;
		}
		
		@Override
		public void destroy() {
			try {
				if(conn!=null)
					conn.close();
				if(ps!=null)
					ps.close();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}

}

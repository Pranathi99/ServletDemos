package com.PreparedStatementDemo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    private PreparedStatement ps;
	    
	    public AddProductServlet() {
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
				ps=conn.prepareStatement("insert into product values(?,?,?,?)");
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String description=request.getParameter("description");
			String price=request.getParameter("price");
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			if(!isValidInput(id,true) || !isValidInput(name,false) || !isValidInput(description,false) || !isValidInput(price,true))
			{
				out.println("<h1>Please enter valid input</h1>");
				return;
			}			
			try {
				ps.setInt(1,Integer.parseInt(id));
				ps.setString(2,name);
				ps.setString(3,description);
				ps.setInt(4,Integer.parseInt(price));
				int result=ps.executeUpdate();
				out.println("Product created");
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

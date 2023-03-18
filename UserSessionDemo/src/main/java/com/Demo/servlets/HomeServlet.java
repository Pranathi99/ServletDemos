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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    
	    public HomeServlet() {
	        super();
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	doGet(request,response);
	    }
	    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			HttpSession session=request.getSession();
			String username=(String) session.getAttribute("username");
			System.out.println("username-"+username);
			if(username==null)
			{
				out.println("Username not found!");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.forward(request, response);
				out.println("\nHere!");   // forward discards original response, but include shows the original response
										 //along with target response
			}
			else
			{
				out.println("Username found - "+username);
				for(Cookie cookie:request.getCookies())
				{
					System.out.println(cookie.getName()+" : "+cookie.getValue());
				}
				out.print("Fav Color:"+request.getCookies()[1].getValue());
			}
		}
		
}

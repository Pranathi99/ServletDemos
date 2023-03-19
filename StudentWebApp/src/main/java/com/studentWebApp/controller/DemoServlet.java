package com.studentWebApp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import com.studentWebApp.util.StudentDataUtil;


@WebServlet("/mvcDemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private StudentDataUtil stdUtil;
	
    public DemoServlet() {
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
			stdUtil=new StudentDataUtil(conn);
		} 
    	catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("student_list", stdUtil.getStudents());
		RequestDispatcher rd=request.getRequestDispatcher("viewStudents.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

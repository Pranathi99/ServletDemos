package com.studentWebApp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.studentWebApp.util.StudentDataUtil;


@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/studentweb")
	private DataSource datasource;
	
	private StudentDataUtil stdUtil;
	
    public StudentControllerServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	try {
			stdUtil=new StudentDataUtil(datasource);
		} 
    	catch (Exception ex) {
			// TODO Auto-generated catch block
			throw new ServletException();
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

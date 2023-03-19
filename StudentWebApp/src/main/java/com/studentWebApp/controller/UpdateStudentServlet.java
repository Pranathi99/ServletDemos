package com.studentWebApp.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.studentWebApp.util.StudentDataUtil;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@Resource(name="jdbc/studentweb")
	private DataSource datasource;
	
	private StudentDataUtil stdUtil;
	
    public UpdateStudentServlet() {
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
		String std_id=request.getParameter("id");
		int id=Integer.parseInt(std_id);
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		stdUtil.updateStudent(id,fname,lname,email);
		
		request.setAttribute("student_list", stdUtil.getStudents());
		RequestDispatcher rd=request.getRequestDispatcher("viewStudents.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

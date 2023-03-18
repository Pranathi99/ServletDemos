package com.demo.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.mvc.utility.UserDataUtil;

@WebServlet("/mvcDemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DemoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String[]students= {"Alex","Haley","Luke","Manny"};
//		request.setAttribute("student_list", students);
		//System.out.println(UserDataUtil.userList());
		request.setAttribute("user_list", UserDataUtil.userList());
		RequestDispatcher rd=request.getRequestDispatcher("viewUsers.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

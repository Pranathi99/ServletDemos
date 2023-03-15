package servletBasics1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdditionServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		int num1=Integer.parseInt(req.getParameter("number1"));
		int num2=Integer.parseInt(req.getParameter("number2"));
		int result=num1+num2;
		PrintWriter out=res.getWriter();
		out.println("<p>Result = "+(result)+"</p>");
		}
		catch(NumberFormatException ex)
		{
			System.out.println("Invalid number");
			PrintWriter out=res.getWriter();
			out.println("<p>Invalid number. Try again!</p>");
		}
	}
	
}

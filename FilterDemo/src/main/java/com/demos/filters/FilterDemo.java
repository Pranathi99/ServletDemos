package com.demos.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/DemoServlet")
public class FilterDemo extends HttpFilter implements Filter {
       
    public FilterDemo() {
        super();
    }
    
    public void destroy() {
		System.out.println("Filter Destroyed...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Inside Filter....");
		response.getWriter().println("Before Servlet...");
		chain.doFilter(request, response);
		response.getWriter().println("After Servlet...");
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter Started...");
	}

}

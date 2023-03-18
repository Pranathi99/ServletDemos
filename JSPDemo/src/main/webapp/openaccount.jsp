<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%!
	    
		Connection con;
		PreparedStatement ps;
		
		public void jspInit(){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root1234");
				ps=con.prepareStatement("insert into account values(?,?,?,?)");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	
		public void jspDestroy(){
			try{
				ps.close();
				con.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	%>
	<%
		int accno=Integer.parseInt(request.getParameter("accno"));
		int bal=Integer.parseInt(request.getParameter("bal"));
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		
		
		
		ps.setInt(1,accno);
		ps.setString(2,fname);
		ps.setString(3,lname);
		ps.setInt(4,bal);
		
		ps.executeUpdate();
	%>
	
	<%@ include file="openaccount.html" %>
</body>
</html>
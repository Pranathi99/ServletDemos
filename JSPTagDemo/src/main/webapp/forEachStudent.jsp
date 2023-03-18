<%@ page import="java.util.*,com.demo.tags.Student" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<% List<Student>al=new ArrayList<Student>();
   al.add(new Student("Alex","Dunphy",true));
   al.add(new Student("Haley","Dunphy",true));
   al.add(new Student("Phil","Dunphy",false));
   al.add(new Student("Claire","Dunphy",false));
   pageContext.setAttribute("students", al);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="2">
		<tr>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Discount</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.fname}</td>
				<td>${student.lname}</td>
				<td>
				<c:if test="${student.above90Percent}">
					Yes
				</c:if>
				<c:if test="${!student.above90Percent}">
					No
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
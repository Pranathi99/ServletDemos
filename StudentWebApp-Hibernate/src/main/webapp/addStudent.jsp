<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addStudent" method="post">
		<table>
			<tr>
				<td>FirstName</td>
				<td><input type="text" name="fname"/></td>
			</tr>
			<tr>
				<td>LastName</td>
				<td><input type="text" name="lname"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="submit"/></td>
			</tr>
		</table>
	</form>
	<a href="index.html">Home</a>
</body>
</html>
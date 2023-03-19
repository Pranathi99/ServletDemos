<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student</title>
</head>
<body>
	<form action="updateStudent" method="post">
		<input type="hidden" name="id" value="${STUDENT.id}"/>
		<table>
			<tr>
				<td>FirstName</td>
				<td><input name="fname" value="${STUDENT.fname}"/></td>
			</tr>
			<tr>
				<td>LastName</td>
				<td><input name="lname" value="${STUDENT.lname}"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input name="email" value="${STUDENT.email}"/></td>
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
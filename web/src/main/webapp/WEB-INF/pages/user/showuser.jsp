<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div>
	<table>
		<tr>
			<td>ID</td> 
			<td>${user.id}</td>
		</tr>
		<tr>
			<td>NAME</td> 
			<td>${user.username}</td>
		</tr>
	</table>
	</div>
</body>
</html>
﻿<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div>
		<h1><fmt:message key="welcome" /></h1>
		<fieldset>
			<a href="login">Login</a> &nbsp;&nbsp;
			<a href="logout">Logout</a>
		</fieldset>
		<fieldset>
			<legend>admin pages</legend>
			<p><a href="admin/welcome">welcome</a></p>
		</fieldset>
		<fieldset>
			<legend>user pages</legend>
			<p><a href="user/welcome">welcome</a></p>
			<p><a href="user/userinfo">userinfo</a></p>
		</fieldset>
	</div>
</body>
</html>
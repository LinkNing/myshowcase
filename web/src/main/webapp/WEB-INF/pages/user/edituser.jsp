﻿<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div>
		<form:form action="save" commandName="user" method="post">
			<form:errors path="*" cssStyle="color:red" />
			<fieldset>
				<legend>User Info:</legend>
				<form:hidden path="id" /><br> 
				<form:label path="username">username</form:label><form:input path="username"/><br> 
				<form:label path="password">password</form:label><form:input path="password"/><br>
			</fieldset>
			<input type="submit" />
		</form:form>
	</div>
</body>
</html>
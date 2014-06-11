<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div>
		<span style="color:red">validation errors：${errors}</span>
	</div>
	<div>
		<form action="save" method="post">
			<fieldset>
				<legend>User Info:</legend>
				<input type="hidden" name="id" value="${user.id}" /><br>
				<label for="username">username</label><input type="text" name="username" value="${user.username}" /><br>
				<label for="password">password</label><input type="text" name="password" value="${user.password}" /><br>
			</fieldset>
			<input type="submit"/>
		</form>
	</div>
</body>
</html>
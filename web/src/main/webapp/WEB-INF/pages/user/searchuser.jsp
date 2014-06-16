<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户查询</title>
</head>
<body>
	<div>
		<form action="${ctx}/users/searcher">
			<fieldset>
				<legend>用户查询:</legend>
				<label for="n">请输入名字：</label><input type="text" name="username" value="${param.username}">
			<input type="submit" value="Search">
			</fieldset>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>ID</th>
				<th>NAME</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
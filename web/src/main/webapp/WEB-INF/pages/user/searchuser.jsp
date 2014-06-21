<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户查询</title>
</head>
<body>
	<h1>用户查询</h1>
	<div class="pull-right">
		<form action="${ctx}/users/searcher" class="form-inline" role="form">
			<div class="form-group">
			    <label class="sr-only" for="username">username</label>
			    <input type="text" id="username" name="username" class="form-control" value="${param.username}" placeholder="请输入名字">
			</div>
			
			<button type="submit" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span>
				Search
			</button>			
		</form>
	</div>
	<div>
		<table class="table table-bordered">
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
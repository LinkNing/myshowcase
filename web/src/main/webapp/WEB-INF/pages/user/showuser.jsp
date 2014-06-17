<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户详情</title>
</head>
<body>
	<h1>用户详情</h1>
	<div>
	<dl class="dl-horizontal">
  		<dt>ID:</dt>
  		<dd>${user.id}</dd>
  		<dt>NAME:</dt>
  		<dd>${user.username}</dd>
  		<dt>PASSWORD:</dt>
  		<dd>${user.password}</dd>  		
	</dl>
	</div>
</body>
</html>
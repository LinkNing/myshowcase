<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
<script type="text/javascript">
$(function(){

	$("a.del").on("click", function() {
		$ATag = $(this);
	    $.ajax({ 
	        url: $ATag.attr("href"), 
	        type: 'DELETE', 
	        success: function(result) { 
	         	//window.location.reload();
	        	$ATag.parent().parent().remove();
	        } 
	    });  
	    
	    return false;
	});
	
});
</script>
</head>
<body>
	<div>
	<h1>用户列表</h1>
	<table>
		<tr>
			<th>ID</th> 
			<th>NAME</th> 
		</tr>
		<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td><a href="${ctx}/users/${user.id}">查看</a></td>
			<td><a href="${ctx}/users/${user.id}" class="del" onclick="javascript:void(0)">删除</a></td>
			<td><a href="${ctx}/users/${user.id}/editor">修改</a></td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>
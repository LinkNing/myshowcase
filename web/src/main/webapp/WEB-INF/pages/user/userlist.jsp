<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
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
	<table class="table">
		<tr>
			<th>ID</th> 
			<th>NAME</th> 
			<th>&nbsp;</th> 
		</tr>
		<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>
			<a href="${ctx}/users/${user.id}">查看</a>
			<a href="${ctx}/users/${user.id}" class="del" onclick="javascript:void(0)">删除</a>
			<a href="${ctx}/users/${user.id}/editor">修改</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>
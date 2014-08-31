<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
<title>编辑用户信息</title>
</head>
<body>
	<div>
		<form:form action="${ctx}/users" commandName="user" method="put" enctype="application/x-www-form-urlencoded" role="form">
			<fieldset>
				<legend>User Info:</legend>
				<form:hidden path="id" />
				
				<div class="form-group">
				<form:label path="username">username</form:label>
				<form:input path="username" cssClass="form-control" placeholder="Enter name"/>
				</div>
				
				<div class="form-group">
				<form:label path="password">password</form:label>
				<form:password path="password" cssClass="form-control"  placeholder="Enter password"/>
				</div>
			</fieldset>
			
			<button type="submit" class="btn btn-primary">Submit</button>
			
			<div class="has-error">
			<form:errors path="*" cssClass="help-block"/>
			</div>
		</form:form>
	</div>
</body>
</html>
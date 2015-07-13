<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="col-md-2">
	<h1>用户管理</h1>
	<div class="submenu">
		<a id="user-welcome-tab" href="${ctx}/users/welcome">欢迎</a>
		<a id="user-list-tab" href="${ctx}/users">用户列表</a>
		<a id="user-search-tab" href="${ctx}/users/searcher">查找用户</a>
		<a id="user-add-tab" href="${ctx}/users/editor">添加用户</a>
		<a id="user-change-tab" href="${ctx}/users/editor">修改用户</a>
	</div>
	<h1>XXXX</h1>
	<div class="submenu">
		<a id="web-tab" href="${ctx}/admin/welcome">welcome</a>
		<a id="webservice-tab"href="${ctx}/">XXX</a>
		<a id="hystrix-tab" href="${ctx}/">XXX</a>
		<a id="jmx-tab" href="${ctx}/">XXX</a>
		<a id="log-tab" href="${ctx}/">XXX</a>
		<a id="monitor-tab" href="${ctx}/">XXX</a>
	</div>
	<h1>XXXX</h1>
	<div class="submenu">
		<a id="web-tab" href="${ctx}/">XXX</a>
		<a id="webservice-tab"href="${ctx}/">XXX</a>
		<a id="hystrix-tab" href="${ctx}/">XXX</a>
		<a id="jmx-tab" href="${ctx}/">XXX</a>
		<a id="log-tab" href="${ctx}/">XXX</a>
		<a id="monitor-tab" href="${ctx}/">XXX</a>
	</div>
</div>
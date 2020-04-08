<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Firstname</td>
				<td>Lastname</td>
			</tr>

		</thead>
		<c:forEach items="${owners}" var="owner">
			<tr>
				<td>${owner.id}</td>
				<td>${owner.firstName}</td> 
				<td>${owner.lastName}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${not empty message}">
		<div style="color:blue">
		${message}</div>
	</c:if>
</body>
</html>
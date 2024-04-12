<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="background-color: #E0E0E0;height: 75px;padding: 1rem">
		<div style="float: left;" >
			<h2>Web shipper</h2>
		</div>
		<div align="center" style="margin-bottom: -2rem">
			<form action="shipperHomeSearch" method="post" enctype="multipart/form-data">
			<c:if test="${keyword == null}">
				Tìm đơn hàng:<input type="search" name="keyword" 
							id="keyword" style="width: 300px" placeholder="Nhập đơn">
			</c:if>
			<c:if test="${keyword != null}">
				Tìm đơn hàng:<input type="search" name="keyword" 
							id="keyword" style="width: 300px" value="${keyword }" placeholder="${keyword }">
			</c:if>
				
				<button type="submit">Search</button>
			</form>
		</div>
		<div style="float: right;;text-align: right;">
		
			<c:if test="${empty loginedUser }">
				<a href="${pageContext.request.contextPath }/login">Đăng nhập</a>
			</c:if>
		
			<c:if test="${not empty loginedUser }">
				Xin chào <b>${loginedUser.fullname }</b>
				|
				<a href="${pageContext.request.contextPath }/logout">Đăng xuất</a>
			</c:if>
			<br>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bookStore_script.js"></script>
</body>
</html>
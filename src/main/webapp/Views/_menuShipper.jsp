<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	li{
		border:1px solid black ;
		border-radius: 10px;
		display: inline-block;
		margin-right: 15px;
		padding: 5px;
	}
	a{text-decoration: none}
</style>
</head>
<body>
	
	<div class="menu" style="padding: 5px;" align="center">
		<ul>
			<li><a id="btn1" href="${pageContext.request.contextPath }/shipperHome">Đơn hàng cần giao</a></li>
			<li><a id="btn2" href="${pageContext.request.contextPath }/shipperHome1">các đơn hàng đã giao hàng</a></li>
			<li><a id="btn3" href="${pageContext.request.contextPath }/shipperHome2">Các đơn hàng cần trả hàng</a></li>
			<li><a id="btn4" href="${pageContext.request.contextPath }/shipperHome3">Các đơn hàng trả hàng thành công</a></li>
		</ul>
	</div>
	
</body>
</html>
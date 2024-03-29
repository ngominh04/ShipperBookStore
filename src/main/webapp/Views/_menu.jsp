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
<div style="padding: 5px;" align="center">
	
	
	<div class="menu" style="padding: 5px;" align="center">
		<ul>
			
			<li><a href="${pageContext.request.contextPath }/clientHome">Trang chủ</a></li>
			<li><a href="">Sách phổ biến</a></li>
			<li><a href="">Sách bán chạy</a></li>
			<li><a href="">Sách mới</a></li>
			<li><a href="">Giá thấp đến cao</a></li>
			<li><a href="">Giá cao đến thấp</a></li>
		</ul>
	</div>

</div>
</body>
</html>
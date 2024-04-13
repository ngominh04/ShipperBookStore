<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="background-color: #E0E0E0;height: 75px;padding: 1rem">
		<div style="float: left;">
			<h2>Website cửa hàng bán sách với JSP/SERVLLET</h2>
		</div>
		<div style="float: right;;text-align: right;">
		
		<c:if test="${empty loginedUser }">
			<a href="${pageContext.request.contextPath }/login">Đăng nhập</a>
		</c:if>
		
			<c:if test="${not empty loginedUser }">
				Xin chào <b>${loginedUser.fullname }</b>
				|
				<a href="${pageContext.request.contextPath}/customerOrderList">Thông tin tài khoản/ đơn hàng</a>
				|
				<a href="${pageContext.request.contextPath }/logout">Đăng xuất</a>
			</c:if>
			<c:if test="${not empty cartOfCustomer }">
				<a href="${pageContext.request.contextPath }/cartBook/viewCart">giỏ hàng</a>
			</c:if>
			<br>
			 Tìm sách :<input name="search" placeholder="search bình thường"
			onchange="activeAsLink('${pageContext.request.contextPath }/clientHome?keyword=' +this.value);">
			<br>
			<div style="margin-top: 0.5rem">
				Tìm sách ajax:<input type="text" name="keyword" value="${keyword }"
					onkeyup="searchByName(this)" placeholder="search ajax">
			</div>
			
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bookStore_script.js"></script>
	<script type="text/javascript">
		var request;
		function searchByName(keyword) {
			
			 var keyword = keyword.value;
			 var url = "/ShipperBookStore/clientHome?keyword=" + keyword;
			
			 if (window.XMLHttpRequest) {
			  request = new XMLHttpRequest();
			 } else if (window.ActiveXObject) {
			  request = new ActiveXObject("Microsoft.XMLHTTP");
			 }
			
			 try {
			  request.onreadystatechange = getInfo;
			  request.open("GET", url, true);
			  request.send();
			 } catch (e) {
			  alert("Unable to connect to server");
			 }
		}
		function getInfo() {
		 if (request.readyState == 4) {
		  var val = request.responseText;
		  document.getElementById('content').innerHTML = val;
		 }
		}
	</script>
</body>
</html>
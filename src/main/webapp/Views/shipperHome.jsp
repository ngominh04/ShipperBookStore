<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_headerShipper.jsp"></jsp:include>
	<jsp:include page="_menuShipper.jsp"></jsp:include>
	<div align="center">
		<h3>Danh sách các đơn hàng</h3>
		
		<p style="color: red">${errors }</p>
		
		<table border="1px">
			<tr>
				<th>Mã hóa đơn</th>
				<th>Tên khách</th>
				<th>Số điện thoại</th>
				<th>Giá Tiền</th>
				<th>Ngày đặt</th>
				<th colspan="3" width="120px">Thao tác</th>
			</tr>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td>${book.orderNo}</td>
					<td>${book.fullname}</td>
					<td>${book.mobile} </td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${book.totalCost}"/><sup>đ</sup></td>
					<td><fmt:formatDate value="${book.orderDate}" pattern="dd-MM-yyyy HH:mm"/> </td>
					
					<c:if test="${book.orderStatus ==2}">
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=2">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${book.orderStatus ==3}">
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=3">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${book.orderStatus ==4}">
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=3">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${book.orderStatus ==5}">
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=5">Xem chi tiết</a>
						</td>
					</c:if>
					
				</tr>
			</c:forEach>
		</table>
		<br>
		
	</div>
</body>
</html>
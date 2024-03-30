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
		<c:if test="${keyword ==null}">
			<h3>Danh sách các đơn hàng</h3>
		</c:if>
		<p style="color: red">${errors }</p>
		<c:if test="${keyword !=null }">
			<span style="color: red"><b>Bạn vừa tìm kiếm theo mã đơn :${keyword}</b></span>
		</c:if>
		
		<table border="1px">
			<tr>
				<th>Mã hóa đơn</th>
				<th>Tên khách</th>
				<th>Số điện thoại</th>
				<th>Giá Tiền</th>
				<th>Ngày đặt</th>
				<th>Trạng thái đơn</th>
				<th colspan="3" width="120px">Thao tác</th>
			</tr>
			<c:if test="${keyword !=null}">
				<tr>
					<td>${bookList.orderNo}</td>
					<td>${bookList.fullname}</td>
					<td>${bookList.mobile} </td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${bookList.totalCost}"/><sup>đ</sup></td>
					<td><fmt:formatDate value="${bookList.orderDate}" pattern="dd-MM-yyyy HH:mm"/> </td>
					
					<c:if test="${bookList.orderStatus ==2}">
						<td align="center">Đơn cần giao</td>
						<td align="center">
							<a href="detailShipper?orderNo=${bookList.orderNo }&orderStatus=2">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${bookList.orderStatus ==3}">
					<td align="center">Đơn cần đã giao thành công</td>
						<td align="center">
							<a href="detailShipper?orderNo=${bookList.orderNo }&orderStatus=3">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${bookList.orderStatus ==4}">
						<td align="center">Đơn cần trả</td>
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=3">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${bookList.orderStatus ==5}">
					<td align="center">Đơn đã trả thành công</td>
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=5">Xem chi tiết</a>
						</td>
					</c:if>
				</tr>
			</c:if>
			<c:if test="${keyword == null }">
				<c:forEach items="${bookList}" var="book">
				<tr>
					<td>${book.orderNo}</td>
					<td>${book.fullname}</td>
					<td>${book.mobile} </td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${book.totalCost}"/><sup>đ</sup></td>
					<td><fmt:formatDate value="${book.orderDate}" pattern="dd-MM-yyyy HH:mm"/> </td>
					
					<c:if test="${book.orderStatus ==2}">
					<td align="center">Đơn <span style="color: red">cần giao</span></td>
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=2">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${book.orderStatus ==3}">
					<td align="center">Đơn cần <span style="color: red">đã giao</span>  thành công</td>
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=3">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${book.orderStatus ==4}">
					<td align="center">Đơn <span style="color: red">cần trả</span> </td>
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=3">Xem chi tiết</a>
						</td>
					</c:if>
					<c:if test="${book.orderStatus ==5}">
					<td align="center">Đơn <span style="color: red">đã trả</span> thành công</td>
						<td align="center">
							<a href="detailShipper?orderNo=${book.orderNo }&orderStatus=5">Xem chi tiết</a>
						</td>
					</c:if>
					
				</tr>
			</c:forEach>
			</c:if>
			
		</table>
		<br>
		
	</div>
</body>
</html>
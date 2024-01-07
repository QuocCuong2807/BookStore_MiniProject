<%@page import="java.security.PolicySpi"%>
<%@page import="java.util.Set"%>
<%@page import="database.OrderDAO"%>
<%@page import="model.OrderDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
	Set entrySet =(Set) request.getAttribute("orderDetail");
	Customer customer = (Customer)request.getAttribute("customer");
	String total = request.getAttribute("total") + "";
	%>
	<p>Dat hang thanh cong</p>
	<c:set var="cus" value="<%=customer	%>"></c:set>
	<a href="Shop">quay ve trang chu</a>

	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Customer Name</th>
				<th scope="col">Book Name</th>
				<th scope="col">Quantity</th>
				<th scope="col">Total</th>
			</tr>
		</thead>
		<tbody>
		
			<tr>
				<td>${cus.getCusFullName() }</td>
				<td>
					<c:forEach items="<%=entrySet %>" var="o">
						${o.getValue().getBook().getBookName() }<br>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="<%=entrySet %>" var="o">
						${o.getValue().getQuantity() }<br>
					</c:forEach>
				</td>
				<td><%=total %></td>
			</tr>
			
		</tbody>
	</table>

</body>
</html>
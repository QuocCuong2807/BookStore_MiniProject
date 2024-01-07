<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String e_obj = request.getAttribute("e_objects") + "";
String e_mail = request.getAttribute("e_cusEmail")+ "";
String e_sdt = request.getAttribute("e_cusPhoneNumber") + "";
%>
<h1>fail</h1>
<p><%=e_obj %></p>
<p><%=e_mail %></p>
<p><%=e_sdt%></p>
</body>
</html>
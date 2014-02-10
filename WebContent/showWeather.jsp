<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>
<body>
<h3>Current Weather of ${current.cityName }</h3>
<UL>
  <LI>Temperature(Kelvin): ${current.tempValue}
  <LI>Max temperature: ${current.tempMax}
  <LI>Min temperature: ${current.tempMin}
  
</UL>
<%-- <jsp:useBean id="current" class="beans.WeatherBean" scope="request"></jsp:useBean>
<jsp:getProperty property="cityName" name="current"/> --%>
<!--if session,scope is session; if content,scope is application  -->

<%-- <c:forEach item="current.test" var="test">
${test }
</c:forEach> --%>
</body>
</html>
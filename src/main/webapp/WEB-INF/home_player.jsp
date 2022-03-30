<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bomberman player - Home</title>
</head>
<body>
	 <%@include  file="content/html/navbar.html" %>
	<h1 id="Welcome">Welcome <c:out value="${ sessionScope.player.getLogin() }"></c:out> ! </h1>
	<section id="statistics" class="statistics">
		<h2> Statistics </h2>
		<p> Number of game played : <c:out value="${ nbPlayed }">NaN</c:out> </p>
		<p> Number of game wined : <c:out value="${ nbWined }">NaN</c:out> </p>
		<p> Ratio : <c:out value="${ nbPlayed/nbWined }">NaN</c:out> </p>
	</section>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bomberman - History</title>
</head>
<body>
 <%@include  file="content/html/navbar.html" %>
<h1>History of <c:out value="${sessionScope.player.getLogin()}">Unknown</c:out> Games </h1>

<ul>
	 <c:forEach items="${games}" var="game">
        <li>
        	<ul>
        		<li> Winer : <c:out value="${game.getWiner()}"></c:out></li>
        		<li> Map :<c:out value="${game.getNameMap()}"></c:out></li>
    
        		<li> Start time :<c:out value="${game.getStartTime()}"></c:out></li>
        		<li> End time :<c:out value="${game.getEndTime()}"></c:out></li>
        		<li> Duration :<c:out value="${game.getDurationMinutes()}"></c:out> minutes</li>
        	</ul>  
        </li>
    </c:forEach>
    
</ul>

${error}

</body>
</html>
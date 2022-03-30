<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style><%@include file="content/css/home.css"%></style>
<script><%@include file="content/js/home.js"%></script>
<meta charset="UTF-8">
<title>Bomberman - Home</title>
</head>
<body>
	<h1>Bomberman website </h1>
	<section id="register" class="log">
		<h2>Registration</h2>
		<form action="#" method="post">
			<section>	
			 	<label for="login" >Login</label>
				<input type="text" name="login" id="login_r" required />
			</section>
			
			<section>
				<label for="password">password</label>
				<input type="password" name="password" id="password_r" required />
			</section>
			<input class="buttonsend" type="submit" name="send" id="send_r" value="to register" />
		</form>
	</section>
	
	<h2>or</h2>
	
	<section id="login" class="log">
		<h2>Login</h2>
		<form action="#" method="post">
			<section>	
			 	<label for="login" >Login</label>
				<input type="text" name="login" id="login_l" required />
			</section>
			
			<section>
				<label for="password">password</label>
				<input type="password" name="password" id="password_l" required />
			</section>
			<input class="buttonsend" type="submit" name="send" id="send_l" value="to login" />
		
		</form>
	</section>
	
	
	<section class="error">
		<p><c:out value="${ eroor }"></c:out></p>
	</section> 


	
	
	
</body>
</html>
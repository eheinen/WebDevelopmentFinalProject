<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sorteio de Usuário</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">



</head>
<body>

	<div class="container">
		<div class="col-md-6 col-md-offset-3" style="margin-top: 20px;">
			<a href="login.jsp">Login</a> <br />
			<a href="user">Cadastro de Usuário</a>
		</div>
		
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<h2>Sorteio :)</h2>
				<br />
				<c:if test="${errorMsg != null}">
					<div class="alert alert-danger" role="alert">
						<p>${errorMsg}</p>
					</div>
				</c:if>
				<div class="form-group">
					<label for="amigo">Nome do Amiguinho!</label> 
					<input type="text" class="form-control" id="amigo" value="${userFriend}" disabled="disabled">
				</div>
				<form action="sorteio" method="post">				
					<button type="submit" class="btn btn-default">Sortear</button>
				</form>

			</div>
		</div>
	</div>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
</body>
</html>
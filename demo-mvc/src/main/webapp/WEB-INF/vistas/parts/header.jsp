<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Demos curso</title>
<%--
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
--%>
	<link rel="stylesheet" href="/css/tema.css" >
	<link rel="stylesheet" href="/webjars/bootstrap/5.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/"><img src="/logo.png" title="<s:message code="img.title.logo" />" height="30"></a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			  </button>
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link" href="/"><s:message code="menu.inicio" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/actores"><s:message code="menu.actores" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/ciudades"><s:message code="menu.ciudades" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/ajax"><s:message code="menu.ajax" /></a>
					</li>
				</ul>
				<div  class="d-flex me-2">
					<div class="btn-group btn-group-sm float-right mr-2">
						<a href="?lang=es" class="btn btn-primary btn-sm">ES</a>
						<a href="?lang=en" class="btn btn-primary btn-sm">EN</a>
					</div>
				</div>
				<div  class="d-flex justify-content-center align-items-center">
					<sec:authorize access="isAnonymous()">
						<a class="nav-link" href="${pageContext.request.contextPath}/mylogin">Log In</a>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<span class="navbar-text"><sec:authentication property="principal.username" /></span>
						<a class="ms-2 nav-link" href="${pageContext.request.contextPath}/logout">Log Out</a>
					</sec:authorize>
				</div>
			</div>
		</div>
	</nav>
	<main class="container-fluid">

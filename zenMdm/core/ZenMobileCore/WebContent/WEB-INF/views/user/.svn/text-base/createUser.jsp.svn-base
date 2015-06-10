<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<METAÂ http-equiv="Content-Type"Â content="text/html;charset=UTF-8">
	<title>Create Account</title>
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
</head>	
<body>
<div class="container">
	<h1>
		Add User
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="uservo" action="user" method="post">
		  	<fieldset>		
				<legend>Add User</legend>
				<p>
					<form:label path="firstName">First Name</form:label><br/>
					<form:input path="firstName" /> <form:errors path="firstName" />			
				</p>
				<p>	
					<form:label path="lastName">Last Name</form:label><br/>
					<form:input path="lastName" /> <form:errors path="lastName" />
				</p>
				<p>
					<form:label path="email">Email</form:label><br/>
					<form:input path="email" /> <form:errors path="email" />
				</p>
				<p>
					<form:label path="userName">User Name</form:label><br/>
					<form:input path="userName" /> <form:errors path="userName" />
				</p>
				<p>
					<form:label path="password">Password</form:label><br/>
					<form:input path="password" /> <form:errors path="password" />
				</p>
				<p>
					<form:label path="secretQuestion">Secret Question</form:label><br/>
					<form:input path="secretQuestion" /> <form:errors path="secretQuestion" />
				</p>
				<p>
					<form:label path="secretPassword">Secret Password</form:label><br/>
					<form:input path="secretPassword" /> <form:errors path="secretPassword" />
				</p>	
				<p>
					<form:label path="expirationDate">Expiration Date</form:label><br/>
					<form:input path="expirationDate" /> <form:errors path="expirationDate" />
				</p>
				<p>
					<form:label path="expirationDate">Expiration Date</form:label><br/>
					<form:input path="expirationDate" /> <form:errors path="expirationDate" />
				</p>																														
				<p>	
					<input type="submit" />
				</p>
			</fieldset>
		</form:form>
	</div>

</div>
</body>
</html>
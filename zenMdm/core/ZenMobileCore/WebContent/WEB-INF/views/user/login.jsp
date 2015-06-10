<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
       "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
  <title>zenmobile</title>
</head>

<body>

<div class="Container">
  
  <div id="Dialog">

    
    

    <form:form modelAttribute="loginvo" action="login" method="post">
    <div style="margin:0;padding:0;display:inline">
    </div>
      <dl>
    		<dt>Username:</dt>
        <dd><form:input path="userName" /></dd>
    		<dt>Password:</dt>
    		<dd>
    		  <form:password path="password" />
        </dd>
        <dd></dd>
    		<dd><input type="submit" value="Submit" /></dd>
    	</dl>
    </form:form>
  </div>
</div>

</body>
</html>

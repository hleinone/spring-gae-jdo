<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html; charset=UTF-8"
	language="java" isELIgnored="false" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
<title>GAE + Spring + JDO Example - Guestbook</title>
</head>
<body>

<c:if test="${user != null}">
	<p>Hello, <c:out value="${user.nickname}" />! (You can <a href="${logoutUrl}">sign out</a>.)</p>
</c:if>
<c:if test="${user == null}">
	<p>Hello! <a href="${loginUrl}">Sign in</a> to include your name with greetings you post.</p>
</c:if>

<c:if test="${empty greetings}">
	<p>The guestbook has no messages.</p>
</c:if>
<c:if test="${not empty greetings}">
	<c:forEach items="${greetings}" var="g">
		<c:if test="${g.author == null}">
			<p>An anonymous person wrote:</p>
		</c:if>
		<c:if test="${g.author != null}">
			<p><b><c:out value="${g.author.nickname}" /></b> wrote:</p>
		</c:if>
		<blockquote>
		<c:out value="${g.content}" />
		</blockquote>
	</c:forEach>
</c:if>

<form action="/sign" method="post">
<div><textarea name="content" rows="3" cols="60"></textarea></div>
<div><input type="submit" value="Post Greeting" /></div>
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
	<li><a href="${pageContext.request.contextPath}/join.do">join</a></li>
<c:choose>
	<c:when test = "${id != null }">
	<li><a href="${pageContext.request.contextPath}/logout.do?url=">logout</a></li>
	</c:when>
	<c:otherwise>
	<li><a href="${pageContext.request.contextPath}/login.do">login</a></li>
	</c:otherwise>
</c:choose>
	
	<li><a href="">search</a>
	<c:if test = "${id != null }"> 
		<li><p style="color:blue;font-size: 25px;" >${id } </p>님 반갑습니다</li>		
	</c:if>
</ul>
<c:choose>
	<c:when test = "${id != null }"> 
		<p style="margin-left: 400px">PlayGround</p>
	</c:when>
	<c:otherwise>
		<p style="margin-left: 220px">PlayGround</p>
	</c:otherwise>
</c:choose>

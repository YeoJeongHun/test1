<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf"%>

<script>
	
</script>

adfasdfasdfasdf
<br/><br/><br/>

<br/>
<c:forEach items="${test}" var="list">
	<div>${list}</div>
</c:forEach>
<br/>

${test}


<%@ include file="../common/foot.jspf"%>
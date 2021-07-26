<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf"%>

<form method="POST" enctype="multipart/form-data" action="doJoin" onsubmit="MemberJoin__submitForm(this); return false;">
	<div class="form-control">
		<label class="label"> 프로필 이미지 </label>
		<input type="file" name="input__file" placeholder="프로필 이미지를 선택해주세요." />
	</div>
</form>

<%@ include file="../common/foot.jspf"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf"%>

<form method="POST" enctype="multipart/form-data" action="uploadFile" onsubmit="MemberJoin__submitForm(this); return false;">
	<div class="form-control">
		<label class="label"> 프로필 이미지 </label>
		<input type="file" name="input__file" placeholder="프로필 이미지를 선택해주세요." />
	</div>
	<br/>
	<button type="submit" class="btn btn-primary btn-sm mb-1">
		<span><i class="fas fa-user-plus"></i></span>
		&nbsp;
		<span>전송!</span>
	</button>
</form>
	
	<br/>
	<br/>
	<br/>
	결과 : ${testtest}
	
	<div class="flex-shrink-0">
		<img class="w-10 h-10 object-cover rounded-full shadow mr-2 cursor-pointer" alt="User avatar" src="C:\Users\aofhd\OneDrive\바탕 화면\test.jpg">
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	asdasd
	<form method="POST" enctype="multipart/form-data" action="test" onsubmit="MemberJoin__submitForm(this); return false;">
		<div class="form-control">
			<label class="label"> 프로필 이미지 </label>
			<input type="file" name="input__file" placeholder="프로필 이미지를 선택해주세요." />
		</div>
		<br/>
		<button type="submit" class="btn btn-primary btn-sm mb-1">
			<span><i class="fas fa-user-plus"></i></span>
			&nbsp;
			<span>전송!</span>
		</button>
	</form>
	
	<img class="rounded-full" src="${rq.getWriterProfileImgUri()}" alt="">

<%@ include file="../common/foot.jspf"%>

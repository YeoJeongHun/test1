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
	---------------------------------------------------------------------------------------------------------------------------------------
	
	<h1> 123123 </h1>
	<div id="testtest">
		ddddddddd
	</div>
	<script>
		$("#testtest").click(function(){
			$.ajax({
				type: 'POST',
				url: 'test1',
				data: {name: "여정훈"},
				dataType: 'json',
				success: function(data){
					console.log(data);
					alert('test성공' + data);
				},
				error: function(request, data) {alert(request.responseText + data);}
			}).done(function(data){
				alert('요청성공시 표현');
			}).fail(function(data){
				alert('요청실패시 표현');
			});

		});
	
	</script>
	<div id="results">
	</div>
	${aa}
	
	<article>
		
	</article>
	
	<input type="button" value="test" onclick="
		fetch('test').then(function(response){
			console.log(response);
			alert(response.url)
		})
	">
	<br/>
	<br/>
	---------------------------------------------------------------------------------------------------------------------------------------
	<img class="rounded-full" src="${rq.getWriterProfileImgUri()}" alt="">
	
</div>
 
<script>
 
    // 테스트용 클릭
    $("#testClick").click(function(){
        alert("테스트 클릭 성공");
    });
</script>

<%@ include file="../common/foot.jspf"%>

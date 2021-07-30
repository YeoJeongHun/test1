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
	---------------------------------------------------------------------------------------------------------------------------------------
	
	<h1> 123123 </h1>
	<div id="testtest">
		<br/>
		ddddddddd
		<br/>
		${aa}33
		<br/>
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
					$.each(data, function(i, item){
						$('#result').append('2ajax결과 value : '+ item + '<br/>');
						$('#result').append('<img class=\"rounded-full\" src=\"/file/profile/basic.jpg\">');
					})
				},
				error: function(request, data) {alert(request.responseText + data);}
			}).done(function(data){
				alert('요청성공시 표현');
			}).fail(function(data){
				alert('요청실패시 표현');
			});

		});
	
	</script>
	<div id="result">
	</div>
	<br/>
	---------------------------------------------------------------------------------------------------------------------------------------
	<br/><br/><br/>
	<div>
		<h2><b>이미지 미리보기</b></h2>
		<input type="file" id="input_img" onchange="readURL(this);"/>
	</div>
	<div>
		<h2>결과 로드된 이미지 결과</h2>
		<img class="rounded-full" id="img" />
		<span id="ttt">abcd</span>
	</div>
	
	<script>
		function readURL(input){
			if(input.files && input.files[0]){
				var reader = new FileReader();
				var img = document.getElementById('input_img');
				reader.onload = function(e){
					$('#img').attr('src',e.target.result);
					$('#img').attr('style', 'height: 150px; width: 150px');
					$('#ttt').text('asdfasdf');
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		
		function resize(obj){
			var naturalWidth = obj.naturalWidth; // img 너비
			var naturalHeight = obj.naturalHeight; // img 높이
			var imgWidth = 150; // div 너비
			var imgHeight = 150; // div 높이
		// (img 너비 / div 너비) 와 (img 높이 / div 높이) 를 이용해 비율을 구한다.
			if(Math.abs(naturalWidth / imgWidth) < Math.abs(naturalHeight / imgHeight)){
		// 작게 나온 쪽으로 크기를 100% 맞춘다.
				$(obj).css('width', '100%')
				var fitHeight = $(obj).height();
				$(obj).css('top', ((imgHeight - fitHeight) / 2) + 'px');
				$(obj).css('left', '0');
			}else{
				$(obj).css('height', '100%')
				var fitWidth = $(obj).width();
				$(obj).css('top', '0');
				$(obj).css('left', ((imgWidth - fitWidth) / 2) + 'px');
			}
		}
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	

<%@ include file="../common/foot.jspf"%>

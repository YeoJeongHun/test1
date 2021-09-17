<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf"%>

aa<br/>
${ccc}<br/><br/>

<script>

function test_click(btn) {
	localStorage.setItem('testa','aaa4444');
	var test = localStorage.getItem('testa')
	
	alert(test);
}


function test1_click(btn) {
	sessionStorage.setItem('id','testID');
	
	alert(sessionStorage.getItem('id'));
}

function test2(btn){
	setTimeout(function(){
		alert('hi');
	}, 1000);
}
window.onload = function(){
	setInterval(function(){
		$.ajax(
				{
					type : 'POST',
					url : '../reply/likeCheckAjax1',
					data : {
						id : 3,
						like : "down"
					},
					dataType : 'json',
					success : function(data) {
						$('#dislike_count_view' + id).remove();
						$('#dislike_count_area' + id).append(
								'<span id=\"dislike_count_view' + id + '\">'
										+ data.dislikeCount + '</span>');
					},
				}).fail(function(data) {
			alert('로그인 후 이용해주세요.');
		});
	}, 5000);
}

</script>

<a onclick="test_click(this);"><i class="fas fa-thumbs-up"></i></a>


<a onclick="test1_click(this);"><i class="fas fa-thumbs-down"></i></a>


<a onclick="test2(this);">test2</i></a>


<%@ include file="../common/foot.jspf"%>
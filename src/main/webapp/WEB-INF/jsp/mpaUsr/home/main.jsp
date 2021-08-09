<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf"%>


<div class="mainContainer">
	<div class="top">
		<span class="mainTop" style="text-align: center; font-size:3em;  color: #44aa88;">상단 제목 라인</span>
	</div>
	<div class="mainMiddle">
		<div class="mainMiddleFloar">
			<span class="mainMiddleLeft float-left">
				<span class="mainMiddleListBox">
					<table class="mainTable">
						<caption>
							<span class="float-left" style="text-align: left; font-size:1.2em;  color: #991155;">
								NOTICE TOP-10
							</span>
							<span class="float-right" style="text-align: right; font-size:0.9em;  color: #111111;">
								<a href="#">더보기+</a>
							</span>
						</caption>
						<th>1번칸</th>
						<th>2번칸</th>
						<th>3번칸</th>
						<c:forEach var="item" begin="0" end="9" step="1">
							<tr>
								<td>내용${item}</td>
								<td>내용${item}</td>
								<td>내용${item}</td>
							</tr>
						</c:forEach>
					</table>
				</span>
			</span>
			<span class="mainMiddleRight float-right">
				<span class="mainMiddleListBox">
					2번 게시글 공간
				</span>
			</span>
		</div>
		<div class="mainMiddleFloar">
			<span class="mainMiddleLeft float-left">
				<span class="mainMiddleListBox">
					3번 게시글 공간
				</span>
			</span>
			<span class="mainMiddleRight float-right">
				<span class="mainMiddleListBox">
					4번 게시글 공간
				</span>
			</span>
		</div>
	</div>
</div>


<%@ include file="../common/foot.jspf"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-home'></i></span> <span>HOME</span>" />

<%@ include file="../common/head.jspf"%>


<div class="mainContainer">
	<div class="top">
		<span class="mainTop" style="text-align: center; font-size:3em;  color: #44aa88;">Welcome To Hunny World!!</span>
	</div>
	<div class="mainMiddle">
		<div class="mainMiddleFloar">
			<span class="mainMiddleLeft float-left">
				<span class="mainMiddleListBox">
					<table class="mainTable">
						<caption>
							<span class="float-left mb-2" style="text-align: left; font-size:1.2em;  color: #991155;">
								NOTICE TOP-10
							</span>
							<span class="float-right" style="text-align: right; font-size:0.9em;  color: #111111;">
								<a href="../article/list?boardId=1">더보기+</a>
							</span>
						</caption>
						<c:forEach items="${topArticlesInNotice}" var="article">
							<tr>
								<td>
								<a href="../article/detail?id=${article.id}">
									<c:choose>
										<c:when test="${fn:length(article.title) gt 34}">
											<c:out value="${fn:substring(article.title,0,34)}"/>...&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:when>
										<c:otherwise>
											${article.title}&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:otherwise>
									</c:choose>
								</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</span>
			</span>
			<span class="mainMiddleRight float-right">
				<span class="mainMiddleListBox">
					<table class="mainTable">
						<caption>
							<span class="float-left mb-2" style="text-align: left; font-size:1.2em;  color: #991155;">
								LASTEST NOTICE
							</span>
							<span class="float-right" style="text-align: right; font-size:0.9em;  color: #111111;">
								<a href="../article/list?boardId=1">더보기+</a>
							</span>
						</caption>
						<c:forEach items="${recentArticlesInNotice}" var="article">
							<tr>
								<td>
								<a href="../article/detail?id=${article.id}">
									<c:choose>
										<c:when test="${fn:length(article.title) gt 34}">
											<c:out value="${fn:substring(article.title,0,34)}"/>...&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:when>
										<c:otherwise>
											${article.title}&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:otherwise>
									</c:choose>
								</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</span>
			</span>
		</div>
		<div class="mainMiddleFloar">
			<span class="mainMiddleLeft float-left">
				<span class="mainMiddleListBox">
					<table class="mainTable">
						<caption>
							<span class="float-left mb-2" style="text-align: left; font-size:1.2em;  color: #991155;">
								FREE TOP-10
							</span>
							<span class="float-right" style="text-align: right; font-size:0.9em;  color: #111111;">
								<a href="../article/list?boardId=2">더보기+</a>
							</span>
						</caption>
						<c:forEach items="${topArticlesInFree}" var="article">
							<tr>
								<td>
								<a href="../article/detail?id=${article.id}">
									<c:choose>
										<c:when test="${fn:length(article.title) gt 34}">
											<c:out value="${fn:substring(article.title,0,34)}"/>...&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:when>
										<c:otherwise>
											${article.title}&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:otherwise>
									</c:choose>
								</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</span>
			</span>
			<span class="mainMiddleRight float-right">
				<span class="mainMiddleListBox">
					<table class="mainTable">
						<caption>
							<span class="float-left mb-2" style="text-align: left; font-size:1.2em;  color: #991155;">
								LASTEST FREE
							</span>
							<span class="float-right" style="text-align: right; font-size:0.9em;  color: #111111;">
								<a href="../article/list?boardId=2">더보기+</a>
							</span>
						</caption>
						<c:forEach items="${recentArticlesInFree}" var="article">
							<tr>
								<td>
								<a href="../article/detail?id=${article.id}">
									<c:choose>
										<c:when test="${fn:length(article.title) gt 34}">
											<c:out value="${fn:substring(article.title,0,34)}"/>...&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:when>
										<c:otherwise>
											${article.title}&nbsp<span style="text-align: right; font-size:0.8em;  color: #F781F3;">[${article.repliesCount}]</span>
										</c:otherwise>
									</c:choose>
								</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</span>
			</span>
		</div>
	</div>
</div>
<script>

</script>


<%@ include file="../common/foot.jspf"%>

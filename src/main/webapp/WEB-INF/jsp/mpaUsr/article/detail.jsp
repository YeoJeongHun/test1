<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} ARTICLE DETAIL</span>" />

<%@ include file="../common/head.jspf"%>
<%@ include file="detail_script.jspf"%>
<script src="https://unpkg.com/vue@next"></script>
<div class="section section-article-detail">
	<div class="container mx-auto">
	    <div class="card bordered shadow-lg item-bt-1-not-last-child">
            <div class="card-title">
                <a href="javascript:history.back();" class="cursor-pointer">
                    <i class="fas fa-chevron-left"></i>
                </a>
                <span>게시물 상세페이지</span>
            </div>
            <div>
                <h1 class="title-bar-type-2 px-4">상세내용</h1>
                <div class="px-4 py-8">
                    <div class="flex">
                        <span>
                            <span>Comments:</span>
                            <span class="text-gray-400 text-light">${article.repliesCount}</span>
                        </span>
                        <span class="ml-3">
                            <span>Views:</span>
                            <span class="text-gray-400 text-light">${article.hitCount}</span>
                        </span>
                        <div class="flex-grow"></div>
                        <span >
                        	<a onclick="article_like(this)"><span id="like_star">
                        		<c:if test="${result eq 1}">
                        			<span class="fas fa-star" id="like_view"></span>
                        		</c:if>
                        		<c:if test="${result eq 0}">
                        			<span class="far fa-star" id="like_view"></span>
                        		</c:if>
                        	</span></a>
                            <span>Likes:</span>
                            <span id="article_like_count"><span class="text-gray-400 text-light" id="article_view">${article.likeCount}</span></span>
                        </span>
                    </div>

                    <div class="mt-4">
                        <span class="badge badge-outline">제목</span>
                        <div>
                        	${article.title}   
						</div>
                    </div>

                    <div class="mt-3 grid sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-3">
                        <div>
                            <span class="badge badge-primary">번호</span>
                            <span>${article.id}</span>
                        </div>

                        <div>
                            <span class="badge badge-accent">작성자</span>
                            <span>${article.extra__writerName}</span>
                        </div>

                        <div>
                            <span class="badge">등록날짜</span>
                            <span class="text-gray-600 text-light">${article.regDate}</span>
                        </div>

                        <div>
                            <span class="badge">수정날짜</span>
                            <span class="text-gray-600 text-light">${article.updateDate}</span>
                        </div>
                    </div>

                    <div class="mt-6">
                        <span class="badge badge-primary">작성자 프로필</span>
                        <div class="mt-3">
                            <img class="rounded" src="${article.getWriterProfileImgUri()}" alt="">
                        </div>
                        <br/>
                        <span class="badge badge-outline">본문</span>
                        <div class="mt-3">
                        	${article.bodyForPrint}    
                        </div>
                    </div>
                    <c:if test="${article.memberId eq rq.getLoginedMemberId()}">
                    <div style="float: right;">
                        <div class="plain-link-wrap gap-3 mt-4">
                        	<form method="POST" action="revise" class="plain-link" style="float: left;">
                        		<input type="hidden" name="articleId" value="${article.id}">
                            	<button type="submit"><span><i class="fas fa-edit"></i></span>
                            	<span>수정</span></button>
                            </form>
                            <a onclick="if ( !confirm('삭제하시겠습니까?') ) return false;" class="plain-link" style="float: left;">
                            	<span><i class="fas fa-trash"></i></span>
                            	<span>삭제</span>
                            </a>
                        </div>
                    </div>
                    </c:if>
                </div>
            </div>


            <div>
                <h1 class="title-bar-type-2 px-4">댓글</h1>
                <c:if test="${rq.notLogined}">
                    <div class="text-center py-4">
                        글 작성은 <a class="plain-link" href="${rq.loginPageUri}">로그인</a> 후 이용할 수 있습니다.
                    </div>
                </c:if>
                <c:if test="${rq.logined}">
                    <div class="px-4 py-8">
                        <!-- 댓글 입력 시작 -->
                        <form method="POST" action="../reply/doWrite" class="relative flex py-4 text-gray-600 focus-within:text-gray-400">
                            <input type="hidden" name="relTypeCode" value="article" />
                            <input type="hidden" name="relId" value="${article.id}" />
                            <input type="hidden" name="redirectUri" value="${rq.currentUri}" />
                            <img class="w-10 h-10 object-cover rounded-full shadow mr-2 cursor-pointer" alt="User avatar" src="${rq.getWriterProfileImgUri()}">

                            <span class="absolute inset-y-0 right-0 flex items-center pr-6">
                                <button type="submit" class="p-1 focus:outline-none focus:shadow-none hover:text-blue-500">
                                    <i class="fas fa-pen"></i>
                                </button>
                            </span>

                            <input name="body" type="text" class="w-full py-2 pl-4 pr-10 text-sm bg-gray-100 border border-transparent appearance-none rounded-tg placeholder-gray-400 focus:bg-white focus:outline-none focus:border-blue-500 focus:text-gray-900 focus:shadow-outline-blue" style="border-radius: 25px" placeholder="댓글을 입력해주세요." autocomplete="off">
                        </form>
                        <!-- 댓글 입력 끝 -->
                    </div>
                </c:if>

                <!-- 댓글 리스트 -->
                
                
                <div> 
                    <c:forEach items="${replies}" var="reply">
                       <div data-id="${reply.id}" data-body="${reply.bodyForPrint}" class="py-5 px-4">
                            <div class="flex">
                                <!-- 아바타 이미지 -->
                                <div class="flex-shrink-0">
                                    <img class="w-10 h-10 object-cover rounded-full shadow mr-2 cursor-pointer" alt="User avatar" src="${reply.getWriterProfileImgUri()}">
                                </div>
                                <div class="flex-grow px-1">
                                    <div class="flex text-gray-400 text-light text-sm">
                                        <span>${reply.extra__writerName}</span>
                                        <span class="mx-1">·</span>
                                        <span>${reply.updateDate}</span>
                                    </div>
                                    <div class="break-all" id="reply_body_${reply.id}">
                                        <span id="reply_body_view_${reply.id}">${reply.bodyForPrint}</span>
                                    </div>
                                    <div class="mt-1" id="like_set">
                                    	<span id="resetting">
                                        <span class="text-gray-400 cursor-pointer" id="like">
                                            <span id="like_count">
                                            <span><a onclick="like_click(this);"><i class="fas fa-thumbs-up"></i></a></span>
                                            <span id="like_count_area${reply.id}"><span id="like_count_view${reply.id}">${reply.likeCount}</span></span>
                                            </span>
                                        </span>
                                        <span class="ml-1 text-gray-400 cursor-pointer" id="dislike">
                                            <span id="dislike_count">
                                            <span><a onclick="dislike_click(this);"><i class="fas fa-thumbs-down"></i></a></span>
                                            <span id="dislike_count_area${reply.id}"><span id="dislike_count_view${reply.id}">${reply.dislikeCount}</span></span>
                                            </span>
                                        </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="plain-link-wrap gap-3 mt-3 float-left pl-11">
                                <c:if test="${reply.memberId == rq.loginedMemberId}">
                                	<span id="modify_button_${reply.id}">
                                	<span id="modify_button_view_${reply.id}">
                                     <a onclick="reply_modify(this);" class="plain-link">
                                        <span><i class="fas fa-edit"></i></span>
                                        <span>수정</span>
                                    </a>
                                    </span>
                                    </span>
                                </c:if>
                            </div>
                            <div class="plain-link-wrap gap-3 mt-3">
                                <c:if test="${reply.memberId == rq.loginedMemberId}">
                                     <a onclick="if ( confirm('정말 삭제하시겠습니까?') ) { ReplyList__deleteReply(this); } return false;" class="plain-link">
                                        <span><i class="fas fa-trash-alt pl-4"></i></span>
                                        <span>글 삭제</span>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>
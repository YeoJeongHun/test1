<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='fas fa-user-plus'></i></span> <span>MEMBER MODIFY</span>" />

<%@ include file="../common/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<%@ include file="modify_script.jspf"%>

<div class="section section-member-modify px-2">
	<div class="container mx-auto">
	    <form method="POST" enctype="multipart/form-data" action="doModify" onsubmit="MemberModify__submitForm(this); return false;">
	        <input type="hidden" name="loginPw">
	        <div class="form-control border w-1/4">
                <label class="label">
                    로그인아이디
                </label>
                <div class="plain-text">
                  ${rq.loginedMember.loginId}
                </div>
            </div>
            <div class="form-control">
            <div class="border w-1/4">
                <label class="label">
                    프로필 이미지
                </label>
                <div id="display">
                <span class="row-span-3 order-1" data-id="${rq.getLoginedMemberId()}">
                	<span id="renew"><img class="rounded-full" src="${rq.getWriterProfileImgUri()}" alt="" style="float: left;" id="profile_img"></span>
                	<div>
                		<a onclick="if ( confirm('정말 삭제하시겠습니까?') ) { reset_img(); } return false;" class="btn btn-sm mb-1">
                    		<span><i class="fas fa-trash-alt"></i></span>
                    		&nbsp;
                    		<span>프로필 이미지 삭제</span>
                		</a>
                	</div>
                </span>
                </div>
                <span class="">
					<input type="file" name="input__file" id="input_img" onchange="readImg(this);"/>
                </span>
            </div>
            </div>
            <div class="form-control">
                <label class="label">
                    로그인비밀번호
                </label>
                <input class="input input-bordered w-full" type="password" maxlength="30" name="loginPwInput" placeholder="로그인비밀번호를 입력해주세요." />
            </div>

            <div class="form-control">
                <label class="label">
                    로그인비밀번호 확인
                </label>
                <input class="input input-bordered w-full" type="password" maxlength="30" name="loginPwConfirm" placeholder="로그인비밀번호 확인을 입력해주세요." />
            </div>

            <div class="form-control">
                <label class="label">
                    이름
                </label>
                <input value="${rq.loginedMember.name}" class="input input-bordered w-full" type="text" maxlength="30" name="name" placeholder="이름을 입력해주세요." />
            </div>

            <div class="form-control">
                <label class="label">
                    별명
                </label>
                <input value="${rq.loginedMember.nickname}" class="input input-bordered w-full" type="text" maxlength="30" name="nickname" placeholder="별명을 입력해주세요." />
            </div>

            <div class="form-control">
                <label class="label">
                    휴대전화번호
                </label>
                <input value="${rq.loginedMember.cellphoneNo}" class="input input-bordered w-full" type="tel" maxlength="30" name="cellphoneNo" placeholder="휴대전화번호를 입력해주세요." />
            </div>

            <div class="form-control">
                <label class="label">
                    이메일
                </label>
                <input value="${rq.loginedMember.email}" class="input input-bordered w-full" type="email" maxlength="50" name="email" placeholder="이메일을 입력해주세요." />
            </div>

            <div class="mt-4 btn-wrap gap-1 float-left">
                <button type="submit" href="#" class="btn btn-primary btn-sm mb-1">
                    <span><i class="fas fa-user-plus"></i></span>
                    &nbsp;
                    <span>수정</span>
                </button>

                <a href="../member/mypage" class="btn btn-link btn-sm mb-1">
                    <span><i class="fas fa-home"></i></span>
                    &nbsp;
                    <span>마이페이지</span>
                </a>

                <a href="/" class="btn btn-link btn-sm mb-1">
                    <span><i class="fas fa-home"></i></span>
                    &nbsp;
                    <span>홈</span>
                </a>
            </div>
            <div class="mt-4 btn-wrap gap-1 float-right">
            	<a onclick="if ( !confirm('삭제하시겠습니까?') ) return false;" href="../member/withdrawal" class="btn btn-link btn-sm mb-1">
            		<span>
           			<i class="fas fa-trash"></i>
            		<span>회원 탈퇴</span>
           			</span>
      			</a>
            </div>
            <input type="hidden" name="authKey" value="${authKey}">
	    </form>
	</div>
</div>

<%@ include file="../common/foot.jspf"%> 
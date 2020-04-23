<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">
<title>회원가입</title>

<script src="./resources/jquery-3.4.1.js"></script>
<script src="./resources/jquery-3.4.1.min.js"></script>  

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./assets/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="./assets/css/jasny-bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="./assets/css/bootstrap-select.min.css"
	type="text/css">
<!-- Material CSS -->
<link rel="stylesheet" href="./assets/css/material-kit.css"
	type="text/css">
<!-- Font Awesome CSS -->
<link rel="stylesheet" href="./assets/fonts/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="./assets/fonts/themify-icons.css">

<!-- Animate CSS -->
<link rel="stylesheet" href="./assets/extras/animate.css"
	type="text/css">
<!-- Owl Carousel -->
<link rel="stylesheet" href="./assets/extras/owl.carousel.css"
	type="text/css">
<link rel="stylesheet" href="./assets/extras/owl.theme.css"
	type="text/css">
<!-- Rev Slider CSS -->
<link rel="stylesheet" href="./assets/extras/settings.css"
	type="text/css">
<!-- Slicknav js -->
<link rel="stylesheet" href="./assets/css/slicknav.css" type="text/css">
<!-- Main Styles -->
<link rel="stylesheet" href="./assets/css/main.css" type="text/css">
<!-- Responsive CSS Styles -->
<link rel="stylesheet" href="./assets/css/responsive.css"
	type="text/css">

<!-- Color CSS Styles  -->
<link rel="stylesheet" type="text/css"
	href="./assets/css/colors/red.css" media="screen" />

<script type="text/javascript">
	$(document).ready(function () {

		/* 이메일 인증코드 어떻게 할지 생각하기! */
		var mailcode;
		$("#email-code").hide();
		$("#send-email-code").on('click',function(){
			var email = $('input[name=email]').val();
			if (email == '') {
				alert('이메일을 입력해주세요.');
				return false;
			} else {
				$.ajax({
					type :'get',
					url:"sendMail.do",
					data:"email=" + email,
					success:function(code){
						if(code == "0") {
							alert("이미 가입된 이메일입니다. \n다른 이메일을 적어주시기 바랍니다.");
						} else {
							mailcode = code;
							$("#email-code").show();
					    	$("#send-email-code").attr("mailcheck", "done");
						}
						
					},
					error : function() {
						alert("인증코드 전송에 실패하였습니다."); 
					}
				})
			}
		});
		
		/* 아이디 중복 확인 */
		$("#id-check").on('click',function(){
			var id = $('input[name=id]').val();
			if (id == '') {
				alert('아이디를 입력해주세요.');
				return false;
			} else {
				$.ajax({
					type :'get',
					url:"checkId.do",
					data:"id=" + id,
					success:function(flag){
						if(flag == 0) {
							alert("사용할 수 있는 아이디입니다.");
					    	$("#id-check").attr("check", "done");
						} else if(flag == 1) {
							alert("사용할 수 없는 아이디입니다. \n다른 아이디를 사용하여 주시기 바랍니다.");
						}
						
					},
					error : function() {
						alert("아이디 중복 확인에 실패하였습니다."); 
					}
				})
			}
		});
		
		/* 비밀번호, 비밀번호 확인 비교 과정 */
		$("#pwd-check-same").hide();
		$("input").keyup(function(){
			if($('input[name=pwd]').val() == $('input[name=pwd-check]').val()) {
	            $("#pwd-check-same").hide();
	            $("#register").removeAttr("disabled");
			} else {
	            $("#pwd-check-same").show();
	            $("#register").attr("disabled", "disabled");
			}
		});
		
		$("#register").on('click',function(){
			if ($('input[name=id]').val() == '') {
				alert('아이디를 입력해주세요.');
				return false;
			}
			if ($('#id-check').attr('check') == null) {
				alert('아이디 중복 확인을 해주시기 바랍니다.');
				return false;
			}
			if ($('input[name=name]').val() == '') {
				alert('이름을 입력해주세요.');
				return false;
			}
			if ($('input[name=email]').val() == '') {
				alert('이메일을 입력해주세요.');
				return false;
			}
			if ($('input[name=pwd]').val() == '') {
				alert('비밀번호를 입력해주세요.');
				return false;
			}
			if ($('input[name=pwd-check]').val() == '') {
				alert('비밀번호 확인을 입력해주세요.');
				return false;
			}
			if ($('#send-email-code').attr('mailcheck') == null) {
				alert('이메일 인증해주시기 바랍니다.');
				return false;
			}
			/* 이메일 인증 코드 확인 과정 */
			if ($("#email-code").val() == '') {
				alert('인증 코드를 입력해주세요.');
				return false;
			} else if ($("#email-code").val() != mailcode) {
				alert('인증 코드가 다릅니다.');
				return false;
			} 
		});
	});
</script>

</head>
<body>
	<!-- Header Section Start -->
	<div class="header">
<div class="logo-menu">
	<nav class="navbar navbar-default main-navigation" role="navigation"
		data-spy="affix" data-offset-top="50">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand logo" href="main.do"><img
					src="./assets/img/logo.png" alt=""></a>
			</div>

			<div class="collapse navbar-collapse" id="navbar">
				<!-- Start Navigation List -->
				<ul class="nav navbar-nav">
					<li><a href="introduceMoviep.do"> MOVIEP </a></li>
					<li><a href="movieSortList.do"> 영화 <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown">
							<li><a href="movieSortList.do"> 장르별 영화 </a></li>
						</ul></li>
					<li><a href="freeList.do"> 커뮤니티 <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown">
							<li><a href="freeList.do"> 자유 게시판 </a></li>
							<li><a href="columnList.do"> 칼럼 게시판 </a></li>
							<li><a href="latterList.do"> 후기 게시판 </a></li>
						</ul></li>
					<li><a href="infoBoard.do"> Q & A <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown">
							<li><a href="infoBoard.do"> 공지사항 </a></li>
							<li><a href="customerCenter.do"> 고객센터 </a></li>
						</ul></li>
					<c:set var="sessionId" value="${sid}" />

					<c:if test="${!empty sid}">
						<c:choose>
							<c:when test="${fn : contains(sessionId, 'admin')}">
								<li><a href="userDelete.do"> 관리자페이지 <i
										class="fa fa-angle-down"></i>
								</a>
									<ul class="dropdown">
										<li><a href="userDelete.do"> 회원 관리 </a></li>
										<li><a href="movieUpdate.do"> 영화 관리 </a></li>
									</ul></li>
							</c:when>
							<c:otherwise>
								<li><a href="memberInfo.do"> 마이페이지 <i
										class="fa fa-angle-down"></i>
								</a>
									<ul class="dropdown">
										<li><a href="memberInfo.do"> 나의 정보 </a></li>
										<li><a href="memberWrite.do">내가 글 쓴 목록</a></li>
										<li><a href="changePwd.do">비밀번호 변경</a></li>

									</ul></li>
							</c:otherwise>
						</c:choose>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right float-right">
					<c:if test="${empty sid }">
						<li class="right"><a href="login.do"><i class="ti-lock"></i>
								로그인</a></li>
					</c:if>
					<c:if test="${!empty sid }">
						<li class="right"><a href="logout_ok.do"><i
								class="ti-lock"></i>로그아웃</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- Mobile Menu Start -->
		<ul class="wpb-mobile-menu">
			<li><a href="introduceMoviep.do">MOVIEP</a></li>
			<li><a href="movieSortList.do">영화</a>
				<ul>
					<li><a href="movieSortList.do">장르별 영화</a></li>
				</ul></li>
			<li><a href="freeList.do">커뮤니티</a>
				<ul>
					<li><a href="freeList.do">자유 게시판</a></li>
					<li><a href="columnList.do">칼럼 게시판</a></li>
					<li><a href="latterList.do">후기 게시판</a></li>
				</ul></li>
			<li><a href="infoBoard.do">Q & A</a>
				<ul>
					<li><a href="infoBoard.do">공지사항</a></li>
					<li><a href="customerCenter.do">고객센터</a></li>
				</ul></li>
			<c:if test="${!empty sid && sid != 'admin'}">

				<li><a href="memberInfo.do">마이페이지</a>
					<ul>
						<li><a href="memberInfo.do">나의 정보</a></li>
						<li><a href="memberWrite.do">내가 글 쓴 목록</a></li>
						<li><a href="changePwd.do">비밀번호 변경</a></li>

					</ul></li>
			</c:if>
			<c:if test="${!empty sid && sid == 'admin'}">

				<li><a href="userDelete.do">관리자페이지</a>
					<ul>
						<li><a href="userDelete.do">회원 관리</a></li>
						<li><a href="movieUpdate.do"> 영화 관리 </a></li>

					</ul></li>
			</c:if>
			<c:if test="${empty sid }">

				<li class="btn-m"><a href="login.do"><i class="ti-lock"></i>
						로그인</a></li>
			</c:if>
			<c:if test="${!empty sid }">
				<li class="btn-m"><a href="logout_ok.do"><i class="ti-lock"></i>
						로그아웃</a></li>
			</c:if>
		</ul>
		<!-- Mobile Menu End -->
	</nav>
</div>
	</div>
	<!-- Header Section End -->

	<!-- Page Header Start -->
	<div class="page-header"
		style="background: url(./assets/img/banner1.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb-wrapper">
						<h2 class="product-title">회원가입</h2>
						<ol class="breadcrumb">
							<li><a href="main.do"><i class="ti-home"></i>  MOVIEP</a></li>
							<li class="current">회원가입</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Page Header End -->

	<div id="content" class="my-account">
		<div class="container">
			<div class="row">
				<div
					class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-6 cd-user-modal">
					<div class="my-account-form">
						<ul class="cd-switcher">
							<li><a href="login.do">로그인</a></li>
							<li><a class="selected" href="register.do">회원가입</a></li>
						</ul>

						<div id="cd-signup" class="is-selected">
							<div class="page-login-form register">
								<form action="register_ok.do" method="post" name="fw" class="login-form">
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-user"></i> 
											<input type="text" id="id" class="form-control" name="id" placeholder="아이디" style="width:68%; display:inline;">
											<a class="btn btn-common1" id="id-check" style="width:30%; height:55px; padding-top:20px; margin-top:-2px; text-top:-10px;">확인</a>
										</div>
									</div>
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-user"></i> <input type="text" id="name"
												class="form-control" name="name" placeholder="이름">
										</div>
									</div>
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-email"></i> <input type="text" id="sender-email"
												class="form-control" name="email" placeholder="이메일">
											<a class="btn btn-common1 email-btn" id="send-email-code">인증 보내기</a>
										</div>
									</div>
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-lock"></i> <input type="text" id="email-code"
												class="form-control" name="email-code" placeholder="메일로 받은 인증코드을 적으세요">
										</div>
									</div>
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-lock"></i> <input type="password"
												class="form-control" name="pwd" placeholder="비밀번호">
										</div>
									</div>
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-lock"></i> <input type="password"
												class="form-control" name="pwd-check" placeholder="비밀번호 확인">
										</div>
									</div>
									<div id="pwd-check-same" style="color:red">비밀번호가 일치하지 않습니다.<br/><br/></div>
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-id-badge"></i> <input type="text"
												class="form-control" name="birth" placeholder="생년월일  (선택)">
										</div>
									</div>									
									<div class="form-group">
										<div class="input-icon">
											<i class="ti-id-badge"></i> <input type="text"
												class="form-control" name="phone" placeholder="전화번호 (선택)">
										</div>
									</div>									
									<input type="submit" id="register" class="btn btn-common log-btn" value="회원가입 하기" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>


	<!-- Go To Top Link -->
	<a href="#" class="back-to-top"> <i class="ti-arrow-up"></i>
	</a>

	<div id="loading">
		<div id="loading-center">
			<div id="loading-center-absolute">
				<div class="object" id="object_one"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_three"></div>
				<div class="object" id="object_four"></div>
				<div class="object" id="object_five"></div>
				<div class="object" id="object_six"></div>
				<div class="object" id="object_seven"></div>
				<div class="object" id="object_eight"></div>
			</div>
		</div>
	</div>
	<!-- Main JS  -->
	<script type="text/javascript" src="./assets/js/jquery-min.js"></script>
	<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./assets/js/material.min.js"></script>
	<script type="text/javascript" src="./assets/js/material-kit.js"></script>
	<script type="text/javascript" src="./assets/js/jquery.parallax.js"></script>
	<script type="text/javascript" src="./assets/js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="./assets/js/jquery.slicknav.js"></script>
	<script type="text/javascript" src="./assets/js/main.js"></script>
	<script type="text/javascript"
		src="./assets/js/jquery.counterup.min.js"></script>
	<script type="text/javascript" src="./assets/js/waypoints.min.js"></script>
	<script type="text/javascript" src="./assets/js/jasny-bootstrap.min.js"></script>
	<script type="text/javascript"
		src="./assets/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="./assets/js/form-validator.min.js"></script>
	<script type="text/javascript" src="./assets/js/contact-form-script.js"></script>
	<script type="text/javascript"
		src="./assets/js/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript"
		src="./assets/js/jquery.themepunch.tools.min.js"></script>
</body>
</html>
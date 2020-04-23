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
<title>MOIVEP</title>


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
					<li><a class="active" href="introduceMoviep.do"> MOVIEP </a></li>
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
			<li><a class="active" href="introduceMoviep.do">MOVIEP</a></li>
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
				<li class="btn-m"><a href="logout.do"><i class="ti-lock"></i>
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
                  <h2 class="product-title">MOVIEP</h2>
                  <ol class="breadcrumb">
                     <li><a href="main.do"><i class="ti-home"></i> MOVIEP</a></li>
                     <li class="current">MOVIEP란?</li>
                  </ol>
               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- Page Header End -->

   <!-- Main container Start -->
   <div class="about section">
      <div class="container">
         <div class="row">
            <div class="col-md-12 col-sm-12" style="text-align:center">
               <img src="assets/img/about/spider.jpg" alt="">
            </div>
            <div class="col-md-12 col-sm-12">
               <div class="about-content" style="text-align:center">
                  <h2 class="medium-title">MOVIEP란?</h2>
                  <p class="desc">쉽고 빠르게 가격를 비교 할 수 있습니다.</p>
                  <p>장르별로 영화를 찾아보세요. 영화를 합리적인 가격으로 구매해서 대여 / 소장하세요.</p>
                  <p>영화를 좋아하는 사용자들과 많은 이야기를 나누어보세요. 많은 생각을 공유할 수 있답니다.</p>
                  <p>칼럼으로 영화를 파악해보세요.</p>

               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- Main container End -->



   <%@include file="./footer.jsp"%>


   <!-- Go To Top Link -->
   <a href="#" class="back-to-top"> <i class="ti-arrow-up"></i>
   </a>
   <!-- 로딩 -->
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
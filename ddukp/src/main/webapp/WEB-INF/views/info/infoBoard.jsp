<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">
<title>공지사항</title>
<style type="text/css">
@media all and (max-width:768px) {

      .hiddenTd {
      display: none;
   }
   }

</style>

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
<link rel="stylesheet" href="./assets/css/main2.css" type="text/css">
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
					<li><a class="active" href="infoBoard.do"> Q & A <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown">
							<li><a class="active" href="infoBoard.do"> 공지사항 </a></li>
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
			<li><a class="active" href="infoBoard.do">Q & A</a>
				<ul>
					<li><a class="active" href="infoBoard.do">공지사항</a></li>
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
						<h2 class="product-title">공지사항</h2>
						<ol class="breadcrumb">
							<li><a href="main.do"><i class="ti-home"></i> MOVIEP</a></li>
							<li class="current">공지사항</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Page Header End -->


	<div id="content">
		<div class="container">
			<div class="content">
				<form method="" action="">
					<div class="row">
						<div class="col-md-3 col-xs-4">
							<div class="search-category-container1">
								<select class="dropdown-product selectpicker">
									<option selected="selected">---</option>
									<option>제목</option>
									<option>내용</option>
								</select>
							</div>
						</div>
						<div class="col-md-4 col-xs-6">
							<div class="form-group">
								<input class="form-control" type="text"
									placeholder="">
							</div>
						</div>
						<div class="col-md-1 col-xs-1">
							<button type="button" class="btn btn-search-icon">
								<i class="ti-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="job-alerts-item">
				<div class="alerts-list">
					<div class="row">
						<div class="col-md-1 col-xs-1 hiddenTd">
							<p>번호</p>
						</div>
						<div class="col-md-2 col-xs-2 hiddenTd">
							<p>카테고리</p>
						</div>
						<div class="col-md-5 col-xs-4">
							<p>제목</p>
						</div>
						<div class="col-md-2 col-xs-4">
							<p>날짜</p>
						</div>
						<div class="col-md-2 col-xs-4">
							<p>조회수</p>
						</div>

					</div>
				</div>
				<c:choose>
				<c:when test="${empty lLTO.infoList }">
									<div class="alerts-content" style="border : none; text-align:center">
						<p>게시글이 없습니다.</p>

					</div>
				</c:when>
				<c:otherwise>
				<c:forEach items="${lLTO.infoList}" var="to">
					<div class="alerts-content">
						<div class="row">
							<a href="infoView.do?inum=${to.inum}&cpage=${lLTO.cpage}">
								<div class="col-md-1 col-xs-1 hiddenTd">
									<p>${to.inum}</p>
								</div>
								<div class="col-md-2 col-xs-2 hiddenTd">
									<p>${to.ictgname}</p>
								</div>
								<div class="col-md-5 col-xs-4">
									<c:if test="${to.igap==0 }">
									<img src='./assets/img/board/mark_new2.gif'
											alt='HOT' style="margin-bottom: 10px;">
									</c:if>${to.isubject}</p>
								</div>
								<div class="col-md-2 col-xs-4">
									<p>${to.idate}</p>
								</div>
								<div class="col-md-2 col-xs-4">
									<p>${to.ihit}</p>
								</div>
							</a>

						</div>
					</div>
				</c:forEach>
				
				</c:otherwise>
								</c:choose>
				
				<br>
			</div>
			<div class="col-md-12" style="padding: 20px">
															<c:set var = "sessionId" value = "${sid}" />
				
				<c:if test="${!empty sid && fn : contains(sessionId, 'admin')}">

					<a href="infoWrite.do?cpage=${lLTO.cpage}" class="btn btn-common pull-right">쓰기</a>
				</c:if>
			</div>
			<div style="text-align: center; padding-top: 50px">
				<ul class="pagination">
					<c:choose>
						<c:when test="${lLTO.cpage eq 1}">
							<li class="active"><a href="#" class="btn btn-common"><i
									class="ti-angle-left"></i> </a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a
								href="infoBoard.do?cpage=${lLTO.cpage-1}"
								class="btn btn-common"><i class="ti-angle-left"></i> </a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${lLTO.startBlock}"
						end="${lLTO.endBlock}" step="1">
						<c:choose>
							<c:when test="${lLTO.cpage eq i}">
								<li class="active"><a href="#">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="infoBoard.do?cpage=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>


					<c:choose>
						<c:when test="${lLTO.cpage eq lLTO.totalPage}">
							<li class="active"><a href="#" class="btn btn-common"> <i
									class="ti-angle-right"></i></a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a
								href="infoBoard.do?cpage=${lLTO.cpage+1}"
								class="btn btn-common"><i class="ti-angle-right"></i></a></li>
						</c:otherwise>
					</c:choose>
				</ul>

			</div>
		</div>

	</div>



	<%@include file="../footer.jsp"%>


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
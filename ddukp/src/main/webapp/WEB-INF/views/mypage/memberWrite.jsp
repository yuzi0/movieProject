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
<title>내가 글 쓴 목록</title>
<style type="text/css">
@media all and (max-width:768px) {

      .hiddenTd {
      display: none;
   }
   }

</style>
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
<link rel="stylesheet" href="./assets/css/main2.css" type="text/css">
<!-- Responsive CSS Styles -->
<link rel="stylesheet" href="./assets/css/responsive.css"
	type="text/css">

<!-- Color CSS Styles  -->
<link rel="stylesheet" type="text/css"
	href="./assets/css/colors/red.css" media="screen" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#selectDelete_btn").click(function() {
			var confirm_val = confirm("정말 삭제하시겠습니까?");
			var chkbox = $(".chkrememberme");
			var send_cnt = 0;
			var freeArr = new Array();
			var reviewkArr = new Array();
			var columnArr = new Array();
			var qnaArr = new Array();
			var test = "";

			if (confirm_val) {
				$("input:checkbox[name='rememberme']").each(function() {
					if ($(this).is(":checked") == true) {
						if ($(this).attr("board") == "free") {
							freeArr.push($(this).attr("num"));
						} else if ($(this).attr("board") == "review") {
							reviewkArr.push($(this).attr("num"));
						} else if ($(this).attr("board") == "column") {
							columnArr.push($(this).attr("num"));
						} else if ($(this).attr("board") == "qna") {
							qnaArr.push($(this).attr("num"));
						}
					}

				});
				// alert("reviewkArr : " + reviewkArr + ", freeArr : " + freeArr);				
				$.ajax({
					url : "memberWriteDelete_ok.do",
					type : "post",
					data : {
						'review' : reviewkArr,
						'free' : freeArr,
						'column' : columnArr,
						'qna' : qnaArr
					},
					dataType : 'json',
					success : function(flag) {
						alert("성공");
						location.href = "memberWrite.do"
					},
					error : function(request, status, error) {
						alert("실패" + flag);
					}

				});
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
								<li><a class="active href="memberInfo.do"> 마이페이지 <i
										class="fa fa-angle-down"></i>
								</a>
									<ul class="dropdown">
										<li><a href="memberInfo.do"> 나의 정보 </a></li>
										<li><a class="active" href="memberWrite.do">내가 글 쓴 목록</a></li>
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

				<li><a class="active" href="memberInfo.do">마이페이지</a>
					<ul>
						<li><a href="memberInfo.do">나의 정보</a></li>
						<li><a class="active" href="memberWrite.do">내가 글 쓴 목록</a></li>
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
						<h2 class="product-title">내가 글 쓴 목록</h2>
						<ol class="breadcrumb">
							<li><a href="main.do"><i class="ti-home"></i> MOVIEP</a></li>
							<li class="current">내가 글 쓴 목록</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Page Header End -->
	<div id="content">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">

					<div class="content">
						<form method="get" action="memberWrite.do">
							<div class="row">
								<div class="col-md-3 col-xs-4">
									<div class="search-category-container1">
										<select class="dropdown-product selectpicker" name="opt">
											<option value="none" selected="selected">---------</option>
											<option value="0" ${searchKey eq "0" ? "selected" :"" }>제목</option>
											<option value="1" ${searchKey eq "1" ? "selected" :"" }>내용</option>
										</select>
									</div>
								</div>
								<div class="col-md-4 col-xs-6">
									<div class="form-group">
										<input class="form-control" type="text" placeholder="제목 / 내용"
											name="searchText">
									</div>
								</div>
								<div class="col-md-1 col-xs-1">
									<input type="submit" class="btn btn-search-icon"
										style="font-size: 14px; padding: 0px;" value="검색">
								</div>
							</div>
						</form>
												<div class="row">
							<div class="checkbox pull-right">  
							
								<label class="rememberme">전체선택 <input name="rememberme"
									class="rememberme chkremembermes" id="chkremembermes"
									 type="checkbox"">
								</label>
							</div>
						</div>
					</div>
					<div class="job-alerts-item">
						<div class="alerts-list">
							<div class="row">
								<div class="col-md-1 col-xs-1 hiddenTd">
									<p>글번호</p>
								</div>
								<div class="col-md-3 col-xs-3">
									<p>제목</p>
								</div>
								<div class="col-md-3 col-xs-3">
									<p>날짜</p>
								</div>
								<div class="col-md-2 col-xs-3">
									<p>조회수</p>
								</div>
								<div class="col-md-1 col-xs-3">체크</div>
							</div>
						</div>
						<c:choose>
						<c:when test="${empty mLTO.latterList }">
											<div class="alerts-content" style="border : none; text-align:center">
								<p>게시글이 없습니다.</p>

							</div>
						</c:when>
						<c:otherwise>
						<c:forEach items="${mLTO.latterList}" var="to">
							<div class="alerts-content">
								<div class="row">
									<a href="memberWriteView.do?board=${ to.ctgname}&num=${to.rnum}&cpage=${mLTO.cpage}">
										<div class="col-md-1 col-xs-1 hiddenTd">
											<p>${ to.rnum}</p>
										</div>
										<div class="col-md-3 col-xs-3">
											<p><c:if test="${ to.ctgname =='free'}">[자유 게시판] </c:if>
											<c:if test="${ to.ctgname =='review'}">[후기 게시판] </c:if>
											<c:if test="${ to.ctgname =='column'}">[컬럼 게시판] </c:if>
											<c:if test="${ to.ctgname =='qna'}">[고객센터] </c:if>
											${ to.rsubject}</p>
										</div>
										<div class="col-md-3 col-xs-3">
											<p>${ to.rdate}</p>
										</div>
										<div class="col-md-2 col-xs-3">
											<p>${ to.rhit}</p>
										</div>
										<div class="checkbox col-md-3">
											<label class="rememberme"> <input name="rememberme"
												id="rememberme" value="forever" class="chkrememberme" type="checkbox"
												board=${ to.ctgname} num=${ to.rnum}>
											</label>
										</div>
									</a>
								</div>
							</div>
						</c:forEach>
						</c:otherwise>
						</c:choose>
						<br>
					</div>
					<div class="" style="padding: 20px">
						<input type="button" class="btn btn-common pull-right"
							id="selectDelete_btn" value="삭제">
					</div>

					<!-- 페이징 처리 -->
					<div style="text-align: center; padding-top: 50px">
						<ul class="pagination">
							<c:choose>
								<c:when test="${mLTO.cpage eq 1}">
									<li class="active"><a
										href="#" class="btn btn-common"><i class="ti-angle-left"></i>
									</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty searchWord }">
											<li class="active"><a
												href="memberWrite.do?cpage=${mLTO.cpage-1}&opt=${searchKey}&searchText=${searchWord}"
												class="btn btn-common"> <i class="ti-angle-left"></i>
											</a></li>
										</c:when>
										<c:otherwise>
											<li class="active"><a
												href="memberWrite.do?cpage=${mLTO.cpage-1}"
												class="btn btn-common"> <i class="ti-angle-left"></i>
											</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							<c:forEach var="i" begin="${mLTO.startBlock}"
								end="${mLTO.endBlock}" step="1">
								<c:choose>
									<c:when test="${mLTO.cpage eq i}">
										<li class="active"><a href="#">${i}</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${not empty searchWord }">
												<li><a
													href="memberWrite.do?cpage=${i}&opt=${searchKey}&searchText=${searchWord}">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="memberWrite.do?cpage=${i}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${mLTO.cpage eq mLTO.totalPage}">
									<li class="active"><a href="#" class="btn btn-common">
											<i class="ti-angle-right"></i>
									</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty searchWord }">
											<li class="active"><a
												href="memberWrite.do?cpage=${mLTO.cpage+1}&opt=${searchKey}&searchText=${searchWord}"
												class="btn btn-common"> <i class="ti-angle-right"></i>
											</a></li>
										</c:when>
										<c:otherwise>
											<li class="active"><a
												href="memberWrite.do?cpage=${mLTO.cpage+1}"
												class="btn btn-common"> <i class="ti-angle-right"></i>
											</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>

						</ul>

					</div>
				</div>
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
	
	<script type="text/javascript">
	
	$("#chkremembermes").click(function(){
		 var chk = $("#chkremembermes").prop("checked");
		 if(chk) {
		  $(".chkrememberme").prop("checked", true);
		 } else {
		  $(".chkrememberme").prop("checked", false);
		 }
		});
	 $(".chkrememberme").click(function(){
		  $("#chkremembermes").prop("checked", false);
		 });	
	
	</script>
	
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
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
<title>회원 관리</title>
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
		$(".userBtn").on('click',function() {
			if (confirm($(this).attr('name')+ "탈퇴시키겠습니까?") == true) {
				location.href = 'userDelete_ok.do?userid='+ $(this).attr('name');
			} else {
				return;
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
								<li><a class="active"  href="userDelete.do"> 관리자페이지 <i
										class="fa fa-angle-down"></i>
								</a>
									<ul class="dropdown">
										<li><a class="active"  href="userDelete.do"> 회원 관리 </a></li>
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

				<li><a class="active" href="userDelete.do">관리자페이지</a>
					<ul>
						<li><a class="active" href="userDelete.do">회원 관리</a></li>
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
						<h2 class="product-title">회원 관리</h2>
						<ol class="breadcrumb">
							<li><a href="main.do"><i class="ti-home"></i> MOVIEP</a></li>
							<li class="current">회원 관리</li>
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
						<div class="row">
							<form method="get" action="userDelete.do">

								<div class="col-md-2 col-xs-4">
									<div class="search-category-container1">
										<select class="dropdown-product selectpicker" name="opt">
											<option value="none" selected="selected">---------</option>
											<option value="0"
												${udListTO.searchKey eq "0" ? "selected" :"" }>아이디</option>
											<option value="1"
												${udListTO.searchKey eq "1" ? "selected" :""}>이메일</option>
										</select>

									</div>
								</div>
								<div class="col-md-4 col-xs-6">
									<div class="form-group">
										<input class="form-control searchText" type="text"
											name="searchText" id="searchText" placeholder="이름  / 이메일"
											value="${udListTO.searchWord }">
									</div>
								</div>
								<div class="col-md-1 col-xs-1">
									<input type="submit" class="searchBtn btn btn-search-icon"
										style="font-size: 14px; padding: 0px;" value="검색">
								</div>
							</form>
						</div>
						<div class="row">
							<div class="checkbox pull-right">  
							
								<label class="rememberme">전체선택 <input name="rememberme"
									class="rememberme chkremembermes" id="chkremembermes"
									value="${to.uid }" type="checkbox" data-uId="${to.uid}">
								</label>
							</div>
						</div>
					</div>
					<div class="job-alerts-item">
						<div class="alerts-list">
							<div class="row">
								<div class="col-md-3 col-xs-4">
									<p>아이디</p>
								</div>
								<div class="col-md-3 col-xs-5">
									<p>이메일</p>
								</div>
								<div class="col-md-3 col-xs-3 hiddenTd">
									<p>탈퇴</p>
								</div>
								<div class="col-md-3 col-xs-3">체크</div>
							</div>
						</div>
						<div class="alerts-content">
							<c:forEach items="${udListTO.userLists }" var="to" varStatus="vs">
								<div class="row">
									<div class="col-md-3 col-xs-4">
										<%-- 											
												<input type="text" value="${to.uid }" name="userid" readonly="readonly">
 --%>
										<p name="${to.uid }" id="userid">${to.uid }</p>
									</div>
									<div class="col-md-3 col-xs-6">
										<p id="msub">${to.uemail }</p>
									</div>
									<div class="col-md-3 col-xs-3 hiddenTd">
										<a name="${to.uid }" class="btn btn-common2 userBtn">탈퇴</a>
									</div> 
									<div class="checkbox col-md-2">
										<label class="rememberme"> <input name="rememberme"
											class="rememberme chkrememberme" id="chkrememberme"
											value="${to.uid }" type="checkbox" data-uId="${to.uid}">
										</label>
									</div>

								</div>
							</c:forEach>
						</div>


						<br>
					</div>
					<div class="" style="padding: 20px">
						<button class="selectDelete_btn btn btn-common pull-right">
							모두 탈퇴</button>
					</div>



					<div style="text-align: center; padding-top: 50px">
						<ul class="pagination">

							<c:choose>
								<c:when test="${udListTO.cpage eq 1}">
									<li class="active"><a href="#" class="btn btn-common"><i
											class="ti-angle-left"></i> </a></li>
								</c:when>
								<c:otherwise>

									<c:choose>
										<c:when test="${not empty udListTO.searchWord }">
											<li class="active"><a
												href="userDelete.do?cpage=${udListTO.cpage-1}&opt=${udListTO.searchKey}&searchText=${udListTO.searchWord}"
												class="btn btn-common"><i class="ti-angle-left"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="active"><a
												href="userDelete.do?cpage=${udListTO.cpage-1}"
												class="btn btn-common"><i class="ti-angle-left"></i></a></li>

										</c:otherwise>
									</c:choose>

								</c:otherwise>
							</c:choose>


							<c:forEach var="i" begin="${udListTO.startBlock}"
								end="${udListTO.endBlock}" step="1">
								<c:choose>
									<c:when test="${udListTO.cpage eq i}">
										<li class="active"><a href="#">${i}</a></li>
									</c:when>
									<c:otherwise>
										<c:choose>

											<c:when test="${not empty udListTO.searchWord }">
												<li><a
													href="userDelete.do?cpage=${i}&opt=${udListTO.searchKey}&searchText=${udListTO.searchWord}">${i}</a></li>

											</c:when>
											<c:otherwise>
												<li><a href="userDelete.do?cpage=${i}">${i}</a></li>

											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>


							<c:choose>
								<c:when test="${udListTO.cpage eq udListTO.totalPage}">
									<li class="active"><a href="#" class="btn btn-common">
											<i class="ti-angle-right"></i>
									</a></li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty udListTO.searchWord }">
											<li class="active"><a
												href="userDelete.do?cpage=${udListTO.cpage+1}&opt=${udListTO.searchKey}&searchText=${udListTO.searchWord}"
												class="btn btn-common"><i class="ti-angle-right"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="active"><a
												href="userDelete.do?cpage=${udListTO.cpage+1}"
												class="btn btn-common"><i class="ti-angle-right"></i></a></li>

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
	<!-- Main JS  -->
	<script type="text/javascript">
		$(".selectDelete_btn").click(function() {
			var confirm_val = confirm("정말 삭제하시겠습니까?");
			var chkbox = $(".chkrememberme");
			var send_cnt = 0;
			var checkArr = new Array();
			var test = "";

			if (confirm_val) {

				$("input:checkbox[name='rememberme']").each(function() {
					if ($(this).is(":checked") == true) {
						checkArr.push($(this).attr("data-uId"));
					}

				});
				console.log(checkArr);
				$.ajax({
					url : "usersDelete_ok.do",
					type : "post",
					data : {
						'chbox' : checkArr
					},
					dataType : 'json',
					success : function(flag) {
						alert("성공");
						location.href = "userDelete.do"
					},
					error : function(request, status, error) {
						alert("실패" + flag);
					}

				});
			}
		});
		
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
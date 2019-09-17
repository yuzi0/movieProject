<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">
<title>영화상세페이지</title>


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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Color CSS Styles  -->
<link rel="stylesheet" type="text/css"
	href="./assets/css/colors/red.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="./assets/css/movie/movieview.css" media="screen" />
</head>
<body>
	<!-- Header Section Start -->
	<div class="header">
		<%@include file="../nav2.jsp"%>

	</div>
	<!-- Header Section End -->

	<div class="page-header"
		style="background: url(./assets/img/movie/movie-918655_1920.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb-wrapper">
						<h2 class="product-title">영화상세</h2>
						<ol class="breadcrumb">
							<li><a href="#"><i class="ti-home"></i> MOVIEP</a></li>
							<li class="current">상세페이지</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="content">
		<div class="container">
			<div class="col-sm-12 col-md-8 col-md-offset-2">

				<div class="page-ads box2">
					<div class="movie-img">
						<img alt="" src="./assets/img/movie/movie_image.jpg">
					</div>
					<div class="movie">
						<div class="movie-subject"
							>
							<b>엑시트</b>
						</div>
						<div class="movie-info">
							<b>평점</b> &nbsp&nbsp <img alt=""
								src="./assets/img/movie/heart.png"> <img alt=""
								src="./assets/img/movie/heart.png"> <img alt=""
								src="./assets/img/movie/heart.png"> <img alt=""
								src="./assets/img/movie/heart.png"> <img alt=""
								src="./assets/img/movie/heart.png"> &nbsp&nbsp &nbsp&nbsp
							&nbsp&nbsp<b>12</b>세 관람가
						</div>
					</div>
					<div class="movie-link">
						<div class="naver">
							<a href="https://series.naver.com/movie/detail.nhn?productNo=4462861"><h4>네이버</h4></a>
							<p>가격 : 10000원</p>
							<p>할인 : 그런거 음다</p>
							<p>이벤트 : [캐시] 추석연휴 캐시백 이벤트! 최대 1만 2천 캐시 돌려받자</p>
							<p>
								이벤트 : [쿠폰] 짠내 탈출 액션
								<엑시트> 구매자 전원 2천캐시! 
							</p>
							
						</div>
						<div class="youtube">
							<h4><b>유투브</b></h4><br>
							<p>가격 : 10000원</p>
							<p>할인 : 그런거 음다</p>
							<p>이벤트 : [캐시] 추석연휴 캐시백 이벤트! 최대 1만 2천 캐시 돌려받자</p>
							<p>
								이벤트 : [쿠폰] 짠내 탈출 액션
								<엑시트> 구매자 전원 2천캐시! 
							</p>
						</div>
					</div>
				</div>
				<div style="margin-bottom: 20px;"></div>

			</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
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
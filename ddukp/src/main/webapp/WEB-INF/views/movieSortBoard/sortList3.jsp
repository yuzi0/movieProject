<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">
<title>영화리스트</title>


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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Color CSS Styles  -->
<link rel="stylesheet" type="text/css"
	href="./assets/css/colors/red.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="./assets/css/movie/sortList.css" media="screen" />
</head>
<body>
	<!-- Header Section Start -->
	<div class="header">
		<%@include file="../nav2.jsp"%>

	</div>
	<div class="page-header"
		style="background: url(./assets/img/movie/movie-918655_1920.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb-wrapper">
						<h2 class="product-title">영화</h2>
						<ol class="breadcrumb">
							<li><a href="#"><i class="ti-home"></i> MOVIEP</a></li>
							<li class="current">영화리스트</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="content">
		<div class="container" id="page">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="page-ads">
					<div id="header" class="col-md-3">
						<div id="logo">
							<!-- <img src="images/pic02.jpg" alt="" /> -->
							<h1><a href="#">Genre</a></h1>
							<!-- <span>Design by <a href="#"rel="nofollow">TEMPLATED</a></span> -->
						</div>
						<div id="menu">
							<ul class="urrent_page_item">
								<li><a href="#" accesskey="1"title="">전체</a></li>
								<li><a href="#" accesskey="2" title="">액션</a></li>
								<li><a href="#" accesskey="3" title="">로맨스</a></li>
								<li><a href="#" accesskey="4" title="">드라마</a></li>
								<li ><a href="#" accesskey="6" title="">코미디</a></li>
								<li><a href="#" accesskey="7" title="">공포/스릴러</a></li>
								<li ><a href="#" accesskey="8" title="">판타지</a></li>
								<li><a href="#" accesskey="9" title="">SF</a></li>
								<li ><a href="#" accesskey="10" title="">어린이/가족</a></li>
								<li ><a href="#" accesskey="11" title="">애니메이션</a></li>
							</ul>
						</div>
					</div>
					<div id="list" class="col-md-9">
						<div class="effect"><img src="./assets/img/movie/movie_image.jpg"></div>
						<div class="effect"><img src="./assets/img/movie/movie_image.jpg"></div>
						<div class="effect"><img src="./assets/img/movie/movie_image.jpg"></div>
						<div class="effect"><img src="./assets/img/movie/movie_image.jpg"></div>
						<div class="effect"><img src="./assets/img/movie/movie_image.jpg"></div>
						<div class="effect"><img src="./assets/img/movie/movie_image.jpg"></div>
					</div>
				</div>
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
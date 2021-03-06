<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">

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

<title>메인페이지</title>
</head>

<body>
	<!-- Header Section Start -->
	<div class="header">
		<!-- Start intro section -->
		<section id="intro" class="section-intro">
			<%@include file="nav2.jsp"%>

			<!-- Testimonial Section Start -->
			<section id="testimonial" class="section">
				<div class="container">
					<div class="row">
						<div class="touch-slider" class="owl-carousel owl-theme">
							<div class="item active text-center">
								<img class="img-member" src="assets/img/testimonial/img1.jpg"
									alt="">
								<div class="client-info">
									<h2 class="client-name">
										Jessica <span>(Senior Accountant)</span>
									</h2>
								</div>
								<p>
									<i class="fa fa-quote-left quote-left"></i> The team that was
									assigned to our project... were extremely professional <i
										class="fa fa-quote-right quote-right"></i><br> throughout
									the project and assured that the owner expectations were met
									and <br> often exceeded.
								</p>
							</div>
							<div class="item text-center">
								<img class="img-member" src="assets/img/testimonial/img2.jpg"
									alt="">
								<div class="client-info">
									<h2 class="client-name">
										John Doe <span>(Project Menager)</span>
									</h2>
								</div>
								<p>
									<i class="fa fa-quote-left quote-left"></i> The team that was
									assigned to our project... were extremely professional <i
										class="fa fa-quote-right quote-right"></i><br> throughout
									the project and assured that the owner expectations were met
									and <br> often exceeded.
								</p>
							</div>
							<div class="item text-center">
								<img class="img-member" src="assets/img/testimonial/img3.jpg"
									alt="">
								<div class="client-info">
									<h2 class="client-name">
										Helen <span>(Engineer)</span>
									</h2>
								</div>
								<p>
									<i class="fa fa-quote-left quote-left"></i> The team that was
									assigned to our project... were extremely professional <i
										class="fa fa-quote-right quote-right"></i><br> throughout
									the project and assured that the owner expectations were met
									and <br> often exceeded.
								</p>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Testimonial Section End -->
		</section>
		<!-- end intro section -->
	</div>


	<!-- Featured Jobs Section Start -->
	<section class="featured-jobs section">
		<div class="container">
			<h2 class="section-title">인기 영화 목록</h2>
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-1.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Graphic Designer</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> Dallas, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-2.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Software Engineer</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> Delaware, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-3.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Managing Director</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> NY, United States
									</div>
								</div>
							</div>
						</div> 
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-3.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Graphic Designer</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> Washington, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-2.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Software Engineer</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> Dallas, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-1.jpg" alt="">
									</a>
								</figure> 
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Managing Director</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> NY, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>				
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-2.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Software Engineer</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> Dallas, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="job-page.html"> <img
										src="assets/img/features/img-1.jpg" alt="">
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title">
										<a href="job-page.html">Managing Director</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin"></i> NY, United States
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<a href="movieSortList.do" class="pull-right">더 보기</a>
				</div>
			</div>
		</div>
	</section>
	<!-- Featured Jobs Section End -->

	<!-- Start Purchase Section -->
	<section class="section purchase" data-stellar-background-ratio="0.5">
		<div class="container">
			<div class="row">
				<div class="section-content text-center">
					<h1 class="title-text">커뮤니티 즐기기</h1>
					<p>영화광들과 여러 이야기를 나누어 보세요!</p>
					<a href="freeList.do" class="btn btn-common">커뮤니티 게시판 가기</a>
				</div>
			</div>
		</div>
	</section>
	<!-- End Purchase Section -->

	<!-- Blog Section -->
	<section id="blog" class="section">
		<!-- Container Starts -->
		<div class="container">
			<h2 class="section-title">인기 칼럼</h2>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 blog-item">
					<!-- Blog Item Starts -->
					<div class="blog-item-wrapper">
						<div class="blog-item-img">
							<a href="columnView.do"> <img
								src="assets/img/blog/home-items/img1.jpg" alt="">
							</a>
						</div>
						<div class="blog-item-text">
							<div class="meta-tags">
								<span class="date"><i class="ti-calendar"></i> 12, 20,
									2017</span> <span class="comments"><a href="#"><i
										class="ti-comment-alt"></i> 댓글: 5</a></span>
							</div>
							<a href="columnView.do">
								<h3>엑시트 무엇인 인기를 끌었나
								</h3>
							</a>
							<p>조정석의 얼굴이 너무 잘생겼다 보는내내 용남아ㅠㅠ하면서 울었따</p>
							<a href="columnView.do" class="btn btn-common btn-rm">보러가기</a>
						</div>
					</div>
					<!-- Blog Item Wrapper Ends-->
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 blog-item">
					<!-- Blog Item Starts -->
					<div class="blog-item-wrapper">
						<div class="blog-item-img">
							<a href="columnView.do"> <img
								src="assets/img/blog/home-items/img1.jpg" alt="">
							</a>
						</div>
						<div class="blog-item-text">
							<div class="meta-tags">
								<span class="date"><i class="ti-calendar"></i> 12, 20,
									2017</span> <span class="comments"><a href="#"><i
										class="ti-comment-alt"></i> 댓글: 5</a></span>
							</div>
							<a href="columnView.do">
								<h3>엑시트 무엇인 인기를 끌었나
								</h3>
							</a>
							<p>조정석의 얼굴이 너무 잘생겼다 보는내내 용남아ㅠㅠ하면서 울었따</p>
							<a href="columnView.do" class="btn btn-common btn-rm">보러가기</a>
						</div>
					</div>
					<!-- Blog Item Wrapper Ends-->
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 blog-item">
					<!-- Blog Item Starts -->
					<div class="blog-item-wrapper">
						<div class="blog-item-img">
							<a href="columnView.do"> <img
								src="assets/img/blog/home-items/img1.jpg" alt="">
							</a>
						</div>
						<div class="blog-item-text">
							<div class="meta-tags">
								<span class="date"><i class="ti-calendar"></i> 12, 20,
									2017</span> <span class="comments"><a href="#"><i
										class="ti-comment-alt"></i> 댓글: 5</a></span>
							</div>
							<a href="columnView.do">
								<h3>엑시트 무엇인 인기를 끌었나
								</h3>
							</a>
							<p>조정석의 얼굴이 너무 잘생겼다 보는내내 용남아ㅠㅠ하면서 울었따</p>
							<a href="columnView.do" class="btn btn-common btn-rm">보러가기</a>
						</div>
					</div>
					<!-- Blog Item Wrapper Ends-->
				</div> 
			</div>
		</div>
	</section>
	<!-- blog Section End -->

	<%@include file="footer.jsp"%>

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
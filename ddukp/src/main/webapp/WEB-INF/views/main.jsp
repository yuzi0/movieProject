<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<div class="touch-slider">
							<div class="item active text-center main-slider"
								style="background-image: url(assets/img/main/main1.jpg);">
								<br /> <br />
								<p>
									<i class="fa fa-quote-left quote-left"></i> 난 인생의 위기가 좋아 <i
										class="fa fa-quote-right quote-right"></i><br> 인생이 나에게
									날리는 펀치를 계속 맞는거야<br /> 그러다 코너에 몰린 순간 결정적인 반격의 펀치를 날리는거지
								</p>
								<div class="client-info">
									<h2 class="client-name">
										라라랜드 <span>(2016)</span>
									</h2>
								</div>
							</div>
							<div class="item text-center main-slider"
								style="background-image: url(assets/img/main/main2.jpg);">
								<br /> <br />
								<p>
									<i class="fa fa-quote-left quote-left"></i> 그녀를 잃는다면 감당할 수 있겠소?
									<i class="fa fa-quote-right quote-right"></i><br> 그럼 답이
									나왔군. <br /> 계산없이 사랑하시오.
								</p>
								<div class="client-info">
									<h2 class="client-name">
										이프 온리 <span>(2004)</span>
									</h2>
								</div>
							</div>
							<div class="item text-center  main-slider"
								style="background-image: url(assets/img/main/main3.jpg);">
								<br /> <br />
								<p>
									<i class="fa fa-quote-left quote-left"></i> 모든 일에는 끝이 있어. <i
										class="fa fa-quote-right quote-right"></i><br> 그래서 시간이 더
									소중하게 느껴지는거야.
								</p>
								<div class="client-info">
									<h2 class="client-name">
										비포선라이즈 <span>(1955)</span>
									</h2>
								</div>
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
			<c:forEach items="${msLTO.movieallList}" var="to">
		
				<div class="col-md-3 col-sm-4 col-xs-6">
					<div class="featured-item">
						<div class="featured-wrap">
							<div class="featured-inner">
								<figure class="item-thumb">
									<a class="hover-effect" href="movieSortView.do?movie=${to.mnum }"> <img
										src="<c:url value='${to.img}'/>" alt=""> 
									</a>
								</figure>
								<div class="item-body">
									<h3 class="job-title" id="msub">
										<a href="job-page.html">${to.msubject}</a>
									</h3>
									<div class="adderess">
										<i class="ti-location-pin">${to.genre}</i> 
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			
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
			<br /> <br />
			<h2 class="section-title">인기 칼럼</h2>
			<div class="row">
			<c:forEach items="${lLTO.columnList}" var="to">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 blog-item">
					<!-- Blog Item Starts -->
					<div class="blog-item-wrapper">
						<div class="blog-item-img">
							<a href="columnView.do?cnum=${to.cnum }"> <img src="${to.cimg }" alt="" style="hegith:auto">
							</a>
						</div>
						<div class="blog-item-text">
							<div class="meta-tags">
								<span class="date">
									<i class="ti-calendar"></i> 
									${ to.cdate}
								</span> 
								<span class="comments">
									<a href="#"><i class="ti-comment-alt"></i> 조회수: ${to.chit}</a>
								</span>
							</div>
							<a href="columnView.do?cnum=${to.cnum }"> <h3>${ to.csubject}</h3> </a>
							<div style="overflow:hidden"><p>${ to.ccontent}</p></div>
							<a href="columnView.do?cnum=${to.cnum }" class="btn btn-common btn-rm">보러가기</a>
						</div>
					</div>
					<!-- Blog Item Wrapper Ends-->
				</div>
			</c:forEach>
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
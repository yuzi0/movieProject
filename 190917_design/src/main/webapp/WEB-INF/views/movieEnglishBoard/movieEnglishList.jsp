<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
   content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">
<title>공지사항</title>


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
      <%@include file="../nav2.jsp"%>

   </div>
   <div class="page-header"
      style="background: url(./assets/img/movie/movie-918655_1920.jpg);">
      <div class="container">
         <div class="row">
            <div class="col-md-12">
               <div class="breadcrumb-wrapper">
                  <h2 class="product-title">영어배우기 쉬운 영화</h2>
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
      <div class="container">   
         <div class="job-alerts-item">
            <div class="alerts-list">
               <div class="row">
                  <div class="col-xs-2">
                     <p>영화</p>
                  </div>
                  <div class="col-xs-2">
                     <p>제목</p>
                  </div>
                  <div class="col-xs-2">
                     <p>발매 날짜</p>
                  </div>
                  <div class="col-xs-2">
                     <p>장르</p>
                  </div>
                  <div class="col-xs-4">
                     <p>내용</p>
                  </div>
               </div>
            </div>
            <div class="alerts-content">
               <div class="row">
			   	  <a href="movieSortView.do">
	                  <div class="col-xs-2">
	                     <p><img src="./assets/img/movie/movie_image.jpg" width="95%"></p>
	                  </div>
	                  <div class="col-xs-2">
	                     <p>영화 제목</p>
	                  </div>
	                  <div class="col-xs-2">
	                     <p>발매 날짜</p>
	                  </div>
	                  <div class="col-xs-2">
	                     <p>장르</p>
	                  </div>
	                  <div class="col-xs-4">
	                     <p>내용</p>
	                  </div>
	              </a>
               </div>
         
            </div>
         <br>
         </div>
         <div style="text-align: center; padding-top: 50px">
            <ul class="pagination">
               <li class="active">
                  <a href="#" class="btn btn-common">
                     <i class="ti-angle-left"></i>
                  </a>
               </li>
               <li><a href="#">1</a></li>
               <li><a href="#">2</a></li>
               <li><a href="#">3</a></li>
               <li><a href="#">4</a></li>
               <li><a href="#">5</a></li>
               <li class="active">
                  <a href="#" class="btn btn-common">
                     <i class="ti-angle-right"></i>
                  </a>
               </li>
            </ul>
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
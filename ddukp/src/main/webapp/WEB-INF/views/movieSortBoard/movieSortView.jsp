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
               <li><a class="active" href="movieSortList.do"> 영화 <i
                     class="fa fa-angle-down"></i>
               </a>
                  <ul class="dropdown">
                     <li><a class="active" href="movieSortList.do"> 장르별 영화 </a></li>
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
         <li><a class="active" href="movieSortList.do">영화</a>
            <ul>
               <li><a class="active" href="movieSortList.do">장르별 영화</a></li>
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
                  <img alt="" src="${img }" style="height:200px">
               </div>
               <div class="movie">
                  <div class="movie-subject">
                     <b>${movie}</b>
                  </div>
                  <div class="movie-info">
                     <b>평점</b> &nbsp&nbsp 
                     ${score }
                     &nbsp&nbsp &nbsp&nbsp
                     &nbsp&nbsp<b>${level_naver }</b>
                     &nbsp&nbsp &nbsp&nbsp
                     &nbsp&nbsp
                     <c:if test="${not empty date_naver}"><b>개봉 날짜</b> : ${date_naver } <br/></c:if><br/>
                  </div>
                  <c:if test="${not empty director_naver}"><b>감독</b> : ${director_naver } <br/></c:if>
                  <c:if test="${not empty star_naver}"><b>주연</b> : ${star_naver } <br/></c:if>
                  <c:if test="${not empty summary_naver}"><b>줄거리</b> : ${summary_naver } <br/></c:if>
               </div>
               <hr />
               <div class="movie-link">
                  <c:if test="${not empty link_naver}">
                  <div class="naver">
                     <a href="${link_naver }"><h4>네이버</h4>
                     <p>${buyType_naver } 가격 : ${price_naver }원</p></a>
                  </div>
                  </c:if>
                  <c:if test="${not empty price_buy_google}">
                  <div class="google">
                     <a href="${link_google }"><h4>구글 플레이</h4>
                     <p>구매 가격 : ${price_buy_google }원</p>
                     <c:if test="${not empty price_rent_google}">
                        <p>대여 가격 : ${price_rent_google }원</p>
                     </c:if></a>
                  </div>
                  </c:if>
               </div>
            </div>
            <div style="margin-bottom: 20px;"></div>

         </div>
         <div class="col-md-12" style="padding: 20px">
            <a href="movieSortList.do?movieCategory=${movieCategory }&cpage=${cpage }" class="btn btn-common pull-right">목록</a>
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
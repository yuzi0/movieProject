<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>	<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0">

<!-- editor -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.0.min.js"></script>

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
	href="./assets/css/lboard/fwrite.css" media="screen" />
<title>자유게시판 상세페이지</title>

<script type="text/javascript">
	$(document).ready(function(){
		$("#btn-left1").on('click',function(){
			var chk = confirm("정말 삭제하시겠습니까?");
			if(chk){
				location.href='freeDelete.do?fnum=${param.fnum}';
			}
		});
		
		$("#list").on('click',function(){
			var board = '${param.board}';
			if(board == "free") {
				//alert('${param.board}');
				$("#list").attr("href", 'memberWrite.do?cpage=${param.cpage}');
			}
		});
		
		
	 	$("#csubmit").on('click',function(){

			if ($('#comment').val() == '') {
				alert('내용을 입력해주세요.');
				return false;
			}
			$.ajax({
				type : 'post',
				url : 'freeCommentOk.do',
				data : $('#cfrm').serialize(),
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(data) {
					
					if (data == 'success') {
						alert('댓글 성공');

						$('textarea[name=comment]').val('');
						CommentList();
					}
					else {
						alert('다시 시도해주세요.');
					}

				},
				error : function(request,status,error){
					alert("code:"
							+ request.status
							+ "\n"
							+ "message:"
							+ request.responseText
							+ "\n"
							+ "error:"
							+ error);
				}
			});
			
		});
	 	
	 	 CommentList();
	 	 
			function CommentList() {
				
				$
						.ajax({

							type : 'get',
							url : 'freeCommentList.do',
							data : $('#cfrm').serialize(),
							dataType : 'json',
							contentType: "application/x-www-form-urlencoded; charset=UTF-8",
							success : function(data) {
								
								var chtml = "";
							
							
							
								 var num="<h3>"+(data.length-1)+" 개의 댓글</h3>"; 
								 
								
								if (data.length > 0 ) {
									for (var i = 0; i < data.length-1; i++) {

										chtml+="<li>";
										chtml+="<div class='media'>";
										chtml+="<div class='"+data[i].fcnum+"' id='com'>";
										chtml+="<h5 class='name'>"+data[i].number+"</h5>";
										chtml+="<p>"+data[i].comment+"</p>";
										chtml+=	"<span class='comment-date'>"+data[i].fcdate+"</span>&nbsp;&nbsp;&nbsp;"; 
										if(data[i].uid===('<%=session.getAttribute("sid")%>')||data[data.length-1].anum>0){	
										chtml+="<button type='button' id='deletebtn' class='reply-link'  style='border:none; background:none;'>삭제</button>"
										}
										chtml+="</div>";
										chtml+="</div>";

										chtml+="</li>";

									}

								}
								$('#num').html(num);
								$('.comments-list').html(chtml);

							},
							error : function() {

							}
						});
			}
			
			$('.comments-list').on('click','#deletebtn',function(){
				$.ajax({
					type : 'get',
					url : 'freeCommentDelete.do',
					data :{ fcnum:$('#com').attr('class')},

					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					success : function(data) {
			
					if(data==='success'){
						alert('삭제되었습니다');
						CommentList();
					}
					else {
						alert('다시 시도해주세요.');
					}
											},
											error : function() {

											}
				})
			})
			
				$('.comments-list').on('click','#modifybtn',function(){
			$.ajax({
				type : 'get',
				url : 'freeCommentModify.do',
				data :$('#cfrm').serialize()+"&fcnum"+$('#com').attr('class'),
					

				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(data) {
		
				if(data==='success'){
					alert('수정되었습니다');
					CommentList();
				}
				else {
					alert('다시 시도해주세요.');
				}
										},
										error : function() {

										}
			})
		})
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
					<li><a class="active" href="freeList.do"> 커뮤니티 <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown">
							<li><a class="active" href="freeList.do"> 자유 게시판 </a></li>
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
			<li><a href="movieSortList.do">영화</a>
				<ul>
					<li><a href="movieSortList.do">장르별 영화</a></li>
				</ul></li>
			<li><a class="active" href="freeList.do">커뮤니티</a>
				<ul>
					<li><a class="active" href="freeList.do">자유 게시판</a></li>
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
				<li class="btn-m"><a href="logout_ok.do"><i class="ti-lock"></i>
						로그아웃</a></li>
			</c:if>
		</ul>
		<!-- Mobile Menu End -->
	</nav>
</div>
	</div>
	<div class="page-header"
		style="background: url(./assets/img/board/queen-of-liberty-202218_1920.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="breadcrumb-wrapper">
						<h2 class="product-title">자유게시판</h2>
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
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="">
					<table class="table table-borderless table-sm">

						<tr>
							<th class="col-md-5" style="text-align: center;">[${fTO.fctgname}]&nbsp&nbsp&nbsp<b>${fTO.fsubject}</b></th>
							<th class="col-md-3" style="text-align: right;">${fTO.fdate}</th>
							<th class="col-md-2" style="text-align: right;">${fTO.fhit}</th>
							<th class="col-md-2" style="text-align: right;">${fTO.commentNum}</th>
						</tr>
					</table>

					<div>${fTO.fcontent} </div>

				</div>
				<div class="col-md-12" style="padding-top: 30px">
					<a href="freeList.do?cpage=${param.cpage}" class="btn btn-common pull-left" id="list">목록</a>
					<c:if test="${!empty sid && sid eq fTO.uid }">
					<a href="freeModify.do?fnum=${param.fnum}&cpage=${param.cpage}" class="btn btn-common pull-right">수정</a> 
						</c:if>
						 <c:if test="${(!empty sid && sid eq fTO.uid) || fTO.anum > 0}">
					<button id="btn-left1" class="btn btn-common pull-right">삭제</button>
						</c:if>
				</div>
			</div>

		</div>
		<br>

		<div class="container">
			<div id="comments" class="col-md-10 col-md-offset-1">
					<div id="num">
				
				</div>
				<ol class="comments-list">
				
					
				</ol>

				<div id="respond">
					<h2 class="respond-title">댓글달기</h2>
					<form id="cfrm" method="post" name="cfrm">
					<input type="hidden" name="fnum" value="${fTO.fnum}"> 
					<input  type="hidden" name="cpage" value="${param.cpage}">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<textarea id="comment" class="form-control" name="comment"
										cols="45" rows="8" placeholder="댓글을 달아주세요."></textarea>
								</div>
								<c:if test="${!empty sid}">
								<button type="button" id="csubmit" class="btn btn-common">댓글달기</button>
								</c:if>
							</div>
						</div>
					</form>
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
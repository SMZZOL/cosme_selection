<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<div class="navbar">
	<!-- <a href="/" style="background-image: ''/images/logo.jpg'"> </a> -->
	<a href="/" style="all: unset;"><IMG src='/images/logo.jpg'
		style="width: 50px"></a>
	<div class="dropdown">
		<button class="dropbtn">
			드롭다운 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="">컨텐츠1 </a>
			<a href="">컨텐츠2 </a>
			<a href="">컨텐츠3 </a>
		</div>
	</div>
	<div class="dropdown">
		<button class="dropbtn">
			게시판 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="#">공지사항</a>
			<a href="#">질문 게시판</a>
			<a href="#">자유 게시판</a>
		</div>
	</div> 
	<!-- class 에서 right 주면 오른쪽 정렬 안주면 기본 left -->
	<a href="/login" class="right btn btn-primary">로그인</a> <a
		href="/register" class="right btn btn-primary">회원 가입</a>
</div>
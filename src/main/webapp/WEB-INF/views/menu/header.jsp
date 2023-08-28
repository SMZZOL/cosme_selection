<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="f_title" value="${f_boardVO.f_boardtitle }" />
<c:set var="memberno" value="${sessionScope.memberno }" />
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<div class="navbar">
	<a href="/" style="all: unset;"><IMG src='/images/logo2.gif'
		style="width: 50px"></a> <a href="/" class="right btn btn-primary"
		style="float: left;">News</a>

	<div class="dropdown">
		<button class="dropbtn">
			게시판 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="/notice/list_all.do">공지사항</a> <a href="/fboard/list_all.do">자유
				게시판</a>
		</div>
	</div>
	<!--  <a class="dropdown-item" href=''>성분검색</a>
	-->
	<div class="dropdown">
		<button class="dropbtn"> 타입별 추천 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="/cosme/cosme_by_cate.do">종류별 </a> 
			<a href="/cosme/list_by_type.do">타입별(효과별)</a> 
			<a href="/cosme/list_by_ingred.do">성분별 </a>
			<c:if test="${sessionScope.id != null}">
				<a
					href="/cosme/list_by_commend.do?memberno=${sessionScope.memberno}">나만의추천
				</a>
			</c:if>

		</div>
	</div>

    <c:choose>
    <c:when test="${sessionScope.id == null && sessionScope.admin_id == null}">
                <a href="/member/create.do" class="right btn btn-primary">회원 가입</a> 
            <a class="menu-link" href="/member/login.do" style="float: right;">로그인</a>
                <a href="/admin/login.do" class="menu_link" style="float: right;">M</a><span class='top_menu_sep'> </span>
    </c:when>
    <c:otherwise>
    
    
    <c:if test="${sessionScope.admin_id != null}">
        <div class="dropdown">
            <button class="dropbtn">
                등록<i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <A class="dropdown-item" href='/cosme_cate/create.do'>화장품 종류 등록</A>
                <a href="/cosme/create.do">화장품 등록 </a>
                <!--  관리자 로그인시에만 보이는 메뉴 -->
                <a href="/ingred/create.do">성분 등록 </a>
                <!--  관리자 로그인시에만 보이는 메뉴 -->
                <a href="/cosmetype/create.do">화장품 타입 등록 </a>
                <!--  관리자 로그인시에만 보이는 메뉴 -->
            </div>
        </div>

    <div class="dropdown">
        <button class="dropbtn">
            목록<i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
            <A class="dropdown-item" href='/cosme_cate/list_all.do'>화장품 종류 목록</A>
            <a class="dropdown-item" href="/cosmetype/list_all.do">화장품 타입 목록</a>
        </div>
    </div>

        <a href="/admin/logout.do" class="menu_link" style="float: right;">M 로그아웃
    </a><span class='top_menu_sep'> </span>
    <div class="dropdown" style="float: right;">
        <button class="dropbtn" >
            관리 목록<i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
            <A class="dropdown-item" href='/admin/list.do'>관리자 목록</A> 
            <a class="dropdown-item" href="/member/list.do">일반 회원 목록</a>
              <A class='dropdown-item'  href='/reply/list_join.do'>댓글 목록</A>
        </div>
    </div>
    </c:if>
    
    <c:if test="${sessionScope.id != null}">
            <a class="menu-link" href='/member/logout.do' style="float: right;">${sessionScope.id }님 로그아웃</a>
          <div class="dropdown" style="float: right;"> <button class="dropbtn"> 회원 관리 <i class="fa fa-caret-down"></i></button>
        <div class="dropdown-content">
            <a href="/member/passwd_update.do?memberno=${memberno}">비밀번호 변경 </a> 
            <a href="/member/read.do">회원 정보 수정</a>
            </div>
            </div>
    </c:if>
    
    
    </c:otherwise>
    </c:choose>

	<!-- class 에서 right 주면 오른쪽 정렬 안주면 기본 left -->


</div>
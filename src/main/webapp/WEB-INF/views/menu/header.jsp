<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<div class="navbar">
	<a href="/" style="all: unset;"><IMG src='/images/logo2.gif' style="width: 50px"></a>
  <a href="/" class="right btn btn-primary" style="float:left;">Home</a> 
  
	<div class="dropdown">
		<button class="dropbtn">
			게시판 
      <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="/notice/list_all.do">공지사항</a>
			<a href="#">질문 게시판</a>
			<a href="#">자유 게시판</a>
		</div>
	</div> 
  
      <div class="dropdown">
    <button class="dropbtn">성분 검색 <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="/cosme/list_by_type.do">컨텐츠1 </a>
      <a href="">컨텐츠2 </a>
      <a href="">컨텐츠3 </a>
    </div>
  </div>
  
    <div class="dropdown">
    <button class="dropbtn">타입별 추천 <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="/cosmetype/list_all.do">컨텐츠1 </a>
      <a href="">컨텐츠2 </a>
      <a href="">컨텐츠3 </a>
    </div>
  </div>
  
              
  
<!-- 관리자 로그인 -->

     <%
      String master_id = (String)session.getAttribute("master_id");

      if (master_id == null) { // 로그인 안된 경우
      %>
      <a class="dropdown-item" href='/cosme_cate/list_all.do'>화장품 종류 목록</a>      
        <a href="/member/create.do" class="right btn btn-primary">회원 가입</a> 
       
                 
<!-- <div class="dropdown" style="float:right;">  
    <button class="dropbtn">  
    로그인
    </button>
  <form class="dropdown-content">
    <div class="mb-3">
      <label for="exampleDropdownFormEmail2" class="form-label">이메일</label>
      <input type="email" class="form-control" id="exampleDropdownFormEmail2" placeholder="email@example.com">
    </div>
    <div class="mb-3">
      <label for="exampleDropdownFormPassword2" class="form-label">비밀번호</label>
      <input type="password" class="form-control" id="exampleDropdownFormPassword2" placeholder="Password">
    </div>
    <div class="mb-3">
      <div class="form-check">
        <input type="checkbox" class="form-check-input" id="dropdownCheck2">
        <label class="form-check-label" for="dropdownCheck2">
          Remember me
        </label>
      </div>
    </div>
    <div class= "form_input">
    <button type="submit" class="right btn btn-primary">Sign in</button></div>
    <A href='master/msg'></A>
  </form>
</div>    --> 

<a href="/master/login.do" class="menu_link" style="float:right;">M</a><span class='top_menu_sep'> </span>

             <!-- 회원 로그인/로그아웃 -->
          <c:choose>
             <c:when test="${sessionScope.id == null}">
                      <a class="menu-link" href="/member/login.do" style="float: right;">로그인</a>
              </c:when>
                <c:otherwise>
                   <a class="menu-link" href='/member/logout.do' style="float: right;">${sessionScope.id } 로그아웃</a>
                </c:otherwise>
              </c:choose>
              
      <%  
      } else { // 로그인 한 경우
      %>
      <a class="dropdown-item" href='/cosme_cate/list_all.do'>화장품 종류 목록</a>      
          <div class="dropdown">
    <button class="dropbtn">등록<i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
        <A class="dropdown-item"  href='/cosme_cate/create.do'>화장품  종류 등록</A>
        <a href="/cosme/create.do">화장품 등록 </a> <!--  관리자 로그인시에만 보이는 메뉴 -->
      <a href="/ingred/create.do">성분 등록 </a> <!--  관리자 로그인시에만 보이는 메뉴 -->
      <a href="/cosmetype/create.do">화장품 타입 등록 </a> <!--  관리자 로그인시에만 보이는 메뉴 -->
        <a href="/notice/create.do">공지사항 등록 </a> <!--  관리자 로그인시에만 보이는 메뉴 -->
        </div>
      </div>
 
      <a href="/master/logout.do" class="menu_link" style="float: right;">M <%=master_id %> 로그아웃</a><span class='top_menu_sep'> </span>
      <%  
      }
      %> 

      
    

	<!-- class 에서 right 주면 오른쪽 정렬 안주면 기본 left -->     


</div>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticeno" value="${noticeVO.noticeno }" />
<c:set var="ntitle" value="${noticeVO.ntitle }" />        
<c:set var="ncontent" value="${noticeVO.ncontent }" />
<c:set var="rdate" value="${noticeVO.rdate.substring(0, 16) }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>공지사항</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>

<DIV class='title_line'>
<A href="./read.do" class='title_link'>공지사항</A></DIV>

  <ASIDE class="aside_right">
  <br>
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.master_id != null }">
      <%--
      http://localhost:9093/notice/create.do?noticeno=1
      http://localhost:9093/notice/create.do?noticeno=2
      http://localhost:9093/notice/create.do?noticeno=3
      --%>
      
      <A href="./create.do">등록</A>
      <span class='menu_divide' >│</span>
     <A href="./update.do?noticeno=${noticeno}&now_page=${param.now_page}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?noticeno=${noticeno}&now_page=${param.now_page}">삭제</A>  
    <span class='menu_divide' >│</span>  
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
  
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">

          <span style="font-size: 1.5em; font-weight: bold;">${ntitle }</span><br>
          <br>
          <div style="font-size: 1em;">${mname } ${rdate }</div><br><br>
          <div style="font-size: 1.1em;">${ncontent }</div>
        </DIV>
      </li>

    </ul>
  </fieldset>
  
    <div class="content_body_bottom">  
    <button type="button" onclick="location.href='/notice/list_all.do'" class="my-btn btn">목록</button>
  </div>

</DIV>
 
<jsp:include page="../menu/footer.jsp"  />
</body>
 
</html>
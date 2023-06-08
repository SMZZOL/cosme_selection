<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticeno" value="${noticeVO.noticeno }" />
<c:set var="masterno" value="${noticeVO.masterno }" />        
<c:set var="ntitle" value="${noticeVO.ntitle }" />        
<c:set var="ncontent" value="${noticeVO.ncontent }" />
<c:set var="rdate" value="${contentsVO.rdate.substring(0, 16) }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>공지사항</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />
 
<DIV class='title_line'><A href="./read.do" class='title_link'>공지사항</A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
  
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.master_id != null }">
      <%--
      http://localhost:9093/notice/create.do?noticeno=1
      http://localhost:9093/notice/create.do?noticeno=2
      http://localhost:9093/notice/create.do?noticeno=3
      --%>
      <A href="./create.do">등록</A>
      <span class='menu_divide' >│</span>
      <A href="./update.do?noticeno=${noticeno}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?noticeno=${noticeno}">삭제</A>  
    </c:if>

    <A href="javascript:location.reload();">새로고침</A>
  
  </ASIDE> 
  
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%;">

          <span style="font-size: 1.5em; font-weight: bold;">${ntitle }</span><br>
          ${ncontent }
        </DIV>
      </li>

    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
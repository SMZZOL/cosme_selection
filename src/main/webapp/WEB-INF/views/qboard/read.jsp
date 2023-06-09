<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="qoticeno" value="${qboardVO.qboardno }" />
<c:set var="qtitle" value="${qboardVO.qtitle }" />        
<c:set var="qcontent" value="${qboardVO.qcontent }" />
<c:set var="rdate" value="${noticeVO.rdate.substring(0, 16) }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>질문게시판</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />
 
<DIV class='title_line'>
<A href="./read.do" class='title_link'>질문게시판</A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
  
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.master_id != null }">
      <%--
      http://localhost:9093/qboard/create.do?qboardno=1
      http://localhost:9093/notice/create.do?noticeno=2
      http://localhost:9093/notice/create.do?noticeno=3
      --%>
    
      <A href="./delete.do?qboardno=${qboardno}&now_page=${param.now_page}">삭제</A>  
    <span class='menu_divide' >│</span>  
    </c:if>
     <A href="./create.do">등록</A>
      <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  
  </ASIDE> 
  
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%;">

          <span style="font-size: 1.5em; font-weight: bold;">${qtitle }</span><br>
            ${qcontent }
        </DIV>
      </li>

    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
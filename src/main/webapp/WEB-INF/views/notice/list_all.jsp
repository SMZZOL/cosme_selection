<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.notice.NoticeVO" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>공지사항</title>

 <link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
</head> 
 <link href="/css/style.css" rel="Stylesheet" type="text/css">
<body>
    <c:import url="../menu/header.jsp" />
 
 <DIV class='content_body'>
 <Br>
<DIV class='title_line'>공지사항</DIV>
<br>
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
      <A href="./update.do?noticeno=${noticeno}&now_page=${param.now_page}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?noticeno=${noticeno}&now_page=${param.now_page}">삭제</A>  
    </c:if>

  </ASIDE>

    <DIV class='menu_line'></DIV>
    
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <c:choose>
        <c:when test="${sessionScope.master_id != null }">
          <col style="width: 10%;"></col>
          <col style="width: 80%;"></col>
          <col style="width: 10%;"></col>        
        </c:when>
        <c:otherwise>
          <col style="width: 10%;"></col>
          <col style="width: 90%;"></col>
        </c:otherwise>
      </c:choose>
    </colgroup>
   
<!--    <thead>  
    <TR>
      <TH class="th_bs">번호</TH>
      <TH class="th_bs">제목</TH>
      <TH class="th_bs">등록일</TH>
    </TR>
    </thead> -->
    
    <tbody>
      <c:forEach var="noticeVO" items="${list}">
        <c:set var="ntitle" value="${noticeVO.ntitle }" />
        <c:set var="ncontent" value="${noticeVO.ncontent }" />
        <c:set var="noticeno" value="${noticeVO.noticeno }" />
        <c:set var="rdate" value="${noticeVO.rdate.substring(0, 16) }" />
        
         <tr style="height: 112px;" onclick="location.href='./read.do?noticeno=${noticeno }'" class='hover'> 
          <td style='vertical-align: middle; text-align: center; '>
            <div style='font-weight: bold;'>
            <a href="./read.do?noticeno=${noticeno }'">${ntitle }</a></div>
            <c:choose> 
              <c:when test="${ncontent.length() > 160 }"> <%-- 160자 이상이면 160자만 출력 --%>
                  <a href="./read.do?noticeno=${noticeno }">${ncontent.substring(0, 160)}.....</a>
              </c:when>
              <c:when test="${ncontent.length() <= 160 }">
                  <a href="./read.do?noticeno=${noticeno }">${ncontent}</a>
              </c:when>
            </c:choose>
            <div style='font-size: 0.95em;'>${rdate }</div>
          </td> 
          
          <c:choose>
            <c:when test="${sessionScope.master_id != null }"> 
              <td style='vertical-align: middle; text-align: center;'>
                <A href="/notice/delete.do?noticeno=${noticeno}" title="삭제"><IMG src="/notice/images/delete.png" class="icon"></A>
              </td>
            </c:when>
            <c:otherwise>
            
            </c:otherwise>
          </c:choose>
          
        </tr>
        
      </c:forEach>
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
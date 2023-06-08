<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.notice.NoticeVO" %>
 
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
  공지사항
 
</DIV>


  <ASIDE class="aside_right">
  <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.master_id != null }">
      <%--
      http://localhost:9093/notice/create.do?noticeno=1
      http://localhost:9093/notice/create.do?noticeno=2
      http://localhost:9093/notice/create.do?noticeno=3
      --%>
         <br>
      <A href="./create.do">등록</A>
      <span class='menu_divide' >│</span>
      <A href="./update.do?noticeno=${noticeno}&now_page=${param.now_page}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?noticeno=${noticeno}&now_page=${param.now_page}">삭제</A>  
    <span class='menu_divide' >│</span>       
    <A href="javascript:location.reload();">새로고침</A>
    </c:if>

  </ASIDE>

  <DIV class='menu_line'></DIV>
  
  <table class="class='table table-hover" style='width: 100%;'>
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

     <thead>
      <tr>
        <th style='text-align: center;'>번호</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>등록일</th>
      </tr>
    
    </thead>
    
    <tbody>
      <%
    ArrayList<NoticeVO> list = (ArrayList<NoticeVO>)request.getAttribute("list");
    
    for (int i=0; i < list.size(); i++) {
    	NoticeVO noticeVO = list.get(i);
    %>
      <TR>
        <TD class='td_bs'><%= noticeVO.getNoticeno() %></TD>
        <TD class='td_bs'><%= noticeVO.getNtitle() %></TD>
        <TD class='td_bs'><%=noticeVO.getRdate().substring(0, 10) %></TD>
        <TD>
         
        </TD>
      </TR>
    <%  
    }
    %>

    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>


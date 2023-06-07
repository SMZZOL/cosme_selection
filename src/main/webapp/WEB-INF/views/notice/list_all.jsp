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
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />
 
 <DIV class='content_body'>
<DIV class='title_line'>공지사항</DIV>

<DIV class='content_body'>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 45%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 20%;'/>
      <col style='width: 15%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">번호</TH>
      <TH class="th_bs">제목</TH>
    </TR>
    </thead>
    
    <tbody>
    <%
    ArrayList<NoticeVO> list = (ArrayList<NoticeVO>)request.getAttribute("list");
    
    for (int i=0; i < list.size(); i++) {
    	NoticeVO noticeVO = list.get(i);
    %>
      <TR>
        <TD class='td_bs'><%= noticeVO.getNoticeno() %></TD>
        <TD><%=noticeVO.getNtitle() %></TD>
        <TD>
      </TR>
    <%  
    }
    %>
    </tbody>
   
  </TABLE>
</DIV>
</DIV>
 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
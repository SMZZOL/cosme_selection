<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.qboard.QboardVO" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>질문게시판</title>

 <link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
</head> 
 <link href="/css/style.css" rel="Stylesheet" type="text/css">
<body>
    <c:import url="../menu/header.jsp" />
 

 <DIV class='content_body'>
 <Br>
<DIV class='title_line'>질문게시판</DIV>
<br>
  <ASIDE class="aside_right">



  
    <%-- 관리자로그인 해야 보이는 것들 --%>
      <c:if test="${sessionScope.master_id != null }">
      <A href="./delete.do?qboardno=${qboardno}&now_page=${param.now_page}">삭제</A>  
    </c:if>
     <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>

  </ASIDE>

    <DIV class='menu_line'></DIV>
    
   <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 50%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">질문 번호</TH>
      <TH class="th_bs">질문 제목</TH>
      <TH class="th_bs">질문 내용</TH>
      <TH class="th_bs">등록일</TH>
    </TR>
    </thead>
    
    <tbody>
    <%
    ArrayList<QboardVO> list = (ArrayList<QboardVO>)request.getAttribute("list");
    
    for (int i=0; i < list.size(); i++) {
      QboardVO qboardVO = list.get(i);
    %>
      <TR>
        <TD class='td_bs'><%= qboardVO.getQboardno() %></TD>
        <TD class='td_bs'><%= qboardVO.getQtitle() %></TD>
        <TD class='td_bs'><%= qboardVO.getQcontent() %></TD>
        <TD class='td_bs'><%=qboardVO.getRdate().substring(0, 10) %></TD>
        <TD>
         
        </TD>
      </TR>
    <%  
    }
    %>
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>댓글 리스트</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
 
</head>
 
<body>
<c:import url="../menu/header.jsp" />
  <DIV class="title_line">
    등록된 모든 댓글
  </DIV>
  <ASIDE class='aside_right'>
    <A href='./list.do'>모든 댓글</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE>
   
  <div class='menu_line'></div>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 50%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>댓글 번호</th>
          <th style='text-align: center;'>글 번호</th>
          <th style='text-align: center;'>회원 번호</th>
          <th style='text-align: center;'>내용</th>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="replyVO" items="${list }">
          <c:set var="fboardno" value="${replyVO.fboardno }" />
          <c:set var="replyno" value="${replyVO.replyno }" />
          
          <tr style='height: 50px;'> 
            <td style='text-align: center; vertical-align: middle;'>
              ${replyVO.replyno }
            </td> 
            <td style='text-align: center; vertical-align: middle;'>
              <A href='../fboard/read.do?fboardno=${fboardno }'>${fboardno}</A>
            </td>
            <td style='text-align: center; vertical-align: middle;'>
              <A href='../member/read.do?memberno=${replyVO.memberno }'>${replyVO.memberno}</A>
            </td>
            <td style='text-align: left; vertical-align: middle;'>${replyVO.content}</td>
            <td style='text-align: center; vertical-align: middle;'>
              ${replyVO.rdate.substring(0, 10)}
            </td>
            <td style='text-align: center; vertical-align: middle;'>
              <a href="./delete.do?replyno=${replyVO.replyno}"><img src="/reply/images/delete.png" title="삭제"  border='0' /></a>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>
<jsp:include page="../menu/footer.jsp" flush='false' />
 
</body>
 
</html>
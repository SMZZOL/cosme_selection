<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>회원 목록</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>
<DIV class='title_line'>
  회원 목록
 
</DIV>
<br>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE>

  <DIV class='menu_line'></DIV>
  
  <table class="class='table table-hover" style='width: 100%;'>
          <col style="width: 20%;"></col>
          <col style="width: 40%;"></col>
          <col style="width: 20%;"></col>
          <col style="width: 20%;"></col>       

      <tr>
        <th style='text-align: center;'>구분</th>
        <th style='text-align: left;'>아이디</th>
        <th style='text-align: left;'>이름</th>
        <th style='text-align: center;'>등록일</th>
      </tr>

<tbody>
  <c:forEach var="memberVO" items="${list}">
  <c:set var="memberno" value="${memberVO.memberno }" />
    <c:set var="id" value="${memberVO.id }" /> 
  <c:set var="mname" value="${memberVO.mname }" />        
  <c:set var="mdate" value="${memberVO.mdate.substring(0, 10) }" />
  
        <tr style="height: 112px;">
          <td style='vertical-align: middle; text-align: center; '>
            <IMG src="/member/images/show.png" style="width: 15px; height: 15px;">          
          </td>  
          
             <td style='vertical-align: middle; '>
            <div style='font-weight: bold;'>${id }</div>

          </td>
          
          <td style='vertical-align: middle; '>
            <div style='font-weight: bold;'>${mname }</div>

          </td>
          
          
            <td style='vertical-align: middle; text-align: center;'>
            <div style='font-weight: bold;'>${mdate }</div>
          </td>
          

              <td style='vertical-align: middle; text-align: center;'>
              

              </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>


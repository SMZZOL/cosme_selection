<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.qboard.QboardVO" %>
 
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

<DIV class='content_body'>
<DIV class='title_line'>질문게시판</DIV>


  <ASIDE class="aside_right">
  <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.master_id != null }">
    </c:if>
    <br>
     <A href="./create.do">등록</A>
     <span class='menu_divide' >│</span>     
    <A href="javascript:location.reload();">새로고침</A>

  </ASIDE>

  <DIV class='menu_line'></DIV>
  
  <table class="class='table table-hover" style='width: 100%;'>
          <col style="width: 20%;"></col>
          <col style="width: 40%;"></col>
          <col style="width: 20%;"></col>        


      <tr>
        <th style='text-align: center;'>순서</th>
        <th style='text-align: left;'>제목</th>
        <th style='text-align: center;'>작성일</th>
      </tr>
    

    
<tbody>
  <c:forEach var="qboardVO" items="${list}">
  <c:set var="qboardno" value="${qboardVO.qboardno }" />
  <c:set var="qtitle" value="${qboardVO.qtitle }" />        
  <c:set var="rdate" value="${qboardVO.rdate.substring(0, 10) }" />
  
   <tr style="height: 112px;" onclick="location.href='./read.do?qboardno=${qboardno }&now_page=${param.now_page == null ? 1 : param.now_page}'" class='hover'>
          <td style='vertical-align: middle; text-align: center; '>
            <IMG src="/qboard/images/show.png" style="width: 15px; height: 15px;">          
          </td>  
          
          <td style='vertical-align: middle;'>
            <div style='font-weight: bold;'>${qtitle }</div>
          </td>
          
          <td style='vertical-align: middle; text-align: center;'>
            <div style='font-weight: bold;'>${rdate }</div>
          </td>
          
          <c:choose>
            <c:when test="${sessionScope.master_id != null }"> 
              <td style='vertical-align: middle; text-align: center;'>
               <A href="/qboard/delete.do?qboardno=${qboardno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="삭제"><IMG src="/qboard/images/delete.png" class="icon"></A>
              </td>
            </c:when>
            <c:otherwise>
            
            </c:otherwise>
          </c:choose>
                    
        </tr>
        
      </c:forEach>
    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>


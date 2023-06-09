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
    <A href="javascript:location.reload();">새로고침</A>
    </c:if>

  </ASIDE>

  <DIV class='menu_line'></DIV>
  
  <table class="class='table table-hover" style='width: 100%;'>
          <col style="width: 20%;"></col>
          <col style="width: 40%;"></col>
          <col style="width: 20%;"></col>        


      <tr>
        <th style='text-align: center;'>번호</th>
        <th style='text-align: left;'>제목</th>
        <th style='text-align: center;'>등록일</th>
        <th style='text-align: center;'>수정/삭제</th>
      </tr>

<tbody>
  <c:forEach var="noticeVO" items="${list}">
  <c:set var="noticeno" value="${noticeVO.noticeno }" />
  <c:set var="ntitle" value="${noticeVO.ntitle }" />        
  <c:set var="ncontent" value="${noticeVO.ncontent }" />
    <c:set var="rdate" value="${noticeVO.rdate.substring(0, 10) }" />
  
   <tr style="height: 112px;" onclick="location.href='./read.do?noticeno=${noticeno }&now_page=${param.now_page == null ? 1 : param.now_page}'" class='hover'>
          <td style='vertical-align: middle; text-align: center; '>
            <IMG src="/notice/images/check.png" style="width: 15px; height: 15px;">          
          </td>  
          
          <td style='vertical-align: middle; '>
            <div style='font-weight: bold;'><a href="./read.do?noticeno=${noticeno }&now_page=${param.now_page == null ? 1 : param.now_page }">${ntitle }</a></div>

          </td>
          
            <td style='vertical-align: middle; text-align: center;'>
            <div style='font-weight: bold;'>${rdate }</div>
          </td>
          
          <c:choose>
            <c:when test="${sessionScope.master_id != null }"> 
              <td style='vertical-align: middle; text-align: center;'>
                <A href="/notice/update.do?noticeno=${noticeno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="수정"><IMG src="/notice/images/update.png" class="icon"></A>
                <A href="/notice/delete.do?noticeno=${noticeno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="삭제"><IMG src="/notice/images/delete.png" class="icon"></A>
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


<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.fboard.FboardVO" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>자유게시판</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>
<DIV class='title_line'>
  자유 게시판
 
</DIV>
<br>
  <ASIDE class="aside_right">
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
        <th style='text-align: center;'></th>
        <th style='text-align: left;'>제목</th>
        <th style='text-align: center;'>등록일</th>
      </tr>

<tbody>
  <c:forEach var="fboardVO" items="${list}">
  <c:set var="fboardno" value="${fboardVO.fboardno }" />
  <c:set var="ftitle" value="${fboardVO.ftitle }" />        
  <c:set var="fcontent" value="${fboardVO.fcontent }" />
    <c:set var="rdate" value="${fboardVO.rdate.substring(0, 10) }" />
  
   <tr style="height: 112px;" onclick="location.href='./read.do?fboardno=${fboardno }&now_page=${param.now_page == null ? 1 : param.now_page}'" class='hover'>
          <td style='vertical-align: middle; text-align: center; '>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- registry.addResourceHandler("/fboard/storage/**").addResourceLocations("file:///" +  Fboard.getUploadDir()); --%>
                <img src="/fboard/storage/${thumb1 }" style="width: 120px; height: 90px;">
              </c:when>
              <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/fboard/images/none1.png -->
                <IMG src="/fboard/images/logo2.gif" style="width: 120px; height: 90px;">
              </c:otherwise>
            </c:choose>
          </td>  
          
          <td style='vertical-align: middle; '>
            <div style='font-weight: bold;'><a href="./read.do?fboardno=${fboardno }&now_page=${param.now_page == null ? 1 : param.now_page }">${ftitle }</a></div>
              <c:choose> 
              <c:when test="${fcontent.length() > 160 }"> <%-- 160자 이상이면 160자만 출력 --%>
                  <a href="./read.do?fboardno=${fboardno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }">${fcontent.substring(0, 160)}.....</a>
              </c:when>
              <c:when test="${fcontent.length() <= 160 }">
                  <a href="./read.do?fboardno=${fboardno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }">${fcontent}</a>
              </c:when>
            </c:choose>
          </td>
          
            <td style='vertical-align: middle; text-align: center;'>
            <div style='font-weight: bold;'>${rdate }</div>
          </td>
          
          <c:choose>
            <c:when test="${sessionScope.master_id != null }"> 
              <td style='vertical-align: middle; text-align: center;'>
              
                <A href="/delete/update.do?fboardno=${fboardno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="삭제"><IMG src="/fboard/images/delete.png" class="icon"></A>
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

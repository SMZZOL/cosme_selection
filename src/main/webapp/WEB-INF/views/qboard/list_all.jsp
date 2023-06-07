<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>질문게시판</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  질문 게시판 전체 글 목록
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>

    <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead>
    
    <tbody>
      <c:forEach var="qboardVO" items="${list}">
        <c:set var="qtitle" value="${qboardVO.qtitle }" />
        <c:set var="qboardno" value="${qboardVO.qboardno }" />
        
        <tr style="height: 112px;">
          <td style='vertical-align: middle; text-align: center;'>
          </td>  
          <td style='vertical-align: middle;'>
            <c:choose>
              <c:when test="${qboard.length() > 160 }"> <%-- 160자 이상이면 160자만 출력 --%>
                  ${qboard.substring(0, 160)}.....
              </c:when>
              <c:when test="${qboard.length() <= 160 }">
                  ${qboard}
              </c:when>
            </c:choose>
            
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
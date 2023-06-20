<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>관리자 목록</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>
<DIV class='title_line'>
  관리자 목록
 
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
        <th class='th_bs' style='text-align: center;'>구분</th>
        <th class='th_bs' style='text-align: left;'>아이디</th>
        <th class='th_bs' style='text-align: left;'>이름</th>
        <th class='th_bs' style='text-align: center;'>등록일</th>
      </tr>

<tbody>
  <c:forEach var="masterVO" items="${list}">
  <c:set var="masterno" value="${masterVO.masterno }" />
    <c:set var="id" value="${masterVO.id }" /> 
  <c:set var="mname" value="${masterVO.mname }" />        
  <c:set var="mdate" value="${masterVO.mdate.substring(0, 10) }" />
  
        <tr style="height: 112px;">
          <td style='vertical-align: middle; text-align: center; '>
            <IMG src="/master/images/check.png" style="width: 15px; height: 15px;">          
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
                <A href="/master/passwd_update.do?masterno=${masterno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="수정"><IMG src="/matser/images/update.png" class="icon"></A>
              </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>


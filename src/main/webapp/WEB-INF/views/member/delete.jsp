<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>회원삭제</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

 
</head> 
<body>
<c:import url="../menu/header.jsp" />
 


  <DIV class='content_body'>
    <DIV class='title_line'>
    회원 삭제(관리자 전용)
  </DIV>
    <ASIDE class="aside_right">
    <br>
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./create.do'>회원 가입</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
   
   
    <DIV class='message'>
      <FORM name='frm' method='POST' action='./delete.do'>
      <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">'${memberVO.mname }(${memberVO.id })' 회원을 삭제하면 복구 할 수 없습니다.<br><br>
        정말로 삭제하시겠습니까?</span><br><br>
                <br><br> 
            
        <button type="submit" class="my-btn btn">삭제</button>
        <button type="button" onclick="location.href='./list.do'" class="my-btn btn">취소(목록)</button>
     </div>
      </FORM>
    </DIV>
  </DIV> <%--  <DIV class='content_body'> END --%>

<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
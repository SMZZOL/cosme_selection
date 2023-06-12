<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="qboardno" value="${qboardVO.qboardno }" />
<c:set var="qtitle" value="${qboardVO.qtitle }" />
           
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
<DIV class='title_line'> ${qtitle } 삭제</DIV>
<br>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>

  </ASIDE> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
      
        <DIV style='text-align: center; width: 100%; float: center;'>
          <span style='font-size: 1.5em;'>${qtitle}</span>
          <br>
          <FORM name='frm' method='POST' action='./delete.do'>
              <input type='hidden' name='qboardno' value='${qboardno}'>
              <br><br>
              <div style='text-align: center; margin: 10px auto;'>
                <span style="color: #FF0000; font-weight: bold;">삭제를 진행 하시겠습니까? 삭제하시면 복구 할 수 없습니다.</span><br><br>
                <br><br>
                <button type = "submit" class="my-btn btn">삭제 진행</button>
                <button type = "button" onclick = "history.back()" class="my-btn btn">취소</button>
              </div>   
          </FORM>
        </DIV>
      </li>
     </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
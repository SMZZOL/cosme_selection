<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=5.0, width=device-width" /> 
<title>패스워드 확인</title>
<style type="text/css">
  *{ font-family: Malgun Gothic; font-size: 26px;}
</style>
</head>
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>
<DIV class='title_line'>
  패스워드 확인
 
</DIV>
<br>
  <ASIDE class="aside_right">  
    <A href="javascript:location.reload();">새로고침</A>
    
  </ASIDE>
  
        <DIV class="aside_right" style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_all.do'>
      
    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
      <label>패스워드 확인: 
        <input type='password' class="form-control form-control-sm" name='passwd2' id='passwd2' value='패스워드' required="required" placeholder="패스워드 확인">
      </label>
    </div>  
      <button type='submit' class='btn btn-info btn-sm'>확인</button>
        <button type='button' class='btn btn-info btn-sm' 
                    onclick="location.href='./passwd_update?matserno=${matserVO.matserno}'">확인</button>  
    </form>
  </DIV>

</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>


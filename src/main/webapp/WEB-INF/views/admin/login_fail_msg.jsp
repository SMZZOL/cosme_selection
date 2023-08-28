<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


</head> 
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='title_line'>알림</DIV>
  <DIV class='message'>
    <fieldset class='fieldset_basic'>
      <ul>
        <li class='li_none'>관리자 로그인에 실패했습니다.</li>
        <li class='li_none'>ID 또는 패스워드가 일치하지 않습니다.</li>
        <li class='li_none'>
          <button type="button" id="btn_retry" class="btn btn-info" onclick="location.href='./login.do'">로그인 다시 시도</button>
          <button type="button" id="btn_home" class="btn btn-info" onclick="location.href='/index.do'">확인</button>
        </li>
        
      </ul>
    </fieldset>    
  </DIV>
 
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>

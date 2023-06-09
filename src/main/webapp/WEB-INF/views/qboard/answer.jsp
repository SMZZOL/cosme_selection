<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>답변 게시판</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
    
</head>
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>

<DIV class='title_line'>답변 게시판 등록</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
   
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>
  
  <FORM name='frm' method='POST' action='/qboard/answer.do'>
    <br>
    
    <div>
       <label>제목</label>
       <input type='text' name='qtitle' value='' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='qcontent' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
    </div>
    <div>

    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="my-btn btn">등록</button>
      <button type="button" onclick="location.href='/qboard/list_all.do'" class="my-btn btn">목록</button>
    </div>
  
  </FORM>
</DIV>
 </DIV>
    <jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
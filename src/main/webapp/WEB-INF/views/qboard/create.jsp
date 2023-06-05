<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>질문 게시판</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
    
</head>
 
<body>
    <c:import url="../menu/header.jsp" />
 


<DIV class='content_body'>
  <DIV class='title_line'>질문게시판</DIV>
  
  <FORM name='frm' method='POST' action='/qboard/create.do'>
    <br><Br>
    <div>
       <label class="">이름</label>
       <input type='text' name='qboardname'  placeholder='입력하세요' required="required" 
                class="form-control" style='width: 100%;'>
    </div>
    <br><Br>
    <div class="content_body_bottom">
      <button type="submit" class="my-btn btn">등록</button>
      <button type="submit" onclick="location.href='./list_all.do'" class="my-btn btn">목록</button>
    </div>
  
  </FORM>
</DIV>
    <jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
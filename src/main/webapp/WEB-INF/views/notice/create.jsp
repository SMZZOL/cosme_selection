<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>공지사항 등록</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

    
</head>
 
<body>
<c:import url="../menu/header.jsp" />


<DIV class='content_body'>
<DIV class='title_line'>공지사항 등록</DIV>
  <ASIDE class="aside_right">
  <A href="./list_all.do?now_page=${param.now_page == null ? 1 : param.now_page}">목록</A>    
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>
  
  <FORM name='frm' method='POST' action='/notice/create.do' enctype="multipart/form-data">
    
    <div>
       <label>제목</label>
       <input type='text' name='ntitle' value='공지사항' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 50%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='ncontent' required="required" class="form-control" rows="12" style='width: 100%;'>필독</textarea>
    </div>
       <div>
       <label>youtube</label>
       <input type='text' name='youtube' value='' placeholder="소스 코드 입력"
       class="form-control" style='width: 100%;'>
    </div> 
    <div>
     <label>이미지</label>
     <input type='file' class="form-control" name='file1MF' id='file1MF' 
               value='' placeholder="파일 선택" style='width: 30%;'>
    </div>  
    <div>
       <label>패스워드</label>
       <input type='password' name='passwd' value='1234' required="required" 
                 class="form-control" style='width: 30%;'>
    </div>    
    <div class="content_body_bottom">
      <button type="submit" class="normal_button">등록</button>
      <button type="button" onclick="location.href='/notice/list_all.do'" class="normal_button">목록</button>
    </div>
  
  </FORM>
      <DIV style="text-align: center;">
      <H5>[참고] Youtube의 등록 방법</H5>
      <IMG src='/notice/images/youtube1.png' style='width: 60%;'><br><br>
  </DIV>
</DIV>
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
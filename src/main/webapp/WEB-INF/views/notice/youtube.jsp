<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="noticeno" value="${noticeVO.noticeno }" />
<c:set var="youtube" value="${noticeVO.youtube }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>youtube 등록</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">


<script type="text/javascript">  

</script>

</head> 
 
<body>
<c:import url="../menu/header.jsp" />
 
 <DIV class='title_line'> Youtube 등록/수정/삭제</DIV>
 
<DIV class='content_body'>


  <ASIDE class="aside_right">
    <A href="./list_all.do?now_page=${param.now_page}">목록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE>
  
  <DIV class='menu_line'></DIV>
  <%--등록 폼 --%>
  <FORM name='frm_youtube' method='POST' action='./youtube.do'>
    <input type="hidden" name="noticeno" value="${param.noticeno}">
    
    <div>
       <label>Youtube 스크립트</label>
       <textarea name='youtube' class="form-control" rows="12" style='width: 100%;'>${noticeVO.youtube}</textarea>
    </div>
    
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-info btn-sm">저장</button>
      <button type="button" onclick="frm_youtube.youtube.value=''; frm_youtube.submit();" class="btn btn-info btn-sm">Youtube 삭제</button>
      <button type="button" onclick="history.back();" class="btn btn-info btn-sm">취소</button>
    </div>
  
  </FORM>

  <DIV style="text-align: center;">
      <H5>[참고] Youtube의 등록 방법</H5>
      <IMG src='/notice/images/youtube1.png' style='width: 60%;'><br><br>
      <IMG src='/notice/images/youtube2.png' style='width: 60%;'><br>
  </DIV>
  
</DIV>
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>

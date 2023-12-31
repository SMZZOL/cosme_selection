<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="fboardno" value="${fboardVO.fboardno }" />
<c:set var="ftitle" value="${fboardVO.ftitle }" />        
<c:set var="fcontent" value="${fboardVO.fcontent }" />
<c:set var="file1" value="${fboardVO.file1 }" />
<c:set var="file1saved" value="${fboardVO.file1saved }" />
<c:set var="thumb1" value="${fboardVO.thumb1 }" />
<c:set var="youtube" value="${fboardVO.youtube }" />
<c:set var="word" value="${fboardVO.word }" />
<c:set var="views" value="${fboardVO.views }" />
<c:set var="size1_label" value="${fboardVO.size1_label }" />
<c:set var="rdate" value="${fboardVO.rdate.substring(0, 16) }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>자유게시판</title>
<link rel="shortcut icon" href="/images/star.png" />
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
<%--$(document).ready(function() {
    // 조회수 증가 함수
    function increaseViews() {
        $.ajax({
            type: 'GET',
            url: '/fboard/read.do',
            data: {
                fboardno: '${fboardno}'
            },
            success: function(response) {
                // 조회수 증가 성공한 경우
                if (response === 'success') {
                    var viewsCount = parseInt('${views}') + 1;
                    // 조회수 업데이트
                    $('#viewsCount').text(viewsCount);
                }
            }
        });
    }

    // 페이지 로드 시 조회수 증가 함수 호출
    increaseViews();
}); --%>

response.setHeader("Set-Cookie", "cookieName=; Max-Age=0");


</script> 
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />
<DIV class='title_line'>
자유게시판
</DIV>

<DIV class='content_body'>

  <ASIDE class="aside_right">
     <A href="./update.do?fboardno=${fboardno}&now_page=${param.now_page}">수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?fboardno=${fboardno}&now_page=${param.now_page}">파일</A>  
      <span class='menu_divide' >│</span>
      <A href="./delete.do?fboardno=${fboardno}&now_page=${param.now_page}">삭제</A>  
    <span class='menu_divide' >│</span>  

    <A href="javascript:location.reload();">새로고침</A>
  
  </ASIDE> 
  
  <DIV style="  margin-top: 30px; text-align: right; clear: both;">   
    <form name='frm' id='frm' method='get' action='./list_all.do'>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='normal_button'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='normal_button' 
                    onclick="location.href='./list_all.do?word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul style='background-color: #F6F6F6;'>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all; text-align: center;" >  
          <span style="font-size: 1.5em; font-weight: bold; text-align: center;">${ftitle }</span><br><br>
          <div style="font-size: 1em; text-align: right; width: 89%;"><img src="/member/images/user.png" style="height: 16px"> ${id.substring(0, 5)}  ${rdate }</div>
          <div style="font-size: 1em; text-align: right; width: 89%;">조회수: <span id="views">${views}</span></div>
          <br><br>
        </DIV>

      <DIV style="width: 100%; word-break: break-all; text-align: center;">  
                <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <%-- /static/fboard/storage/ --%>
              <img src="/fboard/storage/${file1saved }" style='width: 40%; float: center; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <%-- <img src="/fboard/images/logo2.gif" style='width: 30%; float: center; margin-top: 0.5%; margin-right: 1%;'> --%>
            </c:otherwise>
          </c:choose>
          
          <br><br>
          <div style="width: 80%; font-size: 1.1em; text-align: left; margin-left: 10%;">${fcontent }</div>
          </DIV>
           <br>
            <br>
           </li>

      <c:if test="${youtube.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style="text-align: center;">
            ${youtube }
          </DIV>
        </li>
      </c:if>
      
      <li class="li_none">
        <DIV style="width: 80%; text-align: left; margin-left: 10%;" >
          <c:if test="${file1.trim().length() > 0 }"> <%-- ServletRegister.java: registrationBean.addUrlMappings("/download"); --%>
            첨부 파일: <a href='/download?dir=/fboard/storage&filename=${file1saved}&downname=${file1}' > ${file1}</a> (${size1_label})  
          </c:if>
        </DIV>
        <br>
      </li> 
      
      

    </ul>
    <div style="width: 85%; text-align: right; margin-left: 15%;">  
    <button type="button" onclick="location.href='/fboard/list_all.do'" class="btn btn-info btn-sm">목록형</button>
    <button type="button" onclick="location.href='/fboard/list_grid.do'" class="btn btn-info btn-sm">앨범형</button>
     </div>
  </fieldset>


</DIV>
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
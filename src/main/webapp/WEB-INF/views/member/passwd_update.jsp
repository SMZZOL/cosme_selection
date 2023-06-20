<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
  $(function() { // 자동 실행, 동적 이벤트 설정: 여러 상황에따라 버튼이 실행하는 함수 변경 가능한 패턴
    $('#btn_send').on('click', send); // id가 'btn_send'인 태그가 클릭되면 'send' 함수 실행 설정 

    // id가 'btn_close'인 태그가 클릭되면 setFocus 함수를 실행, Dialog창을 닫은후의 focus 이동
    $('#btn_close').on('click', setFocus); 
  });

  function send() {
    if ($('#new_passwd').val() != $('#new_passwd2').val()) { // 새로 입력되는 2개의 패스워드 비교
      $('#modal_title').html('패스워드 일치 여부 확인'); // 제목 

      $('#modal_content').attr('class', 'alert alert-danger'); // CSS 변경  
        msg = '· 입력된 패스워드가 일치하지 않습니다.<br>';
      msg += "· 패스워드를 다시 입력해주세요.<br>"; 
      $('#modal_content').html(msg);  // 내용
      $('#modal_panel').modal();         // 다이얼로그 출력
      
      $('#btn_close').attr("data-focus", "new_passwd"); // 모달창에서 닫기 버튼 클릭시 focus를 이동할 태그 설정
      
      return false; // submit 중지
    }

    $('#frm').submit();
  }
  
  function setFocus() {  // focus 이동
    var tag = $('#btn_close').attr('data-focus'); // 포커스를 적용할 태그 id 가져오기
    $('#' + tag).focus(); // 포커스 지정
  }
  
</script>

</head> 
 
 
<body>
<c:import url="../menu/header.jsp" />

  <DIV class='content_body'>
  <DIV class='title_line'>
    회원 패스워드 변경
  </DIV>
    <ASIDE class="aside_right">
    <br>
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./create.do'>회원 가입</A>

    </ASIDE> 
   
    <div class='menu_line'></div>
    
    <div style='width: 40%; margin: 0px auto;'>  
    <FORM name='frm' id='frm' method='POST' action='./passwd_update.do'>
      <input type='hidden' name='memberno' id='memberno' value='${param.memberno }'>       
  
      <div class="form-group">
          <br>  
        <label>현재 패스워드</label>  
    
        <input type='password' class="form-control" name='current_passwd' 
                    id='current_passwd' value='' required="required" style='width: 70%;' placeholder="현재 패스워드">
      </div>   
          <br>  
                      
      <div class="form-group">
        <label>새로운 패스워드</label>    
        <input type='password' class="form-control" name='new_passwd' 
                  id='new_passwd' value='' required="required" style='width: 70%;' placeholder="새로운 패스워드">
      </div>   
       <br>  
      <div class="form-group">
        <label>새로운 패스워드 확인</label>    
        <input type='password' class="form-control" name='new_passwd2' 
                  id='new_passwd2' value='' required="required" style='width: 70%;' placeholder="패스워드 확인">
      </div>   
      
      <div class="form_input">
        <button type="button" id='btn_send' class="my-btn btn">변경</button>
        <button type="button" onclick="location.href='./list.do'" class="my-btn btn">취소</button>
      </div>   

    </FORM>
    </div>
  
</DIV> <%--  <DIV class='content_body'> END --%>
 
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>
 
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>패스워드 검사</title>

<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
          
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
         
<script type="text/javascript">
  $(function() {
    $('#btn_ck').on('click', check); //passwd 확인

    // id가 'btn_close'인 태그가 클릭되면 setFocus 함수를 실행, Dialog창을 닫은후의 focus 이동
    $('#btn_close').on('click', setFocus); 
  });

  function check() {

	  if $('#passwd').val() != $('#current_passwd').val()) { //passwd 일치 확인
	    $('#modal_title').html('패스워드 일치 여부 확인');
	    $('#modal_content').attr('class', 'alert alert-danger');
	    var msg = '· 입력된 패스워드가 일치하지 않습니다.<br>';
	    msg += "· 패스워드를 다시 입력해주세요.<br>";
	    $('#modal_content').html(msg);
	    $('#modal_panel').modal();
	    $('#btn_close').attr("data-focus", "passwd");
	    return false;
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
    패스워드 확인
  </DIV>
   
    <div class='menu_line'></div>
    
    <div style='width: 40%; margin: 0px auto;'>  
    <FORM name='frm' id='frm' method='POST' action='./passwd_check.do'>
      <input type='hidden' name='masterno' id='masterno' value='${param.masterno }'>       
  
      <div class="form-group">
      <br>
        <label>현재 패스워드</label>    
        <input type='password' class="form-control" name='current_passwd' 
                    id='current_passwd' value='' required="required" style='width: 70%;' placeholder="패스워드">
      </div>   
      
       <div class="form_input">
        <button type="submit" id='btn_ck' class="my-btn btn">확인</button>
        <button type="button" onclick="location.href='./list.do'" class="my-btn btn">취소</button>
      </div> 
      
      </FORM>
      </div>   
          
       </div>  
       
 <jsp:include page="../menu/footer.jsp" flush='false' /> 
</body>
</html>


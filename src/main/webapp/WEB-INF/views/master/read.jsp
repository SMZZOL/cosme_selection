<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>관리자 조회</title>

<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
    // jQuery ajax 요청

  function setFocus() {  // focus 이동
    // console.log('btn_close click!');
    
    let tag = $('#btn_close').attr('data-focus'); // data-focus 속성에 선언된 값을 읽음 
    // alert('tag: ' + tag);
    
    $('#' + tag).focus(); // data-focus 속성에 선언된 태그를 찾아서 포커스 이동
  }
         
    let mname = $('#mname').val(); // 태그의 아이디가 'id'인 태그의 값
    if ($.trim(mname).length == 0) { // id를 입력받지 않은 경우
      $('#modal_title').html('이름 입력 누락'); // 제목 
        
      $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
      msg = '· 이름을 입력하세요.<br>· 이름 입력은 필수입니다.';
      $('#modal_content').html(msg);        // 내용
      $('#btn_close').attr("data-focus", "mname");  // 닫기 버튼 클릭시 mname 입력으로 focus 이동
      $('#modal_panel').modal();               // 다이얼로그 출력
      return false;
   } 

    $('#frm').submit(); // required="required" 작동 안됨.
  }  
</script>
</head> 


<body>
<c:import url="../menu/header.jsp" />

  <DIV class='content_body'>
  
    <DIV class='title_line'>수정</DIV>

  <ASIDE class="aside_right">
  <Br>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 

  <div class='menu_line'></div>
 <br> 
  <div style="width: 60%; margin: 0px auto ">
  <FORM name='frm' id='frm' method='POST' action='./update.do' class="">
    <input type="hidden" name="masterno" value="${masterVO.masterno }">
    
  
    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
      <label>이름 수정:
        <input type='text' class="form-control form-control-sm" name='mname' id='mname' value='${masterVO.mname }' required="required" placeholder="이름">
      </label>
    </div>   

    <div>
    </div>
    
    <div class="form_input">
      <button type="button" id='btn_send' onclick="send()" class="my-btn btn">저장</button>
      <button type="button" onclick="history.back()" class="my-btn btn">취소</button>
    </div>   
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/footer.jsp" flush='false' />
</body>

</html>
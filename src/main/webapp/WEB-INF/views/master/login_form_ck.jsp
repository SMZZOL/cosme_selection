<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>관리자 로그인</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
  $(function() { // click 이벤트 핸들러 등록
    $('#btn_create').on('click', create); // 회원 가입
    $('#btn_loadDefault').on('click', loadDefault); // 기본 로그인 정보 설정
  });

  // 회원 가입  
  function create() {
    location.href="./create.do";
  }

  // 테스트용 기본값 로딩
  function loadDefault() {
    $('#id').val('master1');
    $('#passwd').val('1234');
  }
    
</script> 

</head> 
 
<body>
  <c:import url="../menu/header.jsp" />
    
    <DIV class='content_body'>
    <br>
  <DIV class='title_line'>관리자 로그인</DIV>
   <br>
  <br>

  <DIV class='content_body'> 
    <DIV style='width: 40%; margin: 0px auto;'>
      <FORM name='frm' method='POST' action='./login.do'>
      
        <div class="form_input">
          <input type='text' class="form-control" name='id' id='id' 
                    value="${cookie.ck_master_id.value }" required="required" 
                    style='width: 80%;' placeholder="아이디" autofocus="autofocus">
          <Label>   
            <input type='checkbox' name='id_save' value='Y' ${cookie.ck_master_id_save.value == 'Y' ? "checked='checked'" : "" }> 저장
            
          </Label> 
             
        </div>   
     <br>
        <div class="form_input">
          <input type='password' class="form-control" name='passwd' id='passwd' 
                    value='${cookie.ck_master_passwd.value }' required="required" style='width: 80%;' placeholder="패스워드">
          <Label>
            <input type='checkbox' name='passwd_save' value='Y' ${cookie.ck_master_passwd_save.value == 'Y' ? "checked='checked'" : "" }> 저장
          </Label>                    
        </div>   
     
        <div class="form_input">
          <button type="submit" class="my-btn btn">로그인</button>
          <button type='button' id='btn_loadDefault' class="my-btn btn">테스트 계정</button>
        </div>   
        
      </FORM>
    </DIV>
  </DIV> <%-- <DIV class='content_body'> END --%>
  </DIV>
 
<jsp:include page="../menu/footer.jsp"/>
</body>
 
</html>


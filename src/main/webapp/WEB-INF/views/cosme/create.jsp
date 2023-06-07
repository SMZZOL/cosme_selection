<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>화장품 등록</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />

<DIV class='content_body'>
  <DIV class='title_line'>화장품 등록</DIV>
    
    <div>
       <label class="">제목</label>
       <input type='text' name='cosmename' value='화장품 이름' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <br><Br>
    <div>
       <label class="">브랜드</label>
       <input type='text' name='brand' value='화장품 브랜드' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label class="">화장품 관련 유튜브 채널</label>
       <input type='text' name='cosme_youtube' value='화장품 유튜브 채널' 
                 class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>이미지</label>
       <input type='file' class="form-control" name='file1MF' id='file1MF' 
                 value='' placeholder="파일 선택">
    </div>   
    <div>
        <label>화장품 카테고리</label>
        <select>
          <c:forEach var="cosme_cateVO" items="${list2}" >
            <option value="${cosme_cateVO.cosme_cateno}">${cosme_cateVO.cosme_catename}</option>
          </c:forEach>
        </select>
    </div>
     <div>
        <label>화장품 타입</label>
        <select>
          <c:forEach items="${cosmetype}" var="item">
            <option value="${item.cosmetypeno}">${item.cosmetypename}</option>
          </c:forEach>
        </select>
    </div>
     <div>
        <label>화장품 성분</label>
        <select>
          <c:forEach items="${ingred}" var="item">
            <option value="${item.ingredno}">${item.ingredname}</option>
          </c:forEach>
        </select>
    </div>

    <div class="content_body_bottom">
      <button type="submit" class="btn btn-info">등록</button>
      <button type="button" onclick="location.href='./list_by_type.do'" class="btn btn-info">목록</button>
    </div>
  
  </FORM>
</DIV>
 

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
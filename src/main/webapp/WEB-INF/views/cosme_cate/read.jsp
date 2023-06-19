<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="dev.mvc.cosme_cate.Cosme_cateVO" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=5.0, width=device-width" /> 
<title>http://localhost:9093/cosme_cate/read.jsp?cosme_cateno=1</title>
<style type="text/css">
  *{ font-family: Malgun Gothic; font-size: 26px;}
</style>
</head>
<body>
<DIV style="font-size: 20px;">
<% Cosme_cateVO cosme_cateVO = (Cosme_cateVO) request.getAttribute("cosme_cateVO"); %>

 cosme_cateno:<%= cosme_cateVO.getCosme_cateno() %><br>
 cosme_catename:<%= cosme_cateVO.getCosme_catename() %><br>
</DIV>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cosmetypename" value="${cosmetypename}" />
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>맞춤 추천</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<style>
</style>
<body>
  <c:import url="../menu/header.jsp" />


<h2>${sessionScope.mname} 님의 추천 리스트입니다 ! (${cosmetypename})</h2>
    <div id="grid" class="product-grid">
        <c:forEach items="${cosme_list}" var="cosme_list">
            <div class="product-item" onclick="location.href='/cosme/read_by_cosmeno.do?cosmeno=${cosme_list.cosmeno}'">
                <img class="img-90" src="/cosme/${cosme_list.cosme_file_preview}" alt="image not found">
                <h3>${cosme_list.cosmename}</h3>
                <p>${cosme_list.brand}</p>
                                 <p style="color:red;'">★${cosme_list.reviewgrade}</p>
                </div>
        </c:forEach>
    </div>
    <Br>
<Br>
<Br>
  <c:import url="../menu/footer.jsp" />

</body>
</html>
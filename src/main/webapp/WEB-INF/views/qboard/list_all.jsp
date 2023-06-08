<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.qboard.QboardVO" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>team2</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="../menu/header.jsp" />



<DIV class='title_line'>질문 게시판</DIV>

<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>질문 검색</label>
      <input type='text' name='name' value='' required="required" style='width: 25%;' autofocus="autofocus">
  
      <button type="submit" id='submit' class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>등록</button>
      <button type="button" onclick="location.href='/qobard/list_all.do'" class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>취소</button>
    </FORM>
  </DIV>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 50%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">질문 번호</TH>
      <TH class="th_bs">질문 제목</TH>
      <TH class="th_bs">질문 내용</TH>
      <TH class="th_bs">등록일</TH>
    </TR>
    </thead>
    
    
    <tbody>
    <%
    ArrayList<QboardVO> list = (ArrayList<QboardVO>)request.getAttribute("list");
    
    for (int i=0; i < list.size(); i++) {
      QboardVO qboardVO = list.get(i);
    %>
      <TR>
        <TD class='td_bs'><%= qboardVO.getQboardno() %></TD>
        <TD class='td_bs'><%=qboardVO.getQtitle() %></TD>
        <TD class='td_bs'><%=qboardVO.getQcontent() %></TD>
        <TD class='td_bs'><%=qboardVO.getRdate().substring(0, 10) %></TD>
  
      </TR>
    <%  
    }
    %>
    </tbody>

   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/footer.jsp" />
</body>
 
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cosmename" value="${cosmevo.cosmename }" />
<c:set var="brand" value="${cosmevo.brand }" />
<c:set var="cosme_file_saved" value="${cosmevo.cosme_file_saved }" />
<c:set var="cosmeno" value="${cosmevo.cosmeno }" />
<c:set var="memberno" value="${sessionScope.memberno }" />
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" />
<title>${cosmename}조회</title>
<link rel="stylesheet" href="style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

		// ---------------------------------------- 댓글 관련 시작 ----------------------------------------
		var review_form = $('#review_form');
		$('#btn_create', review_form).on('click', review_create); // 댓글 작성 시 로그인 여부 확인

	});

	function review_create() {
		  var review_form = $('#review_form');
		  var memberno = $('#memberno').val();
		  var cosmeno = $('#cosmeno').val();

		  check_member(memberno, cosmeno, function(result) {
		    if (result) {
		      var params = review_form.serialize();
		      $.ajax({
		        url: "/review/add_review.do",
		        type: "post",
		        cache: false,
		        async: true,
		        dataType: "json",
		        data: params,
		        success: function(rdata) {
		          var comment = document.getElementById("comment");
		          comment.innerHTML = rdata.str;
		        },
		        error: function(request, status, error) {
		          var msg = 'ERROR request.status: ' + request.status + '/ ' + error;
		          console.log(msg);
		        }
		      });
		    } else {
		      alert("후기는 한 개만 등록 가능합니다.");
		    }
		  });
		}

		function check_member(memberno, cosmeno, callback) {
		  var params = {
		    memberno: memberno,
		    cosmeno: cosmeno
		  };
		  $.ajax({
		    url: '/review/check_member.do',
		    type: 'post',
		    cache: false,
		    async: true,
		    dataType: 'json',
		    data: params,
		    success: function(rdata) {
		      var cnt = rdata.cnt;
		      if (cnt >= 1) {
		        callback(false);
		      } else {
		        callback(true);
		      }
		    },
		    error: function(request, status, error) {
		      console.log(error);
		    }
		  });
		}

	function confirmDelete(event) {
	    event.preventDefault();
	    
	    if (confirm("삭제 하면 되돌릴 수 없습니다. 삭제 하시겠습니까?")) {
	        document.forms['deleteForm'].submit();
	    }
	}

	
</script>
</head>

<body>
	<jsp:include page="../menu/header.jsp" />



	<DIV class='content_body'>
		<DIV class='title_line'>화장품 조회</DIV>
            <button class="my-btn btn float-end" onclick="location.href='/cosme/list_by_type.do'">목록</button>
            <form action="/cosme/delete.do" method="POST"  name="deleteForm">
            <c:if test ="${sessionScope.admin_id != null}">
            <input type="hidden" name="cosmeno" value="${cosmeno}">
            <button class="my-btn btn float-end me-2" onclick="confirmDelete(event)'">삭제</button>
            </c:if>
            </form>
		<div class="mb-3 mt-3 clearfix">
			<!--   <span class="float-end">조회수: </span>-->
		</div>
		<section>
			<div class="mb-3 mt-3">
				<div class="text-center">
					<img class="img-40" src="/cosme/${cosme_file_saved}"
						alt="image not found">
				</div>
			</div>
			<div class="mb-3">
				<label for="name">상품명:</label>
				<div class="border bg-light rounded p-2">${cosmename}</div>
			</div>
			<div class="mb-3 mt-3">
				<label for="subject">브랜드:</label>
				<div class="border bg-light rounded p-2">${ brand}</div>
			</div>
			<div class="mb-3 mt-3">
				<label for="content">화장품 타입:</label>
				<div class="border bg-light rounded p-2">
					<c:forEach items="${cosmetype_list}" var="cosmetype_list"
						varStatus="status">
                          ${cosmetype_list.cosmetypename}
							  <c:if test="${not status.last}">,</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="mb-3 mt-3">
				<label for="content">상세 성분:</label>
				<div class="border bg-light rounded p-2">
					<c:forEach items="${ingredVO}" var="ingredVO" varStatus="status">
                          ${ingredVO.ingredname}
                              <c:if test="${not status.last}">,</c:if>
					</c:forEach>
				</div>
			</div>
		</section>
	</DIV>
	<div class="comment-container">
		<!-- 댓글 입력 폼 -->
		<div class="comment-form">
			<h2>후기 작성</h2>
			<form name='review_form' id='review_form'>
				<input type='hidden' name='memberno' id='memberno' value='${sessionScope.memberno}'> 
				<input type='hidden' name='cosmeno' id='cosmeno' value='${cosmeno}'>
				<div>
					<textarea class="review_text" name="reviewcontent"
						placeholder="후기 내용" required></textarea>
						<select name="reviewgrade">
						  <option value="1">1</option>
						  <option value="2">2</option>
						  <option value="3">3</option>
						  <option value="4">4</option>
						  <option value="5">5</option>
						</select>
						<c:if test="${memberno !=null }">
					<button class="normal_button" type="button" id="btn_create">등록</button>
						</c:if>
				</div>
			</form>
		</div>
		<div  id="comment">
		<c:forEach items="${reviewlist}" var="reviewlist" >
		<div class="comment">
			<div class="author">${reviewlist.mname }</div>
			<div class="grade">★${reviewlist.reviewgrade }</div>
			<div class="content">${reviewlist.reviewcontent }</div>
			<div class="timestamp">${reviewlist.reviewtime }</div>
		</div>
		</c:forEach>
		</div>

	</div>
	<jsp:include page="../menu/footer.jsp" />
</body>
</html>
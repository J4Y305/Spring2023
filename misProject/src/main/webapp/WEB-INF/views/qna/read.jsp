<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	//치환 변수 선언합니다.
	pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
	pageContext.setAttribute("br", "<br/>"); //br 태그
%>

<jsp:include page="../include/header.jsp" />

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Q&A 상세보기</h3>
				</div>
				<!-- /.box-header -->

					<form role="form" action="modify" method="post">
						<input type='hidden' name='qnaNo' value="${qnAVO.qnaNo}" id="qnaNo"> 
						<input type='hidden' name='userNo' value="${qnAVO.userNo}" id="userNo"> 
						
						<input type='hidden' name='page' value="${cri.page}"> 
						<input type='hidden' name='perPageNum' value="${cri.perPageNum}"> 
						<input type='hidden' name='searchType' value="${cri.searchType}">
						<input type='hidden' name='keyword' value="${cri.keyword}">

						<div class="row" style="justify-content: center;">
							<div class="col-lg-12">
								<!-- project card -->
								<div class="card d-block">
									<div class="card-body">

										<!-- project title-->
										<h3 class="mt-0">${qnAVO.qnaTitle}</h3>
										<div class="badge badge-secondary mb-3">
											<i class="mdi mdi-face"></i>&nbsp; ${qnAVO.userName}
										</div>
										<div class="badge badge-secondary mb-3">
											<i class="mdi mdi-eye-plus"></i> &nbsp;
											<fmt:formatDate value="${qnAVO.regDate}" pattern="MM/dd/yyyy" />
										</div>
										<div class="badge badge-secondary mb-3">
											<i class="mdi mdi-eye-plus"></i> &nbsp; ${qnAVO.hits}
										</div>

										<p style="font-size: 1em;">${fn:replace(qnAVO.qnaContents, crcn, br)}
										</p>
										<div class="row">
											<div class="col-12">
												<div class="card widget-inline"></div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6">
												<a href="/qna/list"
													class="btn text-muted d-none d-sm-inline-block btn-link font-weight-semibold">
													<i class="mdi mdi-arrow-left"></i> 목록으로 돌아가기
												</a>
											</div>
											<div class="col-lg-6">
												<div class="text-sm-right">
													<c:if test="${login.usid eq qnAVO.userNo}">
														<button class="btn btn-danger qnaDelete">삭제</button>
														<button class="btn btn-primary qnaModify">수정</button>
													</c:if>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					<!-- end card-body-->


					<!-- 댓글창 -->
					<div class="card">
						<div class="card-body">
							<h4 class="mt-0 mb-3">Comments</h4>
							<form role="form" method="get">
								<input type="hidden" value="${login.usid}" id="newUserNo">
								<textarea class="form-control form-control-light mb-2" placeholder="Write message" id="newReplyText" rows="3"></textarea>
								<div class="text-right">
									<div class="btn-group mb-2">
										<button type="button"
											class="btn btn-link btn-sm text-muted font-18">
										</button>
									</div>
									<div class="btn-group mb-2 ml-2">
									<a class="btn btn-outline-primary btn-rounded comentAddBtn" style="font-weight:bold; ">댓글 등록</a>
									</div>
								</div>
								<div class="col-lg-12">
									<div class="inbox-widget">
										<h5 class="mt-0">댓글 목록</h5>
										<div class="card">
											<ul id="reply">
											</ul>
										</div>
										<div style="text-align: right;">
										</div>
									</div>

								</div>

								<!-- end card-body-->
							</form>
						</div>
						<!-- end card-->
					</div>
					<!-- end col -->

				</div>
			</div>
		</div>
		
<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		/* 삭제버튼 */
		$(".qnaDelete").on("click", function() {
			formObj.attr("action", "/qna/remove");
			
			formObj.submit();
		});

		/* 수정버튼 */
		$(".qnaModify").on("click", function() {
			formObj.attr("action", "/qna/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});

	});
</script>



<script>
	var qnaNo = $("#qnaNo").val(); // QnA 게시글 번호
	var loginNo = $("#newUserNo").val();
	var writeUser = $("#userNo").val(); //게시글 쓴 사람 정보 가져오기, QnA 게시글 작성자 번호

	$.getJSON("/reply/all/" + qnaNo,
					function(data) {
						var str = "";
						$(data).each(function() {
							
											var strbutton = "";
											str += "<li data-replyNo='" + this.replyNo + ">"
													+ "<div class='card'>"
													+ this.userName
													+ "<br>"
													+ this.replyText
													+ "<br>"

											if (loginNo == this.userNo || loginNo == writeUser)//댓글 정보와 로그인 정보 같을 경우 OR 게시글의 주인 인 경우 댓글 삭제 가능
											{
												strbutton += "<div class='card'>"
														+ "  <a href='#' onclick='deleteReply("
														+ this.replyNo
														+ ")'>삭제</a>"
														+ "</div>";
											}

											str += strbutton;
											str += "</div></li>";

										});

						$("#reply").html(str);

					});

	//댓글 저장 버튼 클릭 이벤트 submit
	$(".comentAddBtn").on("click", function() {

		// 입력 form 선택자
		var loginNo = $("#newUserNo");
		var replyTextObj = $("#newReplyText");

		var userNo = loginNo.val();
		var replyText = replyTextObj.val();

		if (userNo == "") { //로그인 정보 없을 경우

			alert("로그인 후 댓글 기능을 사용할 수 있습니다.");
			replyTextObj.val("");
			return;

		}

		// 댓글 입력처리 수행
		$.ajax({
			type : "post",
			url : "/reply/",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : "text",
			data : JSON.stringify({
				qnaNo : qnaNo,
				userNo : userNo,
				replyText : replyText
			}),
			success : function(result) {
				if (result === "SUCCESS") {
					alert("댓글이 등록되었습니다.");
					$("#newReplyText").val(""); //댓글 입력창 공백처리
					getReplies(); //댓글 목록 호출
				}
			}
		});
	});

	function deleteReply(replyNo) {

		$.ajax({
			type : 'delete',
			url : '/reply/' + replyNo,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("삭제 되었습니다.");
					getReplies();
				}
			}
		});

	}

	function getReplies() {

		$.getJSON("/reply/all/" + qnaNo,
						function(data) {
							var str = "";

							$(data).each(function() {

												var strbutton = "";
												str += "<li data-replyNo='" + this.replyNo + ">"
												+ "<div class='card'>"
												+ this.userName
												+ "<br>"
												+ this.replyText
												+ "<br>"

												if (loginNo == this.userNo || loginNo == writeUser)//댓글 정보와 로그인 정보 같을 경우 OR 게시글의 주인 인 경우 댓글 삭제 가능
												{
													strbutton += "<div class='card'>"
														+ "  <a href='#' onclick='deleteReply("
														+ this.replyNo
														+ ")'>삭제</a>"
														+ "</div>";
												}
												
												str += strbutton;
												str += "</div></li>";
											});

							$("#reply").html(str);

						});

	}
</script>
</section>
<!-- /.content -->

<%@include file="../include/footer.jsp"%>
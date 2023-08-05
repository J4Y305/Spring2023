<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<jsp:include page="../include/header.jsp" />

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Q&A 수정하기</h3>
				</div>
				<!-- /.box-header -->
				<form role="form" action="frm" method="post">
					<input type='hidden' name='qnaNo' value="${qnAVO.qnaNo}"> 
					<input type='hidden' name='userNo' value="${qnAVO.userNo}"> 
					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
					<input type='hidden' name='searchType' value="${cri.searchType}">
					<input type='hidden' name='keyword' value="${cri.keyword}">


					<div class="row">
						<div class="col-lg-12">
							<div class="center-align">
								<div class="card">
									<div class="card-body">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="proName">제목 <span class="text-danger">*</span></label>
													<input type="text" class="form-control" name="qnaTitle"
														id="qnaTitle" value="${qnAVO.qnaTitle}">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-md-6">
												<label for="inputEmail4" class="col-form-label">작성자</label>
												<input type="text" class="form-control" name="userName"
													id="userName" value="${qnAVO.userName}" readonly="readonly">
											</div>

											<div class="form-group col-md-6">
												<label for="inputAddress2" class=" col-md-7">작성일</label> <input
													type="text" 
													value="<fmt:formatDate pattern="yyyy-MM-dd" value="${qnAVO.regDate}" />"
													class="form-control" readonly="readonly">
											</div>
										</div>

										<div class="row">
											<div class="form-group col-md-12">
												<label for="inputEmail4" class="col-form-label">상세내용</label>
												<textarea class="form-control" name="qnaContents"
													id="qnaContents" rows="15">${qnAVO.qnaContents}</textarea>
											</div>
										</div>

										<div class="row mt-4">
											<div class="col-sm-6">
												<a href="/qna/list"
													class="btn text-muted d-none d-sm-inline-block btn-link font-weight-semibold">
													<i class="mdi mdi-arrow-left"></i> 목록으로 돌아가기
												</a>
											</div>
											<!-- end col -->
											<div class="col-sm-6">
												<div class="text-sm-right">
													<button class="btn btn-primary" type="submit">수정</button>
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div style="margin-bottom: 2%"></div>
			<!-- end card-body -->
		</div>
		<!-- end card-->
	</div>

	<script>
		$(document).ready(function() {

			var formObj = $("form[role='form']");

			console.log(formObj);

			/* 수정버튼 */
			$(".btn-primary").on("click", function() {
				formObj.attr("action", "/qna/modify");
				formObj.attr("method", "post");
				formObj.submit();
			});

		});
	</script>
</section>
<!-- /.content -->

<%@include file="../include/footer.jsp"%>
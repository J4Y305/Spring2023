<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<h3 class="box-title">Q&A 등록하기</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" name="frm" method="post">
					<input type="hidden" name="userNo" id="userNo"
						value="${login.usid}">

					<div class="form-group mb-3">
						<label for="qnaTitle">제목 <span class="text-danger">*</span></label>
						<input type="text" class="form-control" name="qnaTitle"
							id="qnaTitle" placeholder="제목을 입력하세요." />

					</div>
					<div class="form-group mb-3">
						<label for="userName">작성자 <span class="text-danger">*</span></label>
						<input type="text" class="form-control" name="userName"
							id="userName" value="${login.uname}" readonly="readonly" />
					</div>

					<div class="form-group mb-3">
						<label for="inputAddress2" class="col-form-label">내용</label>
						<textarea class="form-control" name="qnaContents" id="qnaContents"
							rows="15"></textarea>
					</div>

					<div style="text-align: right;">
						<button type="submit" class="btn btn-primary">등록</button>
						<button type="button" onclick="location.href='list'"
							class="btn btn-light" style="font-size: 14px;">취소</button>
					</div>
				</form>
			</div>
			<!-- end card-body -->
		</div>
		<!-- end card-->
	</div>

</section>
<!-- /.content -->

<%@include file="../include/footer.jsp"%>
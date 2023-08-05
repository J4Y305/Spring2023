<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../include/header.jsp" />

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->


		<div class="col-md-12">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Q&A 관리</h3>
				</div>

				<div class='box-body'>
					<select name="searchType">
						<option value="nn"
							<c:out value="${cri.searchType == 'nn'?'selected':''}"/>>
							전체</option>
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							제목</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							내용</option>
						<option value="n"
							<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
							문의자</option>
					</select> <input type="text" name='keyword' id="keywordInput"
						value='${cri.keyword }'>
					<button id='searchBtn'>검색</button>
					<button id='newBtn'>Q&A 등록</button>
				</div>

				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Q&A 목록</h3>
					</div>
					<div class="box-body">
						<table class="table table-bordered">
							<tr>
								<th>NO</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>

							<tbody>
								<c:if test="${!empty list}">
									<c:forEach items="${list}" var="qnaVO" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href='read?qnaNo=${qnaVO.qnaNo}'>
													${qnaVO.qnaTitle}</a></td>
											<td>${qnaVO.userName}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd"
													value="${qnaVO.regDate}" /></td>
											<td>${qnaVO.hits}</td>
										</tr>
									</c:forEach>

								</c:if>

								<c:if test="${empty list}">
									<tr>
										<td colspan="4">내역이 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<!-- end card-body-->


				<div class="box-footer">

					<div class="text-center">
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
							</c:if>

						</ul>
					</div>

				</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("등록되었습니다.");
	}

	var result = '${msg}';

	if (result == 'MODIFY') {
		alert("수정되었습니다.");
	}

	var result = '${msg}';

	if (result == 'REMOVE') {
		alert("삭제되었습니다.");
	}
</script>

<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "list"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});
				
				$('#newBtn').on("click", function(evt) {
					self.location = "register";
				});
			});
</script>

<%@include file="../include/footer.jsp"%>
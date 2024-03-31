<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>搜尋關鍵字 : ${querystring } - ${forum.forumName }</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>
<style type="text/css">
a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: underline;
}
</style>
</head>
<body>
	<jsp:include page="/components/header.jsp"></jsp:include>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="/forums">討論區</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/forums/${forum.forumId }">${forum.forumName }</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/articles?forumId=${forum.forumId }">文章列表</a></li>

				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-3">
				<form action="/articles/query" method="get">
					<input type="text" hidden name="forumId" value="${forum.forumId }">
					<input type="text" placeholder="請輸入查詢字串" name="querystring"
						required="required" />
					<button class="btn btn-primary" type="submit">查詢</button>
				</form>
			</div>

			<div class="col-9">
				<ul class="pagination justify-content-center">
					<c:if test="${Pagination.totalPages>1 }">

						<c:url value="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=1"
							var="Firstpage"></c:url>
						<c:url
							value="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=${ Pagination.totalPages }"
							var="endpage"></c:url>

						<c:url
							value="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=${currentPageNo-1 }"
							var="previouspage"></c:url>
						<c:url
							value="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=${currentPageNo+1 }"
							var="nextpage"></c:url>

						<c:url
							value="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=${currentPageNo-2 }"
							var="previoustwo"></c:url>
						<c:url
							value="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=${currentPageNo+2 }"
							var="nexttwo"></c:url>

						<c:choose>
							<c:when test="${currentPageNo==1}">
								<li class="page-item disabled"><span class="page-link">Previous</span></li>
								<li class="page-item active"><span class="page-link">1</span></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="${previouspage }">Previous</a></li>
								<li class="page-item"><a class="page-link"
									href="${startpage }">1</a></li>
							</c:otherwise>
						</c:choose>

						<c:if test="${Pagination.totalPages>2 }">
							<c:if test="${currentPageNo>=4 }">
								<li class="page-item"><a class="page-link"
									href="${previoustwo }">...</a></li>
							</c:if>

							<c:choose>
								<c:when test="${currentPageNo==1 && Pagination.totalPages>3}">
									<li class="page-item"><a class="page-link"
										href="${nextpage }">2</a></li>
								</c:when>
								<c:when test="${currentPageNo==2 && Pagination.totalPages>3}">
									<li class="page-item active"><span class="page-link">2</span></li>
									<li class="page-item"><a class="page-link"
										href="${nextpage }">3</a></li>
								</c:when>
								<c:when
									test="${currentPageNo>=3 && currentPageNo<=Pagination.totalPages-2 }">

									<li class="page-item"><a class="page-link"
										href="${previouspage }">${currentPageNo-1 }</a></li>
									<li class="page-item active"><span class="page-link">${currentPageNo }</span></li>
									<li class="page-item"><a class="page-link"
										href="${nextpage }">${currentPageNo+1 }</a></li>
								</c:when>
								<c:when
									test="${currentPageNo==Pagination.totalPages-1 && Pagination.totalPages>3}">
									<li class="page-item"><a class="page-link"
										href="${previouspage }">${currentPageNo-1}</a></li>
									<li class="page-item active"><span class="page-link">${currentPageNo}</span></li>
								</c:when>
								<c:when
									test="${currentPageNo==Pagination.totalPages && Pagination.totalPages>3}">
									<li class="page-item"><a class="page-link"
										href="${previouspage }">${currentPageNo-1}</a></li>
								</c:when>

								<c:otherwise>
									<c:choose>
										<c:when test="${currentPageNo==2 }">
											<li class="page-item active"><span class="page-link">${Pagination.totalPages-1 }</span></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
												href="/articles/query?forumId=${param.forumId }&querystring=${param.querystring }&PageNo=2">${Pagination.totalPages-1 }</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>

							<c:if test="${currentPageNo<=Pagination.totalPages-3}">
								<li class="page-item"><a class="page-link"
									href="${nexttwo }">...</a></li>
							</c:if>
						</c:if>


						<c:choose>
							<c:when test="${currentPageNo==Pagination.totalPages}">
								<li class="page-item active"><span class="page-link">${Pagination.totalPages }</span></li>
								<li class="page-item disabled"><span class="page-link">Next</span></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="${endpage }">${Pagination.totalPages }</a></li>
								<li class="page-item"><a class="page-link"
									href="${nextpage }">Next</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>

					<c:if test="${Pagination.totalPages<=1 }">
						<li class="page-item disabled"><span class="page-link">Previous</span></li>
						<li class="page-item active"><span class="page-link">1</span></li>
						<li class="page-item disabled"><span class="page-link">Next</span></li>
					</c:if>
				</ul>


			</div>
		</div>
		<div class="row">
			<hr>
			<div class="col-2">
				搜尋結果： <span style="color: red;">${querystring }</span> , ${querymsg }
			</div>
			<div class="col-3">
				每頁顯示 <select id="pagesize">
					<c:forEach begin="5" end="15" step="5" varStatus="status">
						<c:choose>
							<c:when test="${status.index==Pagination.size }">
								<option value="${status.index }" selected="selected">${status.index }</option>
							</c:when>
							<c:otherwise>
								<option value="${status.index }">${status.index }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>篇文章 ${querymsg }
			</div>
		</div>
	</div>

	<div class="container">
		<div >
			<table class="table table-striped table-hover">
				<tbody>
					<tr>
						<c:forEach var="it" items="${Pagination.content }"
							varStatus="status">
							<ul class="list-inline mb-1"
								style="border: solid 1px black; width: 500px; border-radius: 5px; background-color: lightblue;">
								<li class="list-inline-item">
									<ul class="list-unstyled ">
										<li ><a href="/article?articleId=${it.articleId }"
											style="color: black; max-width: 400px;" class="d-inline-block text-truncate" > <c:if
													test="${it.articleTypes.articleTypeName!='空白' && not empty it.articleTypes.articleTypeName}">
													<span >【${it.articleTypes.articleTypeName}】</span>
												</c:if> <span > ${it.articleTitle }</span>
										</a></li>
										<li>by <a href="/memberCenter" target="_blank">
												${it.userData.userAcc} </a></li>
									</ul>

								</li>

							</ul>
						</c:forEach>
					</tr>
				</tbody>
			</table>

		</div>


	</div>




<script type="text/javascript">
//每頁顯示改變
$("#pagesize").change(function (e) {
	let pagesize = e.target.value
	fetch("/articles/changeps/"+pagesize,{method:"get"})
	window.location.href="http://localhost:8080/articles/query?forumId=${param.forumId}&querystring=${param.querystring }"
})
</script>

</body>
</html>
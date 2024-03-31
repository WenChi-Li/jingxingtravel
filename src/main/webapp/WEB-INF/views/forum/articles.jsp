<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${forum.forumName }-文章列表</title>
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
			<div class="col-2">
				<button class="btn btn-primary" id="publish">發文</button>
			</div>

			<div class="col-3">
				<form action="/articles/query" method="get">
					<input type="text" hidden name="forumId" value="${forum.forumId }">
					<input type="text" placeholder="請輸入查詢字串" name="querystring"
						required="required" />
					<button class="btn btn-primary" type="submit">查詢</button>
				</form>
			</div>
			<div class="col-4">

				<ul class="pagination justify-content-center">
					<c:if test="${Pagination.totalPages>1 }">

						<c:url value="/articles?forumId=${param.forumId }&PageNo=1"
							var="Firstpage"></c:url>
						<c:url
							value="/articles?forumId=${param.forumId }&PageNo=${ Pagination.totalPages }"
							var="endpage"></c:url>

						<c:url
							value="/articles?forumId=${param.forumId }&PageNo=${currentPageNo-1 }"
							var="previouspage"></c:url>
						<c:url
							value="/articles?forumId=${param.forumId }&PageNo=${currentPageNo+1 }"
							var="nextpage"></c:url>

						<c:url
							value="/articles?forumId=${param.forumId }&PageNo=${currentPageNo-2 }"
							var="previoustwo"></c:url>
						<c:url
							value="/articles?forumId=${param.forumId }&PageNo=${currentPageNo+2 }"
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
									href="${Firstpage }">1</a></li>
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
												href="/articles?forumId=${param.forumId }&PageNo=2">${Pagination.totalPages-1 }</a></li>
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
		<div class="d-grid gap-2 d-md-block">
			<a class="btn btn-primary" role="button"
				href="/articles?forumId=${forum.forumId }">全部主題</a>
			<c:forEach var="it" items="${forum.categories }">
				<a class="btn btn-primary" role="button"
					href="/articles?forumId=${forum.forumId }&categoryId=${it.categoryId}">${it.categoryName }</a>
			</c:forEach>
		</div>
	</div>

	<div class="container">
		<table class="table table-striped table-hover">


			<tbody>
				<tr class="row">
					<td class="col-2">
						<div>子版</div>
					</td>
					<td class="col-6">
						<div>文章標題</div>
					</td>
					<td class="col-1 text-center" id="likeNo">按讚數</td>
					<td class="col-1 text-center" id="collectNo">收藏數</td>
					<td class="col-2 text-center" id="articleDate">最新編輯時間</td>
				</tr>

				<c:choose>
					<c:when test="${querywithlikeorcollection }">
						<c:forEach var="it" items="${Pagination.content }"
							varStatus="status">
							<tr class="row">
								<td class="col-2"><a style="color: black;"
									href="/articles?forumId=${forum.forumId }&categoryId=${it[0].categories.categoryId}">${it[0].categories.categoryName }</a></td>
								<td class="col-6"><a
									href="/article?articleId=${it[0].articleId }"
									style="color: black;"> <c:if
											test="${it[0].articleTypes.articleTypeName!='空白'&& not empty it[0].articleTypes.articleTypeName}">
											<span>【${it[0].articleTypes.articleTypeName}】</span>
										</c:if> ${it[0].articleTitle }
								</a></td>
								<td class="col-1 text-center">${likeNo[status.index] }</td>
								<td class="col-1 text-center">${collectionNo[status.index]}</td>
								<td class="col-2 text-center">${it[0].articleDate }</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<c:forEach var="it" items="${Pagination.content }"
							varStatus="status">
							<tr class="row">
								<td class="col-2"><a style="color: black;"
									href="/articles?forumId=${forum.forumId }&categoryId=${it.categories.categoryId}">${it.categories.categoryName }</a></td>
								<td class="col-6"><a
									href="/article?articleId=${it.articleId }"
									style="color: black;"> <c:if
											test="${it.articleTypes.articleTypeName!='空白'&& not empty it.articleTypes.articleTypeName}">
											<span>【${it.articleTypes.articleTypeName}】</span>
										</c:if> ${it.articleTitle }
								</a></td>
								<td class="col-1 text-center">${likeNo[status.index] }</td>
								<td class="col-1 text-center">${collectionNo[status.index] }</td>
								<td class="col-2 text-center">${it.articleDate }</td>
							</tr>
						</c:forEach>

					</c:otherwise>
				</c:choose>

			</tbody>
		</table>
	</div>

	<script type="text/javascript">
	$(function(){
		
		
	//改變圖示
		$("#articleDate").mouseover(function (e) {
			$("#articleDate").text("最新編輯時間⇅")
		});
		$("#articleDate").mouseout(function (e) {
			$("#articleDate").text("最新編輯時間")
		});
		
		$("#likeNo").mouseover(function (e) {
			$("#likeNo").text("按讚數⇅")
		});
		$("#likeNo").mouseout(function (e) {
			$("#likeNo").text("按讚數")
		});
		
		$("#collectNo").mouseover(function (e) {
			$("#collectNo").text("收藏數⇅")
		});
		$("#collectNo").mouseout(function (e) {
			$("#collectNo").text("收藏數")
		});
		
		
 	//改變排序屬性
		$("#articleDate").click(function (e) {
			fetch("/articles/change/articleDate",{method:"get"}).then(function () {
		 	//改變排序升降
			fetch("/articles/change",{method:"get"}).then(function () {
				window.location.href="http://localhost:8080/articles?forumId=${param.forumId}&categoryId=${param.categoryId}"
				})
			})
		});
 	//改變排序屬性
		$("#likeNo").click(function (e) {
			fetch("/articles/change/like",{method:"get"}).then(function () {
		 	//改變排序升降
			fetch("/articles/change",{method:"get"}).then(function () {
				window.location.href="http://localhost:8080/articles?forumId=${param.forumId}&categoryId=${param.categoryId}"
				})
			})
		});
 	//改變排序屬性
		$("#collectNo").click(function (e) {
			fetch("/articles/change/collection",{method:"get"}).then(function () {
		 	//改變排序升降
			fetch("/articles/change",{method:"get"}).then(function () {
				window.location.href="http://localhost:8080/articles?forumId=${param.forumId}&categoryId=${param.categoryId}"
				})
			})
		});
 	
	//每頁顯示改變
		$("#pagesize").change(function (e) {
			let pagesize = e.target.value
			fetch("/articles/changeps/"+pagesize,{method:"get"}).then(function () {
				window.location.href="http://localhost:8080/articles?forumId=${param.forumId}&categoryId=${param.categoryId}"
			})
		})
	
	


		
		
		
		//發文
		$("#publish").click(function(e){
			
			let userdata = sessionStorage.getItem("loginStatus");
			userdata=JSON.parse(userdata);
			
			if(userdata==null){
				Swal.fire({
					title:"尚未登入",
					text:"請先登入會員",
					icon:"warning",
					showCloseButton: true,
			        showCancelButton: true,
				}).then(rs=>{
					if(rs.isConfirmed){
						window.location="http://localhost:8080/login";
					}
				})
			}else{

 				window.location="http://localhost:8080/articles/addarticle?forumId="+${forum.forumId}
			}
		})
	});

	</script>
</body>
</html>
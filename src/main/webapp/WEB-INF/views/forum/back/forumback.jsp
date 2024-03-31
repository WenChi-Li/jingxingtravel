<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${forum.forumName }</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>
<body>
	<div class="d-flex flex-row">
		<jsp:include page="/components/adminSidebar.jsp" />
		<div class="container">

			<div class='alert alert-success'>
				<h2 align='center'>討論區文章管理系統</h2>
			</div>

			<hr>


			<div class="container">
				<div class="row">

					<div class="col-3">
						<form action="/back/articles/query" method="get">
							<input type="text" hidden name="forumId"
								value="${forum.forumId }"> <input type="text"
								placeholder="請輸入查詢字串" name="querystring" required="required" />
							<button class="btn btn-primary" type="submit">查詢</button>
						</form>
					</div>

					<c:choose>
						<c:when test="${param.categoryId != null }">
							<div class="col-4">

								<ul class="pagination justify-content-center">
									<c:if test="${Pagination.totalPages>1 }">

										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=1&categoryId=${param.categoryId }"
											var="Firstpage"></c:url>
										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${ Pagination.totalPages }&categoryId=${param.categoryId }"
											var="endpage"></c:url>

										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo-1 }&categoryId=${param.categoryId }"
											var="previouspage"></c:url>
										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo+1 }&categoryId=${param.categoryId }"
											var="nextpage"></c:url>

										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo-2 }&categoryId=${param.categoryId }"
											var="previoustwo"></c:url>
										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo+2 }&categoryId=${param.categoryId }"
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
												<c:when
													test="${currentPageNo==1 && Pagination.totalPages>3}">
													<li class="page-item"><a class="page-link"
														href="${nextpage }">2</a></li>
												</c:when>
												<c:when
													test="${currentPageNo==2 && Pagination.totalPages>3}">
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
																href="/back/forums/${forum.forumId }/articles?PageNo=2&categoryId=${param.categoryId }">${Pagination.totalPages-1 }</a></li>
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
						</c:when>
						<c:otherwise>
							<div class="col-4">

								<ul class="pagination justify-content-center">
									<c:if test="${Pagination.totalPages>1 }">

										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=1"
											var="Firstpage"></c:url>
										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${ Pagination.totalPages }"
											var="endpage"></c:url>

										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo-1 }"
											var="previouspage"></c:url>
										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo+1 }"
											var="nextpage"></c:url>

										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo-2 }"
											var="previoustwo"></c:url>
										<c:url
											value="/back/forums/${forum.forumId }/articles?PageNo=${currentPageNo+2 }"
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
												<c:when
													test="${currentPageNo==1 && Pagination.totalPages>3}">
													<li class="page-item"><a class="page-link"
														href="${nextpage }">2</a></li>
												</c:when>
												<c:when
													test="${currentPageNo==2 && Pagination.totalPages>3}">
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
																href="/back/forums/${forum.forumId }/articles?PageNo=2">${Pagination.totalPages-1 }</a></li>
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

						</c:otherwise>
					</c:choose>


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
						href="/back/forums/${forum.forumId }/articles">全部主題</a>
					<c:forEach var="it" items="${forum.categories }">
						<a class="btn btn-primary" role="button"
							href="/back/forums/${forum.forumId }/articles?categoryId=${it.categoryId}">${it.categoryName }</a>
					</c:forEach>
				</div>
			</div>

			<div class="container">
				<table class="table table-striped table-hover">


					<tbody>
						<tr class="row">
							<td class="col-1">
								<div>子版</div>
							</td>
							<td class="col-4">
								<div>文章標題</div>
							</td>
							<td class="col-1 text-center" >作者</td>
							<td class="col-1 text-center" id="likeNo">按讚數</td>
							<td class="col-1 text-center" id="collectNo">收藏數</td>
							<td class="col-2 text-center" id="articleDate">最新編輯時間</td>
							<th class="col-1 text-center">編輯</th>
						</tr>

						<c:choose>
							<c:when test="${querywithlikeorcollection }">
								<c:forEach var="it" items="${Pagination.content }"
									varStatus="status">
									<tr class="row">
										<td class="col-1"><a style="color: black;"
											href="/back/forums/${forum.forumId }/articles?categoryId=${it[0].categories.categoryId}">${it[0].categories.categoryName }</a></td>
										<td class="col-4"><a
											href="/back/article?articleId=${it[0].articleId }"
											style="color: black;max-width: 400px;" class="d-inline-block text-truncate"> <c:if
													test="${it[0].articleTypes.articleTypeName!='空白'&& not empty it[0].articleTypes.articleTypeName}">
													<span>【${it[0].articleTypes.articleTypeName}】</span>
												</c:if> ${it[0].articleTitle }
										</a></td>
										<td class="col-1 text-center">${it[0].userData.userAcc }</td>
										<td class="col-1 text-center">${likeNo[status.index] }</td>
										<td class="col-1 text-center">${collectionNo[status.index] }</td>
										<td class="col-2 text-center">${it[0].articleDate }</td>
										<td class="col-1 text-center btn"><a href="/back/article?articleId=${it[0].articleId }"> <button class="btn btn-primary">編輯</button></a></td>
									</tr>
								</c:forEach>
							</c:when>

							<c:otherwise>
								<c:forEach var="it" items="${Pagination.content }"
									varStatus="status">
									<tr class="row">
										<td class="col-1"><a style="color: black;"
											href="/back/forums/${forum.forumId }/articles?categoryId=${it.categories.categoryId}">${it.categories.categoryName }</a></td>
										<td class="col-4"><a
											href="/back/article?articleId=${it.articleId }"
											style="color: black;max-width: 400px;" class="d-inline-block text-truncate"> <c:if
													test="${it.articleTypes.articleTypeName!='空白'&& not empty it.articleTypes.articleTypeName}">
													<span>【${it.articleTypes.articleTypeName}】</span>
												</c:if> ${it.articleTitle }
										</a></td>
										<td class="col-1 text-center">${it.userData.userAcc }</td>
										<td class="col-1 text-center">${likeNo[status.index] }</td>
										<td class="col-1 text-center">${collectionNo[status.index] }</td>
										<td class="col-2 text-center">${it.articleDate }</td>
										<td class="col-1 text-center"><a href="/back/article?articleId=${it.articleId }"> <button class="btn btn-primary">編輯</button></a></td>
									</tr>
								</c:forEach>

							</c:otherwise>
						</c:choose>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
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
		fetch("/back/articles/change/articleDate",{method:"get"}).then(function () {
	 	//改變排序升降
		fetch("/back/articles/change",{method:"get"}).then(function () {
			if (${param.category != null}) {
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles?categoryId=${param.categoryId}"
			}else{
				
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles"
			}
			})
		})
	});
	//改變排序屬性
	$("#likeNo").click(function (e) {
		fetch("/back/articles/change/like",{method:"get"}).then(function () {
	 	//改變排序升降
		fetch("/back/articles/change",{method:"get"}).then(function () {
			if (${param.category != null}) {
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles?categoryId=${param.categoryId}"
			}else{
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles"
				
			}
			})
		})
	});
	//改變排序屬性
	$("#collectNo").click(function (e) {
		fetch("/back/articles/change/collection",{method:"get"}).then(function () {
	 	//改變排序升降
		fetch("/back/articles/change",{method:"get"}).then(function () {
			if (${param.category != null}) {
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles?categoryId=${param.categoryId}"
			}else{
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles"
				
			}
			})
		})
	});
	
	
	//每頁顯示改變
	$("#pagesize").change(function (e) {
		let pagesize = e.target.value
		fetch("/back/articles/changeps/"+pagesize,{method:"get"}).then(function () {
			if (${param.category != null}) {
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles?categoryId=${param.categoryId}"
			}else{
			window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles"
			}
			})
		})
	
	
	
	</script>
</body>
</html>
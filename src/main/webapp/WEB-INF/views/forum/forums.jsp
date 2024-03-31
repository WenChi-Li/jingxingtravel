<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	
<meta charset="UTF-8">
<title>討論區列表</title>
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

.forum {
	background-color: lightblue;
}

.forum :hover {
	background-color: lightyellow;
}
</style>
</head>
<body>
	<jsp:include page="/components/header.jsp"></jsp:include>

	



	<div class="py-3">
		<div class="container mb-3" style="background-color: #f5f5f5">

			<jsp:include page="/components/pagination.jsp"></jsp:include>

			<div class="row justify-content-md-center">
				<div class="col-6 px-0">
					<div class="d-flex justify-content-end ">
						<button class="btn btn-primary btn-sm justify-content-end"
							id="sortbtn">所有⮃</button>
						<button class="btn btn-primary btn-sm justify-content-end"
							id="popsortbtn">人氣⮃</button>

					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${not querywithpop }">
					<c:forEach var="it" items="${allforums.content }"
						varStatus="status">

						<div class="row justify-content-md-center">
							<div class="col-6 forum px-0 border border-info ">
								<ul class="d-flex mb-0 px-0 justify-content-start">
									<li class="list-inline-item col-3"><a
										href="/forums/${it.forumId }"><img alt=""
											src="${it.forumImage }" class="img img-thumbnail img-fluid"></a></li>
									<li class="list-inline-item">
										<ul class="list-unstyled">
											<li><a href="/forums/${it.forumId }"
												style="color: #8FBC8F;"><h2>${it.forumName }</h2></a></li>
											<li><a
												href="/article?articleId=${latestArticle[it.forumId].articleId }">
													<c:if
														test="${latestArticle[it.forumId].articleTypes.articleTypeName!='空白' && not empty latestArticle[it.forumId].articleTypes.articleTypeName}">
														<span>【${latestArticle[it.forumId].articleTypes.articleTypeName}】</span>
													</c:if> ${latestArticle[it.forumId].articleTitle }
											</a></li>

										</ul>

									</li>
									<li class="list-inline-item ms-auto ">
										<ul class="list-unstyled">
											<li><br></li>
											<li><br></li>
											<li><span class="fs-1 ">${allPopular[it.forumId] }
											</span></li>
										</ul>
									</li>
									<li class="list-inline-item "><br></li>
								</ul>
							</div>
						</div>
					</c:forEach>

				</c:when>
				<c:otherwise>
					<c:forEach var="it" items="${allforums.content }"
						varStatus="status">

						<div class="row justify-content-md-center">
							<div class="col-6 forum px-0 border border-info ">
								<ul class="d-flex mb-0 px-0 justify-content-start">
									<li class="list-inline-item col-3"><a
										href="/forums/${it[0].forumId }"><img alt=""
											src="${it[0].forumImage }"
											class="img img-thumbnail img-fluid"></a></li>
									<li class="list-inline-item">
										<ul class="list-unstyled">
											<li><a href="/forums/${it[0].forumId }"
												style="color: #8FBC8F;"><h2>${it[0].forumName }</h2></a></li>
											<li><a
												href="/article?articleId=${latestArticle[it[0].forumId].articleId }">
													<c:if
														test="${latestArticle[it[0].forumId].articleTypes.articleTypeName!='空白' && not empty latestArticle[it[0].forumId].articleTypes.articleTypeName}">
														<span>【${latestArticle[it[0].forumId].articleTypes.articleTypeName}】</span>
													</c:if> ${latestArticle[it[0].forumId].articleTitle }
											</a></li>

										</ul>

									</li>
									<li class="list-inline-item ms-auto ">
										<ul class="list-unstyled">
											<li><br></li>
											<li><br></li>
											<li><span class="fs-1 ">${it[1] } </span></li>
										</ul>
									</li>
									<li class="list-inline-item "><br></li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<br>
			<jsp:include page="/components/pagination.jsp"></jsp:include>

		</div>

	</div>
	<jsp:include page="/components/footer.jsp"></jsp:include>

	<script type="text/javascript">
		//改變排序屬性(人氣)
		$("#popsortbtn").click(function(e) {

			fetch("/forums/change/pop", {
				method : "get"
			}).then(function() {
				//改變排序升降
				fetch("/forums/change", {
					method : "get"
				}).then(function() {

					window.location.href = "http://localhost:8080/forums?"
				})

			})

		});

		//改變排序屬性(所有)
		$("#sortbtn").click(function(e) {

			fetch("/forums/change/forumId", {
				method : "get"
			}).then(function() {
				//改變排序升降
				fetch("/forums/change", {
					method : "get"
				}).then(function() {

					window.location.href = "http://localhost:8080/forums?"
				})

			})

		});
	</script>

</body>
</html>
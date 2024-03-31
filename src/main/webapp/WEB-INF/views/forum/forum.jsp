<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${forum.forumName }</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>
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
						href="/articles?forumId=${forum.forumId }">文章列表</a>
					</li>

				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<a href="/articles?forumId=${forum.forumId }"> <img
			alt="" src="${forum.forumImage }" class="img img-fluid " title="進入看板"></a>
		<h2>討論區介紹</h2>
		<hr>
		${forum.forumIntroduction}
	</div>

</body>
</html>
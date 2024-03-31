<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${Article.articleTitle }</title>
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
	<div class="d-flex flex-row">
		<jsp:include page="/components/adminSidebar.jsp" />

		<div class="container">
			<div class='alert alert-success'>
				<h2 align='center'>討論區文章管理系統</h2>
			</div>


			<div class="container">
				<div class="row">
					<div class="col align-self-end">
						<button class="btn btn-primary" id="deletebtn"
							value="${Article.articleId}">刪除</button>
					<a href="/back/forums/${Article.categories.forums.forumId }/articles">
						<button class="btn btn-primary" 
							value="${Article.articleId}">返回</button>
					</a>
					</div>

				</div>
				<div class="row" id="articleHeader">
					<h3>
						<c:if test="${Article.articleTypes.articleTypeName!='空白'}">
				【${Article.articleTypes.articleTypeName}】
			</c:if>
						${Article.articleTitle }

					</h3>
					<hr>
					<span> ${Article.userData.userName} <a href="/authorEdit">
							${Article.userData.userAcc} </a>
					</span>
				</div>
				<div class="row" id="articleContent">${Article.articleContent}</div>
				<div>
					<br> <br>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	//刪除文章
	$("#deletebtn").click(function (e) {
		fetch("/deletearticle?articleId="+e.target.value,{method:"delete"})
		.then(rs=>{
			return rs.json();
		}).then(data=>{
			if (data.success) {
				Swal.fire({
					title:data.msg,
					icon:"success",
				})
				.then(rs=>{
					if (rs.isConfirmed) {
						if (${param.category != null}) {
							window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles?categoryId=${param.categoryId}"
							}else{
							window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles"
							}
					}
				})
			} else {
				Swal.fire({
					title:data.msg,
					icon:"error",
				})
				.then(rs=>{
					if (rs.isConfirmed) {
						if (${param.category != null}) {
							window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles?categoryId=${param.categoryId}"
							}else{
							window.location.href="http://localhost:8080/back/forums/${forum.forumId }/articles"
							}
					}
			})
			}
	});
});
	</script>
</body>
</html>
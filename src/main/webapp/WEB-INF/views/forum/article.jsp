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
		<div class="row">
			<div class="col align-self-end">

				<a href="/updatearticle?articleId=${Article.articleId}"><button
						class="btn btn-primary" hidden id="updatebtn">編輯</button></a>
				<button class="btn btn-primary" id="deletebtn"
					value="${Article.articleId}" hidden>刪除</button>


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
			<span> ${Article.userData.userName} <a href="/authorEdit" id="toAuthor"
				> ${Article.userData.userAcc} </a>
			</span>
		</div>
		<div class="row" id="articleContent">${Article.articleContent}</div>
		<div>
			<br> <br>
		</div>
		<div class="row">
			<div class="col-2">
				<button type="button" class="btn btn-primary btn-sm"
					id="likeArticles">按讚</button>
				<c:choose>
					<c:when test="${likednumber!=0 }">
						<span id="likednumber"> ${likednumber } </span>
					</c:when>
					<c:otherwise>
						<span id="likednumber">-</span>
					</c:otherwise>
				</c:choose>

			</div>

			<div class="col-2">
				<button type="button" class="btn btn-primary btn-sm"
					id="collectArticles">收藏</button>
				<c:choose>
					<c:when test="${collectnumber!=0 }">
						<span id="collectnumber">${collectnumber }</span>
					</c:when>
					<c:otherwise>
						<span id="collectnumber">-</span>
					</c:otherwise>
				</c:choose>

			</div>

		</div>
	</div>
		<script type="text/javascript">
		$(function () {
			
			//抓取sessionStorage的userdata
			let userdata = sessionStorage.getItem("loginStatus");
			userdata=JSON.parse(userdata);
			
			if (userdata!=null) {
			       if (userdata.mid==${Article.userData.mid}) {
			        $("#updatebtn").removeAttr("hidden");
			        $("#deletebtn").removeAttr("hidden");
			        $("#toAuthor").attr("href","/authorEdit/"+userdata.mid)
			       }else {
			        $("#toAuthor").attr("href","/authorData/${Article.userData.mid}")
			       }
			      }else {
			       $("#toAuthor").attr("href","/authorData/${Article.userData.mid}")
			      }
			
			
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
								window.location="http://localhost:8080/articles?forumId=${forum.forumId}&categoryId=${Article.categories.categoryId}";
							}
						})
					} else {
						Swal.fire({
							title:data.msg,
							icon:"error",
						})
						.then(rs=>{
							if (rs.isConfirmed) {
								window.location="http://localhost:8080/articles?forumId=${forum.forumId}&categoryId=${Article.categories.categoryId}";
							}
					})
					}
			});
		});
			
			//顯示當前按讚狀況
			
			
			if (userdata!=null) {
				fetch("http://localhost:8080/articles/likedmembers/contain?mid="+userdata.mid+"&articleId=${Article.articleId}",{method:"get"})
				.then(resp=>{
				 return	resp.text()
				}).then(data=>{
					if (data=="true") {
				  		$("#likeArticles").text("收回讚");
							$("#likeArticles").attr("class","btn btn-secondary btn-sm");
							
					} else {
						$("#likeArticles").text("按讚");
							$("#likeArticles").attr("class","btn btn-primary btn-sm");
							
							
					}
				})
				
			}
			
			//顯示當前收藏狀況
			
			if (userdata!=null) {
				fetch("http://localhost:8080/articles/collection/contain?mid="+userdata.mid+"&articleId=${Article.articleId}",{method:"get"})
				.then(resp=>{
				 return	resp.text()
				}).then(data=>{
					if (data=="true") {
				  		$("#collectArticles").text("取消收藏");
							$("#collectArticles").attr("class","btn btn-secondary btn-sm");
					} else {
						$("#collectArticles").text("收藏");
							$("#collectArticles").attr("class","btn btn-primary btn-sm");
					}
				})
				
			}
			
			
			
			//收藏文章
			$("#collectArticles").click(function (e) {
				let userdata = sessionStorage.getItem("loginStatus");
				userdata=JSON.parse(userdata);
				let collectnumber = $("#collectnumber").text();
				
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
	
	 				fetch("http://localhost:8080/articles/collecteduserdata?mid="+userdata.mid+"&articleId=${Article.articleId}",{method:"post"})
	 				.then(resp=>{
	 					return resp.json()
	 				})
	 				.then(data=>{
	 					if (data.modify=="收藏文章") {
	 						$("#collectArticles").text("取消收藏");
	 						$("#collectArticles").attr("class","btn btn-secondary btn-sm");
	 						if (collectnumber=="-") {
								$("#collectnumber").text(1);
							}else {
								$("#collectnumber").text(parseInt(collectnumber)+1);
							}
						} else {
							$("#collectArticles").text("收藏");
	 						$("#collectArticles").attr("class","btn btn-primary btn-sm");
	 						if (parseInt(collectnumber)==1) {
								$("#collectnumber").text("-");
								}else {
									$("#collectnumber").text(parseInt(collectnumber)-1);
								}
						}
	 				})
				}
				
			});
			
			
			
			//按讚文章
			$("#likeArticles").click(function (e) {
				let userdata = sessionStorage.getItem("loginStatus");
				userdata=JSON.parse(userdata);
				
				let likednumber = $("#likednumber").text();
				
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
	
	 				fetch("http://localhost:8080/articles/likeduserdata?mid="+userdata.mid+"&articleId=${Article.articleId}",{method:"post"})
	 				.then(resp=>{
	 					return resp.json()
	 				})
	 				.then(data=>{
	 					if (data.modify=="按讚文章") {
	 						$("#likeArticles").text("收回讚");
	 						$("#likeArticles").attr("class","btn btn-secondary btn-sm");
	 						if (likednumber=="-") {
								$("#likednumber").text(1);
							}else {
								$("#likednumber").text(parseInt(likednumber)+1);
							}
						} else {
							$("#likeArticles").text("按讚");
	 						$("#likeArticles").attr("class","btn btn-primary btn-sm");
	 						if (parseInt(likednumber)==1) {
								$("#likednumber").text("-");
								}else {
									$("#likednumber").text(parseInt(likednumber)-1);
								}
						}
	 				})
				}
				
			});
			
			
		});
	</script>
</body>
</html>
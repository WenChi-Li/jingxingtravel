<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區管理系統</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>

</head>
<body>
	<div class="d-flex flex-row">
		<jsp:include page="/components/adminSidebar.jsp" />
		<div class="container">
			<div class='alert alert-success'>
				<h2 align='center'>討論區管理系統</h2>
			</div>


			<div class="row">
				<div class="col">
					<a href="/addforums">
						<button class="btn btn-primary">新增討論區</button>
					</a>
				</div>


				<div class="col">
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#insertArticleType">新增文章類型</button>
				</div>
				<!-- 				<div class="col-2"> -->
				<!-- 					<button type="button" class="btn btn-primary" id="deleteArticleTypebtn">刪除文章類型</button> -->
				<!-- 				</div> -->
				<div class="col">
					<select class="form-select ">
						<c:forEach var="atype" items="${ArticleTypes}">
							<option>${atype.articleTypeName }</option>
						</c:forEach>
					</select>
				</div>

				<!-- Modal 新增-->
				<div class="modal fade" id="insertArticleType" tabindex="-1"
					aria-labelledby="insertArticleTypeLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="insertArticleTypeLabel">新增文章類型</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form action="/back/articletypes" method="post" id="insert">
									<label>文章類型名稱：</label> <input type="text"
										name="articleTypeName" />
								</form>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary" form="insert">新增</button>
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>


				<div class="col">
					<jsp:include page="/components/pagination.jsp"></jsp:include>
				</div>

				<div class="col">${querymsg }</div>

			</div>

			<div class="row">
				<div class="col">
					<form action="/back/forums/query" method="get">
						<input type="text" placeholder="請輸入查詢字串" name="querystring" />
						<button class="btn btn-primary" type="submit">查詢</button>
					</form>
				</div>
				<div class="col">
					<button class="btn btn-primary btn-sm" id="sortbtn">排序⮃</button>
				</div>
			</div>

			<div>
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col">討論區編號</th>
							<th scope="col">討論區名字</th>
							<th scope="col">討論區介紹</th>
							<th scope="col">討論區圖片</th>
							<th scope="col">討論區子版</th>
							<th scope="col">討論區更新</th>
							<th scope="col">討論區刪除</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="it" items="${allforums.content }">
							<tr scope="row" id="row${it.forumId }">
								<td>${it.forumId }</td>
								<td><a href="/back/forums/${it.forumId }/articles">${it.forumName }</a></td>
								<td class="text-truncate" style="max-width: 400px;" title="${it.forumIntroduction }">${it.forumIntroduction }</td>
								<td><img alt="" src="${it.forumImage }"
									class="img img-thumbnail" width="100px"></td>
								<td><select class="form-select ">
										<option selected>全部</option>
										<c:forEach var="cat" items="${it.categories}">
											<option>${cat.categoryName }</option>
										</c:forEach>
								</select> <a class="btn btn-primary"
									href="/back/categories?forumId=${it.forumId }">編輯子版</a></td>

								<td><a href="/back/updateforums/${it.forumId }"><button
											class="btn btn-primary updatebtn">更新</button></a></td>
								<td><button class="btn btn-primary deletebtn"
										value="${it.forumId}">刪除</button></td>

							</tr>
						</c:forEach>
					</tbody>

				</table>

			</div>


		</div>
	</div>
	<script>
				
		// 	刪除文章類型
		
		
		
		// 	刪除討論區
		$(".deletebtn").click(function (event) {
	        Swal.fire({
	          title: "確定要刪除討論區嗎?",
	          text: "刪除討論區會一併刪除所有該討論區的文章，確定要刪除嗎?",
	          icon: "warning",
	          showCloseButton: true,
	          showCancelButton: true,
	        }).then((rs) => {
	          if (rs.isConfirmed) {
	        	  console.log(event.target.value);
	        	  fetch("/forums/"+event.target.value,{method:"delete"})
	        	  .then((rs) => {
	                  console.log(rs);
	                  return rs.text();
	                })
	                .then(data=>{
	                	console.log(data);
	                	if (data == "Delete OK") {
	                        Swal.fire({
	                          title: "刪除成功",
	                          icon: "success",
	                        });
	                        console.log($("#row"+event.target.value));
	                        $("#row"+event.target.value).remove();
	                      } else {
	                        Swal.fire({
	                          title: "刪除失敗",
	                          icon: "error",
	                        });
	                      }
	                    });
	                
	          } else {
	            return;
	          }
	        });
	      });
		
		
		//改變排序屬性(所有)
		$("#sortbtn").click(function(e) {
			fetch("/back/forums/change/forumId", {
				method : "get"
			}).then(function() {
				//改變排序升降
				fetch("/back/forums/change", {
					method : "get"
				}).then(function() {
					if (${param.querystring!=null}) {
						
					window.location.href = "http://localhost:8080/back/forums/query?querystring=${param.querystring}"
					} else {

					window.location.href = "http://localhost:8080/back/forums?"
					}
					
				})
			})
		});
		
		
		
		
	</script>

</body>
</html>
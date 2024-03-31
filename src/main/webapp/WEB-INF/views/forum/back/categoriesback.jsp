<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>討論區子版管理系統</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>

</head>
<body>
	<div class="d-flex flex-row">

		<jsp:include page="/components/adminSidebar.jsp" />

		<div class="container">
			<div class='alert alert-success'>
				<h2 align='center'>討論區子版管理系統</h2>
			</div>


			<div class="row">


				<div class="col-2">
					<a href="/back/forums"><button type="button" class="btn btn-primary">返回</button></a>
				</div>
				<div class="col-2">
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#insertCategories">新增</button>
				</div>
				<div class="col-4">
					<form action="/categories/query" method="get">
						<input type="text" hidden name="forumId" value="${forum.forumId }" />
						<input type="text" placeholder="請輸入查詢字串" name="queryString" />
						<button class="btn btn-primary" type="submit">查詢</button>
					</form>
				</div>
				<div class="col-4">${querymsg }</div>
			</div>
			<div>
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col">主版名稱</th>
							<th scope="col">子版編號</th>
							<th scope="col">子版名字</th>
							<th scope="col">子版更新</th>
							<th scope="col">子版刪除</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="it" items="${forum.categories }">

							<tr scope="row" id="row${it.categoryId }">

								<td>${forum.forumName }</td>
								<td>${it.categoryId }</td>
								<td>${it.categoryName }</td>

								<td><button class="btn btn-primary updatebtn" type="button"
										data-bs-toggle="modal" data-bs-target="#updateCategories"
										data-id="${it.categoryId }" data-name="${it.categoryName }"
										data-info='{
									"id":"${it.categoryId }",
									"name":"${it.categoryName }"
									}'>更新</button></td>
								<td><button class="btn btn-primary deletebtn"
										value="${it.categoryId}">刪除</button></td>

							</tr>
						</c:forEach>
					</tbody>

				</table>

			</div>


		</div>


		<!-- Modal 新增-->
		<div class="modal fade" id="insertCategories" tabindex="-1"
			aria-labelledby="insertCategoriesLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="insertCategoriesLabel">新增子版</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="/categories" method="post" id="insert">
							<input type="text" hidden name="forumId"
								value="${forum.forumId }" /> <label>子版名稱：</label> <input
								type="text" name="categoryName" />
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

		<!-- Modal 更新-->
		<div class="modal fade" id="updateCategories" tabindex="-1"
			aria-labelledby="updateCategoriesLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="updateCategoriesLabel">更新子版</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="/categories/update" method="post" id="update">
							<input type="text" hidden name="forumId"
								value="${forum.forumId }" /> <label>子版編號：</label> <input
								type="text" readonly name="categoryId" id="updateId" /><br>
							<label>子版名稱：</label> <input type="text" name="categoryName" />
						</form>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" form="update">更新</button>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>

	</div>





	<script>
		
		
	
	
		// 	刪除
		$(".deletebtn").click(function (event) {
	        Swal.fire({
	          title: "確定要刪除子版嗎?",
	          text: "刪除子版會一併刪除所有該子版的文章，確定要刪除嗎?",
	          icon: "warning",
	          showCloseButton: true,
	          showCancelButton: true,
	        }).then((rs) => {
	          if (rs.isConfirmed) {
	        	  console.log(event.target.value);
	        	  fetch("/categories/"+event.target.value,{method:"delete"})
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
		
		
		//傳送categoryId至更新model中
		$(function () {
			  $("#updateCategories").on("show.bs.modal", function (e) {

				  console.log(e);
				  
				  console.log($(e.relatedTarget).data("bsToggle"));
				  
				  console.log($(e.currentTarget));
				  
			    var categoryId = $(e.relatedTarget).data("id");
			    
			    var categoryName = $(e.relatedTarget).data("name");
			    
			    var categoryInfo = $(e.relatedTarget).data("info");
			    
			    console.log(categoryInfo);
			    console.log(categoryId);
			    console.log(categoryName);
			    
			    $(e.currentTarget).find("input[name=categoryId]").val(categoryInfo.id);
			    
			    $(e.currentTarget).find("input[name=categoryName]").val(categoryInfo.name);
			  });
			});

	</script>


</body>
</html>
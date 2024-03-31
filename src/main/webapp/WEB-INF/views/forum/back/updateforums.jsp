<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新討論區</title>
<jsp:include page="/components/common_imports.jsp"></jsp:include>

<script>
	let inputFileToLoad = null;
	let image = null;
	let image2 = null;
	let img = null;
	window.addEventListener('load', function() {
		inputFileToLoad = document.getElementById("inputFileToLoad");
		image = document.getElementById("image");
		image2 = document.getElementById("image2");
		img = document.getElementById("img");
		inputFileToLoad.addEventListener('change', loadImageFileAsURL);
	});
	function loadImageFileAsURL() {

		let filesSelected = inputFileToLoad.files;
		if (filesSelected.length > 0) {
			let fileReader = new FileReader();
			let fileToLoad = filesSelected[0];

			fileReader.onload = function(fileLoadedEvent) {
				if (image != null) {
					image.value = fileLoadedEvent.target.result;
					console.log("image.value=" + image.value);
				} else {
					image2.value = fileLoadedEvent.target.result;
					console.log("image2.value=" + image2.value);
				}
				img.src = fileLoadedEvent.target.result;
			};
			fileReader.readAsDataURL(fileToLoad);
		}
	}
</script>
</head>
<body>


	<div class="d-flex flex-row">

		<jsp:include page="/components/adminSidebar.jsp" />




		<div class='container'>
			<div class='alert alert-success'>
				<h2 align='center'>更新討論區資料</h2>
			</div>
			<c:url var='insertUrl' value='/forums/${forums.forumId }.do' />
			<form method='POST' action="${insertUrl}">
				<div class='row mb-3'>
					<label class='col-sm-1'>討論區編號</label>
					<div class='col-sm-3'>
						<input type='text' class='form-control' name='forumId'
							value="${forums.forumId }" readonly />
					</div>
					<label class='col-sm-1'>討論區名</label>
					<div class='col-sm-3'>
						<input type='text' class='form-control' name='forumName'
							value="${forums.forumName }" />
					</div>
				</div>

				<div class='row mb-3'>
					<label class='col-sm-1'>討論區介紹</label>
					<div class='col-sm-3'>
						<textarea rows="5" cols="" name='forumIntroduction'
							class='form-control'>${forums.forumIntroduction }</textarea>
					</div>
				</div>


				<div class='row mb-3'>
					<label class='col-sm-1'>討論區圖片</label>
					<div class='col-sm-4	'>
						<input type='file' id='inputFileToLoad' class='form-control'
							value="" />
					</div>
					<div class='col-sm-1'>
						<c:choose>
							<c:when test='${empty forums.forumImage}'>
								<img id='img' width='60' height='80' src="${forums.forumImage }">
								<input type='hidden' id='image' name='forumImage'
									value="${forums.forumImage }">
							</c:when>
							<c:otherwise>
								<img id='img' width='60' height='80' src='${forums.forumImage}'>
								<input type='hidden' id='image2' name='forumImage'
									value="${forums.forumImage }">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class='row mb-3'>
					<div class='offset-sm-3 col-sm-3 d-grid'>
						<button type='submit' class='btn btn-primary' id="submit">提交</button>
					</div>
					<div class='col-sm-3 d-grid'>
						<a class='btn btn-outline-primary'
							href="<c:url value='/back/forums' />" role='button'>放棄更新</a>
					</div>
				</div>
			</form>
			<hr>
		</div>
	</div>
</body>
</html>
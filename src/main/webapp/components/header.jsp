<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

	
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/vendors/linericon/style.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<link rel="stylesheet" href="/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/vendors/nice-select/css/nice-select.css">
<link rel="stylesheet" href="/vendors/owl-carousel/owl.carousel.min.css">
<!-- main css -->
<!-- <link rel="stylesheet" href="/css/style.css"> -->
<!-- <link rel="stylesheet" href="/css/responsive.css"> -->

<style>
    #memberHeader {
        background-color: white; /* 將背景顏色設置為白色 */
    }
</style>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>


		<div class="container" id="memberHeader">
			<nav class="navbar navbar-expand-lg navbar-light">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a class="navbar-brand logo_h" href="index.html" ><img src="/image/公司logo2.png" style="height: 100px;width: 100px; margin-right: 140px; margin-left: 30px;" alt=""></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div  id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item active" ><a class="nav-link" href="http://localhost:8080/index" style="font-size: 20px;">首頁</a></li> 



						<li class="nav-item " v-if="logged == null"><a class="nav-link" :href="`${host}/login`" style="font-size: 20px;">會員中心</a></li>

						<li class="nav-item " v-if="logged != null"><a class="nav-link" :href="`${host}/memberCenter`" style="font-size: 20px;">會員中心</a></li>



						<li class="nav-item"><a class="nav-link" href="http://localhost:8080/searchpage" style="font-size: 20px;">找飯店</a></li>

						<li class="nav-item"><a class="nav-link" href="http://localhost:8080/html/rentcar/index.html" style="font-size: 20px;">找租車</a></li>

						<li class="nav-item submenu dropdown">
							<a href="/forums" class="nav-link"  style="font-size: 20px;">論壇中心</a>
							
						</li> 
						<li class="nav-item "><a class="nav-link" href="http://localhost:8080/html/grouptour/GroupTourUser.html" style="font-size: 20px;">旅遊</a></li>
					</ul>
				</div> 
				<div class="col-md-3 text-end" v-if="logged == null">
					<button type="button" class="btn btn-outline-primary me-2" @click="tologin">登入</button>
					<button type="button" class="btn btn-primary" @click="toRegister">註冊</button>
				</div>

				<div class="col-md-3 text-end" v-if="logged != null">
					使用者：<strong style="font-size: 17px">{{logged.userAcc}}&nbsp;&nbsp;</strong>
					<button type="button" class="btn btn-primary" @click="logout">登出</button>
				</div>
			</nav>
			
		</div>

		


		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
		<script type="module">
			import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
			import { host } from '/js/url.js';
			var jsonDataString = sessionStorage.getItem("loginStatus");
			var jsonData = JSON.parse(jsonDataString);
			const app = createApp({
				data: function () {
					return {
						logged: jsonData,
						host: host
					}
				}, created() {
					if (sessionStorage.getItem("Validation") !== null) {
						sessionStorage.removeItem("Validation");
					}
					console.log(jsonData.email)
				}
				, methods: {
					logout: function () {
						sessionStorage.clear();
						window.location.href = host + "/logoutSuccess"
					},
					tologin: function () {
						window.location.href = host + "/login"
					},
					toRegister: function () {
						window.location.href = host + "/register"
					},
					toGroupTourData: function () {
						sessionStorage.setItem("catalog", "5");
						window.location.href = host + "/html/grouptour/ShowUserGroupTour.html"
					},
				}

			})
			app.mount("#memberHeader");
		</script>
	</div>
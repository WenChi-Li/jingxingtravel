<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title></title>


            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
                crossorigin="anonymous"></script>
            <script>
                if (window.self !== window.top) {
                    window.top.location.href = window.self.location.href;
                }
            </script>
        </head>

        <body>
            <header>
                <jsp:include page="/components/header.jsp" />
            </header>


            <section id="app">

                <div class="container">
                    <div class="row">
                        <div class="col">
                            <nav aria-label="breadcrumb" class="rounded-3 p-3 mb-4"
                                style="background-color: rgb(228, 237, 234);">
                                <ol class="breadcrumb" style="margin: 10px 100px 10px 300px">
                                    <li class="breadcrumb-item active" aria-current="page"><a href="http://localhost:8080/memberCenter">個人檔案</a></li>
                                    <li class="breadcrumb-item active" aria-current="page"><a
                                            href="http://localhost:8080/memberLottery">會員抽獎</a></li>
                                    <li class="breadcrumb-item active" aria-current="page"><a
                                            href="http://localhost:8080/customerServiceUser">客服中心</a></li>
                                            <li class="breadcrumb-item active" aria-current="page"><a
                                                href="#" @click="toHotelOrder">訂單紀錄</a></li>
                                                <li class="breadcrumb-item active" aria-current="page">個人文章</li>
                                </ol>
                            </nav>
                        </div>
                    </div>

                    <div class="rounded-3 p-3 mb-4" style="background-color: rgb(228, 237, 234);">

                        <div style="width: 100%; height: 200px; overflow: hidden;">
                            <img id="imgBack" class="" style="width: 100%; height: auto;" alt="your image"
                                :src="userBackGround64">
                        </div>





                        <div class="row">
                            <div class="col-md-3 border-right">
                                <div class="d-flex flex-column align-items-center text-center p-3 py-5">

                                    <form @submit.prevent="submitBackForm">
                                        <input accept="image/*" type='file' id="imgNewBack" name="userPic"
                                            style="display: none;" />
                                        <button class="btn btn-outline-primary me-2" type="button"
                                            @click="selectBackGround">選擇背景</button>

                                        <input type="hidden" name="mid" :value="mid"> <input type="submit" value="上傳圖片"
                                            style="display: none;" id="inputUserBack">
                                        <button class="btn btn-primary profile-button" type="button"
                                            onclick="document.querySelector('input[id=inputUserBack]').click()">上傳</button>
                                        <br>
                                    </form>

                                    <img id="img" class="rounded-circle mt-5" width="150px" height="150px"
                                        :src="photoBase64" alt="your image"> <span class="font-weight-bold"><strong
                                            style="font-size: 20px">作者：{{userAcc}}</strong></span>
                                    <span class="font-weight-bold"><span
                                            style="font-size: 15px">會員等級：{{memberLv}}</span></span> <br>
                                    <form @submit.prevent="submitForm">
                                        <input accept="image/*" type='file' id="imgNew" name="userPic"
                                            style="display: none;" />
                                        <button class="btn btn-outline-primary me-2" type="button"
                                            @click="selectImage">選擇個人圖片</button>

                                        <input type="hidden" name="mid" :value="mid"> <input type="submit" value="上傳圖片"
                                            style="display: none;" id="inputUserPic">
                                        <button class="btn btn-primary profile-button" type="button"
                                            onclick="document.querySelector('input[id=inputUserPic]').click()">上傳</button>
                                        <br>
                                    </form>



                                </div>

                            </div>
                            <div class="col-md border-right" style="width: 900px; margin-top: 50px;">
                                <ul class="nav nav-tabs" id="myTab" role="tablist">
                                    <li class="nav-item" role="presentation">
                                        <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                            data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                            aria-selected="true">我的文章</button>
                                    </li>
                                    <li class="nav-item" role="presentation">
                                        <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
                                            data-bs-target="#likes" type="button" role="tab" aria-controls="likes"
                                            aria-selected="false">按讚的文章</button>
                                    </li>
                                    <li class="nav-item" role="presentation">
                                        <button class="nav-link" id="contact-tab" data-bs-toggle="tab"
                                            data-bs-target="#collections" type="button" role="tab"
                                            aria-controls="collections" aria-selected="false">收藏的文章</button>
                                    </li>
                                </ul>
                                <div class="tab-content" id="myTabContent">

                                    <div class="tab-pane fade show active" id="home" role="tabpanel"
                                        aria-labelledby="home-tab">

                                        <c:choose>
                                            <c:when test="${not empty userArticles}">
                                                <div class="row">
                                                    <c:forEach var="it" items="${userArticles }">
                                                        <div class="card col-4" style="width: 18rem;" >
                                                            <img src="${it.categories.forums.forumImage }"
                                                                class="card-img-top"
                                                                alt="${it.categories.forums.forumName }"  >
                                                            <div class="card-body"style="width: 18rem;" >
                                                                <h5 class="card-title">
                                                                    【${it.articleTypes.articleTypeName }】</h5>
                                                                <p class="card-text">${it.articleTitle }</p>
                                                                <a href="/article?articleId=${it.articleId }"
                                                                    class="btn btn-primary">詳細內容</a>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                尚未創作
                                            </c:otherwise>
                                        </c:choose>

                                    </div>


                                    <div class="tab-pane fade" id="likes" role="tabpanel" aria-labelledby="profile-tab">

                                        <c:choose>
                                            <c:when test="${not empty likeArticles}">
                                                <div class="row">
                                                    <c:forEach var="it" items="${likeArticles }">
                                                        <div class="card col-4" style="width: 18rem;">
                                                            <img src="${it.categories.forums.forumImage }"
                                                                class="card-img-top"
                                                                alt="${it.categories.forums.forumName }">
                                                            <div class="card-body">
                                                                <h5 class="card-title">
                                                                    【${it.articleTypes.articleTypeName }】</h5>
                                                                <p class="card-text">${it.articleTitle }</p>
                                                                <a href="/article?articleId=${it.articleId }"
                                                                    class="btn btn-primary">詳細內容</a>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                尚未按讚
                                            </c:otherwise>
                                        </c:choose>

                                    </div>


                                    <div class="tab-pane fade" id="collections" role="tabpanel"
                                        aria-labelledby="contact-tab">
                                        <c:choose>
                                            <c:when test="${not empty collectArticles}">
                                                <div class="row">
                                                    <c:forEach var="it" items="${collectArticles }">
                                                        <div class="card col-4" style="width: 18rem;">
                                                            <img src="${it.categories.forums.forumImage }"
                                                                class="card-img-top"
                                                                alt="${it.categories.forums.forumName }">
                                                            <div class="card-body">
                                                                <h5 class="card-title">
                                                                    【${it.articleTypes.articleTypeName }】</h5>
                                                                <p class="card-text">${it.articleTitle }</p>
                                                                <a href="/article?articleId=${it.articleId }"
                                                                    class="btn btn-primary">詳細內容</a>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                尚未收藏
                                            </c:otherwise>
                                        </c:choose>


                                    </div>
                                </div>
                            </div>

                        </div>
                        
                    </div>
                </div>
            </section>

            <footer>
                <jsp:include page="/components/footer.jsp" />
            </footer>

            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
            <script type="module">
                import { createApp, ref } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
                import { host } from '/js/url.js';
                var jsonDataString = sessionStorage.getItem("loginStatus");
                var jsonData = JSON.parse(jsonDataString);
                const app = createApp({
                    data: function () {
                        return {
                            host: host,
                            sessionData: null,
                            memberData: null,
                            memberPic: '',
                            memberName: '',
                            mid: jsonData.mid,
                            selectedCountry: '台灣',
                            selectedCity: '台北市',
                            userName: '',
                            email: '',
                            gender: '',
                            birthday: '',
                            address: '',
                            userTel: '',
                            photoBase64: '',
                            memberLv: '',
                            userAcc: '',
                            userBackGround64: null,
                        }
                    },
                    created() {
                        var jsonDataString = sessionStorage.getItem("loginStatus");
                        var jsonData = JSON.parse(jsonDataString);
                        this.sessionData = jsonData.mid
                        let request = {
                            mid: this.sessionData,
                        }
                        axios.post(host + '/findMidString.controller', request)
                            .then(response => {
                                this.memberData = response.data.showAll;
                                this.userName = this.memberData.userName;
                                this.memberName = this.memberData.userName;
                                this.email = this.memberData.email;
                                this.gender = this.memberData.gender;
                                this.birthday = this.memberData.birthday;
                                this.address = this.memberData.address;
                                this.userTel = this.memberData.userTel;
                                this.photoBase64 = this.memberData.photoBase64;
                                this.memberLv = this.memberData.memberLv;
                                this.userAcc = this.memberData.userAcc;
                                this.userBackGround64 = this.memberData.userBackGround64;
                                console.log(this.userBackGround64)
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    },
                    methods: {
                        toHotelOrder() {
                        window.location.href = host + "/hotelOrder/MemberOrder/" + this.mid
                    },
                        selectImage() {
                            document.querySelector('input[id=imgNew]').click()
                            const img = document.getElementById('img');
                            const imgNew = document.getElementById('imgNew');
                            imgNew.onchange = object => {
                                const [file] = imgNew.files
                                if (file) {
                                    img.src = URL.createObjectURL(file)
                                }
                            }
                        },

                        selectBackGround() {
                            document.querySelector('input[id=imgNewBack]').click()
                            const imgBack = document.getElementById('imgBack');
                            const imgNewBack = document.getElementById('imgNewBack');
                            imgNewBack.onchange = object => {
                                const [file] = imgNewBack.files
                                if (file) {
                                    imgBack.src = URL.createObjectURL(file)
                                }
                            }
                        },
                        submitForm() {
                            let formData = new FormData();
                            formData.append('userPic', document.querySelector('input[id=imgNew]').files[0]);
                            formData.append('mid', this.mid);
                            axios.post(host + '/uploadImage.controller', formData)
                                .then(response => {

                                    Swal.fire({
                                        title: response.data.messages,
                                        icon: "success",
                                        showConfirmButton: false,
                                        timer: 1500
                                    }).then(function () {
                                        window.location.href = host + "/authorEdit/" + this.mid
                                    })
                                })
                                .catch(error => {
                                    Swal.fire({
                                        title: "上傳失敗!",
                                        text: error,
                                        icon: "error",
                                        showConfirmButton: false,
                                        timer: 1500
                                    })
                                });
                        },
                        submitBackForm() {
                            let formData = new FormData();
                            formData.append('userBackGround', document.querySelector('input[id=imgNewBack]').files[0]);
                            formData.append('mid', this.mid);
                            axios.post(host + '/uploadBackGround.controller', formData)
                                .then(response => {

                                    Swal.fire({
                                        title: "上傳成功",
                                        icon: "success",
                                        showConfirmButton: false,
                                        timer: 1500
                                    }).then(function () {
                                        window.location.href = host + "/authorEdit/" + this.mid
                                    })
                                })
                                .catch(error => {
                                    Swal.fire({
                                        title: "上傳失敗!",
                                        text: error,
                                        icon: "error",
                                        showConfirmButton: false,
                                        timer: 1500
                                    })
                                });
                        },
                        updateUserData() {
                            let update = {
                                userName: this.userName,
                                email: this.email,
                                gender: this.gender,
                                birthday: this.birthday,
                                address: this.address,
                                userTel: this.userTel,
                                mid: this.mid,
                            }
                            axios.post(`${host}/userUpdate.controller`, update).then(response => {
                                Swal.fire({
                                    title: "更新成功!",
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    window.location.href = host + "/memberCenter"
                                })

                            })

                        },
                        UpdatePassword() {
                            Swal.fire({
                                title: '請輸入舊密碼',
                                input: 'password',
                                showCancelButton: true,
                                confirmButtonText: '確認',
                                cancelButtonText: '取消',
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    const data = result.value;
                                    let search = {
                                        mid: this.mid,
                                    }
                                    console.log(search)
                                    axios.post(`${host}/searchPwd.controller`, search).then(response => {
                                        console.log(response.data.pwd)
                                        const oldpwd = response.data.pwd
                                        if (data == response.data.pwd) {
                                            Swal.fire({
                                                title: '請輸入新密碼',
                                                input: 'password',
                                                showCancelButton: true,
                                                confirmButtonText: '確認',
                                                cancelButtonText: '取消',
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    const newData = result.value;
                                                    let newpwd = {
                                                        mid: this.mid,
                                                        userPwd: newData
                                                    }
                                                    axios.post(`${host}/updatePwd.controller`, newpwd).then(response => {
                                                        Swal.fire({
                                                            title: "更新成功!",
                                                            text: "請重新登入!!",
                                                            icon: "success",
                                                            showConfirmButton: false,
                                                            timer: 1500
                                                        }).then(function () {
                                                            sessionStorage.clear();
                                                            window.location.href = host + "/index"
                                                        })
                                                    })
                                                }
                                            })

                                        } else {
                                            Swal.fire({
                                                title: "密碼錯誤!",
                                                icon: "error",
                                                showConfirmButton: false,
                                                timer: 1500
                                            }).then(function () {
                                                window.location.href = host + "/memberCenter"
                                            })
                                        }
                                    })

                                }
                            });

                        }
                    }
                })
                app.mount("#app");



            </script>



        </body>

        </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title></title>


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
                            <ol class="breadcrumb" style="margin: 10px 100px 10px 350px">
                                <li class="breadcrumb-item active" aria-current="page">個人檔案</li>
                                <li class="breadcrumb-item active" aria-current="page"><a
                                        href="http://localhost:8080/memberLottery">會員抽獎</a></li>
                                <li class="breadcrumb-item active" aria-current="page"><a
                                        href="http://localhost:8080/customerServiceUser">客服中心</a></li>
                                        <li class="breadcrumb-item active" aria-current="page"><a
                                            href="#" @click="toHotelOrder">訂單紀錄</a></li>
                                            <li class="breadcrumb-item active" aria-current="page"><a
                                                href="#" @click="toAuthorEdit">個人文章</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="rounded-3 p-3 mb-4" style="background-color: rgb(228, 237, 234);">
                    <div class="row">
                        <div class="col-md-3 border-right">
                            <div class="d-flex flex-column align-items-center text-center p-3 py-5">

                                <img id="img" class="rounded-circle mt-5" width="150px" height="150px"
                                    :src="photoBase64" alt="your image">


                                <span class="font-weight-bold"><strong
                                        style="font-size: 20px">{{memberName}}</strong></span>
                                <span class="font-weight-bold"><span
                                        style="font-size: 15px">會員等級：{{memberLv}}</span></span>
                                <br>
                                <!-- action="/uploadImage.contorller" method="post" enctype="multipart/form-data" -->
                                <!-- @submit.prevent="submitForm" -->
                                <form @submit.prevent="submitForm">
                                    <input accept="image/*" type='file' id="imgNew" name="userPic"
                                        style="display: none;" />
                                    <button class="btn btn-outline-primary me-2" type="button"
                                        @click="selectImage">選擇圖片</button>

                                    <input type="hidden" name="mid" :value="mid">
                                    <input type="submit" value="上傳圖片" style="display: none;">
                                    <button class="btn btn-primary profile-button" type="button"
                                        onclick="document.querySelector('input[type=submit]').click()">上傳</button>
                                    <br>
                                </form>



                            </div>

                        </div>
                        <div class="col-md-5 border-right" style="border-right: solid 1px rgb(174, 173, 173);">
                            <div class="p-3 py-5">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h4 class="text-right"><strong>資料設定</strong></h4>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-6">
                                        <label class="labels">姓名：</label><input type="text" class="form-control"
                                            placeholder="first name" :value="userName" v-model="userName">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="labels">帳號：</label><input type="text" class="form-control"
                                            :value="userAcc" placeholder="surname" disabled>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-6">
                                        <label class="labels">Email：</label><input type="text" class="form-control"
                                            placeholder="enter phone number" :value="email" v-model="email">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="labels">開啟二次驗證（信箱）：

                                            <select id="validation" :value="validation" class="form-select"
                                                aria-label="Default select example" v-model="validation">
                                                <option value="0">關閉</option>
                                                <option value="1">開啟</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="col-md-12" style="margin-top: 20px;">


                                    <div class="d-md-flex justify-content-start align-items-center mb-4 py-2"
                                        style="border-bottom: 1px rgb(184, 183, 183) dashed;border-top:1px rgb(184, 183, 183) dashed ;">
                                        <h6 class="mb-0 me-4">性別：</h6>


                                        <div class="form-check form-check-inline mb-0 me-4">
                                            <input class="form-check-input" type="radio" id="gender" name="gender"
                                                value="男" v-model="gender" />
                                            <label class="form-check-label" for="maleGender">男</label>
                                        </div>

                                        <div class="form-check form-check-inline mb-0 me-4">
                                            <input class="form-check-input" type="radio" id="gender" name="gender"
                                                value="女" v-model="gender" />
                                            <label class="form-check-label" for="femaleGender">女</label>
                                        </div>



                                        <div class="form-check form-check-inline mb-0">
                                            <input class="form-check-input" type="radio" id="gender" name="gender"
                                                value="不顯示" v-model="gender" />
                                            <label class="form-check-label" for="otherGender">不顯示</label>
                                        </div>
                                    </div>


                                </div>
                                <div class="col-md-12">
                                    <label class="labels">生日：</label><input type="date" class="form-control"
                                        placeholder="enter address line 2" :value="birthday" v-model="birthday">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels"></label>
                                </div>
                                <div class="col-md-12" style="border-top: 1px dashed rgb(184, 183, 183);">
                                    <label class="labels"></label>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-4"
                                        style="width: 200px;border-right: solid rgb(218, 217, 217) 1px;">
                                        <label class="form-check-label" for="otherGender">國家：</label>
                                        <select class="select" style="width: 60px" v-model="selectedCountry">
                                            <option value="台灣">台灣</option>
                                            <option value="其他">其他</option>
                                        </select>
                                    </div>
                                    <div style="width: 200px;">
                                        <label class="form-check-label" for="otherGender">城市：</label>
                                        <select class="select" v-model="selectedCity">
                                            <option value="台北市">台北市</option>
                                            <option value="新北市">新北市</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12" style="border-top: 1px dashed rgb(184, 183, 183);">
                                    <label class="labels"></label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">地址：</label><input type="text" class="form-control"
                                        placeholder="enter email id" :value="address" v-model="address">
                                </div>

                                <div class="col-md-12">
                                    <label class="labels"></label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">手機號碼：</label><input type="text" class="form-control"
                                        placeholder="enter address line 2" :value="userTel" v-model="userTel">
                                </div>

                            </div>
                            <div class="mt-5 text-center">
                                <button class="btn btn-primary profile-button" type="button" style="width: 100px;"
                                    @click="updateUserData">存檔</button>&nbsp;&nbsp;
                                <button class="btn btn-warning profile-button" type="button" style="width: 100px;"
                                    @click="UpdatePassword">更改密碼</button>
                            </div>
                        </div>
                        <div class="col-md-4">

                            <div class="p-3 py-5">

                                <div
                                    style="display: flex; flex-direction: column; justify-content: flex-start; align-items: flex-start;">
                                    <div>
                                        <h4 class="text-right"><strong>個人倉庫</strong></h4>
                                    </div>

                                    <div>
                                        <div align="center">
                                            <div class="row">
                                                <div class="col-md-6" v-for="(prizes, index) in prizes" :key="index"
                                                    style="width: 190px;height: 190px;">
                                                    <div class="card mb-3">
                                                        <img :src="prizes.prizePicBase64" class="card-img-top"
                                                            style="width: 160px;height: 100px;">
                                                        <div class="card-body">
                                                            <p>獎品：{{ prizes.prizeName }}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
                        // prizeID: null,
                        // prizeName: null,
                        // discount: null,
                        // prizePicBase64: null,
                        prizes: null,
                        validation: null,
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
                            this.prizes = this.memberData.prizes;
                            this.validation = this.memberData.validation;
                            // this.prizeID = this.prizes.prizeID;
                            // this.prizeName = this.prizes.prizeName;
                            // this.discount = this.prizes.discount;
                            // this.prizePicBase64 = this.prizes.prizePicBase64;
                            console.log(this.prizes)
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                },
                methods: {
                    selectImage() {
                        document.querySelector('input[type=file]').click()
                        const img = document.getElementById('img');
                        const imgNew = document.getElementById('imgNew');
                        imgNew.onchange = object => {
                            const [file] = imgNew.files
                            if (file) {
                                img.src = URL.createObjectURL(file)
                            }
                        }
                    },
                    submitForm() {
                        let formData = new FormData();
                        formData.append('userPic', document.querySelector('input[type=file]').files[0]);
                        formData.append('mid', this.mid);
                        axios.post(host + '/uploadImage.controller', formData)
                            .then(response => {

                                Swal.fire({
                                    title: response.data.messages,
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    window.location.href = host + "/memberCenter"
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
                            validation: this.validation,
                            mid: this.mid,
                        }
                        console.log(update)
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

                    },
                    toHotelOrder() {
                        window.location.href = host + "/hotelOrder/MemberOrder/" + this.mid
                    },
                    toAuthorEdit() {
                        window.location.href = host + "/authorEdit/" + this.mid
                    },






                }
            })
            app.mount("#app");
            



        </script>



    </body>

    </html>
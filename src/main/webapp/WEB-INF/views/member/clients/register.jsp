<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script>
            if (window.self !== window.top) {
                window.top.location.href = window.self.location.href;
            }
            window.addEventListener('load', function () {
                document.body.style.display = 'block';
            });
        </script>
        <style>
            body {
                display: none;
            }
        </style>
    </head>

    <body>

        <header>
            <jsp:include page="/components/header.jsp" />
        </header>

        <section class="h-100" id="app2">

            <div class="container py-5 h-100">
                <h3 style="text-align: center;"><strong><em>會員註冊</em></strong></h3>
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card card-registration my-4">
                            <div class="row g-0">
                                <div class="col-xl-6 d-none d-xl-block" style="background-color: rgb(228, 237, 234);">
                                    <img src="/images/sample01.png" class="img-fluid"
                                        style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
                                </div>
                                <div class="col-xl-6" style="background-color: rgb(228, 237, 234);">
                                    <div class="card-body p-md-5 text-black">


                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="userName" name="userName" required
                                                        class="form-control form-control-lg" v-model="userName"
                                                        @blur="nameCheck" />
                                                    <label class="form-label" for="form3Example1m">*姓名</label>
                                                    <span v-if="!userNameValid && userNameValid != null"
                                                        style="color:red;">✘不能有數字!!</span>
                                                    <span v-if="userNameValid && userNameValid != null"
                                                        style="color:green;">✔格式正確</span>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="email" name="email" required
                                                        class="form-control form-control-lg" v-model="email"
                                                        @blur="emailCheck" /> <label class="form-label"
                                                        for="form3Example1n">*Email</label>
                                                    <span v-if="!emailValid && emailValid != null"
                                                        style="color:red;">✘格式錯誤!</span>
                                                    <span v-if="emailValid && emailValid != null"
                                                        style="color:green;">✔格式正確</span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="userAcc" name="userAcc" required
                                                        class="form-control form-control-lg" v-model="userAcc"
                                                        @blur="userAccCheck" maxlength="10" /> <label class="form-label"
                                                        for="form3Example1m1">*帳號</label>
                                                    <span v-if="!userAccValid && userAccValid != null"
                                                        style="color:red;">✘帳號重複</span>
                                                    <span v-if="userAccValid && userAccValid != null"
                                                        style="color:green;">✔帳號可用</span>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="date" id="birthday" name="birthday" required
                                                        class="form-control form-control-lg" v-model="birthday"
                                                        @blur="birthdayCheck" />
                                                    <label class="form-label" for="form3Example1n1">*生日</label>
                                                    <span v-if="!birthdayValid && birthdayValid != null"
                                                        style="color:red;">✘日期格式錯誤</span>
                                                    <span v-if="birthdayValid && birthdayValid != null"
                                                        style="color:green;">✔格式正確</span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="password" id="userPwd" name="userPwd" required
                                                class="form-control form-control-lg" v-model="userPwd"
                                                @blur="userPwdCheck" />
                                            <label class="form-label" for="form3Example8"
                                                style="margin-bottom: 0px;">*密碼
                                            </label>
                                            <span v-if="!userPwdValid && userPwdValid != null" style="color:red;">✘需要英數
                                                + 特殊字元!</span>
                                            <span v-if="userPwdValid && userPwdValid != null"
                                                style="color:green;">✔格式正確</span>
                                        </div>
                                        <div class="form-outline mb-4">
                                            <input type="password" id="pwdCheck" required
                                                class="form-control form-control-lg" v-model="pwdCheck"
                                                @blur="pwdDoubleCheck" /> <label class="form-label" for="form3Example8"
                                                style="margin-bottom: 0px;">*確認密碼
                                                <span v-if="!pwdCheckValid && pwdCheckValid != null"
                                                    style="color:red;">✘密碼不一致</span>
                                                <span v-if="pwdCheckValid && pwdCheckValid != null"
                                                    style="color:green;">✔密碼一致</span>
                                        </div>
                                        <div
                                            style="border-bottom: solid rgb(218, 217, 217) 1px;margin: 10px 10px 30px 10px;">
                                        </div>

                                        <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">
                                            <h6 class="mb-0 me-4">*性別:</h6>


                                            <div class="form-check form-check-inline mb-0 me-4">
                                                <input class="form-check-input" type="radio" id="gender" name="gender"
                                                    value="女" v-model="gender" />
                                                <label class="form-check-label" for="femaleGender">女</label>
                                            </div>

                                            <div class="form-check form-check-inline mb-0 me-4">
                                                <input class="form-check-input" type="radio" id="gender" name="gender"
                                                    value="男" v-model="gender" />
                                                <label class="form-check-label" for="maleGender">男</label>
                                            </div>

                                            <div class="form-check form-check-inline mb-0">
                                                <input class="form-check-input" type="radio" id="gender" name="gender"
                                                    value="不顯示" v-model="gender" />
                                                <label class="form-check-label" for="otherGender">不顯示</label>
                                            </div>
                                        </div>
                                        <div
                                            style="border-bottom: solid rgb(218, 217, 217) 1px;margin: 10px 10px 30px 10px;">
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4"
                                                style="width: 200px;border-right: solid rgb(218, 217, 217) 1px;">
                                                <label class="form-check-label" for="otherGender">國家：</label>
                                                <select class="select" style="width: 60px" v-model="selectedCountry"
                                                    class="form-select" aria-label="Default select example">
                                                    <option value="台灣">台灣</option>
                                                    <option value="其他">其他</option>
                                                </select>
                                            </div>
                                            <div style="width: 200px;">
                                                <label class="form-check-label" for="otherGender">城市：</label>
                                                <select class="select" v-model="selectedCity" class="form-select"
                                                    aria-label="Default select example">
                                                    <option value="台北市">台北市</option>
                                                    <option value="新北市">新北市</option>

                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="text" id="address" name="address"
                                                class="form-control form-control-lg" v-model="address" /> <label
                                                class="form-label" for="form3Example90">住址
                                                <span style="color: gray">（非必填）</label>
                                        </div>
                                        <div class="form-outline mb-4">
                                            <input type="text" id="userTel" name="userTel" required
                                                class="form-control form-control-lg" v-model="userTel"
                                                @blur="userTelCheck" maxlength="10"/> <label class="form-label"
                                                for="form3Example9">*手機號碼</label>
                                            <span v-if="!userTelValid && userTelValid != null"
                                                style="color:red;">✘請填寫正確手機號碼!</span>
                                            <span v-if="userTelValid && userTelValid != null"
                                                style="color:green;">✔格式正確</span>
                                        </div>
                                        <div class="d-flex justify-content-end pt-3">
                                            <button type="button" class="btn btn-light btn-lg"
                                                style="background-color: gray;" @click="clear">重設</button>
                                            <button type="submit" class="btn btn-warning btn-lg ms-2"
                                                @click="submitAll">提交</button>
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
            import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
            import { host } from '/js/url.js';
            const app = createApp({
                data: function () {
                    return {
                        userName: '',
                        userNameValid: null,
                        email: '',
                        emailValid: null,
                        userAcc: '',
                        userAccValid: null,
                        birthday: '',
                        birthdayValid: null,
                        userPwd: '',
                        userPwdValid: null,
                        pwdCheck: '',
                        pwdCheckValid: null,
                        gender: '不顯示',
                        address: null,
                        selectedCountry: '台灣',
                        selectedCity: '台北市',
                        userTel: '',
                        userTelValid: null
                    }
                }, methods: {

                    nameCheck() {
                        if (this.userName.trim() === '') {
                            this.userNameValid = null;
                            return;
                        }
                        this.userNameValid = /^[a-zA-Z\u4e00-\u9fff]{2,}$/.test(this.userName);
                    },
                    emailCheck() {
                        if (this.email.trim() === '') {
                            this.emailValid = null;
                            return;
                        }
                        this.emailValid = /^[^\s@]+@gmail\.com$/.test(this.email);
                    },
                    userAccCheck() {
                        if (this.userAcc.trim() === '') {
                            this.userAccValid = null;
                            return;
                        }
                        let request = {
                            userAcc: this.userAcc
                        }
                        axios.post(host + "/finduserAcc.controller", request).then(response => {
                            console.log(response.data)
                            if (response.data == "no") {
                                this.userAccValid = true;
                            } else {
                                this.userAccValid = false;
                            }

                        })

                    },
                    birthdayCheck() {
                        if (this.birthday.trim() === '') {
                            this.birthdayValid = null;
                            return;
                        }
                        let dateChk = /^\d{4}-\d{2}-\d{2}$/.test(this.birthday);

                        if (dateChk) {
                            let [y, m, d] = this.birthday.split("-").map(Number);
                            let date = new Date(y, m - 1, d);
                            if (
                                date.getFullYear() === y &&
                                date.getMonth() === m - 1 &&
                                date.getDate() === d
                            ) {
                                this.birthdayValid = true;
                            } else {
                                this.birthdayValid = false;
                            }
                        } else {
                            this.birthdayValid = false;
                        }
                    },
                    userPwdCheck() {
                        if (this.userPwd.trim() === '') {
                            this.userPwdValid = null;

                            return;
                        }
                        this.userPwdValid = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{4,}$/.test(this.userPwd);
                    },
                    pwdDoubleCheck() {
                        if (this.pwdCheck.trim() === '') {
                            this.pwdCheckValid = null;
                            return;
                        }
                        if (this.pwdCheck === this.userPwd) {
                            this.pwdCheckValid = true;
                        } else {
                            this.pwdCheckValid = false;
                        }
                    },
                    userTelCheck() {
                        if (this.userTel.trim() === '') {
                            this.userTelValid = null;
                            return;
                        }
                        this.userTelValid = /^09\d{8}$/.test(this.userTel);
                    },
                    clear() {
                        this.userName = null;
                        this.userNameValid = null;
                        this.email = null;
                        this.emailValid = null;
                        this.userAcc = null;
                        this.userAccValid = null;
                        this.birthday = null;
                        this.birthdayValid = null;
                        this.userPwd = null;
                        this.userPwdValid = null;
                        this.pwdCheck = null;
                        this.pwdCheckValid = null;
                        this.gender = '不顯示';
                        this.address = null;
                        this.userTel = null;
                        this.userTelValid = null;
                    },
                    submitAll() {
                        if (!this.userName || !this.email || !this.userAcc) {
                            event.preventDefault();
                            Swal.fire({
                                title: "請勿空白!",
                                icon: "error"
                            })
                        } else if (!this.userNameValid || !this.emailValid || !this.userAccValid || !this.birthdayValid || !this.userPwdValid || !this.pwdCheckValid || !this.userTelValid) {
                            Swal.fire({
                                title: "請修正錯誤!",
                                icon: "warning"
                            })
                        } else {
                            let submit = {
                                userName: this.userName,
                                email: this.email,
                                userAcc: this.userAcc,
                                birthday: this.birthday,
                                userPwd: this.userPwd,
                                gender: this.gender,
                                address: this.address,
                                userTel: this.userTel,
                                messaging: '0',
                                validation: '0',
                            }
                            axios.post(`${host}/userRegister.controller`, submit).then(() => {
                                Swal.fire({
                                    title: "註冊成功!",
                                    text: "請重新登入",
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    window.location.href = host + "/index"
                                })

                            }

                            ).catch()
                        }
                    },
                }

            })
            app.mount("#app2");

        </script>
    </body>

    </html>
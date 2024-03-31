<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>登入</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="/css/signin.css">
        <script>
            if (window.self !== window.top) {
                window.top.location.href = window.self.location.href;
            }
        </script>

    </head>

    <body class="text-center" id="app">
        <main class="form-signin">

            <a href="http://localhost:8080/index"> <img class="mb-4" src="/images/sample02.png" alt="" width="272"
                    height="157"></a>
            <h1 class="h3 mb-3 fw-normal">歡迎登入</h1>
            <div style="padding-bottom: 30px;">
                沒有帳號?<a :href="`${host}/register`">立即註冊</a>
            </div>

            <div class="form-floating">
                <input type="text" class="form-control" id="userAcc" name="userAcc" placeholder="name@example.com"
                    v-model="userAcc"> <label for="floatingInput">帳號</label>
            </div>

            <div class="form-floating">
                <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Password"
                    v-model="userPwd"> <label for="floatingPassword">密碼</label>
            </div>

            <div class="checkbox mb-3">
                <label>
                    <a :href="`${host}/forgetPassword`">忘記密碼</a>
                </label>
            </div>

            <div class="checkbox mb-3"></div>
            <button class="w-100 btn btn-lg btn-primary" type="button" @click="login">登入</button>

            <p class="mt-5 mb-3 text-muted">&copy; 2023 – 2024</p>


        </main>




        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
        <script type="module">
            import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js";
            import { host } from '/js/url.js';
            const app = createApp({
                data: function () {
                    return {
                        userAcc: '',
                        userPwd: '',
                        userData: null,
                        host: host,

                    }
                }, methods: {
                    login() {
                        let request = {
                            userAcc: this.userAcc,
                            userPwd: this.userPwd
                        }

                        axios.post("http://localhost:8080/userlogin.controller", request).then(function (response) {
                            if (response.data.loginStatus === "Admin") {
                                Swal.fire({
                                    title: "工作人員歡迎!",
                                    icon: "info",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    console.log(response.data.loginStatus)
                                    sessionStorage.setItem("catalog", "1");
                                    sessionStorage.setItem("adminData", JSON.stringify(response.data.adminData));
                                    window.location.href = "http://localhost:8080/backend"
                                })

                            } else if (response.data.loginStatus) {
                                console.log(response.data.loginStatus.validation)
                                if (response.data.loginStatus.validation == '1') {
                                    sessionStorage.setItem("Validation", JSON.stringify(response.data.loginStatus));
                                    window.location.href = "http://localhost:8080/validationPlace"
                                } else {
                                    Swal.fire({
                                        title: "登入成功!",
                                        text: response.data.messages,
                                        icon: "success",
                                        showConfirmButton: false,
                                        timer: 1500
                                    }).then(function () {
                                        sessionStorage.setItem("loginStatus", JSON.stringify(response.data.loginStatus));
                                        let hotel = sessionStorage.getItem("hotellog")
                                        console.log(hotel)
                                        if (hotel == '2') {
                                            sessionStorage.removeItem("hotellog")
                                            window.location.href = "http://localhost:8080/ShopingCar/viewCart"
                                        } else {
                                            window.location.href = "http://localhost:8080/index"
                                        }
                                    })
                                }

                            } else {
                                Swal.fire({
                                    title: response.data.messages,
                                    icon: "error",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    window.location.href = "http://localhost:8080/login"
                                })
                            }
                        }).catch();

                    }


                }
            })
            app.mount("#app");

        </script>

    </body>

    </html>
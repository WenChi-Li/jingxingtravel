<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>忘記密碼</title>
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
            <h1 class="h3 mb-3 fw-normal">請輸入帳號</h1>

            <br>
            <div class="form-floating">
                <input type="text" class="form-control" id="userValidation" name="userValidation" v-model="userAcc">
                <label for="floatingInput">帳號</label>
            </div>

            <div class="checkbox mb-3"></div>
            <button class="w-100 btn btn-lg btn-primary" type="button" @click="submit">查詢</button>

            <p class="mt-5 mb-3 text-muted">&copy; 2023–2024</p>


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
                        validationNUM: '',
                        userPwd: '',
                        userData: null,
                        host: host,
                        email: null,

                    }
                }, methods: {
                    submit() {

                        let request = {
                            userAcc: this.userAcc
                        }
                        axios.post("http://localhost:8080/finduserAccAndMid.controller", request).then((response) => {

                            if (response.data.status == 'yes') {
                                Swal.fire({
                                    title: "查有此帳號!轉至客服中心!",
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(() => {
                                    sessionStorage.setItem("forgetPwdMid", response.data.mid);

                                    window.location.href = "http://localhost:8080/forgetPwdServiceCenter"
                                })

                            } else {
                                Swal.fire({
                                    title: "查無此帳號!!",
                                    icon: "error",
                                    showConfirmButton: false,
                                    timer: 1500
                                })

                            }




                        })
                    },

                }
            })
            app.mount("#app");

        </script>

    </body>

    </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>二次驗證</title>
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
            <h1 class="h3 mb-3 fw-normal">Email驗證</h1>
            <button id="countdownButton" @click="startCountdown" class="btn btn-success">寄送驗證碼</button>
            <br>
            <br>
            <div class="form-floating">
                <input type="text" class="form-control" id="userValidation" name="userValidation"
                    v-model="userValidation"> <label for="floatingInput">二次驗證碼</label>
            </div>

            <div class="checkbox mb-3"></div>
            <button class="w-100 btn btn-lg btn-primary" type="button" @click="submit">確認</button>

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
                        userValidation: '',
                        validationNUM: '',
                        userPwd: '',
                        userData: null,
                        host: host,
                        email: null,

                    }
                }, methods: {
                    submit() {

                        if (this.userValidation == this.validationNUM) {
                            Swal.fire({
                                title: "登入成功!",
                                // text: response.data.messages,
                                icon: "success",
                                showConfirmButton: false,
                                timer: 1500
                            }).then(function () {
                                var jsonDataString = sessionStorage.getItem("Validation");
                                sessionStorage.setItem("loginStatus", jsonDataString);
                                sessionStorage.removeItem("Validation");
                                window.location.href = "http://localhost:8080/index"
                            })

                        } else {
                            Swal.fire({
                                title: '驗證錯誤!!',
                                icon: "error",
                                showConfirmButton: false,
                                timer: 1500
                            }).then(() => {
                                this.userValidation = null;
                            })
                        }



                        // let request = {
                        //     userAcc: this.userAcc,
                        //     userPwd: this.userPwd
                        // }

                        // axios.post("http://localhost:8080/userlogin.controller", request).then(function (response) {
                        //     if (response.data.loginStatus === "Admin") {
                        //         Swal.fire({
                        //             title: "工作人員歡迎!",
                        //             icon: "info",
                        //             showConfirmButton: false,
                        //             timer: 1500
                        //         }).then(function () {
                        //             console.log(response.data.loginStatus)
                        //             sessionStorage.setItem("catalog", "1");
                        //             sessionStorage.setItem("adminData", JSON.stringify(response.data.adminData));
                        //             window.location.href = "http://localhost:8080/backend"
                        //         })

                        //     } else if (response.data.loginStatus) {
                        //         console.log(response.data.loginStatus.validation)
                        //         if (response.data.loginStatus.validation == 'true') {
                        //             window.location.href = "http://localhost:8080/validationPlace"
                        //         } else {
                        //             Swal.fire({
                        //                 title: "登入成功!",
                        //                 text: response.data.messages,
                        //                 icon: "success",
                        //                 showConfirmButton: false,
                        //                 timer: 1500
                        //             }).then(function () {
                        //                 sessionStorage.setItem("loginStatus", JSON.stringify(response.data.loginStatus));

                        //                 window.location.href = "http://localhost:8080/index"
                        //             })
                        //         }

                        //     } else {
                        //         Swal.fire({
                        //             title: response.data.messages,
                        //             icon: "error",
                        //             showConfirmButton: false,
                        //             timer: 1500
                        //         }).then(function () {
                        //             window.location.href = "http://localhost:8080/login"
                        //         })
                        //     }
                        // }).catch();

                    },
                    startCountdown() {
                        var button = document.getElementById("countdownButton");
                        button.disabled = true;
                        var countdownTime = 10;
                        button.innerHTML = "已寄送: " + countdownTime;
                        var countdownInterval = setInterval(function () {
                            countdownTime--;
                            button.innerHTML = "已寄送: " + countdownTime;
                            if (countdownTime <= 0) {
                                button.disabled = false;
                                button.innerHTML = "重新寄送";
                                clearInterval(countdownInterval);
                            }
                        }, 1000);
                        var jsonDataString = sessionStorage.getItem("Validation");
                        var jsonData = JSON.parse(jsonDataString);
                        let request = {
                            userEmail: jsonData.email
                        }
                        axios.post("http://localhost:8080/sendVerificationEmail", request).then((response) => {

                            this.validationNUM = response.data.verificationCode;
                            console.log(this.validationNUM)
                        })
                    },
                }
            })
            app.mount("#app");

        </script>

    </body>

    </html>
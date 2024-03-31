<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="1; url=/index">
    <title>登出</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/signin.css">
        <script>
            if (window.self !== window.top) {
                window.top.location.href = window.self.location.href;
            }
        </script>
    
    </head>
    <body class="text-center" id="app">
        <main class="form-signin">
            
            <h1 class="h3 mb-3 fw-normal"></h1>
            <div style="padding-bottom: 30px;">
                
            </div>
    
            <div class="form-floating">
                <label for="floatingInput"></label>
            </div>
    
            <div class="form-floating">
                <h2>登出成功!!</h2>
                <img alt="" src="/images/loading_dot.gif" style="width: 50px">
                <h3>即將轉回首頁...</h3>
                <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Password"
                    v-model="userPwd" hidden> <label for="floatingPassword"></label>
            </div>
    
            <div class="checkbox mb-3">
                <label> <input type="checkbox" value="remember-me" hidden>
                  
                </label>
            </div>
    
            <div class="checkbox mb-3"></div>
            <button class="w-100 btn btn-lg btn-primary" type="button" @click="login" hidden></button>
    
            <p class="mt-5 mb-3 text-muted"></p>
    
    
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
                    login(){                    
                        let request = {
                            userAcc: this.userAcc,
                            userPwd: this.userPwd
                        }
                        console.log(request)
                        axios.post("http://localhost:8080/userlogin.controller", request).then(function (response) {
                            if(response.data.loginStatus === "Admin") {
                                Swal.fire({
                                    title: "工作人員歡迎!",
                                    icon: "info",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    console.log(response.data.loginStatus)
                                    sessionStorage.setItem("adminData", JSON.stringify(response.data.adminData));
                                    window.location.href = "http://localhost:8080/backend"                              
                                })
                                
                            }else if (response.data.loginStatus) {
                                Swal.fire({
                                    title: "登入成功!",
                                    text: response.data.messages,
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    sessionStorage.setItem("loginStatus", JSON.stringify(response.data.loginStatus));
                                    window.location.href = "http://localhost:8080/index"                               
                                })
                            }else{
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
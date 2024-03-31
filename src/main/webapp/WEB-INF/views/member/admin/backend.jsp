<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="5">
        <style type="text/css">
            .flex {
                display: flex;
            }

            .flex-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                width: 100%;
                margin: 0px 20px;
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script>
            if (window.self !== window.top) {
                window.top.location.href = window.self.location.href;
            }
        </script>
        <title>客戶資料</title>
    </head>

    <body style="background-color: rgb(237, 236, 236);">

        <div class="flex" style="height: 100%; width: 100%">
            <div>
                <jsp:include page="/components/adminSidebar.jsp" />

            </div>

            <div class="flex flex-container" style="flex-direction: column; width: 100%; margin: 0px 20px " id="app">
                <h1><strong>歡迎來到後台：</strong></h1>
                <br>
                <h4 style="color: brown;"><strong>{{ adminData.adminName }}</strong></h4>
                <div style="width: 500px ;height: 5px;border: 5px solid gray;"></div>


                <div style="margin-top: 30px;" v-if="oncall == '0'">
                    <span>
                        <h4><strong>目前沒問題!!</strong></h4>
                    </span>
                    <img src="http://localhost:8080/images/greenLight.png">
                </div>

                <div style="margin-top: 30px;" v-if="oncall == '1'">
                    <span>
                        <h4 style="color: red;"><strong>有客戶請求對話!!</strong></h4>
                    </span>
                    <span>
                        <h5 style="color: red;">問題類型：{{problemType}}</h5>
                        <img src="http://localhost:8080/images/redLight.png">
                        <br>
                        <br>
                        <button class="btn btn-primary mb-3" @click="toServiceCenter">接受請求</button>
                </div>
            </div>


            <div></div>
        </div>

        <script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
        <script type="module">
            import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
            import { host } from '/js/url.js';


            var jsonDataString = sessionStorage.getItem("adminData");
            var jsonData = JSON.parse(jsonDataString);
            console.log(jsonData)



            const app = createApp({
                data: function () {
                    return {
                        adminData: jsonData,
                        oncall: null,
                        problemType: null,
                    }
                },
                created() {
                    var jsonDataString = sessionStorage.getItem("adminData");
                    var jsonData = JSON.parse(jsonDataString);

                    let request = {
                        adminID: jsonData.adminID
                    }

                    axios.post(host + '/findAdminString.controller', request).then(response => {
                        let adminAllData = response.data.AdminShowStatus;
                        this.oncall = adminAllData.oncall;
                        this.problemType = adminAllData.problemType
                        console.log(this.oncall);
                        console.log(this.problemType);
                    })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                },
                methods: {
                    toServiceCenter() {
                        window.location.href = host + "/adminCustomerService"
                    }
                }

            })
            app.mount("#app");



        </script>




    </body>

    </html>
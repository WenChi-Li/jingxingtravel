<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <style type="text/css">
            .flex {
                display: flex;
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
            crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
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

            <div class="flex" style="flex-direction: column; width: 100%; margin: 0px 20px" id="app">
                <div align="center"
                    style="margin-top: 10px; margin-bottom: 20px; border-bottom: solid rgb(194, 188, 188) 3px">
                    <h2>
                        <strong>客服中心</strong>
                    </h2>
                </div>

                <div class="flex" style="justify-content: space-between;">

                    <div class="flex" style="width: 700px">

                        <div class="col-auto" style="width: 120px">
                            <input type="text" class="form-control" id="midQuery" name="midQuery" placeholder="會員編號"
                                v-model="midQuery">
                        </div>
                        &nbsp;
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary mb-3" @click="queryMid">查詢</button>
                        </div>

                        &nbsp;&nbsp;&nbsp;&nbsp;


                        <div class="col-auto">
                            <input type="text" class="form-control" id="query" name="query" placeholder="請輸入欲查詢的資料"
                                v-model="fuzzySearch">
                        </div>
                        &nbsp;
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary mb-3" @click="fuzzySearchMember">查詢</button>
                        </div>
                        &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary mb-3" @click="reset">清除</button>

                    </div>


                    <div>
                        <button type="button" class="btn btn-primary" @click="showRecord" data-bs-toggle="modal"
                            data-bs-target="#exampleModal">客服紀錄</button>
                    </div>
                </div>
                <section id="app">
                    <div class="container">

                        <div class="rounded-3 p-3 mb-4" style="background-color: rgb(228, 237, 234);height: 1000px">
                            <div class="flex" style="flex-direction: column; width: 100%; margin: 0px 20px" id="app">

                                <div>
                                    <br>

                                    <div align="center">
                                        <div v-if="allMember == ''">
                                            <table class="table table-hover"
                                                style="background-color: white; table-layout: fixed; width: 100%;">
                                                <tr>
                                                    <th>
                                                        <h4 style="text-align: center; color: gray;">無資料</h4>
                                            </table>
                                        </div>

                                        <table class="table table-hover" style="background-color: white; width: 100%;"
                                            v-if="allMember != ''">
                                            <tr>
                                                <th>ID
                                                <th>帳號
                                                <th>密碼
                                                <th>姓名
                                                <th>性別
                                                <th>信箱
                                                <th>地址
                                                <th>電話
                                                <th>生日
                                                <th>
                                                <th>
                                            <tr v-for="(member, index) in allMember" :key="index">
                                                <td>{{ member.mid }}</td>
                                                <td>{{ member.userAcc }}</td>
                                                <td>{{ member.userPwd }}</td>
                                                <td>{{ member.userName }}</td>
                                                <td>{{ member.gender }}</td>
                                                <td>{{ member.email }}</td>
                                                <td>{{ member.address }}</td>
                                                <td>{{ member.userTel }}</td>
                                                <td>{{ member.birthday }}</td>
                                                <td>
                                                <td><input type="text" :value="member.mid" class="memberMid" hidden>
                                                    <button type="button" class="btn btn-warning"
                                                        @click="sendEmail(index)">寄送密碼至信箱</button>
                                                </td>

                                            </tr>
                                        </table>
                                    </div>


                                    <div class="flex"
                                        style="justify-content: space-between; margin: auto; width: 500px;">

                                        <div align="center">


                                            <button @click="connect" :disabled="connected"
                                                class="btn btn-success">開啟聊天室</button>&nbsp;&nbsp;
                                            <button @click="disconnect" :disabled="!connected"
                                                class="btn btn-success">離開聊天室</button>&nbsp;&nbsp;

                                            <input type="text" v-model="messageInput" placeholder="請輸入訊息">&nbsp;&nbsp;
                                            <button @click="sendMessage" :disabled="!connected"
                                                class="btn btn-primary">傳送</button>

                                            <table>
                                                <tbody id="greetings">
                                                    <tr v-for="message in messages" :key="message">
                                                        <td>{{ message }}</td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                </section>




                <!-- 彈出視窗內容 -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="width: 700px;">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">客服紀錄：</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div align="center">
                                    <div class="row">
                                        <div class="col-md-4" style="width: 500px;">
                                            <div class="card mb-3">
                                                <div class="card-body">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>紀錄ID</th>
                                                                <th>服務對象ID</th>
                                                                <th>問題種類</th>
                                                                <th>紀錄日期</th>
                                                                <th></th>

                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr v-for="(record, index) in allServiceRecord"
                                                                :key="index">
                                                                <td>{{ record.rid }}</td>
                                                                <td>{{ record.serviceTarget }}</td>
                                                                <td>{{ record.recordType }}</td>
                                                                <td>{{ record.recordDate }}</td>

                                                                <td><button type="button" class="btn btn-warning"
                                                                        @click="details(index)">查看對話紀錄</button></td>
                                                                <td><input type="text" :value="record.rid"
                                                                        class="recordID" hidden></td>

                                                            </tr>
                                                        </tbody>
                                                    </table>
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










        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
        <script type="module">
            import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
            import { host } from '/js/url.js';
            var appInstance;
            let isConnected = false;

            var jsonDataString = sessionStorage.getItem("adminData");
            var jsonData = JSON.parse(jsonDataString);
            const app = createApp({
                data: function () {
                    return {
                        websocketUrl: "/websocketexample",
                        topic: "/topic/greetings",
                        app: "/app/hello",
                        client: null,
                        connected: false,
                        messages: [],
                        adminID: jsonData.adminID,
                        adminName: jsonData.adminName,
                        nameInput: null,
                        messageInput: null,
                        allMember: [],
                        host: host,
                        mid: '',
                        fuzzySearch: '',
                        midEdit: null,
                        midQuery: '',
                        userEmail: null,
                        userPwd: null,
                        midQuery: '',
                        allServiceRecord: [],
                        rid: null,

                    }
                },
                methods: {
                    connect() {
                        const sock = new SockJS(this.websocketUrl);
                        this.client = Stomp.over(sock);
                        this.client.connect({}, () => {
                            this.connected = true;
                            isConnected = true;
                            this.client.subscribe(this.topic, payload => {
                                this.showMessage(JSON.parse(payload.body).content);
                            });

                            let content = "自動訊息：客服人員（" + this.adminName + "）已連接!!";
                            this.client.send(this.app, {}, JSON.stringify({ 'content': content }));
                        });
                    },
                    disconnect() {


                        if (this.client !== null) {
                            let content = "自動訊息：客服已離開!!";
                            this.client.send(this.app, {}, JSON.stringify({ 'content': content }));

                            setTimeout(() => {
                                this.client.disconnect();
                                this.connected = false;
                                isConnected = false;
                                console.log("Disconnected");
                            }, 1000);
                        };
                    },
                    showMessage(message) {

                        this.messages.push(message);
                        console.log(message.content)

                    },
                    sendMessage() {
                        let content = this.adminName + ' ： ' + this.messageInput;
                        // console.log(this.mid)
                        this.client.send(this.app, {}, JSON.stringify({ 'content': content }));
                        this.messageInput = null;
                    },
                    fuzzySearchMember() {
                        if (this.fuzzySearch.trim() === '') {
                            this.fuzzySearch = null;
                            return;
                        }
                        let request = {
                            fuzzySearch: this.fuzzySearch
                        }
                        axios.post(host + '/fuzzySearch.controller', request)
                            .then(response => {
                                this.allMember = response.data.showAll;
                                this.fuzzySearch = null;
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });

                    },
                    reset() {
                        this.allMember = [];
                    },
                    sendEmail(index) {
                        Swal.fire({
                            title: '確定寄送?',
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: '確定',
                            cancelButtonText: '取消'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                let memberMid = document.querySelectorAll(".memberMid")[index];
                                this.mid = memberMid.value;
                                console.log(this.mid)
                                let request = {
                                    mid: this.mid
                                }
                                axios.post(host + '/findMidString.controller', request)
                                    .then(response => {
                                        let showAll = response.data.showAll;
                                        this.userEmail = showAll.email;
                                        this.userPwd = showAll.userPwd;
                                        let request = {
                                            userEmail: this.userEmail,
                                            userPwd: this.userPwd
                                        }
                                        axios.post(host + '/sendPasswordEmail', request)
                                        Swal.fire({
                                            title: "傳送成功!",
                                            icon: "success",
                                            showConfirmButton: false,
                                            timer: 1500
                                        })

                                    })
                                    .catch(error => {
                                        console.error('Error:', error);
                                    });
                            }


                        })



                    },
                    queryMid() {
                        if (this.midQuery.trim() === '') {
                            this.midQuery = null;
                            return;
                        }
                        let request = {
                            mid: this.midQuery
                        }
                        axios.post(host + '/findMidList.controller', request)
                            .then(response => {
                                this.allMember = response.data.showAll;
                                this.midQuery = null;
                                console.log(this.allMember)
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });

                    },
                    showRecord() {
                        axios.get(host + '/listServiceRecord.controller').then(response => {
                            this.allServiceRecord = response.data.showAll;
                            console.log(this.allServiceRecord)
                            console.log(this.allServiceRecord[0].rid)

                        })
                    },
                    details(index) {
                        let getRid = document.querySelectorAll(".recordID")[index];
                        this.rid = getRid.value;
                        console.log(this.rid)
                        sessionStorage.setItem("rid", this.rid)
                        window.location.href = host + "/adminViewRecord"

                    },




                }
            });
            appInstance = app.mount("#app");


            window.addEventListener('beforeunload', function (event) {
                if (appInstance.connected) {
                    appInstance.disconnect();
                }
            });



        </script>
    </body>

    </html>
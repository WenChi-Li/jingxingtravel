<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title></title>


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
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
                <div class="row" >
                    <div class="col">
                        <nav aria-label="breadcrumb" class="rounded-3 p-3 mb-4"
                            style="background-color: rgb(228, 237, 234);">
                            <ol class="breadcrumb" style="margin: 10px 100px 10px 350px">
                                <li class="breadcrumb-item active" aria-current="page"> <a href="http://localhost:8080/memberCenter">個人檔案</a></li>
                                <li class="breadcrumb-item active" aria-current="page"> <a href="http://localhost:8080/memberLottery">會員抽獎</a></li>
                                <li class="breadcrumb-item active" aria-current="page">客服中心</li>
                                        <li class="breadcrumb-item active" aria-current="page"><a
                                            href="#" @click="toHotelOrder">訂單紀錄</a></li>
                                            <li class="breadcrumb-item active" aria-current="page"><a
                                                href="#" @click="toAuthorEdit">個人文章</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>


                <div class="rounded-3 p-3 mb-4" style="background-color: rgb(228, 237, 234);height: 1000px">
                    <div class="flex" style="flex-direction: column; width: 100%; margin: 0px 20px" id="app">
                        <div align="center"
                            style="margin-top: 10px; margin-bottom: 20px; border-bottom: solid rgb(194, 188, 188) 3px">
                            <h2>
                                <strong>客服中心</strong>
                            </h2>
                        </div>
                        <div>
                            <div class="flex" style="justify-content: space-between;">


                                <div class="flex" style="width: 700px">


                                </div>

                                <div align="center">
                                    <div v-if="allMember == ''">
                                        <table class="table table-hover"
                                            style="background-color: white; table-layout: fixed; width: 100%;">
                                            <tr>
                                                <th>
                                                    <h4 style="text-align: center; color: gray;">查無資料</h4>
                                        </table>
                                    </div>

                                    <button @click="connect" :disabled="connected"
                                        class="btn btn-success">連接客服</button>&nbsp;&nbsp;
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

        <footer>
            <jsp:include page="/components/footer.jsp" />
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
        <script type="module">
            import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
            import { host } from '/js/url.js';
            var appInstance;
            let isConnected = false;

            let date = new Date().toISOString();
            let currentDate = new Date(date);
            currentDate.setHours(currentDate.getHours() + 8);
            let formattedDate = currentDate.toISOString();
            console.log(formattedDate);
            

            var jsonDataString = sessionStorage.getItem("loginStatus");
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
                        mid: jsonData.mid,
                        userAcc: jsonData.userAcc,
                        nameInput: null,
                        messageInput: null,
                        NowDate: formattedDate,
                        recordType: null,
                    }
                },
                methods: {
                    toAuthorEdit() {
                        window.location.href = host + "/authorEdit/" + this.mid
                    },
                    toHotelOrder() {
                        window.location.href = host + "/hotelOrder/MemberOrder/" + this.mid
                    },
                    connect() {
                        axios.get(host + '/countMessaging.controller')
                            .then(response => {
                                const connectionCount = response.data;
                                if (connectionCount === 0) {
                                    let request = {
                                        messaging: '1',
                                        mid: this.mid
                                    };


                                    Swal.fire({
                                        title: '請選擇問題種類：',
                                        input: 'select',
                                        inputOptions: {
                                            '更新個人資料': '更新個人資料',
                                            '密碼忘記': '密碼忘記',
                                            '增加抽獎次數': '增加抽獎次數',
                                            '其他': '其他',
                                        },
                                        inputPlaceholder: '請選擇',
                                        showCancelButton: true,
                                        cancelButtonText: 'Cancel',
                                        confirmButtonText: 'OK'
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            this.recordType = result.value;
                                            let requestAdmin = {
                                                oncall: '1',
                                                problemType: this.recordType,
                                            }
                                            axios.post(host + '/AdminUpdateOncall.controller', requestAdmin)



                                            console.log(this.recordType);
                                            axios.post(host + '/updateMessagingStatus.controller', request)
                                                .then(response => {
                                                    const sock = new SockJS(this.websocketUrl);
                                                    this.client = Stomp.over(sock);
                                                    this.client.connect({}, () => {
                                                        this.connected = true;
                                                        isConnected = true;
                                                        this.client.subscribe(this.topic, payload => {
                                                            this.showMessage(JSON.parse(payload.body).content);
                                                        });

                                                        let content = "自動訊息：連接客服中...請等待客服回應...";
                                                        this.client.send(this.app, {}, JSON.stringify({ 'content': content }));
                                                    });

                                                })
                                                .catch(error => {
                                                    console.error('Error updating messaging status:', error);
                                                });
                                        }

                                    });
                                } else {
                                    Swal.fire({
                                        title: "客服忙線中!請稍後...",
                                        icon: "error",
                                        showConfirmButton: false,
                                        timer: 1500
                                    });
                                }


                            })
                            .catch(error => {
                                console.error('Error fetching connection count:', error);
                            });


                    },
                    disconnect() {


                        if (this.client !== null) {
                            let content = "自動訊息：" + this.userAcc + " 已離開聊天室!!";
                            this.client.send(this.app, {}, JSON.stringify({ 'content': content }));
                            let request = {
                                messaging: '0',
                                mid: this.mid
                            };
                            axios.post(host + '/updateMessagingStatus.controller', request).then
                                (() => {
                                    setTimeout(() => {
                                        this.client.disconnect();
                                        this.connected = false;
                                        isConnected = false;
                                        console.log("Disconnected");
                                    }, 1000);
                                })
                                .catch(error => {
                                    console.error('Error updating messaging status:', error);
                                });

                        };
                        setTimeout(() => {

                            let requestAdmin = {
                                oncall: '0',
                                problemType: '沒問題',
                            }
                            axios.post(host + '/AdminUpdateOncall.controller', requestAdmin)


                            let request = {
                                serviceTarget: this.mid,
                                recordType: this.recordType,
                                recordDate: this.NowDate,
                                recordText: JSON.stringify(this.messages)
                            }
                            axios.post(host + '/insertServiceRecord.controller', request)
                            console.log(this.messages)
                        }, 1100);




                    },
                    showMessage(message) {

                        this.messages.push(message);
                        console.log(message.content)


                    },
                    sendMessage() {
                        let content = this.userAcc + ' ： ' + this.messageInput;
                        this.client.send(this.app, {}, JSON.stringify({ 'content': content }));
                        this.messageInput = null;
                    },
                }
            });
            appInstance = app.mount("#app");


            window.addEventListener('beforeunload', function (event) {
                let requestAdmin = {
                    oncall: '0',
                    problemType: '沒問題',
                }
                axios.post(host + '/AdminUpdateOncall.controller', requestAdmin)

                let request = {
                    serviceTarget: this.mid,
                    recordType: this.recordType,
                    recordDate: formattedDate,
                    recordText: JSON.stringify(this.messages)
                }
                axios.post(host + '/insertServiceRecord.controller', request)
                if (appInstance.connected) {

                    console.log(this.messages)
                    event.preventDefault();
                    appInstance.disconnect();

                }
            });



        </script>



    </body>

    </html>
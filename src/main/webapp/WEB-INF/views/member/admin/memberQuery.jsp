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
                        <strong>客戶資料</strong>
                    </h2>
                </div>
                <div>
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
                                <button type="button" class="btn btn-primary mb-3"
                                    @click="fuzzySearchMember">查詢</button>
                            </div>

                        </div>


                        <div>
                            <button type="button" class="btn btn-primary" @click="queryAll">顯示全部</button>
                        </div>
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

                        <table class="table table-hover" style="background-color: white; width: 100%;"
                            v-if="allMember != ''">
                            <tr>
                                <th>ID
                                <th>帳號
                                <th>姓名
                                <th>性別
                                <th>信箱
                                <th>地址
                                <th>電話
                                <th>
                                <th>
                            <tr v-for="(member, index) in allMember" :key="index">
                                <td>{{ member.mid }}</td>
                                <td>{{ member.userAcc }}</td>
                                <td>{{ member.userName }}</td>
                                <td>{{ member.gender }}</td>
                                <td>{{ member.email }}</td>
                                <td>{{ member.address }}</td>
                                <td>{{ member.userTel }}</td>
                                <td>
                                <td><input type="text" :value="member.mid" class="memberMid" hidden>
                                    <button type="button" class="btn btn-warning" @click="toEdit(index)">修改</button>
                                </td>

                            </tr>
                        </table>
                    </div>


                </div>
            </div>
        </div>

        <script src="https://www.unpkg.com/axios@1.6.7/dist/axios.min.js"></script>
        <script type="module">
            import { createApp } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
            import { host } from '/js/url.js';

            const app = createApp({
                data: function () {
                    return {
                        allMember: [],
                        host: host,
                        mid: '',
                        fuzzySearch: '',
                        midEdit: null,
                        midQuery: ''
                    }
                }, created() {

                    axios.get(host + '/findAll.controller')
                        .then(response => {
                            this.allMember = response.data.showAll;
                            console.log(this.allMember)
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                },
                methods: {
                    queryAll() {
                        axios.get(host + '/findAll.controller')
                            .then(response => {
                                this.allMember = response.data.showAll;
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
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
                    toEdit(index) {
                        let memberMid = document.querySelectorAll(".memberMid")[index];
                        sessionStorage.setItem("MemberData", memberMid.value);
                        window.location.href = host + "/adminEdit"
                    },



                }

            })
            app.mount("#app");



        </script>




    </body>

    </html>
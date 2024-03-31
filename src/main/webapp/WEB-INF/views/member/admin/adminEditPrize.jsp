<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title></title>
        <style type="text/css">
            .flex {
                display: flex;
            }
        </style>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">


    </head>

    <body>
        <div class="flex" style="height: 100%; width: 100%">

            <!-- width="300px" height="1000px" -->
            <div>
                <jsp:include page="/components/adminSidebar.jsp" />
            </div>





            <div id="app" style="width: 100%;">





                <div class="flex" style="flex-direction: column; margin-left: 20px; margin-right: 20px">

                    <div align="center"
                        style="margin-top: 30px; margin-bottom: 20px; border-bottom: solid rgb(194, 188, 188) 3px">
                        <h2>
                            <strong>（{{userAcc}}）客戶個人倉庫</strong>
                        </h2>
                    </div>
                    <div align="center">
                        <div v-if="prizes && prizes.length === 0">
                            <table class="table table-hover"
                                style="background-color: white; table-layout: fixed; width: 100%;">
                                <tr>
                                    <th>
                                        <h4 style="text-align: center; color: gray;">目前無獎品</h4>
                            </table>
                        </div>

                        <div class="col-md-4" style="margin: auto;">
                            <div class="p-3 py-5" style="width: 700px;">
                                <div
                                    style="display: flex; flex-direction: column; justify-content: flex-start; align-items: flex-start;">
                                    <div>
                                        <div align="center">
                                            <div class="row">
                                                <div class="col-md-3" v-for="(prizes, index) in prizes" :key="index"
                                                    style="width: 190px;height: 250px;">
                                                    <div class="card mb-3">
                                                        <img :src="prizes.prizePicBase64" class="card-img-top"
                                                            style="width: 160px;height: 100px;">
                                                        <div class="card-body">
                                                            <p>獎品：{{ prizes.prizeName }}</p>
                                                            <input type="text" :value="prizes.usid" class="getUsid"
                                                                hidden>
                                                            <button class="btn btn-danger profile-button"
                                                                @click="deletePrize(index)">刪除</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
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
                var jsonDataString = sessionStorage.getItem("MemberData");
                var jsonData = JSON.parse(jsonDataString);
                console.log(jsonData)
                const app = createApp({
                    data: function () {
                        return {
                            memberData: null,
                            userName: null,
                            email: null,
                            gender: null,
                            birthday: null,
                            address: null,
                            userTel: null,
                            photoBase64: null,
                            memberLv: null,
                            userAcc: null,
                            userPwd: null,
                            mid: null,
                            sessionData: '',
                            lotteryTimes: null,
                            prizes: [],
                        }
                    }, created() {
                        var sessionMid = sessionStorage.getItem("MemberData");
                        this.mid = sessionMid
                        let request = {
                            mid: sessionMid,
                        }
                        axios.post(host + '/findMidString.controller', request)
                            .then(response => {
                                this.memberData = response.data.showAll;
                                console.log(this.memberData)
                                this.userName = this.memberData.userName;
                                this.email = this.memberData.email;
                                this.gender = this.memberData.gender;
                                this.birthday = this.memberData.birthday;
                                this.address = this.memberData.address;
                                this.userTel = this.memberData.userTel;
                                this.photoBase64 = this.memberData.photoBase64;
                                this.memberLv = this.memberData.memberLv;
                                this.userAcc = this.memberData.userAcc;
                                this.userPwd = this.memberData.userPwd;
                                this.lotteryTimes = this.memberData.lotteryTimes;
                                this.prizes = this.memberData.prizes;
                                console.log(this.prizes)
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    },
                    methods: {
                        deletePrize(index) {

                            let getUsid = document.querySelectorAll(".getUsid")[index].value;

                            Swal.fire({
                                title: "確定刪除?",
                                icon: "warning",
                                showConfirmButton: true,
                                showCancelButton: true,
                                confirmButtonText: "確定",
                                cancelButtonText: "取消",
                            }).then(result => {
                                if (result.isConfirmed) {
                                    let deleteUsid = {
                                        usid: getUsid
                                    }
                                    axios.post(`${host}/deleteByUsid.controller`, deleteUsid).then(() => {
                                        Swal.fire({
                                            title: "刪除成功!",
                                            icon: "success",
                                            showConfirmButton: false,
                                            timer: 1500
                                        }).then(function () {
                                            window.location.href = host + "/adminEditPrize"
                                        })

                                    })
                                }
                            })
















                        },
                        gotoMemberQuery() {
                            window.location.href = host + "/memberQuery"
                        },
                        gotoAdminEditPrize() {
                            window.location.href = host + "/adminEditPrize"
                        },
                        deleteAcc() {
                            Swal.fire({
                                title: "確定刪除?",
                                icon: "warning",
                                showConfirmButton: true,
                                showCancelButton: true,
                                confirmButtonText: "確定",
                                cancelButtonText: "取消",
                            }).then(result => {
                                if (result.isConfirmed) {
                                    let request = {
                                        mid: this.mid
                                    }
                                    axios.post(host + '/adminMemberDelete.controller', request)
                                        .then(response => {
                                            Swal.fire({
                                                title: "刪除成功!",
                                                icon: "success",
                                                showConfirmButton: false,
                                                timer: 1500
                                            }).then(function () {
                                                window.location.href = host + "/memberQuery"
                                            })
                                        })
                                        .catch(error => {
                                            console.error('Error:', error);
                                        });
                                }
                            })


                        },
                        deletePic() {
                            Swal.fire({
                                title: "確定刪除?",
                                icon: "warning",
                                showConfirmButton: true,
                                showCancelButton: true,
                                confirmButtonText: "確定",
                                cancelButtonText: "取消",
                            }).then(result => {
                                if (result.isConfirmed) {
                                    let request = {
                                        mid: this.mid
                                    }
                                    axios.post(host + '/updateDefaultPic.controller', request)
                                        .then(response => {
                                            Swal.fire({
                                                title: "刪除成功!",
                                                icon: "success",
                                                showConfirmButton: false,
                                                timer: 1500
                                            }).then(function () {
                                                window.location.href = host + "/adminEdit"
                                            })
                                        })
                                        .catch(error => {
                                            console.error('Error:', error);
                                        });
                                }
                            })

                        },


                    }

                })
                app.mount("#app");



            </script>





    </body>

    </html>
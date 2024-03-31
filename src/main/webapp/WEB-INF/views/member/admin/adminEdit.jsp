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
                            <strong>客戶資料</strong>
                        </h2>
                    </div>

                    <div class="bg-light rounded-3 p-3 mb-4" style="width: 100%;">
                        <div class="d-flex flex-column align-items-center text-center">

                            <img id="img" class="rounded-circle mt-5" width="150px" height="150px" :src="photoBase64"
                                alt="your image">
                            <br>
                            <button class="btn btn-danger profile-button" @click="deletePic">刪除圖片</button>
                            <br>
                        </div>

                        <div style="width: 600px; margin: auto">
                            <div class="row mt-2">
                                <div class="col-md-6">
                                    <label class="labels">ID：</label><input type="text" class="form-control"
                                        placeholder="first name" :value="mid" disabled>
                                </div>
                                <div class="col-md-6">
                                    <label class="labels">姓名：</label><input type="text" class="form-control"
                                        :value="userName" placeholder="surname" v-model="userName">
                                </div>
                            </div>
                            <br>
                            <div class="row mt-2">
 
                                <div class="d-md-flex justify-content-start align-items-center mb-4 py-2"
                                style="border-bottom: 1px rgb(184, 183, 183) dashed;border-top:1px rgb(184, 183, 183) dashed ;">
                                <h6 class="mb-0 me-4">性別：</h6>


                                <div class="form-check form-check-inline mb-0 me-4">
                                    <input class="form-check-input" type="radio" id="gender" name="gender"
                                        value="男" v-model="gender" />
                                    <label class="form-check-label" for="maleGender">男</label>
                                </div>

                                <div class="form-check form-check-inline mb-0 me-4">
                                    <input class="form-check-input" type="radio" id="gender" name="gender"
                                        value="女" v-model="gender" />
                                    <label class="form-check-label" for="femaleGender">女</label>
                                </div>



                                <div class="form-check form-check-inline mb-0">
                                    <input class="form-check-input" type="radio" id="gender" name="gender"
                                        value="不顯示" v-model="gender" />
                                    <label class="form-check-label" for="otherGender">不顯示</label>
                                </div>
                            </div>
                                <div class="col-md-6">
                                    <label class="labels">會員等級：</label><br>
                                    <select id="memberLv" :value="memberLv" class="form-select"
                                        aria-label="Default select example" v-model="memberLv">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label class="labels">樂透次數：</label><input type="number" class="form-control"
                                    :value="lotteryTimes" placeholder="surname" v-model="lotteryTimes">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <label class="labels">帳號：</label><input type="text" class="form-control"
                                        placeholder="enter phone number" :value="userAcc" v-model="userAcc">
                                </div>
                                <div class="col-md-6">
                                    <label class="labels">個人倉庫：</label><button class="btn btn-outline-primary me-2 form-control" @click="gotoAdminEditPrize">前往</button>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels"></label><input type="text" class="form-control"
                                        placeholder="enter address line 1" hidden>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">密碼：</label><input type="text" class="form-control"
                                        placeholder="enter address line 1" :value="userPwd" v-model="userPwd">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels"></label><input type="text" class="form-control"
                                        placeholder="enter address line 1" hidden>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Email：</label><input type="text" class="form-control"
                                        placeholder="enter address line 2" :value="email" v-model="email">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels"></label><input type="text" class="form-control"
                                        placeholder="enter address line 1" hidden>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">電話：</label><input type="text" class="form-control"
                                        placeholder="enter address line 2" :value="userTel" v-model="userTel">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels"></label><input type="text" class="form-control"
                                        placeholder="enter address line 1" hidden>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">生日：</label><input type="date" class="form-control"
                                        placeholder="enter address line 2" :value="birthday" v-model="birthday">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels"></label><input type="text" class="form-control"
                                        placeholder="enter address line 1" hidden>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">地址：</label><input type="text" class="form-control"
                                        placeholder="enter address line 2" :value="address" v-model="address">
                                </div>
                                <div class="mt-5 text-center">
                                    <button class="btn btn-primary profile-button" type="button" style="width: 100px;"
                                        @click="gotoMemberQuery">返回</button>&nbsp;&nbsp;
                                    <button class="btn btn-secondary profile-button" type="button" style="width: 100px;"
                                        @click="updateData">更新資料</button>&nbsp;&nbsp;
                                    <button class="btn btn-danger profile-button" type="button" style="width: 100px;"
                                        @click="deleteAcc">刪除帳號</button>
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
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                },
                methods: {
                    updateData() {
                        let update = {
                            userAcc: this.userAcc,
                            userPwd: this.userPwd,
                            userName: this.userName,
                            email: this.email,
                            gender: this.gender,
                            address: this.address,
                            memberLv: this.memberLv,
                            userTel: this.userTel,
                            birthday: this.birthday,
                            lotteryTimes: this.lotteryTimes,
                            mid: this.mid,
                        }
                        axios.post(`${host}/adminMemberUpdate.controller`, update).then(() => {
                            Swal.fire({
                                title: "更新成功!",
                                icon: "success",
                                showConfirmButton: false,
                                timer: 1500
                            }).then(function () {
                                window.location.href = host + "/adminEdit"
                            })

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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <style type="text/css">
            .flex {
                display: flex;
            }

            td {
                vertical-align: middle;
                text-align: center;
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
            crossorigin="anonymous"></script>
        <script>
            if (window.self !== window.top) {
                window.top.location.href = window.self.location.href;
            }
        </script>
        <title>平台管理系統</title>
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
                        <strong>平台管理系統</strong>
                    </h2>
                </div>
                <div>
                    <div class="flex" style="justify-content: space-between;">


                        <div class="flex" style="width: 700px">


                            &nbsp;
                            <div class="col-auto">
                                <button type="button" class="btn btn-primary mb-3"
                                    @click="toPrizePoolEdit">更改獎池中獎率</button>
                            </div>

                            &nbsp;&nbsp;&nbsp;&nbsp;


                            &nbsp;
                            <div class="col-auto">
                                <button type="button" class="btn btn-primary mb-3"
                                    @click="toAdminPrizeEdit">獎品資料</button>
                            </div>

                        </div>
                    </div>

                    <div align="center">
                        <div class="row">
                            <div class="col-md-4" v-for="(allPrize, index) in allPrize" :key="index">
                                <div class="card mb-3">
<br>
                                    <h5 class="card-title">獎池位置：{{ allPrize.prizepoolID }}</h5>
                                    <img :src="allPrize.prizePicBase64" class="card-img-top" alt="..."
                                        v-if="allPrize.prizePicBase64 != null" style="width: 370px;height: 250px;">
                                    <span v-if="allPrize.prizePicBase64 == null"
                                        style="width: 370px;height: 250px;"></span>
                                    <div class="card-body">
                                        <h5 class="card-title">獎品：{{ allPrize.prizeName }}</h5>
                                        <p style="color: gray; font-size: 15px;">一個區域代表中獎率20%</p>
                                        <!-- <h5 class="card-title">獎品名稱：{{ allPrize.prizeName }}</h5>
                                        <h5 class="card-title">折扣：{{ allPrize.discount }}</h5> -->
                                        <input type="text" :value="allPrize.prizepoolID" class="prizepoolID" hidden>
                                        <button type="button" class="btn btn-primary" @click="listPrize(index)"
                                            data-bs-toggle="modal" data-bs-target="#exampleModal">更改品項</button>&nbsp;
                                        <button type="button" class="btn btn-warning"
                                            @click="Reset(index)">重置成"無中獎"</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- 彈出視窗內容 -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">獎池位置：{{ prizeID }}</h5>
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
                                                                    <th>ID</th>
                                                                    <th>帳號</th>
                                                                    <th>折價</th>
                                                                    <th>圖片</th>
                                                                    <th></th>

                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr v-for="(allPrize2, index) in listallPrize"
                                                                    :key="index">
                                                                    <td>{{ allPrize2.prizeID }}</td>
                                                                    <td>{{ allPrize2.prizeName }}</td>
                                                                    <td>{{ allPrize2.discount }}</td>
                                                                    <td v-if="allPrize2.prizePicBase64 != null"><img
                                                                            :src="allPrize2.prizePicBase64"
                                                                            style="width: 100px;height: 50px;"></td>
                                                                    <td v-if="allPrize2.prizePicBase64 == null"
                                                                        style="width: 100px;height: 50px;">
                                                                    <td><button type="button" class="btn btn-warning"
                                                                            @click="change(index)">修改</button></td>
                                                                    <td><input type="text" :value="allPrize2.prizeID"
                                                                            class="prizeID" hidden></td>

                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" @click="submitForm">存檔</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
                                </div>
                            </div>
                        </div>
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
                        allPrize: [],
                        listallPrize: [],
                        cardPrize: null,
                        prizeID: null,
                        prizeName: null,
                        discount: null,
                        prizePicBase64: null,
                        host: host,
                        prizePic: null,
                        prizepoolID: null,

                    }
                },
                created() {
                    axios.post(host + '/listPrizePool.controller')
                        .then(response => {
                            this.allPrize = response.data.prizepool;
                            console.log("this.allPrize" + this.allPrize)
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                }
                , methods: {
                    listPrize(index) {
                        let getPrizePoolID = document.querySelectorAll(".prizepoolID")[index];
                        this.prizepoolID = getPrizePoolID.value;
                        axios.post(host + '/listPrize.controller')
                            .then(response => {
                                this.listallPrize = response.data.prizepool;
                                console.log(this.listallPrize)
                                this.$forceUpdate();

                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    },
                    toAdminPrizeEdit() {
                        window.location.href = host + '/adminPrizeEdit'
                    },
                    selectImage() {
                        document.querySelector('input[type=file]').click()
                        const img = document.getElementById('img');
                        const imgNew = document.getElementById('imgNew');
                        imgNew.onchange = object => {
                            const [file] = imgNew.files
                            if (file) {
                                img.src = URL.createObjectURL(file)
                            }
                        }
                    },
                    change(index) {
                        let getPrizeID = document.querySelectorAll(".prizeID")[index];
                        this.prizeID = getPrizeID.value;
                        let request = {
                            newPrizeID: this.prizeID,
                            prizepoolID: this.prizepoolID
                        }
                        axios.post(host + '/changePrizePool.controller', request).then(response => {
                            window.location.href = host + "/adminPrizePoolEdit"
                        })
                    },
                    submitForm() {
                        let formData = new FormData();
                        if (document.querySelector('input[type=file]').files[0] != undefined) {
                            formData.append('prizePic', document.querySelector('input[type=file]').files[0]);
                        }

                        formData.append('prizeID', this.prizeID);
                        formData.append('prizeName', this.prizeName);
                        formData.append('discount', this.discount);
                        console.log(formData)
                        console.log("getAll" + formData.getAll)
                        axios.post(host + '/uploadPrizePool.controller', formData)
                            .then(response => {

                                Swal.fire({
                                    title: response.data.messages,
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    window.location.href = host + "/adminPrizeEdit"
                                })
                            })
                            .catch(error => {
                                Swal.fire({
                                    title: "更新失敗!",
                                    text: error,
                                    icon: "error",
                                    showConfirmButton: false,
                                    timer: 1500
                                })
                            });
                    },
                    Reset(index) {
                        let getPrizePoolID = document.querySelectorAll(".prizepoolID")[index];
                        this.prizepoolID = getPrizePoolID.value;
                        let request = {
                            newPrizeID: '1',
                            prizepoolID: this.prizepoolID
                        }
                        axios.post(host + '/changePrizePool.controller', request).then(response => {
                            window.location.href = host + "/adminPrizePoolEdit"
                        })
                    },



                }

            })
            app.mount("#app");



        </script>




    </body>

    </html>
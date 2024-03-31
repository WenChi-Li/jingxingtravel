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


                        <div>
                            <button type="button" class="btn btn-primary" @click="setnull" data-bs-toggle="modal"
                                data-bs-target="#exampleModalNew">新增獎品</button>
                        </div>
                    </div>

                    <div align="center">
                        <div class="row">
                            <div class="col-md-4" v-for="(allPrize, index) in allPrize" :key="index">
                                <div class="card mb-3">
                                    <img :src="allPrize.prizePicBase64" class="card-img-top" alt="..."
                                        v-if="allPrize.prizePicBase64 != null" style="width: 370px;height: 250px;">
                                    <span v-if="allPrize.prizePicBase64 == null"
                                        style="width: 370px;height: 250px;"></span>
                                    <div class="card-body">
                                        <h5 class="card-title">ID：{{ allPrize.prizeID }}</h5>
                                        <h5 class="card-title">獎品名稱：{{ allPrize.prizeName }}</h5>
                                        <h5 class="card-title">折扣：{{ allPrize.discount }}</h5>
                                        <input type="text" :value="allPrize.prizeID" class="prizeID" hidden>
                                        <button type="button" class="btn btn-warning" @click="findPrize(index)"
                                            data-bs-toggle="modal" data-bs-target="#exampleModal">修改</button>&nbsp;
                                        <button type="button" class="btn btn-danger" @click="Delete(index)">刪除</button>
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
                                            <div class="col-md-4">
                                                <div class="card mb-3">
                                                    <img :src="prizePicBase64" class="card-img-top" alt="..."
                                                        style="width: 200px;height: 200px;" id="img">
                                                    <div class="card-body">
                                                        <!-- <form @submit.prevent="submitForm"> -->
                                                        獎池位置：<input type="text" :value="prizeID" v-model="prizeID">
                                                        獎池名稱：<input type="text" :value="prizeName" v-model="prizeName">
                                                        折扣：<input type="text" :value="discount" v-model="discount">

                                                        <input accept="image/*" type='file' id="imgNew" name="userPic"
                                                            style="display: none;" />
                                                        <button class="btn btn-outline-primary me-2" type="button"
                                                            @click="selectImage">選擇圖片</button>
                                                        <br>
                                                        <!-- </form> -->
                                                        <!-- <h5 class="card-title">獎池位置：{{ prizeID }}</h5>
                                                        <h5 class="card-title">獎池名稱：{{ prizeName }}</h5>
                                                        <h5 class="card-title">折扣：{{ discount }}</h5> -->
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


                    <!-- 新增的彈出視窗內容 -->
                    <div class="modal fade" id="exampleModalNew" tabindex="-1" aria-labelledby="exampleModalLabelNew"
                        aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabelNew"></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div align="center">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="card mb-3">
                                                    <img :src="" class="card-img-top" alt="..."
                                                        style="width: 200px;height: 200px;" id="imgforNew">
                                                    <div class="card-body">
                                                        <!-- <form @submit.prevent="RegisterNew"> -->
                                                        獎池名稱：<input type="text" v-model="prizeName">
                                                        折扣：<input type="text" v-model="discount">

                                                        <input accept="image/*" type='file' id="imgNewforNew"
                                                            name="userPic" style="display: none;" />
                                                        <button class="btn btn-outline-primary me-2" type="button"
                                                            @click="selectImageforNew">選擇圖片</button>
                                                        <br>
                                                        <!-- </form> -->
                                                        <!-- <h5 class="card-title">獎池位置：{{ prizeID }}</h5>
                                                      <h5 class="card-title">獎池名稱：{{ prizeName }}</h5>
                                                      <h5 class="card-title">折扣：{{ discount }}</h5> -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" @click="RegisterNew">存檔</button>
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
                        cardPrize: null,
                        prizeID: null,
                        prizeName: null,
                        discount: null,
                        prizePicBase64: null,
                        host: host,
                        prizePic: null,

                    }
                },
                created() {
                    axios.post(host + '/listPrize.controller')
                        .then(response => {
                            this.allPrize = response.data.prizepool;
                            console.log(this.allPrize)

                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                }
                , methods: {
                    findPrize(index) {
                        let getPrizeID = document.querySelectorAll(".prizeID")[index];
                        this.prizeID = getPrizeID.value;
                        let request = {
                            prizeID: this.prizeID
                        }
                        axios.post(host + '/findPrizeString.controller', request)
                            .then(response => {
                                this.cardPrize = response.data.prizepool;
                                // console.log(this.cardPrize)
                                this.prizeID = this.cardPrize.prizeID
                                this.prizeName = this.cardPrize.prizeName
                                this.discount = this.cardPrize.discount
                                this.prizePicBase64 = this.cardPrize.prizePicBase64
                                this.prizePic = this.cardPrize.prizePic
                                // console.log(this.prizePicBase64)

                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    },
                    setnull() {
                        this.prizeName = null;
                        this.discount = null;
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
                    selectImageforNew() {
                        document.querySelector('#imgNewforNew').click()
                        const imgforNew = document.getElementById('imgforNew');
                        const imgNewforNew = document.getElementById('imgNewforNew');
                        imgNewforNew.onchange = object => {
                            const [fileforNew] = imgNewforNew.files
                            if (fileforNew) {
                                imgforNew.src = URL.createObjectURL(fileforNew)
                            }
                        }
                    },
                    toPrizePoolEdit() {
                        window.location.href = host + '/adminPrizePoolEdit'
                    },
                    submitForm() {
                        let formData = new FormData();
                        console.log(this.prizeID);
                        console.log(this.prizeName);
                        console.log("prizepic" + this.prizePic);
                        console.log("document" + document.querySelector('input[type=file]').files[0]);
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
                                    title: "修改成功",
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
                    Delete(index) {
                        Swal.fire({
                            title: '確定執行操作?',
                            text: "這個操作無法恢復!",
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonColor: '#3085d6',
                            cancelButtonColor: '#d33',
                            confirmButtonText: '是的!我確定!',
                            cancelButtonText: '取消'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                let getPrizeID = document.querySelectorAll(".prizeID")[index];
                                this.prizeID = getPrizeID.value;
                                if (this.prizeID != 1) {
                                    let request = {
                                        prizeID: this.prizeID
                                    }
                                    axios.post(host + '/deletePrize.controller', request)
                                        .then(response => {
                                            Swal.fire({
                                                title: "刪除成功!!",
                                                icon: "success",
                                                showConfirmButton: false,
                                                timer: 1500
                                            }).then(function () {
                                                window.location.href = host + "/adminPrizeEdit"
                                            })
                                        })
                                        .catch(error => {
                                            Swal.fire({
                                                title: "請先刪除獎池內容!!",
                                                text: error,
                                                icon: "error",
                                                showConfirmButton: false,
                                                timer: 1500
                                            })
                                        });
                                } else {
                                    Swal.fire({
                                        title: "這個不能刪除!!",
                                        icon: "error",
                                        showConfirmButton: false,
                                        timer: 1500
                                    })
                                }
                            }
                        });

                    },
                    RegisterNew() {
                        let formData = new FormData();
                        console.log(this.prizeID);
                        console.log(this.prizeName);
                        console.log("prizepic" + this.prizePic);
                        console.log("document" + document.querySelector('#imgNewforNew').files[0]);
                        if (document.querySelector('#imgNewforNew').files[0] != undefined) {
                            formData.append('prizePic', document.querySelector('#imgNewforNew').files[0]);
                        }
                        formData.append('prizeName', this.prizeName);
                        formData.append('discount', this.discount);
                        axios.post(host + '/newPrize.controller', formData)
                            .then(response => {

                                Swal.fire({
                                    title: "新增成功!",
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



                }

            })
            app.mount("#app");



        </script>




    </body>

    </html>
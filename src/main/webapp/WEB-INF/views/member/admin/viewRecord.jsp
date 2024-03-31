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
                            <strong>對話紀錄</strong>
                        </h2>
                    </div>



                    <div style="width: 600px; margin: auto">
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">紀錄ID：</label><input type="text" class="form-control"
                                    placeholder="first name" :value="allRecord.rid" disabled>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">對方會員ID：</label><input type="text" class="form-control"
                                    :value="allRecord.serviceTarget" placeholder="surname" disabled>
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">問題類別：</label><input type="text" class="form-control"
                                    placeholder="first name" :value="allRecord.recordType" disabled>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">日期時間：</label><input type="text" class="form-control"
                                    placeholder="first name" :value="allRecord.recordDate" disabled>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels">對話紀錄：</label>
                                <textarea class="form-control" placeholder="Enter phone number" readonly rows="20"
                                    cols="50">{{recordText}}</textarea>
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                            <button class="btn btn-primary profile-button" type="button" style="width: 100px;"
                                @click="gotoCustomerService">返回</button>&nbsp;&nbsp;

                            <button class="btn btn-danger profile-button" type="button" style="width: 100px;"
                                @click="deleteRecord">刪除</button>
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
            var jsonData = sessionStorage.getItem("rid");
            console.log(jsonData)
            const app = createApp({
                data: function () {
                    return {
                        allRecord: null,
                        recordText: null,
                        rid: null,
                    }
                }, created() {
                    var jsonData = sessionStorage.getItem("rid");
                    this.rid = jsonData
                    let request = {
                        rid: jsonData,
                    }
                    axios.post(host + '/findRid.controller', request)
                        .then(response => {
                            this.allRecord = response.data.allRecord
                            var str = this.allRecord.recordText
                            var parts = str.split(",");
                            console.log(str)
                            console.log(parts)
                            this.recordText = parts
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                },
                methods: {
                    gotoCustomerService() {

                        window.location.href = host + "/adminCustomerService"

                    },
                    deleteRecord() {
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
                                    rid: this.rid
                                }
                                axios.post(host + '/deleteRid.controller', request)
                                    .then(response => {
                                        Swal.fire({
                                            title: "刪除成功!",
                                            icon: "success",
                                            showConfirmButton: false,
                                            timer: 1500
                                        }).then(function () {
                                            window.location.href = host + "/adminCustomerService"
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
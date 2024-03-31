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
                <div class="flex" style="justify-content: space-between;">


                    <div class="flex" style="width: 700px">


                        &nbsp;
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary mb-3" @click="toPrizePoolEdit">更改獎池中獎率</button>
                        </div>

                        &nbsp;&nbsp;&nbsp;&nbsp;


                        &nbsp;
                        <div class="col-auto">
                            <button type="button" class="btn btn-primary mb-3" @click="toAdminPrizeEdit">獎品資料</button>
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

                    }
                }
                , methods: {
                    toAdminPrizeEdit() {
                        window.location.href = host + '/adminPrizeEdit'
                    },
                    toPrizePoolEdit() {
                        window.location.href = host + '/adminPrizePoolEdit'
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
                }

            })
            app.mount("#app");



        </script>




    </body>

    </html>
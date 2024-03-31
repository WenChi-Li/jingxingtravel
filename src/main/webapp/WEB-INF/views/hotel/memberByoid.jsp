<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title></title>


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script>
            if (window.self !== window.top) {
                window.top.location.href = window.self.location.href;
            }
        </script>
            <script> function viewOrder(orderId) {
                // 將 orderId 傳遞到 viewOrder.jsp
                window.location.href = '/hotelOrder/viewOrder/' + orderId;
            }</script>

    </head>

    <body>
        <header>
            <jsp:include page="/components/header.jsp" />
        </header>


        <section id="app">

            <div class="container">


                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb" class="rounded-3 p-3 mb-4"
                            style="background-color: rgb(228, 237, 234);">
                            <ol class="breadcrumb" style="margin: 10px 100px 10px 400px">
                                <li class="breadcrumb-item active" aria-current="page">個人檔案</li>
                                <li class="breadcrumb-item active" aria-current="page"><a
                                        href="http://localhost:8080/memberLottery">會員抽獎</a></li>
                                <li class="breadcrumb-item active" aria-current="page"><a
                                        href="http://localhost:8080/customerServiceUser">客服中心</a></li>
                                        <li class="breadcrumb-item active" aria-current="page"><a
                                            href="#" @click="toHotelOrder">訂單紀錄</a></li>
                                            <li class="breadcrumb-item active" aria-current="page"><a
                                                href="#" @click="toAuthorEdit">個人文章</a></li>
                                                
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="rounded-3 p-3 mb-4" style="background-color: rgb(228, 237, 234);height: 700px;">
                    <div class="row">
                        <div class="col-md-3 border-right">
                            

                        </div>
                        
                        <div >

                          
                    <div align="center">
                        <div style="margin: 50px;">
                        <h2>訂單詳細</h2></div>
                        <table border="1" class="table text-center">
                            <tr>

                                <th>房型名稱</th>
                                <th>入住日期</th>
                                <th>退房日期</th>
                                <th>訂購房間數</th>
                                <th>價格</th>
                                <th>入住天數</th>

                            </tr>
                            <c:forEach items="${orderItem}" var="item">
                                <tr>
                                    <td>
                                        <c:out value="${item.rooms.roomTypeName}" />
                                    </td>

                                    <td>
                                        <c:out value="${item.checkinDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.checkoutDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.bookedRooms}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.price}" />
                                    </td>
                                    <td>
                                        <c:out value="${item.stayDays}" />
                                    </td>

                                </tr>
                            </c:forEach>
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
            import { createApp, ref } from "https://www.unpkg.com/vue@3.4.19/dist/vue.esm-browser.prod.js"
            import { host } from '/js/url.js';
            var jsonDataString = sessionStorage.getItem("loginStatus");
            var jsonData = JSON.parse(jsonDataString);
            const app = createApp({
                data: function () {
                    return {
                        host: host,
                        sessionData: null,
                        memberData: null,
                        memberPic: '',
                        memberName: '',
                        mid: jsonData.mid,
                        selectedCountry: '台灣',
                        selectedCity: '台北市',
                        userName: '',
                        email: '',
                        gender: '',
                        birthday: '',
                        address: '',
                        userTel: '',
                        photoBase64: '',
                        memberLv: '',
                        userAcc: '',
                        // prizeID: null,
                        // prizeName: null,
                        // discount: null,
                        // prizePicBase64: null,
                        prizes: null,
                        validation: null,
                    }
                },
                created() {
                    var jsonDataString = sessionStorage.getItem("loginStatus");
                    var jsonData = JSON.parse(jsonDataString);



                    this.sessionData = jsonData.mid
                    let request = {
                        mid: this.sessionData,
                    }
                    axios.post(host + '/findMidString.controller', request)
                        .then(response => {
                            this.memberData = response.data.showAll;
                            this.userName = this.memberData.userName;
                            this.memberName = this.memberData.userName;
                            this.email = this.memberData.email;
                            this.gender = this.memberData.gender;
                            this.birthday = this.memberData.birthday;
                            this.address = this.memberData.address;
                            this.userTel = this.memberData.userTel;
                            this.photoBase64 = this.memberData.photoBase64;
                            this.memberLv = this.memberData.memberLv;
                            this.userAcc = this.memberData.userAcc;
                            this.prizes = this.memberData.prizes;
                            this.validation = this.memberData.validation;
                            // this.prizeID = this.prizes.prizeID;
                            // this.prizeName = this.prizes.prizeName;
                            // this.discount = this.prizes.discount;
                            // this.prizePicBase64 = this.prizes.prizePicBase64;
                            console.log(this.prizes)
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });
                },
                methods: {
                    toAuthorEdit() {
                        window.location.href = host + "/authorEdit/" + this.mid
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
                    submitForm() {
                        let formData = new FormData();
                        formData.append('userPic', document.querySelector('input[type=file]').files[0]);
                        formData.append('mid', this.mid);
                        axios.post(host + '/uploadImage.controller', formData)
                            .then(response => {

                                Swal.fire({
                                    title: response.data.messages,
                                    icon: "success",
                                    showConfirmButton: false,
                                    timer: 1500
                                }).then(function () {
                                    window.location.href = host + "/memberCenter"
                                })
                            })
                            .catch(error => {
                                Swal.fire({
                                    title: "上傳失敗!",
                                    text: error,
                                    icon: "error",
                                    showConfirmButton: false,
                                    timer: 1500
                                })
                            });
                    },
                    updateUserData() {
                        let update = {
                            userName: this.userName,
                            email: this.email,
                            gender: this.gender,
                            birthday: this.birthday,
                            address: this.address,
                            userTel: this.userTel,
                            validation: this.validation,
                            mid: this.mid,
                        }
                        console.log(update)
                        axios.post(`${host}/userUpdate.controller`, update).then(response => {
                            Swal.fire({
                                title: "更新成功!",
                                icon: "success",
                                showConfirmButton: false,
                                timer: 1500
                            }).then(function () {
                                window.location.href = host + "/memberCenter"
                            })

                        })

                    },
                    UpdatePassword() {
                        Swal.fire({
                            title: '請輸入舊密碼',
                            input: 'password',
                            showCancelButton: true,
                            confirmButtonText: '確認',
                            cancelButtonText: '取消',
                        }).then((result) => {
                            if (result.isConfirmed) {
                                const data = result.value;
                                let search = {
                                    mid: this.mid,
                                }
                                console.log(search)
                                axios.post(`${host}/searchPwd.controller`, search).then(response => {
                                    console.log(response.data.pwd)
                                    const oldpwd = response.data.pwd
                                    if (data == response.data.pwd) {
                                        Swal.fire({
                                            title: '請輸入新密碼',
                                            input: 'password',
                                            showCancelButton: true,
                                            confirmButtonText: '確認',
                                            cancelButtonText: '取消',
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                const newData = result.value;
                                                let newpwd = {
                                                    mid: this.mid,
                                                    userPwd: newData
                                                }
                                                axios.post(`${host}/updatePwd.controller`, newpwd).then(response => {
                                                    Swal.fire({
                                                        title: "更新成功!",
                                                        text: "請重新登入!!",
                                                        icon: "success",
                                                        showConfirmButton: false,
                                                        timer: 1500
                                                    }).then(function () {
                                                        sessionStorage.clear();
                                                        window.location.href = host + "/index"
                                                    })
                                                })
                                            }
                                        })

                                    } else {
                                        Swal.fire({
                                            title: "密碼錯誤!",
                                            icon: "error",
                                            showConfirmButton: false,
                                            timer: 1500
                                        }).then(function () {
                                            window.location.href = host + "/memberCenter"
                                        })
                                    }
                                })

                            }
                        });

                    },
                    toHotelOrder() {
                        window.location.href = host + "/hotelOrder/MemberOrder/" + this.mid
                    },






                }
            })
            app.mount("#app");
            



        </script>



    </body>

    </html>

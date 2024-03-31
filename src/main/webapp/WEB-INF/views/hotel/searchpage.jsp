<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Hotel Search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
        <link href="/css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.9.1.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
 
        <script>$(function () {
                $("#from").datepicker({
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 3,

                    onClose: function (selectedDate) {
                        $("#to").datepicker("option", "minDate", selectedDate
                        );

                    }
                });
                $("#to").datepicker({
                    defaultDate: "+1w",
                    changeMonth: true,
                    numberOfMonths: 3,

                    onClose: function (selectedDate) {
                        $("#from").datepicker("option", "maxDate", selectedDate);
                    }
                });
            });
            // 在提交表單前檢查輸入欄位是否為空，如果是空的就設置為 null
            document.getElementById("myForm").addEventListener("submit", function (event) {
                var hotelNameInput = document.getElementById("hotelName");
                if (hotelNameInput.value.trim() === "") {
                    hotelNameInput.value = null;
                }
            });
        </script>
    </head>


    <body style="background-image: url(/images/back.jpg);background-size: cover;">
        <header class="header_area">

            <jsp:include page="/components/header.jsp" />

        </header>

        <section class="banner_area">
            <div class="booking_table d_flex align-items-center justify-content-center"
                style="width: 500px;margin: auto;">
                <div class="hotel_booking_area position">
                    <div class="container">
                        <div class="hotel_booking_table">
                            <div class="col-md-3">
                            </div>
                            <form action="/hotel/search" method="post">
                                <div class="col-md-9">
                                    <div class="boking_table" style="margin: auto;">
                                        <div class="row">
                                            <div class="col-md-4" style="margin: auto;">
                                                <h2 style="color: aliceblue;">Book<br> Your Room</h2>
                                                <div class="book_tabel_item" style="width: 200px;margin: auto;">
                                                    <div class="form-group">
                                                        <input type="text" name="hotelName" class="form-control"
                                                            placeholder="飯店名稱">
                                                    </div>
                                                    <div class="form-group">
                                                        <input type="text" class="form-control" id="from"
                                                            placeholder="入住日期">
                                                    </div>
                                                    <div class="form-group">

                                                        <input type="text" class="form-control" id="to"
                                                            placeholder="退房日期">
                                                    </div>
                                                    <div class="form-group">
                                                        <input type="number" name="roomType" class="form-control"
                                                            placeholder="入住人數">
                                                    </div>
                                                    <div class="form-group">
                                                        <select class="form-select" name="area">
                                                            <option value="" selected disabled>區域</option>
                                                            <option value="台北">台北</option>
                                                            <option value="台中">台中</option>
                                                            <option value="台南">台南</option>
                                                            <option value="高雄">高雄</option>
                                                            <option value="桃園">桃園</option>
                                                        </select>
                                                    </div>

                                                    <button type="submit"> 搜尋</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>


                    </div>
                </div>
            </div>
        </section>

        <footer class="footer-area section_gap" style="background-color: rgba(255, 255, 255, 0.722);">
            <jsp:include page="/components/footer.jsp" />
        </footer>


        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js"
            integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
            integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
            crossorigin="anonymous"></script>

    </body>

    </html>
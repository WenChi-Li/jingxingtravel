<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>旅館一覽 (Hotel List)</title>
            <style>
                .align-left {
                    text-align: left;
                }

                .add-button {
                    margin-right: 900px;
                    /* 調整新增按鈕的左側間距 */
                }

                .flex {
                    display: flex;
                }
            </style>

            <script>
                function deleteHotel(hotelId) {
                    if (confirm("確定要刪除該旅館嗎？")) {
                        window.location.href = "/hotel/delete/" + hotelId;
                    }
                }
                function viewHotel(hotelId) {
                    // 將 hotelId 傳遞到 viewHotel.jsp
                    window.location.href = '/rooms/viewRooms/' + hotelId;
                }
            </script>
        </head>

        <body>


            <div class="flex" style="height: 100%; width: 100%">
                <div>
                    <jsp:include page="/components/adminSidebar.jsp" />
                </div>



                <div class="flex" style="flex-direction: column; width: 100%; margin: 0px 20px" id="app">

                    <div align="center">
                        <div style="margin: 50px;">
                        <h2>旅館一覽 (Hotel List)</h2></div>
                        <a href="http://localhost:8080/addNewHotel" class="add-button">
                            <button class="btn btn-primary" >新增旅館資料 (Add
                                Hotel)</button></a>
                        <table border="1" class="table text-center">
                            <tr style="background-color: #a8fefa">
                                <th>旅館代碼</th>
                                <th>旅館名稱</th>
                                <th>地址</th>
                                <th>電話</th>
                                <th>區域</th>
                                <th>游泳池</th>
                                <th>早餐</th>
                                <th>浴缸</th>
                                <th>健身房</th>
                                <th>停車場</th>
                                <th>圖片</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${hotels}" var="hotel">
                                <tr>
                                    <td>
                                        <c:out value="${hotel.hotelId}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.hotelName}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.address}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.phone}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.area}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.swimmingpool}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.breakfast}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.tub}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.gym}" />
                                    </td>
                                    <td>
                                        <c:out value="${hotel.parking}" />
                                    </td>
                                    <td><img src="${hotel.hotelImage}" alt="Hotel Image" style="width: 150px;"></td>

                                    <td>
                                        <a href="/modifyHotel/${hotel.hotelId}"> <button>修改</button></a>
                                        <button onclick="deleteHotel(${hotel.hotelId})">刪除</button>
                                        <button onclick="viewHotel(${hotel.hotelId})">檢視</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </body>

        </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Hotel Rooms</title>
            <script> var hotelId = "${hotelId}";
                function deleteRoom(roomId) {
                    if (confirm("確定要刪除該房間嗎？")) {
                        window.location.href = "/rooms/delete/" + roomId + "?hotelId=" + hotelId;
                    }
                }
                function goBacktoHotel() {
                    window.location.href = "/hotel";
                }
            </script>
            <!-- <style type="text/css">
                .flex {
                    display: flex;
                }
            </style> -->
        </head>

        <body>

            <div class="d-flex flex-row">
                <jsp:include page="/components/adminSidebar.jsp" />
                <div class="container">

            <div class="flex" style="height: 100%; width: 100%">

                
                <div style="margin: 0px 200px 200px 00px;">
                    
                    
                    <div style="margin: 50px;">
                        <h1>Hotel Rooms</h1>
                    </div>
                    <a href="/addRoom/${hotelId}"><button>新增房間資料 (Add Room)</button></a>
                    <button onclick="goBacktoHotel()">回首頁</button>
                    <table border="1" class="table text-center">
                        <tr>

                            <th>房間代碼</th>
                            <th>房型名稱</th>
                            <th>房型</th>
                            <th>剩餘房間</th>
                            <th>旅館代碼</th>
                            <th>價格</th>
                            <th>房間圖片</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="rooms" items="${rooms}">
                            <tr>

                                <td>
                                    <c:out value="${rooms.roomId}" />
                                </td>
                                <td>
                                    <c:out value="${rooms.roomTypeName}" />
                                </td>
                                <td>
                                    <c:out value="${rooms.roomType}" />
                                </td>
                                <td>
                                    <c:out value="${rooms.roomsNumber}" />
                                </td>
                                <td>
                                    <c:out value="${hotelId}" />
                                </td>
                                <td>
                                    <c:out value="${rooms.price}" />
                                </td>
                                <td><img src="${rooms.roomsImage}" alt="Room Image" style="width: 100px;">
                                </td>

                                <td><a href="/modifyRoom/${rooms.roomId}?hotelId=${hotelId}"><button> 修改</button></a>
                                    <button onclick="deleteRoom(${rooms.roomId})">刪除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>




                </div>


            </div>
        </body>

        </html>
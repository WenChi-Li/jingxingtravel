<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>修改房間資料 (Modify Room)</title>
        <!-- 如果需要引入 CSS 或 JavaScript 檔案，請在這裡添加相關連結 -->
        <style type="text/css">
            .flex {
                display: flex;
            }
        </style>
    </head>

    <body>


        <div class="flex" style="height: 100%; width: 100%">
            <div>
                <jsp:include page="/components/adminSidebar.jsp" />
            </div>



            <div class="flex" style="flex-direction: column; width: 100%; margin: 0px 20px" id="app">

                <div align="center">
                    <div style="margin: 50px;"> 
                    <h2>修改房間資料 (Modify Room)</h2></div>
                    <form action="/rooms/update/${room.roomId}" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="hotelId" value="${hotelId}">
                        <table>
                            <tr>
                                <td>房間代碼：</td>
                                <td><input type="text" name="roomId" value="${room.roomId}" readonly></td>
                            </tr>
                            <tr>
                                <td>房間類型名稱：</td>
                                <td><input type="text" name="roomTypeName" value="${room.roomTypeName}"></td>
                            </tr>
                            <tr>
                                <td>房間類型：</td>
                                <td><input type="text" name="roomType" value="${room.roomType}"></td>
                            </tr>
                            <tr>
                                <td>房間數量：</td>
                                <td><input type="text" name="roomsNumber" value="${room.roomsNumber}"></td>
                            </tr>
                            <tr>
                                <td>旅館代碼：</td>
                                <td><input type="text" name="hotelId" value="${hotelId}" readonly></td>
                            </tr>
                            <tr>
                                <td>價格：</td>
                                <td><input type="text" name="price" value="${room.price}"></td>
                            </tr>
                            <tr>
                                <td>房間圖片：</td>
                                <td><input type="file" name="multipartFile" value="${multipartFile}"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="更新"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>

    </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Add Room</title>
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
                    <h1>Add Room</h1></div>
                    <form action="/rooms/addNewRoom/${hotelId}" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="hotelId" name="hotelId" value="${hotelId}">
                        <table>
                            <tr>
                                <td>房型名稱:</td>
                                <td><input type="text" id="roomTypeName" name="roomTypeName"></td>
                            </tr>
                            <tr>
                                <td>房型:</td>
                                <td><input type="text" id="roomType" name="roomType"></td>
                            </tr>
                            <tr>
                                <td>剩餘房間:</td>
                                <td><input type="text" id="roomsNumber" name="roomsNumber"></td>
                            </tr>
                            <tr>
                                <td>價格:</td>
                                <td><input type="text" id="price" name="price"></td>
                            </tr>
                            <tr>
                                <td>房間圖片:</td>
                                <td><input type="file" name="multipartFile"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="Submit"></td>
                            </tr>
                        </table>
                    </form>
                </div>

            </div>
        </div>
    </body>

    </html>
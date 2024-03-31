<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>修改旅館資料 (Modify Hotel)</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
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
                    <h2>修改旅館資料 (Modify Hotel)</h2></div>
                    <form action="/hotel/update/${hotel.hotelId}" method="post" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>旅館代碼：</td>
                                <td><input type="text" name="hotelId" value="${hotel.hotelId}" readonly></td>
                            </tr>
                            <tr>
                                <td>旅館名稱：</td>
                                <td><input type="text" name="hotelName" value="${hotel.hotelName}"></td>
                            </tr>
                            <tr>
                                <td>地址：</td>
                                <td><input type="text" name="address" value="${hotel.address}"></td>
                            </tr>
                            <tr>
                                <td>電話：</td>
                                <td><input type="text" name="phone" value="${hotel.phone}"></td>
                            </tr>
                            <tr>
                                <td>區域：</td>
                                <td><select name="area">
                                        <option value="台北" ${hotel.area=='台北' ? 'selected' : '' }>台北</option>
                                        <option value="台中" ${hotel.area=='台中' ? 'selected' : '' }>台中</option>
                                        <option value="台南" ${hotel.area=='台南' ? 'selected' : '' }>台南</option>
                                        <option value="高雄" ${hotel.area=='高雄' ? 'selected' : '' }>高雄</option>
                                        <option value="桃園" ${hotel.area=='桃園' ? 'selected' : '' }>桃園</option>
                                        <!-- 根據需求添加更多選項 --></td>
                            </tr>
                            <tr>
                                <td>游泳池：</td>
                                <td>
                                    <input type="radio" name="swimmingpool" value="true" ${hotel.swimmingpool=='true'
                                        ? 'checked' : '' }> 是
                                    <input type="radio" name="swimmingpool" value="false" ${hotel.swimmingpool=='false'
                                        ? 'checked' : '' }> 否
                                </td>
                            </tr>
                            <tr>
                                <td>早餐：</td>
                                <td>
                                    <input type="radio" name="breakfast" value="true" ${hotel.breakfast=='true'
                                        ? 'checked' : '' }> 是
                                    <input type="radio" name="breakfast" value="false" ${hotel.breakfast=='false'
                                        ? 'checked' : '' }> 否
                                </td>
                            </tr>
                            <tr>
                                <td>浴缸：</td>
                                <td>
                                    <input type="radio" name="tub" value="true" ${hotel.tub=='true' ? 'checked' : '' }>
                                    是
                                    <input type="radio" name="tub" value="false" ${hotel.tub=='false' ? 'checked' : ''
                                        }> 否
                                </td>
                            </tr>
                            <tr>
                                <td>健身房：</td>
                                <td>
                                    <input type="radio" name="gym" value="true" ${hotel.gym=='true' ? 'checked' : '' }>
                                    是
                                    <input type="radio" name="gym" value="false" ${hotel.gym=='false' ? 'checked' : ''
                                        }> 否
                                </td>
                            </tr>
                            <tr>
                                <td>停車場：</td>
                                <td>
                                    <input type="radio" name="parking" value="true" ${hotel.parking=='true' ? 'checked'
                                        : '' }> 是
                                    <input type="radio" name="parking" value="false" ${hotel.parking=='false'
                                        ? 'checked' : '' }> 否
                                </td>
                            </tr>
                            <tr>
                                <td>圖片：</td>
                                <td><input type="file" name="multipartFile" value="${multipartFile}"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="更新"></td>

                            </tr>
                            <tr>
                                <button onclick="goBacktoHotel()">回首頁</button>
                            </tr>
                        </table>
                    </form>
                </div>




            </div>



        </div>
    </body>

    </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>新增旅館資料 (Add Hotel)</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
        <style type="text/css">
            .flex {
                display: flex;
            }
            
        </style>
        <script>function goBacktoHotel() {
            window.location.href = "/hotel";
        }</script>
    </head>

    <body>

        <div class="flex" style="height: 100%; width: 100%">

            <div>
                <jsp:include page="/components/adminSidebar.jsp" />
            </div>

            <div class="flex" style="flex-direction: column; width: 100%; margin: 0px 20px" id="app">


                <div align="center">
                    <div style="margin: 50px;">
                    <h2>新增旅館資料 (Add Hotel)</h2></div>
                    <form action="/hotel/create" method="post" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>旅館名稱：</td>
                                <td><input type="text" name="hotelName"></td>
                            </tr>
                            <tr>
                                <td>地址：</td>
                                <td><input type="text" name="address"></td>
                            </tr>
                            <tr>
                                <td>電話：</td>
                                <td><input type="text" name="phone" placeholder="例如：07-5503243"></td>
                            </tr>


                            <tr>
                                <td>區域：</td>
                                <td><select name="area">
                                        <option value="台北">台北</option>
                                        <option value="台中">台中</option>
                                        <option value="台南">台南</option>
                                        <option value="高雄">高雄</option>
                                        <option value="桃園">桃園</option>
                                        <!-- 根據需求添加更多選項 -->
                                    </select></td>
                            </tr>
                            <tr>
                                <td>游泳池：</td>
                                <td>
                                    <input type="radio" name="swimmingpool" value="true"> 是
                                    <input type="radio" name="swimmingpool" value="false" checked> 否
                                </td>
                            </tr>
                            <tr>
                                <td>早餐：</td>
                                <td>
                                    <input type="radio" name="breakfast" value="true"> 是
                                    <input type="radio" name="breakfast" value="false" checked> 否
                                </td>
                            </tr>
                            <tr>
                                <td>浴缸：</td>
                                <td>
                                    <input type="radio" name="tub" value="true"> 是
                                    <input type="radio" name="tub" value="false" checked> 否
                                </td>
                            </tr>
                            <tr>
                                <td>健身房：</td>
                                <td>
                                    <input type="radio" name="gym" value="true"> 是
                                    <input type="radio" name="gym" value="false" checked> 否
                                </td>
                            </tr>
                            <tr>
                                <td>停車場：</td>
                                <td>
                                    <input type="radio" name="parking" value="true"> 是
                                    <input type="radio" name="parking" value="false" checked> 否
                                </td>
                            </tr>
                            <tr>
                                <td>圖片：</td>
                                <td><input type="file" name="multipartFile"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="新增">
                                </td>
                            </tr>
                        </table>
                    </form>
                    <button onclick="goBacktoHotel()">回首頁</button>
                </div>

            </div>



        </div>



    </body>

    </html>
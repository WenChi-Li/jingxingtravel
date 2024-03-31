<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>訂單一覽 (Order List)</title>
            <style>
                .align-left {
                    text-align: left;
                }

                .flex {
                    display: flex;
                }
            </style>
            <script>
                function deleteOrder(orderId) {
                    if (confirm("確定要刪除該訂單嗎？")) {
                        window.location.href = "/order/delete/" + orderId;
                    }
                }
                function viewOrder(orderId) {
                    // 將 orderId 傳遞到 viewOrder.jsp
                    window.location.href = '/hotelOrder/viewOrder/' + orderId;
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
                        <h2>訂單一覽 (Order List)</h2></div>
                        <table border="1"class="table text-center">
                            <tr style="background-color: #a8fefa">
                                <th>訂單編號</th>
                                <th>會員ID</th>
                                <th>總價</th>
                                <th>訂單日期</th>
                                <th>訂單狀態</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${allOrders}" var="order">
                                <tr>
                                    <td>
                                        <c:out value="${order.orderid}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.mid}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.totalPrice}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.orderDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.orderStatus}" />
                                    </td>
                                    <td>
                                        <button onclick="deleteOrder(${order.orderid})">刪除</button>
                                        <button onclick="viewOrder(${order.orderid})">檢視</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </body>

        </html>
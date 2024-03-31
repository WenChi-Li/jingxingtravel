<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>訂單詳細</title>
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
        </body>

        </html>
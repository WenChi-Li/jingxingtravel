<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>orderConfirm</title>
</head>
<body>
    <div class="container" align="center">
        <h1 class="mt-5">訂單確認頁面</h1>
        <form id="orderForm" action="/ordersProcess" method="post">
            
            <table border="1" class="align-left">
                <thead>
                    <tr>
                        <td>會員編號:</td>
                        <td><c:out value="${member.mid}" /></td>
                        <input type="hidden" name="mid" value="${member.mid}" />
                        <td>會員名稱:</td>
                        <td><c:out value="${member.userName}" /></td>
                        <input type="hidden" name="userName" value="${member.userName}" />
                    </tr>
                 
                    
                    <tr>
                        <th scope="col">房間名稱</th>
                        <th scope="col">入住日期</th>
                        <th scope="col">退房日期</th>
                        <th scope="col">下訂房間數</th>
                        <th scope="col">價格</th>
                        <th scope="col">入住天數</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${car}" var="item" varStatus="vs">
                        <tr>
                            <td><c:out value="${item.value.rooms.roomTypeName}" /></td>
                            <input type="hidden" name="roomId" value="${item.value.rooms.roomId}">
                            <td><c:out value="${item.value.startDate}" /></td>
                            <td><c:out value="${item.value.endDate}" /></td>
                            <td><c:out value="${item.value.orderNum}" /></td>
                            <td><c:out value="${item.value.rooms.price}" /></td>
                            <td><c:out value="${item.value.numberOfDays}" />天</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4"></td>
                        <td><strong>總價:</strong></td>
                        <td>
                            <c:set var="totalPrice" value="0" />
                            <c:forEach items="${car}" var="item">
                                <c:set var="roomsprice" value="${item.value.numberOfDays * item.value.orderNum * item.value.rooms.price}" />
                                <c:set var="totalPrice" value="${totalPrice + roomsprice}" />
                            </c:forEach>
                            <c:out value="${totalPrice}" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <button type="submit">提交訂單</button>
        </form>
    </div>
</body>
</html>

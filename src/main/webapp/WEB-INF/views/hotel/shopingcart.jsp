<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購物車</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
   <style> .align-left {
    text-align: left;
}</style>
   <script>
        function deleteCartItem(roomId) {
            if (confirm("確定要刪除該房間嗎？")) {
                window.location.href = "/ShopingCar/deleteCartItem/" + roomId;
            }
        }

        function toggleForm(formId) {
            var form = document.getElementById(formId);
            if (form.style.display === 'none') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }

        function calculateStayDays() {
            var startDateInput = document.getElementById("startDate");
            var endDateInput = document.getElementById("endDate");
            var startDate = new Date(startDateInput.value);
            var endDate = new Date(endDateInput.value);
            var differenceInTime = endDate.getTime() - startDate.getTime();
            var differenceInDays = differenceInTime / (1000 * 3600 * 24);
            document.getElementById("stayDays").innerText = differenceInDays + " 天";
        }
        function goBack() {
        window.history.back();
    }
    function clearCart() {
    if (confirm("確定要清空購物車嗎？")) {
        window.location.href = "/ShopingCar/clearCart";
        
    }
}
    function submitCart() {
        let status = sessionStorage.getItem("loginStatus")
        status=JSON.parse(status);
        console.log(status)
    if (confirm("確定要送出購物車嗎？")) {
        // let status = sessionStorage.getItem("loginStatus")
        // console.log(status)
        if(status == null){
                    sessionStorage.setItem("hotellog", "2");
                    window.location.href = "/login";
        }else{
                     window.location.href = "/ShopingCar/submitCart?mid="+status.mid;

        }
    }
    }
    </script>
</head>
<body>
    <div class="container" align="center">
        <h1 class="mt-5">購物車</h1>
        <table border="1" class="align-left">
            <thead>
                <tr>
                    <th scope="col">房間名稱</th>
                    <th scope="col">入住日期</th>
                    <th scope="col">退房日期</th>
                    <th scope="col">下訂房間數</th>
                    <th scope="col">價格</th>
                <th scope="col">入住天數</th>
                <th scope="col">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${car}" var="item" varStatus="vs">
                <tr>
                    <td><c:out value="${item.value.rooms.roomTypeName}" /></td>
                    <td><c:out value="${item.value.startDate}" /></td>
                    <td><c:out value="${item.value.endDate}" /></td>
                    <td><c:out value="${item.value.orderNum}" /></td>
                    <td><c:out value="${item.value.rooms.price}" /></td>
                    <td><c:out value="${item.value.numberOfDays}" />天</td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick="toggleForm('modifyForm${vs.index}')">修改</button>
                        <form id="modifyForm${vs.index}" action="/ShopingCar/modifyDate/${item.value.rooms.roomId}" method="post" style="display: none;">
                            <input type="date" id="startDate" name="startDate" placeholder="新的入住日期 (YYYY-MM-DD)" onchange="calculateStayDays()">
                            <input type="date" id="endDate" name="endDate" placeholder="新的退房日期 (YYYY-MM-DD)" onchange="calculateStayDays()">
                            <input type="int" name="orderNum" placeholder="訂房數量">
                            <button type="submit">確定</button>
                        </form>
                        <button type="button" class="btn btn-danger" onclick="deleteCartItem(<c:out value='${item.key}' />)">刪除</button>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5"></td>
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
    <div class="button-container" style="margin-right: 200px;">
        <button class="btn btn-primary" onclick="goBack()">繼續下訂</button>
        <button class="btn btn-danger" onclick="clearCart()">清空購物車</button>
         
        <button class="btn btn-success" onclick="submitCart()">送出購物車</button>


 


    </div>

</div>

</body>
</html>

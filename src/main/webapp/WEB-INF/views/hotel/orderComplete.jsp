<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>感謝您的訂購</title>
    <style>
        .container {
            text-align: center;
            margin-top: 50px;
        }
        .button-container {
            margin-top: 20px;
        }
        .btn-primary {
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>感謝您的訂購！</h1>
        <p>親愛的會員，</p>
        <p>我們已收到您的訂單，非常感謝您的支持。</p>
        <p>如有任何疑問或需要幫助，請隨時與我們聯繫。</p>
        <p>再次感謝您的訂購！</p>
        <div class="button-container">
            <button class="btn btn-primary" onclick="window.location.href='/index'">回到首頁</button>
        </div>
    </div>
</body>
</html>

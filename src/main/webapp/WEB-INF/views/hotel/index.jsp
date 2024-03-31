<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <!-- <link href="../../../resources/static/styles.css" rel="stylesheet" /> -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">\
        <style>
            .card {
                height: 400px; /* 設置卡片的高度 */
            }
        </style>
        
    </head>



    <body style="background-image: url(/images/hotelback.jpg);background-size: cover;">

        <header>
            <jsp:include page="/components/header.jsp" />
        </header>
        <!-- Navigation-->
        
        <!-- Header-->
        
        <!-- Section-->
        <section class="py-5">
            <div class="container">
                <div class="row">
                    <c:forEach items="${hotels}" var="hotel">
                        <div class="col-md-3 mb-5">
                            <div class="card">
                                <!-- Product image-->
                                <img class="card-img-top w-100" src="${hotel.hotelImage}" alt="Hotel Image"  />
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder">${hotel.hotelName}</h5>
                                        <!-- Product address-->
                                        <span class="text-muted">${hotel.address}</span>

                                    </div>
                                </div>
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="/roomscheak/${hotel.hotelId}">詳細房型</a></div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <footer class="footer-area section_gap" style="background-color: rgba(255, 255, 255, 0.722);">
            <jsp:include page="/components/footer.jsp" />
        </footer>
        
       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        
    </body>
</html>

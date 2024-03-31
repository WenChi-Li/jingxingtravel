<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
       
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <!-- <link href="css/styles.css" rel="stylesheet" /> -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.9.1.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">

     
    </head>
    <body>
   
        <header>
            <jsp:include page="/components/header.jsp" />
        </header>

        <!-- Page Content-->
        <div class="container px-4 px-lg-5">
            <!-- Heading Row-->
            <div class="row gx-4 gx-lg-5 align-items-center my-5">
                <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0" src="${hotel.hotelImage}" alt="Hotel Image" /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">${hotel.hotelName}</h1>
                    <h2 class="text-muted">酒店設施：</h2>
                    <ul>
                        <li id="swimmingpool">游泳池</li>
                        <li id="breakfast">早餐</li>
                        <li id="tub">浴缸</li>
                        <li id="gym">健身房</li>
                        <li id="parking">停車場</li>
                    </ul><br><br><br><br><br><br><br>
                    <span class="text-muted">地址:${hotel.address}</span> <br>
                    <span class="text-muted">電話:${hotel.phone}</span>

                </div>
            </div>
            
           
            <!-- Content Row-->
            <section class="py-5">
                <div class="container">
                    <div class="row">
                        <c:forEach items="${rooms}" var="room">
    <form action="/ShopingCar/addtocart/${room.roomId}" method="get">
        <div class="col-md-4 mb-5">
            <div class="card">
                <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">剩餘房間:${room.roomsNumber}</div>
                <!-- Product image-->
                <img class="card-img-top w-100" src="${room.roomsImage}" alt="Hotel Image"  />
                <!-- Product details-->
                <div class="card-body p-4">
                    <div class="text-center">
                        <!-- Product name-->
                        <h5 class="fw-bolder">${room.roomTypeName}</h5>
                        <!-- Product address-->
                        <span class="text-muted">入住人數:${room.roomType}</span>
                        <span> <br></span>
                        <!-- Product price-->
                        <span class="text-muted">價格:${room.price}</span>
                        <div class="text-center">
                            <input type="date" class="form-control datepicker" id="startDate" name="startDate" placeholder="入住日期"  />
                        </div>
                        <div class="text-center mt-3">
                            <input type="date" class="form-control datepicker" id="endDate" name="endDate" placeholder="退房日期" />
                        </div>
                    </div>
                </div>
                <!-- Product actions-->
                <div class="text-center">
                    <button type="submit" class="btn btn-outline-dark mt-auto">訂房</button>
                </div>
            </div>
        </div>
    </form>
</c:forEach>

                    </div>
                </div>
            </section>
  
        </div>

        <footer class="footer-area section_gap" style="background-color: rgba(106, 100, 100, 0.278);">
            <jsp:include page="/components/footer.jsp" />
        </footer>



        <!-- Footer-->
       
        <!-- Bootstrap core JS-->
        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <!-- <script src="js/scripts.js"></script> --> -->


        <script>
        let swimmingpool= document.getElementById('swimmingpool');
        console.log(swimmingpool)
        
        swimmingpool.hidden = ${hotel.swimmingpool ? false : true};
        
        let breakfast= document.getElementById('breakfast');
        console.log(breakfast)
        
        breakfast.hidden = ${hotel.breakfast ? false : true};
        let tub= document.getElementById('tub');
        console.log(tub)
        
        tub.hidden = ${hotel.tub ? false : true};
        
        let gym= document.getElementById('gym');
        console.log(gym)
        
        gym.hidden = ${hotel.gym ? false : true};
        
        let parking= document.getElementById('parking');
        console.log(parking)
        
        parking.hidden = ${hotel.parking ? false : true};
        
        </script>
       
    
            
    </body>
</html>
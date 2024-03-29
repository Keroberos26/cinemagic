<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chọn combo - ${theater.name} - ${ticket.showtime.movie.title} - <fmt:formatDate value="${ticket.showtime.starttime}" pattern="HH:mm dd/MM/yyyy"/></title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="ticketing-steps">
                    <div class="container-lg">
                        <div class="row align-items-center">
                            <div class="col">
                                <div class="text-center">
                                    <span class="icon"><i class="ri-layout-grid-fill ri-lg"></i></span>
                                    <div>Chọn ghế</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center text-primary">
                                    <span class="icon"><i class="ri-shopping-bag-3-fill ri-lg"></i></span>
                                    <div>Bắp nước</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center">
                                    <span class="icon"><i class="ri-bank-card-fill ri-lg"></i></span>
                                    <div>Thanh toán</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center">
                                    <span class="icon"><i class="ri-article-fill ri-lg"></i></span>
                                    <div>Thông tin vé</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container-lg py-3">
                    <div class="row g-2">
                        <div class="col-12 col-lg-8">
                            <div class="card">
                                <h2 class="card-header bg-primary text-white">Combo - Bắp nước</h2>
                                <div class="card-body">
                                    <form action="">
                                        <c:forEach items="${comboList}" var="combo">
                                            <div class="combo">
                                                <div class="flexitem gap-4">
                                                    <div class="combo-img">
                                                        <img src="${combo.image}" alt="COMBO">
                                                    </div>
                                                    <div class="combo-content">
                                                        <h5>${combo.name}</h5>
                                                        <h5 class="price text-secondary"><fmt:formatNumber value="${combo.price}" pattern="#,###" /></h5>
                                                        <p>${combo.description}</p>
                                                        <p class="combo-qty">
                                                            <input type="hidden" name="combo" value="${combo.id}">
                                                            <a href="#" class="btn btn-minus disabled"><i class="fa-solid fa-circle-minus fa-lg"></i></a>
                                                            <input type="number" step="1" min="0" name="quantity" value="0" readonly>
                                                            <a href="#" class="btn btn-plus"><i class="fa-solid fa-circle-plus fa-lg"></i></a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-lg-4">
                            <div class="card booking-detail">
                                <h5 class="card-header bg-primary text-white">${ticket.showtime.room.name}</h5>
                                <div class="card-body">
                                    <h5 class="card-title">${ticket.showtime.movie.title}</h5>
                                    <p class="card-subtitle subtitle-text"><fmt:formatDate value="${ticket.showtime.starttime}" pattern="HH:mm"/> 
                                        ~ <fmt:formatDate value="${ticket.showtime.endtime}" pattern="HH:mm"/> · 
                                    <fmt:formatDate value="${ticket.showtime.starttime}" pattern="EEEE, dd/MM" /></p>
                                    <div class="card-text mt-3">
                                        <p>Chỗ ngồi <span class="fw-semibold">${ticket.getSeatNum()}</span></p>
                                        <hr>
                                        <h4>Tạm tính <span class="price float-end"><fmt:formatNumber value="${ticket.getSeatPrice() + ticket.getCombosPrice()}" pattern="#,###" /></span></h4>
                                    </div>
                                    <a href="payment" id="submitSeats" class="btn btn-primary d-block">Tiếp tục</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>


            <%@include file="/general/footer.jsp" %>

        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/booking.js"></script>
    </body>
</html>


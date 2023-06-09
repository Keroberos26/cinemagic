<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chọn ghế - ${theater.name} - ${ticket.showtime.movie.title} - <fmt:formatDate value="${ticket.showtime.starttime}" pattern="HH:mm"/> <fmt:formatDate value="${ticket.showtime.showdate}" pattern="dd/MM/yyyy" /></title>
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
                                <div class="text-center text-primary">
                                    <span class="icon"><i class="ri-layout-grid-fill ri-lg"></i></span>
                                    <div>Chọn ghế</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center">
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
                    <div class="row">
                        <div class="col-12 col-lg-8">
                            <div class="seats">
                                <div class="seats-selection">
                                    <div class="front">
                                        <div class="screen"></div>
                                        <span>Màn hình</span>
                                    </div>
                                    <div class="seats-wrapper">
                                        <div class="seats-map">
                                            <c:forEach items="${seatMap}" var="row">
                                                <ul class="seats-row">
                                                    <c:set var="isCouple" value="${false}"/>
                                                    <c:forEach items="${row}" var="seat">
                                                        <c:choose>
                                                            <c:when test="${!isCouple}">
                                                                <c:choose>
                                                                    <c:when test="${seat.type == 'N'}">
                                                                        <li class="seat seat-normal ${seat.taken?"taken":""} ${ticket.seats.contains(seat)?"selected":""}" seat-id="${seat.id}" seat-type="${seat.type}">${seat.seatNum}</li>
                                                                    </c:when>
                                                                    <c:when test="${seat.type == 'V'}">
                                                                        <li class="seat seat-vip ${seat.taken?"taken":""} ${ticket.seats.contains(seat)?"selected":""}" seat-id="${seat.id}" seat-type="${seat.type}">${seat.seatNum}</li>
                                                                    </c:when>
                                                                    <c:when test="${seat.type == 'C'}">
                                                                        <li class="seat seat-couple ${seat.taken?"taken":""} ${ticket.seats.contains(seat)?"selected":""}" seat-id="${seat.id}" seat-type="${seat.type}">${seat.seatNum}</li>
                                                                        <c:set var="isCouple" value="${true}"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <li class="space"></li>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="isCouple" value="${false}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="legend mt-5" style="pointer-events: none;">
                                        <div class="row">
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="taken"></li>
                                                    <span>Đã đặt</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="selected"></li>
                                                    <span>Ghế bạn chọn</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="seat-normal"></li>
                                                    <span>Ghế thường</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="seat-vip"></li>
                                                    <span>Ghế VIP</span>
                                                </div>
                                            </div>  
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="seat-couple"></li>
                                                    <span>Ghế Sweetbox</span>
                                                </div>
                                            </div>  
                                        </div>
                                    </div>
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
                                        <fmt:formatDate value="${ticket.showtime.showdate}" pattern="EEEE, dd/MM" /></p>
                                    <div class="card-text mt-3">
                                        <p>Chỗ ngồi <span class="fw-semibold">${ticket.getSeatNum()}</span></p>
                                        <hr>
                                        <h4>Tạm tính <span class="price float-end"><fmt:formatNumber value="${ticket.getSeatPrice()}" pattern="#,###" /></span></h4>
                                    </div>
                                    <a href="choose-combo" id="submitSeats" class="btn btn-primary d-block ${ticket.getSeatNum()==""?"disabled":""}">Mua vé</a>
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

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chọn combo - Tên rạp - Phim - Suất chiếu</title>
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
                    <div class="row">
                        <div class="col-12 col-lg-8">
                            <div class="card">
                                <h2 class="card-header bg-primary text-white">Combo - Bắp nước</h2>
                                <div class="card-body">
                                    <div class="combo">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-lg-4">
                            <div class="card booking-detail">
                                <h5 class="card-header bg-primary text-white">${st.roomName}</h5>
                                <div class="card-body">
                                    <h5 class="card-title">${st.movie.title}</h5>
                                    <p class="card-subtitle subtitle-text"><fmt:formatDate value="${st.starttime}" pattern="HH:mm"/> 
                                        ~ <fmt:formatDate value="${st.endtime}" pattern="HH:mm"/> · 
                                    <fmt:formatDate value="${st.showdate}" pattern="EEEE, dd/MM" /></p>
                                    <p class="card-text mt-3">Chỗ ngồi <span class="fw-semibold"></span></p>
                                    <hr>
                                    <h4>Tạm tính <span class="price float-end">124124</span></h4>
                                    <a href="#" id="submitSeats" class="btn btn-primary d-block">Tiếp tục</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>


            <%@include file="/general/footer.jsp" %>

        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/choose-seat.js"></script>
    </body>
</html>

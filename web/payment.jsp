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
                                <div class="text-center">
                                    <span class="icon"><i class="ri-shopping-bag-3-fill ri-lg"></i></span>
                                    <div>Bắp nước</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center  text-primary">
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
                    <div class="payment">
                        <div class="row g-0">
                            <div class="col-12 col-md-5">
                                <div class="qr-code h-100" style="background-color: var(--primary-color);">
                                    Test
                                </div>
                            </div>
                            <div class="col-12 col-md-7 order-md-first">
                                <div class="ticket-detail p-3">    
                                    <div class="flexitem">
                                        <div class="age-restricted age-${st.movie.age}">
                                            <span class="badge rounded-pill">${st.movie.age == "0" ? "P" : st.movie.age}</span>
                                        </div>
                                        <h5 class="mb-0 ms-2">${st.movie.title}</h5>
                                    </div>
                                    <div class="row g-3 py-4 mt-4 b-dashed bt-1 b">
                                        <div class="col-8">
                                            <div class="mini-text text-uppercase fw-medium">Thời gian</div>
                                            <div class="fw-bold"><fmt:formatDate value="${st.starttime}" pattern="HH:mm"/> 
                                                ~ <fmt:formatDate value="${st.endtime}" pattern="HH:mm"/></div>
                                        </div>
                                        <div class="col-4 text-end">
                                            <div class="mini-text text-uppercase fw-medium">Ngày chiếu</div>
                                            <div class="fw-bold"><fmt:formatDate value="${st.showdate}" pattern="dd/MM/yyyy"/></div>
                                        </div>
                                        <div class="col-8">
                                            <div class="mini-text text-uppercase fw-medium">Rạp</div>
                                            <div class="fw-bold">${theater.name}</div>
                                            <div class="mini-text text-capitalize">${theater.street}, ${theater.ward}, ${theater.district}, ${theater.city}</div>
                                        </div>
                                        <div class="col-4 text-end">
                                            <div class="mini-text text-uppercase fw-medium">Phòng chiếu</div>
                                            <div class="fw-bold">${st.room.name}</div>
                                        </div>
                                    </div>

                                    <div class="py-4 b-dashed bt-1 b">
                                        <div class="flexbetween align-items-end">
                                            <div>
                                                <div class="mini-text text-uppercase fw-medium">Ghế</div>
                                                <div class="fw-bold">A01, B02, C03</div>
                                            </div>
                                            <span class="price fw-bold">50000</span>
                                        </div>
                                        <div class="my-2">
                                            <div class="mini-text text-uppercase fw-medium">Bắp nước</div>
                                            <ul>
                                                <li class="flexbetween align-items-end py-1">
                                                    <div class="fw-bold">2 x Tên Combo</div>
                                                    <span class="price fw-bold">68000</span>
                                                </li>
                                                <li class="flexbetween align-items-end py-1">
                                                    <div class="fw-bold">2 x Tên Combo</div>
                                                    <span class="price fw-bold">68000</span>
                                                </li>
                                                <li class="flexbetween align-items-end py-1">
                                                    <div class="fw-bold">2 x Tên Combo</div>
                                                    <span class="price fw-bold">68000</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="py-4 b-dashed bt-1 b">
                                        <div class="flexbetween align-items-end">
                                            <div class="fw-bold">Tổng tiền</div>
                                            <span class="price fw-bold">68000</span>
                                        </div>
                                    </div>
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

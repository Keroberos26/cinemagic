<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lịch chiếu phim trên CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="breadcrumb-block">
                    <div class="container-lg">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb main-links">
                                <li class="breadcrumb-item"><a href="/"><span class="icon"><i
                                                class="ri-home-4-fill"></i></span></a></li>
                                <li class="breadcrumb-item active" aria-current="page">Lịch chiếu</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="banner">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="row g-4">
                                <div class="col-md col-12 order-md-2">
                                    <div class="banner-img">
                                        <div class="bg-img"
                                             style="background-image: url(/assets/img/banner.png);">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md col-12 order-md-1">
                                    <div class="text-content">
                                        <h1>Lịch chiếu phim tháng 6/2023 trên CineMagic</h1>
                                        <ul>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span>Lịch chiếu
                                                luôn
                                                <strong>cập nhật sớm nhất</strong>
                                            </li>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span><strong>Suất
                                                    chiếu đầy đủ</strong> các rạp</li>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span>Đặt lịch chiếu
                                                <strong>mua vé siêu nhanh</strong>
                                            </li>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span><strong>Đặt vé
                                                    lịch chiếu phim</strong> yêu thích mọi nơi</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="showtimes">
                    <div class="container-lg">
                        <div class="wrapper">
                            <h1>Lịch chiếu phim</h1>
                            <div class="showtimes-box">
                                <div class="box-header">
                                    <div class="position flexitem">
                                        <h4 class="m-0">Vị trí</h4>

                                        <button type="button" class="btn btn-outline-primary btn-sm fw-bold" data-bs-toggle="modal"
                                                data-bs-target="#positionModal">
                                            <span class="icon"><i class="ri-road-map-line"></i></span><span class="city">${acc.city != null ? acc.city : "Thành phố Đà Nẵng"}</span>
                                        </button>

                                        <a href="#" class="btn btn-outline-primary btn-sm fw-bold"><span class="icon"><i
                                                    class="ri-map-pin-user-line"></i></span>Gần bạn</a>

                                    </div>
                                    <div class="modal fade" id="positionModal" tabindex="-1"
                                         aria-labelledby="positionModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="positionModalLabel">Chọn vị trí
                                                    </h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <ul class="row row-cols-2">
                                                    </ul>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            data-bs-dismiss="modal">Đóng</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="swiper">
                                        <div class="swiper-wrapper">
                                            <div class="swiper-slide">
                                                <div class="cinema active">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/next-js/_next/static/public/cinema/dexuat-icon.svg" alt="Tất cả">
                                                    </div>
                                                    <div class="mini-text">Tất cả</div>
                                                </div>
                                            </div>
                                            <c:forEach items="${cinema}" var="c">
                                                <div class="swiper-slide">
                                                    <div class="cinema" cinema-id="${c.id}">
                                                        <div class="cinema-avt">
                                                            <img src="${c.logo}" alt="${c.name}">
                                                        </div>
                                                        <div class="mini-text">${c.name}</div>
                                                    </div>
                                                </div>     
                                            </c:forEach>
                                        </div>
                                        <div class="swiper-button-next"></div>
                                        <div class="swiper-button-prev"></div>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <div class="row g-md-0">
                                        <div class="col-md-3 col-12">
                                            <div class="theater">

                                            </div>
                                        </div>
                                        <div class="col-md-9 col-12">
                                            <div class="showtimes-movie">
                                                
                                                <div class="address">
                                                    
                                                </div>

                                                <div class="swiper">
                                                    <div class="swiper-wrapper">
                                                        <c:forEach items="${dateList}" var="d" varStatus="today">
                                                            <div class="swiper-slide">
                                                                <div class="day${today.count == 1?" active":""}" date="<fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>">
                                                                    <h4><fmt:formatDate value="${d}" pattern="dd" /></h4>
                                                                    <span class="mini-text">
                                                                        <c:if test="${today.count == 1}">
                                                                            Hôm nay
                                                                        </c:if>
                                                                        <c:if test="${today.count != 1}">
                                                                            <fmt:formatDate value="${d}" pattern="EEEE" />
                                                                        </c:if>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="swiper-button-next"></div>
                                                    <div class="swiper-button-prev"></div>
                                                </div>

                                                <div class="list">

                                                </div>
                                            </div>
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
        <script src="/assets/js/showtime.js"></script>
    </body>
</html>
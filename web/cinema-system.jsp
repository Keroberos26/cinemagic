<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đặt mua vé ${c.name} | Giá vé ưu đãi trên CineMagic</title>
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
                                <li class="breadcrumb-item"><a href="/cinemas">Rạp chiếu</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${c.name}</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="single-movie" style="background-image: url(assets/img/background/background2.jpg);">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="flexitem gap-3" style="margin-top: 15em;">
                                <div class="left flex-shrink-0">
                                    <img src="${c.logo}" alt="${c.name}" width="96" height="96" class="p-1 bg-white rounded-1">
                                </div>
                                <div class="right flex-grow-1">
                                    <h5 class="text-white">${c.name}</h5>
                                    <p class="mini-text text-white">${c.description}</p>
                                    <p class="mini-text"><span class="icon"><i class="ri-map-pin-line"></i></span>${c.numOfTheater} rạp</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
               
                <div class="showtimes">
                    <div class="container-lg">
                        <div class="wrapper">
                            <h1 class="text-primary">Lịch chiếu phim ${c.name}</h1>
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

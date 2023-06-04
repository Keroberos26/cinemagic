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
                                            <span class="icon"><i class="ri-road-map-line"></i></span><span class="city">Đà Nẵng</span>
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
                                                    <ul class="row row-cols-md-4 row-cols-2">
                                                        <li class="col"><a href="#">TP.HCM</a></li>
                                                        <li class="col"><a href="#">Hà Nội</a></li>
                                                        <li class="col"><a href="#">Huế</a></li>
                                                        <li class="col"><a href="#" class="active">Đà Nẵng</a></li>
                                                        <li class="col"><a href="#">Quảng Nam</a></li>
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
                                                <c:forEach items="${theaterList}" var="t" varStatus="no">
                                                    <a href="#" theater-id="${t.id}" onclick="theaterEvent(this, event)" ${no.count == 1?"class='active'":""}>
                                                        <div class="theater-avt">
                                                            <img src="${t.image}">
                                                        </div>
                                                        <div class="theater-name">${t.name}</div>
                                                    </a>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="col-md-9 col-12">
                                            <div class="showtimes-movie">
                                                <c:set var="theater" value="${theaterList.get(0)}" />
                                                <div class="address">
                                                    <div class="left">
                                                        <img src="${theater.image}">
                                                    </div>

                                                    <div class="right">
                                                        <h4>Lịch chiếu phim ${theater.name}</h4>
                                                        <span class="mini-text">${theater.street}, ${theater.ward}, 
                                                            ${theater.district}, ${theater.city}<a href="#">[ Bản đồ]</a></span>
                                                    </div>
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

                <div class="coming-soon">
                    <div class="container-lg">
                        <div class="wrapper">
                            <h1>Phim sắp chiếu</h1>

                            <div class="swiper">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Con Nhót Mót Chồng</h4>
                                                    <p class="mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/latmat6.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Lật Mặt 6: Tấm Vé Định Mệnh</h4>
                                                    <p class="mini-text">Tâm lý</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/mario.jpg);"></div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-0"><span class="badge"></span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Anh Em Super Mario</h4>
                                                    <p class="mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/galaxy3.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Vệ Binh Dải Ngân Hà 3</h4>
                                                    <p class="mini-text">Khoa học viễn tưởng</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Con Nhót Mót Chồng</h4>
                                                    <p class="mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/latmat6.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Lật Mặt 6: Tấm Vé Định Mệnh</h4>
                                                    <p class="mini-text">Tâm lý</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/mario.jpg);"></div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-0"><span class="badge"></span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Anh Em Super Mario</h4>
                                                    <p class="mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/galaxy3.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Vệ Binh Dải Ngân Hà 3</h4>
                                                    <p class="mini-text">Khoa học viễn tưởng</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Con Nhót Mót Chồng</h4>
                                                    <p class="mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/latmat6.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Lật Mặt 6: Tấm Vé Định Mệnh</h4>
                                                    <p class="mini-text">Tâm lý</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/mario.jpg);"></div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-0"><span class="badge"></span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Anh Em Super Mario</h4>
                                                    <p class="mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a href="#">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/galaxy3.jpg);">
                                                    </div>
                                                    <div class="play-btn">
                                                        <svg viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg"
                                                             class="jsx-314b02cb997a0a18">
                                                        <g fill="none" fill-rule="evenodd" class="jsx-314b02cb997a0a18">
                                                        <circle stroke="#FFF" stroke-width="2" fill-opacity=".24"
                                                                fill="#000" cx="24" cy="24" r="23"
                                                                class="jsx-314b02cb997a0a18"></circle>
                                                        <path
                                                            d="M34.667 24.335c0 .515-.529.885-.529.885l-14.84 9.133c-1.08.704-1.965.182-1.965-1.153V15.467c0-1.338.884-1.856 1.968-1.153L34.14 23.45c-.002 0 .527.37.527.885Z"
                                                            fill="#FFF" fill-rule="nonzero"
                                                            class="jsx-314b02cb997a0a18"></path>
                                                        </g>
                                                        </svg>
                                                    </div>
                                                    <div class="age-restricted age-16"><span class="badge">16+</span></div>
                                                </a>
                                            </div>
                                            <div class="content main-links">
                                                <a href="#">
                                                    <h4 class="title">Vệ Binh Dải Ngân Hà 3</h4>
                                                    <p class="mini-text">Khoa học viễn tưởng</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="swiper-pagination"></div>
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

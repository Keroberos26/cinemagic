<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Go Cinema By Magic | CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="banner">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="row g-4">
                                <div class="col-md col-12 order-md-2">
                                    <div class="banner-img">
                                        <div class="bg-img"
                                             style="background-image: url(https://static.mservice.io/img/momo-upload-api-210820191534-637650837346813468.jpg);">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md col-12 order-md-1">
                                    <div class="text-content">
                                        <h1>Đặt mua vé xem phim CineMagic</h1>
                                        <ul>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span>Mua vé Online,
                                                <strong>trải nghiệm phim hay</strong>
                                            </li>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span><strong>Đặt vé
                                                    an toàn</strong> trên CineMagic</li>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span>Tha hồ
                                                <strong>chọn chỗ ngồi, mua bắp nước</strong> tiện lợi
                                            </li>
                                            <li><span class="circle icon"><i class="ri-check-line"></i></span><strong>Lịch
                                                    sử đặt vé</strong> được lưu lại ngay</li>
                                        </ul>
                                        <a href="#" class="btn btn-primary">Đặt vé ngay</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="in-theaters">
                    <div class="container-lg">
                        <div class="wrapper">
                            <h1>Phim đang chiếu</h1>

                            <div class="swiper">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a data-fslightbox="1" href="https://www.youtube.com/watch?v=e7KHOQ-alqY">
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
                                                    <p class=" genre mini-text">Hài hước</p>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="swiper-slide">
                                        <div class="movie-card">
                                            <div class="poster">
                                                <a data-fslightbox="2" href="https://www.youtube.com/watch?v=2EnP2tVC00Q">
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

                <div class="cinema-system">
                    <div class="container-lg">
                        <div class="wrapper">
                            <h1>Hệ thống rạp chiếu phim</h1>
                            <div class="row g-4">
                                <div class="col-md-6 col-12">
                                    <a href="#">
                                        <div class="cinema">
                                            <div class="left"><img
                                                    src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                    alt=""></div>
                                            <div class="right">
                                                <h4>CGV</h4>
                                                <p class="mini-text">Hệ thống rạp chiếu phim lớn nhất Việt Nam</p>
                                                <p class="mini-text"><span class="icon"><i
                                                            class="ri-map-pin-line"></i></span>88 rạp</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-md-6 col-12">
                                    <a href="#">
                                        <div class="cinema">
                                            <div class="left"><img
                                                    src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                    alt=""></div>
                                            <div class="right">
                                                <h4>CGV</h4>
                                                <p class="mini-text">Hệ thống rạp chiếu phim lớn nhất Việt Nam</p>
                                                <p class="mini-text"><span class="icon"><i
                                                            class="ri-map-pin-line"></i></span>88 rạp</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-md-6 col-12">
                                    <a href="#">
                                        <div class="cinema">
                                            <div class="left"><img
                                                    src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                    alt=""></div>
                                            <div class="right">
                                                <h4>CGV</h4>
                                                <p class="mini-text">Hệ thống rạp chiếu phim lớn nhất Việt Nam</p>
                                                <p class="mini-text"><span class="icon"><i
                                                            class="ri-map-pin-line"></i></span>88 rạp</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-md-6 col-12">
                                    <a href="#">
                                        <div class="cinema">
                                            <div class="left"><img
                                                    src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                    alt=""></div>
                                            <div class="right">
                                                <h4>CGV</h4>
                                                <p class="mini-text">Hệ thống rạp chiếu phim lớn nhất Việt Nam</p>
                                                <p class="mini-text"><span class="icon"><i
                                                            class="ri-map-pin-line"></i></span>88 rạp</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-md-6 col-12">
                                    <a href="#">
                                        <div class="cinema">
                                            <div class="left"><img
                                                    src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                    alt=""></div>
                                            <div class="right">
                                                <h4>CGV</h4>
                                                <p class="mini-text">Hệ thống rạp chiếu phim lớn nhất Việt Nam</p>
                                                <p class="mini-text"><span class="icon"><i
                                                            class="ri-map-pin-line"></i></span>88 rạp</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-md-6 col-12">
                                    <a href="#">
                                        <div class="cinema">
                                            <div class="left"><img
                                                    src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                    alt=""></div>
                                            <div class="right">
                                                <h4>CGV</h4>
                                                <p class="mini-text">Hệ thống rạp chiếu phim lớn nhất Việt Nam</p>
                                                <p class="mini-text"><span class="icon"><i
                                                            class="ri-map-pin-line"></i></span>88 rạp</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <%@include file="/general/footer.jsp" %>
        </div>

        <%@include file="/general/script.jsp" %>
    </body>
</html>

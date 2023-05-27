<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="preloader"></div>

<header>
    <div class="header-nav">
        <div class="container-lg">
            <div class="wrapper">
                <div class="left">
                    <div class="logo">
                        <a href="/">
                            <img src="/assets/img/logo.png" alt="CineMagic" title="CineMagic">
                        </a>
                    </div>
                </div>

                <div class="right">
                    <nav class="d-lg-block d-none">
                        <ul class="main-links flexitem">
                            <li><a href="#">Rạp chiếu</a></li>
                            <li><a href="/showtime.jsp">Lịch chiếu</a></li>
                            <li><a href="#">Phim chiếu</a></li>
                            <li><a href="#">Review phim</a></li>
                            <li><a href="/login">Đăng nhập</a></li>
                        </ul>
                    </nav>

                    <div class="main-links d-lg-none">
                        <a class="px-3" data-bs-toggle="offcanvas" href="#offcanvasNav" role="button"
                           aria-controls="offcanvasNav">
                            <span class="icon"><i class="ri-menu-3-line"></i></span>
                        </a>
                    </div>

                    <div class="offcanvas offcanvas-end d-lg-none" tabindex="-1" id="offcanvasNav"
                         aria-labelledby="offcanvasNavLabel">
                        <div class="offcanvas-header">
                            <h5 class="offcanvas-title" id="offcanvasNavLabel">
                                <div class="logo">
                                    <a href="/">
                                        <img src="/assets/img/logo.png" alt="CineMagic" title="CineMagic">
                                    </a>
                                </div>
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                    aria-label="Close"></button>
                        </div>
                        <div class="offcanvas-body">
                            <ul class="main-links flexcol">
                                <li><a href="#">Rạp chiếu</a></li>
                                <li><a href="#">Lịch chiếu</a></li>
                                <li><a href="#">Phim chiếu</a></li>
                                <li><a href="#">Đăng nhập</a></li>
                                <li><a href="#">Đăng ký</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
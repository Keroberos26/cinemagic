<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Phim chiếu rạp 2023 hay | Đặt vé xem phim Online ngay</title>
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
                                            <li class="breadcrumb-item active" aria-current="page">Phim chiếu</li>
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
                                                    <h1>Phim chiếu rạp hấp dẫn trên CineMagic</h1>
                                                    <ul>
                                                        <li><span class="circle icon"><i
                                                                    class="ri-check-line"></i></span>
                                                            <strong>Đa dạng thể loại</strong> phim chiếu rạp
                                                        </li>
                                                        <li><span class="circle icon"><i
                                                                    class="ri-check-line"></i></span>Lịch chiếu phim
                                                            <strong>cập nhật đầy đủ nhất</strong>
                                                        <li><span class="circle icon"><i
                                                                    class="ri-check-line"></i></span><strong>Đánh giá
                                                                phim rạp</strong>
                                                            chi tiết chân thật</li>
                                                        </li>
                                                        <li><span class="circle icon"><i
                                                                    class="ri-check-line"></i></span>Đặt vé
                                                            <strong>xem phim Online</strong> dễ dàng
                                                        </li>
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
                                        <div class="row gy-3 mb-4">
                                            <div class="col-12 col-lg-5">
                                                <h3 class="text-primary text-center text-lg-start">Tìm phim chiếu rạp trên CineMagic</h3>
                                            </div>
                                            <div class="col-12 col-lg-7">
                                                <div class="row g-4 movie-filter align-items-center">
                                                    <div class="col">
                                                        <div class="dropdown">
                                                            <button class="btn btn-outline-secondary btn-sm w-100 dropdown-toggle"
                                                                type="button" data-bs-toggle="dropdown" id="sltGenre" aria-expanded="false">
                                                                Thể loại
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="">Tất cả</button>
                                                                </li>
                                                                <c:forEach items="${genreList}" var="g">
                                                                    <li>
                                                                        <button type="button"
                                                                            class="dropdown-item" value="${g.name}">${g.name}</button>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="dropdown">
                                                            <button class="btn btn-outline-secondary btn-sm w-100 dropdown-toggle"
                                                                type="button" data-bs-toggle="dropdown" id="sltCountry" aria-expanded="false">
                                                                Quốc gia
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="">Tất cả</button>
                                                                </li>
                                                                <c:forEach items="${countries}" var="country">
                                                                    <li>
                                                                        <button type="button"
                                                                            class="dropdown-item" value="${country}">${country}</button>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="dropdown">
                                                            <button class="btn btn-outline-secondary btn-sm w-100 dropdown-toggle"
                                                                type="button" data-bs-toggle="dropdown" id="sltYear" aria-expanded="false">
                                                                Năm
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="">Tất cả</button>
                                                                </li>
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="2019">2019</button>
                                                                </li>
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="2020">2020</button>
                                                                </li>
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="2021">2021</button>
                                                                </li>
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="2022">2022</button>
                                                                </li>
                                                                <li>
                                                                    <button type="button"
                                                                        class="dropdown-item" value="2023">2023</button>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="col-12 col-md-4">
                                                        <input class="form-control" type="search" id="txtSearch" placeholder="Tìm theo tên phim...">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="movie-list">
                                        </div>
                                        <div class="paging"></div>
                                    </div>
                                </div>
                            </div>
                        </main>

                        <%@include file="/general/footer.jsp" %>
                </div>

                <%@include file="/general/script.jsp" %>
                <script src="/assets/js/movies.js"></script>
            </body>

            </html>
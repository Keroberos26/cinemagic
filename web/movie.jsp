<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tên phim | CineMagic</title>
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
                                <li class="breadcrumb-item"><a href="#"><span class="icon"><i
                                                class="ri-home-4-fill"></i></span></a></li>
                                <li class="breadcrumb-item"><a href="#">Phim chiếu</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Tên phim</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="single-movie">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="movie-card detail">
                                <div class="row g-4">
                                    <div class="col-md-3 col-sm-5 col-12">
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
                                    </div>
                                    <div class="col-md col-sm-7 col-12">
                                        <div class="content main-links">
                                            <h1 class="title">Con Nhót Mót Chồng</h1>
                                            <p class="genre mini-text">Hài hước - 2023</p>
                                            <div class="description">
                                                <p><strong>Nội dung</strong></p>
                                                <p class="mini-text">Bộ phim là câu chuyện của Nhót - người phụ nữ “chưa kịp
                                                    già” đã
                                                    sắp bị mãn kinh, vội vàng đi tìm chồng. Nhưng sâu thẳm trong cô, là khao
                                                    khát
                                                    muốn có một đứa con và luôn muốn hàn gắn với người cha suốt ngày say xỉn
                                                    của
                                                    mình.</p>
                                            </div>
                                            <div class="flexitem gap-3 gap-md-5">
                                                <div>
                                                    <p class="mini-text mb-1 d-inline-flex"><span class="icon ps-0"><i
                                                                class="ri-calendar-event-line"></i></span>Khởi chiếu</p>
                                                    <p>28/04/2023</p>
                                                </div>
                                                <div>
                                                    <p class="mini-text mb-1 d-inline-flex"><span class="icon ps-0"><i
                                                                class="ri-time-line"></i></span>Thời lượng</p>
                                                    <p>112 phút</p>
                                                </div>
                                                <div>
                                                    <p class="mini-text mb-1 d-inline-flex"><span class="icon ps-0"><i
                                                                class="ri-earth-line"></i></span>Quốc gia</p>
                                                    <p>Việt Nam</p>
                                                </div>
                                            </div>
                                        </div>
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

                                        <button type="button" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal"
                                                data-bs-target="#positionModal">
                                            <span class="icon"><i class="ri-road-map-line"></i></span>Đà Nẵng
                                        </button>

                                        <a href="#" class="btn btn-outline-primary btn-sm"><span class="icon"><i
                                                    class="ri-map-pin-user-line"></i></span>Gần bạn</a>

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
                                                            <li class="col"><a href="#" class="active">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
                                                            <li class="col"><a href="#">TP. HCM</a></li>
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

                                    <div class="swiper">
                                        <div class="swiper-wrapper">
                                            <div class="swiper-slide">
                                                <div class="cinema active">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/next-js/_next/static/public/cinema/dexuat-icon.svg"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Tất cả</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">CGV</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/blogscontents/momo-upload-api-210604170617-637584231772974269.png"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Lotte Cinema</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/cinema/momo-upload-api-211123095138-637732578984425272.png"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Galaxy Cinema</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/next-js/_next/static/public/cinema/dexuat-icon.svg"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Tất cả</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/next-js/_next/static/public/cinema/dexuat-icon.svg"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Tất cả</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/next-js/_next/static/public/cinema/dexuat-icon.svg"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Tất cả</div>
                                                </div>
                                            </div>
                                            <div class="swiper-slide">
                                                <div class="cinema">
                                                    <div class="cinema-avt">
                                                        <img src="https://static.mservice.io/next-js/_next/static/public/cinema/dexuat-icon.svg"
                                                             alt="Đề xuất">
                                                    </div>
                                                    <div class="mini-text">Tất cả</div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="swiper-button-next"></div>
                                        <div class="swiper-button-prev"></div>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <div class="accordion accordion-flush" id="showtimes-cinema">
                                        <div class="accordion-item showtimes-cinema">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#showtimes-collapse1"
                                                        aria-expanded="false" aria-controls="showtimes-collapse1">
                                                    <div class="address">
                                                        <div class="left">
                                                            <img src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                                 alt="CGV">
                                                        </div>

                                                        <div class="right">
                                                            <h4>CGV Vincom Đà Nẵng</h4>
                                                            <span class="mini-text">Tầng 4, TTTM Vincom Đà Nẵng, đường Ngô
                                                                Quyền, P.An Hải Bắc, Q.Sơn Trà, TP. Đà Nẵng<a href="#">[ Bản đồ ]</a></span>
                                                        </div>
                                                    </div>
                                                </button>
                                            </h2>
                                            <div id="showtimes-collapse1" class="accordion-collapse collapse"
                                                 data-bs-parent="#showtimes-cinema">
                                                <div class="accordion-body">
                                                    <div class="row row-cols-md-5 row-cols-sm-3 row-cols-2 g-3">
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="accordion-item showtimes-cinema">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#showtimes-collapse2"
                                                        aria-expanded="false" aria-controls="showtimes-collapse2">
                                                    <div class="address">
                                                        <div class="left">
                                                            <img src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                                 alt="CGV">
                                                        </div>

                                                        <div class="right">
                                                            <h4>CGV Vincom Đà Nẵng</h4>
                                                            <span class="mini-text">Tầng 4, TTTM Vincom Đà Nẵng, đường Ngô
                                                                Quyền, P.An Hải Bắc, Q.Sơn Trà, TP. Đà Nẵng<a href="#">[ Bản đồ ]</a></span>
                                                        </div>
                                                    </div>
                                                </button>
                                            </h2>
                                            <div id="showtimes-collapse2" class="accordion-collapse collapse"
                                                 data-bs-parent="#showtimes-cinema">
                                                <div class="accordion-body">
                                                    <div class="row row-cols-md-5 row-cols-sm-3 row-cols-2 g-3">
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="accordion-item showtimes-cinema">
                                            <h2 class="accordion-header">
                                                <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#showtimes-collapse3"
                                                        aria-expanded="false" aria-controls="showtimes-collapse3">
                                                    <div class="address">
                                                        <div class="left">
                                                            <img src="https://static.mservice.io/placebrand/s/momo-upload-api-190709165424-636982880641515855.jpg"
                                                                 alt="CGV">
                                                        </div>

                                                        <div class="right">
                                                            <h4>CGV Vincom Đà Nẵng</h4>
                                                            <span class="mini-text">Tầng 4, TTTM Vincom Đà Nẵng, đường Ngô
                                                                Quyền, P.An Hải Bắc, Q.Sơn Trà, TP. Đà Nẵng<a href="#">[ Bản đồ ]</a></span>
                                                        </div>
                                                    </div>
                                                </button>
                                            </h2>
                                            <div id="showtimes-collapse3" class="accordion-collapse collapse"
                                                 data-bs-parent="#showtimes-cinema">
                                                <div class="accordion-body">
                                                    <div class="row row-cols-md-5 row-cols-sm-3 row-cols-2 g-3">
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                        <div class="col">
                                                            <a href="#" class="btn btn-outline-secondary btn-sm d-block"><strong>15:00</strong> ~ 17:29</a>
                                                        </div>
                                                    </div>
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
    </body>
</html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${m.title} | CineMagic</title>
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
                                <li class="breadcrumb-item"><a href="/movies">Phim chiếu</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${m.title}</li>
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
                                            <a data-fslightbox="${m.id}" href="${m.trailer}">
                                                <div class="bg-img thumbnail"
                                                     style="background-image: url(${m.poster});">
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
                                                <div class="age-restricted age-${m.age}"><span class="badge">${m.age}</span></div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-md col-sm-7 col-12">
                                        <div class="content main-links text-white">
                                            <h1 class="title">${m.title}</h1>
                                            <p class="genre mini-text">${m.genres} - <fmt:formatDate value="${m.releaseDate}" pattern="yyyy" /></p>
                                            <c:if test="${m.rating != null && m.rating > 0}">
                                                <p class="stars fs-3">
                                                    <c:forEach begin="1" end="10" varStatus="s">
                                                        <c:choose>
                                                            <c:when test="${m.rating >= s.count}">
                                                                <i class="ri-star-fill"></i>
                                                            </c:when>
                                                            <c:when test="${m.rating < s.count and m.rating > s.count - 1}">
                                                                <i class="ri-star-half-fill"></i>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <i class="ri-star-line"></i>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </p>
                                            </c:if>
                                            <div class="description">
                                                <p><strong>Nội dung</strong></p>
                                                <p class="mini-text">${m.description}</p>
                                            </div>
                                            <div class="flexitem gap-3 gap-md-5">
                                                <div>
                                                    <p class="mini-text mb-1 d-inline-flex"><span class="icon ps-0"><i
                                                                class="ri-calendar-event-line"></i></span>Khởi chiếu</p>
                                                    <p><fmt:formatDate value="${m.releaseDate}" pattern="dd/MM/yyyy" /></p>
                                                </div>
                                                <div>
                                                    <p class="mini-text mb-1 d-inline-flex"><span class="icon ps-0"><i
                                                                class="ri-time-line"></i></span>Thời lượng</p>
                                                    <p>${m.duration} phút</p>
                                                </div>
                                                <div>
                                                    <p class="mini-text mb-1 d-inline-flex"><span class="icon ps-0"><i
                                                                class="ri-earth-line"></i></span>Quốc gia</p>
                                                    <p>${m.country}</p>
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
                            <div class="row gy-5">
                                <div class="col-lg-7">
                                    <h2>Lịch chiếu phim</h2>
                                    <div class="showtimes-box">
                                        <div class="box-header">
                                            <div class="position flexitem">
                                                <h4 class="m-0">Vị trí</h4>

                                                <button type="button" class="btn btn-outline-primary btn-sm fw-bold" data-bs-toggle="modal"
                                                        data-bs-target="#positionModal">
                                                    <span class="icon"><i class="ri-road-map-line"></i></span><span class="city">${acc.city != null ? acc.city : "Đà Nẵng"}</span>
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
                                                                <li class="col"><a href="#" data-bs-dismiss="modal">TP.HCM</a></li>
                                                                <li class="col"><a href="#" data-bs-dismiss="modal">Hà Nội</a></li>
                                                                <li class="col"><a href="#" data-bs-dismiss="modal">Huế</a></li>
                                                                <li class="col"><a href="#" data-bs-dismiss="modal">Đà Nẵng</a></li>
                                                                <li class="col"><a href="#" data-bs-dismiss="modal">Quảng Nam</a></li>
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
                                            <div class="showtimes-movie">
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
                                                <div class="accordion accordion-flush" id="showtimes-cinema">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-5">
                                    <h2>Bình luận từ người xem</h2>
                                    <div class="flexcol my-4 gap-5 reviews">
                                        <c:forEach items="${reviews}" var="r">
                                            <div class="d-flex gap-3 review">
                                                <div class="avatar avatar-xl flex-shrink-0">
                                                    <img src="${r.acc.avatar != null ? r.acc.avatar : "/assets/img/no-avatar.png"}" alt="Avatar"
                                                         class="avatar-img rounded-circle border border-4 d-block">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <div class="fw-bold mb-2">${r.acc.name} <span class="mini-text fw-normal"> · <fmt:formatDate value="${r.date}" pattern="dd/MM/yyyy"/></span></div>
                                                    <div class="stars mb-2">
                                                        <c:forEach begin="1" end="10" varStatus="s">
                                                            <c:choose>
                                                                <c:when test="${r.rating >= s.count}">
                                                                    <i class="ri-star-fill"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="ri-star-line"></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="comment">${r.comment}</div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="text-center">
                                        <button class="btn btn-outline-primary" id="moreReviews"><span class="icon"><i class="fa-solid fa-arrow-down"></i></span>Xem thêm</button>
                                    </div>
                                    <c:if test="${acc != null && bought}">
                                        <div class="mt-4">
                                            <div class="flexitem gap-3 border-top pt-4">
                                                <div class="avatar avatar-xl flex-shrink-0">
                                                    <img src="${acc.avatar != null ? acc.avatar : "/assets/img/no-avatar.png"}" alt="Avatar"
                                                         class="avatar-img rounded-circle border border-4 d-block">
                                                </div>
                                                         <form action="/movie?movieId=${param.id}" method="post">
                                                    <div class="flex-grow-1">
                                                        <div class="stars-rate">
                                                            <input type="radio" name="rating" value="10" id="star10" required ${accReview.rating == 10?"checked":""}>
                                                            <label for="star10"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="9" id="star9" ${accReview.rating == 9?"checked":""}>
                                                            <label for="star9"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="8" id="star8" ${accReview.rating == 8?"checked":""}>
                                                            <label for="star8"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="7" id="star7" ${accReview.rating == 7?"checked":""}>
                                                            <label for="star7"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="6" id="star6" ${accReview.rating == 6?"checked":""}>
                                                            <label for="star6"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="5" id="star5" ${accReview.rating == 5?"checked":""}>
                                                            <label for="star5"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="4" id="star4" ${accReview.rating == 4?"checked":""}>
                                                            <label for="star4"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="3" id="star3" ${accReview.rating == 3?"checked":""}>
                                                            <label for="star3"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="2" id="star2" ${accReview.rating == 2?"checked":""}>
                                                            <label for="star2"><i class="ri-star-fill"></i></label>

                                                            <input type="radio" name="rating" value="1" id="star1" ${accReview.rating == 1?"checked":""}>
                                                            <label for="star1"><i class="ri-star-fill"></i></label>
                                                        </div>
                                                        <div class="position-relative">
                                                            <input type="text" name="review" class="form-control rounded-pill" placeholder="Viết đánh giá ..." value="${accReview.comment}">
                                                            <button name="action" value="review" type="submit" class="btn btn-primary rounded-pill position-absolute top-0 bottom-0 end-0"><i class="fa-solid fa-paper-plane"></i></button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </main>

            <%@include file="/general/footer.jsp" %>
        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/showtimes-movie.js"></script>
    </body>
</html>

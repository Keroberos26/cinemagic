<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${t.name} | Đặt vé xem phim trên CineMagic</title>
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
                                <li class="breadcrumb-item"><a href="/cinema-system?cinema=${c.id}">${c.name}</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${t.name}</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="single-movie" style="background-image: url(${t.image});">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="flexitem gap-3" style="margin-top: 15em;">
                                <div class="left flex-shrink-0">
                                    <img src="${c.logo}" alt="${c.name}" width="96" height="96" class="p-1 bg-white rounded-1">
                                </div>
                                <div class="right flex-grow-1">
                                    <h5 class="text-white">${t.name}</h5>
                                    <p class="mini-text text-white">${t.street}, ${t.ward}, ${t.district}, ${t.city}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="showtimes">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="row gy-5">
                                <h2 class="text-center text-primary">Lịch chiếu phim ${t.name}</h2>
                                <div class="showtimes-box">
                                    <div class="box-body">
                                        <div class="showtimes-movie outline-none">
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
            </main>

            <%@include file="/general/footer.jsp" %>
        </div>

        <%@include file="/general/script.jsp" %>
        <script>
            $('.day').click(function(e) {
                e.preventDefault();

                $('.day').removeClass('active');
                $(this).addClass('active');

                getShowtimes();
            });

            function getShowtimes() {
                var url = new URL(window.location.href);
                var searchParams = new URLSearchParams(url.search);

                var id = searchParams.get('id');
                var date = $('.day.active').attr('date');

                $('.showtimes-movie .list').css('position', 'relative');
                $('.showtimes-movie .list').append(spinner);

                $.ajax({
                    url: url.pathname,
                    data: {
                        action: "getShowtimes",
                        theater: id,
                        date: date,
                    },
                    type: "post",
                    success: function (response) {
                        $('.showtimes-movie .list').html(response);
                    },
                    error: function (xhr) {
                        console.log("ERROR Ajax");
                    }
                });
            }

            $(document).ready(function() {
                getShowtimes();
            })
        </script>
    </body>
</html>

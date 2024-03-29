<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="url" value="${pageContext.request.requestURL}" />
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
                            <li><a href="/cinemas" class="${fn:endsWith(url, "cinemas.jsp")? "active": ""}">Rạp chiếu</a></li>
                            <li><a href="/showtimes" class="${fn:endsWith(url, "showtime.jsp")? "active": ""}">Lịch chiếu</a></li>
                            <li><a href="/movies" class="${fn:endsWith(url, "movies.jsp")? "active": ""}">Phim chiếu</a></li>
                            <li>
                                <div class="dropdown">
                                    <a class="dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <div class="avatar">
                                            <img src="${acc.avatar != null ? acc.avatar : "/assets/img/no-avatar.png"}" alt="Avatar"
                                                 class="avatar-img rounded-circle border border-4 d-block">
                                        </div>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${acc == null}">
                                            <li><a class="dropdown-item" href="/login">
                                                <span class="icon"><i class="ri-login-box-line"></i></span>Đăng nhập
                                            </a></li>
                                            <li><a class="dropdown-item" href="/register">
                                                <span class="icon"><i class="fa-brands fa-angellist"></i></span>Đăng ký
                                            </a></li>
                                        </c:if>
                                        <c:if test="${acc != null}">
                                            <li><a class="dropdown-item" href="/my-account">
                                                <span class="icon"><i class="fa-regular fa-user"></i></span>Tài khoản của tôi
                                            </a></li>
                                            <li><a class="dropdown-item" href="/logout">
                                                <span class="icon"><i class="ri-logout-box-line"></i></span>Đăng xuất
                                            </a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </li>
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
                                <li><a href="/cinemas">Rạp chiếu</a></li>
                                <li><a href="/showtimes">Lịch chiếu</a></li>
                                <li><a href="/movies">Phim chiếu</a></li>
                                <c:if test="${acc == null}">
                                    <li><a href="/login">Đăng nhập</a></li>
                                    <li><a href="/register">Đăng ký</a></li>
                                </c:if>
                                <c:if test="${acc != null}">
                                    <li><a href="/my-account">Tài khoản của tôi</a></li>
                                    <li><a href="/logout">Đăng xuất</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
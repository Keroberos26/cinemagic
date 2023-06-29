<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="url" value="${pageContext.request.requestURL}" />
<nav class="navbar navbar-expand-lg bg-body-tertiary bg-secondary mb-3" data-bs-theme="dark">
    <div class="container-lg">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav text-center flexbetween fs-5">
                <a class="nav-link px-5 ${fn:endsWith(url, "cinema.jsp")? "active": ""}" href="/cinema">
                    <span class="icon"><i class="fa-regular fa-building fa-lg"></i></span><br/>Hệ thống rạp
                </a>
                <a class="nav-link px-5 ${fn:endsWith(url, "theater-form.jsp")? "active": ""}" href="/cinema/theater-form">
                    <span class="icon"><i class="fa-regular fa-square-plus fa-lg"></i></span><br/>Thêm rạp
                </a>
                <a class="nav-link px-5 ${fn:endsWith(url, "statistical.jsp")? "active": ""}" href="/cinema/statistical">
                    <span class="icon"><i class="fa-solid fa-signal fa-lg"></i></span><br/>Thống kê
                </a>
                <a class="nav-link px-5 ${fn:endsWith(url, "cinema-info.jsp")? "active": ""}"" href="/cinema/info">
                    <span class="icon"><i class="fa-solid fa-info fa-lg"></i></span><br/>Thông tin
                </a>
            </div>
        </div>
    </div>
</nav>
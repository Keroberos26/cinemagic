<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thống kê | ${cinema.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/cinema-nav.jsp" %>

            <div class="container-lg py-3">
                <h1 class="mb-3">Thống kê</h1>
                <div class="row g-4">
                    <div class="col-12 col-md-5">
                        <div class="d-flex flex-column flex-sm-row flex-md-column gap-4 h-100">
                            <div class="flex-grow-1">
                                <div class="box-content h-100">
                                    <div class="flexitem flexbetween">
                                        <div class="left">
                                            <span class="icon fs-1 text-success">
                                                <i class="fa-solid fa-money-bill"></i>
                                            </span>
                                        </div>
                                        <div class="right text-end">
                                            <p>Doanh thu</p>
                                            <h2 class="price"><fmt:formatNumber value="${incomeCine}" pattern="#,###" /></h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="flex-grow-1">
                                <div class="box-content h-100">
                                    <div class="flexitem flexbetween">
                                        <div class="left">
                                            <span class="icon fs-1 text-danger">
                                                <i class="fa-solid fa-chart-pie"></i>
                                            </span>
                                        </div>
                                        <div class="right text-end">
                                            <p>Đặt vé</p>
                                            <h2>${countCine} lượt</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-7 order-md-first">
                        <div class="box-content">
                            <select class="form-select" id="chartBy">
                                <option value="year" selected >Doanh thu theo năm</option>
                                <option value="month">Doanh thu theo tháng</option>
                                <option value="day">Doanh thu 7 ngày gần nhất</option>
                            </select>
                            <canvas id="revenueChart"></canvas>
                        </div>
                    </div>

                    <div class="col-12 ">
                        <div class="box-content"> 
                            <h3>Top rạp phim theo doanh thu</h3>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead class="thead-light">
                                        <tr>
                                            <th scope="col">Top</th>
                                            <th scope="col">Rạp</th>
                                            <th scope="col">Địa chỉ</th>
                                            <th scope="col">Tỉnh / Thành phố</th>
                                            <th scope="col">Doanh thu</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${theaterList}" var="t" varStatus="rank">
                                            <tr>
                                                <td>${rank.count}</td>
                                                <td><a href="/cinema?post=true&id=">${t.key.name}</a></td>
                                                <td>${t.key.street}</td>
                                                <td>${t.key.city}</td>
                                                <td class="price">${t.value}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/dashboard.js"></script>
    </body>
</html>

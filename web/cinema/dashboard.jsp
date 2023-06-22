<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>
                
                <div class="container-md py-4">
                    <div class="wrapper">
                        <div class="row g-4">
                            <div class="col-12 col-lg">
                                <div class="box-content">
                                    <div class="flexitem flexbetween">
                                        <div class="left">
                                            <span class="icon fs-1 text-success">
                                                <i class="fa-solid fa-money-bill"></i>
                                            </span>
                                        </div>
                                        <div class="right text-end">
                                            <p>Doanh thu</p>
                                            <h2 class="price"><fmt:formatNumber value="10000000" pattern="#,###" /></h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg">
                                <div class="box-content">
                                    <div class="flexitem flexbetween">
                                        <div class="left">
                                            <span class="icon fs-1 text-danger">
                                                <i class="fa-solid fa-chart-pie"></i>
                                            </span>
                                        </div>
                                        <div class="right text-end">
                                            <p>Đặt vé</p>
                                            <h2>123 lượt</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row g-3 mt-5">
                            <div class="col-12 col-lg-7 col-xl-8">
                                <div class="box-content">
                                    <select class="form-select">
                                        <option selected >Doanh thu theo năm</option>
                                        <option>Doanh thu theo tháng</option>
                                        <option value="1">Doanh thu theo tuần</option>
                                    </select>
                                    <canvas id="revenueChart"></canvas>
                                </div>
                            </div>
                            <div class="col-12 col-lg-5 col-xl-4">
                                <div class="box-content">
                                    <h3>Phim được coi nhiều nhất trong tháng</h3>
                                    <div class="table-responsive">
                                        <table class="table align-middle">
                                            <c:forEach items="${movieList}" var="m" varStatus="rank">
                                                <tr>
                                                    <td class="fs-1 pe-2">${rank.count}</td>
                                                    <td>
                                                        <div class="movie-card showtimes-card">
                                                            <div class="poster">
                                                                <div class="bg-img thumbnail" style="background-image: url(${m.poster});">
                                                                </div>
                                                                <div class="age-restricted age-${m.age}"><span class="badge">${m.age}</span></div>
                                                            </div>
                                                            <div class="content main-links">
                                                                <h4 class="title">${m.title}</h4>
                                                                <p class=" genre mini-text">${m.genres}</p>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>           
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script>
            var revenue = document.getElementById('revenueChart');
            const labels = ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
            

            var barChart = new Chart(revenue, {
            type: 'bar',
            data: {
                labels: labels, // Nhãn của các cột
                datasets: [{
                label: 'Data',
                data: [10, 20, 15, 21, 32, 12, 43, 54, 21, 43, 21, 64], // Dữ liệu của các cột
                backgroundColor: 'rgba(75, 192, 192, 0.6)' // Màu nền của các cột
                }]
            },
            options: {
                responsive: true,
                scales: {
                y: {
                    beginAtZero: true
                }
                }
            }
            });
        </script>
    </body>
</html>

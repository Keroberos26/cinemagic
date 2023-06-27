<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard | ${cinema.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/cinema-nav.jsp" %>

            <div class="container-lg py-3">
                <h1>Thống kê</h1>
                <div class="row g-4">
                    <div class="col-12 col-md-6 order-md-last">
                        <div class="row g-3">
                            <div class="col-6 col-md-12">
                                <div class="box-content">
                                    
                                </div>
                            </div>
                            <div class="col-6 col-md-12">
                                <div class="box-content">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="box-content">
                            1<select class="form-select" id="chartBy">
                                <option value="year" selected >Doanh thu theo năm</option>
                                <option value="month">Doanh thu theo tháng</option>
                            </select>
                            <canvas id="revenueChart"></canvas>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>

        <%@include file="/general/script.jsp" %>
    </body>
</html>

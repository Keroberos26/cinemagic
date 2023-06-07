<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý lịch chiếu | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Chi tiết lịch chiếu</h1>
                        <a href="/cinema/showtime-form" class="btn btn-success"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm lịch chiếu</a>
                    </div>

                    <div class="box-content">
                        <div class="d-flex gap-2">
                            <div class="form-floating mb-3">
                                <input type="date" class="form-control filter" id="showdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="<%=new java.util.Date()%>"/>"/>
                                <label for="showdate">Ngày chiếu</label>
                            </div>
                            <div class="form-floating">
                                <select class="form-select filter" id="sortRoom">
                                    <option value="">Không</option>
                                    <option value="asc">A &#8594; Z</option>
                                    <option value="desc">Z &#8594; A</option>
                                </select>
                                <label for="sortRoom">Phòng</label>
                            </div>
                            <div class="form-floating">
                                <select class="form-select filter" id="sortTime">
                                    <option value="">Không</option>
                                    <option value="asc">Sớm đến trễ</option>
                                    <option value="desc">Trễ đến sớm</option>
                                </select>
                                <label for="sortTime">Giờ bắt đầu</label>
                            </div>
                            <div class="form-floating">
                                <select class="form-select filter" id="sortMovie">
                                    <option value="">Không</option>
                                    <option value="asc">A &#8594; Z</option>
                                    <option value="desc">Z &#8594; A</option>
                                </select>
                                <label for="sortMovie">Phim</label>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table class="table align-middle mb-0 bg-white">
                                <thead class="bg-secondary text-white">
                                    <tr>
                                        <th>Phim</th>
                                        <th>Bắt đầu</th>
                                        <th>Kết thúc</th>
                                        <th>Giá vé</th>
                                        <th>Phòng</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/showtimes-mng.js"></script>
    </body>
</html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý phim chiếu | Admin CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/admin-sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/admin-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Danh sách phim</h1>
                        <a href="/admin/movie-form" class="btn btn-success"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm phim</a>
                    </div>

                    <div class="box-content">
                        <div class="row g-4 movie-filter align-items-center mb-4">
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
                        <div class="table-responsive">
                            <table class="table align-middle text-center">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Ảnh</th>
                                        <th>Tên / Thể loại</th>
                                        <th>Đạo diễn</th>
                                        <th>Diễn viên</th>
                                        <th>Ngày công chiếu</th>
                                        <th>Thời lượng</th>
                                        <th>Quốc gia</th>
                                        <th>Độ tuổi</th>
                                    </tr>
                                </thead>
                                <tbody class="movie-list">
                                    
                                </tbody>
                            </table>
                        </div>
                        <div class="paging">
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/movies.js"></script>
    </body>
</html>

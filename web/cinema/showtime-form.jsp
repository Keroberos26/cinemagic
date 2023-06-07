<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>${param.id == null ? "Thêm" : "Chỉnh sửa"} lịch chiếu | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>

    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Lịch chiếu</h1>
                    </div>

                    <div class="box-content">
                        <form action="/cinema/showtime-form?id=${param.id}" method="post" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="date" class="form-label">Chọn phim</label>
                                <button type="button" class="form-control text-start" data-bs-toggle="modal" data-bs-target="#movieModal">
                                    ${st.movie.title==null?"Chọn phim...":st.movie.title}
                                </button>
                                <input type="hidden" name="sltMovie" value="${st.movie.id}">

                                <div class="modal fade" id="movieModal" tabindex="-1" aria-labelledby="movieModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-4" id="movieModalLabel">Chọn phim</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body overflow-auto pt-0" style="height: 75vh">
                                                <div class="position-sticky bg-white top-0 z-1 py-3 border-bottom">
                                                    <div class="row g-3 gy-sm-0 mb-3">
                                                        <div class="col">
                                                            <div class="form-floating">
                                                                <input type="search" class="form-control search" id="searchMovie" placeholder="Tên phim">
                                                                <label for="searchMovie">Tên phim</label>
                                                            </div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-floating">
                                                                <select class="form-select search" id="filStatus">
                                                                    <option value="">Tất cả</option>
                                                                    <option value="I">Đang chiếu</option>
                                                                    <option value="S">Sắp chiếu</option>
                                                                    <option value="P">Đã chiếu</option>
                                                                </select>
                                                                <label for="filStatus">Lọc theo phim</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="list">
                                                    <c:forEach items="${movieList}" var="m">           
                                                        <div class="movie-card custom-option ${st.movie.id==m.id?"selected":""}" movie-id="${m.id}" data-bs-dismiss="modal">
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
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col">
                                    <label for="date" class="form-label">Ngày chiếu</label>
                                    <input type="date" class="form-control" id="date" name="txtDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${st.showdate}"/>" required>
                                </div>
                                <div class="col">
                                    <label for="time" class="form-label">Giờ bắt đầu</label>
                                    <input type="time" class="form-control" id="time" name="txtTime" value="<fmt:formatDate pattern="HH:mm" value="${st.starttime}"/>" required>
                                </div>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col">
                                    <label for="price" class="form-label">Giá cơ bản</label>
                                    <input type="number" min="0" class="form-control" id="price" name="txtPrice" value="${st.basePrice}" placeholder="45000" required>
                                </div>
                                <div class="col">
                                    <label for="room" class="form-label">Phòng chiếu</label>
                                    <select id="room" class="form-select" name="sltRoom" required>
                                        <option ${param.id==null?"selected":""} disabled value="">Chọn phòng...</option>
                                        <c:forEach items="${roomList}" var="r">
                                            <option value="${r.id}" ${st.room.id==r.id?"selected":""}>${r.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="text-center">
                                <c:if test="${param.id == null}">
                                    <button type="submit" class="btn btn-success" name="action" value="add">Thêm</button>
                                </c:if>
                                <c:if test="${param.id != null}">
                                    <button type="submit" class="btn btn-danger" name="action" value="delete">Xóa</button>
                                    <button type="submit" class="btn btn-warning" name="action" value="update">Cập nhật</button>
                                </c:if>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/showtime-form.js"></script>
    </body>

</html>
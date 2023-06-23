<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>${param.id == null ? "Thêm" : "Chỉnh sửa"} phim chiếu | Admin</title>
        <%@include file="/general/head.jsp" %>
    </head>

    <body>
        <div id="page" class="site">
            <%@include file="/general/admin-sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/admin-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Phim chiếu</h1>
                    </div>

                    <div class="box-content">
                        <form action="/admin/movie-form" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="movieId" value="${param.id}">

                            <div class="row g-3">
                                <div class="col-12 col-md-6">
                                    <label for="fileImg" class="form-label">Poster</label>
                                    <div class="imgFile-form mx-auto">
                                        <img src="${m.poster}" onerror="this.src='/assets/img/no-poster.jpg'" class="poster">
                                        <input type="file" name="fileImg" id="fileImg" accept="image/gif, image/jpeg, image/png">
                                        <label for="fileImg" class="icon"><i class="fas fa-camera"></i></label>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="trailer" class="form-label">Trailer (Youtube URL)</label>
                                        <input type="url" class="form-control" id="trailer" name="txtTrailer" value="${m.trailer}" required>
                                        <div class="trailer-wrapper mt-3">
                                            <iframe src="" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6">
                                    <div class="form-group mb-3">
                                        <label for="title" class="form-label">Tên phim</label>
                                        <input type="text" class="form-control" id="title" name="txtTitle" value="${m.title}" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="directors" class="form-label">Đạo diễn</label>
                                        <input type="text" class="form-control" id="directors" name="txtDirectors" value="${m.directors}" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="actors" class="form-label">Diễn viên</label>
                                        <input type="text" class="form-control" id="actors" name="txtActors" value="${m.actors}">
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="genre" class="form-label">Thể loại</label>
                                        <input type="text" class="form-control" id="genre" value="${m.genres}" data-bs-toggle="modal" data-bs-target="#genresModal" maxlength="0" required>
                                    </div>
                                    <div class="row g-3">
                                        <div class="col-12 col-lg-6">
                                            <div class="form-group mb-3">
                                                <label for="releaseDate" class="form-label">Ngày chiếu</label>
                                                <input type="date" class="form-control" id="releaseDate" name="txtReleaseDate" value="${m.releaseDate}" required>
                                            </div>
                                        </div>
                                        <div class="col-12 col-lg-6">
                                            <div class="form-group mb-3">
                                                <label for="duration" class="form-label">Thời lượng (phút)</label>
                                                <input type="number" min="0" class="form-control" id="duration" name="txtDuration" value="${m.duration}" required>
                                            </div>
                                        </div>
                                        <div class="col-12 col-lg-6">
                                            <div class="form-group mb-3">
                                                <label for="age" class="form-label">Độ tuổi giới hạn</label>
                                                <input type="number" min="0" class="form-control" id="age" name="txtAge" value="${m.age}" required>
                                            </div>
                                        </div>
                                        <div class="col-12 col-lg-6">
                                            <div class="form-group mb-3">
                                                <label for="country" class="form-label">Quốc gia</label>
                                                <input type="text" min="0" class="form-control" id="country" name="txtCountry" value="${m.country}" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description" class="form-label">Mô tả</label>
                                        <textarea class="form-control" name="txtDescription" id="description" rows="5">${m.description}</textarea>
                                    </div>
                                    <div class="modal fade" id="genresModal" data-bs-keyboard="false" tabindex="-1" aria-labelledby="genresModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="genresModalLabel">Thể loại</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row row-cols-3 fs-5">
                                                        <c:forEach items="${genreList}" var="g" varStatus="c">
                                                            <div class="form-group">
                                                                <label class="form-check-label w-100 p-3" for="${g.id}">
                                                                    <input class="form-check-input" type="checkbox" name="chkGenres" value="${g.id}" id="${g.id}" ${m.genres.contains(g.name)?"checked":""}>
                                                                    <span>${g.name}</span>
                                                                </label>
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
        <script src="/assets/js/movie-form.js"></script>
    </body>

</html>
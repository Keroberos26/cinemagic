<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Dashboard | ${theater.jsp}</title>
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
                        <form action="/home" method="post" class="needs-validation" novalidate>
                            <div class="custom-select mb-3">
                                <p class="form-label">Phim chiếu</p>
                                <details class="form-control" required>
                                    <summary class="radios">
                                        <input type="radio" name="movie" id="default" title="Chọn phim..." checked
                                               disabled required>
                                        <input type="radio" name="movie" id="item1" title="Movie 1" value="1"
                                               required>
                                        <input type="radio" name="movie" id="item2" title="Movie 2" value="2"
                                               required>
                                        <input type="radio" name="movie" id="item3" title="Movie 3" value="3"
                                               required>
                                        <input type="radio" name="movie" id="item4" title="Movie 4" required>
                                        <input type="radio" name="movie" id="item5" title="Movie 5" required>
                                    </summary>
                                    <ul class="list">
                                        <li>
                                            <label for="item1" class="flexitem gap-2">
                                                <div style="width: 10%;">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                </div>
                                                <div class="flex-grow-1 text-capitalize">Con nhót mót chồng</div>
                                            </label>
                                        </li>
                                        <li>
                                            <label for="item2" class="flexitem gap-2">
                                                <div style="width: 10%;">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                </div>
                                                <div class="flex-grow-1 text-capitalize">Con nhót mót chồng</div>
                                            </label>
                                        </li>
                                        <li>
                                            <label for="item3" class="flexitem gap-2">
                                                <div style="width: 10%;">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                </div>
                                                <div class="flex-grow-1 text-capitalize">Con nhót mót chồng</div>
                                            </label>
                                        </li>
                                        <li>
                                            <label for="item4" class="flexitem gap-2">
                                                <div style="width: 10%;">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                </div>
                                                <div class="flex-grow-1 text-capitalize">Con nhót mót chồng</div>
                                            </label>
                                        </li>
                                        <li>
                                            <label for="item5" class="flexitem gap-2">
                                                <div style="width: 10%;">
                                                    <div class="bg-img thumbnail"
                                                         style="background-image: url(/assets/img/poster/connhot.jpg);">
                                                    </div>
                                                </div>
                                                <div class="flex-grow-1 text-capitalize">Con nhót mót chồng</div>
                                            </label>
                                        </li>
                                    </ul>
                                </details>
                            </div>

                            <div class="mb-3">
                                <label for="date" class="form-label">Ngày chiếu</label>
                                <input type="date" class="form-control" id="date" name="date" required>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col">
                                    <label for="start" class="form-label">Giờ bắt đầu</label>
                                    <input type="time" class="form-control" id="start" name="start" required>
                                </div>
                                <div class="col">
                                    <label for="end" class="form-label">Giờ kết thúc</label>
                                    <input type="time" class="form-control" id="end" name="end" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="room" class="form-label">Phòng chiếu</label>
                                <select id="room" class="form-select" name="room" required>
                                    <option selected disabled value="">Choose...</option>
                                    <option>Phòng 1</option>
                                    <option>Phòng 1</option>
                                    <option>Phòng 1</option>
                                    <option>Phòng 1</option>
                                </select>
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
    </body>

</html>
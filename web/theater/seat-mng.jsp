<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Quản lý ghế ngồi | Tên rạp</title>
        <%@include file="/general/head.jsp" %>
    </head>

    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content show">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md">
                    <div class="flexitem flexbetween mb-4 py-4">
                        <h1>Bản đồ ghế phòng (...) </h1>
                    </div>

                    <div class="row">
                        <div class="col-12 col-lg-8">
                            <div class="flexbetween mb-3">
                                <div class="btn-group">
                                    <div class="btn btn-danger" onclick="deleteRow()"><span class="icon"><i class="fa-solid fa-diagram-predecessor fa-rotate-180"></i></span>Xóa hàng</div>
                                    <div class="btn btn-success" onclick="addRow()"><span class="icon"><i class="fa-solid fa-diagram-next"></i></span>Thêm hàng</div>
                                </div>
                                <div class="btn-group">
                                    <div class="btn btn-warning text-white" onclick="deleteCol()"><span class="icon"><i class="fa-solid fa-arrow-right-from-bracket fa-flip-horizontal"></i></span>Xóa cột</div>
                                    <div class="btn btn-info text-white" onclick="addCol()"><span class="icon"><i class="fa-solid fa-diagram-next fa-rotate-270"></i></span>Thêm cột</div>
                                </div>
                            </div>

                            <div class="seats-mng">
                                <div class="screen">Màn hình</div>
                                <div class="seats">
                                    <div class="seats-map">
                                        <c:forEach begin="0" end="10">
                                            <ul class="seats-row">
                                                <c:forEach begin="0" end="10" varStatus="s">
                                                    <li class="seat" data-bs-toggle="tooltip" data-bs-title="A02"></li>
                                                </c:forEach>
                                            </ul>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-lg-4">
                            <div class="box-content">
                                <h3 class="text-center">Điều chỉnh ghế</h3>
                                <form>
                                    <div class="row mb-3 align-items-center">
                                        <div class="col-auto">
                                            <div class="form-check form-switch text-center">
                                                <input class="form-check-input" type="checkbox" role="switch" id="isSeat">
                                                <label class="form-check-label" for="isSeat">Ghế</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="row g-3 align-items-center">
                                                <div class="col-auto">
                                                    <label for="seatNum">Số ghế</label>
                                                </div>
                                                <div class="col">
                                                    <input type="text" class="form-control" name="seatNum" id="seatNum">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <p>Loại ghế</p>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type" id="normal" value="N" checked>
                                            <label class="form-check-label" for="normal">Thường</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type" id="vip" value="V">
                                            <label class="form-check-label" for="vip">VIP</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type" id="couple" value="C">
                                            <label class="form-check-label" for="couple">Đôi</label>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="/assets/js/seat-editor.js"></script>
        <%@include file="/general/script.jsp" %>
    </body>

</html>
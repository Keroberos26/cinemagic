<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thêm rạp phim | ${cinema.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/cinema-nav.jsp" %>

            <div class="container-lg py-3">
                <h1 class="mb-3">Thêm rạp chiếu</h1>
                
                <div class="box-content">
                    <form action="/cinema/theater-form" method="post" class="needs-validation" novalidate>
                        <div class="row g-3 mb-3">
                            <div class="col-12">
                                <label for="name" class="form-label">Tên</label>
                                <input type="text" class="form-control" name="txtName" id="name">
                            </div>
                            <div class="col-12 col-md-4">
                                <label for="city" class="form-label">Thành phố / Tỉnh</label>
                                <select class="form-select" aria-label="Default select example" id="city" name="sltCity">
                                </select>
                            </div>
                            <div class="col-12 col-md-4">
                                <label for="district" class="form-label">Quận / Huyện</label>
                                <select class="form-select" aria-label="Default select example" id="district" name="sltDistrict">
                                </select>
                            </div>
                            <div class="col-12 col-md-4">
                                <label for="ward" class="form-label">Phường / Xã</label>
                                <select class="form-select" aria-label="Default select example" id="ward" name="sltWard">
                                </select>
                            </div>
                            <div class="col-12">
                                <label for="street" class="form-label">Đường</label>
                                <input type="text" class="form-control" name="txtStreet" id="street">
                            </div>
                        </div>

                        ${error}

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

        <%@include file="/general/script.jsp" %>
    </body>
</html>

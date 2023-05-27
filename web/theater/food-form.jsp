<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Chỉnh sửa bắp nước | Tên rạp</title>
        <%@include file="/general/head.jsp" %>
    </head>

    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Bắp nước</h1>
                    </div>

                    <div class="box-content">
                        <form action="" method="post" class="needs-validation row g-4" novalidate>
                            <div class="col-12">
                                <label for="combo" class="form-label">Tên combo</label>
                                <input type="text" class="form-control" name="combo" id="combo" required>
                            </div>
                            <div class="col-6">
                                <label for="image" class="form-label">Ảnh minh họa</label>
                                <input type="file" class="form-control" name="image" id="image" accept="image/gif, image/png, image/jpeg" required>
                            </div>
                            <div class="col-6">
                                <label for="price" class="form-label">Giá</label>
                                <input type="number" min="0" class="form-control" name="price" id="price" required>
                            </div>
                            <div class="text-center">
                                <div class="text-center">
                                    <c:if test="${param.id == null}">
                                        <button type="submit" class="btn btn-success" name="action" value="add">Thêm</button>
                                    </c:if>
                                    <c:if test="${param.id != null}">
                                        <button type="submit" class="btn btn-danger" name="action" value="delete">Xóa</button>
                                        <button type="submit" class="btn btn-warning" name="action" value="update">Cập nhật</button>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
    </body>

</html>
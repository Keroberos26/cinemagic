<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý phòng chiếu | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md py-4">
                    <h1 class="mb-3">Thông tin rạp phim</h1>
                
                <div class="box-content">
                    <form action="/cinema/theater-info" method="post" class="needs-validation"  enctype="multipart/form-data" novalidate>
                        <div class="row g-3 mb-3">
                            <div class="col-12">
                                <label for="fileImg" class="form-label">Ảnh</label>
                                <div class="imgFile-form mx-auto">
                                    <img src="${theater.image}" onerror="this.src='/assets/img/no-theater.jpg'" class="thumbnail">
                                    <input type="file" name="fileImg" id="fileImg" accept="image/gif, image/jpeg, image/png">
                                    <label for="fileImg" class="icon"><i class="fas fa-camera"></i></label>
                                </div>
                            </div>
                            <div class="col-12">
                                <label for="name" class="form-label">Tên</label>
                                <input type="text" class="form-control" name="txtName" id="name" value="${theater.name}">
                            </div>
                            <div class="col-12 col-md-4">
                                <label for="city" class="form-label">Thành phố / Tỉnh</label>
                                <select class="form-select" aria-label="Default select example" id="city" name="sltCity" data-city="${theater.city}">
                                    <option selected disabled value="">Chọn khu vực</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-4">
                                <label for="district" class="form-label">Quận / Huyện</label>
                                <select class="form-select" aria-label="Default select example" id="district" name="sltDistrict" data-district="${theater.district}">
                                    <option selected disabled value="">Chọn khu vực</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-4">
                                <label for="ward" class="form-label">Phường / Xã</label>
                                <select class="form-select" aria-label="Default select example" id="ward" name="sltWard" data-ward="${theater.ward}">
                                    <option selected disabled value="">Chọn khu vực</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label for="street" class="form-label">Đường</label>
                                <input type="text" class="form-control" name="txtStreet" id="street" value="${theater.street}">
                            </div>
                        </div>

                        ${error}

                        <div class="text-center">
                            <button type="submit" class="btn btn-danger" name="action" value="delete">Xóa</button>
                            <button type="submit" class="btn btn-primary" name="action" value="update">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/my-account.js"></script>
        <script>
            $('button[value="delete"]').click(function(e) {
                if(!confirm('Bạn có chắc xóa rạp phim này khỏi hệ thống?')) {
                    e.preventDefault();
                }
            })
        </script>
    </body>
</html>

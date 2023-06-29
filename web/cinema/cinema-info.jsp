<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thông tin hệ thống | ${cinema.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/cinema-nav.jsp" %>

            <div class="container-lg py-3">
                <h1 class="mb-3">Thông tin hệ thống</h1>

                <div class="box-content">
                    <form action="/cinema/info" method="post" enctype="multipart/form-data">
                        <div class="row g-3 align-items-center">
                            <div class="col-12 col-md-4">
                                <div class="imgFile-form mx-auto">
                                    <img src="${cinema.logo}" class="rounded-0" onerror="this.src='/assets/img/mascot.png'" alt="C">
                                    <input type="file" name="fileImg" id="fileImg" accept="image/gif, image/jpeg, image/png">
                                    <label for="fileImg" class="icon"><i class="fas fa-camera"></i></label>
                                </div>
                            </div>
                            <div class="col-12 col-md-8">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email liên kết</label>
                                    <input type="email" class="form-control" id="email" value="${acc.email}" disabled readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="name" class="form-label">Tên</label>
                                    <input type="name" class="form-control" id="name" value="${cinema.name}" name="txtName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="description" class="form-label">Mô tả</label>
                                    <textarea name="txtDescription" id="description" rows="5" class="form-control">${cinema.description}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-danger" name="action" value="delete" id="delete">Xóa</button>
                            <button type="submit" class="btn btn-primary" name="action" value="update">Lưu</button>
                        </div>
                    </form>
                </div>                
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script>
            $('#delete').click(function(e){
                if (!confirm('Bạn có chắc chắn xóa hệ thống này không?')) {
                    e.preventDefault();
                }
            })
        </script>
    </body>
</html>

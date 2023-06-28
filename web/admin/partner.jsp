<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý đối tác | Admin CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/admin-sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/admin-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Đối tác</h1>
                        <a id="addCombo" data-bs-toggle="modal" data-bs-target="#partnerModal" class="btn btn-success"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm đối tác</a>
                    </div>

                    <div class="modal fade" id="partnerModal" tabindex="-1" data-bs-backdrop="static" aria-labelledby="partnerModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-md">
                            <div class="modal-content">
                                <form action="/admin/cinema" method="post" enctype="multipart/form-data">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="partnerModalLabel">Thêm đối tác</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <input type="hidden" name="cinemaId">
                                        <div class="mb-4">
                                            <div class="imgFile-form mx-auto">
                                                <img src="/assets/img/mascot.png" onerror="this.src='/assets/img/mascot.png'" alt="C">
                                                <input type="file" name="fileImg" id="fileImg" accept="image/gif, image/jpeg, image/png">
                                                <label for="fileImg" class="icon"><i class="fas fa-camera"></i></label>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <input type="text" class="form-control" id="name" name="txtName" placeholder="..." required>
                                            <label for="name">Tên đối tác</label>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <div class="form-floating">
                                                <select class="form-select" id="account" name="sltAccount" required>
                                                    <c:forEach begin="0" end="5" varStatus="c">
                                                        <option value="${c.count}">email ${c.count}</option>
                                                    </c:forEach>
                                                </select>
                                                <label for="account">Tài khoản liên kết</label>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <textarea class="form-control" placeholder="Description" id="description" name="txtDescription" style="height: 5em;" required></textarea>
                                            <label for="description">Mô tả</label>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-danger d-none" name="action" value="delete" id="delete">Xóa</button>
                                        <button type="submit" class="btn btn-primary" name="action" value="add" id="save">Lưu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="box-content">
                        <div class="row row-cols-2 row-cols-sm-3 row-cols-md-5 row-cols-lg-6">
                            <c:forEach items="${cinemaList}" var="c">
                                <div class="col">
                                    <a href="#" class="p-3 update" data-bs-toggle="modal" data-bs-target="#partnerModal" cinema-id="${c.id}" acc-id="5">
                                        <div class="bg-img" style="padding-bottom: 100%; background-image: url(${c.logo});"></div>
                                        <h4 class="text-primary text-center mt-2">${c.name}</h4>
                                        <p class="text-center mini-text text-2-line">${c.description}</p>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/partner.js"></script>
    </body>
</html>

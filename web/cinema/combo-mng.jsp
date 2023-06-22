<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý bắp nước | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Danh sách bắp nước</h1>
                        <a href="#" class="btn btn-success" id="addCombo" data-bs-toggle="modal" data-bs-target="#comboModal"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm món</a>
                    </div>

                    <div class="modal fade" id="comboModal" tabindex="-1" data-bs-backdrop="static" aria-labelledby="comboModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-md">
                            <div class="modal-content">
                                <form action="/cinema/combo" method="post" enctype="multipart/form-data">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="comboModalLabel">Thêm combo</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <input type="hidden" name="comboId">
                                        <div class="mb-4">
                                            <div class="imgFile-form mx-auto">
                                                <img src="/assets/img/popcorn.png" onerror="this.src='/assets/img/popcorn.png'" alt="C">
                                                <input type="file" name="fileImg" id="fileImg" accept="image/gif, image/jpeg, image/png">
                                                <label for="fileImg" class="icon"><i class="fas fa-camera"></i></label>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <input type="text" class="form-control" id="name" name="txtName" placeholder="Combo ..." required>
                                            <label for="name">Tên Combo</label>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <input type="number" min="0" class="form-control" id="price" name="txtPrice" placeholder="100000" required>
                                            <label for="price">Giá</label>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <textarea class="form-control" placeholder="Description" id="description" name="txtDescription" style="height: 5em;"></textarea>
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
                        <div class="table-responsive">
                            <table class="table align-middle text-center">
                                <thead class="table-dark">
                                    <tr>
                                        <th>STT</th>
                                        <th>Ảnh</th>
                                        <th>Tên</th>
                                        <th>Đơn giá</th>
                                        <th>&nbsp</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${comboList}" var="combo" varStatus="c">
                                        <tr combo-id="${combo.id}">
                                            <td>${c.count}</td>
                                            <td><img src='${combo.image != null ? combo.image : "/assets/img/popcorn.png"}' onerror="this.src='/assets/img/popcorn.png'" alt="Popcorn" style="width: 100px; height: 100px; min-width: 100px;"></td>
                                            <td class="text-start"><span class="name">${combo.name}</span><br/><span class="mini-text">${combo.description}</span></td>
                                            <td><span class="price" price="${combo.price}"><fmt:formatNumber value="${combo.price}" pattern="#,###" /></span></td>
                                            <td>
                                                <a href="#"  data-bs-toggle="modal" data-bs-target="#comboModal" class="btn btn-warning text-white update"><span class="icon"><i class="fa-solid fa-pen-to-square"></i></span></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/combo-mng.js"></script>
    </body>
</html>

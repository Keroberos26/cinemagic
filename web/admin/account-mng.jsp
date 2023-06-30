<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý tài khoản | Admin CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/admin-sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/admin-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Quản lý tài khoản</h1>
                        <a href="#" class="btn btn-success" id="add" data-bs-toggle="modal" data-bs-target="#accModal"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Tạo tài khoản</a>
                    </div>

                    <div class="modal fade" id="accModal" tabindex="-1" data-bs-backdrop="static" aria-labelledby="accModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-md">
                            <div class="modal-content">
                                <form action="/admin/account" method="post">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="accModalLabel">Thêm tài khoản</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <input type="hidden" name="accId">
                                        <div class="form-floating mb-4">
                                            <input type="email" class="form-control" id="email" name="txtEmail" placeholder="example@mail.com" autocomplete="off" required>
                                            <label for="email">Email</label>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <input type="password" class="form-control" id="password" name="txtPassword" placeholder="***" autocomplete="new-password" required>
                                            <label for="password">Mật khẩu</label>
                                        </div>
                                        <div class="text-center mb-4">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="role" id="radUser" value="U" required checked>
                                                <label class="form-check-label" for="radUser">Người dùng</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="role" id="radCinema" value="C">
                                                <label class="form-check-label" for="radCinema">Rạp phim</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="role" id="radAdmin" value="A">
                                                <label class="form-check-label" for="radAdmin">Admin</label>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <input type="text" class="form-control" id="name" name="txtName" placeholder="Tên">
                                            <label for="name">Tên</label>
                                        </div>
                                        <div class="form-floating mb-4">
                                            <input type="tel" class="form-control" id="phone" name="txtPhone" placeholder="***">
                                            <label for="phone">Số điện thoại</label>
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
                                        <th>Email</th>
                                        <th>Tên</th>
                                        <th>SĐT</th>
                                        <th>Vai trò</th>
                                        <th>&nbsp</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${accList}" var="a" varStatus="c">
                                        <tr acc-id="${a.id}">
                                            <td>${c.count}</td>
                                            <td class="email">${a.email}</td>
                                            <td class="name">${a.name}</td>
                                            <td class="phone">${a.phone}</td>
                                            <td class="role">
                                                <c:choose>
                                                    <c:when test="${a.role eq 'U'}">
                                                        <span role="U">Người dùng</span>
                                                    </c:when>
                                                    <c:when test="${a.role == 'C'}">
                                                        <span role="C">Rạp phim</span>
                                                    </c:when>
                                                    <c:when test="${a.role == 'A'}">
                                                        <span role="A">Admin</span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a href="#"  data-bs-toggle="modal" data-bs-target="#accModal" class="btn btn-warning text-white update"><span class="icon"><i class="fa-solid fa-pen-to-square"></i></span></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="paging">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/account-mng.js"></script>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý phòng chiếu | Tên rạp</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Danh sách phòng chiếu</h1>
                        <a href="#" class="btn btn-success" id="insertRow"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm phòng chiếu</a>
                    </div>

                    <div class="box-content">
                        <div class="table-responsive">
                            <table class="table align-middle">
                                <thead class="table-dark">
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên phòng</th>
                                        <th class="text-center">Chỉnh sửa ghế ngồi</th>
                                        <th class="text-center">Xóa</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${roomList}" var="r" varStatus="c">
                                        <tr>
                                            <td>${c.count}</td>
                                            <td><input type="text" class="form-control-plaintext can-edit" value="${r.name}" readonly></td>
                                            <td class="text-center"><a href="#" class="btn btn-primary"><span class="icon"><i class="fa-solid fa-couch"></i></span></a></td>
                                            <td class="text-center"><a href="#" class="btn btn-danger" id="deleteRow"><span class="icon"><i class="fa-regular fa-trash-can"></i></span></a></td>
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
    </body>
</html>

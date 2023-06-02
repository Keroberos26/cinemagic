<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý bắp nước | Tên rạp</title>
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
                        <a href="#" class="btn btn-success"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm món</a>
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
                                    <c:forEach begin="1" end="10" varStatus="c">
                                        <tr>
                                            <td>${c.count}</td>
                                            <td><img src="/assets/img/popcorn.png" alt="Popcorn" style="width: 100px; height: 100px; min-width: 100px;"></td>
                                            <td class="text-start">Nấm Caramel 60oz</td>
                                            <td><span class="price">44000</span></td>
                                            <td>
                                                <a href="#" class="btn btn-warning text-white"><span class="icon"><i class="fa-solid fa-pen-to-square"></i></span></a>
                                                <a href="#" class="btn btn-danger"><span class="icon"><i class="fa-solid fa-trash-can"></i></span></a>
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
    </body>
</html>

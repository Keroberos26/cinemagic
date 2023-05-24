<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý thức ăn và nước uống | Tên rạp</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <div class="header">
                    <button class="btn btn-primary sidebar-button">
                        <span class="icon">
                            <i class="fa-solid fa-bars"></i>
                        </span>
                    </button>
                </div>

                <div class="container-md py-4">
                    <div class="flexitem flexbetween mb-4">
                        <h1>Danh sách phòng chiếu</h1>
                        <a href="#" class="btn btn-success"><span class="icon"><i class="fa-solid fa-circle-plus"></i></span>Thêm món</a>
                    </div>

                    <div class="box-content">
                        <div class="table-responsive">
                            <table class="table align-middle">
                                
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard | Tên hệ thống</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <div class="container-lg py-3">
                <h1 class="text-center text-primary">Chọn rạp</h1>

                <div class="row gx-4 gy-5 my-2">
                    <c:forEach items="${theaterList}" var="t">
                        <div class="col-12 col-md-6 col-lg-4">
                            <div class="position-relative">
                                <div class="bg-img bg-cover" style="background-image: url('${t.image}');"></div>
                                <div class="text-center bg-blacken p-2 text-white">
                                    <h5 class="text-uppercase">${t.name}</h5>
                                    <span class="text-capitalize text-2-line">${t.street}, ${t.ward}, ${t.district}, ${t.city}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
    </body>
</html>

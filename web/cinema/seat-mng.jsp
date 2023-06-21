<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Quản lý ghế ngồi | ${theater.name}</title>
        <%@include file="/general/head.jsp" %>
    </head>

    <body>
        <div id="page" class="site">
            <%@include file="/general/sidebar.jsp" %>

            <div class="main-content">
                <%@include file="/general/theater-header.jsp" %>

                <div class="container-md">
                    <div class="flexitem flexbetween mb-4 py-4">
                        <h1>Bản đồ ghế ${room.name} </h1>
                    </div>

                    <div class="row">
                        <div class="col-12 col-lg-8">
                            <div class="seats seats-mng">
                                <div class="seats-selection">
                                    <div class="front">
                                        <div class="screen"></div>
                                        <span>Màn hình</span>
                                    </div>
                                    <div class="seats-wrapper">
                                        <div class="seats-map">
                                            <c:forEach items="${seatMap}" var="seatRow" varStatus="row">
                                                <ul class="seats-row">
                                                    <c:set var="isCouple" value="${false}"/>
                                                    <c:forEach items="${seatRow}" var="seat" varStatus="col">
                                                        <c:choose>
                                                            <c:when test="${!isCouple}">
                                                                <c:choose>
                                                                    <c:when test="${seat.type == 'N'}">
                                                                        <li class="seat seat-normal" seat-id="${seat.id}" seat-type="N" seat-row="${row.count}" seat-col="${col.count}">${seat.seatNum}</li>
                                                                    </c:when>
                                                                    <c:when test="${seat.type == 'V'}">
                                                                        <li class="seat seat-vip" seat-id="${seat.id}" seat-type="V" seat-row="${row.count}" seat-col="${col.count}">${seat.seatNum}</li>
                                                                    </c:when>
                                                                    <c:when test="${seat.type == 'C'}">
                                                                        <li class="seat seat-couple" seat-id="${seat.id}" seat-type="C" seat-row="${row.count}" seat-col="${col.count}">${seat.seatNum}</li>
                                                                        <c:set var="isCouple" value="${true}"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <li class="space" seat-row="${row.count}" seat-col="${col.count}"></li>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="isCouple" value="${false}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="legend mt-5" style="pointer-events: none;">
                                        <div class="row">
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="seat-normal"></li>
                                                    <span>Ghế thường</span>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="seat-vip"></li>
                                                    <span>Ghế VIP</span>
                                                </div>
                                            </div>  
                                            <div class="col-auto">
                                                <div class="flexitem">
                                                    <li class="seat-couple"></li>
                                                    <span>Ghế Sweetbox</span>
                                                </div>
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-lg-4">
                            <div class="box-content">
                                <h3 class="text-center">Điều chỉnh ghế</h3>
                                <div class="seat-form">
                                    <div class="row mb-3 align-items-center">
                                        <div class="col-auto">
                                            <div class="form-check form-switch text-center">
                                                <input class="form-check-input" type="checkbox" role="switch" id="isSeat" disabled>
                                                <label class="form-check-label" for="isSeat">Ghế</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="row g-3 align-items-center">
                                                <div class="col-auto">
                                                    <label for="seatNum">Số ghế</label>
                                                </div>
                                                <div class="col">
                                                    <input type="text" class="form-control" id="seatNum" disabled>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <p>Loại ghế</p>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type" id="normal" value="N" disabled>
                                            <label class="form-check-label" for="normal">Thường</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type" id="vip" value="V" disabled>
                                            <label class="form-check-label" for="vip">VIP</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type" id="couple" value="C" disabled>
                                            <label class="form-check-label" for="couple">Sweetbox</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary" disabled>Lưu thay đổi</button>
                                </div>
                            </div>
                            <div class="btn-group mt-5 d-flex">
                                <div class="btn btn-success text-white" id="insertRow"><span class="icon"><i class="fa-solid fa-diagram-next"></i></span>Thêm hàng</div>
                                <div class="btn btn-info text-white" id="insertCol"><span class="icon"><i class="fa-solid fa-diagram-next fa-rotate-270"></i></span>Thêm cột</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/seat-editor.js"></script>
    </body>

</html>
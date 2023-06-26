<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thanh toán - ${theater.name} - ${ticket.showtime.movie.title} - <fmt:formatDate value="${ticket.showtime.starttime}" pattern="HH:mm dd/MM/yyyy"/></title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="ticketing-steps">
                    <div class="container-lg">
                        <div class="row align-items-center">
                            <div class="col">
                                <div class="text-center">
                                    <span class="icon"><i class="ri-layout-grid-fill ri-lg"></i></span>
                                    <div>Chọn ghế</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center">
                                    <span class="icon"><i class="ri-shopping-bag-3-fill ri-lg"></i></span>
                                    <div>Bắp nước</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center  text-primary">
                                    <span class="icon"><i class="ri-bank-card-fill ri-lg"></i></span>
                                    <div>Thanh toán</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="text-center">
                                    <span class="icon"><i class="ri-article-fill ri-lg"></i></span>
                                    <div>Thông tin vé</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container-lg py-3">
                    <div class="payment">
                        <div class="row g-0">
                            <div class="col-12 col-md-5">
                                <div class="qr-code h-100 border p-3 bg-special text-white flexitem">
                                    <form action="/vnpayajax" id="frmCreateOrder" method="post" class="w-100">        
                                        <input type="hidden" name="amount" value="${ticket.getSeatPrice() + ticket.getCombosPrice()}">
                                        <div class="form-group mb-3">
                                            <label for="email" class="form-label">Email</label>
                                            <input type="email" class="form-control" id="email" name="txtEmail" value="${acc.email}" ${acc != null ? "readonly disabled" : ""} required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label for="name" class="form-label">Họ tên</label>
                                            <input type="text" class="form-control" id="name" name="txtName" value="${acc.name}" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label for="phone" class="form-label">Số điện thoại</label>
                                            <input type="tel" class="form-control" id="phone" name="txtPhone" value="${acc.phone}">
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100 mt-3">Thanh toán</button>
                                    </form>
                                </div>
                            </div>
                            <div class="col-12 col-md-7 order-first">
                                <div class="ticket-detail p-3">    
                                    <div class="flexitem">
                                        <div class="age-restricted age-${ticket.showtime.movie.age}">
                                            <span class="badge rounded-pill">${ticket.showtime.movie.age == "0" ? "P" : ticket.showtime.movie.age}</span>
                                        </div>
                                        <h5 class="mb-0 ms-2">${ticket.showtime.movie.title}</h5>
                                    </div>
                                    <div class="row g-3 py-4 mt-4 b-dashed bt-1 b">
                                        <div class="col-8">
                                            <div class="mini-text text-uppercase fw-medium">Thời gian</div>
                                            <div class="fw-bold"><fmt:formatDate value="${ticket.showtime.starttime}" pattern="HH:mm"/> 
                                                ~ <fmt:formatDate value="${ticket.showtime.endtime}" pattern="HH:mm"/></div>
                                        </div>
                                        <div class="col-4 text-end">
                                            <div class="mini-text text-uppercase fw-medium">Ngày chiếu</div>
                                            <div class="fw-bold"><fmt:formatDate value="${ticket.showtime.starttime}" pattern="dd/MM/yyyy"/></div>
                                        </div>
                                        <div class="col-8">
                                            <div class="mini-text text-uppercase fw-medium">Rạp</div>
                                            <div class="fw-bold">${theater.name}</div>
                                            <div class="mini-text text-capitalize">${theater.street}, ${theater.ward}, ${theater.district}, ${theater.city}</div>
                                        </div>
                                        <div class="col-4 text-end">
                                            <div class="mini-text text-uppercase fw-medium">Phòng chiếu</div>
                                            <div class="fw-bold">${ticket.showtime.room.name}</div>
                                        </div>
                                    </div>

                                    <div class="py-4 b-dashed bt-1 b">
                                        <div class="flexbetween align-items-end">
                                            <div>
                                                <div class="mini-text text-uppercase fw-medium">Ghế</div>
                                                <div class="fw-bold">${ticket.getSeatNum()}</div>
                                            </div>
                                            <span class="price fw-bold"><fmt:formatNumber value="${ticket.getSeatPrice()}" pattern="#,###" /></span>
                                        </div>
                                        <div class="my-2">
                                            <c:if test="${ticket.combos != null}">
                                                <div class="mini-text text-uppercase fw-medium">Bắp nước</div>
                                                <ul>
                                                    <c:forEach items="${ticket.combos}" var="e">
                                                        <li class="flexbetween align-items-end py-1">
                                                            <div class="fw-bold">${e.value} x ${e.key.name}</div>
                                                            <span class="price fw-bold"><fmt:formatNumber value="${e.value * e.key.price}" pattern="#,###" /></span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="py-4 b-dashed bt-1 b">
                                        <div class="flexbetween align-items-end">
                                            <div class="fw-bold">Tổng tiền</div>
                                            <span class="price fw-bold"><fmt:formatNumber value="${ticket.getSeatPrice() + ticket.getCombosPrice()}" pattern="#,###" /></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>


            <%@include file="/general/footer.jsp" %>

        </div>

        <%@include file="/general/script.jsp" %>
        <script src="/assets/js/booking.js"></script>
        $("#frmCreateOrder").submit(function () {
            var postData = $("#frmCreateOrder").serialize();
            var submitUrl = $("#frmCreateOrder").attr("action");
            $.ajax({
                type: "POST",
                url: submitUrl,
                data: postData,
                dataType: 'JSON',
                success: function (x) {
                    if (x.code === '00') {
                        if (window.vnpay) {
                            vnpay.open({width: 768, height: 600, url: x.data});
                        } else {
                            location.href = x.data;
                        }
                        return false;
                    } else {
                        alert(x.Message);
                    }
                }
            });
            return false;
        });
    </body>
</html>


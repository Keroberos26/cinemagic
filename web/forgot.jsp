<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Thay đổi mật khẩu | CineMagic</title>
            <%@include file="/general/head.jsp" %>
        </head>

        <body style="background: linear-gradient(to right, #b92b27, #1565c0)">
            <div class="container">
                <div class="row justify-content-center vh-100 align-items-center">
                    <div class="col-md-6">
                        <div class="box-content">
                            <div class="mx-auto mb-5" style="width: 25%;">
                                <img src="assets/img/favicon.png" alt="CineMagic">
                            </div>
                            <h1 class="text-center mb-5">Thay đổi mật khẩu</h1>
                            <form action="/forgot" method="post" class="needs-validation" novalidate>
                                <input type="hidden" name="email" value="${param.token}">
                                <input type="hidden" name="period" value="${param.period}">
                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" id="password" placeholder="Password"
                                        name="password"
                                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$"
                                        maxlength="32" required>
                                    <label for="password">Mật khẩu</label>
                                    <div class="invalid-feedback">
                                        Mật khẩu phải chứa 8 - 32 ký tự, bao gồm ít nhất 1 chữ hoa [A - Z], 1 chữ thường
                                        [a
                                        - z], 1 chữ số và 1 ký tự đặc biệt (@ $ ! % * ? &).
                                    </div>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" id="confirm" placeholder="Password"
                                        name="confirm"
                                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$"
                                        maxlength="32" required>
                                    <label for="confirm">Xác nhận mật khẩu</label>
                                    <div class="invalid-feedback">
                                        Mật khẩu không khớp!
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test='${param.error == "period"}'>
                                        <div class="alert alert-danger" role="alert">
                                            Yêu cầu đổi mật khẩu đã hết hạn!
                                        </div>
                                    </c:when>
                                    <c:when test='${param.error == "password"}'>
                                        <div class="alert alert-danger" role="alert">
                                            Mật khẩu phải chứa 8 - 32 ký tự, bao gồm ít nhất 1 chữ hoa [A - Z], 1 chữ
                                            thường
                                            [a
                                            - z], 1 chữ số và 1 ký tự đặc biệt (@ $ ! % * ? &).
                                        </div>
                                    </c:when>
                                    <c:when test='${param.error == "confirm"}'>
                                        <div class="alert alert-danger" role="alert">
                                            Mật khẩu xác nhận không khớp!
                                        </div>
                                    </c:when>
                                    <c:when test='{param.error == "other"}'>
                                        <div class="alert alert-danger" role="alert">
                                            Đổi mật khẩu không thành công!
                                        </div>
                                    </c:when>
                                </c:choose>
                                <button class="w-100 btn btn-primary" name="action" value="reset">Xác nhận</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        <%@include file="/general/script.jsp" %>

        </html>
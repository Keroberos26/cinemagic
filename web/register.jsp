<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng ký tài khoản | CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="account-form">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="row g-4">
                                <div class="col-md">
                                    <h1 class="text-center mb-4">Đăng ký</h1>
                                    <form action="/register" method="post" class="needs-validation" novalidate>
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Email</label>
                                            <input type="email" class="form-control" id="email" name="txtEmail" required>
                                            <div class="invalid-feedback">
                                                Định dạng email không hợp lệ.
                                            </div>
                                        </div>
                                        ${errorEmail}
                                        <div class="mb-3">
                                            <label for="password" class="form-label">Mật khẩu</label>
                                            <input type="password" class="form-control" id="password" name="txtPassword" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$" maxlength="32">
                                            <div class="invalid-feedback">
                                                Mật khẩu phải chứa 8 - 32 ký tự, bao gồm ít nhất 1 chữ hoa [A - Z], 1 chữ thường [a - z], 1 chữ số và 1 ký tự đặc biệt (@ $ ! % * ? &).
                                            </div>
                                        </div>
                                        ${errorPass}
                                        <div class="mb-3">
                                            <label for="confirm" class="form-label">Nhập lại mật khẩu</label>
                                            <input type="password" class="form-control" id="confirm" name="txtConfirm" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$" maxlength="32">
                                            <div class="invalid-feedback">
                                                Mật khẩu không khớp
                                            </div>
                                        </div>
                                        ${errorConfirm}
                                        <button type="submit" class="btn btn-primary w-100 mt-3">Tạo tài khoản</button>
                                    </form>

                                    <p class="mt-3 mini-text text-center">hoặc</p>
                                    <a href="#" class="btn btn-facebook w100 d-flex"><span class="icon"><i class="ri-facebook-fill"></i></span>Đăng nhập bằng Facebook</a>

                                    <p class="mini-text mt-3 text-center">Đã có tài khoản? <strong><a href="/login">Đăng nhập</a></strong></p>
                                </div>
                                <div class="col-md">
                                    <div class="d-flex justify-content-center align-items-center h-100">
                                        <img src="/assets/img/mascot.png" alt="Đăng ký">
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
    </body>
</html>

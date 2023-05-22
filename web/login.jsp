<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập | CineMagic</title>
        <%@include file="/general/head.jsp" %>

    </head>
    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="account-form">
                    <div class="container-lg">
                        <div class="wrapper">
                            <div class="row">
                                <div class="col-md-5">
                                    <h1 class="text-center mb-4">Đăng nhập</h1>
                                    <form action="#" method="post">
                                        <div class="mb-3 form-floating">
                                            <input type="email" class="form-control" id="email" name="txtEmail"
                                                   placeholder="name@example.com" required>
                                            <label for="email" class="form-label">Email</label>
                                            <div class="invalid-feedback">
                                                Email chưa được đăng ký!
                                            </div>
                                        </div>

                                        <div class="mb-3 form-floating">
                                            <input type="password" class="form-control" id="password" name="txtPassword"
                                                   placeholder="Password" required maxlength="32">
                                            <label for="password" class="form-label">Mật khẩu</label>
                                            <div class="invalid-feedback">
                                                Mật khẩu không đúng!
                                            </div>
                                        </div>

                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" type="checkbox" name="checkRemember" id="remember">
                                                <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
                                            </div>

                                            <p class="text-end"><a href="#">Quên mật khẩu?</a></p>
                                        </div>


                                        <button type="submit" class="btn btn-primary w-100 mt-3">Đăng nhập</button>
                                    </form>

                                    <p class="mt-3 mini-text text-center">hoặc</p>
                                    <a href="#" class="btn btn-facebook w100 d-flex"><span class="icon"><i
                                                class="ri-facebook-fill"></i></span>Đăng nhập bằng Facebook</a>

                                    <p class="mini-text mt-3 text-center">Chưa có tài khoản? <strong><a href="#">Đăng ký
                                                ngay</a></strong></p>
                                </div>
                                <div class="col-md-7">
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

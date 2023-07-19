<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                                        <form action="/login" method="post">
                                            <div class="mb-3 form-floating">
                                                <input type="email" class="form-control ${errorEmail}" id="email"
                                                    name="txtEmail" placeholder="name@example.com" required>
                                                <label for="email" class="form-label">Email</label>
                                                <div class="invalid-feedback">
                                                    Email chưa được đăng ký!
                                                </div>
                                            </div>

                                            <div class="mb-3 form-floating">
                                                <input type="password" class="form-control ${errorPass}" id="password"
                                                    name="txtPassword" placeholder="Password" required maxlength="32">
                                                <label for="password" class="form-label">Mật khẩu</label>
                                                <div class="invalid-feedback">
                                                    Mật khẩu không đúng!
                                                </div>
                                            </div>

                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="form-check mb-3">
                                                    <input class="form-check-input" type="checkbox" name="checkRemember"
                                                        id="remember">
                                                    <label class="form-check-label" for="remember">Ghi nhớ đăng
                                                        nhập</label>
                                                </div>

                                                <p class="text-end"><a href="#" data-bs-toggle="modal"
                                                        data-bs-target="#forgotPassModal">Quên mật khẩu?</a></p>
                                            </div>

                                            <button type="submit" class="btn btn-primary w-100 mt-3">Đăng nhập</button>
                                        </form>

                                        <p class="mt-3 mini-text text-center">hoặc</p>

                                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8080/google&response_type=code
                                       &client_id=888385144954-a2i14sajvi08ich6pec4edhs4ndu1h9p.apps.googleusercontent.com&approval_prompt=force"
                                            class="btn btn-google w-100 d-flex">
                                            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/2008px-Google_%22G%22_Logo.svg.png"
                                                alt="Google" width="16" height="16">
                                            <span class="ms-2">Đăng nhập bằng Google</span>
                                        </a>


                                        <p class="mini-text mt-3 text-center">Chưa có tài khoản? <strong><a
                                                    href="/register">Đăng ký
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

                    <!-- Modal -->
                    <div class="modal fade" id="forgotPassModal" tabindex="-1" aria-labelledby="forgotPassModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header h5 text-white bg-primary justify-content-center">
                                    Password Reset
                                </div>
                                <div class="modal-body">
                                    <form action="/forgot" method="post">
                                        <p class="py-2">
                                            Enter your email address and we'll send you an email
                                            with instructions to
                                            reset
                                            your password.
                                        </p>
                                        <div class="form-group mb-3">
                                            <label class="form-label" for="typeEmail">Email
                                                input</label>
                                            <input type="email" id="typeEmail" class="form-control" name="email"
                                                required />
                                        </div>
                                        <button type="submit" name="action" value="sendOTP"
                                            class="btn btn-primary w-100">Reset
                                            password</button>
                                    </form>
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
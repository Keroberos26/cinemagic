<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Trang không tìm thấy | CineMagic</title>
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
                                    <div class="col-md-5 align-self-center text-center">
                                        <span style="font-size: 120px;">404</span>
                                        <h1>Úi, trang không tìm thấy</h1>
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
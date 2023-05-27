<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Xác minh email | CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>
    <body>

        <div id="page" class="site">
            <div class="verification">
                <div class="container height-100">
                    <div class="d-flex justify-content-center align-items-center h-100">
                        <div class="position-relative"> 
                            <div class="card p-2 text-center"> 
                                <h6>Vui lòng nhập OTP một lần để<br>xác minh tài khoản của bạn</h6> 
                                <div> 
                                    <span>Một mã OTP đã được gửi vào </span><small>***n212*@gmail.com</small> 
                                </div> 
                                <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2"> 
                                    <input class="m-2 text-center form-control rounded" type="text" id="first" maxlength="1" /> 
                                    <input class="m-2 text-center form-control rounded" type="text" id="second" maxlength="1" /> 
                                    <input class="m-2 text-center form-control rounded" type="text" id="third" maxlength="1" /> 
                                    <input class="m-2 text-center form-control rounded" type="text" id="fourth" maxlength="1" /> 
                                    <input class="m-2 text-center form-control rounded" type="text" id="fifth" maxlength="1" /> 
                                    <input class="m-2 text-center form-control rounded" type="text" id="sixth" maxlength="1" /> 
                                </div> 
                                <div class="mt-4"> 
                                    <button class="btn btn-danger px-4 validate">Xác nhận</button> 
                                </div> 
                            </div> <div class="card-2"> 
                                <div class="content d-flex justify-content-center align-items-center"> 
                                    <span>Không nhận được mã?</span> <a href="#" class="text-decoration-none ms-3">Gửi lại</a> 
                                </div> 
                            </div> 
                        </div>
                    </div> 
                </div>
            </div>
        </div>


        <%@include file="/general/script.jsp" %>
    </body>
</html>

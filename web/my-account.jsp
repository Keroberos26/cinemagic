<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Trang cá nhân | CineMagic</title>
        <%@include file="/general/head.jsp" %>
    </head>

    <body>
        <div id="page" class="site">
            <%@include file="/general/header.jsp" %>

            <main>
                <div class="top-img mb-5">
                    <img src="/assets/img/background/tix-banner.png" alt="Banner" class="h-auto w-100">
                    <div class="container">
                        <div class="wrapper mt-n5">
                            <div class="row align-items-end mb-3">
                                <div class="col-auto">
                                    <div class="avatar avatar-xxl">
                                        <img src="${acc.avatar == null ? "/assets/img/no-avatar.png" : acc.avatar}" alt="Avatar"
                                             class="avatar-img rounded-circle border border-4">
                                    </div>
                                </div>
                                <div class="col mb-md-3">
                                    <h1>${acc.name == null ? acc.id : acc.name}</h1>
                                </div>
                            </div>
                            <ul class="nav nav-underline gap-4" id="myTab" role="tablist"
                                style="--bs-emphasis-color: var(--primary-color); --bs-link-color: var(--dark-color); --bs-link-hover-color: var(--dark-color);">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link ${accInfo}" data-bs-toggle="tab" data-bs-target="#accInfo"
                                            type="button" role="tab">Tài khoản</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link ${changePass}" data-bs-toggle="tab" data-bs-target="#changePass"
                                            type="button" role="tab">Đổi mật khẩu</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" data-bs-toggle="tab" data-bs-target="#history"
                                            type="button" role="tab">Lịch sử đặt vé</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="my-account mb-5">
                    <div class="container">
                        <div class="wrapper">
                            <div class="tab-content">
                                <div class="tab-pane fade ${accInfo}" id="accInfo" role="tabpanel" tabindex="0">
                                    <div class="card">
                                        <div class="card-body">
                                            <form action="my-account" method="post" class="row g-4" enctype="multipart/form-data">
                                                <div class="col-12">
                                                    <label for="email" class="form-label">Email</label>
                                                    <input type="email" class="form-control" value="${acc.email}" id="email" disabled readonly>
                                                </div>
                                                <div class="col-12 col-md-6">
                                                    <label for="name" class="form-label">Họ và Tên</label>
                                                    <input type="text" class="form-control" name="txtName" value="${acc.name}" id="name">
                                                </div>
                                                <div class="col-12 col-md-6">
                                                    <label for="city" class="form-label">Khu vực</label>
                                                    <select class="form-select" aria-label="Default select example" id="city" name="sltCity">
                                                        <option selected disabled value="">Chọn khu vực</option>
                                                        <option value="TP. HCM">TP. HCM</option>
                                                        <option value="Đà Nẵng">Đà Nẵng</option>
                                                        <option value="Hà Nội">Hà Nội</option>
                                                        <option value="Huế">Huế</option>
                                                    </select>
                                                </div>
                                                <div class="col-12 col-md-6">
                                                    <label for="phone" class="form-label">Số điện thoại</label>
                                                    <input type="tel" class="form-control" name="txtPhone" value="${acc.phone}" id="phone">
                                                </div>
                                                <div class="col-12 col-md-6">
                                                    <label for="avatar" class="form-label">Ảnh đại diện</label>
                                                    <input class="form-control" type="file" name="fileAvt" id="avatar" accept="image/gif, image/jpeg, image/png">
                                                </div>
                                                <div class="col-12 text-center">
                                                    <button type="submit" class="btn btn-primary" name="action" value="updateInfo">Cập nhật</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade ${changePass}" id="changePass" role="tabpanel" tabindex="0">
                                    <div class="card">
                                        <div class="card-body">
                                            <form action="/my-account" method="post" class="needs-validation" novalidate>
                                                <div class="mb-3">
                                                    <label for="curPassword" class="form-label">Mật khẩu hiện tại</label>
                                                    <input type="password" class="form-control ${errorCurPass}" id="curPassword" name="txtCurPassword" required>
                                                    <div class="invalid-feedback">
                                                        Mật khẩu không đúng
                                                    </div>
                                                </div>
        
                                                <div class="mb-3">
                                                    <label for="password" class="form-label">Mật khẩu mới</label>
                                                    <input type="password" class="form-control ${errorNewPass}" id="password" name="txtPassword" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$" maxlength="32">
                                                    <div class="invalid-feedback">
                                                        Mật khẩu phải chứa 8 - 32 ký tự, bao gồm ít nhất 1 chữ hoa [A - Z], 1 chữ thường [a - z], 1 chữ số và 1 ký tự đặc biệt (@ $ ! % * ? &).
                                                    </div>
                                                </div>
        
                                                <div class="mb-3">
                                                    <label for="confirm" class="form-label">Nhập lại mật khẩu</label>
                                                    <input type="password" class="form-control ${errorCfmPass}" id="confirm" name="txtConfirm" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$" maxlength="32">
                                                    <div class="invalid-feedback">
                                                        Mật khẩu không khớp
                                                    </div>
                                                </div>
        
                                                <button type="submit" class="btn btn-primary w-100 mt-3" name="action" value="changePass">Cập nhật</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="history" role="tabpanel" tabindex="0">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>

            <%@include file="/general/footer.jsp" %>
        </div>

        <!--<script src="/assets/js/my-account.js"></script>-->
        <%@include file="/general/script.jsp" %>
    </body>

</html>
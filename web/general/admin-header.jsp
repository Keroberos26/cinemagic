<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header flexbetween">
    <button class="btn btn-primary sidebar-button">
        <span class="icon">
            <i class="fa-solid fa-bars"></i>
        </span>
    </button>
    <div class="dropdown">
        <a class="dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <div class="avatar">
                <img src="${acc.avatar != null ? acc.avatar : "/assets/img/no-avatar.png"}" alt="Avatar"
                     class="avatar-img rounded-circle border border-4 d-block">
            </div>
        </a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">
                <span class="icon"><i class="fa-regular fa-user"></i></span>Tài khoản của tôi
            </a></li>
            <li><a class="dropdown-item" href="#">
                <span class="icon"><i class="ri-logout-box-line"></i></span>Đăng xuất
            </a></li>
        </ul>
    </div>
</div>

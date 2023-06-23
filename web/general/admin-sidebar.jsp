<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <div class="container-fluid p-3">
        <a href="/cinema">
            <div class="row align-items-center g-2">
                <div class="col-3">
                    <div class="sidebar-brand-icon">
                        <img src="/assets/img/favicon.png" alt="CineMagic">
                    </div>
                </div>
                <div class="col-9">
                    <div class="sidebar-brand-name">
                        <img src="/assets/img/logo.png" alt="CineMagic">
                    </div>
                </div>
            </div>
        </a>
    </div>

    <nav>
        <ul class="main-links">
            <li>
                <a href="/admin/dashboard" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-gauge-high fa-lg"></i></span>Dashboard Admin
                </a>

                <hr>
            </li>
            <li>
                <a href="/admin/movies" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-clapperboard fa-lg"></i></span>Quản lý phim chiếu
                </a>
            </li>
            <li>
                <a href="/admin/cinema" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-handshake fa-lg"></i></span>Quản lý đối tác
                </a>
            </li>
            <li>
                <a href="/admin/account-mng" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-user-gear fa-lg"></i></span>Quản lý tài khoản
                </a>

                <hr>
            </li>
            <li>
                <a href="#" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-clock-rotate-left fa-lg"></i></span>Lịch sử đặt vé
                </a>
                <hr>
            </li>
            <li>
                <a href="#" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-building-user fa-lg"></i></span>Thông tin rạp phim
                </a>
            </li>
            <li>
                <a href="/logout" class="p-3">
                    <span class="icon pe-4"><i class="fa-solid fa-right-from-bracket fa-lg"></i></span>Đăng xuất
                </a>
            </li>
        </ul>
    </nav>
</div>

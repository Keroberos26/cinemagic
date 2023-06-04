package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("acc");
        Cookie cookieEmail = new Cookie("cEmail", null);
        Cookie cookiePass = new Cookie("cPass", null);
        Cookie cookieTheater = new Cookie("cTheater", null);

        // Đặt thời gian sống của cookie thành 0
        cookieEmail.setMaxAge(0);
        cookiePass.setMaxAge(0);
        cookieTheater.setMaxAge(0);

        // Đặt lại cookie trong phản hồi
        resp.addCookie(cookieEmail);
        resp.addCookie(cookiePass);
        resp.addCookie(cookieTheater);
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

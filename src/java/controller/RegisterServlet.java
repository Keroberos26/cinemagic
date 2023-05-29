package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        String email = req.getParameter("txtEmail").trim().toLowerCase();
        String password = req.getParameter("txtPassword");
        String confirm = req.getParameter("txtConfirm");

        String error = "";
        String errorMsg = "";

            if (!dao.isExisted(email)) {
                if (dao.validatePassword(password)) {
                    if (password.equals(confirm)) {
                        dao.addAccount(email, password, "U");
                        req.setAttribute("txtEmail", email);
                        req.setAttribute("txtPassword", password);
                        req.getRequestDispatcher("/login?doPost=true").forward(req, resp);
//                    resp.sendRedirect("login");
                    } else {
                        error = "errorConfirm";
                        errorMsg = "Mật khẩu không khớp";
                    }
                } else {
                    error = "errorPass";
                    errorMsg = "Mật khẩu phải chứa 8 - 32 ký tự, bao gồm ít nhất 1 chữ hoa [A - Z], 1 chữ thường [a - z], 1 chữ số và 1 ký tự đặc biệt (@ $ ! % * ? &).";
                }
            } else {
                error = "errorEmail";
                errorMsg = "Email đã được đăng ký.";
            }

        if (!error.isBlank()) {
            req.setAttribute(error, "<div class=\"alert alert-warning\" role=\"alert\">\n"
                    + "" + errorMsg + "\n"
                    + "</div>");
            doGet(req, resp);
        }
    }

}

package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("doPost") == null) {
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        String email = req.getParameter("txtEmail").trim().toLowerCase();
        String password = req.getParameter("txtPassword");

        String error = "";
        String errorMsg = "";
        
        if (!dao.isExisted(email)) {
            if (dao.validatePassword(password)) {
                dao.addAccount(email, password, "U");
                Account acc = new Account();
                acc.setEmail(email);
                acc.setPassword(password);
                HttpSession session = req.getSession();
                session.setAttribute("registerAcc", acc);
                req.setAttribute("txtEmail", email);
                req.setAttribute("txtPassword", password);
                req.getRequestDispatcher("/login?doPost=true").forward(req, resp);
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

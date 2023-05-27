package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("doPost") == null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        String remember = req.getParameter("checkRemember");

        AccountDAO dao = new AccountDAO();

        String error = "";
        if (dao.isExisted(email)) {
            Account acc = dao.login(email, password);
            if (acc != null) {
                req.getSession().setAttribute("acc", acc);
                resp.sendRedirect("/");
            } else {
                error = "errorPass";
            }
        } else {
            error = "errorEmail";
        }
        if (!error.isBlank()) {
            req.setAttribute(error, "is-invalid");
            doGet(req, resp);
        }
    }

}

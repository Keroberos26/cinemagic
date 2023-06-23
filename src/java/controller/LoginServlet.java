package controller;

import dao.AccountDAO;
import dao.CinemaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.CinemaSystem;

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
        HttpSession session = req.getSession();
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        String remember = req.getParameter("checkRemember");
        AccountDAO dao = new AccountDAO();

        String error = "";
        if (dao.isExisted(email)) {
            Account acc = dao.login(email, password, false);
            if (acc != null) {
                if (remember != null) {
                    Cookie cEmail = new Cookie("cEmail", email);
                    Cookie cPass = new Cookie("cPass", dao.encrypPassword(password));

                    cEmail.setMaxAge(60 * 60 * 24 * 30);//30 days
                    cPass.setMaxAge(60 * 60 * 24 * 30);

                    resp.addCookie(cEmail);
                    resp.addCookie(cPass);
                }
                switch (acc.getRole()) {
                    case "C":
                        CinemaDAO cineDao = new CinemaDAO();
                        CinemaSystem cinema = cineDao.getCinemaByAccountId(acc.getId());
                        session.setAttribute("cinema", cinema);
                        resp.sendRedirect("/cinema");
                        break;
                    case "U":
                        resp.sendRedirect("/");
                    case "A":
                        resp.sendRedirect("/admin/dashboard");
                        break;
                    default:
                        throw new AssertionError();
                }
                session.setAttribute("acc", acc);
                
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

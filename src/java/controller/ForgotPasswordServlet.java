package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import util.Email;
import util.Encryption;

public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String email = req.getParameter("email");
        AccountDAO dao = new AccountDAO();

        switch (action) {
            case "send":
                String token = Encryption.AESEncrypt(email);
                PrintWriter out = resp.getWriter();

                if (dao.isExisted(email)) {
                    Email.send(email, "Đổi mật khẩu trên CineMagic", Email.resetPassword(token));
                    out.write("<div class=\"alert alert-success\" role=\"alert\">\n"
                            + "  Đã gửi yêu cầu thành công! Vui lòng kiểm tra email.\n"
                            + "</div>");
                } else {
                    out.write("<div class=\"alert alert-danger\" role=\"alert\">\n"
                            + "  Email chưa được đăng ký!\n"
                            + "</div>");
                }
                break;
            case "reset":
                String password = req.getParameter("password");
                String confirm = req.getParameter("confirm");
                String mail = Encryption.AESDecrypt(email);
                if (dao.validatePassword(password) && password.equals(confirm) && dao.resetPassword(mail, password)) {
                    resp.sendRedirect("/login");
                } else {
                    resp.sendRedirect("/forgot?token=" + email + "&error=true");
                }
                System.out.println(Encryption.AESDecrypt(email));
                break;
            default:
                throw new AssertionError();
        }
    }

}

package controller.admin;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccountServlet extends HttpServlet {    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accDao = new AccountDAO();
        
        req.setAttribute("accList", accDao.getAccounts());
        req.getRequestDispatcher("/admin/account-mng.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        String id = req.getParameter("accId");
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        String role = req.getParameter("role");
        String name = req.getParameter("txtName");
        String phone = req.getParameter("txtPhone");
        
        AccountDAO acc = new AccountDAO();
        
        switch (action) {
            case "add":
                acc.addAccount(email, password, role, name, phone);
                break;
            case "update":
                acc.updateAccount(name, phone, role, id);
                break;
            case "delete":
                acc.deleteAccount(id);
                break;
            default:
                throw new AssertionError();

        }
        resp.sendRedirect("/admin/account");
    }
    
}

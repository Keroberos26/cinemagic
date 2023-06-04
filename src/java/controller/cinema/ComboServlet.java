package controller.cinema;

import dao.ComboDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Theater;

public class ComboServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater =  (Theater)session.getAttribute("theater");
        ComboDAO comDao = new ComboDAO();

        req.setAttribute("comboList", comDao.getCombosByTheaterId(theater.getId()));
        req.getRequestDispatcher("/cinema/combo-mng.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

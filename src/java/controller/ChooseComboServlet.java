package controller;

import dao.ComboDAO;
import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Combo;
import model.Ticket;

public class ChooseComboServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Ticket ticket = (Ticket) session.getAttribute("ticket");

        ComboDAO comDao = new ComboDAO();
        req.setAttribute("comboList", comDao.getCombosByTheaterId(ticket.getShowtime().getRoom().getTheaterid()));
        TheaterDAO theDao = new TheaterDAO();
        req.setAttribute("theater", theDao.getTheaterById(ticket.getShowtime().getRoom().getTheaterid(), false));
        req.getRequestDispatcher("combo-selection.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        
        String[] combos = req.getParameterValues("combos[]");
        String[] quantity = req.getParameterValues("quantity[]");
        
        ComboDAO comDao = new ComboDAO();
        Map<Combo, Integer> map = null;
        if (combos != null && quantity != null && combos.length == quantity.length) {
            map = new LinkedHashMap<>();
            
            for (int i = 0; i < combos.length; i++) {
                int quan = Integer.parseInt(quantity[i]);
                if(quan != 0) {
                    Combo c = comDao.getComboById(combos[i]);
                    map.put(c, quan);
                }
            }
        }
        ticket.setCombos(map);
        PrintWriter out = resp.getWriter();
        DecimalFormat priceFormat = new DecimalFormat("#,###");
        out.write(priceFormat.format(ticket.getSeatPrice() + ticket.getCombosPrice()));
    }

}

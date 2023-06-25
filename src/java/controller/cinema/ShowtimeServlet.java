package controller.cinema;

import dao.ShowtimeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Showtime;
import model.Theater;

public class ShowtimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cinema/showtimes-mng.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");

        String room = req.getParameter("room");
        String time = req.getParameter("time");
        String title = req.getParameter("title");

        

        ShowtimeDAO showDao = new ShowtimeDAO();
        List<Showtime> list = showDao.getShowtimesByTheaterId(theater.getId(), room, time, title);

        PrintWriter out = resp.getWriter();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DecimalFormat priceFormat = new DecimalFormat("#,###");

        for (Showtime st : list) {
            out.write("<tr>\n"
                    + "     <td>\n"
                    + "         <div class=\"d-flex align-items-center\">\n"
                    + "             <img src=\"" + st.getMovie().getPoster() + "\" width=\"45\" height=\"63\">\n"
                    + "             <div class=\"ms-3\">\n"
                    + "                 <h5 class=\"text-capitalize\">" + st.getMovie().getTitle() + "</h5>\n"
                    + "             </div>\n"
                    + "         </div>\n"
                    + "     </td>\n"
                    + "     <td>" + timeFormat.format(st.getStarttime()) + "</td>\n"
                    + "     <td>" + timeFormat.format(st.getEndtime()) + "</td>\n"
                    + "     <td><span class=\"price\">" + priceFormat.format(st.getPriceN()) + "</span></td>\n"
                    + "     <td><span class=\"price\">" + priceFormat.format(st.getPriceV()) + "</span></td>\n"
                    + "     <td><span class=\"price\">" + priceFormat.format(st.getPriceC()) + "</span></td>\n"
                    + "     <td>" + st.getRoom().getName() + "</td>\n"
                    + "     <td class=\"text-center\">\n"
                    + "         <a href=\"/cinema/showtime-form?id=" + st.getId() + "\" class=\"text-warning\"><i class=\"fa-regular fa-pen-to-square\"></i></a>\n"
                    + "     </td>\n"
                    + "</tr>");
        }
    }

}

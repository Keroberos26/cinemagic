package controller.cinema;

import dao.MovieDAO;
import dao.RoomDAO;
import dao.ShowtimeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Movie;
import model.Showtime;
import model.Theater;

public class ShowtimeFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");

        String id = req.getParameter("id");
        if (id != null) {
            ShowtimeDAO showDao = new ShowtimeDAO();
            req.setAttribute("st", showDao.getShowtimeById(id));
        }

        RoomDAO roomDao = new RoomDAO();
        MovieDAO movDao = new MovieDAO();

        req.setAttribute("movieList", movDao.getAllMovies());
        req.setAttribute("roomList", roomDao.getRoomsByTheaterId(theater.getId()));
        req.getRequestDispatcher("/cinema/showtime-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        String movieid = req.getParameter("sltMovie");
        String date = req.getParameter("txtDate");
        String time = req.getParameter("txtTime");
        String price = req.getParameter("txtPrice");
        String roomid = req.getParameter("sltRoom");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        Date showdate = null;
        Time starttime = null;
        int basePrice = 0;
        if (date != null && time != null) {
            try {
                showdate = new Date(dateFormat.parse(date).getTime());
                starttime = new Time(timeFormat.parse(time).getTime());
                basePrice = Integer.parseInt(price);
            } catch (ParseException ex) {
                System.err.println("-----> Cannot cast! <-----");
            }
        }

        ShowtimeDAO showDao = new ShowtimeDAO();

        switch (action) {
            case "search":
                String status = req.getParameter("status");
                String title = req.getParameter("title");

                MovieDAO movDao = new MovieDAO();
                Showtime st = null;
                if (!id.isBlank())
                    st = showDao.getShowtimeById(id);

                List<Movie> movieList = movDao.getMoviesByStatus(status, title);
                PrintWriter out = resp.getWriter();

                for (Movie m : movieList) {
                    out.write("<div class=\"movie-card custom-option " + (st != null && st.getMovie().equals(m) ? "selected" : "") + "\" movie-id=\"" + m.getId() + "\" onclick=\"chooseMovie(this)\" data-bs-dismiss=\"modal\">\n"
                            + "     <div class=\"poster\">\n"
                            + "         <div class=\"bg-img thumbnail\" style=\"background-image: url('" + m.getPoster() + "');\">\n"
                            + "         </div>\n"
                            + "         <div class=\"age-restricted age-" + m.getAge() + "\"><span class=\"badge\">" + m.getAge() + "</span></div>\n"
                            + "     </div>\n"
                            + "     <div class=\"content main-links\">\n"
                            + "         <h4 class=\"title\">" + m.getTitle() + "</h4>\n"
                            + "         <p class=\"genre mini-text\">" + m.getGenres() + "</p>\n"
                            + "     </div>\n"
                            + "</div>");
                }
                break;

            case "add":
                showDao.addShowtime(movieid, showdate, starttime, basePrice, roomid);
                resp.sendRedirect("showtimes");
                break;
            case "update":
                showDao.updateShowtime(id, movieid, showdate, starttime, basePrice, roomid);
                resp.sendRedirect("showtimes");
                break;
            case "delete":
                showDao.deleteShowtime(id);
                resp.sendRedirect("showtimes");
                break;
            default:
                throw new AssertionError();
        }
    }

}

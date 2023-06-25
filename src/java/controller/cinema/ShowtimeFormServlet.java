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
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;
import model.Room;
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
        
        Calendar calendar = Calendar.getInstance();
        
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date future = calendar.getTime();
        
        Timestamp currentTimestamp = new Timestamp(future.getTime());
        
        RoomDAO roomDao = new RoomDAO();
        MovieDAO movDao = new MovieDAO();
        
        req.setAttribute("min", currentTimestamp);
        req.setAttribute("movieList", movDao.getAllMovies());
        req.setAttribute("roomList", roomDao.getRoomsByTheaterId(theater.getId()));
        req.getRequestDispatcher("/cinema/showtime-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("showId");
        String action = req.getParameter("action");
        String movieid = req.getParameter("sltMovie");
        String time = req.getParameter("txtTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String txtPriceN = req.getParameter("txtPriceN");
        String txtPriceV = req.getParameter("txtPriceV");
        String txtPriceC = req.getParameter("txtPriceC");
        String roomid = req.getParameter("sltRoom");

        Timestamp starttime = null;
        int priceN = 0, priceV = 0, priceC = 0;

        try {
            if (time != null) {
                Date parsedDate = sdf.parse(time);
                starttime = new Timestamp(parsedDate.getTime());
                priceN = Integer.parseInt(txtPriceN);
                priceV = Integer.parseInt(txtPriceV);
                priceC = Integer.parseInt(txtPriceC);

            }
        } catch (ParseException ex) {
            Logger.getLogger(ShowtimeFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Showtime st = null;
        ShowtimeDAO showDao = new ShowtimeDAO();
        String error = "";

        switch (action) {
            case "search":
                String status = req.getParameter("status");
                String title = req.getParameter("title");

                MovieDAO movDao = new MovieDAO();
                if (id != null && !id.isBlank()) {
                    st = showDao.getShowtimeById(id);
                }

                List<Movie> movieList = movDao.getMoviesByStatus(status, title);
                PrintWriter out = resp.getWriter();

                for (Movie m : movieList) {
                    out.write("<div class=\"movie-card custom-option " + (st != null && st.getMovie().equals(m) ? "selected" : "") + "\" movie-id=\"" + m.getId() + "\" data-bs-dismiss=\"modal\">\n"
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
                error = showDao.addShowtime(movieid, starttime, priceN, priceV, priceC, roomid);
                break;
            case "update":
                error = showDao.updateShowtime(id, movieid, starttime, priceN, priceV, priceC, roomid);
                break;
            case "delete":
                showDao.deleteShowtime(id);
                break;
            default:
                throw new AssertionError();
        }

        if (!action.equals("search")) {
            if (!error.isEmpty()) {
                req.setAttribute("error", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                        + "                         <span class=\"icon\"><i class=\"fa-solid fa-circle-exclamation\"></i></span>\n"
                        + "                          " + error + "\n"
                        + "                      </div>");
                RoomDAO roomDao = new RoomDAO();
                MovieDAO movDao = new MovieDAO();
                Movie m = movDao.getMovieById(movieid);
                Room r = roomDao.getRoomById(roomid);
                st = new Showtime(null, starttime, null, priceN, priceV, priceC, m, r);
                req.setAttribute("st", st);
                doGet(req, resp);
            } else {
                resp.sendRedirect("showtimes");
            }
        }
    }

}

package controller;

import dao.CinemaDAO;
import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Account;

public class CinemaSystemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("cinema");
        CinemaDAO cineDao = new CinemaDAO();
        TheaterDAO theDao = new TheaterDAO();

        Account acc = (Account) req.getSession().getAttribute("acc");
        String city = null;
        if (acc != null && acc.getCity() != null) {
            city = acc.getCity();
        } else {
            city = "Đà Nẵng";
        }

        // Lấy ngày hôm nay
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        // Tạo danh sách chứa 7 ngày
        List<Date> dateList = new ArrayList<>();

        // Thêm 7 ngày kể từ ngày hôm nay vào danh sách
        for (int i = 0; i < 7; i++) {
            calendar.setTime(currentDate);
            calendar.add(calendar.DAY_OF_YEAR, i);
            java.util.Date date = calendar.getTime();
            Date sqlDate = new Date(date.getTime());
            dateList.add(sqlDate);
        }

        req.setAttribute("c", cineDao.getCinemaById(id));
        req.setAttribute("theaterList", theDao.getTheatersByCinemaIdAndCity(id, city));
        req.setAttribute("cinema", cineDao.getAllCinemas());
        req.setAttribute("dateList", dateList);
        req.getRequestDispatcher("cinema-system.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}

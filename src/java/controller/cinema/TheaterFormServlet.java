package controller.cinema;

import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;
import model.CinemaSystem;
import model.Theater;

public class TheaterFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cinema/theater-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");

        String name = req.getParameter("txtName");
        String city = req.getParameter("sltCity");
        String district = req.getParameter("sltDistrict");
        String ward = req.getParameter("sltWard");
        String street = req.getParameter("txtStreet");
        Part part = req.getPart("fileImg");
        String filename = part.getSubmittedFileName();
        CinemaSystem cinema = (CinemaSystem) session.getAttribute("cinema");
        System.out.println(cinema + "aaaaaaaaaaaaaaaaaa");

        TheaterDAO thDAO = new TheaterDAO();

        switch (action) {
            case "add":
                String id = UUID.randomUUID().toString();

                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = id + "." + ext;

                    String appPath = getServletContext().getRealPath("");
                    File rootDir = new File(appPath).getParentFile().getParentFile();
                    filename = "/assets/img/theater/" + filename;

                    String uploadPath = rootDir.getAbsolutePath() + "\\web" + filename;

                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = part.getInputStream();

                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();
                } else {
                    filename = "/assets/img/theater.jpg";
                }
                thDAO.addTheater(id, name, city, district, ward, street, "", cinema.getId());
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                throw new AssertionError();

        }
    }

}

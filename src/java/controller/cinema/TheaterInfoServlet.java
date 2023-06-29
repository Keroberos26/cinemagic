package controller.cinema;

import dao.TheaterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import model.Theater;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class TheaterInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cinema/theater-info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");
        
        String action = req.getParameter("action");

        String name = req.getParameter("txtName");
        String street = req.getParameter("txtStreet");
        String ward = req.getParameter("sltWard");
        String district = req.getParameter("sltDistrict");
        String city = req.getParameter("sltCity");
        Part part = req.getPart("fileImg");
        String filename = part.getSubmittedFileName();

        TheaterDAO thDAO = new TheaterDAO();

        switch (action) {
            case "update":
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = theater.getId() + "." + ext;

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
                }
                thDAO.updateTheater(theater.getId(), name, street, ward, district, city, filename);
                break;
            case "delete":
                thDAO.deleteTheaterById(theater.getId());
                break;
            default:
                throw new AssertionError();

        }
        resp.sendRedirect("/cinema/theater-info");
    }

}

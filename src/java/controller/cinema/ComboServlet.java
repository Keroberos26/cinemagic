package controller.cinema;

import dao.ComboDAO;
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
import java.util.UUID;
import model.Theater;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ComboServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");
        ComboDAO comDao = new ComboDAO();

        req.setAttribute("comboList", comDao.getCombosByTheaterId(theater.getId()));
        req.getRequestDispatcher("/cinema/combo-mng.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("comboId");
        String txtPrice = req.getParameter("txtPrice");
        String name = req.getParameter("txtName");
        String description = req.getParameter("txtDescription");
        Part part = req.getPart("fileImg");

        int price = 0;
        if (txtPrice != null) {
            price = Integer.parseInt(txtPrice);
        }
        String filename = part.getSubmittedFileName();

        Theater theater = (Theater) req.getSession().getAttribute("theater");
        ComboDAO dao = new ComboDAO();

        switch (action) {
            case "add":
                id = UUID.randomUUID().toString();

                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = id + "." + ext;

                    String appPath = getServletContext().getRealPath("");
                    File rootDir = new File(appPath).getParentFile().getParentFile();
                    filename = "\\assets\\img\\combo\\" + filename;

                    String uploadPath = rootDir.getAbsolutePath() + "\\web" + filename;

                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = part.getInputStream();

                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();
                } else {
                    filename = "/assets/img/popcorn.png";
                }
                dao.addCombo(id, name, price, description, filename, theater.getId());
                break;
            case "update":
                if (!filename.isBlank()) {
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    filename = id + "." + ext;

                    String appPath = getServletContext().getRealPath("");
                    File rootDir = new File(appPath).getParentFile().getParentFile();
                    filename = "\\assets\\img\\combo\\" + filename;

                    String uploadPath = rootDir.getAbsolutePath() + "\\web" + filename;

                    FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = part.getInputStream();

                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();
                    
                }
                    dao.updateCombo(id, name, price, description, filename, theater.getId());
                break;
            case "delete":
                dao.deleteComboById(id);
                break;
            default:
                throw new AssertionError();

        }
        resp.sendRedirect("/cinema/combo");
    }

}

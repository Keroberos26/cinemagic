package controller.cinema;

import dao.RoomDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import model.Room;
import model.Theater;

public class RoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");

        RoomDAO roomDao = new RoomDAO();

        req.setAttribute("roomList", roomDao.getRoomsByTheaterId(theater.getId()));
        req.getRequestDispatcher("/cinema/room-mng.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Theater theater = (Theater) session.getAttribute("theater");

        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String roomName = req.getParameter("name");
        

        RoomDAO dao = new RoomDAO();
        switch (action) {
            case "add":
                dao.addRoom(theater.getId());
                break;
            case "update":
                dao.updateRoom(id, roomName, theater.getId());
                break;
            case "delete":
                dao.deleteRoomById(id);
                break;
            default:
                throw new AssertionError();
        }

        List<Room> roomList = dao.getRoomsByTheaterId(theater.getId());
        PrintWriter out = resp.getWriter();
        int count=0;
        for (Room m : roomList) {
            out.write("<tr>\n"
                    + "<td>" + ++count +" </td>\n"
                    + "<td><input type=\"text\" class=\"form-control-plaintext can-edit\" room-id=\""+ m.getId() +"\" value=\""+m.getName()+"\" readonly></td>\n"
                    + "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-primary\"><span class=\"icon\"><i class=\"fa-solid fa-couch\"></i></span></a></td>\n"
                    + "<td class=\"text-center\"><a href=\"#\" class=\"btn btn-danger\" id=\"deleteRow\"><span class=\"icon\"><i class=\"fa-regular fa-trash-can\"></i></span></a></td>\n"
                    + "</tr>");
        }
    }
}

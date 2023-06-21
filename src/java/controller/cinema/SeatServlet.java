package controller.cinema;

import com.google.gson.Gson;
import dao.RoomDAO;
import dao.SeatDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import model.Seat;

public class SeatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String row = req.getParameter("row");
        String col = req.getParameter("col");

        if (row != null && !row.isBlank()) {
            SeatDAO dao = new SeatDAO();
            Seat s = dao.getSeatByRowCol(id, Integer.parseInt(row), Integer.parseInt(col));
            PrintWriter out = resp.getWriter();
            if (s == null) {
                out.print("<div class=\"row mb-3 align-items-center\">\n"
                        + "     <div class=\"col-auto\">\n"
                        + "         <div class=\"form-check form-switch text-center\">\n"
                        + "             <input class=\"form-check-input\" type=\"checkbox\" role=\"switch\" id=\"isSeat\">\n"
                        + "             <label class=\"form-check-label\" for=\"isSeat\">Ghế</label>\n"
                        + "         </div>\n"
                        + "     </div>\n"
                        + "     <div class=\"col\">\n"
                        + "         <div class=\"row g-3 align-items-center\">\n"
                        + "             <div class=\"col-auto\">\n"
                        + "                 <label for=\"seatNum\">Số ghế</label>\n"
                        + "             </div>\n"
                        + "             <div class=\"col\">\n"
                        + "                 <input type=\"text\" class=\"form-control\" name=\"seatNum\" id=\"seatNum\" disabled>\n"
                        + "             </div>\n"
                        + "         </div>\n"
                        + "     </div>\n"
                        + "</div>\n"
                        + "<div class=\"mb-3\">\n"
                        + "     <p>Loại ghế</p>\n"
                        + "     <div class=\"form-check form-check-inline\">\n"
                        + "         <input class=\"form-check-input\" type=\"radio\" name=\"type\" id=\"normal\" value=\"N\" disabled checked>\n"
                        + "         <label class=\"form-check-label\" for=\"normal\">Thường</label>\n"
                        + "     </div>\n"
                        + "     <div class=\"form-check form-check-inline\">\n"
                        + "         <input class=\"form-check-input\" type=\"radio\" name=\"type\" id=\"vip\" value=\"V\" disabled>\n"
                        + "         <label class=\"form-check-label\" for=\"vip\">VIP</label>\n"
                        + "     </div>\n"
                        + "     <div class=\"form-check form-check-inline\">\n"
                        + "         <input class=\"form-check-input\" type=\"radio\" name=\"type\" id=\"couple\" value=\"C\" disabled>\n"
                        + "         <label class=\"form-check-label\" for=\"couple\">Sweetbox</label>\n"
                        + "     </div>\n"
                        + "</div>");
            } else {
                out.print("<div class=\"row mb-3 align-items-center\">\n"
                        + "     <div class=\"col-auto\">\n"
                        + "         <div class=\"form-check form-switch text-center\">\n"
                        + "             <input class=\"form-check-input\" type=\"checkbox\" role=\"switch\" id=\"isSeat\" checked>\n"
                        + "             <label class=\"form-check-label\" for=\"isSeat\">Ghế</label>\n"
                        + "         </div>\n"
                        + "     </div>\n"
                        + "     <div class=\"col\">\n"
                        + "         <div class=\"row g-3 align-items-center\">\n"
                        + "             <div class=\"col-auto\">\n"
                        + "                 <label for=\"seatNum\">Số ghế</label>\n"
                        + "             </div>\n"
                        + "             <div class=\"col\">\n"
                        + "                 <input type=\"text\" class=\"form-control\" name=\"seatNum\" id=\"seatNum\" value=\"" + s.getSeatNum() + "\">\n"
                        + "             </div>\n"
                        + "         </div>\n"
                        + "     </div>\n"
                        + "</div>\n"
                        + "<div class=\"mb-3\">\n"
                        + "     <p>Loại ghế</p>\n"
                        + "     <div class=\"form-check form-check-inline\">\n"
                        + "         <input class=\"form-check-input\" type=\"radio\" name=\"type\" id=\"normal\" value=\"N\" " + (s.getType().equals("N") ? "checked" : "") + ">\n"
                        + "         <label class=\"form-check-label\" for=\"normal\">Thường</label>\n"
                        + "     </div>\n"
                        + "     <div class=\"form-check form-check-inline\">\n"
                        + "         <input class=\"form-check-input\" type=\"radio\" name=\"type\" id=\"vip\" value=\"V\" " + (s.getType().equals("V") ? "checked" : "") + ">\n"
                        + "         <label class=\"form-check-label\" for=\"vip\">VIP</label>\n"
                        + "     </div>\n"
                        + "     <div class=\"form-check form-check-inline\">\n"
                        + "         <input class=\"form-check-input\" type=\"radio\" name=\"type\" id=\"couple\" value=\"C\" " + (s.getType().equals("C") ? "checked" : "") + ">\n"
                        + "         <label class=\"form-check-label\" for=\"couple\">Sweetbox</label>\n"
                        + "     </div>\n"
                        + "</div>");
            }
        } else {
            RoomDAO dao = new RoomDAO();
            req.setAttribute("room", dao.getRoomById(id));
            req.setAttribute("seatMap", dao.getSeatsByRoomId(id));
            req.getRequestDispatcher("/cinema/seat-mng.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomId = req.getParameter("roomid");
        String seatId = req.getParameter("seatid");
        int row = Integer.parseInt(req.getParameter("row"));
        int col = Integer.parseInt(req.getParameter("col"));
        String isSeat = req.getParameter("isSeat");
        String seatNum = req.getParameter("seatNum");
        String seatType = req.getParameter("seatType");

        PrintWriter out = resp.getWriter();
        SeatDAO dao = new SeatDAO();

        Seat seat = null;
        Gson gson = new Gson();

        if (seatId != null && !seatId.isBlank()) {
            if (Boolean.parseBoolean(isSeat)) {
                // Update
                seat = dao.updateSeat(seatId, roomId, row, col, seatNum, seatType);
                out.write(gson.toJson(seat));

            } else {
                // Delete
                dao.deleteSeatById(seatId);
                out.write("{\"action\": \"delete\"}");
            }
        } else {
            if (Boolean.parseBoolean(isSeat)) {
                // Add
                seat = dao.addSeat(roomId, row, col, seatNum, seatType);
                out.write(gson.toJson(seat));
            } else {
            }
        }
    }
}

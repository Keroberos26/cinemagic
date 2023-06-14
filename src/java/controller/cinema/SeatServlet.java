package controller.cinema;

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

        boolean print = true;

        if (seatId != null && !seatId.isBlank()) {
            if (Boolean.parseBoolean(isSeat)) {
                // Update
                print = dao.updateSeat(seatId, roomId, row, col, seatNum, seatType);
            } else {
                // Delete
                print = dao.deleteSeatById(seatId);
            }
        } else {
            if (Boolean.parseBoolean(isSeat)) {
                // Add
                print = dao.addSeat(roomId, row, col, seatNum, seatType);
            } else {
                // Nothing
                print = false;
            }
        }

        RoomDAO roomDao = new RoomDAO();
        Seat[][] seatMap = roomDao.getSeatsByRoomId(roomId);
        boolean isCouple = false;

        for (int i = 0; i < seatMap.length; i++) {
            out.write("<ul class=\"seats-row\">");
            for (int j = 0; j < seatMap[i].length; j++) {
                Seat s = seatMap[i][j];
                if (isCouple) {
                    isCouple = false;
                    continue;
                }
                String selected = "";
                if (row == i + 1 && col == j + 1) {
                    selected = " selected";
                }
                if (s != null) {
                    switch (s.getType()) {
                        case "N":
                            out.write("<li class=\"seat seat-normal" + selected + "\" seat-id=\"" + s.getId() + "\" seat-type=\"N\" seat-row=\"" + (i + 1) + "\" seat-col=\"" + (j + 1) + "\">" + s.getSeatNum() + "</li>");
                            break;
                        case "V":
                            out.write("<li class=\"seat seat-vip" + selected + "\" seat-id=\"" + s.getId() + "\" seat-type=\"V\" seat-row=\"" + (i + 1) + "\" seat-col=\"" + (j + 1) + "\">" + s.getSeatNum() + "</li>");
                            break;
                        case "C":
                            out.write("<li class=\"seat seat-couple" + selected + "\" seat-id=\"" + s.getId() + "\" seat-type=\"C\" seat-row=\"" + (i + 1) + "\" seat-col=\"" + (j + 1) + "\">" + s.getSeatNum() + "</li>");
                            isCouple = true;
                            break;
                        default:
                            throw new AssertionError();
                    }
                } else {
                    out.write("<li class=\"space" + selected + "\" seat-row=\"" + (i + 1) + "\" seat-col=\"" + (j + 1) + "\"></li>");
                }
            }
            out.write("</ul>");
        }
        if (!print) {
            out.write("<div class=\"alert alert-danger d-flex align-items-center mt-4 mx-3\" role=\"alert\">\n"
                    + "     <span class=\"icon\"><i class=\"fa-solid fa-circle-exclamation\"></i></span>\n"
                    + "     <div>\n"
                    + "         Đã có lỗi xảy ra!\n"
                    + "     </div>\n"
                    + "</div>");
        }
    }

}
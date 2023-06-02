/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.SeatDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class MngSeatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        
        String seatNum = req.getParameter("seatNum");
        String colInput = req.getParameter("col");
        int col = Integer.parseInt(colInput);
        String row = req.getParameter("row");
        String type = req.getParameter("type");
        
        String seatId = req.getParameter("seatId");
        String roomId = req.getParameter("roomId");
        SeatDAO dao = new SeatDAO();
        if(action.equals("addSeat")){
            dao.addSeat(seatNum, col, row, type, roomId);
        }
//        System.out.println(dao.addSeat("A20", 20, "D", "v", "7c975b87-e3ba-4f9c-9d23-d9e38698b97c"));
        if(action.equals("deleteSeat")){
            dao.deleteSeatById(seatId);
        }
        if(action.equals("updateSeat")){
            dao.updateSeat(seatId, seatNum, col, row, type, roomId);
        }
        resp.sendRedirect("/");
    }

}

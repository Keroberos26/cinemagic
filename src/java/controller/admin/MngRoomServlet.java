/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.RoomDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class MngRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        String name = req.getParameter("name");
        //todo:lay therterid to add
        String therterid = req.getParameter("theaterid");
        String roomid = req.getParameter("roomid");

        RoomDAO dao = new RoomDAO();

        if (action.equals("addRoom")) {
            dao.addRoom(name, therterid);
        }
//        dao.addRoom("Screen05", "2d1ede78-c56a-4c8f-a98d-2487ddd1dd5c");
        if(action.equals("deleteRoom")){
            dao.deleteRoomById(roomid);
        }
////        dao.deleteRoomById("0d0ed117-83d0-4c1b-b02e-0029dd71f01e");
        if(action.equals("updateRoom")){
            dao.updateRoom(roomid, name, therterid);
        }
//        dao.updateRoom("5d38ed2b-1b08-437a-87a2-eeaf195f73d4", "Screen07", "2d1ede78-c56a-4c8f-a98d-2487ddd1dd5c");
        resp.sendRedirect("/");
    }

}

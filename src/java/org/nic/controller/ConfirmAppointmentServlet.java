package org.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.nic.epanjeeyan.dao.ConfirmAppointDao;
import org.nic.epanjeeyan.jdbc.ConfirmAppointmentDAOImpl;

/**
 *
 * @author HP
 */
public class ConfirmAppointmentServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get the appointment id
        String appointmentID = request.getParameter("appointment_id");
        
        ConfirmAppointDao dao = new ConfirmAppointmentDAOImpl();
        int res = dao.updateAppoint(appointmentID);
        
        
        JSONObject obj = new JSONObject();
        try {
            obj.put("success", true);
            obj.put("update", res);
        }
        catch(Exception ex) {}
        
        // send JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        out.print(obj.toString());
    }

}

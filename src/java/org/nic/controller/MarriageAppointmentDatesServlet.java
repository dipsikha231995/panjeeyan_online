package org.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nic.epanjeeyan.AppointmentHandler;

public class MarriageAppointmentDatesServlet extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try {
            ArrayList dates = new AppointmentHandler().getAppointmentDates("marriage");
            JSONObject obj = new JSONObject();
            
            /*
                {
                    "success" : true,
                    "dates" : ["20/05/2019", "10/05/2019"]
                }
            
                {
                    "success" : false,
                    "msg" : "No Appointments available for next 14 days. Please try later."
                }
            
            */
            
            if (dates.isEmpty()) {
               obj.put("success", false);
               obj.put("msg", "No Appointments available for next 14 days. Please try later.");
            }
            else {
                obj.put("success", true);
                obj.put("dates", new JSONArray(dates));
            }
           
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
             //printwriter object to write in 
            PrintWriter out = response.getWriter();

            //list is printed in the browser
            out.print(obj.toString());
        } 
        catch (Exception ex) {}   
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

}

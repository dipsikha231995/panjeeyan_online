package org.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, String[]> params =  request.getParameterMap();
        
       /*
         $arr['SUB_SYSTEM'] = "GRAS-APP";
        $arr["TREASURY_CODE"] = "BIL";
        $arr["MAJOR_HEAD"] = "0029";
        $arr['DEPARTMENT_ID'] = "Ele". $id;
        
        */
       
       
       
       
       
       
        
    }

}

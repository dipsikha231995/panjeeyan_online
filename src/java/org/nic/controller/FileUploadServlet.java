package org.nic.controller;

import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nic.epanjeeyan.jdbc.FileUploadDAOImpl;

public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //uploadFile(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //System.out.println("Hello Servlet");
        //It's the WEB ROOT
//        System.out.println("web context: " + getServletContext().getContextPath());
//        System.out.println("/" + getServletContext().getRealPath("/"));
//        System.out.println("/uploads" + getServletContext().getRealPath("/uploads"));
//        System.out.println(File.separator);
        uploadFile(request, response);

    }

    private void uploadFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FileUploadDAOImpl dao = new FileUploadDAOImpl();

        String appointmentID = dao.getAppointmentID();

        // AS-MAR/1/2019
        // replace '/' by '-'
        appointmentID = appointmentID.replace("/", "-");

        String path = getServletContext().getRealPath("/upload");

        String userPath = path + File.separator + appointmentID;
        int dataSize = 50 * 1024 * 1024;

        // create the directory for the user
        File newDir = new File(userPath);

        if (newDir.mkdir()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Directory not created");
        }

        MultipartRequest m = new MultipartRequest(request, userPath, dataSize);
        String addressProof = m.getParameter("address-proof");
        String ageProof = m.getParameter("age-proof");
        String idProof = m.getParameter("id-proof");

        System.out.println("path: " + userPath);
        System.out.println("add: " + addressProof);
        System.out.println("age: " + ageProof);
        System.out.println("id: " + idProof);

        // write doc path in file_upload table
        dao.storeFiles(appointmentID.replace("-", "/"), 
                userPath + File.separator + addressProof, 
                userPath + File.separator + ageProof, 
                userPath + File.separator + idProof);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("file uploaded");
    }
}

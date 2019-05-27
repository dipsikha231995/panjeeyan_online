package org.nic.epanjeeyan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.jdbc.ResourceManager;
import sun.misc.BASE64Encoder;

public class ViewDeedHandler {

//Following Code will show deed for particular sro to respective comcaseno
    public String showDeed(String dbname, String bdusername, String dbpassword, String comcaseno, String filepath, int id) {

        String message = "";
//        String doc_scan_complete = null, startNo = null;
//        FileOutputStream fos = null;
//        filepath += "\\doc" + id + ".pdf";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String PDF_File = "";
        try {
            conn = ResourceManager.getConnectionDynamic(dbname + "_image", bdusername, dbpassword);
            //String SQLdocscanChk = "select cam_img as camimg,thumb as thmbfinger,indexf as indexfinger,middle as midfinger,ring as rngfinger,little as ltlfinger from  biometric_image where comcaseno=?";
            String SQLdocscanChk = "select docimg from  doc_image where comcaseno=?";
            stmt = conn.prepareStatement(SQLdocscanChk);
            stmt.setString(1, comcaseno);
            rs = stmt.executeQuery();
            System.out.println("rs---" + rs.getStatement());
            while (rs.next()) {
              
                if (rs.getBytes("docimg") != null) {
                    byte[] Data = rs.getBytes("docimg");
                    PDF_File = getPdfDataUri(Data);

//                    File file = new File(filepath);
//                    if (file.exists()) {
//                        file.delete();
//                    }
//                    fos = new FileOutputStream(file);
//                    fos.write(Data);
//                    fos.close();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewDeedHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        catch (FileNotFoundException ex) {
//            Logger.getLogger(ViewDeedHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ViewDeedHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return PDF_File;
    }
    // get URI data

    public String getPdfDataUri(byte[] pdfArray) {
        String base64EncodedPDF = new BASE64Encoder().encode(pdfArray);
        return "data:application/pdf;base64, " + base64EncodedPDF;
    }

    public String getImageDataUri(byte[] pdfArray) {
        System.out.println("pdfArray---" + pdfArray);
        String base64EncodedPDF = new BASE64Encoder().encode(pdfArray);
        return "data:application/png;base64, " + base64EncodedPDF;
    }
}

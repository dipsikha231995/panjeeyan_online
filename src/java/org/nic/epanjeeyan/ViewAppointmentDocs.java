package org.nic.epanjeeyan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.AppointmentDocuments;
import org.nic.epanjeeyan.exceptions.AppointmentDocumentsDaoException;
import org.nic.epanjeeyan.jdbc.AppointmentDocumentsDaoImpl;
import sun.misc.BASE64Encoder;

public class ViewAppointmentDocs {

   //Following Code will show a appointment Documents for particular appointment ID
    public String showDocs(String appointmentId) {
        String PDF_File = "";
        try {
        AppointmentDocumentsDaoImpl appointmentDao = new AppointmentDocumentsDaoImpl();
        AppointmentDocuments[] appointmentDto = appointmentDao.findByDynamicWhere("appointment_id=?", new Object[]{appointmentId});
        PDF_File = getPdfDataUri(appointmentDto[0].getDocument());

        } catch (AppointmentDocumentsDaoException ex) {
            Logger.getLogger(ViewAppointmentDocs.class.getName()).log(Level.SEVERE, null, ex);
        }
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

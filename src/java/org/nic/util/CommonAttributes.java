package org.nic.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.nic.epanjeeyan.LocationHandler;

import org.nic.epanjeeyan.dto.ImplementSro;
import org.nic.epanjeeyan.dto.Vilcode;

import org.nic.epanjeeyan.exceptions.DocdetailDaoException;
import org.nic.epanjeeyan.exceptions.ImplementSroDaoException;
import org.nic.epanjeeyan.exceptions.VilcodeDaoException;
import org.nic.epanjeeyan.jdbc.DocdetailDaoImpl;
import org.nic.epanjeeyan.jdbc.ResourceManager;
import org.nic.epanjeeyan.jdbc.VilcodeDaoImpl;
import sun.misc.BASE64Encoder;

public class CommonAttributes {

    private DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
    private DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd");

    
    public String findVlCodeByName(Object[] sqlParams) {
        VilcodeDaoImpl vilDao = new VilcodeDaoImpl();
        String name = "";
        try {
            name = vilDao.CustomDynamicSelect1("select vlname from vilcode where vlcode=?", sqlParams);
        } catch (VilcodeDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
public Vilcode[] getVilmouzaDropdown(String code) {
        Vilcode[] vil = null;
        VilcodeDaoImpl dao = new VilcodeDaoImpl();
        Object[] sqlParams = new Object[]{code + "%"};
        try {
            vil = dao.findByDynamicWhere("vlcode like ? and vlcode not like'" + "%00000'", sqlParams);

        } catch (VilcodeDaoException ex) {
           // Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vil;
    }
    // date parsing

    public Date setParseDate(String myDate) {
        Date dt = new Date();
        try {

            dt = dbSdf.parse(dbSdf.format(formSdf.parse(myDate)));
        } catch (ParseException ex) {
            Logger.getLogger(CommonAttributes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }

    public String setDateFormatDDMMYY(Date myDate) {
        return formSdf.format(myDate);
    }

    public String getTodaysDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String reportDate = df.format(Calendar.getInstance().getTime());
        return reportDate;
    }

    

    public String convertTosqlDate(String date) {
        Date dt = new Date();
        dt = setParseDate(date);
        java.sql.Date sqldate = new java.sql.Date(dt.getTime());
        return sqldate.toString();
    }
    public String getPdfDataUri(byte[] pdfArray) {
         String base64EncodedPDF = new BASE64Encoder().encode(pdfArray);
         return "data:application/pdf;base64, " + base64EncodedPDF;
    }
    public String getImageDataUri(byte[] pdfArray) {
         String base64EncodedPDF = new BASE64Encoder().encode(pdfArray);
         return "data:application/png;base64, " + base64EncodedPDF;
    }

    
   
}

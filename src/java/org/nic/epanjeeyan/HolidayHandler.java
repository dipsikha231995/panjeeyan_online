
package org.nic.epanjeeyan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.Holiday;
import org.nic.epanjeeyan.dto.HolidayPk;
import org.nic.epanjeeyan.exceptions.HolidayDaoException;
import org.nic.epanjeeyan.jdbc.HolidayDaoImpl;


public class HolidayHandler {
    private DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
    private DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd");
    // insert nodal officer details
    
    // Add new Holiday
    public String addHoliday(String date) {
        String message = "";
        try {
            Holiday holidayDTO = new Holiday();
            holidayDTO.setDate(setParseDate(date));
            HolidayDaoImpl holidayDaoImpl = new HolidayDaoImpl();
            holidayDaoImpl.insert(holidayDTO);
            message = "<div class=\"alert alert-success\">Record for Holiday has been added successfully</div>";
        } catch (Exception ex) {
            message = "<div class=\"alert alert-danger\">Error in Holiday Addition</div>";
        }
        System.out.println("" + message);
        return message;
    }

    public Date setParseDate(String myDate) {
        Date dt = new Date();
        try {
            dt = dbSdf.parse(dbSdf.format(formSdf.parse(myDate)));
        } catch (ParseException ex) {
            //Logger.getLogger(CommonAttributes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    public String convertTosqlDate(String date) {
        Date dt = new Date();
        dt = setParseDate(date);
        java.sql.Date sqldate = new java.sql.Date(dt.getTime());
        return sqldate.toString();
    } 
    
     // delete a holiday
    public String deleteHoliday(int  holidayid) {
        String message = "";
        HolidayPk holidayPk = new HolidayPk();
        HolidayDaoImpl holidayDaoImpl = new HolidayDaoImpl();
        holidayPk.setId(holidayid);
        try {
            holidayDaoImpl.delete(holidayPk);
             message = "<div class=\"alert alert-success\">Holiday Record has been deleted successfully</div>";       
        } catch (HolidayDaoException ex) {
            message = "<div class=\"alert alert-danger\">Error in deletion of Holiday</div>";
            Logger.getLogger(HolidayHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}

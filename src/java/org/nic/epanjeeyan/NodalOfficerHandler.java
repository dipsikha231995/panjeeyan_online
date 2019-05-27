
package org.nic.epanjeeyan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.LeavePlan;
import org.nic.epanjeeyan.dto.LeavePlanPk;
import org.nic.epanjeeyan.dto.NodalOfficers;
import org.nic.epanjeeyan.dto.NodalOfficersPk;
import org.nic.epanjeeyan.exceptions.LeavePlanDaoException;
import org.nic.epanjeeyan.exceptions.NodalOfficersDaoException;
import org.nic.epanjeeyan.jdbc.LeavePlanDaoImpl;
import org.nic.epanjeeyan.jdbc.NodalOfficersDaoImpl;


public class NodalOfficerHandler {
    private DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
    private DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd");
    // insert nodal officer details
    public String insert(NodalOfficers nodalofficer) {
        //NodalOfficersPk nodalofficerspk = new NodalOfficersPk();
        NodalOfficersDaoImpl nodalofficersdao = new NodalOfficersDaoImpl();
        String message = "";
        try {
            nodalofficersdao.insert(nodalofficer);
            message = "<div class=\"alert alert-success\">Record for the nodal officer has been added successfully</div>";
        } catch (NodalOfficersDaoException ex) {
            message = "<div class=\"alert alert-danger\">Error in nodal officer addition</div>";
            Logger.getLogger(NodalOfficerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    // delete a nodal officer under a particular office name
    public String delete(int  OfficerId) {
        NodalOfficersPk nodalofficerspk = new NodalOfficersPk();
        NodalOfficersDaoImpl nodalofficersdao = new NodalOfficersDaoImpl();
        String message = "";
        try {
            nodalofficerspk.setOfficerId(OfficerId);
            nodalofficersdao.delete(nodalofficerspk);
            message = "<div class=\"alert alert-success\">Record for the nodal officer has been deleted successfully</div>";
        } catch (NodalOfficersDaoException ex) {
            message = "<div class=\"alert alert-danger\">Error in nodal officer deletion</div>";
            Logger.getLogger(NodalOfficerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
    // update a nodal officer with a particular officer id under a particular office name
    public String update(int  OfficerId,NodalOfficers nodalofficer) {
        NodalOfficersPk nodalofficerspk = new NodalOfficersPk();
        NodalOfficersDaoImpl nodalofficersdao = new NodalOfficersDaoImpl();
        String message = "";
        try {
            nodalofficer.setOfficerId(OfficerId);
            nodalofficerspk.setOfficerId(OfficerId);
            nodalofficersdao.update(nodalofficerspk,nodalofficer);
            message = "<div class=\"alert alert-success\">Record for the nodal officer has been updated successfully</div>";
        } catch (NodalOfficersDaoException ex) {
            message = "<div class=\"alert alert-danger\">Error in nodal officer updation</div>";
            Logger.getLogger(NodalOfficerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
//Following Code will Register leave for SRO
    public String leavePlanAdd(LeavePlan leavePlanDTO) throws LeavePlanDaoException {
        LeavePlanDaoImpl leavePlanDaoImpl = new LeavePlanDaoImpl();
        String message = "";
        try {
            leavePlanDaoImpl.insert(leavePlanDTO);
            message = "<div class=\"alert alert-success\">Record for Leave of SRO has been added successfully</div>";
        } catch (Exception ex) {
            message = "<div class=\"alert alert-danger\">Error in SRO Leave Addition</div>";
        }
        return message;
    }
//Register of leave for SRO End

// update a  Leave Taken a particular SRO
    public String updateLeaveTaken(int slno, LeavePlan leavePlanDTO) {
        LeavePlanPk leavePlanPk = new LeavePlanPk();
        leavePlanPk.setSlno(slno);
        LeavePlanDaoImpl leavePlanDaoImpl = new LeavePlanDaoImpl();

        String message = "";
        try {
            leavePlanDaoImpl.update(leavePlanPk, leavePlanDTO);
            message = "Data Updated";
        } catch (LeavePlanDaoException ex) {
            Logger.getLogger(NodalOfficerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
// check if already leave taken in between to and from date     
       public String chkifleavetaken(int officer_id, String fromdate,String todate) {
        String errorMsg = "";
        LeavePlanDaoImpl leavePlanDaoImpl = new LeavePlanDaoImpl();
        LeavePlan[] leavePlan1,leavePlan2 = null;
        fromdate = convertTosqlDate(fromdate);
        todate = convertTosqlDate(todate);
        
        Date date = new Date();
        String todayDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
            
        Object[] obj = new Object[]{officer_id,fromdate, todate};
        try {
            leavePlan1 = leavePlanDaoImpl.findByDynamicSelect("select * from  leave_plan where officer_id = ? and from_date between ? and ?", obj);
            leavePlan2 = leavePlanDaoImpl.findByDynamicSelect("select * from  leave_plan where officer_id = ? and to_date between ? and ?", obj);

            if (leavePlan1.length > 0 || leavePlan2.length > 0) {
                errorMsg = "You have already taken a leave on that date";
            } else if (todayDate.equals(fromdate) || todayDate.equals(todate)) {
                errorMsg = "You can't take leave on Today's date";
            } 
        } catch (LeavePlanDaoException ex) {
            Logger.getLogger(NodalOfficerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return errorMsg;    
    }  
}

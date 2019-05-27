package org.nic.epanjeeyan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.Spmarrigenotice;
import org.nic.epanjeeyan.dto.SpmarrigenoticePk;
import org.nic.epanjeeyan.exceptions.DistrictDaoException;
import org.nic.epanjeeyan.exceptions.SpmarrigenoticeDaoException;
import org.nic.epanjeeyan.jdbc.DistrictDaoImpl;
import org.nic.epanjeeyan.jdbc.ResourceManager;
import org.nic.epanjeeyan.jdbc.SpmarrigenoticeDaoImpl;

public class MarriageDataHandler {

    public String insert(Spmarrigenotice spmarriagenotice) throws SpmarrigenoticeDaoException {
        SpmarrigenoticeDaoImpl spmarrigenoticedao = new SpmarrigenoticeDaoImpl();
        SpmarrigenoticePk pk = new SpmarrigenoticePk();
        Calendar cal = Calendar.getInstance();
        int year = Integer.valueOf(cal.get(Calendar.YEAR));
//        if (spmarrigenoticedao.CustomDynamicSelect(
//                "select nslno from spmarrigenotice where slno=(select max(slno) from spmarrigenotice)", null) == null) {
//            pk.setNslno("1");
//            spmarriagenotice.setNslno("1/" + year);
//            spmarriagenotice.setSlno(1);
//        } else {
//            String nslno = spmarrigenoticedao.CustomDynamicSelect("select nslno from spmarrigenotice where slno=(select max(slno) from spmarrigenotice)", null);
//            String yearPart = nslno.substring((nslno.indexOf('/') + 1), nslno.length());
//            String slPart = nslno.substring(0, (nslno.indexOf('/')));
//            int slnoInt = Integer.parseInt(slPart);
//            if (year == Integer.parseInt(yearPart)) {
//                spmarriagenotice.setNslno((slnoInt + 1) + "/" + yearPart);
//                spmarriagenotice.setSlno(Integer.parseInt(slPart) + 1);
//            } else {
//                nslno = spmarrigenoticedao.CustomDynamicSelect("select nslno from spmarrigenotice where nslno like ? and slno=(select max(slno) from spmarrigenotice)",
//                        new Object[]{"%" + year});
//                slPart = nslno.substring(0, (nslno.indexOf('/')));
//                spmarriagenotice.setNslno((Integer.parseInt(slPart) + 1) + "/" + year);
//                spmarriagenotice.setSlno(Integer.parseInt(slPart) + 1);
//            }
//        }
        spmarrigenoticedao.insert(spmarriagenotice);
        return spmarriagenotice.getNslno();
    }

    public String getMarriageTypeName(String marriageType) {
        marriageType = "marriage(" + marriageType + ")";
        return marriageType;
    }

    public Spmarrigenotice[] viewMarriagesNotice() {
        SpmarrigenoticeDaoImpl spmarrigenoticedao = new SpmarrigenoticeDaoImpl();
        Spmarrigenotice[] spmarrigenotice = null;
        try {
            spmarrigenotice = spmarrigenoticedao.findAll();
        } catch (SpmarrigenoticeDaoException ex) {
            Logger.getLogger(MarriageDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spmarrigenotice;
    }
    
    public Spmarrigenotice editData(String nslno) {
        SpmarrigenoticeDaoImpl spmarrigenoticedao = new SpmarrigenoticeDaoImpl();
        Spmarrigenotice spmarrigenotice = new Spmarrigenotice();
        try {
            spmarrigenotice = spmarrigenoticedao.findByPrimaryKey(nslno);
        } catch (SpmarrigenoticeDaoException ex) {
            Logger.getLogger(MarriageDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spmarrigenotice;
    }

    public String updateMarriageDetails(Spmarrigenotice spmarriagenotice) {
        SpmarrigenoticeDaoImpl spmarrigenoticedao = new SpmarrigenoticeDaoImpl();
        SpmarrigenoticePk pk = new SpmarrigenoticePk();
        String message = "";
        Calendar cal = Calendar.getInstance();
        try {
            spmarriagenotice.setSlno(spmarrigenoticedao.findWhereNslnoEquals(spmarriagenotice.getNslno())[0].getSlno());
            pk.setNslno(spmarriagenotice.getNslno());
            spmarrigenoticedao.update(pk, spmarriagenotice);
            message = "Data Updated successfully for the Reference No." + spmarriagenotice.getNslno();
        } catch (SpmarrigenoticeDaoException ex) {
            message = "Error in data updation";
            Logger.getLogger(MarriageDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public Spmarrigenotice generateNotice(String nslno) {
        String message = "";
        SpmarrigenoticeDaoImpl spmarrigenoticedao = new SpmarrigenoticeDaoImpl();
        Spmarrigenotice spmarrigenotice = new Spmarrigenotice();
        try {
            spmarrigenotice = spmarrigenoticedao.findByPrimaryKey(nslno);
        } catch (SpmarrigenoticeDaoException ex) {
            Logger.getLogger(MarriageDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spmarrigenotice;
    }

//    public String generateReport(String refNoString, String reporttemplatepath, String reportstorepath, int id,String cerificateheading,String reportfile) {
//
//        reportstorepath = reportstorepath + cerificateheading + id + ".pdf";
//        reporttemplatepath = reporttemplatepath + reportfile;
//        HashMap jasperParameter = new HashMap();
//        String message = "";
//        try {
//            System.out.println("reportstorepath"+reportstorepath);
//            System.out.println("reporttemplatepath"+reporttemplatepath);
//            JasperReport jasperReport = JasperCompileManager.compileReport(reporttemplatepath);
//            jasperParameter.put("slnoMrg", refNoString);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, ResourceManager.getConnection());
//            JasperExportManager.exportReportToPdfFile(jasperPrint, reportstorepath);
//            jasperReport = null;
//        } catch (Exception e) {
//            message = "<div class='text-danger'><strong>Some error occured generating the Information slip</strong></div>";
//            e.printStackTrace();
//        }
//        try {
//
//            ResourceManager.close(ResourceManager.getConnection());
//        } catch (SQLException ex) {
//            Logger.getLogger(MarriageDataHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return message;
//    }
    

    
//

    public String getReportUrl(String Report_store_url,String cerificateheading,Integer id, String contextpath) {
        return contextpath + Report_store_url + cerificateheading + id + ".pdf"; 
    }

    /* partha changes for spmarriage starts
     * to insert data in spmarriage table and generate comcase no
     */
   
    
    // Update Marriage registration, spmarriage start

    public void updateMarriageregistrationSPMarNoticeDetails(Spmarrigenotice spmarnotice) throws SpmarrigenoticeDaoException {
        SpmarrigenoticeDaoImpl spmarrigenoticeDaoImpl = new SpmarrigenoticeDaoImpl();
        SpmarrigenoticePk pk = new SpmarrigenoticePk();
        String nslno = spmarnotice.getNslno();
        pk.setNslno(nslno);
        spmarrigenoticeDaoImpl.update(pk, spmarnotice);
    }
    // Update Marriage registration, spmarriage End
    //  to get slno dependent data in meriage reg form

    public Spmarrigenotice[] getSlnoDependentData(String serialno) {
        Spmarrigenotice[] datavalues = null;
        SpmarrigenoticeDaoImpl spmarrigenoticeDaoImpl = new SpmarrigenoticeDaoImpl();
        try {
            datavalues = spmarrigenoticeDaoImpl.findByDynamicWhere("nslno=?", new Object[]{serialno});
        } catch (SpmarrigenoticeDaoException ex) {
            Logger.getLogger(MarriageDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datavalues;
    }

    
    // partha chabges for spmarriage ends
}
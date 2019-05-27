package org.nic.epanjeeyan;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.Nonencum;
import org.nic.epanjeeyan.dto.NonencumPk;
import org.nic.epanjeeyan.exceptions.NonencumDaoException;
import org.nic.epanjeeyan.jdbc.NonencumDaoImpl;

public class CertificateHandler {
    // delete nonencumbrance data
    public boolean deleteNonencumbranceData(Nonencum nonencum) {
        NonencumPk nonencumpk = new NonencumPk();
        NonencumDaoImpl nonencumdao = new NonencumDaoImpl();
        nonencumpk.setApplno(nonencum.getApplno());
        nonencumpk.setSrlno(nonencum.getSrlno());
        try {
            nonencumdao.delete(nonencumpk);
            return true;
        } catch (NonencumDaoException ex) {
            Logger.getLogger(CertificateHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertNonencumbranceApplication(Nonencum nonencum) {
        NonencumDaoImpl nonencumdao = new NonencumDaoImpl();
        try {
            nonencumdao.insert(nonencum);
            return true;
        } catch (NonencumDaoException ex) {
            Logger.getLogger(CertificateHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
// Update Issue Certificate Deed in certicopy End
    // @ to do get Nonencumbrance report


    public boolean updateNonencumbranceApplication(Nonencum nonencum) {
        NonencumDaoImpl nonencumdao = new NonencumDaoImpl();
        NonencumPk nonencumpk = new NonencumPk();
        nonencumpk.setApplno(nonencum.getApplno());
        nonencumpk.setSrlno(nonencum.getSrlno());
        try {
            nonencumdao.update(nonencumpk, nonencum);
            return true;
        } catch (NonencumDaoException ex) {
            Logger.getLogger(CertificateHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // @ to do get EditNonEncumbrance data
    public Nonencum getNonEncumbranceApplicationData(String ApplicationNo,int srlno) {
        NonencumDaoImpl nonencumdao = new NonencumDaoImpl();
        Nonencum nonencum = new Nonencum();
        NonencumPk nonencumpk = new NonencumPk();
        try {
            nonencumpk.setApplno(ApplicationNo);
            nonencumpk.setSrlno(srlno);
            nonencum = nonencumdao.findByPrimaryKey(nonencumpk);
        } catch (NonencumDaoException ex) {
            Logger.getLogger(CertificateHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nonencum;
    }

    //@ to do get report url

    public String getReportUrl(String Report_store_url,String cerificateheading,Integer id, String contextpath) {
        return contextpath + Report_store_url + cerificateheading + id + ".pdf"; 
    }


    // set Comcaseno for nonencumbrance form

    public Nonencum setApplicationNumberNonEncumbrance(Nonencum nonencum) {
        NonencumDaoImpl nonencumdao = new NonencumDaoImpl();
        Nonencum[] nonencumArray = null;
        Calendar now = Calendar.getInstance();
        java.util.Date temp = now.getTime();
        java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(temp.getTime());
        java.util.Date dt = now.getTime();
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        int year = sqlDate.getYear() + 1900;
        int slno;
        try {
            nonencumArray = nonencumdao.findByDynamicSelect("select * from nonencum", null);
            if (nonencumArray.length == 0) {
                nonencum.setSrlno(1);
                nonencum.setSlno(1);
                nonencum.setApplno("1/" + year);
            } else {
                int maxapplicationno = nonencumdao.customDynamicSelect2(
                        "select max(slno) from nonencum where applno like ?", new Object[]{"%" + year});
                slno = maxapplicationno;
                System.out.println("max" + maxapplicationno);
                if (maxapplicationno == 0) {
                    nonencum.setSlno(1);
                    nonencum.setSrlno(1);
                    nonencum.setApplno(slno + "/" + year);
                } else {
                    slno++;
                    nonencum.setSlno(slno);
                    nonencum.setSrlno(slno);
                    nonencum.setApplno(slno + "/" + year);
                }
            }
        } catch (NonencumDaoException ex) {
            Logger.getLogger(CertificateHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nonencum;
    }
}

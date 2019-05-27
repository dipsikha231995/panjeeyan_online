/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.nic.epanjeeyan.dto.Category;
import org.nic.epanjeeyan.dto.Renquiry;
import org.nic.epanjeeyan.dto.RenquiryPk;
import org.nic.epanjeeyan.dto.Vilcode;
import org.nic.epanjeeyan.exceptions.CategoryDaoException;
import org.nic.epanjeeyan.exceptions.RenquiryDaoException;
import org.nic.epanjeeyan.exceptions.VilcodeDaoException;
import org.nic.epanjeeyan.jdbc.CategoryDaoImpl;
import org.nic.epanjeeyan.jdbc.RenquiryDaoImpl;
import org.nic.epanjeeyan.jdbc.VilcodeDaoImpl;

/**
 *
 * @author ankita
 */
public class EnquiryHandler {
    public Category [] getSubDeedDropdown(String code) {
        Category [] categories = null;
        CategoryDaoImpl dao = new CategoryDaoImpl();
        Object [] sqlParams = new Object[] {Integer.parseInt(code)} ;
        
        try {
            categories = dao.findByDynamicWhere("code=?", sqlParams);
            
        } catch (CategoryDaoException ex) {
            Logger.getLogger(EnquiryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categories;
    }
        public String entryEnquiry(Renquiry req) throws RenquiryDaoException, CategoryDaoException
        {
            
            String sql1="select count(*) from renquiry";
            String sql="Select max(slno) from renquiry";
            RenquiryDaoImpl rDao1=new RenquiryDaoImpl();
            Integer a;
            Calendar cal = Calendar.getInstance();
            Integer year=Integer.valueOf(cal.get(Calendar.YEAR));
            String temp=null;
            a = rDao1.CustomDynamicSelect(sql1, null);
                if(a==0){
                    req.setSlno(1);
                    temp="tmp/1/"+year;
                    req.setTmpcaseno("tmp/1/"+year);
                }
                 else
                {
                    a = rDao1.CustomDynamicSelect(sql, null);
                    Integer k=a+1;
                    req.setSlno(k);
                
        String sql2="select tmpcaseno from renquiry where slno=(select max(slno)from renquiry)";
        RenquiryDaoImpl rDao2=new RenquiryDaoImpl();
        String b;
        
            try {
                b = rDao2.CustomDynamicSelect1(sql2, null);
                String c=b.substring((b.lastIndexOf('/')+1),b.length());
                String d=b.substring((b.indexOf('/')+1), b.lastIndexOf('/'));
                Integer e=Integer.parseInt(c);
                Integer f=Integer.parseInt(d);
                Calendar now = Calendar.getInstance();
                Integer g=Integer.valueOf(now.get(Calendar.YEAR));
                 if (g.toString().equals(e.toString()))
                {
                Integer l=f+1;  
                temp="tmp/"+l+"/"+e;
                req.setTmpcaseno("tmp/"+l+"/"+e);
                }
                else
                {
                    temp="tmp/1/"+g;
                req.setTmpcaseno("tmp/1/"+g);
                }
                
            } catch (RenquiryDaoException ex) {
                //Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                }
        CategoryDaoImpl c=new CategoryDaoImpl();
        
        String sqll="select act from category where code=? and sub_deed_type=?";
        Object []obj=new Object[]{req.getDeedType(),req.getDeedSubtype()};
    
        req.setAct(c.CustomDynamicSelect1(sqll, obj));
        
        Calendar now = Calendar.getInstance();
        java.util.Date dt = now.getTime();
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        
        req.setEnquiryDate(sqlDate);
        
        RenquiryDaoImpl rDao=new RenquiryDaoImpl();
            try {
                rDao.insert(req);
            } catch (RenquiryDaoException ex) {
                //Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
       return temp;
        }
        public void deleteEnquiry(String t) throws RenquiryDaoException
        {
            RenquiryDaoImpl rdao=new RenquiryDaoImpl();
            RenquiryPk pk=new RenquiryPk();
            pk.setTmpcaseno(t);
            rdao.delete(pk);
        }
       
        public void updateEnquiry(Renquiry req,String t,Integer s) throws RenquiryDaoException, CategoryDaoException{
            
          RenquiryDaoImpl rdao=new RenquiryDaoImpl();
          RenquiryPk pk=new RenquiryPk();
         
          pk.setTmpcaseno(t);
        
          req.setTmpcaseno(t);
          req.setSlno(s);
          
        Calendar now = Calendar.getInstance();
        java.util.Date dt = now.getTime();
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
        
        req.setEnquiryDate(sqlDate);
        CategoryDaoImpl c=new CategoryDaoImpl();
        
        String sqll="select act from category where code=? and sub_deed_type=?";
        Object []obj=new Object[]{req.getDeedType(),req.getDeedSubtype()};
        
        req.setAct(c.CustomDynamicSelect1(sqll, obj));
        
        rdao.update(pk, req);
        }
       
        public String getVillageDropdown(String code, String village) {
            Vilcode [] vils = null;
            String options = null;
            
            code=code.substring(0, 8);
            VilcodeDaoImpl dao = new VilcodeDaoImpl();
            Object [] sqlParams = new Object[] {code+"%"} ;

            try {
                vils = dao.findByDynamicWhere("vlcode like ? and vlcode not like '%00000'", sqlParams);
                options = "<option value=''>Select Town/Village</option>";
                for(int j = 0;j < vils.length;j++) {
                    
                    if(vils[j].getVlcode().equals(village))
                        options = options + "<option value='"+vils[j].getVlcode()+"' selected='selected'>"+vils[j].getVlname()+"</option>";
                    else
                        options = options + "<option value='"+vils[j].getVlcode()+"' >"+vils[j].getVlname()+"</option>";
                }


            } catch (VilcodeDaoException ex) {
                Logger.getLogger(EnquiryHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            return options;
        }
    
//public boolean generateSlip(Integer id,String tmpcaseno,String reporttemplatepath,String reportstorepath) throws JRException{
//        try {    
//            Connection connection =null;
//            connection=ResourceManager.getConnection();
//
//            String storepath = reportstorepath +"\\EnquirySlip"+id+".pdf";
//            String templatepath = reporttemplatepath +"\\test_report.jrxml";
//            System.out.println(templatepath);
//            HashMap jasperParameter = new HashMap();
//            JasperReport jasperReport = JasperCompileManager.compileReport(templatepath);
//            jasperParameter.put("tmpcaseno", tmpcaseno);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, storepath);
//            return true;
//        } 
//        catch (Exception ex) {
//            return false;
//        }
//}
 public Renquiry[] pagination(Integer offset,Integer num) throws RenquiryDaoException
 {
     
                
     RenquiryDaoImpl rdao=new RenquiryDaoImpl();
     Renquiry [] req=null;
     req=rdao.pageAll(offset, num);
     return req;
 }

    public int getNoOfRecords() throws RenquiryDaoException {
    String query = "select count(*) from renquiry  ";
    RenquiryDaoImpl rdao=new RenquiryDaoImpl();
    
    Integer num=rdao.CustomDynamicSelect(query,null);
        return num;
    }
    
    
}



           
    
    
    


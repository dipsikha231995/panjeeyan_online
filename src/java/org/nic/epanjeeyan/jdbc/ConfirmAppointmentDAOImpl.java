package org.nic.epanjeeyan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.nic.epanjeeyan.dao.ConfirmAppointDao;


public class ConfirmAppointmentDAOImpl implements ConfirmAppointDao{
    
      final String url = "jdbc:mysql://localhost/metadatabase";
        final String uname = "root";
        final String pswd = "root";
        final String driver = "com.mysql.jdbc.Driver";
    
        
   
    @Override
    public int updateAppoint(String appointmentID) {
         PreparedStatement pstmt = null;
        Connection con = null;
        int success = 0;

        try {
            Class.forName(driver);
            
            con = DriverManager.getConnection(url, uname, pswd);

            String sql = "UPDATE appointment_slot_booking set DEPARTMENTID = null where appointment_id = ?";
            pstmt = con.prepareStatement(sql);

            //set the values in the prepare statement
            pstmt.setString(1, appointmentID);
           
            //execute prepare statement
            success = pstmt.executeUpdate();
            // returns 1 if, sucess
            // else 
            
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            try {
                pstmt.close();
                con.close();
            } 
            catch (Exception ex) {}

        }
       
        return success;
     
    }
    
}

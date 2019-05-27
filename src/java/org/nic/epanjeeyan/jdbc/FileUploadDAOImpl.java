package org.nic.epanjeeyan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.nic.epanjeeyan.dao.FileUploadDao;

public class FileUploadDAOImpl implements FileUploadDao{

    final String url = "jdbc:mysql://localhost/metadatabase";
    final String uname = "root";
    final String pswd = "root";
    final String driver = "com.mysql.jdbc.Driver";
    
    
    
    @Override
    public String getAppointmentID() {
        String appointmentID = "";
        
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, uname, pswd);

            //since no user input in sql statement hence use create statement
            stmt = con.createStatement();

            String query = "SELECT appointment_id from appointment_slot_booking where slno = (select max(slno) from appointment_slot_booking)";
            ResultSet rs = stmt.executeQuery(query);

            // applicant_type.add(rs.getString("type"))-To add the result set in a list
            while (rs.next()) {
                appointmentID = rs.getString("appointment_id");
            }
        } 
        catch (Exception ex) {
        } 
        finally {
            try {
                stmt.close();
                con.close();
            } 
            catch (Exception ex) {}

        }

      
       return appointmentID;
    }
    
    
    @Override
    public int storeFiles(String a_id, String add, String age, String id) {
        PreparedStatement pstmt = null;
        Connection con = null;
        int success = 0;

        try {
            Class.forName(driver);
            
            con = DriverManager.getConnection(url, uname, pswd);

            String sql = "INSERT INTO file_uploads(appointment_id, path_address_proof, path_age_proof, path_identity_proof) VALUES(?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            //set the values in the prepare statement
            pstmt.setString(1, a_id);
            pstmt.setString(2, add);
            pstmt.setString(3, age);
            pstmt.setString(4, id);

            //execute prepare statement
            success = pstmt.executeUpdate();
            // returns 1 if, sucess
            // else 
            
        } 
        catch (Exception ex) {
        } 
        finally {
            try {
                pstmt.close();
                con.close();
            } 
            catch (Exception ex) {}

        }
        
        
        
        System.out.println("add: " + add);
        System.out.println("age: " + age);
        System.out.println("id: " + id);
        System.out.println("success: " + success);
        
        
        
        
        return success;

    }
   
}

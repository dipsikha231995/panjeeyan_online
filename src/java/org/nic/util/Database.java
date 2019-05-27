/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.util;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Administrator
 */
public class Database {
    
    
      public static Connection getConnection() {
        Connection con;
        InitialContext ic = null;
        DataSource ds = null;  
        
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/epanjeeyan");
            con = ds.getConnection();

            if (!con.isClosed()) {
                System.out.println("Connection successfully opened to MySQL server.");
            }
            return con;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
          return null;
    
      }
 
       public static void close(Connection con) {
          try  {
              con.close();
          }
          catch(Exception ex) {
          }
      }
          
}

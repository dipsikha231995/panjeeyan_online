/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.nic.epanjeeyan.dto.UserNew;
import org.nic.epanjeeyan.dto.UserNewPk;
import org.nic.epanjeeyan.exceptions.UserNewDaoException;
import org.nic.epanjeeyan.jdbc.UserNewDaoImpl;

/**
 *
 * @author ankita
 */
public class LoginHandler {
    
    
    public String encryptPassword(String password)
{
    String sha1 = "";
    try
    {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
    }
    catch(NoSuchAlgorithmException e)
    {
        e.printStackTrace();
    }
    catch(UnsupportedEncodingException e)
    {
        e.printStackTrace();
    }
    return sha1;
}
    private static String byteToHex(final byte[] hash)
{
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
        formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
}
public boolean isLoggedIn(HttpServletRequest request) throws IOException, ServletException
{
   UserNew[] u=(UserNew[]) request.getSession().getAttribute("usernew");      
             
    if(u==null)
        return false;
 
    return true;
}
    
  public UserNew[] pagination(Integer offset,Integer num) throws UserNewDaoException 
 {
     
                
     UserNewDaoImpl udao=new UserNewDaoImpl();
     UserNew [] user=null;
     user=udao.pageAll(offset, num);
     return user;
 }

    public int getNoOfRecords() throws UserNewDaoException  {
    String query = "select count(*) from user_new";
    UserNewDaoImpl rdao=new UserNewDaoImpl();
    Integer num=rdao.CustomDynamicSelect(query,null);
    return num;
    }  
     public void deleteUser(String n) throws UserNewDaoException 
        {
            UserNewDaoImpl rdao=new UserNewDaoImpl();
            UserNewPk pk=new UserNewPk();
            pk.setUserid(n);
            rdao.delete(pk);
            
            }
     
     public void updateUser(String code,UserNew u) throws UserNewDaoException  {
        
        UserNewPk pk=new UserNewPk();
        pk.setUserid(code);
        UserNewDaoImpl vdao=new UserNewDaoImpl();
       vdao.update(pk, u);
        
        
    }
     
     public void entryUser(UserNew u) throws UserNewDaoException 
    {
    UserNewDaoImpl rdao=new UserNewDaoImpl();
    rdao.insert(u);
    }
     
}

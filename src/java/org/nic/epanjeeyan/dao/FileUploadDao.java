package org.nic.epanjeeyan.dao;

public interface FileUploadDao {
    public String getAppointmentID();
    
    
    public int storeFiles(String a_id, String add, String age, String id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.SroOffices;
import org.nic.epanjeeyan.dto.SroOfficesPk;
import org.nic.epanjeeyan.exceptions.SroOfficesDaoException;
import org.nic.epanjeeyan.jdbc.SroOfficesDaoImpl;

/**
 *
 * @author Administrator
 */
public class SroOfficeHandler {
    public void addSroOffice(SroOffices sroOfficeDTO) throws SroOfficesDaoException {
       SroOfficesDaoImpl sroOfficesDaoImpl = new SroOfficesDaoImpl();
       sroOfficesDaoImpl.insert(sroOfficeDTO);
    }
    public void deleteSroOffice(Integer id) throws SroOfficesDaoException{
        SroOfficesDaoImpl sroOfficesDaoImpl = new SroOfficesDaoImpl();
        SroOfficesPk sroOfficesPk = new SroOfficesPk();
        sroOfficesPk.setId(id);
        sroOfficesDaoImpl.delete(sroOfficesPk);    
    }
    public void updateSroOffice(SroOffices sroOfficesDTO, Integer id) throws SroOfficesDaoException{
        SroOfficesPk sroOfficesPk = new SroOfficesPk();
        sroOfficesPk.setId(id);
        SroOfficesDaoImpl sroOfficesDaoImpl = new SroOfficesDaoImpl();
        sroOfficesDaoImpl.update(sroOfficesPk, sroOfficesDTO);
                
    }
    SroOfficesDaoImpl sroOfficesDaoImpl = new SroOfficesDaoImpl();

    public SroOffices[] pagination(Integer offset, Integer num) throws SroOfficesDaoException {
        SroOffices[] sroOffices = null;
        sroOffices = sroOfficesDaoImpl.findAll();
        return sroOffices;
    }

    public int getNoOfRecords() throws SroOfficesDaoException {
        Integer num = null;
        try {
            String query = "select count(*) from sro_offices";
            num = sroOfficesDaoImpl.CustomDynamicSelect(query, null);
        } catch (SroOfficesDaoException ex) {
            Logger.getLogger(SroOfficeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
}

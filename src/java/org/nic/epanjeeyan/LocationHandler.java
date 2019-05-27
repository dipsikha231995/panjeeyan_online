///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.nic.epanjeeyan;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.nic.epanjeeyan.dto.Areadetail;
//import org.nic.epanjeeyan.dto.AreadetailPk;
//import org.nic.epanjeeyan.dto.Vilcode;
//import org.nic.epanjeeyan.exceptions.AreadetailDaoException;
//import org.nic.epanjeeyan.exceptions.PartyDaoException;
//import org.nic.epanjeeyan.exceptions.VilcodeDaoException;
//import org.nic.epanjeeyan.jdbc.AreadetailDaoImpl;
//import org.nic.epanjeeyan.jdbc.VilcodeDaoImpl;
//
///**
// *
// * @author ankita
// */
//public class LocationHandler {
//
//    public Vilcode[] getMouzaDropdown(String code) {
//        Vilcode[] vil = null;
//        VilcodeDaoImpl dao = new VilcodeDaoImpl();
//        Object[] sqlParams = new Object[]{code + "%"};
//        try {
//            vil = dao.findByDynamicWhere("vltype='M'and vlcode like ?", sqlParams);
//
//        } catch (VilcodeDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return vil;
//    }
//    // @ to do display subdivision
//
//    public Vilcode[] getSubDivisionDropdown(String code) {
//        Vilcode[] vil = null;
//        VilcodeDaoImpl dao = new VilcodeDaoImpl();
//        Object[] sqlParams = new Object[]{code + "%"};
//        try {
//            vil = dao.findByDynamicWhere("vltype='S'and vlcode like ?", sqlParams);
//        } catch (VilcodeDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return vil;
//    }
//
//    public Vilcode[] getRevenueCircleDropdown(String code) {
//        Vilcode[] vil = null;
//        VilcodeDaoImpl dao = new VilcodeDaoImpl();
//        Object[] sqlParams = new Object[]{code + "%"};
//        try {
//            vil = dao.findByDynamicWhere("vltype='C'and vlcode like ?", sqlParams);
//        } catch (VilcodeDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return vil;
//    }
//
//    public Vilcode[] getVilmouzaDropdown(String code) {
//        Vilcode[] vil = null;
//        VilcodeDaoImpl dao = new VilcodeDaoImpl();
//        Object[] sqlParams = new Object[]{code + "%"};
//        try {
//            vil = dao.findByDynamicWhere("vlcode like ? and vlcode not like'" + "%00000'", sqlParams);
//
//        } catch (VilcodeDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return vil;
//    }
//
//    public void areaEntry(Areadetail area) throws AreadetailDaoException, VilcodeDaoException {
//
//        Object obj[] = new Object[]{area.getComcaseno()};
//        AreadetailDaoImpl adao = new AreadetailDaoImpl();
//        Integer count_comcaseno = adao.CustomDynamicSelect("select count(*) from areadetail where comcaseno=?", obj);
//        if (count_comcaseno == 0) {
//            area.setCaseslno(1);
//        } else {
//            //String sql2 = "select max(caseslno) from areadetail where comcaseno=?";
//            //Integer caseslno = adao.CustomDynamicSelect(sql2, obj);
//            //caseslno = caseslno + 1;
//            area.setCaseslno(adao.CustomDynamicSelect("select max(caseslno) from areadetail where comcaseno=?", obj)+1);
//        }
//        area.setLarea(0);
//        VilcodeDaoImpl vdao = new VilcodeDaoImpl();
//        area.setVillcode(vdao.CustomDynamicSelect1("Select vlcode from vilcode where vlname=?", new Object[]{area.getVillage()}));
//        adao.insert(area);
//    }
//    /*
//     * Method 'findVlCodeByName' finds the vlcode that matches the criteria
//     */
//
//    public String findVlCodeByName(Object[] sqlParams) {
//        VilcodeDaoImpl vilDao = new VilcodeDaoImpl();
//        String name = "";
//        try {
//            name = vilDao.CustomDynamicSelect1("select vlname from vilcode where vlcode=?", sqlParams);
//        } catch (VilcodeDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return name;
//    }
//
//    public void deleteArea(Integer Caseslno, String comcaseno) throws AreadetailDaoException {
//        AreadetailDaoImpl adao = new AreadetailDaoImpl();
//        AreadetailPk pk = new AreadetailPk();
//        Areadetail[] area = null;
//        pk.setCaseslno(Caseslno);
//        pk.setComcaseno(comcaseno);
//        adao.delete(pk);
//        // @ to do update noofperson for other entries
//        area = adao.findWhereComcasenoEquals(comcaseno);
//        for (int i = 0; i < area.length; i++) {
//            pk.setCaseslno(area[i].getSlno());
//            pk.setComcaseno(area[i].getComcaseno());
//            area[i].setNoofplot(area[i].getNoofplot() - 1);
//            adao.update(pk, area[i]);
//        }
//        // @ to do update noofperson for other entries
//
//    }
//
//    public void updateArea(Areadetail area, String comcase, Integer caseslno) throws AreadetailDaoException, VilcodeDaoException {
//        AreadetailPk pk = new AreadetailPk();
//        pk.setCaseslno(caseslno);
//        pk.setComcaseno(comcase);
//        area.setCaseslno(caseslno);
//        area.setComcaseno(comcase);
//        VilcodeDaoImpl vdao = new VilcodeDaoImpl();
//        area.setVillcode(vdao.CustomDynamicSelect1("Select vlcode from vilcode where vlname=?", new Object[]{area.getVillage()}));
//        AreadetailDaoImpl adao = new AreadetailDaoImpl();
//        try {
//            adao.update(pk, area);
//        } catch (AreadetailDaoException ex) {
//            Logger.getLogger(LocationHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//}
;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;


import org.nic.epanjeeyan.dto.Category;
import org.nic.epanjeeyan.dto.LandFee;
import org.nic.epanjeeyan.exceptions.AreadetailDaoException;
import org.nic.epanjeeyan.exceptions.CategoryDaoException;
import org.nic.epanjeeyan.exceptions.ImplementSroDaoException;
import org.nic.epanjeeyan.exceptions.LandFeeDaoException;
import org.nic.epanjeeyan.exceptions.LandvalueDaoException;
import org.nic.epanjeeyan.exceptions.RegfeeDaoException;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.exceptions.RenquiryDaoException;
import org.nic.epanjeeyan.jdbc.CategoryDaoImpl;
import org.nic.epanjeeyan.jdbc.LandFeeDaoImpl;
import org.nic.epanjeeyan.jdbc.LandvalueDaoImpl;
import org.nic.epanjeeyan.jdbc.RegfeeDaoImpl;

/**
 *
 * @author ankita
 */
public class FeeCalculator {
    public int getMarketvalue(Integer consider,Integer estimate,Integer deed,String subdeed,Float bigha,Float katha,Float lessa,String loc) throws LandvalueDaoException, ImplementSroDaoException, AreadetailDaoException, AppointmentSlotBookingDaoException, CategoryDaoException {
      
        int i=0;
        LandvalueDaoImpl ldao=new LandvalueDaoImpl();

        if((deed==11) && ldao.findAll().length > 0)
        {
            i = (int)(getLandValue(bigha,katha,lessa,loc));
            estimate=i;
        }
        
        int conamount;
        if(consider>estimate)            
            conamount = consider;
        else 
            conamount = estimate;
        CategoryDaoImpl c=new CategoryDaoImpl();
        String sqll="select criteria from category where code=? and sub_deed_type=?";
        Object []obj=new Object[]{deed,subdeed};
        String criteria=c.CustomDynamicSelect1(sqll, obj);
        //String result;
        if(criteria.equals("a4") && conamount<1000) {
          // result="Market Value must be greater than 1000";
         return 0;
        }

        if(criteria.equals("a3")){
           if(conamount >=901 && conamount<=1000) {
           }
           else{     
              //result="Market Value must be within 901 and 1000";
           return 0;
           }
        }
        if(criteria.equals("a2")) {
           if(conamount >=501 && conamount<=900) {
           }
           else{
              //result="Market Value must be within 501 and 900";
           return 0;
           }
        }
        if(criteria.equals("a1") && conamount>500) {
           //result="Market Value cannot be greater than 500";
           return 0;
        }

        if(criteria.equals("RegFee/1000") && conamount<1000) {
           //result="Market Value must be greater than 1000";
            return 0;
        }

        if(criteria.equals("-1000") && conamount>1000) {
           //result= "Market Value must be lesser than 1000";
          return 0;
        }
        if(criteria.equals("SD/1000") && conamount<1000) {
           //result="Market Value must be greater than 1000";
          return 0;
        }
        if(criteria.equals("SD/5000") && conamount<5000) {
          // result="Market Value must be greater than 5000";
         return 0;
        }
        if(criteria.equals("a500-1000")) {
           if(conamount >500 && conamount<=1000) {
           }
           else{     
             // result="Market Value must be within 500 and 1000";
           return 0;
           }
        }
        if(criteria.equals("exceed900") && conamount<=900) {
           //result="Market Value must be greater than 900";
           return 0;
        }

        if(criteria.equals("between1001-5000")) {
           if(conamount >=1001 && conamount<=5000) {
           }
           else{
              //result="Market Value must be within 1001 and 5000";
           return 0;
           }
        }
         
         return (conamount);
         
    }

    public float getLandValue(Float bigha,Float katha,Float lessa,String loc) throws ImplementSroDaoException, AreadetailDaoException
    {
        
        Float amount = new Float("0");
        if( loc.equals(""))
            System.out.println("no land value");
        else
        {
            amount = new Float (loc.toString());
        }
        Float totalAreaFloat;
        Float totalValue;
        totalAreaFloat = bigha*100 + katha*20 + lessa;
        totalValue = (totalAreaFloat/100)* amount;
        return totalValue;
    }    
public int[] getFee(Integer deed, String subdeed, String gender, String area, Integer consider, Integer estimate, Float bigha, Float katha, Float lessa, String loc) throws RenquiryDaoException, LandFeeDaoException, CategoryDaoException, LandvalueDaoException, ImplementSroDaoException, AreadetailDaoException, RegfeeDaoException, AppointmentSlotBookingDaoException {

        int conamount = getMarketvalue(consider, estimate, deed, subdeed, bigha, katha, lessa, loc);
        int fees[] = new int[2];
        CategoryDaoImpl c = new CategoryDaoImpl();
        String sqll = "select act from category where code=? and sub_deed_type=?";
        Object[] obj = new Object[]{deed, subdeed};
        
        String act = c.CustomDynamicSelect1(sqll, obj);
        if (act.equals("18") || act.equals("23") || act.equals("33") || act.equals("31") || act.equals("58") || act.equals("54(23)") || act.equals("35(23)") || act.equals("63") || act.equals("40(23)") || act.equals("48(A+F)")) {
        
            if (gender.equals("") /*|| area.equals("")*/) {
//            fees[0] = -500;
//            fees[1] = -500;
                // e-Panjeeyan changes 13th January
                fees[0] = 0;
                fees[1] = 0;
                return fees;
            } else {
                fees = FeeforAct23(conamount, deed, subdeed, gender, area);
                return fees;
            }

        }

        if (act.equals("15") || act.equals("26") || act.equals("40") || act.equals("16") || act.equals("34") || act.equals("45") || act.equals("h1") || act.equals("57") || act.equals("64") || act.equals("35") || act.equals("12")) {
            fees = FeeforBond(conamount, deed, subdeed);
            return fees;
        }

        fees = Others(conamount, deed, subdeed);
        return fees;
    }

    public int[] FeeforAct23(int market_value, int deedtype, String deedsubtype, String gender, String area_type) throws LandFeeDaoException, RegfeeDaoException {
        int fees[] = new int[2];
        LandFee[] lfee = ExtractLandFee(gender);

        int common_fee = lfee[0].getFee();
        int board_fee = lfee[0].getUrbanMb();
        int corporation_fee = lfee[0].getUrbanMc();
        
      
        fees[0] = market_value * common_fee / 100;

        if (area_type.equals("UM")) {
            fees[0] = fees[0] + market_value * (board_fee) / 100;
        } 
        else if (area_type.equals("UG")) {
            fees[0] = fees[0] + market_value * (corporation_fee) / 100;
        } 

        if (deedtype == 6) {
            fees[1] = 30;
            return fees;
        }
        if (deedtype == 25) {
            fees[1] = -1;
            return fees;
        }
        fees[1] = getTheSlabFromregfeeAndSettheSlabValue(market_value);

        if (gender.equals("F") && deedtype == 11) {
            fees[1] = (int) (market_value * 0.01);
        }
        else if (deedtype == 11) {
            fees[1] = (int) (market_value * 0.02);
        }

        return fees;
    }
//public int[] getFee(Integer deed,String subdeed,String gender,String area,Integer consider,Integer estimate,Float bigha,Float katha,Float lessa,String loc) throws AppointmentSlotBookingDaoException, LandFeeDaoException, CategoryDaoException, LandvalueDaoException, ImplementSroDaoException, AreadetailDaoException, RegfeeDaoException {
//    
//    Integer conamount=getMarketvalue(consider,estimate,deed,subdeed,bigha,katha,lessa,loc);
//    int fees[] = new int[2];
//    CategoryDaoImpl c=new CategoryDaoImpl();
//    String sqll="select act from category where code=? and sub_deed_type=?";
//    Object []obj=new Object[]{deed,subdeed};
//    String act = c.CustomDynamicSelect1(sqll, obj);
//    if(act.equals("18") || act.equals("23") || act.equals("33") ||act.equals("31") || act.equals("58") || act.equals("54(23)") || act.equals("35(23)") || act.equals("63") || act.equals("40(23)") || act.equals("48(A+F)")) 
//    {
//        if(gender.equals("") ) {
//                    
//
//            fees[0] = -500;
//            fees[1] = -500;
//            System.out.println("GGGGGGGGGGGGGGGGGFeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees"+fees[0]);
//            return fees;
//        }
//        else {
//            fees =  FeeforAct23(conamount, deed, subdeed, gender, area);
//            
//            System.out.println("Feeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees"+fees[0]);
//            return fees;
//        }
//
//    }
//
//    if(act.equals("15") || act.equals("26") || act.equals("40") || act.equals("16") || act.equals("34") || act.equals("45") || act.equals("h1") || act.equals("57") || act.equals("64") || act.equals("35") ||act.equals("12")) {
//      fees =  FeeforBond(conamount, deed, subdeed);
//      return fees;
//    }
//    fees = Others(conamount,deed,subdeed);
//    return fees;
//}
//     
//   
//   public int[] FeeforAct23(int market_value, int deedtype, String deedsubtype, String gender, String area_type) throws LandFeeDaoException, RegfeeDaoException {
//   int fees[] = new int[2];
//   LandFee[] lfee = ExtractLandFee(gender);
//   
//     int common_fee = lfee[0].getFee();
//     int board_fee = lfee[0].getUrbanMb();
//     int corporation_fee = lfee[0].getUrbanMc();
//
//
//     if(gender.equals("F") && area_type.equals("R")) {
//          fees[0] = market_value * common_fee/100;
//     }
//    if(gender.equals("M") && area_type.equals("R")) {
//        fees[0] = market_value * common_fee/100;
//     }
//    if(gender.equals("F") && area_type.equals("UM")) {
//         fees[0] = market_value * (common_fee+board_fee)/100;
//      }
//     else if(gender.equals("F") && area_type.equals("UG")) {
//         fees[0] = market_value * (common_fee+corporation_fee)/100;
//      }
//      else if(gender.equals("M") && area_type.equals("UM")) {
//          fees[0] = market_value * (common_fee+board_fee)/100;
//       }
//      else if(gender.equals("M") && area_type.equals("UG")) {
//          fees[0] = market_value * (common_fee+corporation_fee)/100;
//        }
//
//     if(deedtype == 6) {
//         fees[1] = 30;
//          return fees;
//      }
//     if(deedtype == 25) {
//         fees[1] = -1;
//         return fees;
//    }
//   fees[1]=getTheSlabFromregfeeAndSettheSlabValue(market_value);
//      
//   if(gender.equals("F") && deedtype == 11)
//   {
//   fees[1] = (int) (market_value * 0.01);
//   }
//   if(gender.equals("M") && deedtype == 11)
//  {
//   fees[1] = (int) (market_value * 0.02);
//   }
//
//   return fees;
//}
   public LandFee[] ExtractLandFee(String gender) throws LandFeeDaoException {
    LandFee []lfee = null;
    LandFeeDaoImpl ldao=new LandFeeDaoImpl();
    lfee= ldao.findWhereGenderEquals(gender);
    return lfee;
}
public int getTheSlabFromregfeeAndSettheSlabValue(int considerationAmount) throws RegfeeDaoException{
int slab;
String sql = "Select amount From regfee where amt1<=? and amt2>=?";
   Object []obj=new Object[]{considerationAmount,considerationAmount};
   RegfeeDaoImpl rdao=new RegfeeDaoImpl();
   Integer i=rdao.CustomDynamicSelect(sql, obj);
   System.out.println("..a.."+i);
   if(considerationAmount>1000) {
       double exceed_1000 = (double)considerationAmount/1000;
       int slot_1000 = (int)Math.ceil(exceed_1000);
        slab = i*slot_1000;
   }
   else
       slab = i;
   return slab;
}
public int[] FeeforBond(int market_value, int deedtype, String deedsubtype) throws CategoryDaoException, RegfeeDaoException {

   int fees[] = new int[2];
    CategoryDaoImpl c=new CategoryDaoImpl();
   String sql = "code=? and sub_deed_type=?";
    Object obj[]=new Object[]{deedtype,deedsubtype};
    Category cat[] = c.findByDynamicWhere(sql, obj);
    int sfee = cat[0].getSfee();
    
    if(deedtype == 38) {
        if(cat[0].getCriteria().equals("SD/100 but not more than 113")) {
         double exceed_5000 = (double)market_value/100;
          int slot_100 = (int)Math.ceil(exceed_5000);
          fees[0] = slot_100 * sfee;
          if(fees[0]>113)
            fees[0] = 113;
        }
        else {
            fees[0]= sfee;          
       }
       
       fees[1] = getTheSlabFromregfeeAndSettheSlabValue(market_value);
       return fees;
    }

    if(deedtype == 33 || deedtype==32) {
        fees[0]= sfee;
        
        fees[1]=getTheSlabFromregfeeAndSettheSlabValue(market_value); 
        return fees;
    }

    else {
        if(cat[0].getCriteria().equals("a4")) {
            double exceed_1000 = (double)market_value/500;
           int slot_1000 = (int)Math.ceil(exceed_1000);
           fees[0] = slot_1000 * sfee;
       }
       else {
           fees[0] = sfee;
        }

            
        fees[1] = getTheSlabFromregfeeAndSettheSlabValue(market_value); 
        return fees;
    }
}

public int[] Others(int market_value, int deedtype, String deedsubtype) throws CategoryDaoException  {
    int fees[] = new int[2];
    CategoryDaoImpl c=new CategoryDaoImpl();
    String sql = " code=? and sub_deed_type=?";
    Object obj[]=new Object[]{deedtype,deedsubtype};
    Category cat[] = c.findByDynamicWhere(sql, obj);
    if(cat[0].getAct().equals("46(15)")) {
        if(market_value<500 || (market_value>=500 && market_value<=900) || (market_value>=901 && market_value<=1000)) {
            fees[0] = cat[0].getSfee();
            fees[1] = cat[0].getRfee();
        }

        else {
            fees[0] = cat[0].getSfee();
          
            double exceed_1000 = (double)market_value/1000;
            int slot_1000 = (int)Math.ceil(exceed_1000);
            fees[1] = slot_1000 *cat[0].getRfee();
       }
        return fees;
    }

    if(cat[0].getAct().equals("46") || cat[0].getAct().equals("4") || cat[0].getAct().equals("4(E)") || cat[0].getAct().equals("3") || cat[0].getAct().equals("29") || cat[0].getAct().equals("61") || cat[0].getAct().equals("55") || cat[0].getAct().equals("17") || cat[0].getAct().equals("48") || cat[0].getAct().equals("65") || cat[0].getAct().equals("54") || cat[0].getAct().equals("19") || cat[0].getAct().equals("20") || cat[0].getAct().equals("22")){
      fees[0] = cat[0].getSfee();
     fees[1] = cat[0].getRfee();
      return fees;
    }

    if(cat[0].getAct().equals("5") && cat[0].getCriteria().equals("F")) {
      fees[0] = cat[0].getSfee();
      fees[1] = cat[0].getRfee();
      return fees;
    }
    if(cat[0].getAct().equals("5") && cat[0].getCriteria().equals("SD/1000")) {
      double exceed_1000 = (double)market_value/1000;
      int slot_1000 = (int)Math.ceil(exceed_1000);
      fees[0] = slot_1000 *cat[0].getSfee() ;
      fees[1] = cat[0].getRfee();
      return fees;
    }
    if(cat[0].getAct().equals("5") && cat[0].getCriteria().equals("SD/5000")) {
      double exceed_1000 = (double)market_value/5000;
      int slot_5000 = (int)Math.ceil(exceed_1000);
      fees[0] = slot_5000 * cat[0].getSfee();
      fees[1] = cat[0].getRfee();
      return fees;
    }

    if(cat[0].getAct().toString().equals("42")) {
        if(cat[0].getCriteria().toString().equals("SD for every 10000")) {
          double exceed_10000 = (double)market_value/10000;
          int slot_10000 = (int)Math.ceil(exceed_10000);
          fees[0] = slot_10000 * cat[0].getSfee();
          fees[1] = cat[0].getRfee();
          return fees;
        }
        else {
         fees[0] = cat[0].getSfee();
          fees[1] = cat[0].getRfee();
          return fees;
        }
    }

    if(deedtype==22) {
          fees[0] = cat[0].getSfee();
         fees[1] = cat[0].getRfee();
          return fees;        
    }
    return fees;
}

}

    




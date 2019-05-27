package org.nic.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.nic.epanjeeyan.AppointmentHandler;
import org.nic.epanjeeyan.CaptchaHandler;
import org.nic.epanjeeyan.MarriageDataHandler;
import org.nic.epanjeeyan.ValidationHandler;
import org.nic.epanjeeyan.dto.AppointmentSlotBooking;
import org.nic.epanjeeyan.dto.ImplementSro;
import org.nic.epanjeeyan.dto.LastOfficerAssigned;
import org.nic.epanjeeyan.dto.Marriagecondn;
import org.nic.epanjeeyan.dto.NodalOfficers;
import org.nic.epanjeeyan.dto.Spmarrigenotice;
import org.nic.epanjeeyan.dto.SroOffices;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.exceptions.HolidayDaoException;
import org.nic.epanjeeyan.exceptions.ImplementSroDaoException;
import org.nic.epanjeeyan.exceptions.LastOfficerAssignedDaoException;
import org.nic.epanjeeyan.exceptions.MarriagecondnDaoException;
import org.nic.epanjeeyan.exceptions.MarriagefeeDaoException;
import org.nic.epanjeeyan.exceptions.MarriagetypeDaoException;
import org.nic.epanjeeyan.exceptions.NodalOfficersDaoException;
import org.nic.epanjeeyan.exceptions.OffDaysDaoException;
import org.nic.epanjeeyan.exceptions.SpmarrigenoticeDaoException;
import org.nic.epanjeeyan.exceptions.SroOfficesDaoException;
import org.nic.epanjeeyan.jdbc.AppointmentSlotBookingDaoImpl;
import org.nic.epanjeeyan.jdbc.ImplementSroDaoImpl;
import org.nic.epanjeeyan.jdbc.LastOfficerAssignedDaoImpl;
import org.nic.epanjeeyan.jdbc.MarriagecondnDaoImpl;
import org.nic.epanjeeyan.jdbc.MarriagefeeDaoImpl;
import org.nic.epanjeeyan.jdbc.MarriagetypeDaoImpl;
import org.nic.epanjeeyan.jdbc.NodalOfficersDaoImpl;
import org.nic.epanjeeyan.jdbc.SroOfficesDaoImpl;
import org.nic.util.CommonAttributes;

public class MarriageDataServelet extends HttpServlet {

    private String ReportTemplateFilePath;
    private static final String Report_template_url = "/resources/reporttemplates";
    private String ReportStoreFilePath;
    private static final String Report_store_url = "/resources/reports";
    private String ImageFilePath;
    private static final String imagetemplateUrl = "/resources/images";
    private int updateflag = 0;
    private String message = "";
    private ImplementSroDaoImpl implementsroDao = new ImplementSroDaoImpl();
    private ImplementSro[] implement = null;

    // get district code
    public String getDistrictCode() {
        String district_code = "";
        try {
            implement = implementsroDao.findAll();
            district_code = implement[0].getDistrictCode();
        } catch (ImplementSroDaoException ex) {
            Logger.getLogger(CommonAttributes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return district_code;
    }
// get sro code

    public String getSroCode() {
        String sro_code = "";
        try {
            implement = implementsroDao.findAll();
            sro_code = implement[0].getSroCode();
        } catch (ImplementSroDaoException ex) {
            Logger.getLogger(CommonAttributes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sro_code;
    }

    // get sro name

    public String getSroName() {
        String sro_code = "";
        try {
            implement = implementsroDao.findAll();
            sro_code = implement[0].getSroName();
        } catch (ImplementSroDaoException ex) {
            Logger.getLogger(CommonAttributes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sro_code;
    }
    // view mariages
    

    public void getMarriageMarriageNoticeForm(HttpServletRequest request, HttpServletResponse response) {
        MarriagetypeDaoImpl marriagetypedao = new MarriagetypeDaoImpl();
        MarriagecondnDaoImpl marriagecondtndao = new MarriagecondnDaoImpl();
        CommonAttributes common = new CommonAttributes();
        MarriagefeeDaoImpl marriagefeedao = new MarriagefeeDaoImpl();
        try {
            request.setAttribute("noticefee", marriagefeedao.findWhereFeeNameEquals("noticeFee")[0].getFeeValue());
            request.setAttribute("marriagetypes", marriagetypedao.findAll());
            request.setAttribute("marriagecondtn", marriagecondtndao.findAll());
            request.setAttribute("reportDate", common.getTodaysDate());
        } catch (MarriagefeeDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MarriagecondnDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MarriagetypeDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getListMarriageRegistrationNotices(HttpServletRequest request, HttpServletResponse response) {
        MarriageDataHandler marriageHandle = new MarriageDataHandler();
        request.setAttribute("marriagedetails", marriageHandle.viewMarriagesNotice());
        if (updateflag == 0) {
            request.getSession().setAttribute("StatusMessage", "");
        }
        if (updateflag == 1) {
            updateflag = 0;
            request.getSession().setAttribute("StatusMessage", message);
        }
    }

    public void editMarriageDetailsForm(HttpServletRequest request, HttpServletResponse response) {
        MarriagetypeDaoImpl marriagetypedao = new MarriagetypeDaoImpl();
        MarriagecondnDaoImpl marriagecondtndao = new MarriagecondnDaoImpl();
        CommonAttributes common = new CommonAttributes();
        MarriageDataHandler marriageHandle = new MarriageDataHandler();
        Spmarrigenotice spmarrigenotice = new Spmarrigenotice();
        try {
            spmarrigenotice = marriageHandle.editData(request.getParameter("nslno"));
            request.setAttribute("applicationdate", common.setDateFormatDDMMYY(spmarrigenotice.getDtappl()));
            request.setAttribute("marriagedetails", spmarrigenotice);
            request.setAttribute("marriagetype", marriagetypedao.findAll());
            request.setAttribute("marriagecondtn", marriagecondtndao.findAll());
            request.setAttribute("marriagetypename", marriageHandle.getMarriageTypeName(spmarrigenotice.getOldnew()));
        } catch (MarriagecondnDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MarriagetypeDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateMarriageNoticeForm(HttpServletRequest request, HttpServletResponse response, String contextpath) throws IOException {
        try {
            MarriageDataHandler marriageHandle = new MarriageDataHandler();
            Spmarrigenotice spmarriagenotice = new Spmarrigenotice();
            spmarriagenotice = marriageHandle.editData(request.getParameter("nslno"));
            Random ran = new Random();
            String message = "";
            int id = ran.nextInt();
            if (request.getParameter("InfoSlip") == null) {
                String cerificateheading = "\\intendedMrg_";
                String reportfile = "\\intendedMrg.jrxml";
                //message = marriageHandle.generateReport(spmarriagenotice.getNslno(), ReportTemplateFilePath, ReportStoreFilePath, id, cerificateheading, reportfile);
                message = "<div class='text-success'><strong>The Marriage Notice for this particular Ref. No. has been generated successfully</strong></div>";
                request.setAttribute("ReportUrl", marriageHandle.getReportUrl(Report_store_url, "/intendedMrg_", id, contextpath));
            }
            if (request.getParameter("NoticeSlip") == null) {
                String cerificateheading = "\\MrgInfoSlip_";
                String reportfile = "\\InfoSlip.jrxml";
                //message = marriageHandle.generateReport(spmarriagenotice.getNslno(), ReportTemplateFilePath, ReportStoreFilePath, id, cerificateheading, reportfile);
                message = "<div class='text-success'><strong>The Information Slip for this particular Ref. No. has been generated successfully</strong></div>";
                request.setAttribute("ReportUrl", marriageHandle.getReportUrl(Report_store_url, "/MrgInfoSlip_", id, contextpath));
            }
            request.setAttribute("StatusMessage", message);
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/generatenoticeform");
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // @ to do to generate mrrg declaration certificate

    public void updateMarriageDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarriagefeeDaoImpl marriagefeedao = new MarriagefeeDaoImpl();
        MarriageDataHandler marriageHandle = new MarriageDataHandler();
        Spmarrigenotice spmarriagenotice = new Spmarrigenotice();
        CommonAttributes common = new CommonAttributes();
        if (request.getParameter("MarriageType").equals("marriage(New)")) {
            spmarriagenotice.setOldnew("New");
        }
        if (request.getParameter("MarriageType").equals("marriage(Old)")) {
            spmarriagenotice.setOldnew("Old");
        }
        spmarriagenotice.setNslno(request.getParameter("nslno"));
        spmarriagenotice.setNmappl(request.getParameter("ApplicantName"));
        spmarriagenotice.setBridename(request.getParameter("BrideName"));
        spmarriagenotice.setBrideage(Integer.parseInt(request.getParameter("BrideAge")));
        spmarriagenotice.setBridecondi(request.getParameter("BrideMarriageCondition"));
        spmarriagenotice.setBrideoccu(request.getParameter("BrideOccupation"));
        spmarriagenotice.setBridefather(request.getParameter("BrideFathersName"));
        spmarriagenotice.setBridevillage(request.getParameter("BrideVillage"));
        spmarriagenotice.setBrideps(request.getParameter("BridePoliceStation"));
        spmarriagenotice.setBridepo(request.getParameter("BridePostOffice"));
        spmarriagenotice.setBridedistrict(request.getParameter("BrideDistrict"));
        spmarriagenotice.setBridestate(request.getParameter("BrideState"));
        spmarriagenotice.setBridepin(Integer.parseInt(request.getParameter("BridePincode")));
        spmarriagenotice.setBrideVillPrmnt(request.getParameter("BridePermanentVillage"));
        spmarriagenotice.setBridePOPrmnt(request.getParameter("BridePermanentPO"));
        spmarriagenotice.setBridePSPrmnt(request.getParameter("BridePermanentPS"));
        spmarriagenotice.setBrideDistPrmnt(request.getParameter("BridePermanentDistrict"));
        spmarriagenotice.setBrideStatePrmnt(request.getParameter("BridePermanentState"));
        spmarriagenotice.setBridePinPrmnt(Integer.parseInt(request.getParameter("BridePermanentPincode")));
        spmarriagenotice.setBrdLenRes(request.getParameter("LengthOfResidence"));
        spmarriagenotice.setBgroomname(request.getParameter("GroomName"));
        spmarriagenotice.setBgroomage(Integer.parseInt(request.getParameter("GroomAge")));
        spmarriagenotice.setBgroomcondi(request.getParameter("GroomMarriageCondition"));
        spmarriagenotice.setBgroomoccu(request.getParameter("GroomOccupation"));
        spmarriagenotice.setBgroomfather(request.getParameter("GroomFathersName"));
        spmarriagenotice.setBgroomvillage(request.getParameter("GroomVillage"));
        spmarriagenotice.setBgroomps(request.getParameter("GroomPoliceStation"));
        spmarriagenotice.setBgroompo(request.getParameter("GroomPostOffice"));
        spmarriagenotice.setBgroomdistrict(request.getParameter("GroomDistrict"));
        spmarriagenotice.setBgroomstate(request.getParameter("GroomState"));
        spmarriagenotice.setBgroompin(Integer.parseInt(request.getParameter("GroomPincode")));
        spmarriagenotice.setGrmVillPrmnt(request.getParameter("GroomPermanentVillage"));
        spmarriagenotice.setGrmPOPrmnt(request.getParameter("GroomPermanentPO"));
        spmarriagenotice.setGrmPSPrmnt(request.getParameter("GroomPermanentPS"));
        spmarriagenotice.setGrmDistPrmnt(request.getParameter("GroomPermanentDistrict"));
        spmarriagenotice.setGrmStatePrmnt(request.getParameter("GroomPermanentState"));
        spmarriagenotice.setGrmPinPrmnt(Integer.parseInt(request.getParameter("GroomPermanentPincode")));
        spmarriagenotice.setGrmLenRes(request.getParameter("LengthOfResidence"));
        spmarriagenotice.setNtfee(Integer.parseInt(request.getParameter("NoticeFee")));
        spmarriagenotice.setDtappl(common.setParseDate(request.getParameter("ApplicationDate")));
        spmarriagenotice.setDistrictCode(getDistrictCode());
        spmarriagenotice.setSroCode(getSroCode());
        spmarriagenotice.setSroName(getSroName());
        spmarriagenotice.setDatemp(common.setParseDate(common.getTodaysDate()));
        try {
            spmarriagenotice.setCertifee(marriagefeedao.findWhereFeeNameEquals("certFee")[0].getFeeValue());
        } catch (MarriagefeeDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
        message = marriageHandle.updateMarriageDetails(spmarriagenotice);
        updateflag = 1;
        response.sendRedirect("/panjeeyanonline/listmarriages");
    }

    public void postMarriageRegistrationNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, AppointmentSlotBookingDaoException {
        
       // Insert into apppointment table
        String sql1 = "select count(*) from appointment_slot_booking";
        String sql = "Select max(slno) from appointment_slot_booking";
//        int officerId = 1;
        DateFormat formSdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AppointmentSlotBookingDaoImpl rDao1 = new AppointmentSlotBookingDaoImpl();
        AppointmentSlotBooking appointmentDto = new AppointmentSlotBooking();
        Integer a, slno=0;
        Calendar cal = Calendar.getInstance();
        Integer year = cal.get(Calendar.YEAR);
        String temp = null;
 
        a = rDao1.CustomDynamicSelect(sql1, null);
        
        if (a == 0) {   
            slno = 1;
            temp = "tmp/1/" + year;
        } 
        else {
            a = rDao1.CustomDynamicSelect(sql, null);
            Integer k = a + 1;
            String sql2 = "select appointment_id from appointment_slot_booking where slno=(select max(slno)from appointment_slot_booking)";
            AppointmentSlotBookingDaoImpl rDao2 = new AppointmentSlotBookingDaoImpl();
            String b;

                b = rDao2.CustomDynamicSelect1(sql2, null);
                String c = b.substring((b.lastIndexOf('/') + 1), b.length());
                String d = b.substring((b.indexOf('/') + 1), b.lastIndexOf('/'));
                Integer e = Integer.parseInt(c);
                Integer f = Integer.parseInt(d);
                Calendar now = Calendar.getInstance();
                Integer g = now.get(Calendar.YEAR);
                if (g.toString().equals(e.toString())) {
                    Integer l = f + 1;
                    slno = l;
                    temp = "AS-MAR/" + l + "/" + e;
                } else {
                    slno = 1;
                    temp = "AS-MAR/1/" + g;
                }
        }
        appointmentDto.setAct("M");
        appointmentDto.setApplicantName(request.getParameter("ApplicantName"));
        formSdf = new SimpleDateFormat("dd-MM-yyyy");
        dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
          appointmentDto.setAppointmentDate(dbSdf.parse(request.getParameter("appointment_date")));
        }catch (ParseException e) {
        }
        //appointmentDto.setSlno(slno);
        appointmentDto.setDeedType(0);
        appointmentDto.setDeedSubtype("Marriage");
        appointmentDto.setSroOffice(Integer.parseInt(request.getParameter("sro_office")));
        appointmentDto.setAppointmentId(temp);
        appointmentDto.setEmail(request.getParameter("email"));
        appointmentDto.setMobileNumber(request.getParameter("mobile"));
        appointmentDto.setApplicantAddress(request.getParameter("BrideVillage"));
        appointmentDto.setApplicantType("0");
        
        appointmentDto.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart());
        appointmentDto.setAppointmentDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getParameter("appointment_date")))));
        Date date = new Date();
        appointmentDto.setApplicationDateTime(date);
        rDao1.insert(appointmentDto);
        
       // End here 
        MarriageDataHandler marriageHandle = new MarriageDataHandler();
        Spmarrigenotice spmarriagenotice = new Spmarrigenotice();
        CommonAttributes common = new CommonAttributes();
        MarriagefeeDaoImpl marriagefeedao = new MarriagefeeDaoImpl();
        if (request.getParameter("MarriageType").equals("marriage(New)")) {
            spmarriagenotice.setOldnew("New");
        }
        if (request.getParameter("MarriageType").equals("marriage(Old)")) {
            spmarriagenotice.setOldnew("Old");
        }
        spmarriagenotice.setNmappl(request.getParameter("ApplicantName"));
        spmarriagenotice.setBridename(request.getParameter("BrideName"));
        spmarriagenotice.setBrideage(Integer.parseInt(request.getParameter("BrideAge")));
        spmarriagenotice.setBridecondi(request.getParameter("BrideMarriageCondition"));
        spmarriagenotice.setBrideoccu(request.getParameter("BrideOccupation"));
        spmarriagenotice.setBridefather(request.getParameter("BrideFathersName"));
        spmarriagenotice.setBridevillage(request.getParameter("BrideVillage"));
        spmarriagenotice.setBrideps(request.getParameter("BridePoliceStation"));
        spmarriagenotice.setBridepo(request.getParameter("BridePostOffice"));
        spmarriagenotice.setBridedistrict(request.getParameter("BrideDistrict"));
        spmarriagenotice.setBridestate(request.getParameter("BrideState"));
        spmarriagenotice.setBridepin(Integer.parseInt(request.getParameter("BridePincode")));
        spmarriagenotice.setBrideVillPrmnt(request.getParameter("BridePermanentVillage"));
        spmarriagenotice.setBridePOPrmnt(request.getParameter("BridePermanentPO"));
        spmarriagenotice.setBridePSPrmnt(request.getParameter("BridePermanentPS"));
        spmarriagenotice.setBrideDistPrmnt(request.getParameter("BridePermanentDistrict"));
        spmarriagenotice.setBrideStatePrmnt(request.getParameter("BridePermanentState"));
        spmarriagenotice.setBridePinPrmnt(Integer.parseInt(request.getParameter("BridePermanentPincode")));
        spmarriagenotice.setBrdLenRes(request.getParameter("LengthOfResidence"));
        spmarriagenotice.setBgroomname(request.getParameter("GroomName"));
        spmarriagenotice.setBgroomage(Integer.parseInt(request.getParameter("GroomAge")));
        spmarriagenotice.setBgroomcondi(request.getParameter("GroomMarriageCondition"));
        spmarriagenotice.setBgroomoccu(request.getParameter("GroomOccupation"));
        spmarriagenotice.setBgroomfather(request.getParameter("GroomFathersName"));
        spmarriagenotice.setBgroomvillage(request.getParameter("GroomVillage"));
        spmarriagenotice.setBgroomps(request.getParameter("GroomPoliceStation"));
        spmarriagenotice.setBgroompo(request.getParameter("GroomPostOffice"));
        spmarriagenotice.setBgroomdistrict(request.getParameter("GroomDistrict"));
        spmarriagenotice.setBgroomstate(request.getParameter("GroomState"));
        spmarriagenotice.setBgroompin(Integer.parseInt(request.getParameter("GroomPincode")));
        spmarriagenotice.setGrmVillPrmnt(request.getParameter("GroomPermanentVillage"));
        spmarriagenotice.setGrmPOPrmnt(request.getParameter("GroomPermanentPO"));
        spmarriagenotice.setGrmPSPrmnt(request.getParameter("GroomPermanentPS"));
        spmarriagenotice.setGrmDistPrmnt(request.getParameter("GroomPermanentDistrict"));
        spmarriagenotice.setGrmStatePrmnt(request.getParameter("GroomPermanentState"));
        spmarriagenotice.setGrmPinPrmnt(Integer.parseInt(request.getParameter("GroomPermanentPincode")));
        spmarriagenotice.setGrmLenRes(request.getParameter("LengthOfResidence"));
        spmarriagenotice.setNtfee(2);
        spmarriagenotice.setDtappl(common.setParseDate(request.getParameter("appointment_date")));
        try {
            spmarriagenotice.setCertifee(marriagefeedao.findWhereFeeNameEquals("certFee")[0].getFeeValue());
        } catch (MarriagefeeDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
        spmarriagenotice.setDistrictCode(getDistrictCode());
        spmarriagenotice.setSroCode(getSroCode());
        spmarriagenotice.setSroName(getSroName());
        spmarriagenotice.setDatemp(common.setParseDate(common.getTodaysDate()));
        try {
            spmarriagenotice.setSlno(slno);
            spmarriagenotice.setNslno(temp);
            
            request.getSession().setAttribute("appointment_fee_structure", "<div class=\"appointment_details\"><br><b>Appointment Details as follows </b><br>Appointment ID: <label id=\"Message\" value=\"" + temp + "\">" + temp + "</div>");
            request.getSession().setAttribute("appointmentIdMarriage", temp);
            String nslno = marriageHandle.insert(spmarriagenotice);
            request.getSession().setAttribute("nslno", nslno);
        } catch (SpmarrigenoticeDaoException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //response.sendRedirect("/panjeeyanonline/successMarriageNotice");
        
        JSONObject json = new JSONObject();
        try {
            json.put("appointment_id", temp);
            
        } 
        catch (JSONException ex) {
            Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        System.out.println("####################################################################################");
        System.out.println(json.toString());
        
        
        
        
        // send json response to Android
        response.getWriter().write(json.toString());
        
    }
    
    
    
    
    //Get list of marriage registration Entries End

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        if (userPath.equals("/addmarriagedetails")) {
            try {
                new CaptchaHandler().setCaptcha(request);
                SroOfficesDaoImpl sro_office_dao = new SroOfficesDaoImpl();
                request.setAttribute("SroOffices", sro_office_dao.findAll());
                request.setAttribute("dates", new AppointmentHandler().getAppointmentDates("marriage"));
                getMarriageMarriageNoticeForm(request, response);
            } catch (SroOfficesDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HolidayDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OffDaysDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (userPath.equals("/listmarriages")) {
            getListMarriageRegistrationNotices(request, response);
        }
        if (userPath.equals("/editmarriagedetails")) {
            editMarriageDetailsForm(request, response);
        }

        if (userPath.equals("/successMarriageNotice")) {
            userPath = userPath;
        }
        String url = "/WEB-INF/view/marriage/" + userPath + ".jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        // necessary
        if (userPath.equals("/addmarriagedetails")) {
            
              System.out.println("##########################################################################");
                for (String key : request.getParameterMap().keySet()) {
                System.out.println(key + " : " + request.getParameter(key));
            }
            
            
            
            
            try {
//                if(request.getSession().getAttribute("ran1") == null || request.getSession().getAttribute("ran2") == null) {
//                    request.setAttribute("errormsg", "Invalid reqest.");
//                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/error/error.jsp");
//                    view.forward(request, response);
//                    return;
//                }
                SroOffices[] sroOffices = new SroOfficesDaoImpl().findAll();
                String[] offices = new String[sroOffices.length]; 
                for(int i = 0; i < sroOffices.length; i++) {
                    offices[i] = Integer.toString(sroOffices[i].getId());
                }
                
                Marriagecondn[] maritalStatList = new MarriagecondnDaoImpl().findAll();
                String[] statusList = new String[maritalStatList.length]; 
                for(int i = 0; i < maritalStatList.length; i++) {
                    statusList[i] = maritalStatList[i].getMarriageCondn();
                }
                
                ArrayList dates = new AppointmentHandler().getAppointmentDates("marriage");
                String[] datesList = new String[dates.size()]; 
                for(int i = 0; i < dates.size(); i++) {
                    datesList[i] = (String) dates.get(i);
                }
                
                this.setResponse(request);
//                
//                String ran1 = request.getSession().getAttribute("ran1").toString();
//                String ran2 = request.getSession().getAttribute("ran2").toString();
                
                //String res = Integer.toString((Integer.parseInt(ran1) + Integer.parseInt(ran2)));
                
                ValidationHandler validator = new ValidationHandler();
                
                validator.validate(request.getParameter("appointment_date"), "Desired Appointment date", "valuein", datesList);
                validator.validate(request.getParameter("MarriageType"), "Marriage Type", "valuein", new String[]{"Intended Marriage", "Marriage"});
                validator.validate(request.getParameter("ApplicantName"), "Name of the applicant", "required:max_40:sql:xss");
                validator.validate(request.getParameter("email"), "e-mail", "email:max_30:sql:xss");
                validator.validate(request.getParameter("mobile"), "Mobile number", "required:mobile:sql:xss");
                validator.validate(request.getParameter("sro_office"), "Office for Registration", "valuein", offices);
                
                validator.validate(request.getParameter("BrideName"), "Name of Bride", "required:max_100:sql:xss");
                validator.validate(request.getParameter("BrideAge"), "Age", "required:numeric:max_3:low_18:high_200");
                validator.validate(request.getParameter("BrideMarriageCondition"), "Marital Status", "valuein", statusList);
                validator.validate(request.getParameter("BrideOccupation"), "Occupation", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BrideFathersName"), "Father's Name", "required:max_100:sql:xss");
                
                validator.validate(request.getParameter("BrideVillage"), "Village/City/Locality", "required:max_250:sql:xss");
                validator.validate(request.getParameter("BridePoliceStation"), "Police Station", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BridePostOffice"), "Post Office", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BrideDistrict"), "District", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BrideState"), "State", "required:max_30:sql:xss");
                validator.validate(request.getParameter("BridePincode"), "PIN code", "required:PIN:sql:xss");
                
                validator.validate(request.getParameter("BridePermanentVillage"), "Village/City/Locality", "required:max_250:sql:xss");
                validator.validate(request.getParameter("BridePermanentPS"), "Police Station", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BridePermanentPO"), "Post Office", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BridePermanentDistrict"), "District", "required:max_50:sql:xss");
                validator.validate(request.getParameter("BridePermanentState"), "State", "required:max_30:sql:xss");
                validator.validate(request.getParameter("BridePermanentPincode"), "PIN Code", "required:PIN:sql:xss");
                validator.validate(request.getParameter("BrideLengthOfResidence"), "Length of residence at present address in years", "required:numeric:max_3:low_0:high_200");
                
                validator.validate(request.getParameter("GroomName"), "Name of Groom", "required:max_100:sql:xss");
                validator.validate(request.getParameter("GroomAge"), "Age", "required:numeric:max_3:low_21:high_200");
                validator.validate(request.getParameter("GroomMarriageCondition"), "Marital Status", "valuein", statusList);
                validator.validate(request.getParameter("GroomOccupation"), "Occupation", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomFathersName"), "Father's Name", "required:max_100:sql:xss");
                
                validator.validate(request.getParameter("GroomVillage"), "Village/City/Locality", "required:max_250:sql:xss");
                validator.validate(request.getParameter("GroomPoliceStation"), "Police Station", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomPostOffice"), "Post Office", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomDistrict"), "District", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomState"), "State", "required:max_30:sql:xss");
                validator.validate(request.getParameter("GroomPincode"), "PIN code", "required:PIN:sql:xss");
                
                validator.validate(request.getParameter("GroomPermanentVillage"), "Village/City/Locality", "required:max_250:sql:xss");
                validator.validate(request.getParameter("GroomPermanentPS"), "Police Station", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomPermanentPO"), "Post Office", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomPermanentDistrict"), "District", "required:max_50:sql:xss");
                validator.validate(request.getParameter("GroomPermanentState"), "State", "required:max_30:sql:xss");
                validator.validate(request.getParameter("GroomPermanentPincode"), "PIN Code", "required:PIN:sql:xss");
                validator.validate(request.getParameter("gLengthOfResidence"), "Length of residence at present address in years", "required:numeric:max_3:low_0:high_200");
                
                //validator.validate(request.getParameter("captcha"), "Security Question", "valuein", new String[]{res});
                
                
                ArrayList<String> errors = validator.getErrors();
                new CaptchaHandler().setCaptcha(request);
                if (!errors.isEmpty()) {
                    try {
                        request.setAttribute("errormsg", errors);
                        request.setAttribute("dates", dates);
                        request.setAttribute("SroOffices", sroOffices);
                        request.setAttribute("marriagetypes", new MarriagetypeDaoImpl().findAll());
                        request.setAttribute("marriagecondtn", maritalStatList);
                        
                        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/marriage/addmarriagedetails.jsp");
                        view.forward(request, response);
                        
                    } catch (MarriagetypeDaoException ex) {
                        Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    postMarriageRegistrationNotice(request, response);
                } 
            } catch (SroOfficesDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MarriagecondnDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HolidayDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OffDaysDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MarriageDataServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if (userPath.equals("/editmarriagedetails")) {
            editMarriageDetailsForm(request, response);
            String url = "/WEB-INF/view/marriage/" + userPath + ".jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
        if (userPath.equals("/updatemarriagedetails")) {
            updateMarriageDetails(request, response);
        }
        if (userPath.equals("/generatenoticeform")) {
            editMarriageDetailsForm(request, response);
            String url = "/WEB-INF/view/marriage/" + userPath + ".jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
        if (userPath.equals("/generatenotice")) {
            ReportTemplateFilePath = getServletContext().getRealPath(Report_template_url);
            ReportStoreFilePath = getServletContext().getRealPath(Report_store_url);
            generateMarriageNoticeForm(request, response, getServletContext().getContextPath());
        }
//        

    }
    
    
    public int getNoOfAppointmentsPerOfficer(int officer_id, int office_id, String appointment_date) throws AppointmentSlotBookingDaoException {
        System.out.println("I am called");
        String query = "select count(officer_id) from appointment_slot_booking where sro_office=? and officer_id=? and appointment_date like '" + appointment_date + "%'";
        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
        Object[] sqlParams = new Object[]{office_id, officer_id};
        Integer num = rdao.CustomDynamicSelect(query, sqlParams);
        return num;
    }
    
    public Date getLastTImeSlotPerOfficer(int office_id, int officer_id, Date appointmentDate) throws AppointmentSlotBookingDaoException{
        DateFormat formSdf = new SimpleDateFormat("yyyy-MM-dd");
        String appointmentDateString = formSdf.format(appointmentDate);
        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
        Object[] sqlParams = new Object[]{office_id, officer_id};
        String query = "sro_office=? and officer_id =? and appointment_date like '" + appointmentDateString + "%' order by appointment_date DESC";
        AppointmentSlotBooking[] appointment = rdao.findByDynamicWhere(query, sqlParams);
        Calendar cal = Calendar.getInstance();
        DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        if (appointment.length > 0) {
          cal.setTime(appointment[0].getAppointmentDate());
          cal.add(Calendar.MINUTE, 15);
          String newTime = dbSdf.format(cal.getTime());
          return dbSdf.parse(newTime);
        }
        cal.setTime(appointmentDate);
        cal.add(Calendar.HOUR, 10);
        cal.add(Calendar.MINUTE, 00);
        cal.add(Calendar.SECOND, 00);
        return dbSdf.parse(dbSdf.format(cal.getTime()));
        }catch(ParseException p) {
            
        }
        return null;
    }
    
    private void setResponse(HttpServletRequest request) {
        request.setAttribute("appdate", request.getParameter("appointment_date"));
        request.setAttribute("marriagetype", request.getParameter("MarriageType"));
        request.setAttribute("applicant", request.getParameter("ApplicantName"));
        if(StringUtils.isNumeric(request.getParameter("sro_office"))) {request.setAttribute("sro", request.getParameter("sro_office"));}
        else {request.setAttribute("sro", 0);}
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("mobile", request.getParameter("mobile"));
        
        request.setAttribute("bride", request.getParameter("BrideName"));
        request.setAttribute("bage", request.getParameter("BrideAge"));
        request.setAttribute("bmcondition", request.getParameter("BrideMarriageCondition"));
        request.setAttribute("boccupation", request.getParameter("BrideOccupation"));
        request.setAttribute("bfather", request.getParameter("BrideFathersName"));
        
        request.setAttribute("bvil", request.getParameter("BrideVillage"));
        request.setAttribute("bpolstat", request.getParameter("BridePoliceStation"));
        request.setAttribute("bpostoff", request.getParameter("BridePostOffice"));
        request.setAttribute("bdist", request.getParameter("BrideDistrict"));
        request.setAttribute("bstate", request.getParameter("BrideState"));
        request.setAttribute("bpin", request.getParameter("BridePincode"));
        
        request.setAttribute("permbvil", request.getParameter("BridePermanentVillage"));
        request.setAttribute("permbpolstat", request.getParameter("BridePermanentPS"));
        request.setAttribute("permbpostoff", request.getParameter("BridePermanentPO"));
        request.setAttribute("permbdist", request.getParameter("BridePermanentDistrict"));
        request.setAttribute("permbstate", request.getParameter("BridePermanentState"));
        request.setAttribute("permbpin", request.getParameter("BridePermanentPincode"));
        request.setAttribute("blenthofres", request.getParameter("BrideLengthOfResidence"));
        
        request.setAttribute("groom", request.getParameter("GroomName"));
        request.setAttribute("gage", request.getParameter("GroomAge"));
        request.setAttribute("gmcondition", request.getParameter("GroomMarriageCondition"));
        request.setAttribute("goccupation", request.getParameter("GroomOccupation"));
        request.setAttribute("gfather", request.getParameter("GroomFathersName"));
        
        request.setAttribute("gvil", request.getParameter("GroomVillage"));
        request.setAttribute("gpolstat", request.getParameter("GroomPoliceStation"));
        request.setAttribute("gpostoff", request.getParameter("GroomPostOffice"));
        request.setAttribute("gdist", request.getParameter("GroomDistrict"));
        request.setAttribute("gstate", request.getParameter("GroomState"));
        request.setAttribute("gpin", request.getParameter("GroomPincode"));
        
        request.setAttribute("permgvil", request.getParameter("GroomPermanentVillage"));
        request.setAttribute("permgpolstat", request.getParameter("GroomPermanentPS"));
        request.setAttribute("permgpostoff", request.getParameter("GroomPermanentPO"));
        request.setAttribute("permgdist", request.getParameter("GroomPermanentDistrict"));
        request.setAttribute("permgstate", request.getParameter("GroomPermanentState"));
        request.setAttribute("permgpin", request.getParameter("GroomPermanentPincode"));
        request.setAttribute("glenthofres", request.getParameter("gLengthOfResidence"));
    
    }
   
}

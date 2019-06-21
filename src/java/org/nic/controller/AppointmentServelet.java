/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nic.epanjeeyan.AppointmentApproveHandler;
import org.nic.epanjeeyan.AppointmentHandler;
import org.nic.epanjeeyan.FeeCalculator;
import org.nic.epanjeeyan.dto.Category;
import org.nic.epanjeeyan.dto.AppointmentSlotBooking;
import org.nic.epanjeeyan.exceptions.AreadetailDaoException;
import org.nic.epanjeeyan.exceptions.CategoryDaoException;
import org.nic.epanjeeyan.exceptions.DeedtypeDaoException;
import org.nic.epanjeeyan.exceptions.ImplementSroDaoException;
import org.nic.epanjeeyan.exceptions.LandFeeDaoException;
import org.nic.epanjeeyan.exceptions.LandvalueDaoException;
import org.nic.epanjeeyan.exceptions.RegfeeDaoException;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.exceptions.SroOfficesDaoException;
import org.nic.epanjeeyan.UpcomingAppointmentsHandler;
import org.nic.epanjeeyan.jdbc.AppointmentSlotBookingDaoImpl;
import org.nic.epanjeeyan.jdbc.DeedtypeDaoImpl;
import org.nic.epanjeeyan.jdbc.SroOfficesDaoImpl;
import javax.servlet.annotation.MultipartConfig;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Part;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nic.epanjeeyan.CaptchaHandler;
import org.nic.epanjeeyan.ValidationHandler;
import org.nic.epanjeeyan.ViewAppointmentDocs;
import org.nic.epanjeeyan.dto.AppointmentDetails;
import org.nic.epanjeeyan.dto.AppointmentDocuments;
import org.nic.epanjeeyan.dto.CancelledAppointments;
import org.nic.epanjeeyan.dto.Dbusers;
import org.nic.epanjeeyan.dto.Deedtype;
import org.nic.epanjeeyan.dto.EgrasResponse;
import org.nic.epanjeeyan.dto.EgrasResponseLog;
import org.nic.epanjeeyan.dto.EgrasResponseLogPk;
import org.nic.epanjeeyan.dto.EgrasResponsePk;
import org.nic.epanjeeyan.dto.SroOffices;
import org.nic.epanjeeyan.exceptions.ApplicantTypeDaoException;
import org.nic.epanjeeyan.exceptions.AppointmentDetailsDaoException;
import org.nic.epanjeeyan.exceptions.AppointmentDocumentsDaoException;
import org.nic.epanjeeyan.exceptions.CancelledAppointmentsDaoException;
import org.nic.epanjeeyan.exceptions.DbusersDaoException;
import org.nic.epanjeeyan.exceptions.EgrasResponseDaoException;
import org.nic.epanjeeyan.exceptions.EgrasResponseLogDaoException;
import org.nic.epanjeeyan.exceptions.HolidayDaoException;
import org.nic.epanjeeyan.exceptions.LandTypeDaoException;
import org.nic.epanjeeyan.exceptions.NodalOfficersDaoException;
import org.nic.epanjeeyan.exceptions.OffDaysDaoException;
import org.nic.epanjeeyan.exceptions.RenquiryDaoException;
import org.nic.epanjeeyan.jdbc.ApplicantTypeDaoImpl;
import org.nic.epanjeeyan.jdbc.AppointmentDocumentsDaoImpl;
import org.nic.epanjeeyan.jdbc.CancelledAppointmentsDaoImpl;
import org.nic.epanjeeyan.jdbc.CategoryDaoImpl;
import org.nic.epanjeeyan.jdbc.DbusersDaoImpl;
import org.nic.epanjeeyan.jdbc.EgrasResponseDaoImpl;
import org.nic.epanjeeyan.jdbc.EgrasResponseLogDaoImpl;
import org.nic.epanjeeyan.jdbc.LandTypeDaoImpl;
import org.nic.util.CommonAttributes;

// important code for getting multipart
@MultipartConfig(maxFileSize = 1617721500)
/**
 *
 * @author ankita
 */
public class AppointmentServelet extends HttpServlet {

    private int updateFlag = 0;

    public String getSroOffice() throws SroOfficesDaoException, JSONException {
        JSONObject jsonData = new JSONObject();
        ArrayList sroOfficeList = new ArrayList();
        SroOffices[] sroOffices = new SroOfficesDaoImpl().findAll();
        //String[] offices = new String[sroOffices.length]; 
        for (int i = 0; i < sroOffices.length; i++) {
            JSONObject jsonData2 = new JSONObject();
            //deedList[i] = Integer.toString(deedTypes[i].getCode());
            jsonData2.put("Code", sroOffices[i].getId());
            jsonData2.put("Name", sroOffices[i].getOfficeName());
            sroOfficeList.add(i, jsonData2.toString());
            //offices[i] = Integer.toString(sroOffices[i].getId());
        }
        jsonData.put("sroOfficeList", sroOfficeList);
        return jsonData.toString();
    }

    public String getSubDeed(String id) throws JSONException {
        JSONObject jsonData = new JSONObject();
        try {
            //String id = request.getParameter("id");

            Deedtype[] deedTypes = new DeedtypeDaoImpl().findAll();
            String[] deedList = new String[deedTypes.length];
            for (int i = 0; i < deedTypes.length; i++) {
                deedList[i] = Integer.toString(deedTypes[i].getCode());
            }
            ValidationHandler validator = new ValidationHandler();
            validator.validate(id, "Deed Category", "valuein", deedList);
            ArrayList subdeedList = new ArrayList();
            ArrayList<String> errors = validator.getErrors();
            if (!errors.isEmpty()) {
                jsonData.put("Status", "Error");
                jsonData.put("errMsg", errors);
                jsonData.put("subdeedlist", "");
            } else {
                AppointmentHandler enq = new AppointmentHandler();
                Category[] categories = enq.getSubDeedDropdown(id);
                jsonData.put("Status", "OK");
                jsonData.put("errMsg", "");
                for (int i = 0; i < categories.length; i++) {
                    JSONObject jsonData2 = new JSONObject();
                    jsonData2.put("subDeed", categories[i].getSubDeedType());
                    subdeedList.add(i, jsonData2.toString());
                }
                jsonData.put("subdeedlist", subdeedList);
            }

        } catch (DeedtypeDaoException ex) {
            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonData.toString();
    }

    public String getDeedTypes() throws JSONException {
        JSONObject jsonData = new JSONObject();
        Deedtype[] deedTypes = null;

        try {
            deedTypes = new DeedtypeDaoImpl().findAll();
            //String[] deedList = new String[deedTypes.length];
            ArrayList deedList = new ArrayList();
            for (int i = 0; i < deedTypes.length; i++) {
                JSONObject jsonData2 = new JSONObject();
                //deedList[i] = Integer.toString(deedTypes[i].getCode());
                jsonData2.put("Code", deedTypes[i].getCode());
                jsonData2.put("Section", deedTypes[i].getSection());
                deedList.add(i, jsonData2.toString());
            }

            jsonData.put("deedlist", deedList);

        } catch (DeedtypeDaoException ex) {
            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonData.toString();
    }

    public String create_appointment(HttpServletRequest request) throws JSONException, DeedtypeDaoException, CategoryDaoException, SroOfficesDaoException, HolidayDaoException, OffDaysDaoException, IOException, ServletException {
        JSONObject jsonData = new JSONObject();
        ArrayList<String> errors = null;
        //request.getSession().getAttribute("ran1") == null || request.getSession().getAttribute("ran2") == null
        if (false) {
            //request.setAttribute("errormsg", "Invalid reqest."); 
            errors.add("Invalid reqest.");;
            jsonData.put("Status", "Error");
            jsonData.put("errofalsermsg", errors);
            jsonData.put("message", "");

        } else {

            Deedtype[] deedTypes = new DeedtypeDaoImpl().findAll();
            String[] deedList = new String[deedTypes.length];
            for (int i = 0; i < deedTypes.length; i++) {
                deedList[i] = Integer.toString(deedTypes[i].getCode());
            }

            Category[] catTypes = new CategoryDaoImpl().findAll();
            String[] catList = new String[catTypes.length];
            for (int i = 0; i < catTypes.length; i++) {
                catList[i] = catTypes[i].getSubDeedType();
            }

            SroOffices[] sroOffices = new SroOfficesDaoImpl().findAll();
            String[] offices = new String[sroOffices.length];
            for (int i = 0; i < sroOffices.length; i++) {
                offices[i] = Integer.toString(sroOffices[i].getId());
            }

            ArrayList dates = null;
            try {
                dates = new AppointmentHandler().getAppointmentDates("deed");
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] datesList = new String[dates.size()];
            for (int i = 0; i < dates.size(); i++) {
                datesList[i] = (String) dates.get(i);
            }

//            String ran1 = request.getSession().getAttribute("ran1").toString();
//            String ran2 = request.getSession().getAttribute("ran2").toString();
//            String res = Integer.toString(Integer.parseInt(ran1) + Integer.parseInt(ran2));
            setResponse(request);
            ValidationHandler validator = new ValidationHandler();
            validator.validate(request.getParameter("ApplicantName"), "Name of the applicant", "required:max_50:sql:xss");
            validator.validate(request.getParameter("email"), "e-mail", "email:max_30:sql:xss");
            validator.validate(request.getParameter("mobile_number"), "Mobile number", "required:mobile:sql:xss");
            validator.validate(request.getParameter("applicant_address"), "Address (House No/Street/Locality)", "required:max_100:sql:xss");
            validator.validate(request.getParameter("applicant_city_vill"), "City/Town/Village", "required:max_30:sql:xss");
            validator.validate(request.getParameter("applicant_post_office"), "Post Office", "required:max_30:sql:xss");
            validator.validate(request.getParameter("applicant_district"), "District", "required:max_30:sql:xss");
            validator.validate(request.getParameter("applicant_pin"), "PIN", "required:PIN:sql:xss");
            validator.validate(request.getParameter("applicant_type"), "Applicant Type", "valuein", new String[]{"0", "1"});
            validator.validate(request.getParameter("appointment_date"), "Select Desired Appointment date", "valuein", datesList);
            validator.validate(request.getParameter("sro_office"), "Select Office for Registration", "valuein", offices);
            validator.validate(request.getParameter("Deedtype"), "Deed Category", "valuein", deedList);
            validator.validate(request.getParameter("Subdeedtype"), "Deed Sub-category", "valuein", catList);
            //validator.validate(request.getParameter("captcha"), "Security Question", "valuein", new String[]{res});
            validator.validate(request.getParameter("ConsiderationAmt"), "Consideration Amount ", "numeric:max_9:low_0:high_2147483647");
            validator.validate(request.getParameter("Gender"), "Purchaser with", "valuein", new String[]{null, "MF", "F", "M"});
            validator.validate(request.getParameter("UrbanRural"), "Land/Flat is located in", "valuein", new String[]{null, "UG", "UM", "R"});

            errors = validator.getErrors();
            //new CaptchaHandler().setCaptcha(request);

            if (!errors.isEmpty()) {
                jsonData.put("success", false);
                jsonData.put("errormsg", errors);
            } 
            else {

                AppointmentHandler appointment_enq = new AppointmentHandler();
                AppointmentSlotBooking req = new AppointmentSlotBooking();
                String str = (String) request.getParameter("Gender");
                ArrayList<String> return_data = new ArrayList<String>();
                FeeCalculator fc = new FeeCalculator();
                int result = 0;
                int fee[] = new int[2];
                // if land related
                if (str != null) {
                    try {
                        result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                    } catch (AppointmentSlotBookingDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LandvalueDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ImplementSroDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AreadetailDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CategoryDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (Integer.parseInt(request.getParameter("applicant_type")) == 1) {
                        fee[0] = 0;
                        fee[1] = 0;
                    } else {
                        try {
                            fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), request.getParameter("Gender"), request.getParameter("UrbanRural"), Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), "");
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (fee[0] == -500 && fee[1] == -500) {
                        errors.add("Something went wrong. Check whether the deed is land related or not???");;

                        jsonData.put("success", false);

                        jsonData.put("msg", "Something went wrong. Check whether the deed is land related or not???");

                        //request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong. Check whether the deed is land related or not???</strong></div>");
                    } else {
                        if (fee[1] < 0) {
                            fee[1] = 0;
                        }
                        if (fee[0] < 0) {
                            fee[0] = 0;
                        }
                        req.setApplicantName(request.getParameter("ApplicantName"));
                        req.setEmail(request.getParameter("email"));
                        req.setMobileNumber(request.getParameter("mobile_number"));
                        req.setApplicantAddress(request.getParameter("applicant_address")
                                + "," + request.getParameter("applicant_city_vill")
                                + "," + request.getParameter("applicant_post_office")
                                + "," + request.getParameter("applicant_district")
                                + "," + request.getParameter("applicant_pin"));
                        req.setApplicantType(request.getParameter("applicant_type"));
                        req.setSroOffice(Integer.parseInt(request.getParameter("sro_office")));
                        req.setDeedType(Integer.parseInt(request.getParameter("Deedtype")));
                        req.setDeedSubtype(request.getParameter("Subdeedtype"));
                        req.setDocSubject(request.getParameter("DocSubject"));
                        req.setConsiderationAmount(Integer.parseInt(request.getParameter("ConsiderationAmt")));
                        if (request.getParameter("Gender") != null) {
                            req.setWhetherLand(Short.parseShort("1"));
                        } else {
                            req.setWhetherLand(Short.parseShort("0"));
                        }
                        req.setAreaType(request.getParameter("UrbanRural"));
                        req.setGender(request.getParameter("Gender"));
                        req.setRegistrationFee(fee[1]);
                        req.setStampDuty(fee[0]);
                        DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
                        DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            req.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart());
                            req.setAppointmentDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getParameter("appointment_date")))));
                            Date date = new Date();
                            req.setApplicationDateTime(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            return_data = appointment_enq.entryAppointment(req);
                            if (return_data == null) {
                                errors.add("Appointment is over for the selected date. Please try some other day");

                                jsonData.put("success", false);
                                jsonData.put("msg", "Appointment is over for the selected date. Please try some other day");

                                //request.setAttribute("appointment_failure", "<div class=\"alert alert-danger\">Appointment is over for the selected date. Please try some other day</div>");
                            } else {
                                ArrayList<String> appointmentInfo = appointment_enq.getAppointmentInfo(req.getSroOffice(), return_data.get(0));

                                jsonData.put("success", true);
                                jsonData.put("Status", "inserted");
                                //jsonData.put("message", "<div class=\"appointment_details\"><br><b>Appointment Details as follows </b><br> Officer assigned: " + return_data.get(1) + "<br>Consideration Amount:" + result + "<br> Appointment Date and time: " + appointmentInfo.get(0) + " <br>Appointment ID: <label id=\"Message\" value=\"" + return_data.get(0) + "\">" + return_data.get(0) + "</label><br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + "</div>");

                                errors.add("");
                                jsonData.put("Status", "Error");

                                ///
                                jsonData.put("appointment_id", return_data.get(0));
                                jsonData.put("Officer assigned", return_data.get(1));
                                jsonData.put("Consideration Amount", result);
                                jsonData.put("Appointment Date and time", appointmentInfo.get(0));
                                jsonData.put("Registration Fee", fee[1]);
                                jsonData.put("Stamp Duty", fee[0]);

                            }
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                //if not land related
                if (str == null) {
                    try {
                        result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), null, null, null, "");
                    } catch (AppointmentSlotBookingDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LandvalueDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ImplementSroDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AreadetailDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CategoryDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //if (result != 0) {
                    if (Integer.parseInt(request.getParameter("applicant_type")) == 1) {
                        fee[0] = 0;
                        fee[1] = 0;
                    } else {
                        try {
                            fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), "", "", Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, null, null, null, "");
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (fee[0] == -500 && fee[1] == -500) {
                        errors.add("Something went wrong. Check whether the deed is land related or not???");;
                        jsonData.put("Status", "Error");
                        jsonData.put("errormsg", errors);
                        jsonData.put("message", "");
                    } else {
                        if (fee[1] < 0) {
                            fee[1] = 0;
                        }
                        if (fee[0] < 0) {
                            fee[0] = 0;
                        }
                        req.setApplicantName(request.getParameter("ApplicantName"));
                        req.setEmail(request.getParameter("email"));
                        req.setMobileNumber(request.getParameter("mobile_number"));
                        req.setApplicantAddress(request.getParameter("applicant_address"));
                        req.setApplicantType(request.getParameter("applicant_type"));
                        req.setSroOffice(Integer.parseInt(request.getParameter("sro_office")));
                        req.setDeedType(Integer.parseInt(request.getParameter("Deedtype")));
                        req.setDeedSubtype(request.getParameter("Subdeedtype"));
                        req.setDocSubject(request.getParameter("DocSubject"));
                        req.setConsiderationAmount(result);
                        req.setRegistrationFee(fee[1]);
                        req.setStampDuty(fee[0]);
                        req.setWhetherLand(Short.parseShort("0"));
                        DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
                        DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            req.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart());
                            req.setAppointmentDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getParameter("appointment_date")))));
                            Date date = new Date();
                            req.setApplicationDateTime(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            return_data = appointment_enq.entryAppointment(req);
                            if (return_data == null) {
                                errors.add("Appointment is over for the selected date. Please try some other day");

                                jsonData.put("success", false);
                                jsonData.put("msg", "Appointment is over for the selected date. Please try some other day");
                            } else {
                                ArrayList<String> appointmentInfo = appointment_enq.getAppointmentInfo(req.getSroOffice(), return_data.get(0));
                                errors.add("");

                                jsonData.put("success", true);
                                jsonData.put("Status", "inserted");
                                //sonData.put("message", "<div class=\"appointment_details\"><br><b>Appointment Details as follows </b><br> Officer assigned: " + return_data.get(1) + "<br>Consideration Amount:" + result + "<br> Appointment Date and time: " + appointmentInfo.get(0) + " <br>Appointment ID: <label id=\"Message\" value=\"" + return_data.get(0) + "\">" + return_data.get(0) + "</label><br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + "</div>");

                                jsonData.put("appointment_id", return_data.get(0));
                                jsonData.put("Officer assigned", return_data.get(1));
                                jsonData.put("Consideration Amount", result);
                                jsonData.put("Appointment Date and time", appointmentInfo.get(0));
                                jsonData.put("Registration Fee", fee[1]);
                                jsonData.put("Stamp Duty", fee[0]);

                            }
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                request.getSession().setAttribute("sro_office", Integer.parseInt(request.getParameter("sro_office")));
                try {
                    request.getSession().setAttribute("appointment_date", new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("appointment_date")));
                } catch (ParseException ex) {
                    Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (return_data != null) {
//                        try {
//                            //Get all the parts from request and write it to the file on server
//                            InputStream inputStream = null;
//                            AppointmentDocumentsDaoImpl appointmentDao = new AppointmentDocumentsDaoImpl();
//                            for (Part part : request.getParts()) {
//                                if (part.getName().equals("files")) {
//                                    AppointmentDocuments appointmentDocs = new AppointmentDocuments();
//                                    inputStream = part.getInputStream();
//                                    appointmentDocs.setAppointmentId(return_data.get(0));
//                                    appointmentDocs.setDocument(appointment_enq.toByteArray(inputStream));
//                                    appointmentDao.insert(appointmentDocs);
//                                }
//                            }
//                        } catch (AppointmentDocumentsDaoException ex) {
//                        }

                }

            }
        }

        return jsonData.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/getGrn")) {
            DbusersDaoImpl dbusersDaoImpl = new DbusersDaoImpl();
            //Dbusers[] dbusers=null;
            try {
                Dbusers[] dbusers = dbusersDaoImpl.findByDynamicSelect("SELECT * FROM `dbusers` where username<>''", null);
                request.setAttribute("dbusers", dbusers);
                ;
                System.out.println(dbusers[0].getUsername());
                System.out.println(dbusers[0].getDistrictCode());
                System.out.println(dbusers[0].getDbname());
                System.out.println(dbusers[0].getOfficename());
                Calendar cal = Calendar.getInstance();
                int CurrentYear = cal.get(Calendar.YEAR);
                int Previuos = CurrentYear - 1;
                String yearRange = Previuos + "-" + CurrentYear;
                request.setAttribute("yearRange", yearRange);
            } catch (DbusersDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("WEB-INF/view/appointment/getGrn.jsp").forward(request, response);
            return;
        }
        if (userPath.equals("/getChallan")) {
            request.getRequestDispatcher("WEB-INF/view/appointment/viewChallan.jsp").forward(request, response);
            return;
        }
        if (userPath.equals("/getCin")) {
            DbusersDaoImpl dbusersDaoImpl = new DbusersDaoImpl();
            //Dbusers[] dbusers=null;
            try {
                Dbusers[] dbusers = dbusersDaoImpl.findByDynamicSelect("SELECT * FROM `dbusers` where username<>''", null);
                request.setAttribute("dbusers", dbusers);
                ;
                System.out.println(dbusers[0].getUsername());
                System.out.println(dbusers[0].getDistrictCode());
                System.out.println(dbusers[0].getDbname());
                System.out.println(dbusers[0].getOfficename());
                Calendar cal = Calendar.getInstance();
                int CurrentYear = cal.get(Calendar.YEAR);
                int Previuos = CurrentYear - 1;
                String yearRange = Previuos + "-" + CurrentYear;
                request.setAttribute("yearRange", yearRange);
            } catch (DbusersDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("WEB-INF/view/appointment/getCin.jsp").forward(request, response);
            return;
        }
        if (userPath.equals("/verify_challan")) {
            try {
                String urlParameters = "GRN=AS000001997201819E&AMOUNT=1000&OFFICECODE=LRS000&USERID=nicadmin&VIEWCHALLAN=Y";
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength = postData.length;
                //http://103.8.248.139/challan/models/frmgrnverificationoutsidebe.php
                String requesturl = "http://103.8.248.139/challan/models/frmgrnverificationoutsidebe.php";
                URL url = new URL(requesturl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                conn.setUseCaches(false);
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(postData);
                }
                FileOutputStream fos1 = new FileOutputStream("D:\\download.pdf");
                byte[] ba1 = new byte[1024];
                int baLength;
                String i = "";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream is1 = conn.getInputStream();
                while ((baLength = is1.read(ba1)) != -1) {
                    fos1.write(ba1, 0, baLength);
                    baos.write(ba1, 0, baLength);
//                    String s = new String(ba1);  
                }
                byte[] bytes = baos.toByteArray();
                fos1.write(bytes);
                fos1.flush();
                fos1.close();
                baos.flush();
                baos.close();
                is1.close();

                CommonAttributes commonUtil = new CommonAttributes();
                String pdfUrl = commonUtil.getPdfDataUri(bytes);
                request.setAttribute("pdfUrl", pdfUrl);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                if (false) {
//                    String Str=Base64.getEncoder().encodeToString(i.getBytes());
//                    response.getWriter().write(Str);
                } else {
                    //response.getWriter().write(pdfUrl);
                }
                userPath = "/verify_challan";
                request.getRequestDispatcher("WEB-INF/view/appointment/verify_challan.jsp").forward(request, response);
                return;
            } catch (java.net.ConnectException e) {
                System.out.println(e);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(e.toString());

                return;
            } catch (java.io.IOException e) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(e.toString());
                return;
            }

        }

        if (userPath.equals("/egras_payment")) {
            DbusersDaoImpl dbusersDaoImpl = new DbusersDaoImpl();
            //Dbusers[] dbusers=null;
            try {
                Dbusers[] dbusers = dbusersDaoImpl.findByDynamicSelect("SELECT * FROM `dbusers` where username<>''", null);
                request.setAttribute("dbusers", dbusers);
                ;
                System.out.println(dbusers[0].getUsername());
                System.out.println(dbusers[0].getDistrictCode());
                System.out.println(dbusers[0].getDbname());
                System.out.println(dbusers[0].getOfficename());
                Calendar cal = Calendar.getInstance();
                int CurrentYear = cal.get(Calendar.YEAR);
                int Previuos = CurrentYear - 1;
                String yearRange = Previuos + "-" + CurrentYear;
                request.setAttribute("yearRange", yearRange);
            } catch (DbusersDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

            userPath = "/appointment" + userPath;

        }
        if (userPath.equals("/egras_payment_response")) {
            CommonAttributes common = new CommonAttributes();
            Timestamp time = new Timestamp(common.setParseDate(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date())).getTime());
            String inDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Timestamp ts = null;
            try {
                ts = new Timestamp(((java.util.Date) df.parse(inDate)).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("DEPARTMENT_ID : Ele1976,GRN : AS000002697201819E,AMOUNT:  10.00,BANKCIN:02003942019020632376,time: " + ts);
            return;
        }
        if (userPath.equals("/getsrooffice")) {
            try {
                String getSroOffice = getSroOffice();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(getSroOffice);
            } catch (SroOfficesDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } else if (userPath.equals("/get_deeds")) {
            String deedTypes = null;
            try {
                deedTypes = getDeedTypes();
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(deedTypes);
            return;
        } else if (userPath.equals("/create_appointment")) {
            String result = "{\"Status\":\"Error\",\"errormsg\":[\"Value specified for field <strong>Applicant Type<\\/strong> is not valid.\"]}";
            JSONObject jsonResult = null;
            System.out.println("Name of appilcant :::" + request.getParameter("ApplicantName"));
            try {
                jsonResult = new JSONObject(result);
                JSONArray errormsg = jsonResult.getJSONArray("errormsg");
                System.out.println("Length:::" + errormsg.length());
                String[] arr = new String[errormsg.length()];
                for (int i = 0; i < errormsg.length(); i++) {
                    //errors.add(errormsg.getString(i));
                    arr[i] = errormsg.optString(i);
                    System.out.println("Error Message::::: " + errormsg.getString(i));
                }
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            return;
        } else if (userPath.equals("/appointment")) {
            userPath = "/appointment" + userPath;
            try {
                ArrayList dates = new AppointmentHandler().getAppointmentDates("deed");

                System.out.println("##########################################");
                System.out.println(dates.toString());

                if (dates.isEmpty()) {
                    request.setAttribute("errormsg", "No Appointments available for next 14 days. Please try later.");
                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/error/error.jsp");
                    view.forward(request, response);
                    return;
                }

                new CaptchaHandler().setCaptcha(request);
                DeedtypeDaoImpl dao = new DeedtypeDaoImpl();
                CategoryDaoImpl subdao = new CategoryDaoImpl();
                SroOfficesDaoImpl sro_office_dao = new SroOfficesDaoImpl();
                ApplicantTypeDaoImpl applicantDao = new ApplicantTypeDaoImpl();
                String deedTypes = getDeedTypes();
                JSONObject jsonResult = null;
                //System.out.println("Name of appilcant :::"+request.getParameter("ApplicantName"));
                try {
                    jsonResult = new JSONObject(deedTypes);
                    JSONArray deedlist = jsonResult.getJSONArray("deedlist");
                    ArrayList deedListMain = new ArrayList();
                    for (int i = 0; i < deedlist.length(); i++) {
                        ArrayList deedList = new ArrayList();
                        //errors.add(errormsg.getString(i));
                        jsonResult = new JSONObject(deedlist.optString(i));
                        deedList.add(0, jsonResult.get("Code"));
                        deedList.add(1, jsonResult.get("Section"));
                        //System.out.println("Code::: "+jsonResult.get("Code")+" Section::: "+jsonResult.get("Section"));
                        deedListMain.add(deedList);
                    }
                    request.setAttribute("deedList", deedListMain);
                } catch (JSONException ex) {
                    Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("dates", dates);

                request.setAttribute("Subdeedtypes", subdao.findAll());
                request.setAttribute("SroOffices", sro_office_dao.findAll());
                request.setAttribute("applicantTypes", applicantDao.findAll());

//                String url = "/WEB-INF/view" + userPath + ".jsp";
//                request.getRequestDispatcher(url).forward(request, response);
            } catch (SroOfficesDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ApplicantTypeDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CategoryDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HolidayDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OffDaysDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (userPath.equals("/appointment_status")) {
            //new CaptchaHandler().setCaptcha(request);
            request.getSession().setAttribute("appointment_fee_structure", null);
            userPath = "/appointment" + userPath;
        } else if (userPath.equals("/appointment_details")) {
            userPath = "/appointment" + userPath;
        } else if (userPath.equals("/upcoming_appointments")) {
            int page = 1;
            int recordsPerPage = 10;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            UpcomingAppointmentsHandler upcmgappointment = new UpcomingAppointmentsHandler();
            AppointmentDetails slotbooking[] = null;
            try {
                slotbooking = upcmgappointment.pagination((page - 1) * recordsPerPage, recordsPerPage, null);
                if (slotbooking.length == 0) {
                    request.setAttribute("firstMessage", "<div class=\'alert alert-danger\'><b>No Upcoming Appointment</b></div>");
                } else {
                    request.setAttribute("appointmentslotbooking", slotbooking);
                }
                if (updateFlag == 0) {
                    request.getSession().setAttribute("StatusMsg", "");
                }
                if (updateFlag == 1) {
                    updateFlag = 0;
                    request.getSession().setAttribute("StatusMsg", "Appointment successfully Approved");
                }
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AppointmentDetailsDaoException ex) {
                Logger.getLogger(AppointmentDetailsDaoException.class.getName()).log(Level.SEVERE, null, ex);
            }

            int noOfRecords;
            try {
                noOfRecords = upcmgappointment.getNoOfRecords(null);
                if (noOfRecords > 1) {
                    request.setAttribute("currentPage", page);
                }
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "/WEB-INF/view/appointment" + userPath + ".jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } else if (userPath.equals("/appointment_approve")) {
            userPath = "/appointment" + userPath;
        } else if (userPath.equals("/appointment_docs")) {
            userPath = "/appointment" + userPath;
        }

        System.out.println("3::::::::::::::::::::" + userPath);
        String url = "/WEB-INF/view" + userPath + ".jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/getGrn")) {
            try {
                String urlParameters = "DEPARTMENT_ID=" + request.getParameter("DEPARTMENT_ID") + "&AMOUNT=" + request.getParameter("AMOUNT") + "&OFFICE_CODE=" + request.getParameter("OFFICE_CODE");
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength = postData.length;
                //http://103.8.248.139/challan/models/frmgrnverificationoutsidebe.php
                String requesturl = "http://103.8.248.139/challan/models/frmgetgrn.php";
                URL url = new URL(requesturl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                conn.setUseCaches(false);
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(postData);
                }
                int responseCode = conn.getResponseCode();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                //OutputStream responseOutputStream = response.getOutputStream();
                String inputLine;
                String i = "";
                while ((inputLine = in.readLine()) != null) {
                    i = i + inputLine;

                }
                in.close();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");

                if ("Y".equals(request.getParameter("VIEWCHALLAN"))) {
                    String Str = Base64.getEncoder().encodeToString(i.getBytes());
                    response.getWriter().write(Str);
                } else {
                    response.getWriter().write(i);
                }

                return;
            } catch (java.net.ConnectException e) {
                System.out.println(e);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(e.toString());

                return;
            } catch (java.io.IOException e) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(e.toString());
                return;
            }

        }
        if (userPath.equals("/verify_challan")) {
            try {
                String urlParameters = "GRN=" + request.getParameter("GRN") + "&AMOUNT=" + request.getParameter("amount") + "&OFFICECODE=" + request.getParameter("office_code") + "&USERID=nicadmin&VIEWCHALLAN=Y";
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength = postData.length;
                //http://103.8.248.139/challan/models/frmgrnverificationoutsidebe.php
                String requesturl = "http://103.8.248.139/challan/models/frmgrnverificationoutsidebe.php";
                URL url = new URL(requesturl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                conn.setUseCaches(false);
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(postData);
                }
                // FileOutputStream fos1 = new FileOutputStream("D:\\download.pdf");
                byte[] ba1 = new byte[1024];
                int baLength;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream is1 = conn.getInputStream();
                while ((baLength = is1.read(ba1)) != -1) {
                    // fos1.write(ba1, 0, baLength);
                    baos.write(ba1, 0, baLength);
                    //                    String s = new String(ba1);  
                }
                byte[] bytes = baos.toByteArray();
//                fos1.write(bytes);
//                fos1.flush();
//                fos1.close();
                baos.flush();
                baos.close();
                is1.close();

                CommonAttributes commonUtil = new CommonAttributes();
                String pdfUrl = commonUtil.getPdfDataUri(bytes);
                request.setAttribute("pdfUrl", pdfUrl);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(pdfUrl);
                return;
            } catch (java.net.ConnectException e) {
                System.out.println(e);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(e.toString());

                return;
            } catch (java.io.IOException e) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(e.toString());
                return;
            }

        }
        if (userPath.equals("/getChallanData")) {
            if ("getCin".equals(request.getParameter("Request_from"))) {
                JSONObject jsonData = new JSONObject();
                EgrasResponseDaoImpl egrasResponseDaoImpl = new EgrasResponseDaoImpl();
                EgrasResponse[] egrasResponse = null;
                try {
                    egrasResponse = egrasResponseDaoImpl.findByDynamicSelect("select * from egras_response where mobileNo=? AND cin =''", new Object[]{request.getParameter("Mobile")});
                } catch (EgrasResponseDaoException ex) {
                    Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
                Integer serial = 0;
                System.out.println(egrasResponse.length);
                if (egrasResponse.length > 0) {
                    for (EgrasResponse i : egrasResponse) {
                        try {
                            jsonData.put("DEPARTMENT_ID", i.getDepartmentId());
                            jsonData.put("AMOUNT", i.getAmount());
                            jsonData.put("OFFICE_CODE", i.getOfficeCode());
                        } catch (JSONException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonData.toString());
            } else if ("getChallan".equals(request.getParameter("Request_from"))) {
                EgrasResponseDaoImpl egrasResponseDaoImpl = new EgrasResponseDaoImpl();
                EgrasResponse[] egrasResponse = null;
                try {
                    egrasResponse = egrasResponseDaoImpl.findByDynamicSelect("select * from egras_response where mobileNo=? AND cin <>0", new Object[]{request.getParameter("Mobile")});
                } catch (EgrasResponseDaoException ex) {
                    Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
                Integer serial = 1;
                System.out.println(egrasResponse.length);
                if (egrasResponse.length > 0) {

                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");

                    for (EgrasResponse i : egrasResponse) {
                        response.getWriter().write("<tr>");
                        response.getWriter().write("<td ><span data-group='" + serial + "' data-id='grn'>" + i.getGrnNo() + "</span></td>");
                        response.getWriter().write("<td><span data-group='" + serial + "' data-id='amount'>" + i.getAmount() + "</span></td>");
                        response.getWriter().write("<td><span data-group='" + serial + "' data-id='mobile'>" + i.getMobileNo() + "</span><span data-group='" + serial + "' data-id='office_code' style='display:none;'>" + i.getOfficeCode() + "</span></td>");
                        try {
                            JSONObject jsonData = new JSONObject(i.getResponseParameters());
                            response.getWriter().write("<td><span data-group='" + serial + "' data-id='Entry_date'>" + jsonData.getString("ENTRY_DATE") + "</span></td>");
                        } catch (JSONException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        response.getWriter().write("<td><a class='btn btn-primary' href='#' data-id='view' data-group='" + serial + "'>View</a></td>");
                        response.getWriter().write("</tr>");
                        serial = serial + 1;
                    }
                }

            }

            return;
        }
        if (userPath.equals("/getCin")) {
            Enumeration<String> parameterNames = request.getParameterNames();
            JSONObject jsonData = new JSONObject();
            while (parameterNames.hasMoreElements()) {

                String paramName = parameterNames.nextElement();
                System.out.println("paramName :" + paramName);

                String[] paramValues = request.getParameterValues(paramName);
                for (int i = 0; i < paramValues.length; i++) {
                    String paramValue = paramValues[i];
                    try {
                        jsonData.put(paramName, paramValue);
                        request.setAttribute(paramName, paramValue);
                    } catch (JSONException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("paramValue :" + paramValue);
                }

            }
            EgrasResponseLog egrasResponseLog = new EgrasResponseLog();
            EgrasResponseLogDaoImpl egrasResponseLogDaoImpl = new EgrasResponseLogDaoImpl();
            egrasResponseLog.setDepartmentId(request.getParameter("DEPARTMENT_ID"));
            egrasResponseLog.setRequestParameters(jsonData.toString());
            egrasResponseLog.setResponseParameters("");
            new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            String inDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Timestamp ts = null;
            try {
                ts = new Timestamp(((java.util.Date) df.parse(inDate)).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            egrasResponseLog.setDatetime(ts);
            try {
                egrasResponseLogDaoImpl.insert(egrasResponseLog);
            } catch (EgrasResponseLogDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("WEB-INF/view/appointment/get_Cin.jsp").forward(request, response);
            return;
        }
        if (userPath.equals("/egrasPayment")) {
            DbusersDaoImpl dbusersDaoImpl = new DbusersDaoImpl();
            EgrasResponseDaoImpl egrasResponseDaoImpl = new EgrasResponseDaoImpl();
            String DEPARTMENT_ID = null;
            Calendar cal = Calendar.getInstance();
            int CurrentYear = cal.get(Calendar.YEAR);
            //Dbusers[] dbusers=null;
            try {

                Dbusers[] dbusers = dbusersDaoImpl.findByDynamicSelect("SELECT * FROM `dbusers` where username=?", new Object[]{request.getParameter("OFFICE_CODE")});
                if (dbusers != null) {

                    String temp = "";
                    for (Dbusers i : dbusers) {
                        temp = i.getSroCode() + "/" + i.getPassword();
                        DEPARTMENT_ID = i.getSroCode() + "/" + i.getPassword() + "/" + CurrentYear;

                    }
                    EgrasResponse[] egrasResponse = null;

                    egrasResponse = egrasResponseDaoImpl.findByDynamicSelect("select * from egras_response where id=(SELECT max(id) as id FROM `egras_response` where departmentId like '" + temp + "%' and year =?) ", new Object[]{CurrentYear});
                    Integer serial = 0;
                    System.out.println(egrasResponse.length);
                    if (egrasResponse.length > 0) {
                        System.out.println("adsadasd");
                        for (EgrasResponse i : egrasResponse) {
                            System.out.println(i.getDepartmentId());
                            String[] upcomingserialArray = i.getDepartmentId().split("/");
                            serial = parseInt(upcomingserialArray[2]);
                            serial = serial + 1;
                            System.out.println("Serial :" + serial);

                        }
                    } else {
                        serial = 00001;
                    }
                    DEPARTMENT_ID = temp + "/" + serial + "/" + CurrentYear;
                    System.out.println("Upcoming DEPARTMENT_ID: " + DEPARTMENT_ID);
                }
            } catch (DbusersDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EgrasResponseDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Enumeration<String> parameterNames = request.getParameterNames();
            JSONObject jsonData = new JSONObject();
            while (parameterNames.hasMoreElements()) {

                String paramName = parameterNames.nextElement();
                System.out.println("paramName :" + paramName);

                String[] paramValues = request.getParameterValues(paramName);
                for (int i = 0; i < paramValues.length; i++) {
                    String paramValue = paramValues[i];
                    try {
                        jsonData.put(paramName, paramValue);
                        request.setAttribute(paramName, paramValue);
                    } catch (JSONException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("paramValue :" + paramValue);
                }

            }
            request.setAttribute("DEPARTMENT_ID", DEPARTMENT_ID);
            EgrasResponse egrasResponse = new EgrasResponse();
            egrasResponse.setAmount(Math.round(Float.valueOf(request.getParameter("CHALLAN_AMOUNT"))));
            egrasResponse.setDepartmentId(DEPARTMENT_ID);
            egrasResponse.setOfficeCode(request.getParameter("OFFICE_CODE"));
            egrasResponse.setRequestParameters(jsonData.toString());
            egrasResponse.setMobileNo(parseInt(request.getParameter("MOBILE_NO")));
            egrasResponse.setCin("0");
            egrasResponse.setGrnNo("0");
            egrasResponse.setResponseParameters("0");
            egrasResponse.setYear(CurrentYear);
            try {
                egrasResponseDaoImpl.insert(egrasResponse);
            } catch (EgrasResponseDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            EgrasResponseLog egrasResponseLog = new EgrasResponseLog();
            EgrasResponseLogDaoImpl egrasResponseLogDaoImpl = new EgrasResponseLogDaoImpl();
            egrasResponseLog.setDepartmentId(DEPARTMENT_ID);
            egrasResponseLog.setRequestParameters(jsonData.toString());
            egrasResponseLog.setResponseParameters("");
            new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            CommonAttributes common = new CommonAttributes();
            String inDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Timestamp ts = null;
            try {
                ts = new Timestamp(((java.util.Date) df.parse(inDate)).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            egrasResponseLog.setDatetime(ts);
            try {
                egrasResponseLogDaoImpl.insert(egrasResponseLog);
            } catch (EgrasResponseLogDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

//            response.setContentType("text/plain");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write("DEPARTMENT_ID : "+request.getParameter("DEPARTMENT_ID").trim()+",GRN : "+request.getParameter("GRN")+",AMOUNT :  "+request.getParameter("AMOUNT")+",BANKCIN : " +request.getParameter("BANKCIN")+",PRN : "+request.getParameter("PRN")+",JsonRequestString"+jsonData.toString());
            //return;
            //userPath = "/appointment" + userPath;
            request.getRequestDispatcher("WEB-INF/view/appointment/" + userPath + ".jsp").forward(request, response);
            return;
        }
        if (userPath.equals("/egras_payment_response")) {
            EgrasResponseDaoImpl egrasResponseDaoImpl = new EgrasResponseDaoImpl();
            Enumeration<String> parameterNames = request.getParameterNames();
            JSONObject jsonData = new JSONObject();
            while (parameterNames.hasMoreElements()) {

                String paramName = parameterNames.nextElement();
                System.out.println("paramName :" + paramName.trim());

                String[] paramValues = request.getParameterValues(paramName);
                for (int i = 0; i < paramValues.length; i++) {
                    String paramValue = paramValues[i];
                    try {
                        jsonData.put(paramName, paramValue.trim());
                        request.setAttribute(paramName, paramValue);
                    } catch (JSONException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("paramValue :" + paramValue);
                }

            }
            EgrasResponse[] egrasResponse = null;
            EgrasResponseLog[] EgrasResponseLogData = null;

            try {
                egrasResponse = egrasResponseDaoImpl.findByDynamicSelect("select * from egras_response where departmentId=? ", new Object[]{request.getParameter("DEPARTMENT_ID").trim()});
                if (egrasResponse.length > 0) {
                    String OFFICE_CODE = null;
                    System.out.println("adsadasd");
                    EgrasResponsePk epk = new EgrasResponsePk();
                    for (EgrasResponse i : egrasResponse) {
                        System.out.println(i.getDepartmentId());
                        i.setGrnNo(request.getParameter("GRN"));
                        i.setResponseParameters(jsonData.toString());
                        i.setCin(request.getParameter("BANKCIN"));
                        OFFICE_CODE = i.getOfficeCode();
                        epk.setId(i.getId());
                        egrasResponseDaoImpl.update(epk, i);

                    }
                    EgrasResponseLog egrasResponseLog = new EgrasResponseLog();
                    EgrasResponseLogDaoImpl egrasResponseLogDaoImpl = new EgrasResponseLogDaoImpl();
                    EgrasResponseLogData = egrasResponseLogDaoImpl.findByDynamicSelect("select * from egras_response_log where id=(select max(id) as id from egras_response_log where department_id=?)", new Object[]{request.getParameter("DEPARTMENT_ID").trim()});
                    EgrasResponseLogPk epkLog = new EgrasResponseLogPk();
                    for (EgrasResponseLog i : EgrasResponseLogData) {
                        System.out.println(i.getDepartmentId() + ",response :" + i.getRequestParameters());
                        i.setResponseParameters(jsonData.toString());
                        epkLog.setId(i.getId());
                        egrasResponseLogDaoImpl.update(epkLog, i);
                    }
                    //request.getParameter("BANKCIN")!="" || request.getParameter("BANKCIN")!=null
                    if (request.getParameter("BANKCIN") != "") {
                        String urlParameters = "GRN=" + request.getParameter("GRN") + "&AMOUNT=" + request.getParameter("AMOUNT") + "&OFFICECODE=" + OFFICE_CODE + "&USERID=nicadmin&VIEWCHALLAN=Y";
                        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                        int postDataLength = postData.length;
                        String requesturl = "http://103.8.248.139/challan/models/frmgrnverificationoutsidebe.php";
                        URL url = new URL(requesturl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoOutput(true);
                        conn.setInstanceFollowRedirects(false);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestProperty("charset", "utf-8");
                        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                        conn.setUseCaches(false);
                        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                            wr.write(postData);
                        }
                        //FileOutputStream fos1 = new FileOutputStream("D:\\download.pdf");
                        byte[] ba1 = new byte[1024];
                        int baLength;
                        String i = "";
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        InputStream is1 = conn.getInputStream();
                        while ((baLength = is1.read(ba1)) != -1) {
                            //fos1.write(ba1, 0, baLength);
                            baos.write(ba1, 0, baLength);
                            String s = new String(ba1);
                        }
                        byte[] bytes = baos.toByteArray();
//                            fos1.write(bytes);
//                            fos1.flush();
//                            fos1.close();
                        baos.flush();
                        baos.close();
                        is1.close();

                        CommonAttributes commonUtil = new CommonAttributes();
                        String pdfUrl = commonUtil.getPdfDataUri(bytes);
                        request.setAttribute("pdfUrl", pdfUrl);
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        request.getRequestDispatcher("WEB-INF/view/appointment/verify_challan.jsp").forward(request, response);
                        return;

                    } else {
                        request.setAttribute("OFFICE_CODE", OFFICE_CODE);
                        request.setAttribute("DEPARTMENT_ID", request.getParameter("DEPARTMENT_ID").trim());
                        request.setAttribute("AMOUNT", request.getParameter("AMOUNT"));
                        request.getRequestDispatcher("WEB-INF/view/appointment/getCin.jsp").forward(request, response);
                    }

                }
            } catch (EgrasResponseDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EgrasResponseLogDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //response.getWriter().write("DEPARTMENT_ID : "+request.getParameter("DEPARTMENT_ID").trim()+",GRN : "+request.getParameter("GRN")+",AMOUNT :  "+request.getParameter("AMOUNT")+",BANKCIN : " +request.getParameter("BANKCIN")+",PRN : "+request.getParameter("PRN")+",JsonString :"+jsonData.toString());
        }
        if (userPath.equals("/file_upload_curl")) {

        }

        if (userPath.equals("/create_appointment")) {

            System.out.println("##########################################################################");
            for (String key : request.getParameterMap().keySet()) {
                System.out.println(key + " : " + request.getParameter(key));
            }

            String result = "";
            try {
                System.out.println("Name of appilcant :::" + request.getParameter("email"));
                result = create_appointment(request);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(result);
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DeedtypeDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CategoryDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SroOfficesDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HolidayDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OffDaysDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
//            JSONObject jsonResult = null;
            // System.out.println("Result:::::::: "+result);
            /*try {
                jsonResult = new JSONObject(result);
                //System.out.println("message "+jsonResult.get("message"));
                if("Error".equals(jsonResult.get("Status").toString())){
                    JSONArray errormsg = jsonResult.getJSONArray("errormsg");
                    String[] errors=new String[errormsg.length()];
                     for (int i = 0; i < errormsg.length(); i++) {
                         //errors.add(errormsg.getString(i));
                         errors[i]=errormsg.optString(i);
                         System.out.println("Error Message::::: "+errormsg.getString(i));
                     }
//                    request.getSession().setAttribute("errormsg", errors);
//                    response.sendRedirect("/panjeeyanonline/appointment");
                    return;
                }else{
                    request.getSession().setAttribute("appointment_fee_structure",jsonResult.get("message").toString());
                    request.getSession().setAttribute("appointment_id", jsonResult.get("appointment_id").toString());
                    //response.sendRedirect("appointment_details");
//                    response.setContentType("text/plain");
//                    response.setCharacterEncoding("UTF-8");
//                    response.getWriter().write(jsonResult.get("message").toString());
                }
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }*/

            return;
        } else if (userPath.equals("/appointment")) {
            try {
                if (request.getSession().getAttribute("ran1") == null || request.getSession().getAttribute("ran2") == null) {
                    request.setAttribute("errormsg", "Invalid reqest.");
                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/error/error.jsp");
                    view.forward(request, response);
                    return;
                }

                Deedtype[] deedTypes = new DeedtypeDaoImpl().findAll();
                String[] deedList = new String[deedTypes.length];
                for (int i = 0; i < deedTypes.length; i++) {
                    deedList[i] = Integer.toString(deedTypes[i].getCode());
                }

                Category[] catTypes = new CategoryDaoImpl().findAll();
                String[] catList = new String[catTypes.length];
                for (int i = 0; i < catTypes.length; i++) {
                    catList[i] = catTypes[i].getSubDeedType();
                }

                SroOffices[] sroOffices = new SroOfficesDaoImpl().findAll();
                String[] offices = new String[sroOffices.length];
                for (int i = 0; i < sroOffices.length; i++) {
                    offices[i] = Integer.toString(sroOffices[i].getId());
                }

                ArrayList dates = new AppointmentHandler().getAppointmentDates("deed");
                String[] datesList = new String[dates.size()];
                for (int i = 0; i < dates.size(); i++) {
                    datesList[i] = (String) dates.get(i);
                }

                String ran1 = request.getSession().getAttribute("ran1").toString();
                String ran2 = request.getSession().getAttribute("ran2").toString();
                String res = Integer.toString(Integer.parseInt(ran1) + Integer.parseInt(ran2));

                setResponse(request);
                ValidationHandler validator = new ValidationHandler();

                validator.validate(request.getParameter("ApplicantName"), "Name of the applicant", "required:max_50:sql:xss");
                validator.validate(request.getParameter("email"), "e-mail", "email:max_30:sql:xss");
                validator.validate(request.getParameter("mobile_number"), "Mobile number", "required:mobile:sql:xss");
                validator.validate(request.getParameter("applicant_address"), "Address (House No/Street/Locality)", "required:max_100:sql:xss");
                validator.validate(request.getParameter("applicant_city_vill"), "City/Town/Village", "required:max_30:sql:xss");
                validator.validate(request.getParameter("applicant_post_office"), "Post Office", "required:max_30:sql:xss");
                validator.validate(request.getParameter("applicant_district"), "District", "required:max_30:sql:xss");
                validator.validate(request.getParameter("applicant_pin"), "PIN", "required:PIN:sql:xss");
                validator.validate(request.getParameter("applicant_type"), "Applicant Type", "valuein", new String[]{"0", "1"});
                validator.validate(request.getParameter("appointment_date"), "Select Desired Appointment date", "valuein", datesList);
                validator.validate(request.getParameter("sro_office"), "Select Office for Registration", "valuein", offices);
                validator.validate(request.getParameter("Deedtype"), "Deed Category", "valuein", deedList);
                validator.validate(request.getParameter("Subdeedtype"), "Deed Sub-category", "valuein", catList);
                validator.validate(request.getParameter("captcha"), "Security Question", "valuein", new String[]{res});
                validator.validate(request.getParameter("ConsiderationAmt"), "Consideration Amount ", "numeric:max_9:low_0:high_2147483647");
                validator.validate(request.getParameter("Gender"), "Purchaser with", "valuein", new String[]{null, "MF", "F", "M"});
                validator.validate(request.getParameter("UrbanRural"), "Land/Flat is located in", "valuein", new String[]{null, "UG", "UM", "R"});

                ArrayList<String> errors = validator.getErrors();
                new CaptchaHandler().setCaptcha(request);

                if (!errors.isEmpty()) {
                    try {

                        request.setAttribute("errormsg", errors);
                        request.setAttribute("Deedtypes", deedTypes);
                        request.setAttribute("Subdeedtypes", catTypes);
                        request.setAttribute("dates", dates);
                        request.setAttribute("SroOffices", sroOffices);
                        request.setAttribute("applicantTypes", new ApplicantTypeDaoImpl().findAll());

                        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/appointment/appointment.jsp");
                        view.forward(request, response);
                    } catch (ApplicantTypeDaoException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // Check for holidays

                    AppointmentHandler appointment_enq = new AppointmentHandler();
                    AppointmentSlotBooking req = new AppointmentSlotBooking();
                    String str = (String) request.getParameter("Gender");
                    ArrayList<String> return_data = new ArrayList<String>();

                    FeeCalculator fc = new FeeCalculator();
                    int result = 0;
                    int fee[] = new int[2];
                    // if land related
                    if (str != null) {
                        try {
                            result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (Integer.parseInt(request.getParameter("applicant_type")) == 1) {
                            fee[0] = 0;
                            fee[1] = 0;
                        } else {
                            try {
                                fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), request.getParameter("Gender"), request.getParameter("UrbanRural"), Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), "");
                            } catch (AppointmentSlotBookingDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (LandFeeDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (CategoryDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (LandvalueDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ImplementSroDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (AreadetailDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (RegfeeDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (RenquiryDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (fee[0] == -500 && fee[1] == -500) {
                            request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong. Check whether the deed is land related or not???</strong></div>");
                        } else {
                            if (fee[1] < 0) {
                                fee[1] = 0;
                            }
                            if (fee[0] < 0) {
                                fee[0] = 0;
                            }
                            req.setApplicantName(request.getParameter("ApplicantName"));
                            req.setEmail(request.getParameter("email"));
                            req.setMobileNumber(request.getParameter("mobile_number"));
                            req.setApplicantAddress(request.getParameter("applicant_address")
                                    + "," + request.getParameter("applicant_city_vill")
                                    + "," + request.getParameter("applicant_post_office")
                                    + "," + request.getParameter("applicant_district")
                                    + "," + request.getParameter("applicant_pin"));
                            req.setApplicantType(request.getParameter("applicant_type"));
                            req.setSroOffice(Integer.parseInt(request.getParameter("sro_office")));
                            req.setDeedType(Integer.parseInt(request.getParameter("Deedtype")));
                            req.setDeedSubtype(request.getParameter("Subdeedtype"));
                            req.setDocSubject(request.getParameter("DocSubject"));
                            req.setConsiderationAmount(Integer.parseInt(request.getParameter("ConsiderationAmt")));
                            if (request.getParameter("Gender") != null) {
                                req.setWhetherLand(Short.parseShort("1"));
                            } else {
                                req.setWhetherLand(Short.parseShort("0"));
                            }
                            req.setAreaType(request.getParameter("UrbanRural"));
                            req.setGender(request.getParameter("Gender"));
                            req.setRegistrationFee(fee[1]);
                            req.setStampDuty(fee[0]);
                            DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
                            DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                req.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart());
                                req.setAppointmentDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getParameter("appointment_date")))));
                                Date date = new Date();
                                req.setApplicationDateTime(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                return_data = appointment_enq.entryAppointment(req);
                                if (return_data == null) {
                                    request.setAttribute("appointment_failure", "<div class=\"alert alert-danger\">Appointment is over for the selected date. Please try some other day</div>");
                                } else {
                                    ArrayList<String> appointmentInfo = appointment_enq.getAppointmentInfo(req.getSroOffice(), return_data.get(0));
                                    request.getSession().setAttribute("appointment_fee_structure", "<div class=\"appointment_details\"><br><b>Appointment Details as follows </b><br> Officer assigned: " + return_data.get(1) + "<br>Consideration Amount:" + result + "<br> Appointment Date and time: " + appointmentInfo.get(0) + " <br>Appointment ID: <label id=\"Message\" value=\"" + return_data.get(0) + "\">" + return_data.get(0) + "</label><br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + "</div>");
                                }
                            } catch (AppointmentSlotBookingDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (CategoryDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    //if not land related
                    if (str == null) {
                        try {
                            result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), null, null, null, "");
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //if (result != 0) {
                        if (Integer.parseInt(request.getParameter("applicant_type")) == 1) {
                            fee[0] = 0;
                            fee[1] = 0;
                        } else {
                            try {
                                fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), "", "", Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, null, null, null, "");
                            } catch (AppointmentSlotBookingDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (LandFeeDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (CategoryDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (LandvalueDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ImplementSroDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (AreadetailDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (RegfeeDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (RenquiryDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (fee[0] == -500 && fee[1] == -500) {
                            request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong.Check whether the deed is land related or not???</strong></div>");
                        } else {
                            if (fee[1] < 0) {
                                fee[1] = 0;
                            }
                            if (fee[0] < 0) {
                                fee[0] = 0;
                            }
                            req.setApplicantName(request.getParameter("ApplicantName"));
                            req.setEmail(request.getParameter("email"));
                            req.setMobileNumber(request.getParameter("mobile_number"));
                            req.setApplicantAddress(request.getParameter("applicant_address"));
                            req.setApplicantType(request.getParameter("applicant_type"));
                            req.setSroOffice(Integer.parseInt(request.getParameter("sro_office")));
                            req.setDeedType(Integer.parseInt(request.getParameter("Deedtype")));
                            req.setDeedSubtype(request.getParameter("Subdeedtype"));
                            req.setDocSubject(request.getParameter("DocSubject"));
                            req.setConsiderationAmount(result);
                            req.setRegistrationFee(fee[1]);
                            req.setStampDuty(fee[0]);
                            req.setWhetherLand(Short.parseShort("0"));
                            DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
                            DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                req.setApplicationDate(new SimpleDateFormat("yyyy-MM-dd").get2DigitYearStart());
                                req.setAppointmentDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getParameter("appointment_date")))));
                                Date date = new Date();
                                req.setApplicationDateTime(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                return_data = appointment_enq.entryAppointment(req);
                                if (return_data == null) {
                                    request.setAttribute("appointment_failure", "<div class=\"alert alert-danger\">Appointment is over for the selected date. Please try some other day</div>");
                                } else {
                                    ArrayList<String> appointmentInfo = appointment_enq.getAppointmentInfo(req.getSroOffice(), return_data.get(0));

                                    request.getSession().setAttribute("datetime", appointmentInfo.get(0));
                                    request.getSession().setAttribute("appointment_fee_structure", "<div class=\"appointment_details\"><br><b>Appointment Details as follows </b><br> Registry Office: " + appointmentInfo.get(1) + "<br> Appointment Date and time: " + appointmentInfo.get(0) + "<br>Consideration Amount: " + result + "<br />Appointment ID: <label id=\"Message\" value=\"" + return_data.get(0) + "\">" + return_data.get(0) + "</label><br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + "</div><br>");
                                }
                            } catch (AppointmentSlotBookingDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (CategoryDaoException ex) {
                                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    request.getSession().setAttribute("sro_office", Integer.parseInt(request.getParameter("sro_office")));
                    try {
                        request.getSession().setAttribute("appointment_date", new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("appointment_date")));
                    } catch (ParseException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (return_data == null) {
                        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/appointment/appointment.jsp");
                        view.forward(request, response);
                    } else {
                        request.getSession().setAttribute("appointment_id", return_data.get(0));
                        response.sendRedirect("appointment_details");

                        try {
                            //Get all the parts from request and write it to the file on server
                            InputStream inputStream = null;
                            AppointmentDocumentsDaoImpl appointmentDao = new AppointmentDocumentsDaoImpl();
                            for (Part part : request.getParts()) {
                                if (part.getName().equals("files")) {
                                    AppointmentDocuments appointmentDocs = new AppointmentDocuments();
                                    inputStream = part.getInputStream();
                                    appointmentDocs.setAppointmentId(return_data.get(0));
                                    appointmentDocs.setDocument(appointment_enq.toByteArray(inputStream));
                                    appointmentDao.insert(appointmentDocs);
                                }
                            }
                        } catch (AppointmentDocumentsDaoException ex) {
                        }
                    }
                }

            } catch (DeedtypeDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CategoryDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SroOfficesDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HolidayDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OffDaysDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (userPath.equals("/getsubdeed")) {
            try {
                String subdeedList = getSubDeed(request.getParameter("id"));
                System.out.println("subdeedList::: " + subdeedList);
                JSONObject jsonResult = new JSONObject(subdeedList);
                if (!"Error".equals(jsonResult.get("Status").toString())) {
                    JSONArray subdeedlist = jsonResult.getJSONArray("subdeedlist");
                    String options = "<option value=''>Click here to select</option>";
                    for (int i = 0; i < subdeedlist.length(); i++) {
                        jsonResult = new JSONObject(subdeedlist.optString(i));
                        options = options + "<option value='" + jsonResult.get("subDeed") + "'>" + jsonResult.get("subDeed") + "</option>";
                    }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(options);
                } else {
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonResult.get("Status").toString());
                }

            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //added By Shashanka
        //getsubdeed_json
        if (userPath.equals("/getsubdeed_json")) {
            try {
                String subdeedList = getSubDeed(request.getParameter("id"));
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(subdeedList);
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        if (userPath.equals("/getdeed_json")) {
            DeedtypeDaoImpl dao = new DeedtypeDaoImpl();
            JSONObject jsonData = new JSONObject();
            String options = "{\"success\":1,\"Deedtypes\":[";
            try {
                //request.setAttribute("Deedtypes", dao.findAll());
                System.out.println(dao.findAll());
                //jsonData.put("Deedtypes", dao.findAll());
                int length = dao.findAll().length;
                int i = 0;
                for (Deedtype x : dao.findAll()) {
                    jsonData.put("section", x.getSection());
                    jsonData.put("code", x.getCode());
                    if (i == length - 1) {
                        options = options + jsonData.toString();
                    } else {
                        options = options + jsonData.toString() + ",";
                    }

                    i++;
                }

                options = options + "]";

            } catch (DeedtypeDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            CategoryDaoImpl subdao = new CategoryDaoImpl();
            options = options + ",\"Subdeedtypes\":[";
            JSONObject jsonData1 = new JSONObject();
            try {
                //request.setAttribute("Subdeedtypes", subdao.findAll());
                //jsonData.put("Subdeedtypes", subdao.findAll());
                int length = subdao.findAll().length;
                int i = 0;
                for (Category x : subdao.findAll()) {
                    jsonData1.put("type", x.getSubDeedType());
                    if (i == length - 1) {
                        options = options + jsonData1.toString();
                    } else {
                        options = options + jsonData1.toString() + ",";
                    }
                    i++;
                }

                options = options + "]}";
            } catch (CategoryDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }

            LandTypeDaoImpl dao2 = new LandTypeDaoImpl();
            //request.setAttribute("Landtypes", dao2.findAll());
            //jsonData.put("Landtypes", dao2.findAll());

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(options);
            return;
        }

        //Ends
        if (userPath.equals("/subdeedbydeed")) {

            try {
                String id = request.getParameter("id");
                String subdeed = request.getParameter("subdeed");

                Deedtype[] deedTypes = new DeedtypeDaoImpl().findAll();
                String[] deedList = new String[deedTypes.length];
                for (int i = 0; i < deedTypes.length; i++) {
                    deedList[i] = Integer.toString(deedTypes[i].getCode());
                }

                Category[] catTypes = new CategoryDaoImpl().findAll();
                String[] catList = new String[catTypes.length];
                for (int i = 0; i < catTypes.length; i++) {
                    catList[i] = catTypes[i].getSubDeedType();
                }

                ValidationHandler validator = new ValidationHandler();
                validator.validate(id, "Deed Category", "valuein", deedList);
                validator.validate(subdeed, "Deed Sub-category", "valuein", catList);

                ArrayList<String> errors = validator.getErrors();
                if (!errors.isEmpty()) {
                    request.setAttribute("errormsg", errors);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("error");
                } else {
                    AppointmentHandler enq = new AppointmentHandler();
                    Category[] categories = enq.getSubDeedDropdown(id);

                    String options = "<option value=''>Click here to select</option>";
                    for (Category categorie : categories) {
                        if (categorie.getSubDeedType().equals(subdeed)) {
                            options = options + "<option value='" + categorie.getSubDeedType() + "' selected >" + categorie.getSubDeedType() + "</option>";
                        } else {
                            options = options + "<option value='" + categorie.getSubDeedType() + "' >" + categorie.getSubDeedType() + "</option>";
                        }
                    }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(options);
                }
            } catch (DeedtypeDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CategoryDaoException ex) {
                Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (userPath.equals("/getvillage")) {
            String i = request.getParameter("id");
            String id = i.substring(0, 8);
            AppointmentHandler enq = new AppointmentHandler();
            String vils = enq.getVillageDropdown(id, null);

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(vils);
        }
        if (userPath.equals("/appointment_details")) {
            request.setAttribute("sro_office", request.getSession().getAttribute("sro_office"));
            request.setAttribute("appointment_id", request.getSession().getAttribute("appointment_id"));
            request.setAttribute("appointment_date", request.getSession().getAttribute("appointment_date"));
            request.getRequestDispatcher("WEB-INF/view/appointment/appointment_details.jsp").forward(request, response);
        }

        if (userPath.equals("/appointment_status")) {
//            if(request.getSession().getAttribute("ran1") == null || request.getSession().getAttribute("ran2") == null) {
//                request.setAttribute("errormsg", "Invalid reqest.");
//                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/error/error.jsp");
//                view.forward(request, response);
//                return;
//            }

//            String ran1 = request.getSession().getAttribute("ran1").toString();
//            String ran2 = request.getSession().getAttribute("ran2").toString();
//            String res = Integer.toString(Integer.parseInt(ran1) + Integer.parseInt(ran2));
            // DocRefNo : AS-MAR/1/2019
            request.setAttribute("docrefno", request.getParameter("DocRefNo"));

            ValidationHandler validator = new ValidationHandler();
            validator.validate(request.getParameter("DocRefNo"), "Referrence No", "required:sql:xss");
//            validator.validate(request.getParameter("captcha"), "Security Question", "valuein", new String[]{res});

            ArrayList<String> errors = validator.getErrors();
            //new CaptchaHandler().setCaptcha(request);

            if (!errors.isEmpty()) {
                request.setAttribute("errormsg", errors);
                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/appointment/appointment_status.jsp");
                view.forward(request, response);
            } else {
                postAppointmentStatus(request, response);
            }

        }

        //-----Display the Applicant Details to Approve---
        if (userPath.equals("/appointment_approve")) {
            Integer slno = Integer.parseInt(request.getParameter("slno"));
            System.out.println("serial no is " + slno);
            Object[] obj = new Object[]{slno};
            AppointmentSlotBooking[] appointmentSlotBooking = null;
            AppointmentSlotBookingDaoImpl apointmentSlotBookingDAO = new AppointmentSlotBookingDaoImpl();
            try {
                appointmentSlotBooking = apointmentSlotBookingDAO.findByDynamicWhere("slno=?", obj);

                for (AppointmentSlotBooking appointmentSlotBooking1 : appointmentSlotBooking) {
                    System.out.println("name is" + appointmentSlotBooking1.getApplicantName());
                    request.getSession().setAttribute("slno", appointmentSlotBooking1.getSlno());
                    request.getSession().setAttribute("appointment_id", appointmentSlotBooking1.getAppointmentId());
                    request.getSession().setAttribute("applicant_name", appointmentSlotBooking1.getApplicantName());
                    request.getSession().setAttribute("area_type", appointmentSlotBooking1.getAreaType());
                    request.getSession().setAttribute("appointment_date", appointmentSlotBooking1.getApplicationDate());
                    request.getSession().setAttribute("deed_type", appointmentSlotBooking1.getDeedType());
                    request.getSession().setAttribute("deed_subtype", appointmentSlotBooking1.getDeedSubtype());
                    request.getSession().setAttribute("consideration_amount", appointmentSlotBooking1.getConsiderationAmount());
                    request.getSession().setAttribute("document_to_be_furnished", appointmentSlotBooking1.getDocumentToBeFurnished());
                    request.getSession().setAttribute("doc_subject", appointmentSlotBooking1.getDocSubject());
                    request.getSession().setAttribute("gender", appointmentSlotBooking1.getGender());
                    request.getSession().setAttribute("act", appointmentSlotBooking1.getAct());
                    request.getSession().setAttribute("sro_office", appointmentSlotBooking1.getSroOffice());
                    request.getSession().setAttribute("expdate", new SimpleDateFormat("dd-MM-yyyy").format(appointmentSlotBooking1.getAppointmentDate()));
                    request.getSession().setAttribute("stampdutyfee", appointmentSlotBooking1.getStampDuty());
                    request.getSession().setAttribute("regfee", appointmentSlotBooking1.getRegistrationFee());
                    request.getSession().setAttribute("regfee", appointmentSlotBooking1.getRegistrationFee());
                    request.getSession().setAttribute("email", appointmentSlotBooking1.getEmail());
                    request.getSession().setAttribute("mobile_number", appointmentSlotBooking1.getMobileNumber());
                    request.getSession().setAttribute("whether_land", appointmentSlotBooking1.getWhetherLand());
                }
                request.setAttribute("appointmentSlotBooking", appointmentSlotBooking);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("appointment_approve");
        }
//----Update, Approval Status of Applicant---
        if (userPath.equals("/appointment_approve_update")) {
            AppointmentSlotBooking appointmentSlotBooking = new AppointmentSlotBooking();
            //seting entity
            appointmentSlotBooking.setSlno(Integer.parseInt(request.getSession().getAttribute("slno").toString()));
            appointmentSlotBooking.setAppointmentId(request.getSession().getAttribute("appointment_id").toString());
            appointmentSlotBooking.setApplicantName(request.getParameter("appointee_name"));
            if (request.getSession().getAttribute("area_type") == null) {
                appointmentSlotBooking.setAreaType(null);
            } else {
                appointmentSlotBooking.setAreaType(request.getSession().getAttribute("area_type").toString());
            }
            appointmentSlotBooking.setDeedType(Integer.parseInt(request.getSession().getAttribute("deed_type").toString()));
            appointmentSlotBooking.setDeedSubtype(request.getSession().getAttribute("deed_subtype").toString());
            appointmentSlotBooking.setConsiderationAmount(Integer.parseInt(request.getSession().getAttribute("consideration_amount").toString()));
            if (request.getSession().getAttribute("doc_subject") == null) {
                appointmentSlotBooking.setDocSubject(null);
            } else {
                appointmentSlotBooking.setDocSubject(request.getSession().getAttribute("doc_subject").toString());
            }
            appointmentSlotBooking.setWhetherLand(Short.parseShort(request.getSession().getAttribute("whether_land").toString()));
            if (request.getSession().getAttribute("gender") == null) {
                appointmentSlotBooking.setGender(null);
            } else {
                appointmentSlotBooking.setGender(request.getSession().getAttribute("gender").toString());
            }
            appointmentSlotBooking.setAct(request.getSession().getAttribute("act").toString());
            appointmentSlotBooking.setEmail(request.getSession().getAttribute("email").toString());
            appointmentSlotBooking.setMobileNumber(request.getSession().getAttribute("mobile_number").toString());
            appointmentSlotBooking.setDocumentToBeFurnished(request.getParameter("doc_to_furnish"));

            appointmentSlotBooking.setSroOffice(Integer.parseInt(request.getSession().getAttribute("sro_office").toString()));
            DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                appointmentSlotBooking.setAppointmentDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getParameter("exp_date")))));
                appointmentSlotBooking.setApplicationDate(dbSdf.parse(dbSdf.format(formSdf.parse(request.getSession().getAttribute("appointment_date").toString()))));
            } catch (Exception e) {
            }
            appointmentSlotBooking.setStampDuty(Integer.parseInt(request.getParameter("stampduty_fee")));
            appointmentSlotBooking.setRegistrationFee(Integer.parseInt(request.getParameter("reg_fee")));
            appointmentSlotBooking.setWhetherLand((short) 1);
            try {
                AppointmentApproveHandler appointmentapprovehandler = new AppointmentApproveHandler();
                appointmentapprovehandler.updateAppointmentApprove(appointmentSlotBooking, request.getSession().getAttribute("appointment_id").toString());

            } catch (Exception e) {
            }
            updateFlag = 1;
            response.sendRedirect("upcoming_appointments");
        }
//---End of Update the Approval Status of Applicant-------
        if (userPath.equals("/upcoming_appointments")) {
            int page = 1;
            int recordsPerPage = 10;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            UpcomingAppointmentsHandler upcmgappointment = new UpcomingAppointmentsHandler();
            AppointmentDetails slotbooking[] = null;
            try {
                slotbooking = upcmgappointment.pagination((page - 1) * recordsPerPage, recordsPerPage, request.getParameter("appointment_date"));
                if (slotbooking.length == 0) {
                    request.setAttribute("firstMessage", "<div class=\'alert alert-danger\'><b>No Upcoming Appointment</b></div>");
                } else {
                    request.setAttribute("appointmentslotbooking", slotbooking);
                }
                if (updateFlag == 0) {
                    request.getSession().setAttribute("StatusMsg", "");
                }
                if (updateFlag == 1) {
                    updateFlag = 0;
                    request.getSession().setAttribute("StatusMsg", "Appointment successfully Approved");
                }
            } catch (AppointmentDetailsDaoException ex) {
                Logger.getLogger(AppointmentDetailsDaoException.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
            }

            int noOfRecords;
            try {
                noOfRecords = upcmgappointment.getNoOfRecords(request.getParameter("appointment_date"));
                if (noOfRecords > 1) {
                    request.setAttribute("currentPage", page);
                }
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
            } catch (AppointmentSlotBookingDaoException ex) {
                Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "/WEB-INF/view/appointment" + userPath + ".jsp";
            request.getRequestDispatcher(url).forward(request, response);
            //response.sendRedirect("upcoming_appointments");
        }

        if (userPath.equals("/appointment_docs")) {
            Random ran = new Random();
            Integer id = ran.nextInt();
            //String realPdfFilePath = getServletContext().getRealPath(PDF_URL);
            ViewAppointmentDocs viewAppointmentDocs = new ViewAppointmentDocs();
            request.setAttribute("appointmentDocs", viewAppointmentDocs.showDocs(request.getParameter("appointment_id")));
            String url = "/WEB-INF/view/appointment/appointment_docs" + ".jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public void postAppointmentStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject json = new JSONObject();

        //String name = request.getParameter("applicant_name");
        Object[] obj = new Object[]{request.getParameter("DocRefNo")};
        AppointmentSlotBooking[] appointmentSlotBooking = null;
        AppointmentSlotBookingDaoImpl apointmentSlotBookingDAO = new AppointmentSlotBookingDaoImpl();

        try {
            appointmentSlotBooking = apointmentSlotBookingDAO.findByDynamicWhere("appointment_id=?", obj);
            if (appointmentSlotBooking.length == 0) {
                request.getSession().setAttribute("appointment_fee_structure", null);
                //request.setAttribute("appointment_error", "<div class=\'alert alert-danger\'><b>Application number is incorrect. Please provide the correct information.</b></div>");
                try {
                    json.put("success", false);
                    json.put("msg", "Application number is incorrect. Please provide the correct information.");

                } catch (JSONException ex) {
                    Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                // Check for cancelled apointment
                CancelledAppointments cancelledAppointment = null;
                CancelledAppointmentsDaoImpl canceledDao = new CancelledAppointmentsDaoImpl();
                if (canceledDao.findByPrimaryKey("tmp/1/2015") != null) {

                    cancelledAppointment = canceledDao.findByPrimaryKey("tmp/1/2015");
                    //request.getSession().setAttribute("appointment_fee_structure", "<div class=\"appointment_details\"><br><b><br>" + cancelledAppointment.getReason() + "</div>");

                    try {
                        json.put("success", false);
                        json.put("msg", "Appointment Canceled due to following reasons... " + cancelledAppointment.getReason());

                    } catch (JSONException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    AppointmentHandler appointment_enq = new AppointmentHandler();
                    //String officer_name = appointment_enq.getNodalOfficerName(appointmentSlotBooking[0].getSroOffice(), appointmentSlotBooking[0].getOfficerId());
                    ArrayList<String> appointmentInfo = appointment_enq.getAppointmentInfo(appointmentSlotBooking[0].getSroOffice(), request.getParameter("DocRefNo"));

                    try {
                        json.put("success", true);
                        json.put("Registry Office", appointmentInfo.get(1));
                        json.put("Appointment Date and time", appointmentInfo.get(0));
                        json.put("Appointment ID", appointmentSlotBooking[0].getAppointmentId());
                        json.put("Registration Fee", appointmentSlotBooking[0].getRegistrationFee());
                        json.put("Stamp Duty", appointmentSlotBooking[0].getStampDuty());

                        // Registration Fee (In Rupees):" + appointmentSlotBooking[0].getRegistrationFee() + " <br> Stamp Duty: (In Rupees):" + appointmentSlotBooking[0].getStampDuty()
                    } catch (JSONException ex) {
                        Logger.getLogger(AppointmentServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //request.getSession().setAttribute("appointment_fee_structure", "<div style=\"padding: 10px; margin: 20px 0px 0px 10px;\" class=\"appointment_details panel panel-success\"><b>Appointment Details as follows </b><br> Registry Office: " + appointmentInfo.get(1) +  "<br> Appointment Date and time: " + appointmentInfo.get(0) + " <br>Appointment ID: " + appointmentSlotBooking[0].getAppointmentId() + "<br>Registration Fee (In Rupees):" + appointmentSlotBooking[0].getRegistrationFee() + " <br> Stamp Duty: (In Rupees):" + appointmentSlotBooking[0].getStampDuty() + "</div>");
                }
            }
        } catch (AppointmentSlotBookingDaoException ex) {
            Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CancelledAppointmentsDaoException ex) {
            Logger.getLogger(AppointmentSlotBookingDaoException.class.getName()).log(Level.SEVERE, null, ex);
        }

        // send json response back to Android
        response.getWriter().print(json.toString());

        //request.getRequestDispatcher("WEB-INF/view/appointment/appointment_status.jsp").forward(request, response);
    }

    public void send_email(String userName, String emailId, ArrayList<String> appointmentDetails, String contextPath, int marketValue, int[] fee) {
        final String username = "techxplor@gmail.com";
        final String password = "Passw0rd123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("test@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
            message.setSubject("Status on your appointment booking");
            message.setContent("Dear " + userName + ","
                    + "<h4> Your appointment is confirmed. </h4>" + "<div class=\"appointment_details\"><br><b>Appointment Details as follows </b><br> Officer assigned: " + appointmentDetails.get(1) + "<br>Consideration Amount: " + marketValue + " <br>Appointment ID: <label id=\"Message\" value=\"" + appointmentDetails.get(0) + "\">" + appointmentDetails.get(0) + "</label><br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + "</div>" + "<div><a href=" + contextPath + "/appointment_status" + ">" + "Click here to know more" + "</a></div>", "text/html");

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void send_email(String userName, String emailId, String appointmentId, String contextPath, Date expectedDate, Date givenDate) {

        final String username = "techxplor@gmail.com";
        final String password = "Passw0rd123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        System.out.println("I am called");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("test@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailId));
            message.setSubject("Status on your appointment booking");
            if (expectedDate.equals(givenDate)) {
                message.setContent("Dear " + userName + ","
                        + "<h4>We are happy to say that the appointment is confirmed for " + new SimpleDateFormat("dd-MM-yyyy").format(expectedDate) + "</h4>"
                        + "<div>Come along with the proper documents for hassle free registration</div>", "text/html");
            } else {
                message.setContent("Dear " + userName + ","
                        + "<h4>We are extremely sorry. We could not be able to give you the appointment for " + new SimpleDateFormat("dd-MM-yyyy").format(expectedDate) + "</h4>"
                        + "<div>The new appointment date alloted for you is " + new SimpleDateFormat("dd-MM-yyyy").format(givenDate) + "</div>", "text/html");
            }
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void setResponse(HttpServletRequest request) {

        request.setAttribute("applicant", request.getParameter("ApplicantName"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("mobile", request.getParameter("mobile_number"));
        request.setAttribute("address", request.getParameter("applicant_address"));
        request.setAttribute("cityvill", request.getParameter("applicant_city_vill"));
        request.setAttribute("postoff", request.getParameter("applicant_post_office"));
        request.setAttribute("district", request.getParameter("applicant_district"));
        request.setAttribute("pin", request.getParameter("applicant_pin"));
        request.setAttribute("apptype", request.getParameter("applicant_type"));
        if (StringUtils.isNumeric(request.getParameter("applicant_type"))) {
            request.setAttribute("apptype", request.getParameter("applicant_type"));
        } else {
            request.setAttribute("apptype", "");
        }
        request.setAttribute("appdate", request.getParameter("appointment_date"));
        if (StringUtils.isNumeric(request.getParameter("sro_office"))) {
            request.setAttribute("sro", request.getParameter("sro_office"));
        }
        if (StringUtils.isNumeric(request.getParameter("Deedtype"))) {
            request.setAttribute("deedtype", request.getParameter("Deedtype"));
        } else {
            request.setAttribute("deedtype", 0);
        }
        request.setAttribute("subdeedtype", request.getParameter("Subdeedtype"));
        request.setAttribute("conamount", request.getParameter("ConsiderationAmt"));
        request.setAttribute("areatype", request.getParameter("UrbanRural"));
        request.setAttribute("gender", request.getParameter("Gender"));
    }

}

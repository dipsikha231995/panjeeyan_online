/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.nic.epanjeeyan.CaptchaHandler;
import org.nic.epanjeeyan.FeeCalculator;
import org.nic.epanjeeyan.ValidationHandler;
import org.nic.epanjeeyan.dto.Category;
import org.nic.epanjeeyan.dto.Deedtype;
import org.nic.epanjeeyan.dto.Renquiry;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.exceptions.AreadetailDaoException;
import org.nic.epanjeeyan.exceptions.CategoryDaoException;
import org.nic.epanjeeyan.exceptions.DeedtypeDaoException;
import org.nic.epanjeeyan.exceptions.ImplementSroDaoException;
import org.nic.epanjeeyan.exceptions.LandFeeDaoException;
import org.nic.epanjeeyan.exceptions.LandTypeDaoException;
import org.nic.epanjeeyan.exceptions.LandvalueDaoException;
import org.nic.epanjeeyan.exceptions.RegfeeDaoException;
import org.nic.epanjeeyan.exceptions.RenquiryDaoException;
import org.nic.epanjeeyan.jdbc.CategoryDaoImpl;
import org.nic.epanjeeyan.jdbc.DeedtypeDaoImpl;
import org.nic.epanjeeyan.jdbc.LandTypeDaoImpl;

/**
 *
 * @author ankita
 */
public class EnquiryServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        if (userPath.equals("/enquiry")) {
            try {
                userPath = "/enquiry" + userPath;
                
                new CaptchaHandler().setCaptcha(request);
                DeedtypeDaoImpl dao = new DeedtypeDaoImpl();
                request.setAttribute("Deedtypes", dao.findAll());
                CategoryDaoImpl subdao = new CategoryDaoImpl();
                request.setAttribute("Subdeedtypes", subdao.findAll());
                
                LandTypeDaoImpl dao2 = new LandTypeDaoImpl();
                request.setAttribute("Landtypes", dao2.findAll());
            } 
            catch (DeedtypeDaoException ex) {
                Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (CategoryDaoException ex) {
                Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (LandTypeDaoException ex) {
                Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String url = "/WEB-INF/view" + userPath + ".jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        String token = request.getParameter("token");
        Renquiry req = new Renquiry();
        
        System.out.println("##########################################################################");
        for (String key : request.getParameterMap().keySet()) {
            System.out.println(key + " : " + request.getParameter(key));
        }
        
        //Changes By Shashanka Starts
        if (userPath.equals("/enquiry_api_demo")) {
            System.out.println("asdasdasd");
            String successMessage="";
            JSONObject jsonData = new JSONObject();
            try {
                
//                if(request.getSession().getAttribute("ran1") == null && request.getSession().getAttribute("ran2") == null) {
//                    request.setAttribute("errormsg", "Invalid answer to security question");
//                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/error/error.jsp");
//                    view.forward(request, response);
//                    return;
//                }
                
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
                
                
                String str = request.getParameter("Gender");
                
                setResponse(request);
                
//                String ran1 = request.getSession().getAttribute("ran1").toString();
//                String ran2 = request.getSession().getAttribute("ran2").toString();
                
                //String res = Integer.toString(Integer.parseInt(ran1) + Integer.parseInt(ran2));
                
                ValidationHandler validator = new ValidationHandler();
                validator.validate(request.getParameter("Deedtype"), "Deed Category", "valuein", deedList);
                validator.validate(request.getParameter("Subdeedtype"), "Deed Sub-category", "valuein", catList);
                
                //validator.validate(request.getParameter("captcha"), "Security Question", "valuein", new String[]{res});
                
                validator.validate(request.getParameter("ConsiderationAmt"), "Consideration Amount", "numeric:max_9:low_0:high_2147483647");
                validator.validate(request.getParameter("Gender"), "Purchaser with", "valuein", new String[]{null, "MF", "F", "M"});
                validator.validate(request.getParameter("UrbanRural"), "Land/Flat is located in", "valuein", new String[]{null, "UG", "UM", "R"});
                
                ArrayList<String> errors = validator.getErrors();
                new CaptchaHandler().setCaptcha(request);
                if (!errors.isEmpty()) {
                    request.setAttribute("errormsg", errors);
                    request.setAttribute("Deedtypes", deedTypes);
                    request.setAttribute("Subdeedtypes", catTypes);
                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/enquiry/enquiry.jsp");
                    view.forward(request, response);
                    
                    
                } 
                else {
                    FeeCalculator fc = new FeeCalculator();
                    int result = 0;
                    int fee[] = new int[2];
                    
                    if (str != null) {
                        try {
                            req.setWhetherLand(1);
                            result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                            fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), request.getParameter("Gender"), request.getParameter("UrbanRural"), Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                            
                            req.setRegistrationFee(fee[1]);
                            req.setStampDuty(fee[0]);
                            
                            if (fee[0] == -500 && fee[1] == -500) {
                                request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong. Check whether the deed is land related or not???</strong></div>");
                            } 
                            else {
                                if (fee[1] < 0) {
                                    fee[1] = 0;
                                }
                                if (fee[0] < 0) {
                                    fee[0] = 0;
                                }
                                
                            }
                            
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (str == null) {
                        try {
                            result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), null, null, null, "");
                            fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), "", "", Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, null, null, null, "");
                            
                            if (fee[0] == -500 && fee[1] == -500) {
                                request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong.Check whether the deed is land related or not???</strong></div>");
                            } else {
                                if (fee[1] < 0) {
                                    fee[1] = 0;
                                }
                                if (fee[0] < 0) {
                                    fee[0] = 0;
                                }
                                
                                
                            }
                            
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    String options = "{\"success\":1,\"Result\":[";
                    try {
                        
                        //jsonData.put("renquiry", req);
                        
                        jsonData.put("conAmount", result);
                        jsonData.put("stampDuty", fee[0]);
                        jsonData.put("regFee", fee[1]);
                        jsonData.put("successMessage", successMessage);
                    } catch (JSONException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    options=options+jsonData.toString();
                    options = options+ "]}";
                    
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(options);
                    
                    System.out.println(options);
                    
                    //RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/enquiry/enquiry.jsp");
                    //view.forward(request, response);
                }
                } catch (DeedtypeDaoException ex) {
                    Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CategoryDaoException ex) {
                    Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
           
        }
        
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        if (userPath.equals("/enquiry_api")) {
            JSONObject jsonData = new JSONObject();
            try{
                Algorithm algorithm = Algorithm.HMAC256("ingDLMRuGe9UKHRNjs7cYckS2yul4lc3");
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                
                Claim Deedtype = jwt.getClaim("Deedtype");
                Claim Subdeedtype = jwt.getClaim("Subdeedtype");
                Claim ConsiderationAmt = jwt.getClaim("ConsiderationAmt");
                Claim Gender = jwt.getClaim("Gender");
                Claim UrbanRural = jwt.getClaim("UrbanRural");
                FeeCalculator fc = new FeeCalculator();
                int result = 0;
                int fee[] = new int[2];
                String successMessage="";
                if (Gender.asString() != null) {
                    System.out.println(ConsiderationAmt.asString());
                    try {
                        req.setWhetherLand(1);
                        result = fc.getMarketvalue(Integer.parseInt(ConsiderationAmt.asString()), 0, Integer.parseInt(Deedtype.asString()), Subdeedtype.asString(), Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                        fee = fc.getFee(Integer.parseInt(Deedtype.asString()), Subdeedtype.asString(), Gender.asString(), UrbanRural.asString(), Integer.parseInt(ConsiderationAmt.asString()), 0, Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));

                        req.setRegistrationFee(fee[1]);
                        req.setStampDuty(fee[0]);

                        if (fee[0] == -500 && fee[1] == -500) {
                            //request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong. Check whether the deed is land related or not???</strong></div>");
                            successMessage="Something went wrong. Check whether the deed is land related or not???";
                        } else {
                            if (fee[1] < 0) {
                                fee[1] = 0;
                            }
                            if (fee[0] < 0) {
                                fee[0] = 0;
                            }
                        }

                    } catch (LandvalueDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ImplementSroDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AreadetailDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AppointmentSlotBookingDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CategoryDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RenquiryDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LandFeeDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RegfeeDaoException ex) {
                        Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Gender.asString() == null) {
                        try {
                            result = fc.getMarketvalue(Integer.parseInt(ConsiderationAmt.asString()), 0, Integer.parseInt(Deedtype.asString()), Subdeedtype.asString(), null, null, null, "");
                            fee = fc.getFee(Integer.parseInt(Deedtype.asString()), Subdeedtype.asString(), "", "", Integer.parseInt(ConsiderationAmt.asString()), 0, null, null, null, "");
                            
                            if (fee[0] == -500 && fee[1] == -500) {
                                //request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong.Check whether the deed is land related or not???</strong></div>");
                                successMessage="Something went wrong. Check whether the deed is land related or not???";
                            } else {
                                if (fee[1] < 0) {
                                    fee[1] = 0;
                                }
                                if (fee[0] < 0) {
                                    fee[0] = 0;
                                }
                                
                                req.setRegistrationFee(fee[1]);
                                req.setStampDuty(fee[0]);
                                req.setWhetherLand(0);
                            }
                            
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                try {
                    jsonData.put("Deedtype", Deedtype.asString());
                    jsonData.put("Subdeedtype", Subdeedtype.asString());
                    jsonData.put("ConsiderationAmt", ConsiderationAmt.asString());
                    jsonData.put("Gender", Gender.asString());
                    jsonData.put("UrbanRural", UrbanRural.asString());
                    //jsonData.put("renquiry", req);
                    jsonData.put("conAmount", result);
                    jsonData.put("stampDuty", fee[0]);
                    jsonData.put("regFee", fee[1]);
                    jsonData.put("successMessage", successMessage);
                } catch (JSONException ex) {
                    Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonData.toString());
//                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/enquiry/enquiry.jsp");
//                    view.forward(request, response);
                }catch (JWTVerificationException exception){
                        System.out.println("JWT Token verification failed."+ exception.getMessage());
                        try {
                            jsonData.put("Status", "JWT Token verification failed");
                        } catch (JSONException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonData.toString());
                                //returnWithError(userPath, request, response);
                }
            
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        
        if (userPath.equals("/enquiry")) {
                try {
                if(request.getSession().getAttribute("ran1") == null && request.getSession().getAttribute("ran2") == null) {
                    request.setAttribute("errormsg", "Invalid answer to security question");
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
                
                
                String str = request.getParameter("Gender");
                
                setResponse(request);
                String ran1 = request.getSession().getAttribute("ran1").toString();
                String ran2 = request.getSession().getAttribute("ran2").toString();
                
                String res = Integer.toString(Integer.parseInt(ran1) + Integer.parseInt(ran2));
                
                ValidationHandler validator = new ValidationHandler();
                validator.validate(request.getParameter("Deedtype"), "Deed Category", "valuein", deedList);
                validator.validate(request.getParameter("Subdeedtype"), "Deed Sub-category", "valuein", catList);
                validator.validate(request.getParameter("captcha"), "Security Question", "valuein", new String[]{res});
                validator.validate(request.getParameter("ConsiderationAmt"), "Consideration Amount", "numeric:max_9:low_0:high_2147483647");
                validator.validate(request.getParameter("Gender"), "Purchaser with", "valuein", new String[]{null, "MF", "F", "M"});
                validator.validate(request.getParameter("UrbanRural"), "Land/Flat is located in", "valuein", new String[]{null, "UG", "UM", "R"});
                
                ArrayList<String> errors = validator.getErrors();
                new CaptchaHandler().setCaptcha(request);
                if (!errors.isEmpty()) {
                    request.setAttribute("errormsg", errors);
                    request.setAttribute("Deedtypes", deedTypes);
                    request.setAttribute("Subdeedtypes", catTypes);
                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/enquiry/enquiry.jsp");
                    view.forward(request, response);
                    
                    
                } else {
                    FeeCalculator fc = new FeeCalculator();
                    int result = 0;
                    int fee[] = new int[2];
                    
                    if (str != null) {
                        try {
                            req.setWhetherLand(1);
                            result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                            fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), request.getParameter("Gender"), request.getParameter("UrbanRural"), Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Float.parseFloat("0"), Float.parseFloat("0"), Float.parseFloat("0"), request.getParameter("LocDetail"));
                            
                            req.setRegistrationFee(fee[1]);
                            req.setStampDuty(fee[0]);
                            
                            if (fee[0] == -500 && fee[1] == -500) {
                                request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong. Check whether the deed is land related or not???</strong></div>");
                            } else {
                                if (fee[1] < 0) {
                                    fee[1] = 0;
                                }
                                if (fee[0] < 0) {
                                    fee[0] = 0;
                                }
                                request.setAttribute("Deedtypes", deedTypes);
                                request.setAttribute("Subdeedtypes", catTypes);
                                request.setAttribute("successMessage", "<div class=\"alert alert-success\"><font color=\"green\"><b><h4>Information Slip for Registration<h4></font></b><br>Consideration Amout:" + result + " <br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + " </div><br> ");
                                request.setAttribute("renquiry", req);
                                request.setAttribute("conAmount", result);
                                request.setAttribute("stampDuty", fee[0]);
                                request.setAttribute("regFee", fee[1]);
                            }
                            
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (str == null) {
                        try {
                            result = fc.getMarketvalue(Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), null, null, null, "");
                            fee = fc.getFee(Integer.parseInt(request.getParameter("Deedtype")), request.getParameter("Subdeedtype"), "", "", Integer.parseInt(request.getParameter("ConsiderationAmt")), 0, null, null, null, "");
                            
                            if (fee[0] == -500 && fee[1] == -500) {
                                request.setAttribute("successMessage", "<div class=\'alert alert-danger\'><strong>Something went wrong.Check whether the deed is land related or not???</strong></div>");
                            } else {
                                if (fee[1] < 0) {
                                    fee[1] = 0;
                                }
                                if (fee[0] < 0) {
                                    fee[0] = 0;
                                }
                                
                                req.setRegistrationFee(fee[1]);
                                req.setStampDuty(fee[0]);
                                req.setWhetherLand(0);
                                request.setAttribute("Deedtypes", deedTypes);
                                request.setAttribute("Subdeedtypes", catTypes);
                                request.setAttribute("renquiry", req);
                                request.setAttribute("successMessage", "<div style=\"margin-bottom:0;\" class=\"alert alert-success\"><font color=\"green\"><b><h4> Information Slip for Registration</h4></font></b>Consideration Amount:" + result + " <br>Registration Fee (In Rupees):" + fee[1] + " <br> Stamp Duty: (In Rupees):" + fee[0] + "</div>");
                                request.setAttribute("conAmount", result);
                                request.setAttribute("stampDuty", fee[0]);
                                request.setAttribute("regFee", fee[1]);
                            }
                            
                        } catch (LandvalueDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ImplementSroDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AreadetailDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AppointmentSlotBookingDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CategoryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RenquiryDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (LandFeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RegfeeDaoException ex) {
                            Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/view/enquiry/enquiry.jsp");
                    view.forward(request, response);
                }
                } catch (DeedtypeDaoException ex) {
                    Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CategoryDaoException ex) {
                    Logger.getLogger(EnquiryServelet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
        }
    }
    
    private void setResponse(HttpServletRequest request) {
        if(StringUtils.isNumeric(request.getParameter("Deedtype"))) {request.setAttribute("deedtype", request.getParameter("Deedtype"));}
        else {request.setAttribute("deedtype", 0);}
        
        request.setAttribute("subdeedtype", request.getParameter("Subdeedtype"));
        request.setAttribute("conamount", request.getParameter("ConsiderationAmt"));
        request.setAttribute("areatype", request.getParameter("UrbanRural"));
        request.setAttribute("gender", request.getParameter("Gender"));
    }

}

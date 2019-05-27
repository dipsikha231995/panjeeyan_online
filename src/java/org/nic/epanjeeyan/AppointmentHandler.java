/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.nic.epanjeeyan.dto.Category;
import org.nic.epanjeeyan.dto.AppointmentSlotBooking;
import org.nic.epanjeeyan.dto.Holiday;
import org.nic.epanjeeyan.dto.LastOfficerAssigned;
import org.nic.epanjeeyan.dto.LeavePlan;
import org.nic.epanjeeyan.dto.NodalOfficers;
import org.nic.epanjeeyan.dto.OffDays;
import org.nic.epanjeeyan.dto.SroOffices;
import org.nic.epanjeeyan.dto.Vilcode;
import org.nic.epanjeeyan.exceptions.CategoryDaoException;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.exceptions.HolidayDaoException;
import org.nic.epanjeeyan.exceptions.LastOfficerAssignedDaoException;
import org.nic.epanjeeyan.exceptions.LeavePlanDaoException;
import org.nic.epanjeeyan.exceptions.NodalOfficersDaoException;
import org.nic.epanjeeyan.exceptions.OffDaysDaoException;
import org.nic.epanjeeyan.exceptions.SroOfficesDaoException;
import org.nic.epanjeeyan.exceptions.VilcodeDaoException;
import org.nic.epanjeeyan.jdbc.CategoryDaoImpl;
import org.nic.epanjeeyan.jdbc.AppointmentSlotBookingDaoImpl;
import org.nic.epanjeeyan.jdbc.HolidayDaoImpl;
import org.nic.epanjeeyan.jdbc.LastOfficerAssignedDaoImpl;
import org.nic.epanjeeyan.jdbc.LeavePlanDaoImpl;
import org.nic.epanjeeyan.jdbc.NodalOfficersDaoImpl;
import org.nic.epanjeeyan.jdbc.OffDaysDaoImpl;
import org.nic.epanjeeyan.jdbc.SroOfficesDaoImpl;
import org.nic.epanjeeyan.jdbc.VilcodeDaoImpl;

/**
 *
 * @author ankita
 */
public class AppointmentHandler {

    public Category[] getSubDeedDropdown(String code) {
        Category[] categories = null;
        CategoryDaoImpl dao = new CategoryDaoImpl();
        Object[] sqlParams = new Object[]{Integer.parseInt(code)};

        try {
            categories = dao.findByDynamicWhere("code=?", sqlParams);

        } catch (CategoryDaoException ex) {
            Logger.getLogger(AppointmentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }

    public ArrayList entryAppointment(AppointmentSlotBooking req) throws AppointmentSlotBookingDaoException, CategoryDaoException {

        int max_appointment = 20;   // max appointment for sro per day
        Boolean check = true; //for normal process of assigning timeslot
        
        String sql1 = "select count(*) from appointment_slot_booking";
        String sql = "Select max(slno) from appointment_slot_booking";
        DateFormat formSdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<String> return_data = new ArrayList<String>();
        AppointmentSlotBookingDaoImpl rDao1 = new AppointmentSlotBookingDaoImpl();
        Integer a;
        Calendar cal = Calendar.getInstance();
        Integer year = Integer.valueOf(cal.get(Calendar.YEAR));
        String temp = null;
        a = rDao1.CustomDynamicSelect(sql1, null);
        if (a == 0) {
            req.setSlno(1);
            temp = "AS-REG/1/" + year;
            req.setAppointmentId("AS-REG/1/" + year);
        } else {
            a = rDao1.CustomDynamicSelect(sql, null);
            Integer k = a + 1;
            req.setSlno(k);

            String sql2 = "select appointment_id from appointment_slot_booking where slno=(select max(slno)from appointment_slot_booking)";
            AppointmentSlotBookingDaoImpl rDao2 = new AppointmentSlotBookingDaoImpl();
            String b;

            try {
                b = rDao2.CustomDynamicSelect1(sql2, null);
                String c = b.substring((b.lastIndexOf('/') + 1), b.length());
                String d = b.substring((b.indexOf('/') + 1), b.lastIndexOf('/'));
                Integer e = Integer.parseInt(c);
                Integer f = Integer.parseInt(d);
                Calendar now = Calendar.getInstance();
                Integer g = now.get(Calendar.YEAR);
                if (g.toString().equals(e.toString())) {
                    Integer l = f + 1;
                    temp = "AS-REG/" + l + "/" + e;
                    req.setAppointmentId("AS-REG/" + l + "/" + e);
                } else {
                    temp = "AS-REG/" + g;
                    req.setAppointmentId("AS-REG/1/" + g);
                }
            } catch (AppointmentSlotBookingDaoException ex) {
                
            }

        }
        CategoryDaoImpl c = new CategoryDaoImpl();

        String sqll = "select act from category where code=? and sub_deed_type=?";
        Object[] obj = new Object[]{req.getDeedType(), req.getDeedSubtype()};
        req.setAct(c.CustomDynamicSelect1(sqll, obj));

        Calendar now = Calendar.getInstance();
        java.util.Date dt = now.getTime();
        java.sql.Date sqlDate = new java.sql.Date(dt.getTime());

        req.setApplicationDate(sqlDate);

// @todo: set the Officer to an appointment
//        try {
//            LastOfficerAssignedDaoImpl lastOfficer = new LastOfficerAssignedDaoImpl();
//            LastOfficerAssigned[] officers = null;
//            officers = lastOfficer.execute(req.getSroOffice(), formSdf.format(req.getAppointmentDate()));
//            // if not exists---------- 
//            if (officers == null) {
//            } else {
//                NodalOfficersDaoImpl nodalDao = new NodalOfficersDaoImpl();
//                Object[] sqlParam = new Object[]{req.getSroOffice()};
//                NodalOfficers[] all_officers = null;
//                all_officers = nodalDao.findByDynamicWhere("office_id=?", sqlParam);
//                //int[] avbl_officers = new int[all_officers.length];
//                ArrayList<Integer> avbl_officers = new ArrayList<Integer>();
//// if there is more than one officer for that particular sro office
//                if (all_officers.length > 1) {
//                    int j = 0;
//                    boolean officer_match = false;
//                    for (int i = 0; i < all_officers.length; i++) {
//                        LeavePlanDaoImpl leaveDao = new LeavePlanDaoImpl();
//                        sqlParam = new Object[]{req.getSroOffice(), all_officers[i].getOfficerId(), formSdf.format(req.getAppointmentDate())};
//                        LeavePlan[] leaveplanDto = leaveDao.findByDynamicWhere("office_id=? and officer_id=? and ? between from_date and to_date", sqlParam);
//                        if (leaveplanDto.length == 0) {
//                            if (getNoOfAppointmentsPerOfficer(all_officers[i].getOfficerId(), req.getSroOffice(), formSdf.format(req.getAppointmentDate())) <= max_appointment) {
//                                avbl_officers.add(all_officers[i].getOfficerId());
//                            }
//                        }
//                    }
//                    
//                    
//                    // If there is noone assigned for that particular date then get the first officer
//                    if (officers.length == 0) {
//                        // work to do check if the appointment is today, then assign after 15 minutes of current time 
//                        req.setOfficerId(avbl_officers.get(0));
//                        Date ap_date = req.getAppointmentDate();
//                        try {
//                            // partha changes check appointment is for today                           
//                            String appointmentdate = formSdf.format(req.getAppointmentDate());
//                            String todaydate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                            String todaydateTime = new SimpleDateFormat("HH:mm:ss").format(new Date().getTime());
//                            cal.setTime(req.getAppointmentDate());
//                            if (appointmentdate.equals(todaydate)) {
//                                String[] parts = todaydateTime.split(":");
//                                int hour = Integer.parseInt(parts[0]);
//                                int minute = Integer.parseInt(parts[1]);
//                                cal.add(Calendar.HOUR, hour);
//                                cal.add(Calendar.MINUTE, minute + 15);
//                                cal.add(Calendar.SECOND, 00);
//                                } else {
//                                cal.add(Calendar.HOUR, 10);
//                                cal.add(Calendar.MINUTE, 00);
//                                cal.add(Calendar.SECOND, 00);
//                            }
//                            req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    } // If there is someone already assigned then get the very next available officer
//                    else {
//                        String appointmentDateString = formSdf.format(req.getAppointmentDate());
//                        String query = "sro_office=? and appointment_date like '" + appointmentDateString + "%' order by appointment_date DESC";
//                        sqlParam = new Object[]{req.getSroOffice()};
//                        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
//                        AppointmentSlotBooking[] appointments = rdao.findByDynamicWhere(query, sqlParam);
//
//                        // partha changes check appointment is for today                           
//                        String appointmentdate = formSdf.format(req.getAppointmentDate());
//                        String todaydate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                        String todaydateTime = new SimpleDateFormat("HH:mm:ss").format(new Date().getTime());
//                        cal.setTime(req.getAppointmentDate());
//
//                        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
//                        Date currenttime = dbSdf.parse(current);
//
//                        for (int i = 0; i < avbl_officers.size(); i++) {
//                            if (avbl_officers.get(i) == officers[0].getOfficer_id()) {
//                                officer_match = true;
//                                if (i == avbl_officers.size() - 1) {
//                                    req.setOfficerId(avbl_officers.get(0));
//                                    Date dbdatetime = getLastAppointmentDateTime(req.getSroOffice(), avbl_officers.get(0), req.getAppointmentDate());
//                                    if(dbdatetime!=null){check = dbdatetime.after(currenttime);}
//                                    if (appointmentdate.equals(todaydate) && getLastAppointmentDateTime(req.getSroOffice(), avbl_officers.get(0), req.getAppointmentDate()) == null) {
//
//                                        String[] parts = todaydateTime.split(":");
//                                        int hour = Integer.parseInt(parts[0]);
//                                        int minute = Integer.parseInt(parts[1]);
//                                        cal.add(Calendar.HOUR, hour);
//                                        cal.add(Calendar.MINUTE, minute + 15);
//                                        cal.add(Calendar.SECOND, 00);
//                                        req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//                                    }else if (check == false) {// check if the assign time is before the current time
//                                        cal.setTime(currenttime);
//                                        String[] parts = todaydateTime.split(":");
//                                        int hour = Integer.parseInt(parts[0]);
//                                        int minute = Integer.parseInt(parts[1]);
//                                        cal.add(Calendar.MINUTE, minute);
//                                        req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//                                    } else {
//                                        req.setAppointmentDate(getLastTImeSlotPerOfficer(req.getSroOffice(), avbl_officers.get(0), req.getAppointmentDate()));
//                                    }
//                                } else {
//                                    req.setOfficerId(avbl_officers.get(i + 1));
//                                    Date dbdatetime = getLastAppointmentDateTime(req.getSroOffice(), avbl_officers.get(i + 1), req.getAppointmentDate());
//                                    if(dbdatetime!=null){ check = dbdatetime.after(currenttime);}
//                                    if (appointmentdate.equals(todaydate) && getLastAppointmentDateTime(req.getSroOffice(), avbl_officers.get(i + 1), req.getAppointmentDate()) == null) {
//                                        String[] parts = todaydateTime.split(":");
//                                        int hour = Integer.parseInt(parts[0]);
//                                        int minute = Integer.parseInt(parts[1]);
//                                        cal.add(Calendar.HOUR, hour);
//                                        cal.add(Calendar.MINUTE, minute + 15);
//                                        cal.add(Calendar.SECOND, 00);
//                                        req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//
//                                    } else if (check == false) {
//                                        cal.setTime(currenttime);
//                                        String[] parts = todaydateTime.split(":");
//                                        int hour = Integer.parseInt(parts[0]);
//                                        int minute = Integer.parseInt(parts[1]);
////                                cal.add(Calendar.HOUR, hour);
//                                        cal.add(Calendar.MINUTE, minute);
//                                        req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//                                        } else {
//                                        req.setAppointmentDate(getLastTImeSlotPerOfficer(req.getSroOffice(), avbl_officers.get(i + 1), req.getAppointmentDate()));
//                                    }
//                                }
//                                break;
//                            }
//                        }
//                        // No appointment possible for the selected date
//                        if (avbl_officers.size() == 0) {
//                            return null;
//                        } else if (avbl_officers.size() > 0 && officer_match == false) {
//                            req.setOfficerId(avbl_officers.get(0));
//                            req.setAppointmentDate(getLastTImeSlotPerOfficer(req.getSroOffice(), avbl_officers.get(0), req.getAppointmentDate()));
//                        }
//                    }
//                } // if there is  only one officer
//                else {
//                    req.setOfficerId(all_officers[0].getOfficerId());
//                    String appointmentDateString = formSdf.format(req.getAppointmentDate());
//                    String query = "sro_office=? and appointment_date like '" + appointmentDateString + "%' order by appointment_date DESC";
//                    sqlParam = new Object[]{req.getSroOffice()};
//                    AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
//                    AppointmentSlotBooking[] appointments = rdao.findByDynamicWhere(query, sqlParam);
//                    if (appointments.length == 0) {
//                        try {
//                            // partha changes check appointment is for today                           
//                            String appointmentdate = formSdf.format(req.getAppointmentDate());
//                            String todaydate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                            String todaydateTime = new SimpleDateFormat("HH:mm:ss").format(new Date().getTime());
//                            cal.setTime(req.getAppointmentDate());
//
//                            if (appointmentdate.equals(todaydate)) {
//                                String[] parts = todaydateTime.split(":");
//                                int hour = Integer.parseInt(parts[0]);
//                                int minute = Integer.parseInt(parts[1]);
//                                cal.add(Calendar.HOUR, hour);
//                                cal.add(Calendar.MINUTE, minute + 15);
//                                cal.add(Calendar.SECOND, 00);
//                             } else {
//                                cal.add(Calendar.HOUR, 10);
//                                cal.add(Calendar.MINUTE, 00);
//                                cal.add(Calendar.SECOND, 00);
//                            }
//                            req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (appointments.length == max_appointment) {
//                        return null;
//                    } else {
//                        try {
//                            // check if the assign time is before the current time               
//                            Date dbdatetime = getLastAppointmentDateTime(req.getSroOffice(), all_officers[0].getOfficerId(), req.getAppointmentDate());
//                            String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
//                            String todaydateTime = new SimpleDateFormat("HH:mm:ss").format(new Date().getTime());
//                            Date currenttime = dbSdf.parse(current);
//                            if(dbdatetime!=null){check = dbdatetime.after(currenttime);}
//                            if (check == true) {
//                                cal.setTime(appointments[0].getAppointmentDate());
//                                cal.add(Calendar.MINUTE, 15);
//                                String newTime = dbSdf.format(cal.getTime());
//                                req.setAppointmentDate(dbSdf.parse(newTime));
//                               } else {
//                                cal.setTime(currenttime);
//                                String[] parts = todaydateTime.split(":");
//                                int hour = Integer.parseInt(parts[0]);
//                                int minute = Integer.parseInt(parts[1]);
////                                cal.add(Calendar.HOUR, hour);
//                                cal.add(Calendar.MINUTE, minute - 15);
//                                req.setAppointmentDate(dbSdf.parse(dbSdf.format(cal.getTime())));
//                                }
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//            }// last officer else part ends here 14-03-2016
//        } catch (LastOfficerAssignedDaoException ex) {
//            Logger.getLogger(AppointmentHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NodalOfficersDaoException ex) {
//            Logger.getLogger(AppointmentHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (LeavePlanDaoException ex) {
//            Logger.getLogger(AppointmentHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(AppointmentHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // Ends here
        AppointmentSlotBookingDaoImpl rDao = new AppointmentSlotBookingDaoImpl();
        try {
            rDao.insert(req);
            
            return_data.add(temp);
            return_data.add("NA");
        } catch (AppointmentSlotBookingDaoException ex) {
            //Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return return_data;
    }

    public String getVillageDropdown(String code, String village) {
        Vilcode[] vils = null;
        String options = null;

        code = code.substring(0, 8);
        VilcodeDaoImpl dao = new VilcodeDaoImpl();
        Object[] sqlParams = new Object[]{code + "%"};

        try {
            vils = dao.findByDynamicWhere("vlcode like ? and vlcode not like '%00000'", sqlParams);
            options = "<option value=''>Select Town/Village</option>";
            for (int j = 0; j < vils.length; j++) {

                if (vils[j].getVlcode().equals(village)) {
                    options = options + "<option value='" + vils[j].getVlcode() + "' selected='selected'>" + vils[j].getVlname() + "</option>";
                } else {
                    options = options + "<option value='" + vils[j].getVlcode() + "' >" + vils[j].getVlname() + "</option>";
                }
            }

        } catch (VilcodeDaoException ex) {
            Logger.getLogger(AppointmentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return options;
    }

    public int getNoOfRecords() throws AppointmentSlotBookingDaoException {
        String query = "select count(*) from appointment_slot_booking  ";
        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
        Integer num = rdao.CustomDynamicSelect(query, null);
        return num;
    }

    public int getNoOfAppointmentsPerOfficer(int officer_id, int office_id, String appointment_date) throws AppointmentSlotBookingDaoException {
        System.out.println("I am called");
        String query = "select count(officer_id) from appointment_slot_booking where sro_office=? and officer_id=? and appointment_date like '" + appointment_date + "%'";
        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
        Object[] sqlParams = new Object[]{office_id, officer_id};
        Integer num = rdao.CustomDynamicSelect(query, sqlParams);
        System.out.println("total count ...." + num);
        return num;
    }

    public ArrayList<String> getAppointmentInfo(int office_id, String appointment_id) throws AppointmentSlotBookingDaoException {
        String query = "appointment_id=?";
        ArrayList<String> appointment_info = new ArrayList<String>();
        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();
        Object[] sqlParams = new Object[]{appointment_id};
        System.out.println("From get info");
        AppointmentSlotBooking[] appointment = rdao.findByDynamicWhere(query, sqlParams);
        if(appointment==null){
            System.out.println("Null");
        }
        
        String[] appDateTime = appointment[0].getAppointmentDate().toString().split(" ");
        appointment_info.add(appDateTime[0]+" 10:00 AM");

        try {
            SroOfficesDaoImpl sroOfficesDao = new SroOfficesDaoImpl();
            query = "id=?";
            sqlParams = new Object[]{office_id};
            SroOffices[] offices = sroOfficesDao.findByDynamicWhere(query, sqlParams);
            appointment_info.add(offices[0].getOfficeName());
        } catch (SroOfficesDaoException e) {
            e.printStackTrace();
        }
        return appointment_info;
    }

    public String getNodalOfficerName(int office_id, int officer_id) throws NodalOfficersDaoException {
        NodalOfficersDaoImpl nodalDao = new NodalOfficersDaoImpl();
        Object[] sqlParam = new Object[]{office_id, officer_id};
        NodalOfficers[] officers = nodalDao.findByDynamicWhere("office_id=? and officer_id=?", sqlParam);
        return officers[0].getName();
    }

    public Date getLastTImeSlotPerOfficer(int office_id, int officer_id, Date appointmentDate) throws AppointmentSlotBookingDaoException {
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
        } catch (ParseException p) {
        }
        return null;
    }

    public static byte[] toByteArray(InputStream is)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();

        while (reads != -1) {
            baos.write(reads);
            reads = is.read();
        }

        return baos.toByteArray();
    }

    public void send_email(String userName, String emailId, String subject, String body) {
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
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailId));
            message.setSubject(subject);
            
            message.setContent("Dear " + userName + "," + body, "text/html");

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

// following code will return the appointment datetime of particular sro on particular date   
    public Date getLastAppointmentDateTime(int office_id, int officer_id, Date appointmentDate) throws AppointmentSlotBookingDaoException {
        DateFormat formSdf = new SimpleDateFormat("yyyy-MM-dd");
        String appointmentDateString = formSdf.format(appointmentDate);
        AppointmentSlotBookingDaoImpl rdao = new AppointmentSlotBookingDaoImpl();

        Object[] sqlParams = new Object[]{office_id, officer_id};
        String query = "sro_office=? and officer_id =? and appointment_date like '" + appointmentDateString + "%' order by slno DESC";
        AppointmentSlotBooking[] appointment = rdao.findByDynamicWhere(query, sqlParams);
        Calendar cal = Calendar.getInstance();
        DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (appointment.length > 0) {
                cal.setTime(appointment[0].getAppointmentDate());
                cal.add(Calendar.MINUTE, 15);
                String newTime = dbSdf.format(cal.getTime());
                return dbSdf.parse(newTime);
            } else {
                return null;
            }
        } catch (ParseException p) {
        }
        return null;
    }
    public ArrayList getAppointmentDates(String appointmentType) throws HolidayDaoException, OffDaysDaoException, AppointmentSlotBookingDaoException {
        int maxAppointments = 1;
        ArrayList dates = new ArrayList();
        for (int i = 0; i < 14; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, i + 1);

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String date = format1.format(calendar.getTime());
            
            AppointmentSlotBooking[] appointments = null;
            if(appointmentType.equals("deed")) {
                appointments = new AppointmentSlotBookingDaoImpl().findByDynamicWhere("DATE(appointment_date) = ? AND deed_type != 0", new Object[]{date});
            }
            else if (appointmentType.equals("marriage")) {
                appointments = new AppointmentSlotBookingDaoImpl().findByDynamicWhere("DATE(appointment_date) = ? AND deed_type = 0", new Object[]{date});
            }
            
            //Holiday[] holidays = (new HolidayDaoImpl()).findByDynamicWhere("date = '"+date+"'", new Object[]{0});
            Holiday[] holidays = (new HolidayDaoImpl()).findByDynamicWhere("date = ?", new Object[]{date});
            System.out.println("Holiday length "+holidays.length);
            OffDays[] offdays = (new OffDaysDaoImpl()).findByDynamicWhere("date = ?", new Object[]{date});
            System.out.println("Date :"+date);
            if( holidays.length == 0 && offdays.length == 0 && appointments.length < maxAppointments ) {
                SimpleDateFormat ddFormat = new SimpleDateFormat("dd-MM-yyyy");
                String ddDate = ddFormat.format(calendar.getTime());
                dates.add(ddDate); 
            }
        }
        return dates;
    }
}

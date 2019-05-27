/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.nic.epanjeeyan.dto.AppointmentDetails;
import org.nic.epanjeeyan.dto.AppointmentSlotBooking;
import org.nic.epanjeeyan.exceptions.AppointmentDetailsDaoException;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.jdbc.AppointmentDetailsDaoImpl;
import org.nic.epanjeeyan.jdbc.AppointmentSlotBookingDaoImpl;

/**
 *
 * @author Administrator
 */
public class UpcomingAppointmentsHandler {

    public AppointmentDetails[] pagination(Integer offset, Integer num, String checkForAppointmentDate) throws AppointmentSlotBookingDaoException, AppointmentDetailsDaoException {
        AppointmentSlotBookingDaoImpl dao = new AppointmentSlotBookingDaoImpl();
        AppointmentSlotBooking[] slotbooking = null;
        AppointmentDetailsDaoImpl appointmentDetailsDao = new AppointmentDetailsDaoImpl();
        AppointmentDetails[] appointmentDetailsDto = null;
        if (checkForAppointmentDate != null) {
            DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd");
            Date appointment_date = null;
            try {
                appointment_date = dbSdf.parse(dbSdf.format(formSdf.parse(checkForAppointmentDate)));
            } catch (ParseException e) {

            }
            appointmentDetailsDto = appointmentDetailsDao.execute(11, dbSdf.format(appointment_date) + "%");
            System.out.println("The new Dao is ..............." + appointmentDetailsDto.length);
            //slotbooking = dao.findByDynamicWhere("sro_office = 11 and appointment_date like '" + dbSdf.format(appointment_date) + "%'" + " ORDER BY slno limit " + num + " offset " + offset, null);
        } else {
            appointmentDetailsDto = appointmentDetailsDao.execute(11, "%");
            //slotbooking = dao.findByDynamicWhere("sro_office = 11 ORDER BY slno limit " + num + " offset " + offset, null);
        }
        System.out.println("sql query ok");
        return appointmentDetailsDto;
    }

    public int getNoOfRecords(String check_for_appointment_date) throws AppointmentSlotBookingDaoException {
        Integer num = 0;
        AppointmentSlotBookingDaoImpl dao = new AppointmentSlotBookingDaoImpl();
        if (check_for_appointment_date != null) {
            DateFormat formSdf = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd");
            Date appointment_date = null;
            try {
                appointment_date = dbSdf.parse(dbSdf.format(formSdf.parse(check_for_appointment_date)));
            } catch (ParseException e) {

            }
            String query = "select count(*) from appointment_slot_booking where sro_office = 11 and appointment_date like '" + dbSdf.format(appointment_date) + "%'";
            num = dao.CustomDynamicSelect(query, null);
        } else {
            String query = "select count(*) from appointment_slot_booking where sro_office = 11";
            num = dao.CustomDynamicSelect(query, null);
        }
        return num;
    }
}

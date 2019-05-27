/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.epanjeeyan;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nic.epanjeeyan.dto.AppointmentSlotBooking;
import org.nic.epanjeeyan.dto.AppointmentSlotBookingPk;
import org.nic.epanjeeyan.exceptions.AppointmentSlotBookingDaoException;
import org.nic.epanjeeyan.jdbc.AppointmentSlotBookingDaoImpl;

/**
 *
 * @author Administrator
 */
public class AppointmentApproveHandler {
    public void updateAppointmentApprove(AppointmentSlotBooking appointmentSlotBooking, String appointment_id) throws AppointmentSlotBookingDaoException {    
        AppointmentSlotBookingPk pk=new AppointmentSlotBookingPk();
        System.out.println("id is ----> "+appointment_id);
        pk.setAppointmentId(appointment_id);
        appointmentSlotBooking.setAppointmentId(appointment_id);
      //System.out.println("Hiiiiii");
        AppointmentSlotBookingDaoImpl appointmentSlotBookingDaoImpl = new AppointmentSlotBookingDaoImpl();
        appointmentSlotBookingDaoImpl.update(pk, appointmentSlotBooking);
    }
}





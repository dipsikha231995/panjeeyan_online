/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the appointment_slot_booking table.
 */
public class AppointmentSlotBookingPk implements Serializable
{
	protected String appointmentId;

	/** 
	 * Sets the value of appointmentId
	 */
	public void setAppointmentId(String appointmentId)
	{
		this.appointmentId = appointmentId;
	}

	/** 
	 * Gets the value of appointmentId
	 */
	public String getAppointmentId()
	{
		return appointmentId;
	}

	/**
	 * Method 'AppointmentSlotBookingPk'
	 * 
	 */
	public AppointmentSlotBookingPk()
	{
	}

	/**
	 * Method 'AppointmentSlotBookingPk'
	 * 
	 * @param appointmentId
	 */
	public AppointmentSlotBookingPk(final String appointmentId)
	{
		this.appointmentId = appointmentId;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof AppointmentSlotBookingPk)) {
			return false;
		}
		
		final AppointmentSlotBookingPk _cast = (AppointmentSlotBookingPk) _other;
		if (appointmentId == null ? _cast.appointmentId != appointmentId : !appointmentId.equals( _cast.appointmentId )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (appointmentId != null) {
			_hashCode = 29 * _hashCode + appointmentId.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "org.nic.epanjeeyan.dto.AppointmentSlotBookingPk: " );
		ret.append( "appointmentId=" + appointmentId );
		return ret.toString();
	}

}

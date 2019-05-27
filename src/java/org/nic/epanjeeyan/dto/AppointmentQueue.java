/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dto;

import org.nic.epanjeeyan.dao.*;
import org.nic.epanjeeyan.factory.*;
import org.nic.epanjeeyan.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class AppointmentQueue implements Serializable
{
	/** 
	 * This attribute maps to the column appointment_id in the appointment_queue table.
	 */
	protected String appointmentId;

	/** 
	 * This attribute maps to the column officer_id in the appointment_queue table.
	 */
	protected int officerId;

	/** 
	 * This attribute maps to the column appointment_date in the appointment_queue table.
	 */
	protected Date appointmentDate;

	/**
	 * Method 'AppointmentQueue'
	 * 
	 */
	public AppointmentQueue()
	{
	}

	/**
	 * Method 'getAppointmentId'
	 * 
	 * @return String
	 */
	public String getAppointmentId()
	{
		return appointmentId;
	}

	/**
	 * Method 'setAppointmentId'
	 * 
	 * @param appointmentId
	 */
	public void setAppointmentId(String appointmentId)
	{
		this.appointmentId = appointmentId;
	}

	/**
	 * Method 'getOfficerId'
	 * 
	 * @return int
	 */
	public int getOfficerId()
	{
		return officerId;
	}

	/**
	 * Method 'setOfficerId'
	 * 
	 * @param officerId
	 */
	public void setOfficerId(int officerId)
	{
		this.officerId = officerId;
	}

	/**
	 * Method 'getAppointmentDate'
	 * 
	 * @return Date
	 */
	public Date getAppointmentDate()
	{
		return appointmentDate;
	}

	/**
	 * Method 'setAppointmentDate'
	 * 
	 * @param appointmentDate
	 */
	public void setAppointmentDate(Date appointmentDate)
	{
		this.appointmentDate = appointmentDate;
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
		
		if (!(_other instanceof AppointmentQueue)) {
			return false;
		}
		
		final AppointmentQueue _cast = (AppointmentQueue) _other;
		if (appointmentId == null ? _cast.appointmentId != appointmentId : !appointmentId.equals( _cast.appointmentId )) {
			return false;
		}
		
		if (officerId != _cast.officerId) {
			return false;
		}
		
		if (appointmentDate == null ? _cast.appointmentDate != appointmentDate : !appointmentDate.equals( _cast.appointmentDate )) {
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
		
		_hashCode = 29 * _hashCode + officerId;
		if (appointmentDate != null) {
			_hashCode = 29 * _hashCode + appointmentDate.hashCode();
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
		ret.append( "org.nic.epanjeeyan.dto.AppointmentQueue: " );
		ret.append( "appointmentId=" + appointmentId );
		ret.append( ", officerId=" + officerId );
		ret.append( ", appointmentDate=" + appointmentDate );
		return ret.toString();
	}

}
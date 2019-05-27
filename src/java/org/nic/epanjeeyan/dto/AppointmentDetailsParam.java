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

public class AppointmentDetailsParam implements Serializable
{
	protected int officeId;

	/** 
	 * This attribute represents whether the primitive attribute officeId is null.
	 */
	protected boolean officeIdNull = true;

	protected java.lang.String appointmentDate;

	/**
	 * Method 'AppointmentDetailsParam'
	 * 
	 */
	public AppointmentDetailsParam()
	{
	}

	/**
	 * Method 'getOfficeId'
	 * 
	 * @return int
	 */
	public int getOfficeId()
	{
		return officeId;
	}

	/**
	 * Method 'setOfficeId'
	 * 
	 * @param officeId
	 */
	public void setOfficeId(int officeId)
	{
		this.officeId = officeId;
	}

	/** 
	 * Sets the value of officeIdNull
	 */
	public void setOfficeIdNull(boolean officeIdNull)
	{
		this.officeIdNull = officeIdNull;
	}

	/** 
	 * Gets the value of officeIdNull
	 */
	public boolean isOfficeIdNull()
	{
		return officeIdNull;
	}

	/**
	 * Method 'getAppointmentDate'
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getAppointmentDate()
	{
		return appointmentDate;
	}

	/**
	 * Method 'setAppointmentDate'
	 * 
	 * @param appointmentDate
	 */
	public void setAppointmentDate(java.lang.String appointmentDate)
	{
		this.appointmentDate = appointmentDate;
	}

}

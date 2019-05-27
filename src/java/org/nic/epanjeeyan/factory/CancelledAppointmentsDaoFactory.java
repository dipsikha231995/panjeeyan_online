/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.factory;

import java.sql.Connection;
import org.nic.epanjeeyan.dao.*;
import org.nic.epanjeeyan.jdbc.*;

public class CancelledAppointmentsDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return CancelledAppointmentsDao
	 */
	public static CancelledAppointmentsDao create()
	{
		return new CancelledAppointmentsDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return CancelledAppointmentsDao
	 */
	public static CancelledAppointmentsDao create(Connection conn)
	{
		return new CancelledAppointmentsDaoImpl( conn );
	}

}
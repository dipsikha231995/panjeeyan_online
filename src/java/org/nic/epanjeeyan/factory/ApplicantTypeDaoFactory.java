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

public class ApplicantTypeDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return ApplicantTypeDao
	 */
	public static ApplicantTypeDao create()
	{
		return new ApplicantTypeDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return ApplicantTypeDao
	 */
	public static ApplicantTypeDao create(Connection conn)
	{
		return new ApplicantTypeDaoImpl( conn );
	}

}
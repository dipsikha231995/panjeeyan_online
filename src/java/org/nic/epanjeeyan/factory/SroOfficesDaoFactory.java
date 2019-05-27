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

public class SroOfficesDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return SroOfficesDao
	 */
	public static SroOfficesDao create()
	{
		return new SroOfficesDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return SroOfficesDao
	 */
	public static SroOfficesDao create(Connection conn)
	{
		return new SroOfficesDaoImpl( conn );
	}

}

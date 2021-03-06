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

public class LandFeeDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return LandFeeDao
	 */
	public static LandFeeDao create()
	{
		return new LandFeeDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return LandFeeDao
	 */
	public static LandFeeDao create(Connection conn)
	{
		return new LandFeeDaoImpl( conn );
	}

}

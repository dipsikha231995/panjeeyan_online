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

public class VilcodeDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return VilcodeDao
	 */
	public static VilcodeDao create()
	{
		return new VilcodeDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return VilcodeDao
	 */
	public static VilcodeDao create(Connection conn)
	{
		return new VilcodeDaoImpl( conn );
	}

}

/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dao;

import org.nic.epanjeeyan.dto.*;
import org.nic.epanjeeyan.exceptions.*;

public interface PartytypeDao
{
	/** 
	 * Inserts a new row in the partytype table.
	 */
	public void insert(Partytype dto) throws PartytypeDaoException;

	/** 
	 * Returns all rows from the partytype table that match the criteria ''.
	 */
	public Partytype[] findAll() throws PartytypeDaoException;

	/** 
	 * Returns all rows from the partytype table that match the criteria 'type = :type'.
	 */
	public Partytype[] findWhereTypeEquals(String type) throws PartytypeDaoException;

	/** 
	 * Returns all rows from the partytype table that match the criteria 'first_or_sec = :firstOrSec'.
	 */
	public Partytype[] findWhereFirstOrSecEquals(String firstOrSec) throws PartytypeDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the partytype table that match the specified arbitrary SQL statement
	 */
	public Partytype[] findByDynamicSelect(String sql, Object[] sqlParams) throws PartytypeDaoException;

	/** 
	 * Returns all rows from the partytype table that match the specified arbitrary SQL statement
	 */
	public Partytype[] findByDynamicWhere(String sql, Object[] sqlParams) throws PartytypeDaoException;

}

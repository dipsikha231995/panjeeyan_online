/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.dao;

import java.util.Date;
import org.nic.epanjeeyan.dto.*;
import org.nic.epanjeeyan.exceptions.*;

public interface HolidayDao
{
	/** 
	 * Inserts a new row in the holiday table.
	 */
	public HolidayPk insert(Holiday dto) throws HolidayDaoException;

	/** 
	 * Updates a single row in the holiday table.
	 */
	public void update(HolidayPk pk, Holiday dto) throws HolidayDaoException;

	/** 
	 * Deletes a single row in the holiday table.
	 */
	public void delete(HolidayPk pk) throws HolidayDaoException;

	/** 
	 * Returns the rows from the holiday table that matches the specified primary-key value.
	 */
	public Holiday findByPrimaryKey(HolidayPk pk) throws HolidayDaoException;

	/** 
	 * Returns all rows from the holiday table that match the criteria 'id = :id'.
	 */
	public Holiday findByPrimaryKey(int id) throws HolidayDaoException;

	/** 
	 * Returns all rows from the holiday table that match the criteria ''.
	 */
	public Holiday[] findAll() throws HolidayDaoException;

	/** 
	 * Returns all rows from the holiday table that match the criteria 'id = :id'.
	 */
	public Holiday[] findWhereIdEquals(int id) throws HolidayDaoException;

	/** 
	 * Returns all rows from the holiday table that match the criteria 'date = :date'.
	 */
	public Holiday[] findWhereDateEquals(Date date) throws HolidayDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the holiday table that match the specified arbitrary SQL statement
	 */
	public Holiday[] findByDynamicSelect(String sql, Object[] sqlParams) throws HolidayDaoException;

	/** 
	 * Returns all rows from the holiday table that match the specified arbitrary SQL statement
	 */
	public Holiday[] findByDynamicWhere(String sql, Object[] sqlParams) throws HolidayDaoException;

}

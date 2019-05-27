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

public interface DeedtypeDao
{
	/** 
	 * Inserts a new row in the deedtype table.
	 */
	public DeedtypePk insert(Deedtype dto) throws DeedtypeDaoException;

	/** 
	 * Updates a single row in the deedtype table.
	 */
	public void update(DeedtypePk pk, Deedtype dto) throws DeedtypeDaoException;

	/** 
	 * Deletes a single row in the deedtype table.
	 */
	public void delete(DeedtypePk pk) throws DeedtypeDaoException;

	/** 
	 * Returns the rows from the deedtype table that matches the specified primary-key value.
	 */
	public Deedtype findByPrimaryKey(DeedtypePk pk) throws DeedtypeDaoException;

	/** 
	 * Returns all rows from the deedtype table that match the criteria 'code = :code'.
	 */
	public Deedtype findByPrimaryKey(int code) throws DeedtypeDaoException;

	/** 
	 * Returns all rows from the deedtype table that match the criteria ''.
	 */
	public Deedtype[] findAll() throws DeedtypeDaoException;

	/** 
	 * Returns all rows from the deedtype table that match the criteria 'code = :code'.
	 */
	public Deedtype[] findWhereCodeEquals(int code) throws DeedtypeDaoException;

	/** 
	 * Returns all rows from the deedtype table that match the criteria 'section = :section'.
	 */
	public Deedtype[] findWhereSectionEquals(String section) throws DeedtypeDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the deedtype table that match the specified arbitrary SQL statement
	 */
	public Deedtype[] findByDynamicSelect(String sql, Object[] sqlParams) throws DeedtypeDaoException;

	/** 
	 * Returns all rows from the deedtype table that match the specified arbitrary SQL statement
	 */
	public Deedtype[] findByDynamicWhere(String sql, Object[] sqlParams) throws DeedtypeDaoException;

}

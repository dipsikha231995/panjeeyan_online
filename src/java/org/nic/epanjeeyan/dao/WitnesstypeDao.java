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

public interface WitnesstypeDao
{
	/** 
	 * Inserts a new row in the witnesstype table.
	 */
	public WitnesstypePk insert(Witnesstype dto) throws WitnesstypeDaoException;

	/** 
	 * Updates a single row in the witnesstype table.
	 */
	public void update(WitnesstypePk pk, Witnesstype dto) throws WitnesstypeDaoException;

	/** 
	 * Deletes a single row in the witnesstype table.
	 */
	public void delete(WitnesstypePk pk) throws WitnesstypeDaoException;

	/** 
	 * Returns the rows from the witnesstype table that matches the specified primary-key value.
	 */
	public Witnesstype findByPrimaryKey(WitnesstypePk pk) throws WitnesstypeDaoException;

	/** 
	 * Returns all rows from the witnesstype table that match the criteria 'slno = :slno'.
	 */
	public Witnesstype findByPrimaryKey(int slno) throws WitnesstypeDaoException;

	/** 
	 * Returns all rows from the witnesstype table that match the criteria ''.
	 */
	public Witnesstype[] findAll() throws WitnesstypeDaoException;

	/** 
	 * Returns all rows from the witnesstype table that match the criteria 'slno = :slno'.
	 */
	public Witnesstype[] findWhereSlnoEquals(int slno) throws WitnesstypeDaoException;

	/** 
	 * Returns all rows from the witnesstype table that match the criteria 'witnesstype = :witnesstype'.
	 */
	public Witnesstype[] findWhereWitnesstypeEquals(String witnesstype) throws WitnesstypeDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the witnesstype table that match the specified arbitrary SQL statement
	 */
	public Witnesstype[] findByDynamicSelect(String sql, Object[] sqlParams) throws WitnesstypeDaoException;

	/** 
	 * Returns all rows from the witnesstype table that match the specified arbitrary SQL statement
	 */
	public Witnesstype[] findByDynamicWhere(String sql, Object[] sqlParams) throws WitnesstypeDaoException;

}

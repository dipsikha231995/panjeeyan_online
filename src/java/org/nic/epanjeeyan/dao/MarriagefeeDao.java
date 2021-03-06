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

public interface MarriagefeeDao
{
	/** 
	 * Inserts a new row in the marriagefee table.
	 */
	public void insert(Marriagefee dto) throws MarriagefeeDaoException;

	/** 
	 * Returns all rows from the marriagefee table that match the criteria ''.
	 */
	public Marriagefee[] findAll() throws MarriagefeeDaoException;

	/** 
	 * Returns all rows from the marriagefee table that match the criteria 'feeName = :feeName'.
	 */
	public Marriagefee[] findWhereFeeNameEquals(String feeName) throws MarriagefeeDaoException;

	/** 
	 * Returns all rows from the marriagefee table that match the criteria 'feeValue = :feeValue'.
	 */
	public Marriagefee[] findWhereFeeValueEquals(int feeValue) throws MarriagefeeDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the marriagefee table that match the specified arbitrary SQL statement
	 */
	public Marriagefee[] findByDynamicSelect(String sql, Object[] sqlParams) throws MarriagefeeDaoException;

	/** 
	 * Returns all rows from the marriagefee table that match the specified arbitrary SQL statement
	 */
	public Marriagefee[] findByDynamicWhere(String sql, Object[] sqlParams) throws MarriagefeeDaoException;

}

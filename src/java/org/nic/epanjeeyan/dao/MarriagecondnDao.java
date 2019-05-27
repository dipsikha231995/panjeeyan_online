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

public interface MarriagecondnDao
{
	/** 
	 * Inserts a new row in the marriagecondn table.
	 */
	public void insert(Marriagecondn dto) throws MarriagecondnDaoException;

	/** 
	 * Returns all rows from the marriagecondn table that match the criteria ''.
	 */
	public Marriagecondn[] findAll() throws MarriagecondnDaoException;

	/** 
	 * Returns all rows from the marriagecondn table that match the criteria 'marriageCondn = :marriageCondn'.
	 */
	public Marriagecondn[] findWhereMarriageCondnEquals(String marriageCondn) throws MarriagecondnDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the marriagecondn table that match the specified arbitrary SQL statement
	 */
	public Marriagecondn[] findByDynamicSelect(String sql, Object[] sqlParams) throws MarriagecondnDaoException;

	/** 
	 * Returns all rows from the marriagecondn table that match the specified arbitrary SQL statement
	 */
	public Marriagecondn[] findByDynamicWhere(String sql, Object[] sqlParams) throws MarriagecondnDaoException;

}

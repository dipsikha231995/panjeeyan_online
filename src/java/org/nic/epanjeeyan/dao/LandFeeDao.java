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

public interface LandFeeDao
{
	/** 
	 * Inserts a new row in the land_fee table.
	 */
	public void insert(LandFee dto) throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the criteria ''.
	 */
	public LandFee[] findAll() throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the criteria 'gender = :gender'.
	 */
	public LandFee[] findWhereGenderEquals(String gender) throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the criteria 'fee = :fee'.
	 */
	public LandFee[] findWhereFeeEquals(int fee) throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the criteria 'urban_mc = :urbanMc'.
	 */
	public LandFee[] findWhereUrbanMcEquals(int urbanMc) throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the criteria 'urban_mb = :urbanMb'.
	 */
	public LandFee[] findWhereUrbanMbEquals(int urbanMb) throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the criteria 'rural_fee = :ruralFee'.
	 */
	public LandFee[] findWhereRuralFeeEquals(int ruralFee) throws LandFeeDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the land_fee table that match the specified arbitrary SQL statement
	 */
	public LandFee[] findByDynamicSelect(String sql, Object[] sqlParams) throws LandFeeDaoException;

	/** 
	 * Returns all rows from the land_fee table that match the specified arbitrary SQL statement
	 */
	public LandFee[] findByDynamicWhere(String sql, Object[] sqlParams) throws LandFeeDaoException;

}

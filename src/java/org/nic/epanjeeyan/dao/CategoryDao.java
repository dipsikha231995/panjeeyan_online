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

public interface CategoryDao
{
	/** 
	 * Inserts a new row in the category table.
	 */
	public CategoryPk insert(Category dto) throws CategoryDaoException;

	/** 
	 * Updates a single row in the category table.
	 */
	public void update(CategoryPk pk, Category dto) throws CategoryDaoException;

	/** 
	 * Deletes a single row in the category table.
	 */
	public void delete(CategoryPk pk) throws CategoryDaoException;

	/** 
	 * Returns the rows from the category table that matches the specified primary-key value.
	 */
	public Category findByPrimaryKey(CategoryPk pk) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'code = :code AND sdcode = :sdcode'.
	 */
	public Category findByPrimaryKey(int code, int sdcode) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria ''.
	 */
	public Category[] findAll() throws CategoryDaoException;
public Category[] pageAll(Integer offset,Integer num) throws CategoryDaoException;
	/** 
	 * Returns all rows from the category table that match the criteria 'code = :code'.
	 */
	public Category[] findWhereCodeEquals(int code) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'deed_type = :deedType'.
	 */
	public Category[] findWhereDeedTypeEquals(String deedType) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'sdcode = :sdcode'.
	 */
	public Category[] findWhereSdcodeEquals(int sdcode) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'sub_deed_type = :subDeedType'.
	 */
	public Category[] findWhereSubDeedTypeEquals(String subDeedType) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'rfee = :rfee'.
	 */
	public Category[] findWhereRfeeEquals(int rfee) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'sfee = :sfee'.
	 */
	public Category[] findWhereSfeeEquals(int sfee) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'dcat = :dcat'.
	 */
	public Category[] findWhereDcatEquals(String dcat) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'act = :act'.
	 */
	public Category[] findWhereActEquals(String act) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'finindex = :finindex'.
	 */
	public Category[] findWhereFinindexEquals(String finindex) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'criteria = :criteria'.
	 */
	public Category[] findWhereCriteriaEquals(String criteria) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the criteria 'scndprtyreqrd = :scndprtyreqrd'.
	 */
	public Category[] findWhereScndprtyreqrdEquals(int scndprtyreqrd) throws CategoryDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public Category[] findByDynamicSelect(String sql, Object[] sqlParams) throws CategoryDaoException;

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public Category[] findByDynamicWhere(String sql, Object[] sqlParams) throws CategoryDaoException;

}
/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.jdbc;

import org.nic.epanjeeyan.dao.*;
import org.nic.epanjeeyan.factory.*;
import java.util.Date;
import org.nic.epanjeeyan.dto.*;
import org.nic.epanjeeyan.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class LeavePlanDaoImpl extends AbstractDAO implements LeavePlanDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT slno, office_id, officer_id, from_date, to_date FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( slno, office_id, officer_id, from_date, to_date ) VALUES ( ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET slno = ?, office_id = ?, officer_id = ?, from_date = ?, to_date = ? WHERE slno = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE slno = ?";

	/** 
	 * Index of column slno
	 */
	protected static final int COLUMN_SLNO = 1;

	/** 
	 * Index of column office_id
	 */
	protected static final int COLUMN_OFFICE_ID = 2;

	/** 
	 * Index of column officer_id
	 */
	protected static final int COLUMN_OFFICER_ID = 3;

	/** 
	 * Index of column from_date
	 */
	protected static final int COLUMN_FROM_DATE = 4;

	/** 
	 * Index of column to_date
	 */
	protected static final int COLUMN_TO_DATE = 5;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/** 
	 * Index of primary-key column slno
	 */
	protected static final int PK_COLUMN_SLNO = 1;

	/** 
	 * Inserts a new row in the leave_plan table.
	 */
	public LeavePlanPk insert(LeavePlan dto) throws LeavePlanDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			int index = 1;
			stmt.setInt( index++, dto.getSlno() );
			stmt.setInt( index++, dto.getOfficeId() );
			stmt.setInt( index++, dto.getOfficerId() );
			stmt.setDate(index++, dto.getFromDate()==null ? null : new java.sql.Date( dto.getFromDate().getTime() ) );
			stmt.setDate(index++, dto.getToDate()==null ? null : new java.sql.Date( dto.getToDate().getTime() ) );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		
			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				dto.setSlno( rs.getInt( 1 ) );
			}
		
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new LeavePlanDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the leave_plan table.
	 */
	public void update(LeavePlanPk pk, LeavePlan dto) throws LeavePlanDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + dto );
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setInt( index++, dto.getSlno() );
			stmt.setInt( index++, dto.getOfficeId() );
			stmt.setInt( index++, dto.getOfficerId() );
			stmt.setDate(index++, dto.getFromDate()==null ? null : new java.sql.Date( dto.getFromDate().getTime() ) );
			stmt.setDate(index++, dto.getToDate()==null ? null : new java.sql.Date( dto.getToDate().getTime() ) );
			stmt.setInt( 6, pk.getSlno() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new LeavePlanDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the leave_plan table.
	 */
	public void delete(LeavePlanPk pk) throws LeavePlanDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_DELETE + " with PK: " + pk );
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setInt( 1, pk.getSlno() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new LeavePlanDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the leave_plan table that matches the specified primary-key value.
	 */
	public LeavePlan findByPrimaryKey(LeavePlanPk pk) throws LeavePlanDaoException
	{
		return findByPrimaryKey( pk.getSlno() );
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria 'slno = :slno'.
	 */
	public LeavePlan findByPrimaryKey(int slno) throws LeavePlanDaoException
	{
		LeavePlan ret[] = findByDynamicSelect( SQL_SELECT + " WHERE slno = ?", new Object[] {  new Integer(slno) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria ''.
	 */
	public LeavePlan[] findAll() throws LeavePlanDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY slno", null );
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria 'slno = :slno'.
	 */
	public LeavePlan[] findWhereSlnoEquals(int slno) throws LeavePlanDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE slno = ? ORDER BY slno", new Object[] {  new Integer(slno) } );
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria 'office_id = :officeId'.
	 */
	public LeavePlan[] findWhereOfficeIdEquals(int officeId) throws LeavePlanDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE office_id = ? ORDER BY office_id", new Object[] {  new Integer(officeId) } );
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria 'officer_id = :officerId'.
	 */
	public LeavePlan[] findWhereOfficerIdEquals(int officerId) throws LeavePlanDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE officer_id = ? ORDER BY officer_id", new Object[] {  new Integer(officerId) } );
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria 'from_date = :fromDate'.
	 */
	public LeavePlan[] findWhereFromDateEquals(Date fromDate) throws LeavePlanDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE from_date = ? ORDER BY from_date", new Object[] { fromDate==null ? null : new java.sql.Date( fromDate.getTime() ) } );
	}

	/** 
	 * Returns all rows from the leave_plan table that match the criteria 'to_date = :toDate'.
	 */
	public LeavePlan[] findWhereToDateEquals(Date toDate) throws LeavePlanDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE to_date = ? ORDER BY to_date", new Object[] { toDate==null ? null : new java.sql.Date( toDate.getTime() ) } );
	}

	/**
	 * Method 'LeavePlanDaoImpl'
	 * 
	 */
	public LeavePlanDaoImpl()
	{
	}

	/**
	 * Method 'LeavePlanDaoImpl'
	 * 
	 * @param userConn
	 */
	public LeavePlanDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "leave_plan";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected LeavePlan fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			LeavePlan dto = new LeavePlan();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected LeavePlan[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			LeavePlan dto = new LeavePlan();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		LeavePlan ret[] = new LeavePlan[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(LeavePlan dto, ResultSet rs) throws SQLException
	{
		dto.setSlno( rs.getInt( COLUMN_SLNO ) );
		dto.setOfficeId( rs.getInt( COLUMN_OFFICE_ID ) );
		dto.setOfficerId( rs.getInt( COLUMN_OFFICER_ID ) );
		dto.setFromDate( rs.getDate(COLUMN_FROM_DATE ) );
		dto.setToDate( rs.getDate(COLUMN_TO_DATE ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(LeavePlan dto)
	{
	}

	/** 
	 * Returns all rows from the leave_plan table that match the specified arbitrary SQL statement
	 */
	public LeavePlan[] findByDynamicSelect(String sql, Object[] sqlParams) throws LeavePlanDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new LeavePlanDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the leave_plan table that match the specified arbitrary SQL statement
	 */
	public LeavePlan[] findByDynamicWhere(String sql, Object[] sqlParams) throws LeavePlanDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		        System.out.println("statement is leave ...." + stmt);

			rs = stmt.executeQuery();

			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new LeavePlanDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

}
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

public class OfficeTreasuryMappingDaoImpl extends AbstractDAO implements OfficeTreasuryMappingDao
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
	protected final String SQL_SELECT = "SELECT id, db_id, office_code, treasury_code FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( id, db_id, office_code, treasury_code ) VALUES ( ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET id = ?, db_id = ?, office_code = ?, treasury_code = ? WHERE id = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE id = ?";

	/** 
	 * Index of column id
	 */
	protected static final int COLUMN_ID = 1;

	/** 
	 * Index of column db_id
	 */
	protected static final int COLUMN_DB_ID = 2;

	/** 
	 * Index of column office_code
	 */
	protected static final int COLUMN_OFFICE_CODE = 3;

	/** 
	 * Index of column treasury_code
	 */
	protected static final int COLUMN_TREASURY_CODE = 4;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/** 
	 * Index of primary-key column id
	 */
	protected static final int PK_COLUMN_ID = 1;

	/** 
	 * Inserts a new row in the office_treasury_mapping table.
	 */
	public OfficeTreasuryMappingPk insert(OfficeTreasuryMapping dto) throws OfficeTreasuryMappingDaoException
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
			stmt.setInt( index++, dto.getId() );
			stmt.setInt( index++, dto.getDbId() );
			stmt.setString( index++, dto.getOfficeCode() );
			stmt.setString( index++, dto.getTreasuryCode() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		
			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				dto.setId( rs.getInt( 1 ) );
			}
		
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new OfficeTreasuryMappingDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the office_treasury_mapping table.
	 */
	public void update(OfficeTreasuryMappingPk pk, OfficeTreasuryMapping dto) throws OfficeTreasuryMappingDaoException
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
			stmt.setInt( index++, dto.getId() );
			stmt.setInt( index++, dto.getDbId() );
			stmt.setString( index++, dto.getOfficeCode() );
			stmt.setString( index++, dto.getTreasuryCode() );
			stmt.setInt( 5, pk.getId() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new OfficeTreasuryMappingDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the office_treasury_mapping table.
	 */
	public void delete(OfficeTreasuryMappingPk pk) throws OfficeTreasuryMappingDaoException
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
			stmt.setInt( 1, pk.getId() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new OfficeTreasuryMappingDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the office_treasury_mapping table that matches the specified primary-key value.
	 */
	public OfficeTreasuryMapping findByPrimaryKey(OfficeTreasuryMappingPk pk) throws OfficeTreasuryMappingDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the criteria 'id = :id'.
	 */
	public OfficeTreasuryMapping findByPrimaryKey(int id) throws OfficeTreasuryMappingDaoException
	{
		OfficeTreasuryMapping ret[] = findByDynamicSelect( SQL_SELECT + " WHERE id = ?", new Object[] {  new Integer(id) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the criteria ''.
	 */
	public OfficeTreasuryMapping[] findAll() throws OfficeTreasuryMappingDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY id", null );
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the criteria 'id = :id'.
	 */
	public OfficeTreasuryMapping[] findWhereIdEquals(int id) throws OfficeTreasuryMappingDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE id = ? ORDER BY id", new Object[] {  new Integer(id) } );
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the criteria 'db_id = :dbId'.
	 */
	public OfficeTreasuryMapping[] findWhereDbIdEquals(int dbId) throws OfficeTreasuryMappingDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE db_id = ? ORDER BY db_id", new Object[] {  new Integer(dbId) } );
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the criteria 'office_code = :officeCode'.
	 */
	public OfficeTreasuryMapping[] findWhereOfficeCodeEquals(String officeCode) throws OfficeTreasuryMappingDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE office_code = ? ORDER BY office_code", new Object[] { officeCode } );
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the criteria 'treasury_code = :treasuryCode'.
	 */
	public OfficeTreasuryMapping[] findWhereTreasuryCodeEquals(String treasuryCode) throws OfficeTreasuryMappingDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE treasury_code = ? ORDER BY treasury_code", new Object[] { treasuryCode } );
	}

	/**
	 * Method 'OfficeTreasuryMappingDaoImpl'
	 * 
	 */
	public OfficeTreasuryMappingDaoImpl()
	{
	}

	/**
	 * Method 'OfficeTreasuryMappingDaoImpl'
	 * 
	 * @param userConn
	 */
	public OfficeTreasuryMappingDaoImpl(final java.sql.Connection userConn)
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
		return "office_treasury_mapping";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected OfficeTreasuryMapping fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			OfficeTreasuryMapping dto = new OfficeTreasuryMapping();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected OfficeTreasuryMapping[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			OfficeTreasuryMapping dto = new OfficeTreasuryMapping();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		OfficeTreasuryMapping ret[] = new OfficeTreasuryMapping[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(OfficeTreasuryMapping dto, ResultSet rs) throws SQLException
	{
		dto.setId( rs.getInt( COLUMN_ID ) );
		dto.setDbId( rs.getInt( COLUMN_DB_ID ) );
		dto.setOfficeCode( rs.getString( COLUMN_OFFICE_CODE ) );
		dto.setTreasuryCode( rs.getString( COLUMN_TREASURY_CODE ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(OfficeTreasuryMapping dto)
	{
	}

	/** 
	 * Returns all rows from the office_treasury_mapping table that match the specified arbitrary SQL statement
	 */
	public OfficeTreasuryMapping[] findByDynamicSelect(String sql, Object[] sqlParams) throws OfficeTreasuryMappingDaoException
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
			throw new OfficeTreasuryMappingDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the office_treasury_mapping table that match the specified arbitrary SQL statement
	 */
	public OfficeTreasuryMapping[] findByDynamicWhere(String sql, Object[] sqlParams) throws OfficeTreasuryMappingDaoException
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
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new OfficeTreasuryMappingDaoException( "Exception: " + _e.getMessage(), _e );
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

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

public class MarriagecondnDaoImpl extends AbstractDAO implements MarriagecondnDao
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
	protected final String SQL_SELECT = "SELECT marriageCondn FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( marriageCondn ) VALUES ( ? )";

	/** 
	 * Index of column marriageCondn
	 */
	protected static final int COLUMN_MARRIAGE_CONDN = 1;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 1;

	/** 
	 * Inserts a new row in the marriagecondn table.
	 */
	public void insert(Marriagecondn dto) throws MarriagecondnDaoException
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
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			stmt.setString( index++, dto.getMarriageCondn() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MarriagecondnDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the marriagecondn table that match the criteria ''.
	 */
	public Marriagecondn[] findAll() throws MarriagecondnDaoException
	{
		return findByDynamicSelect( SQL_SELECT, null );
	}

	/** 
	 * Returns all rows from the marriagecondn table that match the criteria 'marriageCondn = :marriageCondn'.
	 */
	public Marriagecondn[] findWhereMarriageCondnEquals(String marriageCondn) throws MarriagecondnDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE marriageCondn = ? ORDER BY marriageCondn", new Object[] { marriageCondn } );
	}

	/**
	 * Method 'MarriagecondnDaoImpl'
	 * 
	 */
	public MarriagecondnDaoImpl()
	{
	}

	/**
	 * Method 'MarriagecondnDaoImpl'
	 * 
	 * @param userConn
	 */
	public MarriagecondnDaoImpl(final java.sql.Connection userConn)
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
		return "marriagecondn";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Marriagecondn fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Marriagecondn dto = new Marriagecondn();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Marriagecondn[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Marriagecondn dto = new Marriagecondn();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Marriagecondn ret[] = new Marriagecondn[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Marriagecondn dto, ResultSet rs) throws SQLException
	{
		dto.setMarriageCondn( rs.getString( COLUMN_MARRIAGE_CONDN ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Marriagecondn dto)
	{
	}

	/** 
	 * Returns all rows from the marriagecondn table that match the specified arbitrary SQL statement
	 */
	public Marriagecondn[] findByDynamicSelect(String sql, Object[] sqlParams) throws MarriagecondnDaoException
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
			throw new MarriagecondnDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the marriagecondn table that match the specified arbitrary SQL statement
	 */
	public Marriagecondn[] findByDynamicWhere(String sql, Object[] sqlParams) throws MarriagecondnDaoException
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
			throw new MarriagecondnDaoException( "Exception: " + _e.getMessage(), _e );
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

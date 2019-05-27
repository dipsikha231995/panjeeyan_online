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

public class WitnessDaoImpl extends AbstractDAO implements WitnessDao
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
	protected final String SQL_SELECT = "SELECT slno, comcaseno, namew, addrw, wtype, check_complete, witnesscheck_complete, identifiercheck_complete, draftercheck_complete, any_othercheck_complete, district_code, sro_code FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( slno, comcaseno, namew, addrw, wtype, check_complete, witnesscheck_complete, identifiercheck_complete, draftercheck_complete, any_othercheck_complete, district_code, sro_code ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET slno = ?, comcaseno = ?, namew = ?, addrw = ?, wtype = ?, check_complete = ?, witnesscheck_complete = ?, identifiercheck_complete = ?, draftercheck_complete = ?, any_othercheck_complete = ?, district_code = ?, sro_code = ? WHERE slno = ? AND comcaseno = ? AND wtype = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE slno = ? AND comcaseno = ? AND wtype = ?";

	/** 
	 * Index of column slno
	 */
	protected static final int COLUMN_SLNO = 1;

	/** 
	 * Index of column comcaseno
	 */
	protected static final int COLUMN_COMCASENO = 2;

	/** 
	 * Index of column namew
	 */
	protected static final int COLUMN_NAMEW = 3;

	/** 
	 * Index of column addrw
	 */
	protected static final int COLUMN_ADDRW = 4;

	/** 
	 * Index of column wtype
	 */
	protected static final int COLUMN_WTYPE = 5;

	/** 
	 * Index of column check_complete
	 */
	protected static final int COLUMN_CHECK_COMPLETE = 6;

	/** 
	 * Index of column witnesscheck_complete
	 */
	protected static final int COLUMN_WITNESSCHECK_COMPLETE = 7;

	/** 
	 * Index of column identifiercheck_complete
	 */
	protected static final int COLUMN_IDENTIFIERCHECK_COMPLETE = 8;

	/** 
	 * Index of column draftercheck_complete
	 */
	protected static final int COLUMN_DRAFTERCHECK_COMPLETE = 9;

	/** 
	 * Index of column any_othercheck_complete
	 */
	protected static final int COLUMN_ANY_OTHERCHECK_COMPLETE = 10;

	/** 
	 * Index of column district_code
	 */
	protected static final int COLUMN_DISTRICT_CODE = 11;

	/** 
	 * Index of column sro_code
	 */
	protected static final int COLUMN_SRO_CODE = 12;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 12;

	/** 
	 * Index of primary-key column slno
	 */
	protected static final int PK_COLUMN_SLNO = 1;

	/** 
	 * Index of primary-key column comcaseno
	 */
	protected static final int PK_COLUMN_COMCASENO = 2;

	/** 
	 * Index of primary-key column wtype
	 */
	protected static final int PK_COLUMN_WTYPE = 3;

	/** 
	 * Inserts a new row in the witness table.
	 */
	public WitnessPk insert(Witness dto) throws WitnessDaoException
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
			stmt.setInt( index++, dto.getSlno() );
			stmt.setString( index++, dto.getComcaseno() );
			stmt.setString( index++, dto.getNamew() );
			stmt.setString( index++, dto.getAddrw() );
			stmt.setString( index++, dto.getWtype() );
			stmt.setInt( index++, dto.getCheckComplete() );
			stmt.setInt( index++, dto.getWitnesscheckComplete() );
			stmt.setInt( index++, dto.getIdentifiercheckComplete() );
			stmt.setInt( index++, dto.getDraftercheckComplete() );
			stmt.setInt( index++, dto.getAnyOthercheckComplete() );
			stmt.setString( index++, dto.getDistrictCode() );
			stmt.setString( index++, dto.getSroCode() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the witness table.
	 */
	public void update(WitnessPk pk, Witness dto) throws WitnessDaoException
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
			stmt.setString( index++, dto.getComcaseno() );
			stmt.setString( index++, dto.getNamew() );
			stmt.setString( index++, dto.getAddrw() );
			stmt.setString( index++, dto.getWtype() );
			stmt.setInt( index++, dto.getCheckComplete() );
			stmt.setInt( index++, dto.getWitnesscheckComplete() );
			stmt.setInt( index++, dto.getIdentifiercheckComplete() );
			stmt.setInt( index++, dto.getDraftercheckComplete() );
			stmt.setInt( index++, dto.getAnyOthercheckComplete() );
			stmt.setString( index++, dto.getDistrictCode() );
			stmt.setString( index++, dto.getSroCode() );
			stmt.setInt( 13, pk.getSlno() );
			stmt.setString( 14, pk.getComcaseno() );
			stmt.setString( 15, pk.getWtype() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the witness table.
	 */
	public void delete(WitnessPk pk) throws WitnessDaoException
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
			stmt.setString( 2, pk.getComcaseno() );
			stmt.setString( 3, pk.getWtype() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the witness table that matches the specified primary-key value.
	 */
	public Witness findByPrimaryKey(WitnessPk pk) throws WitnessDaoException
	{
		return findByPrimaryKey( pk.getSlno(), pk.getComcaseno(), pk.getWtype() );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'slno = :slno AND comcaseno = :comcaseno AND wtype = :wtype'.
	 */
	public Witness findByPrimaryKey(int slno, String comcaseno, String wtype) throws WitnessDaoException
	{
		Witness ret[] = findByDynamicSelect( SQL_SELECT + " WHERE slno = ? AND comcaseno = ? AND wtype = ?", new Object[] {  new Integer(slno), comcaseno, wtype } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the witness table that match the criteria ''.
	 */
	public Witness[] findAll() throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY slno, comcaseno, wtype", null );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'slno = :slno'.
	 */
	public Witness[] findWhereSlnoEquals(int slno) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE slno = ? ORDER BY slno", new Object[] {  new Integer(slno) } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'comcaseno = :comcaseno'.
	 */
	public Witness[] findWhereComcasenoEquals(String comcaseno) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE comcaseno = ? ORDER BY comcaseno", new Object[] { comcaseno } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'namew = :namew'.
	 */
	public Witness[] findWhereNamewEquals(String namew) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE namew = ? ORDER BY namew", new Object[] { namew } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'addrw = :addrw'.
	 */
	public Witness[] findWhereAddrwEquals(String addrw) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE addrw = ? ORDER BY addrw", new Object[] { addrw } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'wtype = :wtype'.
	 */
	public Witness[] findWhereWtypeEquals(String wtype) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE wtype = ? ORDER BY wtype", new Object[] { wtype } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'check_complete = :checkComplete'.
	 */
	public Witness[] findWhereCheckCompleteEquals(int checkComplete) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE check_complete = ? ORDER BY check_complete", new Object[] {  new Integer(checkComplete) } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'witnesscheck_complete = :witnesscheckComplete'.
	 */
	public Witness[] findWhereWitnesscheckCompleteEquals(int witnesscheckComplete) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE witnesscheck_complete = ? ORDER BY witnesscheck_complete", new Object[] {  new Integer(witnesscheckComplete) } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'identifiercheck_complete = :identifiercheckComplete'.
	 */
	public Witness[] findWhereIdentifiercheckCompleteEquals(int identifiercheckComplete) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE identifiercheck_complete = ? ORDER BY identifiercheck_complete", new Object[] {  new Integer(identifiercheckComplete) } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'draftercheck_complete = :draftercheckComplete'.
	 */
	public Witness[] findWhereDraftercheckCompleteEquals(int draftercheckComplete) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE draftercheck_complete = ? ORDER BY draftercheck_complete", new Object[] {  new Integer(draftercheckComplete) } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'any_othercheck_complete = :anyOthercheckComplete'.
	 */
	public Witness[] findWhereAnyOthercheckCompleteEquals(int anyOthercheckComplete) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE any_othercheck_complete = ? ORDER BY any_othercheck_complete", new Object[] {  new Integer(anyOthercheckComplete) } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'district_code = :districtCode'.
	 */
	public Witness[] findWhereDistrictCodeEquals(String districtCode) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE district_code = ? ORDER BY district_code", new Object[] { districtCode } );
	}

	/** 
	 * Returns all rows from the witness table that match the criteria 'sro_code = :sroCode'.
	 */
	public Witness[] findWhereSroCodeEquals(String sroCode) throws WitnessDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE sro_code = ? ORDER BY sro_code", new Object[] { sroCode } );
	}

	/**
	 * Method 'WitnessDaoImpl'
	 * 
	 */
	public WitnessDaoImpl()
	{
	}

	/**
	 * Method 'WitnessDaoImpl'
	 * 
	 * @param userConn
	 */
	public WitnessDaoImpl(final java.sql.Connection userConn)
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
		return "witness";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Witness fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Witness dto = new Witness();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Witness[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Witness dto = new Witness();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Witness ret[] = new Witness[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Witness dto, ResultSet rs) throws SQLException
	{
		dto.setSlno( rs.getInt( COLUMN_SLNO ) );
		dto.setComcaseno( rs.getString( COLUMN_COMCASENO ) );
		dto.setNamew( rs.getString( COLUMN_NAMEW ) );
		dto.setAddrw( rs.getString( COLUMN_ADDRW ) );
		dto.setWtype( rs.getString( COLUMN_WTYPE ) );
		dto.setCheckComplete( rs.getInt( COLUMN_CHECK_COMPLETE ) );
		dto.setWitnesscheckComplete( rs.getInt( COLUMN_WITNESSCHECK_COMPLETE ) );
		dto.setIdentifiercheckComplete( rs.getInt( COLUMN_IDENTIFIERCHECK_COMPLETE ) );
		dto.setDraftercheckComplete( rs.getInt( COLUMN_DRAFTERCHECK_COMPLETE ) );
		dto.setAnyOthercheckComplete( rs.getInt( COLUMN_ANY_OTHERCHECK_COMPLETE ) );
		dto.setDistrictCode( rs.getString( COLUMN_DISTRICT_CODE ) );
		dto.setSroCode( rs.getString( COLUMN_SRO_CODE ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Witness dto)
	{
	}

	/** 
	 * Returns all rows from the witness table that match the specified arbitrary SQL statement
	 */
	public Witness[] findByDynamicSelect(String sql, Object[] sqlParams) throws WitnessDaoException
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
			throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the witness table that match the specified arbitrary SQL statement
	 */
	public Witness[] findByDynamicWhere(String sql, Object[] sqlParams) throws WitnessDaoException
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
			throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

        
         public String CustomDynamicSelect1(String sql, Object[] sqlParams) throws WitnessDaoException           {
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
                       
                        String ret=null;
                        while (rs.next()) {
                                ret = rs.getString(1);
                        }

                        return ret;
                       
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
       
        }
       
    }
  public Integer CustomDynamicSelect(String sql, Object[] sqlParams) throws WitnessDaoException           {
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
                       
                        Integer ret=null;
                        while (rs.next()) {
                                ret = rs.getInt(1);
                        }

                        return ret;
                       
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new WitnessDaoException( "Exception: " + _e.getMessage(), _e );
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
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

public class NonencumDaoImpl extends AbstractDAO implements NonencumDao
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
	protected final String SQL_SELECT = "SELECT srlno, slno, applno, appldate, nmappl, nmfather, addr, mcode, vlcode, record1, record2, refno, ref_yr, pattano, dagno, area, search, feeamt, issued, fee_type FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( srlno, slno, applno, appldate, nmappl, nmfather, addr, mcode, vlcode, record1, record2, refno, ref_yr, pattano, dagno, area, search, feeamt, issued, fee_type ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET srlno = ?, slno = ?, applno = ?, appldate = ?, nmappl = ?, nmfather = ?, addr = ?, mcode = ?, vlcode = ?, record1 = ?, record2 = ?, refno = ?, ref_yr = ?, pattano = ?, dagno = ?, area = ?, search = ?, feeamt = ?, issued = ?, fee_type = ? WHERE srlno = ? AND applno = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE srlno = ? AND applno = ?";

	/** 
	 * Index of column srlno
	 */
	protected static final int COLUMN_SRLNO = 1;

	/** 
	 * Index of column slno
	 */
	protected static final int COLUMN_SLNO = 2;

	/** 
	 * Index of column applno
	 */
	protected static final int COLUMN_APPLNO = 3;

	/** 
	 * Index of column appldate
	 */
	protected static final int COLUMN_APPLDATE = 4;

	/** 
	 * Index of column nmappl
	 */
	protected static final int COLUMN_NMAPPL = 5;

	/** 
	 * Index of column nmfather
	 */
	protected static final int COLUMN_NMFATHER = 6;

	/** 
	 * Index of column addr
	 */
	protected static final int COLUMN_ADDR = 7;

	/** 
	 * Index of column mcode
	 */
	protected static final int COLUMN_MCODE = 8;

	/** 
	 * Index of column vlcode
	 */
	protected static final int COLUMN_VLCODE = 9;

	/** 
	 * Index of column record1
	 */
	protected static final int COLUMN_RECORD1 = 10;

	/** 
	 * Index of column record2
	 */
	protected static final int COLUMN_RECORD2 = 11;

	/** 
	 * Index of column refno
	 */
	protected static final int COLUMN_REFNO = 12;

	/** 
	 * Index of column ref_yr
	 */
	protected static final int COLUMN_REF_YR = 13;

	/** 
	 * Index of column pattano
	 */
	protected static final int COLUMN_PATTANO = 14;

	/** 
	 * Index of column dagno
	 */
	protected static final int COLUMN_DAGNO = 15;

	/** 
	 * Index of column area
	 */
	protected static final int COLUMN_AREA = 16;

	/** 
	 * Index of column search
	 */
	protected static final int COLUMN_SEARCH = 17;

	/** 
	 * Index of column feeamt
	 */
	protected static final int COLUMN_FEEAMT = 18;

	/** 
	 * Index of column issued
	 */
	protected static final int COLUMN_ISSUED = 19;

	/** 
	 * Index of column fee_type
	 */
	protected static final int COLUMN_FEE_TYPE = 20;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 20;

	/** 
	 * Index of primary-key column srlno
	 */
	protected static final int PK_COLUMN_SRLNO = 1;

	/** 
	 * Index of primary-key column applno
	 */
	protected static final int PK_COLUMN_APPLNO = 2;

	/** 
	 * Inserts a new row in the nonencum table.
	 */
	public NonencumPk insert(Nonencum dto) throws NonencumDaoException
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
			stmt.setInt( index++, dto.getSrlno() );
			stmt.setInt( index++, dto.getSlno() );
			stmt.setString( index++, dto.getApplno() );
			stmt.setDate(index++, dto.getAppldate()==null ? null : new java.sql.Date( dto.getAppldate().getTime() ) );
			stmt.setString( index++, dto.getNmappl() );
			stmt.setString( index++, dto.getNmfather() );
			stmt.setString( index++, dto.getAddr() );
			stmt.setString( index++, dto.getMcode() );
			stmt.setString( index++, dto.getVlcode() );
			stmt.setDate(index++, dto.getRecord1()==null ? null : new java.sql.Date( dto.getRecord1().getTime() ) );
			stmt.setDate(index++, dto.getRecord2()==null ? null : new java.sql.Date( dto.getRecord2().getTime() ) );
			stmt.setString( index++, dto.getRefno() );
			stmt.setString( index++, dto.getRefYr() );
			stmt.setString( index++, dto.getPattano() );
			stmt.setString( index++, dto.getDagno() );
			stmt.setString( index++, dto.getArea() );
			stmt.setString( index++, dto.getSearch() );
			if (dto.isFeeamtNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getFeeamt() );
			}
		
			if (dto.isIssuedNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setShort( index++, dto.getIssued() );
			}
		
			stmt.setString( index++, dto.getFeeType() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new NonencumDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the nonencum table.
	 */
	public void update(NonencumPk pk, Nonencum dto) throws NonencumDaoException
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
			stmt.setInt( index++, dto.getSrlno() );
			stmt.setInt( index++, dto.getSlno() );
			stmt.setString( index++, dto.getApplno() );
			stmt.setDate(index++, dto.getAppldate()==null ? null : new java.sql.Date( dto.getAppldate().getTime() ) );
			stmt.setString( index++, dto.getNmappl() );
			stmt.setString( index++, dto.getNmfather() );
			stmt.setString( index++, dto.getAddr() );
			stmt.setString( index++, dto.getMcode() );
			stmt.setString( index++, dto.getVlcode() );
			stmt.setDate(index++, dto.getRecord1()==null ? null : new java.sql.Date( dto.getRecord1().getTime() ) );
			stmt.setDate(index++, dto.getRecord2()==null ? null : new java.sql.Date( dto.getRecord2().getTime() ) );
			stmt.setString( index++, dto.getRefno() );
			stmt.setString( index++, dto.getRefYr() );
			stmt.setString( index++, dto.getPattano() );
			stmt.setString( index++, dto.getDagno() );
			stmt.setString( index++, dto.getArea() );
			stmt.setString( index++, dto.getSearch() );
			if (dto.isFeeamtNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getFeeamt() );
			}
		
			if (dto.isIssuedNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setShort( index++, dto.getIssued() );
			}
		
			stmt.setString( index++, dto.getFeeType() );
			stmt.setInt( 21, pk.getSrlno() );
			stmt.setString( 22, pk.getApplno() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new NonencumDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the nonencum table.
	 */
	public void delete(NonencumPk pk) throws NonencumDaoException
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
			stmt.setInt( 1, pk.getSrlno() );
			stmt.setString( 2, pk.getApplno() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new NonencumDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the nonencum table that matches the specified primary-key value.
	 */
	public Nonencum findByPrimaryKey(NonencumPk pk) throws NonencumDaoException
	{
		return findByPrimaryKey( pk.getSrlno(), pk.getApplno() );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'srlno = :srlno AND applno = :applno'.
	 */
	public Nonencum findByPrimaryKey(int srlno, String applno) throws NonencumDaoException
	{
		Nonencum ret[] = findByDynamicSelect( SQL_SELECT + " WHERE srlno = ? AND applno = ?", new Object[] {  new Integer(srlno), applno } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria ''.
	 */
	public Nonencum[] findAll() throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY srlno, applno", null );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'srlno = :srlno'.
	 */
	public Nonencum[] findWhereSrlnoEquals(int srlno) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE srlno = ? ORDER BY srlno", new Object[] {  new Integer(srlno) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'slno = :slno'.
	 */
	public Nonencum[] findWhereSlnoEquals(int slno) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE slno = ? ORDER BY slno", new Object[] {  new Integer(slno) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'applno = :applno'.
	 */
	public Nonencum[] findWhereApplnoEquals(String applno) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE applno = ? ORDER BY applno", new Object[] { applno } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'appldate = :appldate'.
	 */
	public Nonencum[] findWhereAppldateEquals(Date appldate) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE appldate = ? ORDER BY appldate", new Object[] { appldate==null ? null : new java.sql.Date( appldate.getTime() ) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'nmappl = :nmappl'.
	 */
	public Nonencum[] findWhereNmapplEquals(String nmappl) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE nmappl = ? ORDER BY nmappl", new Object[] { nmappl } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'nmfather = :nmfather'.
	 */
	public Nonencum[] findWhereNmfatherEquals(String nmfather) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE nmfather = ? ORDER BY nmfather", new Object[] { nmfather } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'addr = :addr'.
	 */
	public Nonencum[] findWhereAddrEquals(String addr) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE addr = ? ORDER BY addr", new Object[] { addr } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'mcode = :mcode'.
	 */
	public Nonencum[] findWhereMcodeEquals(String mcode) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE mcode = ? ORDER BY mcode", new Object[] { mcode } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'vlcode = :vlcode'.
	 */
	public Nonencum[] findWhereVlcodeEquals(String vlcode) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE vlcode = ? ORDER BY vlcode", new Object[] { vlcode } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'record1 = :record1'.
	 */
	public Nonencum[] findWhereRecord1Equals(Date record1) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE record1 = ? ORDER BY record1", new Object[] { record1==null ? null : new java.sql.Date( record1.getTime() ) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'record2 = :record2'.
	 */
	public Nonencum[] findWhereRecord2Equals(Date record2) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE record2 = ? ORDER BY record2", new Object[] { record2==null ? null : new java.sql.Date( record2.getTime() ) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'refno = :refno'.
	 */
	public Nonencum[] findWhereRefnoEquals(String refno) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE refno = ? ORDER BY refno", new Object[] { refno } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'ref_yr = :refYr'.
	 */
	public Nonencum[] findWhereRefYrEquals(String refYr) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ref_yr = ? ORDER BY ref_yr", new Object[] { refYr } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'pattano = :pattano'.
	 */
	public Nonencum[] findWherePattanoEquals(String pattano) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE pattano = ? ORDER BY pattano", new Object[] { pattano } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'dagno = :dagno'.
	 */
	public Nonencum[] findWhereDagnoEquals(String dagno) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE dagno = ? ORDER BY dagno", new Object[] { dagno } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'area = :area'.
	 */
	public Nonencum[] findWhereAreaEquals(String area) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE area = ? ORDER BY area", new Object[] { area } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'search = :search'.
	 */
	public Nonencum[] findWhereSearchEquals(String search) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE search = ? ORDER BY search", new Object[] { search } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'feeamt = :feeamt'.
	 */
	public Nonencum[] findWhereFeeamtEquals(float feeamt) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE feeamt = ? ORDER BY feeamt", new Object[] {  new Float(feeamt) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'issued = :issued'.
	 */
	public Nonencum[] findWhereIssuedEquals(short issued) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE issued = ? ORDER BY issued", new Object[] {  new Short(issued) } );
	}

	/** 
	 * Returns all rows from the nonencum table that match the criteria 'fee_type = :feeType'.
	 */
	public Nonencum[] findWhereFeeTypeEquals(String feeType) throws NonencumDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE fee_type = ? ORDER BY fee_type", new Object[] { feeType } );
	}

	/**
	 * Method 'NonencumDaoImpl'
	 * 
	 */
	public NonencumDaoImpl()
	{
	}

	/**
	 * Method 'NonencumDaoImpl'
	 * 
	 * @param userConn
	 */
	public NonencumDaoImpl(final java.sql.Connection userConn)
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
		return "nonencum";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Nonencum fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Nonencum dto = new Nonencum();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Nonencum[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Nonencum dto = new Nonencum();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Nonencum ret[] = new Nonencum[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Nonencum dto, ResultSet rs) throws SQLException
	{
		dto.setSrlno( rs.getInt( COLUMN_SRLNO ) );
		dto.setSlno( rs.getInt( COLUMN_SLNO ) );
		dto.setApplno( rs.getString( COLUMN_APPLNO ) );
		dto.setAppldate( rs.getDate(COLUMN_APPLDATE ) );
		dto.setNmappl( rs.getString( COLUMN_NMAPPL ) );
		dto.setNmfather( rs.getString( COLUMN_NMFATHER ) );
		dto.setAddr( rs.getString( COLUMN_ADDR ) );
		dto.setMcode( rs.getString( COLUMN_MCODE ) );
		dto.setVlcode( rs.getString( COLUMN_VLCODE ) );
		dto.setRecord1( rs.getDate(COLUMN_RECORD1 ) );
		dto.setRecord2( rs.getDate(COLUMN_RECORD2 ) );
		dto.setRefno( rs.getString( COLUMN_REFNO ) );
		dto.setRefYr( rs.getString( COLUMN_REF_YR ) );
		dto.setPattano( rs.getString( COLUMN_PATTANO ) );
		dto.setDagno( rs.getString( COLUMN_DAGNO ) );
		dto.setArea( rs.getString( COLUMN_AREA ) );
		dto.setSearch( rs.getString( COLUMN_SEARCH ) );
		dto.setFeeamt( rs.getFloat( COLUMN_FEEAMT ) );
		if (rs.wasNull()) {
			dto.setFeeamtNull( true );
		}
		
		dto.setIssued( rs.getShort( COLUMN_ISSUED ) );
		if (rs.wasNull()) {
			dto.setIssuedNull( true );
		}
		
		dto.setFeeType( rs.getString( COLUMN_FEE_TYPE ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Nonencum dto)
	{
	}

	/** 
	 * Returns all rows from the nonencum table that match the specified arbitrary SQL statement
	 */
	public Nonencum[] findByDynamicSelect(String sql, Object[] sqlParams) throws NonencumDaoException
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
			throw new NonencumDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the nonencum table that match the specified arbitrary SQL statement
	 */
	public Nonencum[] findByDynamicWhere(String sql, Object[] sqlParams) throws NonencumDaoException
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
			throw new NonencumDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}
        
        public int customDynamicSelect2(String sql, Object[] sqlParams) throws NonencumDaoException {
            // declare variables
            final boolean isConnSupplied = (userConn != null);
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                
                conn = isConnSupplied ? userConn : ResourceManager.getConnection();
                final String SQL = sql;
                System.out.println( "Executing " + SQL );
                stmt = conn.prepareStatement( SQL );
                stmt.setMaxRows( maxRows );
                for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                    stmt.setObject( i+1, sqlParams[i] );
                }
                rs = stmt.executeQuery();
                int ret=0;
                while (rs.next()) {
                    ret = rs.getInt(1);
                }
               return ret;
            }
            catch (Exception _e) {
                _e.printStackTrace();
                throw new NonencumDaoException( "Exception: " + _e.getMessage(), _e );
            }
            finally {
                ResourceManager.close(rs);
                ResourceManager.close(stmt);
                if (!isConnSupplied) {
                    ResourceManager.close(conn);
                }

            }
       
        }
        // @ to do  generalize a selection query

}

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

public class PartyDaoImpl extends AbstractDAO implements PartyDao
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
	protected final String SQL_SELECT = "SELECT auto_id, slno, party_comcaseno, noofp, nameparty, noofpersons, fname, address, village, poff, pstat, district, state, partytype, check_complete, district_code, sro_code, firstPartyComplete, category FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( auto_id, slno, party_comcaseno, noofp, nameparty, noofpersons, fname, address, village, poff, pstat, district, state, partytype, check_complete, district_code, sro_code, firstPartyComplete, category ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	//protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET auto_id = ?, slno = ?, party_comcaseno = ?, noofp = ?, nameparty = ?, noofpersons = ?, fname = ?, address = ?, village = ?, poff = ?, pstat = ?, district = ?, state = ?, partytype = ?, check_complete = ?, district_code = ?, sro_code = ?, firstPartyComplete = ?, category = ? WHERE slno = ? AND party_comcaseno = ? AND partytype = ?";
        /*
         * 
         */ 
        // @ to do update for party changes
        protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET auto_id = ?,slno = ?, party_comcaseno = ?, noofp = ?, nameparty = ?, noofpersons = ?, fname = ?, address = ?, village = ?, poff = ?, pstat = ?, district = ?, state = ?, partytype = ?, check_complete = ?, district_code = ?, sro_code = ?, firstPartyComplete = ?, category = ? WHERE slno = ? AND party_comcaseno = ? AND partytype = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE slno = ? AND party_comcaseno = ? AND partytype = ?";

	/** 
	 * Index of column auto_id
	 */
	protected static final int COLUMN_AUTO_ID = 1;

	/** 
	 * Index of column slno
	 */
	protected static final int COLUMN_SLNO = 2;

	/** 
	 * Index of column party_comcaseno
	 */
	protected static final int COLUMN_PARTY_COMCASENO = 3;

	/** 
	 * Index of column noofp
	 */
	protected static final int COLUMN_NOOFP = 4;

	/** 
	 * Index of column nameparty
	 */
	protected static final int COLUMN_NAMEPARTY = 5;

	/** 
	 * Index of column noofpersons
	 */
	protected static final int COLUMN_NOOFPERSONS = 6;

	/** 
	 * Index of column fname
	 */
	protected static final int COLUMN_FNAME = 7;

	/** 
	 * Index of column address
	 */
	protected static final int COLUMN_ADDRESS = 8;

	/** 
	 * Index of column village
	 */
	protected static final int COLUMN_VILLAGE = 9;

	/** 
	 * Index of column poff
	 */
	protected static final int COLUMN_POFF = 10;

	/** 
	 * Index of column pstat
	 */
	protected static final int COLUMN_PSTAT = 11;

	/** 
	 * Index of column district
	 */
	protected static final int COLUMN_DISTRICT = 12;

	/** 
	 * Index of column state
	 */
	protected static final int COLUMN_STATE = 13;

	/** 
	 * Index of column partytype
	 */
	protected static final int COLUMN_PARTYTYPE = 14;

	/** 
	 * Index of column check_complete
	 */
	protected static final int COLUMN_CHECK_COMPLETE = 15;

	/** 
	 * Index of column district_code
	 */
	protected static final int COLUMN_DISTRICT_CODE = 16;

	/** 
	 * Index of column sro_code
	 */
	protected static final int COLUMN_SRO_CODE = 17;

	/** 
	 * Index of column firstPartyComplete
	 */
	protected static final int COLUMN_FIRST_PARTY_COMPLETE = 18;

	/** 
	 * Index of column category
	 */
	protected static final int COLUMN_CATEGORY = 19;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 19;

	/** 
	 * Index of primary-key column slno
	 */
	protected static final int PK_COLUMN_SLNO = 1;

	/** 
	 * Index of primary-key column party_comcaseno
	 */
	protected static final int PK_COLUMN_PARTY_COMCASENO = 2;

	/** 
	 * Index of primary-key column partytype
	 */
	protected static final int PK_COLUMN_PARTYTYPE = 3;

	/** 
	 * Inserts a new row in the party table.
	 */
	public PartyPk insert(Party dto) throws PartyDaoException
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
			if (dto.getAutoId() != null) {
				stmt.setLong( index++, dto.getAutoId().longValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.BIGINT);
			}
		
			if (dto.getSlno() != null) {
				stmt.setInt( index++, dto.getSlno().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			stmt.setString( index++, dto.getPartyComcaseno() );
			if (dto.getNoofp() != null) {
				stmt.setInt( index++, dto.getNoofp().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			stmt.setString( index++, dto.getNameparty() );
			if (dto.getNoofpersons() != null) {
				stmt.setInt( index++, dto.getNoofpersons().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			stmt.setString( index++, dto.getFname() );
			stmt.setString( index++, dto.getAddress() );
			stmt.setString( index++, dto.getVillage() );
			stmt.setString( index++, dto.getPoff() );
			stmt.setString( index++, dto.getPstat() );
			stmt.setString( index++, dto.getDistrict() );
			stmt.setString( index++, dto.getState() );
			stmt.setString( index++, dto.getPartytype() );
			if (dto.getCheckComplete() != null) {
				stmt.setShort( index++, dto.getCheckComplete().shortValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.SMALLINT);
			}
		
			stmt.setString( index++, dto.getDistrictCode() );
			stmt.setString( index++, dto.getSroCode() );
			if (dto.getFirstPartyComplete() != null) {
				stmt.setShort( index++, dto.getFirstPartyComplete().shortValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.SMALLINT);
			}
		
			if (dto.getCategory() != null) {
				stmt.setInt( index++, dto.getCategory().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		
			// retrieve values from auto-increment columns
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				dto.setAutoId( new Long( rs.getLong(1) ) );
			}
		
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new PartyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the party table.
	 */
	public void update(PartyPk pk, Party dto) throws PartyDaoException
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
			if (dto.getAutoId() != null) {
				stmt.setLong( index++, dto.getAutoId().longValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.BIGINT);
			}
		
			if (dto.getSlno() != null) {
				stmt.setInt( index++, dto.getSlno().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			stmt.setString( index++, dto.getPartyComcaseno() );
			if (dto.getNoofp() != null) {
				stmt.setInt( index++, dto.getNoofp().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			stmt.setString( index++, dto.getNameparty() );
			if (dto.getNoofpersons() != null) {
				stmt.setInt( index++, dto.getNoofpersons().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			stmt.setString( index++, dto.getFname() );
			stmt.setString( index++, dto.getAddress() );
			stmt.setString( index++, dto.getVillage() );
			stmt.setString( index++, dto.getPoff() );
			stmt.setString( index++, dto.getPstat() );
			stmt.setString( index++, dto.getDistrict() );
			stmt.setString( index++, dto.getState() );
			stmt.setString( index++, dto.getPartytype() );
			if (dto.getCheckComplete() != null) {
				stmt.setShort( index++, dto.getCheckComplete().shortValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.SMALLINT);
			}
		
			stmt.setString( index++, dto.getDistrictCode() );
			stmt.setString( index++, dto.getSroCode() );
			if (dto.getFirstPartyComplete() != null) {
				stmt.setShort( index++, dto.getFirstPartyComplete().shortValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.SMALLINT);
			}
		
			if (dto.getCategory() != null) {
				stmt.setInt( index++, dto.getCategory().intValue() );
			} else {
				stmt.setNull(index++, java.sql.Types.INTEGER);
			}
		
			if (pk.getSlno() != null) {
				stmt.setInt( 20, pk.getSlno().intValue() );
			} else {
				stmt.setNull(20, java.sql.Types.INTEGER);
			}
		
			stmt.setString( 21, pk.getPartyComcaseno() );
			stmt.setString( 22, pk.getPartytype() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new PartyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the party table.
	 */
	public void delete(PartyPk pk) throws PartyDaoException
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
			if (pk.getSlno() != null) {
				stmt.setInt( 1, pk.getSlno().intValue() );
			} else {
				stmt.setNull(1, java.sql.Types.INTEGER);
			}
		
			stmt.setString( 2, pk.getPartyComcaseno() );
			stmt.setString( 3, pk.getPartytype() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new PartyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the party table that matches the specified primary-key value.
	 */
	public Party findByPrimaryKey(PartyPk pk) throws PartyDaoException
	{
		return findByPrimaryKey( pk.getSlno(), pk.getPartyComcaseno(), pk.getPartytype() );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'slno = :slno AND party_comcaseno = :partyComcaseno AND partytype = :partytype'.
	 */
	public Party findByPrimaryKey(Integer slno, String partyComcaseno, String partytype) throws PartyDaoException
	{
		Party ret[] = findByDynamicSelect( SQL_SELECT + " WHERE slno = ? AND party_comcaseno = ? AND partytype = ?", new Object[] { slno, partyComcaseno, partytype } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the party table that match the criteria ''.
	 */
	public Party[] findAll() throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY slno, party_comcaseno, partytype", null );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'auto_id = :autoId'.
	 */
	public Party[] findWhereAutoIdEquals(Long autoId) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE auto_id = ? ORDER BY auto_id", new Object[] { autoId } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'slno = :slno'.
	 */
	public Party[] findWhereSlnoEquals(Integer slno) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE slno = ? ORDER BY slno", new Object[] { slno } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'party_comcaseno = :partyComcaseno'.
	 */
	public Party[] findWherePartyComcasenoEquals(String partyComcaseno) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE party_comcaseno = ? ORDER BY party_comcaseno", new Object[] { partyComcaseno } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'noofp = :noofp'.
	 */
	public Party[] findWhereNoofpEquals(Integer noofp) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE noofp = ? ORDER BY noofp", new Object[] { noofp } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'nameparty = :nameparty'.
	 */
	public Party[] findWhereNamepartyEquals(String nameparty) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE nameparty = ? ORDER BY nameparty", new Object[] { nameparty } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'noofpersons = :noofpersons'.
	 */
	public Party[] findWhereNoofpersonsEquals(Integer noofpersons) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE noofpersons = ? ORDER BY noofpersons", new Object[] { noofpersons } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'fname = :fname'.
	 */
	public Party[] findWhereFnameEquals(String fname) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE fname = ? ORDER BY fname", new Object[] { fname } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'address = :address'.
	 */
	public Party[] findWhereAddressEquals(String address) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE address = ? ORDER BY address", new Object[] { address } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'village = :village'.
	 */
	public Party[] findWhereVillageEquals(String village) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE village = ? ORDER BY village", new Object[] { village } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'poff = :poff'.
	 */
	public Party[] findWherePoffEquals(String poff) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE poff = ? ORDER BY poff", new Object[] { poff } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'pstat = :pstat'.
	 */
	public Party[] findWherePstatEquals(String pstat) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE pstat = ? ORDER BY pstat", new Object[] { pstat } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'district = :district'.
	 */
	public Party[] findWhereDistrictEquals(String district) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE district = ? ORDER BY district", new Object[] { district } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'state = :state'.
	 */
	public Party[] findWhereStateEquals(String state) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE state = ? ORDER BY state", new Object[] { state } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'partytype = :partytype'.
	 */
	public Party[] findWherePartytypeEquals(String partytype) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE partytype = ? ORDER BY partytype", new Object[] { partytype } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'check_complete = :checkComplete'.
	 */
	public Party[] findWhereCheckCompleteEquals(Short checkComplete) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE check_complete = ? ORDER BY check_complete", new Object[] { checkComplete } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'district_code = :districtCode'.
	 */
	public Party[] findWhereDistrictCodeEquals(String districtCode) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE district_code = ? ORDER BY district_code", new Object[] { districtCode } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'sro_code = :sroCode'.
	 */
	public Party[] findWhereSroCodeEquals(String sroCode) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE sro_code = ? ORDER BY sro_code", new Object[] { sroCode } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'firstPartyComplete = :firstPartyComplete'.
	 */
	public Party[] findWhereFirstPartyCompleteEquals(Short firstPartyComplete) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE firstPartyComplete = ? ORDER BY firstPartyComplete", new Object[] { firstPartyComplete } );
	}

	/** 
	 * Returns all rows from the party table that match the criteria 'category = :category'.
	 */
	public Party[] findWhereCategoryEquals(Integer category) throws PartyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE category = ? ORDER BY category", new Object[] { category } );
	}

	/**
	 * Method 'PartyDaoImpl'
	 * 
	 */
	public PartyDaoImpl()
	{
	}

	/**
	 * Method 'PartyDaoImpl'
	 * 
	 * @param userConn
	 */
	public PartyDaoImpl(final java.sql.Connection userConn)
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
		return "epanjeeyan_new.party";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Party fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Party dto = new Party();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Party[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Party dto = new Party();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Party ret[] = new Party[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Party dto, ResultSet rs) throws SQLException
	{
		dto.setAutoId( new Long( rs.getLong(COLUMN_AUTO_ID) ) );
		dto.setSlno( new Integer( rs.getInt(COLUMN_SLNO) ) );
		dto.setPartyComcaseno( rs.getString( COLUMN_PARTY_COMCASENO ) );
		dto.setNoofp( new Integer( rs.getInt(COLUMN_NOOFP) ) );
		dto.setNameparty( rs.getString( COLUMN_NAMEPARTY ) );
		dto.setNoofpersons( new Integer( rs.getInt(COLUMN_NOOFPERSONS) ) );
		dto.setFname( rs.getString( COLUMN_FNAME ) );
		dto.setAddress( rs.getString( COLUMN_ADDRESS ) );
		dto.setVillage( rs.getString( COLUMN_VILLAGE ) );
		dto.setPoff( rs.getString( COLUMN_POFF ) );
		dto.setPstat( rs.getString( COLUMN_PSTAT ) );
		dto.setDistrict( rs.getString( COLUMN_DISTRICT ) );
		dto.setState( rs.getString( COLUMN_STATE ) );
		dto.setPartytype( rs.getString( COLUMN_PARTYTYPE ) );
		dto.setCheckComplete( new Short( rs.getShort(COLUMN_CHECK_COMPLETE) ) );
		if (rs.wasNull()) {
			dto.setCheckComplete( null );
		}
		
		dto.setDistrictCode( rs.getString( COLUMN_DISTRICT_CODE ) );
		dto.setSroCode( rs.getString( COLUMN_SRO_CODE ) );
		dto.setFirstPartyComplete( new Short( rs.getShort(COLUMN_FIRST_PARTY_COMPLETE) ) );
		if (rs.wasNull()) {
			dto.setFirstPartyComplete( null );
		}
		
		dto.setCategory( new Integer( rs.getInt(COLUMN_CATEGORY) ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Party dto)
	{
	}

	/** 
	 * Returns all rows from the party table that match the specified arbitrary SQL statement
	 */
	public Party[] findByDynamicSelect(String sql, Object[] sqlParams) throws PartyDaoException
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
			throw new PartyDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the party table that match the specified arbitrary SQL statement
	 */
	public Party[] findByDynamicWhere(String sql, Object[] sqlParams) throws PartyDaoException
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
			throw new PartyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}
        // @ to do write customdynamicselect fn
        public Integer CustomDynamicSelect(String sql, Object[] sqlParams) throws PartyDaoException           {
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
            throw new PartyDaoException( "Exception: " + _e.getMessage(), _e );
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
       
        }
       
    }     

// @to do ends

}
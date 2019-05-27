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

public class RenquiryDaoImpl extends AbstractDAO implements RenquiryDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;
	protected final String SQL_SELECT = "SELECT slno, tmpcaseno, applicant_name, area_type, enquiry_date, deed_type, deed_subtype, consideration_amount, registration_fee, stamp_duty, document_to_be_furnished, doc_subject, whether_land, gender, act FROM " + getTableName() + "";
	protected int maxRows;
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( slno, tmpcaseno, applicant_name, area_type, enquiry_date, deed_type, deed_subtype, consideration_amount, registration_fee, stamp_duty, document_to_be_furnished, doc_subject, whether_land, gender, act ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET slno = ?, tmpcaseno = ?, applicant_name = ?, area_type = ?, enquiry_date = ?, deed_type = ?, deed_subtype = ?, consideration_amount = ?, registration_fee = ?, stamp_duty = ?, document_to_be_furnished = ?, doc_subject = ?, whether_land = ?, gender = ?, act = ? WHERE tmpcaseno = ?";
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE tmpcaseno = ?";
	protected static final int COLUMN_SLNO = 1;
	protected static final int COLUMN_TMPCASENO = 2;
	protected static final int COLUMN_APPLICANT_NAME = 3;
	protected static final int COLUMN_AREA_TYPE = 4;
	protected static final int COLUMN_ENQUIRY_DATE = 5;
	protected static final int COLUMN_DEED_TYPE = 6;
	protected static final int COLUMN_DEED_SUBTYPE = 7;
	protected static final int COLUMN_CONSIDERATION_AMOUNT = 8;
	protected static final int COLUMN_REGISTRATION_FEE = 9;
	protected static final int COLUMN_STAMP_DUTY = 10;
	protected static final int COLUMN_DOCUMENT_TO_BE_FURNISHED = 11;
	protected static final int COLUMN_DOC_SUBJECT = 12;
	protected static final int COLUMN_WHETHER_LAND = 13;
	protected static final int COLUMN_GENDER = 14;
	protected static final int COLUMN_ACT = 15;
	protected static final int NUMBER_OF_COLUMNS = 15;
	protected static final int PK_COLUMN_TMPCASENO = 1;
	public RenquiryPk insert(Renquiry dto) throws RenquiryDaoException
	{
		long t1 = System.currentTimeMillis();
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			if (dto.isSlnoNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getSlno() );
			}
		
			stmt.setString( index++, dto.getTmpcaseno() );
			stmt.setString( index++, dto.getApplicantName() );
			stmt.setString( index++, dto.getAreaType() );
			stmt.setDate(index++, dto.getEnquiryDate()==null ? null : new java.sql.Date( dto.getEnquiryDate().getTime() ) );
                        
			if (dto.isDeedTypeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getDeedType() );
			}
		
			stmt.setString( index++, dto.getDeedSubtype() );
			if (dto.isConsiderationAmountNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getConsiderationAmount() );
			}
		
			if (dto.isRegistrationFeeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getRegistrationFee() );
			}
		
			if (dto.isStampDutyNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getStampDuty() );
			}
		
			stmt.setString( index++, dto.getDocumentToBeFurnished() );
			stmt.setString( index++, dto.getDocSubject() );
			stmt.setInt( index++, dto.getWhetherLand() );
			stmt.setString( index++, dto.getGender() );
			stmt.setString( index++, dto.getAct() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the renquiry table.
	 */
	public void update(RenquiryPk pk, Renquiry dto) throws RenquiryDaoException
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
			if (dto.isSlnoNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getSlno() );
			}
		
			stmt.setString( index++, dto.getTmpcaseno() );
			stmt.setString( index++, dto.getApplicantName() );
			stmt.setString( index++, dto.getAreaType() );
			stmt.setDate(index++, dto.getEnquiryDate()==null ? null : new java.sql.Date( dto.getEnquiryDate().getTime() ) );
			if (dto.isDeedTypeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getDeedType() );
			}
		
			stmt.setString( index++, dto.getDeedSubtype() );
			if (dto.isConsiderationAmountNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getConsiderationAmount() );
			}
		
			if (dto.isRegistrationFeeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getRegistrationFee() );
			}
		
			if (dto.isStampDutyNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getStampDuty() );
			}
		
			stmt.setString( index++, dto.getDocumentToBeFurnished() );
			stmt.setString( index++, dto.getDocSubject() );
			stmt.setInt( index++, dto.getWhetherLand() );
			stmt.setString( index++, dto.getGender() );
			stmt.setString( index++, dto.getAct() );
			stmt.setString( 16, pk.getTmpcaseno() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the renquiry table.
	 */
	public void delete(RenquiryPk pk) throws RenquiryDaoException
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
			stmt.setString( 1, pk.getTmpcaseno() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the renquiry table that matches the specified primary-key value.
	 */
	public Renquiry findByPrimaryKey(RenquiryPk pk) throws RenquiryDaoException
	{
		return findByPrimaryKey( pk.getTmpcaseno() );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'tmpcaseno = :tmpcaseno'.
	 */
	public Renquiry findByPrimaryKey(String tmpcaseno) throws RenquiryDaoException
	{
		Renquiry ret[] = findByDynamicSelect( SQL_SELECT + " WHERE tmpcaseno = ?", new Object[] { tmpcaseno } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria ''.
	 */
	public Renquiry[] pageAll(Integer offset,Integer num) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY tmpcaseno limit "
                 + num + " offset " + offset , null );
	}
        public Renquiry[] findAll() throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY tmpcaseno" , null );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'slno = :slno'.
	 */
	public Renquiry[] findWhereSlnoEquals(int slno) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE slno = ? ORDER BY slno", new Object[] {  new Integer(slno) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'tmpcaseno = :tmpcaseno'.
	 */
	public Renquiry[] findWhereTmpcasenoEquals(String tmpcaseno) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE tmpcaseno = ? ORDER BY tmpcaseno", new Object[] { tmpcaseno } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'applicant_name = :applicantName'.
	 */
	public Renquiry[] findWhereApplicantNameEquals(String applicantName) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE applicant_name = ? ORDER BY applicant_name", new Object[] { applicantName } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'area_type = :areaType'.
	 */
	public Renquiry[] findWhereAreaTypeEquals(String areaType) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE area_type = ? ORDER BY area_type", new Object[] { areaType } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'enquiry_date = :enquiryDate'.
	 */
	public Renquiry[] findWhereEnquiryDateEquals(Date enquiryDate) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE enquiry_date = ? ORDER BY enquiry_date", new Object[] { enquiryDate==null ? null : new java.sql.Date( enquiryDate.getTime() ) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'deed_type = :deedType'.
	 */
	public Renquiry[] findWhereDeedTypeEquals(int deedType) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE deed_type = ? ORDER BY deed_type", new Object[] {  new Integer(deedType) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'deed_subtype = :deedSubtype'.
	 */
	public Renquiry[] findWhereDeedSubtypeEquals(String deedSubtype) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE deed_subtype = ? ORDER BY deed_subtype", new Object[] { deedSubtype } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'consideration_amount = :considerationAmount'.
	 */
	public Renquiry[] findWhereConsiderationAmountEquals(int considerationAmount) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE consideration_amount = ? ORDER BY consideration_amount", new Object[] {  new Integer(considerationAmount) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'registration_fee = :registrationFee'.
	 */
	public Renquiry[] findWhereRegistrationFeeEquals(int registrationFee) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE registration_fee = ? ORDER BY registration_fee", new Object[] {  new Integer(registrationFee) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'stamp_duty = :stampDuty'.
	 */
	public Renquiry[] findWhereStampDutyEquals(int stampDuty) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE stamp_duty = ? ORDER BY stamp_duty", new Object[] {  new Integer(stampDuty) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'document_to_be_furnished = :documentToBeFurnished'.
	 */
	public Renquiry[] findWhereDocumentToBeFurnishedEquals(String documentToBeFurnished) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE document_to_be_furnished = ? ORDER BY document_to_be_furnished", new Object[] { documentToBeFurnished } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'doc_subject = :docSubject'.
	 */
	public Renquiry[] findWhereDocSubjectEquals(String docSubject) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE doc_subject = ? ORDER BY doc_subject", new Object[] { docSubject } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'whether_land = :whetherLand'.
	 */
	public Renquiry[] findWhereWhetherLandEquals(int whetherLand) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE whether_land = ? ORDER BY whether_land", new Object[] {  new Integer(whetherLand) } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'gender = :gender'.
	 */
	public Renquiry[] findWhereGenderEquals(String gender) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE gender = ? ORDER BY gender", new Object[] { gender } );
	}

	/** 
	 * Returns all rows from the renquiry table that match the criteria 'act = :act'.
	 */
	public Renquiry[] findWhereActEquals(String act) throws RenquiryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE act = ? ORDER BY act", new Object[] { act } );
	}

	/**
	 * Method 'RenquiryDaoImpl'
	 * 
	 */
	public RenquiryDaoImpl()
	{
	}

	/**
	 * Method 'RenquiryDaoImpl'
	 * 
	 * @param userConn
	 */
	public RenquiryDaoImpl(final java.sql.Connection userConn)
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
		return "renquiry";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Renquiry fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Renquiry dto = new Renquiry();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Renquiry[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Renquiry dto = new Renquiry();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Renquiry ret[] = new Renquiry[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Renquiry dto, ResultSet rs) throws SQLException
	{
		dto.setSlno( rs.getInt( COLUMN_SLNO ) );
		if (rs.wasNull()) {
			dto.setSlnoNull( true );
		}
		
		dto.setTmpcaseno( rs.getString( COLUMN_TMPCASENO ) );
		dto.setApplicantName( rs.getString( COLUMN_APPLICANT_NAME ) );
		dto.setAreaType( rs.getString( COLUMN_AREA_TYPE ) );
		dto.setEnquiryDate( rs.getDate(COLUMN_ENQUIRY_DATE ) );
                
		dto.setDeedType( rs.getInt( COLUMN_DEED_TYPE ) );
		if (rs.wasNull()) {
			dto.setDeedTypeNull( true );
		}
		
		dto.setDeedSubtype( rs.getString( COLUMN_DEED_SUBTYPE ) );
		dto.setConsiderationAmount( rs.getInt( COLUMN_CONSIDERATION_AMOUNT ) );
		if (rs.wasNull()) {
			dto.setConsiderationAmountNull( true );
		}
		
		dto.setRegistrationFee( rs.getInt( COLUMN_REGISTRATION_FEE ) );
		if (rs.wasNull()) {
			dto.setRegistrationFeeNull( true );
		}
		
		dto.setStampDuty( rs.getInt( COLUMN_STAMP_DUTY ) );
		if (rs.wasNull()) {
			dto.setStampDutyNull( true );
		}
		
		dto.setDocumentToBeFurnished( rs.getString( COLUMN_DOCUMENT_TO_BE_FURNISHED ) );
		dto.setDocSubject( rs.getString( COLUMN_DOC_SUBJECT ) );
		dto.setWhetherLand( rs.getInt( COLUMN_WHETHER_LAND ) );
		dto.setGender( rs.getString( COLUMN_GENDER ) );
		dto.setAct( rs.getString( COLUMN_ACT ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Renquiry dto)
	{
	}

	/** 
	 * Returns all rows from the renquiry table that match the specified arbitrary SQL statement
	 */
	public Renquiry[] findByDynamicSelect(String sql, Object[] sqlParams) throws RenquiryDaoException
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
			throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the renquiry table that match the specified arbitrary SQL statement
	 */
	public Renquiry[] findByDynamicWhere(String sql, Object[] sqlParams) throws RenquiryDaoException
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
			throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

        public int CustomDynamicSelect(String sql, Object[] sqlParams) throws RenquiryDaoException
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
                       
                        int ret = 0;
                        while (rs.next()) {
                                ret = rs.getInt(1);
                        }

                        return ret;
                       
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
       
        }
       
    }
        public String CustomDynamicSelect1(String sql, Object[] sqlParams) throws RenquiryDaoException
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
                       
                        String ret=null;
                        while (rs.next()) {
                                ret = rs.getString(1);
                        }

                        return ret;
                       
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new RenquiryDaoException( "Exception: " + _e.getMessage(), _e );
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

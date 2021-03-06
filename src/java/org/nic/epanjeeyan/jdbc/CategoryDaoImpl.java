/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package org.nic.epanjeeyan.jdbc;

import org.nic.epanjeeyan.dao.*;
import org.nic.epanjeeyan.dto.*;
import org.nic.epanjeeyan.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class CategoryDaoImpl extends AbstractDAO implements CategoryDao
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
	protected final String SQL_SELECT = "SELECT code, deed_type, sdcode, sub_deed_type, rfee, sfee, dcat, act, finindex, criteria, scndprtyreqrd FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( code, deed_type, sdcode, sub_deed_type, rfee, sfee, dcat, act, finindex, criteria, scndprtyreqrd ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET code = ?, deed_type = ?, sdcode = ?, sub_deed_type = ?, rfee = ?, sfee = ?, dcat = ?, act = ?, finindex = ?, criteria = ?, scndprtyreqrd = ? WHERE code = ? AND sdcode = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE code = ? AND sdcode = ?";

	/** 
	 * Index of column code
	 */
	protected static final int COLUMN_CODE = 1;

	/** 
	 * Index of column deed_type
	 */
	protected static final int COLUMN_DEED_TYPE = 2;

	/** 
	 * Index of column sdcode
	 */
	protected static final int COLUMN_SDCODE = 3;

	/** 
	 * Index of column sub_deed_type
	 */
	protected static final int COLUMN_SUB_DEED_TYPE = 4;

	/** 
	 * Index of column rfee
	 */
	protected static final int COLUMN_RFEE = 5;

	/** 
	 * Index of column sfee
	 */
	protected static final int COLUMN_SFEE = 6;

	/** 
	 * Index of column dcat
	 */
	protected static final int COLUMN_DCAT = 7;

	/** 
	 * Index of column act
	 */
	protected static final int COLUMN_ACT = 8;

	/** 
	 * Index of column finindex
	 */
	protected static final int COLUMN_FININDEX = 9;

	/** 
	 * Index of column criteria
	 */
	protected static final int COLUMN_CRITERIA = 10;

	/** 
	 * Index of column scndprtyreqrd
	 */
	protected static final int COLUMN_SCNDPRTYREQRD = 11;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 11;

	/** 
	 * Index of primary-key column code
	 */
	protected static final int PK_COLUMN_CODE = 1;

	/** 
	 * Index of primary-key column sdcode
	 */
	protected static final int PK_COLUMN_SDCODE = 2;

	/** 
	 * Inserts a new row in the category table.
	 */
	public CategoryPk insert(Category dto) throws CategoryDaoException
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
			stmt.setInt( index++, dto.getCode() );
			stmt.setString( index++, dto.getDeedType() );
			stmt.setInt( index++, dto.getSdcode() );
			stmt.setString( index++, dto.getSubDeedType() );
			if (dto.isRfeeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getRfee() );
			}
		
			if (dto.isSfeeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getSfee() );
			}
		
			stmt.setString( index++, dto.getDcat() );
			stmt.setString( index++, dto.getAct() );
			stmt.setString( index++, dto.getFinindex() );
			stmt.setString( index++, dto.getCriteria() );
			stmt.setInt( index++, dto.getScndprtyreqrd() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the category table.
	 */
	public void update(CategoryPk pk, Category dto) throws CategoryDaoException
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
			stmt.setInt( index++, dto.getCode() );
			stmt.setString( index++, dto.getDeedType() );
			stmt.setInt( index++, dto.getSdcode() );
			stmt.setString( index++, dto.getSubDeedType() );
			if (dto.isRfeeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getRfee() );
			}
		
			if (dto.isSfeeNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getSfee() );
			}
		
			stmt.setString( index++, dto.getDcat() );
			stmt.setString( index++, dto.getAct() );
			stmt.setString( index++, dto.getFinindex() );
			stmt.setString( index++, dto.getCriteria() );
			stmt.setInt( index++, dto.getScndprtyreqrd() );
			stmt.setInt( 12, pk.getCode() );
			stmt.setInt( 13, pk.getSdcode() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the category table.
	 */
	public void delete(CategoryPk pk) throws CategoryDaoException
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
			stmt.setInt( 1, pk.getCode() );
			stmt.setInt( 2, pk.getSdcode() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the category table that matches the specified primary-key value.
	 */
	public Category findByPrimaryKey(CategoryPk pk) throws CategoryDaoException
	{
		return findByPrimaryKey( pk.getCode(), pk.getSdcode() );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'code = :code AND sdcode = :sdcode'.
	 */
	public Category findByPrimaryKey(int code, int sdcode) throws CategoryDaoException
	{
		Category ret[] = findByDynamicSelect( SQL_SELECT + " WHERE code = ? AND sdcode = ?", new Object[] {  new Integer(code),  new Integer(sdcode) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the category table that match the criteria ''.
	 */
	public Category[] findAll() throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY code, sdcode", null );
	}
public Category[] pageAll(Integer offset,Integer num) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY code, sdcode limit "
                 + num + " offset " + offset , null );
	}
	/** 
	 * Returns all rows from the category table that match the criteria 'code = :code'.
	 */
	public Category[] findWhereCodeEquals(int code) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE code = ? ORDER BY code", new Object[] {  new Integer(code) } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'deed_type = :deedType'.
	 */
	public Category[] findWhereDeedTypeEquals(String deedType) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE deed_type = ? ORDER BY deed_type", new Object[] { deedType } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'sdcode = :sdcode'.
	 */
	public Category[] findWhereSdcodeEquals(int sdcode) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE sdcode = ? ORDER BY sdcode", new Object[] {  new Integer(sdcode) } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'sub_deed_type = :subDeedType'.
	 */
	public Category[] findWhereSubDeedTypeEquals(String subDeedType) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE sub_deed_type = ? ORDER BY sub_deed_type", new Object[] { subDeedType } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'rfee = :rfee'.
	 */
	public Category[] findWhereRfeeEquals(int rfee) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE rfee = ? ORDER BY rfee", new Object[] {  new Integer(rfee) } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'sfee = :sfee'.
	 */
	public Category[] findWhereSfeeEquals(int sfee) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE sfee = ? ORDER BY sfee", new Object[] {  new Integer(sfee) } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'dcat = :dcat'.
	 */
	public Category[] findWhereDcatEquals(String dcat) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE dcat = ? ORDER BY dcat", new Object[] { dcat } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'act = :act'.
	 */
	public Category[] findWhereActEquals(String act) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE act = ? ORDER BY act", new Object[] { act } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'finindex = :finindex'.
	 */
	public Category[] findWhereFinindexEquals(String finindex) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE finindex = ? ORDER BY finindex", new Object[] { finindex } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'criteria = :criteria'.
	 */
	public Category[] findWhereCriteriaEquals(String criteria) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE criteria = ? ORDER BY criteria", new Object[] { criteria } );
	}

	/** 
	 * Returns all rows from the category table that match the criteria 'scndprtyreqrd = :scndprtyreqrd'.
	 */
	public Category[] findWhereScndprtyreqrdEquals(int scndprtyreqrd) throws CategoryDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE scndprtyreqrd = ? ORDER BY scndprtyreqrd", new Object[] {  new Integer(scndprtyreqrd) } );
	}

	/**
	 * Method 'CategoryDaoImpl'
	 * 
	 */
	public CategoryDaoImpl()
	{
	}

	/**
	 * Method 'CategoryDaoImpl'
	 * 
	 * @param userConn
	 */
	public CategoryDaoImpl(final java.sql.Connection userConn)
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
		return "category";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Category fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Category dto = new Category();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Category[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Category dto = new Category();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Category ret[] = new Category[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Category dto, ResultSet rs) throws SQLException
	{
		dto.setCode( rs.getInt( COLUMN_CODE ) );
		dto.setDeedType( rs.getString( COLUMN_DEED_TYPE ) );
		dto.setSdcode( rs.getInt( COLUMN_SDCODE ) );
		dto.setSubDeedType( rs.getString( COLUMN_SUB_DEED_TYPE ) );
		dto.setRfee( rs.getInt( COLUMN_RFEE ) );
		if (rs.wasNull()) {
			dto.setRfeeNull( true );
		}
		
		dto.setSfee( rs.getInt( COLUMN_SFEE ) );
		if (rs.wasNull()) {
			dto.setSfeeNull( true );
		}
		
		dto.setDcat( rs.getString( COLUMN_DCAT ) );
		dto.setAct( rs.getString( COLUMN_ACT ) );
		dto.setFinindex( rs.getString( COLUMN_FININDEX ) );
		dto.setCriteria( rs.getString( COLUMN_CRITERIA ) );
		dto.setScndprtyreqrd( rs.getInt( COLUMN_SCNDPRTYREQRD ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Category dto)
	{
	}

	/** 
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public Category[] findByDynamicSelect(String sql, Object[] sqlParams) throws CategoryDaoException
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
			throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the category table that match the specified arbitrary SQL statement
	 */
	public Category[] findByDynamicWhere(String sql, Object[] sqlParams) throws CategoryDaoException
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
			throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}
        public List<Category> getCategory(String sql, Object[] sqlParams) throws CategoryDaoException {
       
        final boolean isConnSupplied = (userConn != null);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Category> sublist=new ArrayList<Category>();
       
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
                       
             while (rs.next()) {
            Category c=new Category();
            c.setSubDeedType(rs.getString("sub_deed_type"));
            
            sublist.add(c);                 
        }
              return sublist;      
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
       
        }
       
    }
     
      public String CustomDynamicSelect1(String sql, Object[] sqlParams) throws CategoryDaoException           {
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
            throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (!isConnSupplied) {
                ResourceManager.close(conn);
            }
       
        }
       
    }
  public Integer CustomDynamicSelect(String sql, Object[] sqlParams) throws CategoryDaoException           {
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
            throw new CategoryDaoException( "Exception: " + _e.getMessage(), _e );
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

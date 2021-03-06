package org.nic.epanjeeyan.jdbc;

import java.sql.*;

public class ResourceManager
{
    private static String JDBC_HOSTNAME = "jdbc:mysql://localhost";
    private static String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
    private static String JDBC_URL      = "jdbc:mysql://localhost/metadatabase";
    private static String JDBC_USER     = "root";
    private static String JDBC_PASSWORD = "root";
//    private static String JDBC_HOSTNAME = "jdbc:mysql://localhost";
//    private static String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
//    private static String JDBC_URL      = "jdbc:mysql://localhost/metadatabase";
//    private static String JDBC_USER     = "root";
//    private static String JDBC_PASSWORD = "root";

    private static Driver driver = null;

    public static synchronized Connection getConnection()
	throws SQLException
    {
        if (driver == null)
        {
            try
            {
                Class jdbcDriverClass = Class.forName( JDBC_DRIVER );
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver( driver );
            }
            catch (Exception e)
            {
                System.out.println( "Failed to initialise JDBC driver" );
                e.printStackTrace();
            }
        }

        return DriverManager.getConnection(
                JDBC_URL,
                JDBC_USER,
                JDBC_PASSWORD
        );
    }


	public static void close(Connection conn)
	{
		try {
			if (conn != null) conn.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(PreparedStatement stmt)
	{
		try {
			if (stmt != null) stmt.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(ResultSet rs)
	{
		try {
			if (rs != null) rs.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}
        public static synchronized Connection getConnectionDynamic(String office_dbname,String office_username,String office_password)
            throws SQLException {
        if (driver != null) {
            try {
                
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Failed to initialise JDBC driver");
                System.out.println(""+office_dbname);
                e.printStackTrace();
            }
        }

        return DriverManager.getConnection(
                JDBC_HOSTNAME+"/"+office_dbname,
                office_username,
                office_password);
    }
}

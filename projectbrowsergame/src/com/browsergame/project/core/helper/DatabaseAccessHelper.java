
package com.browsergame.project.core.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccessHelper
{
	
	private static DatabaseAccessHelper INSTANCE = new DatabaseAccessHelper();
	
	public static DatabaseAccessHelper getInstance()
	{
		return INSTANCE;
	}
	
	private DatabaseAccessHelper()
	{
	}
	
	public Connection openConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection sqlConnection;
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		sqlConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/projectbrowsergame", "root", "root");
		return sqlConnection;
	}
	
	public void resetDatabase(final boolean withUsers)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("TRUNCATE TABLE `building`;");
		if (withUsers)
		{
			sqlStatementAsString.append("TRUNCATE TABLE `user`;");
		}
		sqlStatementAsString.append("");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.execute(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
		}
		
	}
}

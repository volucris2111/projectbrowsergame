
package com.browsergame.project.struggling.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;

import com.browsergame.project.struggling.datatransfer.Struggling;
import com.browsergame.project.struggling.datatransfer.StrugglingAvatar;

public class StrugglingDataaccess
{
	private static StrugglingDataaccess INSTANCE = new StrugglingDataaccess();
	
	public static StrugglingDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private StrugglingDataaccess()
	{
	}
	
	public int createStrugglingAndReturnId(final int locationId)
	{
		Connection sqlConnection;
		int generatedKey = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.DATE, 7);
		calendar.set(Calendar.HOUR, 20);
		
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO struggling (locationId, endTimestamp) VALUES ('");
		sqlStatementAsString.append(locationId);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(calendar.getTimeInMillis());
		sqlStatementAsString.append("')");
		ResultSet result;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.execute(sqlStatementAsString.toString(),
					Statement.RETURN_GENERATED_KEYS);
			result = sqlStatment.getGeneratedKeys();
			result.next();
			generatedKey = result.getInt(1);
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while creating Struggling: " + sqlEx);
		}
		return generatedKey;
	}
	
	public LinkedList<StrugglingAvatar> getAllStrugglingAvatarsOfStrugglingWithId(
			final int strugglingId)
	{
		LinkedList<StrugglingAvatar> allStrugglingAvatarsOfStruggling = new LinkedList<StrugglingAvatar>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM strugglingavatar WHERE strugglingId ='");
		sqlStatementAsString.append(strugglingId);
		sqlStatementAsString.append("' AND endTimestamp > ");
		sqlStatementAsString.append(System.currentTimeMillis());
		sqlStatementAsString.append(";");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				StrugglingAvatar strugglingAvatar = new StrugglingAvatar();
				strugglingAvatar.setAvatarId(result.getInt("avatarId"));
				strugglingAvatar.setSupportedAvatarId(result
						.getInt("supportedAvatarId"));
				allStrugglingAvatarsOfStruggling.add(strugglingAvatar);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading all StrugglingAvatars Of Struggling by Id: "
							+ sqlEx);
		}
		
		return allStrugglingAvatarsOfStruggling;
	}
	
	public Struggling getStrugglingById(final int strugglingId)
	{
		Struggling struggling = new Struggling();
		
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM struggling WHERE strugglingId ='");
		sqlStatementAsString.append(strugglingId);
		sqlStatementAsString.append("' AND endTimestamp > ");
		sqlStatementAsString.append(System.currentTimeMillis());
		sqlStatementAsString.append(";");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				struggling.setStrugglingId(result.getInt("strugglingId"));
				struggling.setLocationId(result.getInt("locationId"));
				struggling.setEndTimestamp(result.getLong("endTimestamp"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading Struggling by Id: " + sqlEx);
		}
		
		return struggling;
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
	
	public void setStrugglingAvatar(final int avatarId, final int strugglingId,
			final int supportedAvatarId)
	{
		Connection sqlConnection;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.DATE, 7);
		calendar.set(Calendar.HOUR, 20);
		
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO strugglingavatar (avatarId, strugglingId, supportedAvatarId) VALUES ('");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(strugglingId);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(supportedAvatarId);
		sqlStatementAsString.append("')");
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
			System.out.println("Error while add StrugglingAvatar: " + sqlEx);
		}
	}
	
	public void setStrugglingIdToLocationId(final int locationId,
			final int struggelindId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET struggelindId = '");
		sqlStatementAsString.append(struggelindId);
		sqlStatementAsString.append("' WHERE locationId = ");
		sqlStatementAsString.append(locationId);
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
	}
}

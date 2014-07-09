
package com.browsergame.project.attribute.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.attribute.datatransfer.Attribute;

public class AttributeDataaccess
{
	private static AttributeDataaccess INSTANCE = new AttributeDataaccess();
	
	public static AttributeDataaccess getInstance()
	{
		return AttributeDataaccess.INSTANCE;
	}
	
	private AttributeDataaccess()
	{
	}
	
	public void createAllAttributesForAvatarId(final int avatarId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		LinkedList<Integer> allAttributeIds = getAllAttributeIdAsList();
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			for (int currentAttributeId : allAttributeIds)
			{
				sqlStatementAsString = new StringBuilder();
				sqlStatementAsString
						.append("INSERT INTO avatarattribute (attributeId, avatarId) VALUES ('");
				sqlStatementAsString.append(currentAttributeId);
				sqlStatementAsString.append("', '");
				sqlStatementAsString.append(avatarId);
				sqlStatementAsString.append("'); ");
				sqlStatment = sqlConnection.createStatement();
				sqlStatment.execute(sqlStatementAsString.toString());
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err
					.println("Error while creating all attributes for avatar: "
							+ sqlEx);
		}
	}
	
	public LinkedList<Integer> getAllAttributeIdAsList()
	{
		LinkedList<Integer> allAttributeIds = new LinkedList<Integer>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM attribute;");
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
				allAttributeIds.add(result.getInt("attributeId"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading all attribute ids: "
					+ sqlEx);
		}
		return allAttributeIds;
	}
	
	public HashMap<String, Attribute> getAttributesAsHashMapOfAvatarId(
			final int avatarId)
	{
		HashMap<String, Attribute> attributesOfAvatar = new HashMap<String, Attribute>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM avatarattribute INNER JOIN attribute ON avatarattribute.attributeId = attribute.attributeId WHERE avatarId ='");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append("';");
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
				Attribute attribute = new Attribute();
				fillAttribute(result, attribute);
				attributesOfAvatar.put((result.getString("name")), attribute);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err
					.println("Error while loading attributes of Avatar as HashMap: "
							+ sqlEx);
		}
		return attributesOfAvatar;
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
	
	public void updateAttributeWithIdOfAvatarId(final int avatarId,
			final Attribute attribute)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE avatarattribute SET lvl = '");
		sqlStatementAsString.append(attribute.getLvl());
		sqlStatementAsString.append("' WHERE avatarId = ");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append(" AND attributeId = ");
		sqlStatementAsString.append(attribute.getAttributeId());
		sqlStatementAsString.append(";");
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
	
	private void fillAttribute(final ResultSet result, final Attribute attribute)
			throws SQLException
	{
		attribute.setName(result.getString("name"));
		attribute.setNameForJsp(result.getString("nameForJsp"));
		attribute.setAttributeId(result.getInt("attributeId"));
		attribute.setLvl(result.getInt("lvl"));
	}
}

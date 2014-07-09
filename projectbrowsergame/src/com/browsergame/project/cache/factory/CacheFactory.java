package com.browsergame.project.cache.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.browsergame.project.cache.Cache;
import com.browsergame.project.field.datatransfer.Field;

public class CacheFactory
{
	private static CacheFactory INSTANCE = new CacheFactory();

	public static CacheFactory getInstance()
	{
		return INSTANCE;
	}

	private Cache cache;

	public Map<String, Field> createCache()
	{
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM field;");
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		Connection sqlConnection;
		Statement sqlStatment;
		Field field = null;
		try
		{
			sqlConnection = this.openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());

			while (result.next())
			{
				field = new Field();
				field.setFieldId(result.getInt("fieldId"));
				field.setPositionX(result.getInt("positionX"));
				field.setPositionY(result.getInt("positionY"));
				field.setTerrainString(result.getString("terrain"));
				if (result.getString(5) != null)
				{
					field.setInventoryId(result.getInt("inventoryId"));
				}

				fieldMap.put(
						result.getString("positionX")
								+ result.getString("positionY"), field);

			}
			sqlConnection.close();

		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return fieldMap;
	}

	public Cache getCache()
	{
		return this.cache;
	}

	public Field getElementAtFromCache(int xKoords, int yKoords)
	{
		return this.getElementAtFromCache(xKoords, yKoords);

	}

	public Field getElementAtFromCache(Integer xKoords, Integer yKoords)
	{
		return this.getElementAtFromCache(xKoords.toString(),
				yKoords.toString());
	}

	public Field getElementAtFromCache(String xKoords, String yKoords)
	{
		Field returnField = null;
		if (this.cache == null)
		{
			this.cache = new Cache();
		}
		if (this.cache.getCacheMap().containsKey(xKoords + yKoords))
		{
			returnField = this.cache.getCacheMap().get(xKoords + yKoords);
		}

		return returnField;
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

	public void setCache(Cache cache)
	{
		this.cache = cache;
	}

}

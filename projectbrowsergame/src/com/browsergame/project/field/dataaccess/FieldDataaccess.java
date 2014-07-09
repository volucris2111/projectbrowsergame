
package com.browsergame.project.field.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.terrain.datatransfer.TerrainEnum;

public class FieldDataaccess
{
	private static FieldDataaccess INSTANCE = new FieldDataaccess();
	
	public static FieldDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private FieldDataaccess()
	{
	}
	
	public void addBuildingAndLocationIdToField(final Field field)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE field SET buildingId = '");
		sqlStatementAsString.append(field.getBuildingId());
		sqlStatementAsString.append("', locationId = '");
		sqlStatementAsString.append(field.getLocationId());
		sqlStatementAsString.append("' WHERE fieldId ='");
		sqlStatementAsString.append(field.getFieldId());
		sqlStatementAsString.append("';");
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
	
	public void addInventoryIdToField(final Field field)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE field SET inventoryId = '");
		sqlStatementAsString.append(field.getInventoryId());
		sqlStatementAsString.append("' WHERE fieldId ='");
		sqlStatementAsString.append(field.getFieldId());
		sqlStatementAsString.append("';");
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
	
	public void changeTerrainOfFieldId(final int fieldId, final String terrain,
			final String backgroundImageUrl)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE field SET terrain = '");
		sqlStatementAsString.append(terrain);
		sqlStatementAsString.append("', backgroundImageUrl ='");
		sqlStatementAsString.append(backgroundImageUrl);
		sqlStatementAsString.append("' WHERE fieldId ='");
		sqlStatementAsString.append(fieldId);
		sqlStatementAsString.append("';");
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
	
	public void createNewField(final String type, final int positionX,
			final int positionY, final int inventoryId,
			final String backgroundImageUrl)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO field (positionX, positionY, terrain, inventoryId, backgroundImageUrl, fieldObjectImageUrl) VALUES ('");
		sqlStatementAsString.append(positionX);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(positionY);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(type);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(inventoryId);
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(backgroundImageUrl);
		sqlStatementAsString
				.append("', '../projectbrowsergame/images/worldobjects/worldobjectblank.gif')");
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
			System.err.println("Error while adding Field: " + sqlEx);
		}
	}
	
	public int getFieldIdOfCoords(final int positionX, final int positionY)
	{
		int fieldId = 0;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM field WHERE positionX ='");
		sqlStatementAsString.append(positionX);
		sqlStatementAsString.append("' AND positionY ='");
		sqlStatementAsString.append(positionY);
		sqlStatementAsString.append("';");
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
				fieldId = result.getInt("fieldId");
			}
			
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return fieldId;
	}
	
	public Field getFieldOfCoords(final int positionX, final int positionY)
	{
		Field field = new Field();
		field.setPositionX(positionX);
		field.setPositionY(positionY);
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM field WHERE positionX ='");
		sqlStatementAsString.append(positionX);
		sqlStatementAsString.append("' AND positionY ='");
		sqlStatementAsString.append(positionY);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			TerrainEnum terrainEnum;
			if (result.next())
			{
				terrainEnum = TerrainEnum.valueOf(result.getString("terrain"));
				field.setInventoryId(result.getInt("inventoryId"));
				field.setFieldId(result.getInt("fieldId"));
				field.setBuildingId(result.getInt("buildingId"));
				field.setLocationId(result.getInt("locationId"));
			}
			else
			{
				terrainEnum = TerrainEnum.SEA;
			}
			field.setTerrain(terrainEnum.getTerrain());
			field.setBackgroundImageUrl(field.getTerrain().getImageUrl());
			field.setTerrainString(terrainEnum.name());
			
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return field;
	}
	
	public LinkedList<Field> getPieceOfMapWithCoordsAsCenter(
			final int positionX, final int positionY)
	{
		LinkedList<Field> pieceOfMap = new LinkedList<Field>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM field WHERE positionX >= '");
		sqlStatementAsString.append(positionX - 2);
		sqlStatementAsString.append("' AND positionX <= '");
		sqlStatementAsString.append(positionX + 2);
		sqlStatementAsString.append("' AND positionY >= '");
		sqlStatementAsString.append(positionY - 2);
		sqlStatementAsString.append("' AND positionY <= '");
		sqlStatementAsString.append(positionY + 2);
		sqlStatementAsString.append("' ORDER BY positionY, positionX");
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
				Field field = new Field();
				field.setPositionX(result.getInt("positionX"));
				field.setPositionY(result.getInt("positionY"));
				field.setInventoryId(result.getInt("inventoryId"));
				field.setTerrainString(result.getString("terrain"));
				field.setBackgroundImageUrl(result
						.getString("backgroundImageUrl"));
				field.setFieldObjectImageUrl(result
						.getString("fieldObjectImageUrl"));
				pieceOfMap.add(field);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return pieceOfMap;
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
	
	public void setBackgroundImageUrlOfFieldId(final String backgroundImageUrl,
			final int fieldId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE field SET backgroundImageUrl = '");
		sqlStatementAsString.append(backgroundImageUrl);
		sqlStatementAsString.append("' WHERE fieldId ='");
		sqlStatementAsString.append(fieldId);
		sqlStatementAsString.append("';");
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
			System.err.println("Error while setting backgroundImageUrl: "
					+ sqlEx);
		}
	}
	
	public void setFieldObjectImageUrlOfFieldId(
			final String fieldObjectImageUrl, final int fieldId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE field SET fieldObjectImageUrl = '");
		sqlStatementAsString.append(fieldObjectImageUrl);
		sqlStatementAsString.append("' WHERE fieldId ='");
		sqlStatementAsString.append(fieldId);
		sqlStatementAsString.append("';");
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
			System.err.println("Error while setting fieldObjectImageUrl: "
					+ sqlEx);
		}
	}
}

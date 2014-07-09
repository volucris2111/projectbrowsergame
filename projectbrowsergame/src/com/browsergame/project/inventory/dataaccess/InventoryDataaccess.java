
package com.browsergame.project.inventory.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map.Entry;

import com.browsergame.project.core.helper.DatabaseAccessHelper;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;

public class InventoryDataaccess
{
	private static InventoryDataaccess INSTANCE = new InventoryDataaccess();
	
	public static InventoryDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private InventoryDataaccess()
	{
	}
	
	public int createNewInventoryAndReturnId()
	{
		int generatedKey = 0;
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO inventory (stone, leather, meat, wood) VALUES ('0','0','0','0')");
		ResultSet result;
		Statement sqlStatment;
		try
		{
			sqlConnection = DatabaseAccessHelper.getInstance().openConnection();
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
			System.out.println("Error while creating Inventory: " + sqlEx);
		}
		return generatedKey;
	}
	
	public Inventory getInventoryWithId(final int inventoryId)
	{
		Inventory inventory = new Inventory();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM inventory WHERE inventoryId ='");
		sqlStatementAsString.append(inventoryId);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = DatabaseAccessHelper.getInstance().openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				inventory.setInventoryId(result.getInt("inventoryId"));
				for (Resource currentResource : Resource.values())
				{
					
					inventory.getResources().put(currentResource,
							result.getInt(currentResource.getResource()));
				}
				inventory.setCoins(result.getInt("coins"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return inventory;
	}
	
	public void updateInventory(final Inventory inventory)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE inventory SET");
		
		for (Entry<Resource, Integer> currentResource : inventory
				.getResources().entrySet())
		{
			sqlStatementAsString.append(" ");
			sqlStatementAsString.append(currentResource.getKey().getResource());
			sqlStatementAsString.append(" = '");
			sqlStatementAsString.append(currentResource.getValue());
			sqlStatementAsString.append("',");
		}
		if (sqlStatementAsString.charAt(sqlStatementAsString.length() - 1) == ',')
		{
			sqlStatementAsString
					.deleteCharAt(sqlStatementAsString.length() - 1);
		}
		sqlStatementAsString.append(", coins = '");
		sqlStatementAsString.append(inventory.getCoins());
		sqlStatementAsString.append("' WHERE inventoryId ='");
		sqlStatementAsString.append(inventory.getInventoryId());
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = DatabaseAccessHelper.getInstance().openConnection();
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

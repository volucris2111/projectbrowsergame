
package com.browsergame.project.buildingupgrade.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.browsergame.project.buildingupgrade.datatransfer.BuildingUpgradeType;
import com.browsergame.project.buildingupgrade.datatransfer.BuildingUpgrades;
import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingUpgradeDataaccess
{
	private static BuildingUpgradeDataaccess INSTANCE = new BuildingUpgradeDataaccess();
	
	public static BuildingUpgradeDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private BuildingUpgradeDataaccess()
	{
	}
	
	public BuildingUpgradeType fillBuildingUpgradeCostsOfType(
			final BuildingUpgradeType buildingUpgradeType)
	{
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingupgradecosts WHERE buildingUpgradeTypeId ='");
		sqlStatementAsString.append(buildingUpgradeType
				.getBuildingUpgradeTypeId());
		sqlStatementAsString.append("' AND lvl ='");
		sqlStatementAsString.append(buildingUpgradeType.getLvl());
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
				HashMap<Resource, Integer> costs = new HashMap<Resource, Integer>();
				buildingUpgradeType.setCoins(result.getInt("coins"));
				costs.put(Resource.STONE, result.getInt("stone"));
				costs.put(Resource.WOOD, result.getInt("wood"));
				costs.put(Resource.FOOD, result.getInt("food"));
				costs.put(Resource.CLOTHES, result.getInt("clothes"));
				costs.put(Resource.FURNITURE, result.getInt("furniture"));
				costs.put(Resource.TOOL, result.getInt("tool"));
				buildingUpgradeType.setCosts(costs);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading BuildingUpgrades: " + sqlEx);
		}
		return buildingUpgradeType;
	}
	
	public BuildingUpgrades getBuildingUpgradesByBuildingId(final int buildingId)
	{
		BuildingUpgrades buildingUpgrades = new BuildingUpgrades();
		buildingUpgrades.setBuildingId(buildingId);
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingupgrades WHERE buildingId ='");
		sqlStatementAsString.append(buildingId);
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
				buildingUpgrades.setGuards(result.getInt("guards"));
				buildingUpgrades.setHideout(result.getInt("hideout"));
				buildingUpgrades.setStorage(result.getInt("storage"));
				buildingUpgrades.setTools(result.getInt("tools"));
				buildingUpgrades.setWorker(result.getInt("worker"));
			}
			else
			{
				buildingUpgrades.setGuards(0);
				buildingUpgrades.setHideout(0);
				buildingUpgrades.setStorage(1);
				buildingUpgrades.setTools(0);
				buildingUpgrades.setWorker(0);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading BuildingUpgrades: " + sqlEx);
		}
		return buildingUpgrades;
	}
	
	public BuildingUpgradeType getBuildingUpgradeTypeByName(final String name)
	{
		BuildingUpgradeType buildingUpgradeType = new BuildingUpgradeType();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingupgradetype WHERE name ='");
		sqlStatementAsString.append(name);
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
				buildingUpgradeType.setStringForJSP(result
						.getString("stringForJSP"));
				buildingUpgradeType.setDescription(result
						.getString("description"));
				buildingUpgradeType.setName(result.getString("name"));
				buildingUpgradeType.setBuildingUpgradeTypeId(result
						.getInt("buildingUpgradeTypeId"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading BuildingUpgrades: " + sqlEx);
		}
		return buildingUpgradeType;
	}
	
	public void insertOrUpdateBuildingUpgrades(
			final BuildingUpgrades buildingUpgrades)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO buildingupgrades (buildingId, guards, hideout, storage, tools, worker) VALUES ('");
		sqlStatementAsString.append(buildingUpgrades.getBuildingId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(buildingUpgrades.getGuards());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(buildingUpgrades.getHideout());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(buildingUpgrades.getStorage());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(buildingUpgrades.getTools());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(buildingUpgrades.getWorker());
		sqlStatementAsString.append("') ON DUPLICATE KEY UPDATE guards='");
		sqlStatementAsString.append(buildingUpgrades.getGuards());
		sqlStatementAsString.append("', hideout='");
		sqlStatementAsString.append(buildingUpgrades.getHideout());
		sqlStatementAsString.append("', storage='");
		sqlStatementAsString.append(buildingUpgrades.getStorage());
		sqlStatementAsString.append("', tools='");
		sqlStatementAsString.append(buildingUpgrades.getTools());
		sqlStatementAsString.append("', worker='");
		sqlStatementAsString.append(buildingUpgrades.getWorker());
		sqlStatementAsString.append("';");
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
			System.out
					.println("Error while insering or updating BuildingUpgrades: "
							+ sqlEx);
		}
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
}

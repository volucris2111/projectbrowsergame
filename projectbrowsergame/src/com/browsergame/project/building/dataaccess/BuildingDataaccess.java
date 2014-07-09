
package com.browsergame.project.building.dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.building.datatransfer.BuildingProduct;
import com.browsergame.project.building.datatransfer.BuildingType;
import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingDataaccess
{
	private static BuildingDataaccess INSTANCE = new BuildingDataaccess();
	
	public static BuildingDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private BuildingDataaccess()
	{
	}
	
	public boolean checkForFirstBuildingOfAvatarId(final int avatarId)
	{
		boolean firstBuilding = true;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM building WHERE ownerAvatarId ='");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append("' LIMIT 1;");
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
				firstBuilding = false;
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while checking first building of user: "
					+ sqlEx);
		}
		return firstBuilding;
	}
	
	public int createBuildingAndReturnId(final Building building)
	{
		Connection sqlConnection;
		int generatedKey = 0;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO building (buildingTypeId, ownerAvatarId, inventoryId, positionX, positionY, buildingMarketId) VALUES ('");
		sqlStatementAsString.append(building.getBuildingTypeId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(building.getOwnerAvatarId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(building.getInventoryId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(building.getPositionX());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(building.getPositionY());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(building.getBuildingMarketId());
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
			System.out.println("Error while creating Building: " + sqlEx);
		}
		return generatedKey;
	}
	
	public LinkedList<Building> getAllBuildings()
	{
		LinkedList<Building> allBuildings = new LinkedList<Building>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM building;");
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
				Building building = new Building();
				getAllBuildingAttributes(result, building);
				allBuildings.add(building);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while loading all Building: " + sqlEx);
		}
		return allBuildings;
	}
	
	public Building getBuildingById(final int buildingId)
	{
		Building building = new Building();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM building WHERE buildingId ='");
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
				getAllBuildingAttributes(result, building);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while loading Building by Id: " + sqlEx);
		}
		return building;
	}
	
	public Building getBuildingByPosition(final int positionX,
			final int positionY)
	{
		Building building = new Building();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM building WHERE positionX ='");
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
				getAllBuildingAttributes(result, building);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return building;
	}
	
	public HashMap<Resource, Integer> getBuildingCostsByBuildingTypeIdAsHashmap(
			final int buildingCostsId)
	{
		HashMap<Resource, Integer> costs = new HashMap<Resource, Integer>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingcost WHERE buildingCostId ='");
		sqlStatementAsString.append(buildingCostsId);
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
				costs.put(Resource.STONE, result.getInt("stone"));
				costs.put(Resource.WOOD, result.getInt("wood"));
				costs.put(Resource.FOOD, result.getInt("food"));
				costs.put(Resource.CLOTHES, result.getInt("clothes"));
				costs.put(Resource.FURNITURE, result.getInt("furniture"));
				costs.put(Resource.TOOL, result.getInt("tool"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading Building costs by buildingtypeId: "
							+ sqlEx);
		}
		return costs;
	}
	
	public LinkedList<BuildingProduct> getBuildingProductsByBuildingTypeIdAsList(
			final int buildingTypeId)
	{
		LinkedList<BuildingProduct> buildingProducts = new LinkedList<BuildingProduct>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingproduct WHERE buildingTypeId ='");
		sqlStatementAsString.append(buildingTypeId);
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
				BuildingProduct buildingProduct = new BuildingProduct();
				buildingProduct.setFactor(result.getInt("factor"));
				buildingProduct.setRequiredSkill(result
						.getString("requiredSkill"));
				buildingProduct.setResource(Resource.valueOf(result.getString(
						"resource").toUpperCase()));
				buildingProducts.add(buildingProduct);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out
					.println("Error while loading building products as list by buildingtypeId: "
							+ sqlEx);
		}
		return buildingProducts;
	}
	
	public BuildingType getBuildingTypeById(final int buildingTypeId)
	{
		BuildingType buildingType = new BuildingType();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingtype WHERE buildingTypeId ='");
		sqlStatementAsString.append(buildingTypeId);
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
				getAllBuildingTypeAttributes(result, buildingType);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while loading BuildingType by Id: "
					+ sqlEx);
		}
		return buildingType;
	}
	
	public LinkedList<BuildingType> getBuildingTypesByTerrain(
			final String terrain)
	{
		LinkedList<BuildingType> buildingTypes = new LinkedList<BuildingType>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM buildingtype WHERE terrain = '");
		sqlStatementAsString.append(terrain);
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
				BuildingType buildingType = new BuildingType();
				getAllBuildingTypeAttributes(result, buildingType);
				buildingTypes.add(buildingType);
				
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while loading BuildingType by Id: "
					+ sqlEx);
		}
		return buildingTypes;
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
	
	public void updateBuildingLastHalfDayReport(final int buildingId,
			final String lastHalfDayReport)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("UPDATE building SET lastHalfDayReport = '");
		sqlStatementAsString.append(lastHalfDayReport);
		sqlStatementAsString.append("' WHERE buildingId = ");
		sqlStatementAsString.append(buildingId);
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
	
	public void updateBuildingLastHourReport(final int buildingId,
			final String lastHourReport)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE building SET lastHourReport = '");
		sqlStatementAsString.append(lastHourReport);
		sqlStatementAsString.append("' WHERE buildingId = ");
		sqlStatementAsString.append(buildingId);
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
	
	public void updateBuildingName(final int buildingId, final String name)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE building SET name = '");
		sqlStatementAsString.append(name);
		sqlStatementAsString.append("' WHERE buildingId = ");
		sqlStatementAsString.append(buildingId);
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
	
	public void updateBuildingsProductionAttributes(final Building building)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE building SET selectedWorkers = '");
		sqlStatementAsString.append(building.getSelectedWorkers());
		sqlStatementAsString.append("', selectedTools = '");
		sqlStatementAsString.append(building.getSelectedTools());
		sqlStatementAsString.append("', finishingProduction = '");
		sqlStatementAsString.append(building.getFinishingProduction());
		sqlStatementAsString.append("' WHERE buildingId = ");
		sqlStatementAsString.append(building.getBuildingId());
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
	
	private void getAllBuildingAttributes(final ResultSet result,
			final Building building) throws SQLException
	{
		building.setBuildingId(result.getInt("buildingId"));
		building.setOwnerAvatarId(result.getInt("ownerAvatarId"));
		building.setBuildingTypeId(result.getInt("buildingTypeId"));
		building.setPositionX(result.getInt("positionX"));
		building.setPositionY(result.getInt("positionY"));
		building.setInventoryId(result.getInt("inventoryId"));
		building.setName(result.getString("name"));
		building.setLastHourReport(result.getString("lastHourReport"));
		building.setLastHalfDayReport(result.getString("lastHalfDayReport"));
		building.setSelectedWorkers(result.getInt("selectedWorkers"));
		building.setSelectedTools(result.getInt("selectedTools"));
		building.setFinishingProduction(result.getLong("finishingProduction"));
		building.setFinishingProductionAsDate(new Date(result
				.getLong("finishingProduction")));
		building.setBuildingMarketId(result.getInt("buildingMarketId"));
	}
	
	private void getAllBuildingTypeAttributes(final ResultSet result,
			final BuildingType buildingType) throws SQLException
	{
		buildingType.setBuildingTypeId(result.getInt("buildingTypeId"));
		buildingType.setImageUrl(result.getString("imageUrl"));
		buildingType.setName(result.getString("name"));
		buildingType.setNameForJsp(result.getString("nameForJsp"));
		buildingType.setBuildingCostsId(result.getInt("buildingCostsId"));
		buildingType.setTerrain(result.getString("terrain"));
		buildingType.setAvatarClassNeededToBuild(result
				.getString("avatarClassNeededToBuild"));
	}
}

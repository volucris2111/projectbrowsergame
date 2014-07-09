
package com.browsergame.project.location.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.datatransfer.LocationType;
import com.browsergame.project.location.datatransfer.LocationUpgrades;

public class LocationDataaccess
{
	private static LocationDataaccess INSTANCE = new LocationDataaccess();
	
	public static LocationDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private LocationDataaccess()
	{
	}
	
	public int createLocationAndReturnId(final Location location)
	{
		Connection sqlConnection;
		int generatedKey = 0;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO location (locationId, locationMarketId, locationFreeMarketId, positionX, positionY, locationTypeId, inventoryId) VALUES ('");
		sqlStatementAsString.append(location.getLocationId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(location.getLocationMarketId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(location.getLocationFreeMarketId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(location.getPositionX());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(location.getPositionY());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(location.getLocationTypeId());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(location.getInventoryId());
		sqlStatementAsString.append("')");
		Statement sqlStatment;
		ResultSet result;
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
			System.out.println("Error while creating Location:" + sqlEx);
		}
		return generatedKey;
	}
	
	public void fillLocationNeeds(final Location location)
	{
		StringBuilder sqlStatementAsString = new StringBuilder();
		location.setBasicGoods(new HashMap<Resource, Double>());
		location.setLuxuryGoods(new HashMap<Resource, Double>());
		location.setGrowingGoods(new HashMap<Resource, Double>());
		sqlStatementAsString
				.append("SELECT * FROM locationneeds WHERE locationTypeId ='");
		sqlStatementAsString.append(location.getLocationTypeId());
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
				if (result.getString("needType").equals("luxury"))
				{
					location.getLuxuryGoods()
							.put(Resource.valueOf(result.getString("resource")
									.toUpperCase()), result.getDouble("factor"));
				}
				else if (result.getString("needType").equals("basic"))
				{
					location.getBasicGoods()
							.put(Resource.valueOf(result.getString("resource")
									.toUpperCase()), result.getDouble("factor"));
				}
				else
				{
					location.getGrowingGoods()
							.put(Resource.valueOf(result.getString("resource")
									.toUpperCase()), result.getDouble("factor"));
				}
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while filling location needs: " + sqlEx);
		}
	}
	
	public LinkedList<Integer> getAllLocationIds()
	{
		LinkedList<Integer> allLocationIds = new LinkedList<Integer>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT locationId FROM location;");
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
				allLocationIds.add(result.getInt("locationId"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err
					.println("Error while loading masterIds of all unavailable masters: "
							+ sqlEx);
		}
		return allLocationIds;
	}
	
	public LinkedList<Location> getAllLocations()
	{
		LinkedList<Location> allLocation = new LinkedList<Location>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM location;");
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
				Location Location = new Location();
				setLocationAttributes(result, Location);
				allLocation.add(Location);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while loading all Locations: " + sqlEx);
		}
		return allLocation;
	}
	
	public Location getLocationById(final int locationId)
	{
		Location location = new Location();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM location WHERE locationId ='");
		sqlStatementAsString.append(locationId);
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
				setLocationAttributes(result, location);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading location by id: " + sqlEx);
		}
		
		return location;
	}
	
	public Location getLocationByPosition(final int positionX,
			final int positionY)
	{
		Location location = new Location();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM location WHERE positionX ='");
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
				setLocationAttributes(result, location);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading location by position: "
					+ sqlEx);
		}
		
		return location;
	}
	
	public LocationType getLocationTypeById(final int locationTypeId)
	{
		LocationType locationType = new LocationType();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM locationtype WHERE locationTypeId ='");
		sqlStatementAsString.append(locationTypeId);
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
				locationType.setImageUrl(result.getString("imageUrl"));
				locationType.setName(result.getString("name"));
				locationType.setNameForJsp(result.getString("nameForJsp"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading LocationType by id: "
					+ sqlEx);
		}
		
		return locationType;
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
	
	public void updateLocationLastHalfDayReport(final int locationId,
			final String lastHalfDayReport)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("UPDATE location SET lastHalfDayReport = '");
		sqlStatementAsString.append(lastHalfDayReport);
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
			System.err
					.println("Error while updating location last half day report: "
							+ sqlEx);
		}
	}
	
	public void updateLocationLastHourReport(final int locationId,
			final String lastHourReport)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET lastHourReport = '");
		sqlStatementAsString.append(lastHourReport);
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
			System.err
					.println("Error while updating location last hour report: "
							+ sqlEx);
		}
	}
	
	public void updateLocationName(final int locationId, final String name)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET name = '");
		sqlStatementAsString.append(name);
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
			System.err.println("Error while updating location name: " + sqlEx);
		}
	}
	
	public void updateLocationOwner(final int locationId,
			final int ownerAvatarId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET ownerAvatarId = '");
		sqlStatementAsString.append(ownerAvatarId);
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
			System.err.println("Error while updating location owner: " + sqlEx);
		}
	}
	
	public void updateLocationPopulation(final int locationId,
			final int population)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET humans = '");
		sqlStatementAsString.append(population);
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
			System.err.println("Error while updating location population: "
					+ sqlEx);
		}
	}
	
	public void updateLocationStruggling(final int locationId,
			final int strugglingId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET strugglingId = '");
		sqlStatementAsString.append(strugglingId);
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
			System.err.println("Error while updating location struggling: "
					+ sqlEx);
		}
	}
	
	public void updateLocationUpgrades(final LocationUpgrades locationUpgrades)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET smithLvl='");
		sqlStatementAsString.append(locationUpgrades.getSmithLvl());
		sqlStatementAsString.append("', butcherLvl='");
		sqlStatementAsString.append(locationUpgrades.getButcherLvl());
		sqlStatementAsString.append("', carpenterLvl='");
		sqlStatementAsString.append(locationUpgrades.getCarpenterLvl());
		sqlStatementAsString.append("', bakerLvl='");
		sqlStatementAsString.append(locationUpgrades.getBakerLvl());
		sqlStatementAsString.append("', tailorLvl='");
		sqlStatementAsString.append(locationUpgrades.getTailorLvl());
		sqlStatementAsString.append("', housingLvl='");
		sqlStatementAsString.append(locationUpgrades.getHousingLvl());
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
			System.err.println("Error while updating locationUpgrades: "
					+ sqlEx);
		}
	}
	
	public void updatePopulationOfLocationWithId(final int locationId,
			final int population)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE location SET humans = '");
		sqlStatementAsString.append(population);
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
			System.err.println("Error while updating location name: " + sqlEx);
		}
	}
	
	private void setLocationAttributes(final ResultSet result,
			final Location location) throws SQLException
	{
		location.setLocationId(result.getInt("locationId"));
		location.setOwnerAvatarId(result.getInt("ownerAvatarId"));
		location.setLocationTypeId(result.getInt("locationTypeId"));
		location.setPositionX(result.getInt("positionX"));
		location.setPositionY(result.getInt("positionY"));
		location.setInventoryId(result.getInt("inventoryId"));
		location.setName(result.getString("name"));
		location.setLastHourReport(result.getString("lastHourReport"));
		location.setLastHalfDayReport(result.getString("lastHalfDayReport"));
		location.setLocationMarketId(result.getInt("locationMarketId"));
		location.setLocationFreeMarketId(result.getInt("locationFreeMarketId"));
		LocationUpgrades locationUpgrades = new LocationUpgrades();
		locationUpgrades.setBakerLvl(result.getInt("bakerLvl"));
		locationUpgrades.setButcherLvl(result.getInt("butcherLvl"));
		locationUpgrades.setCarpenterLvl(result.getInt("carpenterLvl"));
		locationUpgrades.setHousingLvl(result.getInt("housingLvl"));
		locationUpgrades.setSmithLvl(result.getInt("smithLvl"));
		locationUpgrades.setTailorLvl(result.getInt("tailorLvl"));
		LocationDataaccess.getInstance().fillLocationNeeds(location);
		location.setLocationUpgrades(locationUpgrades);
	}
}


package com.browsergame.project.market.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.market.datatransfer.Market;
import com.browsergame.project.market.datatransfer.MarketResource;

public class MarketDataaccess
{
	private static MarketDataaccess INSTANCE = new MarketDataaccess();
	
	public static MarketDataaccess getInstance()
	{
		return INSTANCE;
	}
	
	private MarketDataaccess()
	{
	}
	
	public int createMarketAndReturnId(final Market market)
	{
		int generatedKey = 0;
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO market (inventoryId, ownerAvatarId, marketType) VALUES ('");
		sqlStatementAsString.append(market.getInventoryId());
		sqlStatementAsString.append("','");
		sqlStatementAsString.append(market.getOwnerAvatarId());
		sqlStatementAsString.append("','");
		sqlStatementAsString.append(market.getMarketType());
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
			System.out.println("Error while creating Market: " + sqlEx);
		}
		return generatedKey;
	}
	
	public void createMarketResource(final MarketResource marketResource)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO marketresource (marketId, resource) VALUES ('");
		sqlStatementAsString.append(marketResource.getMarketId());
		sqlStatementAsString.append("','");
		sqlStatementAsString.append(marketResource.getResource().getName());
		sqlStatementAsString.append("');");
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
			System.out.println("Error while creating MarketResource: " + sqlEx);
		}
	}
	
	public HashMap<Resource, MarketResource> getMarketResourceOfMarketId(
			final int marketId)
	{
		HashMap<Resource, MarketResource> resourceOptions = new HashMap<Resource, MarketResource>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM marketresource WHERE marketId ='");
		sqlStatementAsString.append(marketId);
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
				Resource currentResource = Resource.valueOf(result
						.getString("Resource"));
				MarketResource marketResource = new MarketResource();
				marketResource.setMarketResourceId(result
						.getInt("marketResourceId"));
				marketResource.setMarketId(result.getInt("marketId"));
				marketResource.setResource(currentResource);
				marketResource.setPriceBuy(result.getInt("priceBuy"));
				marketResource.setPriceSell(result.getInt("priceSell"));
				marketResource.setMaxAmount(result.getInt("maxAmount"));
				marketResource.setMinAmount(result.getInt("minAmount"));
				marketResource.setAmountCounterBuy(result
						.getInt("amountCounterBuy"));
				marketResource.setAmountCounterSell(result
						.getInt("amountCounterSell"));
				marketResource.setLastBuyCycle(result.getInt("lastBuyCycle"));
				marketResource.setLastSellCycle(result.getInt("lastSellCycle"));
				resourceOptions.put(currentResource, marketResource);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return resourceOptions;
	}
	
	public Market getMarketWithId(final int marketId)
	{
		Market market = new Market();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM market WHERE marketId ='");
		sqlStatementAsString.append(marketId);
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
				market.setMarketId(result.getInt("marketId"));
				market.setInventoryId(result.getInt("inventoryId"));
				market.setOwnerAvatarId(result.getInt("ownerAvatarId"));
				market.setMarketType(result.getString("marketType"));
			}
			sqlConnection.close();
		}
		
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return market;
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
	
	public void updateMarketResource(final MarketResource marketResource)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE marketresource SET priceBuy = ");
		sqlStatementAsString.append(marketResource.getPriceBuy());
		sqlStatementAsString.append(", priceSell = ");
		sqlStatementAsString.append(marketResource.getPriceSell());
		sqlStatementAsString.append(", maxAmount = ");
		sqlStatementAsString.append(marketResource.getMaxAmount());
		sqlStatementAsString.append(", minAmount = ");
		sqlStatementAsString.append(marketResource.getMinAmount());
		sqlStatementAsString.append(", amountCounterBuy = ");
		sqlStatementAsString.append(marketResource.getAmountCounterBuy());
		sqlStatementAsString.append(", amountCounterSell = ");
		sqlStatementAsString.append(marketResource.getAmountCounterSell());
		sqlStatementAsString.append(", lastBuyCycle = ");
		sqlStatementAsString.append(marketResource.getLastBuyCycle());
		sqlStatementAsString.append(", lastSellCycle = ");
		sqlStatementAsString.append(marketResource.getLastSellCycle());
		sqlStatementAsString.append(" WHERE marketResourceId = ");
		sqlStatementAsString.append(marketResource.getMarketResourceId());
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
			System.out.println("Error while updating MarketResource: " + sqlEx);
		}
	}
}

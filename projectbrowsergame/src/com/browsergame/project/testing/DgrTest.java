
package com.browsergame.project.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.factory.LocationFactory;
import com.browsergame.project.market.datatransfer.MarketResource;
import com.browsergame.project.market.factory.MarketFactory;

public class DgrTest
{
	
	public static void changeToMainForLocationHourTick(final String[] args)
	{
		Calendar currentTime = Calendar.getInstance();
		System.out.println("HourTick start at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
		LocationFactory locationFactory = LocationFactory.getInstance();
		LinkedList<Location> allLocations = locationFactory.getAllLocations();
		for (Location currentLocation : allLocations)
		{
			for (MarketResource currentMarketResource : currentLocation
					.getLocationMarket().getMarketResources().values())
			{
				currentMarketResource.setLastBuyCycle(currentMarketResource
						.getLastBuyCycle() + 1);
				currentMarketResource.setLastSellCycle(currentMarketResource
						.getLastSellCycle() + 1);
				if (currentMarketResource.getLastBuyCycle() >= 7)
				{
					if (currentMarketResource.getPriceSell() != currentMarketResource
							.getPriceBuy())
					{
						currentMarketResource.setLastBuyCycle(0);
						currentMarketResource
								.setPriceSell(currentMarketResource
										.getPriceSell() - 1);
					}
				}
				if (currentMarketResource.getLastSellCycle() >= 7)
				{
					if (currentMarketResource.getPriceSell() != currentMarketResource
							.getPriceBuy())
					{
						currentMarketResource.setLastSellCycle(0);
						currentMarketResource.setPriceBuy(currentMarketResource
								.getPriceBuy() + 1);
					}
				}
				MarketFactory.getInstance().updateMarketResource(
						currentMarketResource);
			}
		}
		currentTime.setTimeInMillis(System.currentTimeMillis());
		System.out.println("HourTick finished at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
	}
	
	public static void changeToMainForNextQuaderTick(final String[] args)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		if (calendar.get(Calendar.MINUTE) > 0)
		{
			if (calendar.get(Calendar.MINUTE) > 15)
			{
				if (calendar.get(Calendar.MINUTE) > 30)
				{
					if (calendar.get(Calendar.MINUTE) > 45)
					{
						calendar.add(Calendar.HOUR, 1);
						calendar.set(Calendar.MINUTE, 0);
					}
					else
					{
						calendar.set(Calendar.MINUTE, 45);
					}
				}
				else
				{
					calendar.set(Calendar.MINUTE, 30);
				}
			}
			else
			{
				calendar.set(Calendar.MINUTE, 15);
			}
		}
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println(calendar.getTimeInMillis());
	}
	
	public static void main(final String[] args)
	{
		// linkedlist<integer> alllocationids = locationfactory.getinstance()
		// .getalllocationids();
		// for (int currentlocationid : alllocationids)
		// {
		// avatarfactory.getinstance().createnewmastersforlocation(
		// currentlocationid);
		// }
		AvatarFactory.getInstance().moveAllMastersToRandomLocation();
	}
	
	public static Connection openConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection sqlConnection;
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		sqlConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/projectbrowsergame", "root", "root");
		return sqlConnection;
	}
	
	public static void setToMainToGiveAllFieldsInventorys(final String[] args)
	{
		LinkedList<Field> allField = new LinkedList<Field>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM field;");
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
				field.setFieldId(result.getInt("fieldId"));
				field.setInventoryId(result.getInt("inventoryId"));
				allField.add(field);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		for (Field currentField : allField)
		{
			if (currentField.getInventoryId() == 0)
			{
				currentField.setInventoryId(InventoryFactory.getInstance()
						.createNewInventoryAndReturnId());
				FieldFactory.getInstance().addInventoryIdToField(currentField);
				System.out.println("Updatet Field with Id: "
						+ currentField.getFieldId());
			}
		}
	}
}


package com.browsergame.project.core.controller;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimerTask;

import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.factory.LocationFactory;
import com.browsergame.project.market.datatransfer.MarketResource;
import com.browsergame.project.market.factory.MarketFactory;

public class LocationHourTickTimer extends TimerTask
{
	
	@Override
	public void run()
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
			locationFactory.doLocationTick(currentLocation);
			for (MarketResource currentMarketResource : currentLocation
					.getLocationMarket().getMarketResources().values())
			{
				boolean marketResourceHasChanged = false;
				if (currentMarketResource.getLastBuyCycle() >= 7)
				{
					if (currentMarketResource.getPriceSell() != currentMarketResource
							.getPriceBuy())
					{
						currentMarketResource.setLastBuyCycle(0);
						currentMarketResource
								.setPriceSell(currentMarketResource
										.getPriceSell() - 1);
						marketResourceHasChanged = true;
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
						marketResourceHasChanged = true;
					}
				}
				if (marketResourceHasChanged)
				{
					MarketFactory.getInstance().updateMarketResource(
							currentMarketResource);
				}
			}
		}
		currentTime.setTimeInMillis(System.currentTimeMillis());
		System.out.println("HourTick finished at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
	}
}

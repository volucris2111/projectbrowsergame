
package com.browsergame.project.market.factory;

import java.util.HashMap;

import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.market.dataaccess.MarketDataaccess;
import com.browsergame.project.market.datatransfer.Market;
import com.browsergame.project.market.datatransfer.MarketResource;

public class MarketFactory
{
	private static MarketFactory INSTANCE = new MarketFactory();
	
	public static MarketFactory getInstance()
	{
		return INSTANCE;
	}
	
	private MarketFactory()
	{
	}
	
	public void createAllMarketResourcesForMarketId(final int marketId)
	{
		for (Resource currentResource : Resource.values())
		{
			MarketResource marketResource = new MarketResource();
			marketResource.setMarketId(marketId);
			marketResource.setResource(currentResource);
			MarketDataaccess.getInstance().createMarketResource(marketResource);
		}
	}
	
	public int createMarketAndReturnId(final Market market)
	{
		int marketId = MarketDataaccess.getInstance().createMarketAndReturnId(
				market);
		createAllMarketResourcesForMarketId(marketId);
		return marketId;
	}
	
	public void createMarketResource(final MarketResource marketResource)
	{
		MarketDataaccess.getInstance().createMarketResource(marketResource);
	}
	
	public HashMap<Resource, MarketResource> getMarketResourceOfMarketId(
			final int marketId)
	{
		return MarketDataaccess.getInstance().getMarketResourceOfMarketId(
				marketId);
	}
	
	public Market getMarketWithId(final int marketId)
	{
		Market market = MarketDataaccess.getInstance()
				.getMarketWithId(marketId);
		if (market.getInventoryId() != 0)
		{
			market.setInventory(InventoryFactory.getInstance()
					.getInventorWithId(market.getInventoryId()));
		}
		market.setMarketResources(getMarketResourceOfMarketId(marketId));
		return market;
	}
	
	public void updateMarketResource(final MarketResource marketResource)
	{
		MarketDataaccess.getInstance().updateMarketResource(marketResource);
	}
}

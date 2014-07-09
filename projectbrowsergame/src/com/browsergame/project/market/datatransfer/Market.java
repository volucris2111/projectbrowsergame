
package com.browsergame.project.market.datatransfer;

import java.io.Serializable;
import java.util.HashMap;

import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;

public class Market implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 929186239441099468L;
	
	private Inventory inventory;
	
	private int inventoryId;
	
	private int marketId;
	
	private HashMap<Resource, MarketResource> marketResources;
	
	private String marketType;
	
	private int ownerAvatarId;
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public int getInventoryId()
	{
		return inventoryId;
	}
	
	public int getMarketId()
	{
		return marketId;
	}
	
	public HashMap<Resource, MarketResource> getMarketResources()
	{
		return marketResources;
	}
	
	public String getMarketType()
	{
		return marketType;
	}
	
	public int getOwnerAvatarId()
	{
		return ownerAvatarId;
	}
	
	public void setInventory(final Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	public void setInventoryId(final int inventoryId)
	{
		this.inventoryId = inventoryId;
	}
	
	public void setMarketId(final int marketId)
	{
		this.marketId = marketId;
	}
	
	public void setMarketResources(
			final HashMap<Resource, MarketResource> marketResources)
	{
		this.marketResources = marketResources;
	}
	
	public void setMarketType(final String marketType)
	{
		this.marketType = marketType;
	}
	
	public void setOwnerAvatarId(final int ownerAvatarId)
	{
		this.ownerAvatarId = ownerAvatarId;
	}
}

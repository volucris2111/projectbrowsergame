
package com.browsergame.project.inventory.datatransfer;

import java.io.Serializable;
import java.util.HashMap;

public class Inventory implements Serializable
{
	private static final long serialVersionUID = 3147491940944794815L;
	
	private int coins;
	
	private int inventoryId;
	
	private HashMap<Resource, Integer> resources = new HashMap<Resource, Integer>();
	
	public int getCoins()
	{
		return coins;
	}
	
	public int getInventoryId()
	{
		return inventoryId;
	}
	
	public HashMap<Resource, Integer> getResources()
	{
		return resources;
	}
	
	public int getValueOfResource(final String resource)
	{
		int valueOfresource = 0;
		if (resources.get(Resource.valueOf(resource)) != null)
		{
			valueOfresource = resources.get(Resource.valueOf(resource));
		}
		return valueOfresource;
	}
	
	public void setCoins(final int coins)
	{
		this.coins = coins;
	}
	
	public void setInventoryId(final int inventoryId)
	{
		this.inventoryId = inventoryId;
	}
	
	public void setResources(final HashMap<Resource, Integer> resources)
	{
		this.resources = resources;
	}
	
}

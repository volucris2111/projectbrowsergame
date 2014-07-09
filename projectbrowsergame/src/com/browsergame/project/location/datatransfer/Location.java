
package com.browsergame.project.location.datatransfer;

import java.io.Serializable;
import java.util.HashMap;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.market.datatransfer.Market;
import com.browsergame.project.struggling.datatransfer.Struggling;

public class Location implements Serializable
{
	private static final long serialVersionUID = -6689277870173469788L;
	
	private HashMap<Resource, Double> basicGoods = new HashMap<Resource, Double>();
	
	private int currentStorage;
	
	private HashMap<Resource, Double> growingGoods = new HashMap<Resource, Double>();
	
	private Inventory inventory;
	
	private int inventoryId;
	
	private String lastHalfDayReport;
	
	private String lastHourReport;
	
	private Market locationFreeMarket;
	
	private int locationFreeMarketId;
	
	private int locationId;
	
	private Market locationMarket;
	
	private int locationMarketId;
	
	private LocationType locationType;
	
	private int locationTypeId;
	
	private LocationUpgrades locationUpgrades;
	
	private HashMap<Resource, Double> luxuryGoods = new HashMap<Resource, Double>();
	
	private int maxStorage;
	
	private String name;
	
	private Avatar ownerAvatar;
	
	private int ownerAvatarId;
	
	private int population;
	
	private int positionX;
	
	private int positionY;
	
	private HashMap<Resource, Double> productingGoods = new HashMap<Resource, Double>();
	
	private Struggling struggling;
	
	private int strugglingId;
	
	private Tavern tavern = new Tavern();
	
	public HashMap<Resource, Double> getBasicGoods()
	{
		return basicGoods;
	}
	
	public int getCurrentStorage()
	{
		return currentStorage;
	}
	
	public HashMap<Resource, Double> getGrowingGoods()
	{
		return growingGoods;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public int getInventoryId()
	{
		return inventoryId;
	}
	
	public String getLastHalfDayReport()
	{
		return lastHalfDayReport;
	}
	
	public String getLastHourReport()
	{
		return lastHourReport;
	}
	
	public Market getLocationFreeMarket()
	{
		return locationFreeMarket;
	}
	
	public int getLocationFreeMarketId()
	{
		return locationFreeMarketId;
	}
	
	public int getLocationId()
	{
		return locationId;
	}
	
	public Market getLocationMarket()
	{
		return locationMarket;
	}
	
	public int getLocationMarketId()
	{
		return locationMarketId;
	}
	
	public LocationType getLocationType()
	{
		return locationType;
	}
	
	public int getLocationTypeId()
	{
		return locationTypeId;
	}
	
	public LocationUpgrades getLocationUpgrades()
	{
		return locationUpgrades;
	}
	
	public HashMap<Resource, Double> getLuxuryGoods()
	{
		return luxuryGoods;
	}
	
	public int getMaxStorage()
	{
		return maxStorage;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Avatar getOwnerAvatar()
	{
		return ownerAvatar;
	}
	
	public int getOwnerAvatarId()
	{
		return ownerAvatarId;
	}
	
	public int getPopulation()
	{
		return population;
	}
	
	public int getPositionX()
	{
		return positionX;
	}
	
	public int getPositionY()
	{
		return positionY;
	}
	
	public HashMap<Resource, Double> getProductingGoods()
	{
		return productingGoods;
	}
	
	public Struggling getStruggling()
	{
		return struggling;
	}
	
	public int getStrugglingId()
	{
		return strugglingId;
	}
	
	public Tavern getTavern()
	{
		return tavern;
	}
	
	public void setBasicGoods(final HashMap<Resource, Double> basicGoods)
	{
		this.basicGoods = basicGoods;
	}
	
	public void setCurrentStorage(final int currentStorage)
	{
		this.currentStorage = currentStorage;
	}
	
	public void setGrowingGoods(final HashMap<Resource, Double> growingGoods)
	{
		this.growingGoods = growingGoods;
	}
	
	public void setInventory(final Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	public void setInventoryId(final int inventoryId)
	{
		this.inventoryId = inventoryId;
	}
	
	public void setLastHalfDayReport(final String lastHalfDayReport)
	{
		this.lastHalfDayReport = lastHalfDayReport;
	}
	
	public void setLastHourReport(final String lastHourReport)
	{
		this.lastHourReport = lastHourReport;
	}
	
	public void setLocationFreeMarket(final Market locationFreeMarket)
	{
		this.locationFreeMarket = locationFreeMarket;
	}
	
	public void setLocationFreeMarketId(final int locationFreeMarketId)
	{
		this.locationFreeMarketId = locationFreeMarketId;
	}
	
	public void setLocationId(final int locationId)
	{
		this.locationId = locationId;
	}
	
	public void setLocationMarket(final Market locationMarket)
	{
		this.locationMarket = locationMarket;
	}
	
	public void setLocationMarketId(final int locationMarketId)
	{
		this.locationMarketId = locationMarketId;
	}
	
	public void setLocationType(final LocationType locationType)
	{
		this.locationType = locationType;
	}
	
	public void setLocationTypeId(final int locationTypeId)
	{
		this.locationTypeId = locationTypeId;
	}
	
	public void setLocationUpgrades(final LocationUpgrades locationUpgrades)
	{
		this.locationUpgrades = locationUpgrades;
	}
	
	public void setLuxuryGoods(final HashMap<Resource, Double> luxuryGoods)
	{
		this.luxuryGoods = luxuryGoods;
	}
	
	public void setMaxStorage(final int maxStorage)
	{
		this.maxStorage = maxStorage;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setOwnerAvatar(final Avatar ownerAvatar)
	{
		this.ownerAvatar = ownerAvatar;
	}
	
	public void setOwnerAvatarId(final int ownerAvatarId)
	{
		this.ownerAvatarId = ownerAvatarId;
	}
	
	public void setPopulation(final int population)
	{
		this.population = population;
	}
	
	public void setPositionX(final int positionX)
	{
		this.positionX = positionX;
	}
	
	public void setPositionY(final int positionY)
	{
		this.positionY = positionY;
	}
	
	public void setProductingGoods(
			final HashMap<Resource, Double> productingGoods)
	{
		this.productingGoods = productingGoods;
	}
	
	public void setStruggling(final Struggling struggling)
	{
		this.struggling = struggling;
	}
	
	public void setStrugglingId(final int strugglingId)
	{
		this.strugglingId = strugglingId;
	}
	
	public void setTavern(final Tavern tavern)
	{
		this.tavern = tavern;
	}
}

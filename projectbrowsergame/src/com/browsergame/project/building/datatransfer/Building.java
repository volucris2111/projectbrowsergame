
package com.browsergame.project.building.datatransfer;

import java.io.Serializable;
import java.sql.Date;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.buildingupgrade.datatransfer.BuildingUpgrades;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.market.datatransfer.Market;

public class Building implements Serializable
{
	private static final long serialVersionUID = -3002043054713444572L;
	
	private int buildingId;
	
	private Market buildingMarket = new Market();
	
	private int buildingMarketId;
	
	private BuildingType buildingType;
	
	private int buildingTypeId;
	
	private BuildingUpgrades buildingUpgrades = new BuildingUpgrades();
	
	private int buildingUpgradesId;
	
	private int currentStorage;
	
	private long finishingProduction;
	
	private Date finishingProductionAsDate;
	
	private Inventory inventory = new Inventory();
	
	private int inventoryId;
	
	private String lastHalfDayReport;
	
	private String lastHourReport;
	
	private int maxStorage;
	
	private String name;
	
	private String objectType;
	
	private Avatar ownerAvatar;
	
	private int ownerAvatarId;
	
	private int positionX;
	
	private int positionY;
	
	private int pricesId;
	
	private int selectedTools;
	
	private int selectedWorkers;
	
	public int getBuildingId()
	{
		return buildingId;
	}
	
	public Market getBuildingMarket()
	{
		return buildingMarket;
	}
	
	public int getBuildingMarketId()
	{
		return buildingMarketId;
	}
	
	public BuildingType getBuildingType()
	{
		return buildingType;
	}
	
	public int getBuildingTypeId()
	{
		return buildingTypeId;
	}
	
	public BuildingUpgrades getBuildingUpgrades()
	{
		return buildingUpgrades;
	}
	
	public int getBuildingUpgradesId()
	{
		return buildingUpgradesId;
	}
	
	public int getCurrentStorage()
	{
		return currentStorage;
	}
	
	public long getFinishingProduction()
	{
		return finishingProduction;
	}
	
	public Date getFinishingProductionAsDate()
	{
		return finishingProductionAsDate;
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
	
	public int getMaxStorage()
	{
		return maxStorage;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getObjectType()
	{
		return objectType;
	}
	
	public Avatar getOwnerAvatar()
	{
		return ownerAvatar;
	}
	
	public int getOwnerAvatarId()
	{
		return ownerAvatarId;
	}
	
	public int getPositionX()
	{
		return positionX;
	}
	
	public int getPositionY()
	{
		return positionY;
	}
	
	public int getPricesId()
	{
		return pricesId;
	}
	
	public int getSelectedTools()
	{
		return selectedTools;
	}
	
	public int getSelectedWorkers()
	{
		return selectedWorkers;
	}
	
	public void setBuildingId(final int buildingId)
	{
		this.buildingId = buildingId;
	}
	
	public void setBuildingMarket(final Market buildingMarket)
	{
		this.buildingMarket = buildingMarket;
	}
	
	public void setBuildingMarketId(final int buildingMarketId)
	{
		this.buildingMarketId = buildingMarketId;
	}
	
	public void setBuildingType(final BuildingType buildingType)
	{
		this.buildingType = buildingType;
	}
	
	public void setBuildingTypeId(final int buildingTypeId)
	{
		this.buildingTypeId = buildingTypeId;
	}
	
	public void setBuildingUpgrades(final BuildingUpgrades buildingUpgrades)
	{
		this.buildingUpgrades = buildingUpgrades;
	}
	
	public void setBuildingUpgradesId(final int buildingUpgradesId)
	{
		this.buildingUpgradesId = buildingUpgradesId;
	}
	
	public void setCurrentStorage(final int currentStorage)
	{
		this.currentStorage = currentStorage;
	}
	
	public void setFinishingProduction(final long finishingProduction)
	{
		this.finishingProduction = finishingProduction;
	}
	
	public void setFinishingProductionAsDate(
			final Date finishingProductionAsDate)
	{
		this.finishingProductionAsDate = finishingProductionAsDate;
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
	
	public void setMaxStorage(final int maxStorage)
	{
		this.maxStorage = maxStorage;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setObjectType(final String objectType)
	{
		this.objectType = objectType;
	}
	
	public void setOwnerAvatar(final Avatar ownerAvatar)
	{
		this.ownerAvatar = ownerAvatar;
	}
	
	public void setOwnerAvatarId(final int ownerAvatarId)
	{
		this.ownerAvatarId = ownerAvatarId;
	}
	
	public void setPositionX(final int positionX)
	{
		this.positionX = positionX;
	}
	
	public void setPositionY(final int positionY)
	{
		this.positionY = positionY;
	}
	
	public void setPricesId(final int pricesId)
	{
		this.pricesId = pricesId;
	}
	
	public void setSelectedTools(final int selectedTools)
	{
		this.selectedTools = selectedTools;
	}
	
	public void setSelectedWorkers(final int selectedWorkers)
	{
		this.selectedWorkers = selectedWorkers;
	}
}

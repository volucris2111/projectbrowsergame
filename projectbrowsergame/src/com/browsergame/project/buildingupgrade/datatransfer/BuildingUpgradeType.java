
package com.browsergame.project.buildingupgrade.datatransfer;

import java.io.Serializable;
import java.util.HashMap;

import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingUpgradeType implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4834923061658128289L;
	
	private int buildingUpgradeId;
	
	private int buildingUpgradeTypeId;
	
	private int coins;
	
	private HashMap<Resource, Integer> costs = new HashMap<Resource, Integer>();
	
	private String description;
	
	private int lvl;
	
	private String name;
	
	private String stringForJSP;
	
	public int getBuildingUpgradeId()
	{
		return buildingUpgradeId;
	}
	
	public int getBuildingUpgradeTypeId()
	{
		return buildingUpgradeTypeId;
	}
	
	public int getCoins()
	{
		return coins;
	}
	
	public HashMap<Resource, Integer> getCosts()
	{
		return costs;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getLvl()
	{
		return lvl;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getStringForJSP()
	{
		return stringForJSP;
	}
	
	public void setBuildingUpgradeId(final int buildingUpgradeId)
	{
		this.buildingUpgradeId = buildingUpgradeId;
	}
	
	public void setBuildingUpgradeTypeId(final int buildingUpgradeTypeId)
	{
		this.buildingUpgradeTypeId = buildingUpgradeTypeId;
	}
	
	public void setCoins(final int coins)
	{
		this.coins = coins;
	}
	
	public void setCosts(final HashMap<Resource, Integer> costs)
	{
		this.costs = costs;
	}
	
	public void setDescription(final String description)
	{
		this.description = description;
	}
	
	public void setLvl(final int lvl)
	{
		this.lvl = lvl;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setStringForJSP(final String stringForJSP)
	{
		this.stringForJSP = stringForJSP;
	}
	
}

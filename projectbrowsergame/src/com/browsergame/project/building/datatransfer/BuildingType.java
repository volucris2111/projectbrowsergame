
package com.browsergame.project.building.datatransfer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingType implements Serializable
{
	private static final long serialVersionUID = 1670915146586602713L;
	
	private String avatarClassNeededToBuild;
	
	private int buildingCostsId;
	
	private LinkedList<BuildingProduct> buildingProducts = new LinkedList<BuildingProduct>();
	
	private int buildingTypeId;
	
	private HashMap<Resource, Integer> costs = new HashMap<Resource, Integer>();
	
	private boolean enoughResources; // transient
	
	private String imageUrl;
	
	private String name;
	
	private String nameForJsp;
	
	private String terrain;
	
	public String getAvatarClassNeededToBuild()
	{
		return avatarClassNeededToBuild;
	}
	
	public int getBuildingCostsId()
	{
		return buildingCostsId;
	}
	
	public LinkedList<BuildingProduct> getBuildingProducts()
	{
		return buildingProducts;
	}
	
	public int getBuildingTypeId()
	{
		return buildingTypeId;
	}
	
	public HashMap<Resource, Integer> getCosts()
	{
		return costs;
	}
	
	public String getImageUrl()
	{
		return imageUrl;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getNameForJsp()
	{
		return nameForJsp;
	}
	
	public String getTerrain()
	{
		return terrain;
	}
	
	public boolean isEnoughResources()
	{
		return enoughResources;
	}
	
	public void setAvatarClassNeededToBuild(
			final String avatarClassNeededToBuild)
	{
		this.avatarClassNeededToBuild = avatarClassNeededToBuild;
	}
	
	public void setBuildingCostsId(final int buildingCostsId)
	{
		this.buildingCostsId = buildingCostsId;
	}
	
	public void setBuildingProducts(
			final LinkedList<BuildingProduct> buildingProducts)
	{
		this.buildingProducts = buildingProducts;
	}
	
	public void setBuildingTypeId(final int buildingTypeId)
	{
		this.buildingTypeId = buildingTypeId;
	}
	
	public void setCosts(final HashMap<Resource, Integer> costs)
	{
		this.costs = costs;
	}
	
	public void setEnoughResources(final boolean enoughResources)
	{
		this.enoughResources = enoughResources;
	}
	
	public void setImageUrl(final String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setNameForJsp(final String nameForJsp)
	{
		this.nameForJsp = nameForJsp;
	}
	
	public void setTerrain(final String terrain)
	{
		this.terrain = terrain;
	}
}

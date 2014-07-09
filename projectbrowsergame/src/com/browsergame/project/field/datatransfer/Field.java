
package com.browsergame.project.field.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.terrain.datatransfer.Terrain;

public class Field implements Serializable
{
	private static final long serialVersionUID = -6536753174964436470L;
	
	private LinkedList<Avatar> allAvatarsInside;
	
	private LinkedList<Avatar> allAvatarsOutside;
	
	private String backgroundImageUrl;
	
	private boolean buildable = true;
	
	private Building building;
	
	private int buildingId;
	
	private int fieldId;
	
	private String fieldObjectImageUrl;
	
	private Inventory inventory;
	
	private int inventoryId;
	
	private Location location;
	
	private int locationId;
	
	private int positionX;
	
	private int positionY;
	
	private String shownObject;
	
	private Terrain terrain;
	
	private String terrainString;
	
	public LinkedList<Avatar> getAllAvatarsInside()
	{
		return allAvatarsInside;
	}
	
	public LinkedList<Avatar> getAllAvatarsOutside()
	{
		return allAvatarsOutside;
	}
	
	public String getBackgroundImageUrl()
	{
		return backgroundImageUrl;
	}
	
	public Building getBuilding()
	{
		return building;
	}
	
	public int getBuildingId()
	{
		return buildingId;
	}
	
	public int getFieldId()
	{
		return fieldId;
	}
	
	public String getFieldObjectImageUrl()
	{
		return fieldObjectImageUrl;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public int getInventoryId()
	{
		return inventoryId;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public int getLocationId()
	{
		return locationId;
	}
	
	public int getPositionX()
	{
		return positionX;
	}
	
	public int getPositionY()
	{
		return positionY;
	}
	
	public String getShownObject()
	{
		return shownObject;
	}
	
	public Terrain getTerrain()
	{
		return terrain;
	}
	
	public String getTerrainString()
	{
		return terrainString;
	}
	
	public boolean isBuildable()
	{
		return buildable;
	}
	
	public void setAllAvatarsInside(final LinkedList<Avatar> allAvatarsInside)
	{
		this.allAvatarsInside = allAvatarsInside;
	}
	
	public void setAllAvatarsOutside(final LinkedList<Avatar> allAvatarsOutside)
	{
		this.allAvatarsOutside = allAvatarsOutside;
	}
	
	public void setBackgroundImageUrl(final String backgroundImageUrl)
	{
		this.backgroundImageUrl = backgroundImageUrl;
	}
	
	public void setBuildable(final boolean buildable)
	{
		this.buildable = buildable;
	}
	
	public void setBuilding(final Building building)
	{
		this.building = building;
	}
	
	public void setBuildingId(final int buildingId)
	{
		this.buildingId = buildingId;
	}
	
	public void setFieldId(final int fieldId)
	{
		this.fieldId = fieldId;
	}
	
	public void setFieldObjectImageUrl(final String fieldObjectImageUrl)
	{
		this.fieldObjectImageUrl = fieldObjectImageUrl;
	}
	
	public void setInventory(final Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	public void setInventoryId(final int inventoryId)
	{
		this.inventoryId = inventoryId;
	}
	
	public void setLocation(final Location location)
	{
		this.location = location;
	}
	
	public void setLocationId(final int locationId)
	{
		this.locationId = locationId;
	}
	
	public void setPositionX(final int positionX)
	{
		this.positionX = positionX;
	}
	
	public void setPositionY(final int positionY)
	{
		this.positionY = positionY;
	}
	
	public void setShownObject(final String shownObject)
	{
		this.shownObject = shownObject;
	}
	
	public void setTerrain(final Terrain terrain)
	{
		this.terrain = terrain;
	}
	
	public void setTerrainString(final String terrainString)
	{
		this.terrainString = terrainString;
	}
}

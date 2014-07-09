
package com.browsergame.project.adventure.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.building.datatransfer.BuildingType;
import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.map.datatransfer.AdventureMapRow;
import com.browsergame.project.market.datatransfer.Market;
import com.browsergame.project.terrain.constants.TerrainActionsEnum;
import com.browsergame.project.user.datatransfer.User;

public class AdventureForm extends ActionForm
{
	private static final long serialVersionUID = -6095183435065330285L;
	
	private boolean actionsAvailable;
	
	private LinkedList<TerrainActionsEnum> actionsOnCurrentfield = new LinkedList<TerrainActionsEnum>();
	
	private AdventureMapRow[] adventureMap = new AdventureMapRow[5];
	
	private HashSet<Resource> allResources = new HashSet<Resource>();
	
	private LinkedList<BuildingType> buildableBuildings = new LinkedList<BuildingType>();
	
	private String buildingManualProductionMessage;
	
	private boolean buildingOwner;
	
	private String buildingUpgradeMessage;
	
	private String comeFromPage;
	
	private boolean comeFromPost;
	
	private Field currentField;
	
	private Market currentMarket = new Market();
	
	private HashMap<Resource, Integer> displayBasicGoodsNeed = new HashMap<Resource, Integer>();
	
	private HashMap<Resource, Integer> displayLuxuryGoodsNeed = new HashMap<Resource, Integer>();
	
	private HashMap<Resource, Integer> displayProduction = new HashMap<Resource, Integer>();
	
	private HashMap<Resource, Integer> displayProductionConsumption = new HashMap<Resource, Integer>();
	
	private Location location = new Location();
	
	private User loggedInUser;
	
	private String message;
	
	private int selctedBuildingId;
	
	private int selctedLocationId;
	
	private String selectedAction;
	
	private int selectedAvatarId;
	
	private String selectedBuilding;
	
	private int selectedBuildingTypeId;
	
	private int selectedMarketId;
	
	private int selectedPositionX;
	
	private int selectedPositionY;
	
	private int selectedProductionDays;
	
	private int selectedProductionHours;
	
	private String selectedTerrain;
	
	private Avatar userLoggedInHero;
	
	public LinkedList<TerrainActionsEnum> getActionsOnCurrentfield()
	{
		return actionsOnCurrentfield;
	}
	
	public AdventureMapRow[] getAdventureMap()
	{
		return adventureMap;
	}
	
	public HashSet<Resource> getAllResources()
	{
		return allResources;
	}
	
	public LinkedList<BuildingType> getBuildableBuildings()
	{
		return buildableBuildings;
	}
	
	public String getBuildingManualProductionMessage()
	{
		return buildingManualProductionMessage;
	}
	
	public String getBuildingUpgradeMessage()
	{
		return buildingUpgradeMessage;
	}
	
	public String getComeFromPage()
	{
		return comeFromPage;
	}
	
	public Field getCurrentField()
	{
		return currentField;
	}
	
	public Market getCurrentMarket()
	{
		return currentMarket;
	}
	
	public HashMap<Resource, Integer> getDisplayBasicGoodsNeed()
	{
		return displayBasicGoodsNeed;
	}
	
	public HashMap<Resource, Integer> getDisplayLuxuryGoodsNeed()
	{
		return displayLuxuryGoodsNeed;
	}
	
	public HashMap<Resource, Integer> getDisplayProduction()
	{
		return displayProduction;
	}
	
	public HashMap<Resource, Integer> getDisplayProductionConsumption()
	{
		return displayProductionConsumption;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public User getLoggedInUser()
	{
		return loggedInUser;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public int getSelctedBuildingId()
	{
		return selctedBuildingId;
	}
	
	public int getSelctedLocationId()
	{
		return selctedLocationId;
	}
	
	public String getSelectedAction()
	{
		return selectedAction;
	}
	
	public int getSelectedAvatarId()
	{
		return selectedAvatarId;
	}
	
	public String getSelectedBuilding()
	{
		return selectedBuilding;
	}
	
	public int getSelectedBuildingTypeId()
	{
		return selectedBuildingTypeId;
	}
	
	public int getSelectedMarketId()
	{
		return selectedMarketId;
	}
	
	public int getSelectedPositionX()
	{
		return selectedPositionX;
	}
	
	public int getSelectedPositionY()
	{
		return selectedPositionY;
	}
	
	public int getSelectedProductionDays()
	{
		return selectedProductionDays;
	}
	
	public int getSelectedProductionHours()
	{
		return selectedProductionHours;
	}
	
	public String getSelectedTerrain()
	{
		return selectedTerrain;
	}
	
	public Avatar getUserLoggedInHero()
	{
		return userLoggedInHero;
	}
	
	public boolean isActionsAvailable()
	{
		return actionsAvailable;
	}
	
	public boolean isBuildingOwner()
	{
		return buildingOwner;
	}
	
	public boolean isComeFromPost()
	{
		return comeFromPost;
	}
	
	public void setActionsAvailable(final boolean actionsAvailable)
	{
		this.actionsAvailable = actionsAvailable;
	}
	
	public void setActionsOnCurrentfield(
			final LinkedList<TerrainActionsEnum> actionsOnCurrentfield)
	{
		this.actionsOnCurrentfield = actionsOnCurrentfield;
	}
	
	public void setAdventureMap(final AdventureMapRow[] adventureMap)
	{
		this.adventureMap = adventureMap;
	}
	
	public void setAllResources(final HashSet<Resource> allResources)
	{
		this.allResources = allResources;
	}
	
	public void setBuildableBuildings(
			final LinkedList<BuildingType> buildableBuildings)
	{
		this.buildableBuildings = buildableBuildings;
	}
	
	public void setBuildingManualProductionMessage(
			final String buildingManualProductionMessage)
	{
		this.buildingManualProductionMessage = buildingManualProductionMessage;
	}
	
	public void setBuildingOwner(final boolean buildingOwner)
	{
		this.buildingOwner = buildingOwner;
	}
	
	public void setBuildingUpgradeMessage(final String buildingUpgradeMessage)
	{
		this.buildingUpgradeMessage = buildingUpgradeMessage;
	}
	
	public void setComeFromPage(final String comeFromPage)
	{
		this.comeFromPage = comeFromPage;
	}
	
	public void setComeFromPost(final boolean comeFromPost)
	{
		this.comeFromPost = comeFromPost;
	}
	
	public void setCurrentField(final Field currentField)
	{
		this.currentField = currentField;
	}
	
	public void setCurrentMarket(final Market currentMarket)
	{
		this.currentMarket = currentMarket;
	}
	
	public void setDisplayBasicGoodsNeed(
			final HashMap<Resource, Integer> displayBasicGoodsNeed)
	{
		this.displayBasicGoodsNeed = displayBasicGoodsNeed;
	}
	
	public void setDisplayLuxuryGoodsNeed(
			final HashMap<Resource, Integer> displayLuxuryGoodsNeed)
	{
		this.displayLuxuryGoodsNeed = displayLuxuryGoodsNeed;
	}
	
	public void setDisplayProduction(
			final HashMap<Resource, Integer> displayProduction)
	{
		this.displayProduction = displayProduction;
	}
	
	public void setDisplayProductionConsumption(
			final HashMap<Resource, Integer> displayProductionConsumption)
	{
		this.displayProductionConsumption = displayProductionConsumption;
	}
	
	public void setLocation(final Location location)
	{
		this.location = location;
	}
	
	public void setLoggedInUser(final User loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}
	
	public void setMessage(final String message)
	{
		this.message = message;
	}
	
	public void setSelctedBuildingId(final int selctedBuildingId)
	{
		this.selctedBuildingId = selctedBuildingId;
	}
	
	public void setSelctedLocationId(final int selctedLocationId)
	{
		this.selctedLocationId = selctedLocationId;
	}
	
	public void setSelectedAction(final String selectedAction)
	{
		this.selectedAction = selectedAction;
	}
	
	public void setSelectedAvatarId(final int selectedAvatarId)
	{
		this.selectedAvatarId = selectedAvatarId;
	}
	
	public void setSelectedBuilding(final String selectedBuilding)
	{
		this.selectedBuilding = selectedBuilding;
	}
	
	public void setSelectedBuildingTypeId(final int selectedBuildingTypeId)
	{
		this.selectedBuildingTypeId = selectedBuildingTypeId;
	}
	
	public void setSelectedMarketId(final int selectedMarketId)
	{
		this.selectedMarketId = selectedMarketId;
	}
	
	public void setSelectedPositionX(final int selectedPositionX)
	{
		this.selectedPositionX = selectedPositionX;
	}
	
	public void setSelectedPositionY(final int selectedPositionY)
	{
		this.selectedPositionY = selectedPositionY;
	}
	
	public void setSelectedProductionDays(final int selectedProductionDays)
	{
		this.selectedProductionDays = selectedProductionDays;
	}
	
	public void setSelectedProductionHours(final int selectedProductionHours)
	{
		this.selectedProductionHours = selectedProductionHours;
	}
	
	public void setSelectedTerrain(final String selectedTerrain)
	{
		this.selectedTerrain = selectedTerrain;
	}
	
	public void setUserLoggedInHero(final Avatar userLoggedInHero)
	{
		this.userLoggedInHero = userLoggedInHero;
	}
}

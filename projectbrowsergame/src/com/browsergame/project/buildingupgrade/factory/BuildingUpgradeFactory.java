
package com.browsergame.project.buildingupgrade.factory;

import java.util.Map.Entry;

import com.browsergame.project.buildingupgrade.dataaccess.BuildingUpgradeDataaccess;
import com.browsergame.project.buildingupgrade.datatransfer.BuildingUpgradeType;
import com.browsergame.project.buildingupgrade.datatransfer.BuildingUpgrades;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingUpgradeFactory
{
	private static BuildingUpgradeFactory INSTANCE = new BuildingUpgradeFactory();
	
	public static BuildingUpgradeFactory getInstance()
	{
		return INSTANCE;
	}
	
	private BuildingUpgradeFactory()
	{
	}
	
	public boolean checkResourcesForBuildingUpgradeAndRemoveCostFromInventory(
			final Inventory inventory, final StringBuilder message,
			final String upgradeName, final int upgradeLvl)
	{
		boolean enoughResources = true;
		BuildingUpgradeType buildingUpgradeType = BuildingUpgradeFactory
				.getInstance().getBuildingUpgradeTypeByNameAndLvl(upgradeName,
						upgradeLvl + 1);
		for (Entry<Resource, Integer> currentResource : buildingUpgradeType
				.getCosts().entrySet())
		{
			if (currentResource.getValue() > (inventory.getResources().get(
					currentResource.getKey()) != null ? inventory
					.getResources().get(currentResource.getKey()) : 0))
			{
				message.append("Nicht genug Rohstoffe im Gebäude für diesen Ausbau!<br/>");
				enoughResources = false;
				break;
			}
		}
		if (buildingUpgradeType.getCoins() > inventory.getCoins())
		{
			message.append("Nicht genug Münzen im Gebäude für diesen Ausbau!");
			enoughResources = false;
		}
		if (enoughResources)
		{
			for (Entry<Resource, Integer> currentResource : buildingUpgradeType
					.getCosts().entrySet())
			{
				inventory
						.getResources()
						.put(currentResource.getKey(),
								((inventory.getResources().get(
										currentResource.getKey()) != null ? inventory
										.getResources().get(
												currentResource.getKey()) : 0) - currentResource
										.getValue()));
				
			}
			inventory.setCoins(inventory.getCoins()
					- buildingUpgradeType.getCoins());
		}
		return enoughResources;
	}
	
	public void fillBuildingUpgradeCostsOfType(
			final BuildingUpgradeType buildingUpgradeType)
	{
		BuildingUpgradeDataaccess.getInstance().fillBuildingUpgradeCostsOfType(
				buildingUpgradeType);
	}
	
	public BuildingUpgrades getBuildingUpgradesByBuildingId(final int buildingId)
	{
		return BuildingUpgradeDataaccess.getInstance()
				.getBuildingUpgradesByBuildingId(buildingId);
	}
	
	public BuildingUpgradeType getBuildingUpgradeTypeByNameAndLvl(
			final String name, final int lvl)
	{
		BuildingUpgradeType buildingUpgradeType = new BuildingUpgradeType();
		buildingUpgradeType = BuildingUpgradeDataaccess.getInstance()
				.getBuildingUpgradeTypeByName(name);
		buildingUpgradeType.setLvl(lvl);
		fillBuildingUpgradeCostsOfType(buildingUpgradeType);
		return buildingUpgradeType;
	}
	
	public void insertOrUpdateBuildingUpgrades(
			final BuildingUpgrades buildingUpgrades)
	{
		BuildingUpgradeDataaccess.getInstance().insertOrUpdateBuildingUpgrades(
				buildingUpgrades);
	}
	
}

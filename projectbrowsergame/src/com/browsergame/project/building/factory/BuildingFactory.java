
package com.browsergame.project.building.factory;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.building.dataaccess.BuildingDataaccess;
import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.building.datatransfer.BuildingProduct;
import com.browsergame.project.building.datatransfer.BuildingType;
import com.browsergame.project.buildingupgrade.factory.BuildingUpgradeFactory;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.market.factory.MarketFactory;
import com.browsergame.project.skill.factory.SkillFactory;

public class BuildingFactory
{
	private static BuildingFactory INSTANCE = new BuildingFactory();
	
	public static BuildingFactory getInstance()
	{
		return INSTANCE;
	}
	
	private BuildingFactory()
	{
		
	}
	
	public boolean checkForFirstBuildingOfAvatarId(final int avatarId)
	{
		return BuildingDataaccess.getInstance()
				.checkForFirstBuildingOfAvatarId(avatarId);
	}
	
	public int createBuilding(final Building building)
	{
		int buildingId = BuildingDataaccess.getInstance()
				.createBuildingAndReturnId(building);
		return buildingId;
	}
	
	public void doConsumptionOfProduction(final Building currentBuilding,
			final int productionHoursTotal)
	{
		Inventory buildingInventory = currentBuilding.getInventory();
		int consumptionFood = 0;
		int consumptionTools = 0;
		int consumptionClothes = 0;
		int consumptionCoins = 0;
		
		consumptionCoins = productionHoursTotal * 5;
		consumptionFood = productionHoursTotal / 12;
		
		consumptionTools = 2 * productionHoursTotal / 12;
		if (productionHoursTotal % 12 > 0)
		{
			consumptionTools = consumptionTools + 2;
			consumptionFood++;
		}
		consumptionTools = consumptionTools
				* currentBuilding.getSelectedTools();
		consumptionTools = consumptionTools
				+ (currentBuilding.getSelectedWorkers() * productionHoursTotal / 24);
		consumptionFood = consumptionFood
				* currentBuilding.getSelectedWorkers();
		consumptionClothes = productionHoursTotal / 24;
		
		if (productionHoursTotal % 24 > 0)
		{
			consumptionTools = consumptionTools
					+ currentBuilding.getSelectedWorkers();
			consumptionClothes++;
		}
		
		consumptionClothes = consumptionClothes
				* currentBuilding.getSelectedWorkers();
		buildingInventory.getResources().put(
				Resource.FOOD,
				buildingInventory.getResources().get(Resource.FOOD)
						- consumptionFood);
		buildingInventory.getResources().put(
				Resource.TOOL,
				buildingInventory.getResources().get(Resource.TOOL)
						- consumptionTools);
		buildingInventory.getResources().put(
				Resource.CLOTHES,
				buildingInventory.getResources().get(Resource.CLOTHES)
						- consumptionClothes);
		buildingInventory.setCoins(buildingInventory.getCoins()
				- consumptionCoins);
		InventoryFactory.getInstance().updateInventory(buildingInventory);
	}
	
	public void doTickForBuilding(final Building building)
	{
		int productivity = building.getSelectedWorkers()
				* (building.getSelectedTools() + 1);
		if (productivity * 2 < building.getMaxStorage()
				- building.getCurrentStorage())
		{
			for (BuildingProduct buildingProduct : building.getBuildingType()
					.getBuildingProducts())
			{
				
				building.getInventory()
						.getResources()
						.put(buildingProduct.getResource(),
								(building.getInventory().getResources()
										.get(buildingProduct.getResource()) != null ? building
										.getInventory().getResources()
										.get(buildingProduct.getResource())
										: 0)
										+ buildingProduct.getFactor()
										* productivity);
			}
			InventoryFactory.getInstance().updateInventory(
					building.getInventory());
			building.setLastHourReport("");
		}
		else
		{
			building.setLastHourReport("Nicht genug Lagerplatz!");
		}
	}
	
	public LinkedList<Building> getAllBuildings()
	{
		LinkedList<Building> allBuildings = BuildingDataaccess.getInstance()
				.getAllBuildings();
		for (Building currentBuilding : allBuildings)
		{
			setBuildingObjects(currentBuilding);
		}
		return allBuildings;
	}
	
	public Building getBuildingById(final int buildingId)
	{
		Building building = BuildingDataaccess.getInstance().getBuildingById(
				buildingId);
		setBuildingObjects(building);
		return building;
	}
	
	public Building getBuildingByPosition(final int positionX,
			final int positionY)
	{
		Building building = BuildingDataaccess.getInstance()
				.getBuildingByPosition(positionX, positionY);
		building.setOwnerAvatar(AvatarFactory.getInstance().getAvatarById(
				building.getOwnerAvatarId()));
		building.setBuildingUpgrades(BuildingUpgradeFactory.getInstance()
				.getBuildingUpgradesByBuildingId(building.getBuildingId()));
		building.setMaxStorage(building.getBuildingUpgrades().getStorage() * 500);
		building.setInventory(InventoryFactory.getInstance().getInventorWithId(
				building.getInventoryId()));
		for (int currentAmount : building.getInventory().getResources()
				.values())
		{
			building.setCurrentStorage(building.getCurrentStorage()
					+ currentAmount);
		}
		building.setInventory(InventoryFactory.getInstance().getInventorWithId(
				building.getInventoryId()));
		return building;
	}
	
	public LinkedList<BuildingProduct> getBuildingProductsOfBuildingTypeIdAsList(
			final int buildingTypeId)
	{
		return BuildingDataaccess.getInstance()
				.getBuildingProductsByBuildingTypeIdAsList(buildingTypeId);
	}
	
	public BuildingType getBuildingTypeById(final int buildingTypeId)
	{
		BuildingType buildingType = BuildingDataaccess.getInstance()
				.getBuildingTypeById(buildingTypeId);
		setBuildingTypeObjects(buildingType);
		return buildingType;
	}
	
	public LinkedList<BuildingType> getBuildingTypesByTerrain(
			final String terrain)
	{
		LinkedList<BuildingType> buildingTypes = BuildingDataaccess
				.getInstance().getBuildingTypesByTerrain(terrain);
		for (BuildingType currentBuildingType : buildingTypes)
		{
			setBuildingTypeObjects(currentBuildingType);
		}
		return buildingTypes;
	}
	
	public String manualProducing(final Building building, final Avatar avatar)
	{
		StringBuilder message = new StringBuilder();
		message.append("Du hast ");
		Iterator<BuildingProduct> buildingProductIerator = building
				.getBuildingType().getBuildingProducts().iterator();
		BuildingProduct buildingProduct;
		while (buildingProductIerator.hasNext())
		{
			buildingProduct = buildingProductIerator.next();
			Random random = new Random();
			int totalSkill = avatar.getWorkingSkills()
					.get(buildingProduct.getRequiredSkill()).getLvl();
			for (Avatar currentMaster : avatar.getMasters())
			{
				totalSkill = totalSkill
						+ currentMaster.getWorkingSkills()
								.get(buildingProduct.getRequiredSkill())
								.getLvl();
				currentMaster
						.getWorkingSkills()
						.get(buildingProduct.getRequiredSkill())
						.setExp(currentMaster.getWorkingSkills()
								.get(buildingProduct.getRequiredSkill())
								.getExp()
								+ (currentMaster
										.getWorkingSkills()
										.get(buildingProduct.getRequiredSkill())
										.isSpecialization() ? 4 : 2));
				SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
						currentMaster.getAvatarId(),
						currentMaster.getWorkingSkills().get(
								buildingProduct.getRequiredSkill()));
				currentMaster.setExp(currentMaster.getExp() + 1);
				AvatarFactory.getInstance().updateExpOfAvatarWithId(
						currentMaster.getAvatarId(), currentMaster.getExp());
			}
			int gatheredResources = random.nextInt(totalSkill + 2);
			building.getInventory()
					.getResources()
					.put(buildingProduct.getResource(),
							(building.getInventory().getResources()
									.get(buildingProduct.getResource()) != null ? building
									.getInventory().getResources()
									.get(buildingProduct.getResource())
									: 0)
									+ gatheredResources);
			avatar.getWorkingSkills()
					.get(buildingProduct.getRequiredSkill())
					.setExp(avatar.getWorkingSkills()
							.get(buildingProduct.getRequiredSkill()).getExp()
							+ (avatar.getWorkingSkills()
									.get(buildingProduct.getRequiredSkill())
									.isSpecialization() ? 4 : 2));
			SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
					avatar.getAvatarId(),
					avatar.getWorkingSkills().get(
							buildingProduct.getRequiredSkill()));
			avatar.setExp(avatar.getExp() + 1);
			AvatarFactory.getInstance().updateExpOfAvatarWithId(
					avatar.getAvatarId(), avatar.getExp());
			message.append(gatheredResources);
			message.append(" ");
			message.append(buildingProduct.getResource().getResourceForJsp());
			message.append(" ");
			if (buildingProductIerator.hasNext())
			{
				message.append("und ");
			}
		}
		InventoryFactory.getInstance().updateInventory(building.getInventory());
		message.append("für das Gebäude produziert.");
		return message.toString();
	}
	
	public void setProductionFinishTime(final Building building,
			final int productionHoursTotal)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.HOUR, productionHoursTotal);
		if (calendar.get(Calendar.MINUTE) > 0)
		{
			if (calendar.get(Calendar.MINUTE) > 15)
			{
				if (calendar.get(Calendar.MINUTE) > 30)
				{
					if (calendar.get(Calendar.MINUTE) > 45)
					{
						calendar.add(Calendar.HOUR, 1);
						calendar.set(Calendar.MINUTE, 0);
					}
					else
					{
						calendar.set(Calendar.MINUTE, 45);
					}
				}
				else
				{
					calendar.set(Calendar.MINUTE, 30);
				}
			}
			else
			{
				calendar.set(Calendar.MINUTE, 15);
			}
		}
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		building.setFinishingProduction(calendar.getTimeInMillis());
	}
	
	public void updateBuildingLastHalfDayReport(final int buildingId,
			final String lastHalfDayReport)
	{
		BuildingDataaccess.getInstance().updateBuildingLastHalfDayReport(
				buildingId, lastHalfDayReport);
	}
	
	public void updateBuildingLastHourReport(final int buildingId,
			final String lastHourReport)
	{
		BuildingDataaccess.getInstance().updateBuildingLastHourReport(
				buildingId, lastHourReport);
	}
	
	public void updateBuildingName(final int buildingId, final String name)
	{
		BuildingDataaccess.getInstance().updateBuildingName(buildingId, name);
	}
	
	public void updateBuildingsProductionAttributes(final Building building)
	{
		BuildingDataaccess.getInstance().updateBuildingsProductionAttributes(
				building);
	}
	
	private void setBuildingObjects(final Building building)
	{
		building.setOwnerAvatar(AvatarFactory.getInstance().getAvatarById(
				building.getOwnerAvatarId()));
		building.setBuildingUpgrades(BuildingUpgradeFactory.getInstance()
				.getBuildingUpgradesByBuildingId(building.getBuildingId()));
		building.setMaxStorage(building.getBuildingUpgrades().getStorage() * 500);
		building.setInventory(InventoryFactory.getInstance().getInventorWithId(
				building.getInventoryId()));
		if (building.getBuildingMarketId() != 0)
		{
			building.setBuildingMarket(MarketFactory.getInstance()
					.getMarketWithId(building.getBuildingMarketId()));
		}
		for (int currentAmount : building.getInventory().getResources()
				.values())
		{
			building.setCurrentStorage(building.getCurrentStorage()
					+ currentAmount);
		}
		building.setBuildingType(getBuildingTypeById(building
				.getBuildingTypeId()));
	}
	
	private void setBuildingTypeObjects(final BuildingType buildingType)
	{
		BuildingDataaccess buildingDataaccess = BuildingDataaccess
				.getInstance();
		buildingType.setCosts(buildingDataaccess
				.getBuildingCostsByBuildingTypeIdAsHashmap(buildingType
						.getBuildingCostsId()));
		buildingType.setBuildingProducts(buildingDataaccess
				.getBuildingProductsByBuildingTypeIdAsList(buildingType
						.getBuildingTypeId()));
	}
	
}

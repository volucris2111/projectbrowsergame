
package com.browsergame.project.location.factory;

import java.util.LinkedList;
import java.util.Random;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.location.dataaccess.LocationDataaccess;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.datatransfer.LocationType;
import com.browsergame.project.location.datatransfer.LocationUpgrades;
import com.browsergame.project.market.factory.MarketFactory;
import com.browsergame.project.struggling.factory.StrugglingFactory;

public class LocationFactory
{
	private static LocationFactory INSTANCE = new LocationFactory();
	
	public static LocationFactory getInstance()
	{
		return INSTANCE;
	}
	
	private LocationFactory()
	{
		
	}
	
	public int createLocationAndReturnId(final Location location)
	{
		return LocationDataaccess.getInstance().createLocationAndReturnId(
				location);
	}
	
	public void doHalfDayTick(final Location location)
	{
		StringBuilder halfDayReport = new StringBuilder();
		int consumptionFood = (int) (location.getPopulation() * location
				.getBasicGoods().get(Resource.FOOD));
		int consumptionClothes = (int) (location.getPopulation() * location
				.getLuxuryGoods().get(Resource.CLOTHES));
		
		if (location.getInventory().getResources().get(Resource.FOOD) != null
				&& location.getInventory().getResources().get(Resource.FOOD) > consumptionFood)
		{
			location.getInventory()
					.getResources()
					.put(Resource.FOOD,
							location.getInventory().getResources()
									.get(Resource.FOOD)
									- consumptionFood);
			if (location.getInventory().getResources().get(Resource.CLOTHES) != null
					&& location.getInventory().getResources()
							.get(Resource.CLOTHES) > consumptionClothes)
			{
				location.getInventory()
						.getResources()
						.put(Resource.CLOTHES,
								location.getInventory().getResources()
										.get(Resource.CLOTHES)
										- consumptionClothes);
				Random randomPopulationChange = new Random();
				int populationChange = randomPopulationChange
						.nextInt((int) (location.getPopulation() * 0.05) + 1) + 1;
				int consumptionTools = (int) (populationChange * location
						.getGrowingGoods().get(Resource.TOOL));
				int consumptionFurniture = (int) (populationChange * location
						.getGrowingGoods().get(Resource.FURNITURE));
				int consumptionStone = (int) (populationChange * location
						.getGrowingGoods().get(Resource.STONE));
				int consumptionWood = (int) (populationChange * location
						.getGrowingGoods().get(Resource.WOOD));
				int maxPopulationIncrease = populationChange;
				if (location.getLocationUpgrades().getHousingLvl() * 250 < location
						.getPopulation() + maxPopulationIncrease)
				{
					if (location.getLocationUpgrades().getHousingLvl() * 250
							- location.getPopulation() > 0)
					{
						maxPopulationIncrease = location.getLocationUpgrades()
								.getHousingLvl()
								* 250
								- location.getPopulation();
					}
					halfDayReport
							.append("Keine Bauplätze für neuen Unterkünfte!<br/>");
				}
				if (maxPopulationIncrease > 0)
				{
					if (location.getInventory().getResources()
							.get(Resource.STONE) != null)
					{
						if (consumptionStone > location.getInventory()
								.getResources().get(Resource.STONE))
						{
							if (maxPopulationIncrease > location.getInventory()
									.getResources().get(Resource.STONE)
									/ location.getGrowingGoods().get(
											Resource.STONE))
							{
								maxPopulationIncrease = (int) (location
										.getInventory().getResources()
										.get(Resource.STONE) / location
										.getGrowingGoods().get(Resource.STONE));
							}
							halfDayReport
									.append("Nicht genug Steine für alle neuen Unterkünfte!<br/>");
						}
					}
					else
					{
						halfDayReport
								.append("Nicht genug Steine für alle neuen Unterkünfte!<br/>");
					}
					if (location.getInventory().getResources()
							.get(Resource.TOOL) != null)
					{
						if (consumptionTools > location.getInventory()
								.getResources().get(Resource.TOOL))
						{
							if (maxPopulationIncrease > location.getInventory()
									.getResources().get(Resource.TOOL)
									/ location.getGrowingGoods().get(
											Resource.TOOL))
							{
								maxPopulationIncrease = (int) (location
										.getInventory().getResources()
										.get(Resource.TOOL) / location
										.getGrowingGoods().get(Resource.TOOL));
							}
							halfDayReport
									.append("Nicht genug Werkzeug für alle neuen Unterkünfte!<br/>");
						}
					}
					else
					{
						halfDayReport
								.append("Nicht genug Werkzeug für alle neuen Unterkünfte!<br/>");
					}
					if (location.getInventory().getResources()
							.get(Resource.WOOD) != null)
					{
						if (consumptionWood > location.getInventory()
								.getResources().get(Resource.WOOD))
						{
							if (maxPopulationIncrease > location.getInventory()
									.getResources().get(Resource.WOOD)
									/ location.getGrowingGoods().get(
											Resource.WOOD))
							{
								maxPopulationIncrease = (int) (location
										.getInventory().getResources()
										.get(Resource.WOOD) / location
										.getGrowingGoods().get(Resource.WOOD));
							}
							halfDayReport
									.append("Nicht genug Holz für alle neuen Unterkünfte!<br/>");
						}
					}
					else
					{
						halfDayReport
								.append("Nicht genug Holz für alle neuen Unterkünfte!<br/>");
					}
					if (location.getInventory().getResources()
							.get(Resource.FURNITURE) != null)
					{
						if (consumptionFurniture > location.getInventory()
								.getResources().get(Resource.FURNITURE))
						{
							if (maxPopulationIncrease > location.getInventory()
									.getResources().get(Resource.FURNITURE)
									/ location.getGrowingGoods().get(
											Resource.FURNITURE))
							{
								maxPopulationIncrease = (int) (location
										.getInventory().getResources()
										.get(Resource.FURNITURE) / location
										.getGrowingGoods().get(
												Resource.FURNITURE));
							}
							halfDayReport
									.append("Nicht genug Möbel für alle neuen Unterkünfte!<br/>");
						}
					}
					else
					{
						halfDayReport
								.append("Nicht genug Möbel für alle neuen Unterkünfte!<br/>");
					}
					if (maxPopulationIncrease > 0)
					{
						consumptionTools = (int) (maxPopulationIncrease * location
								.getGrowingGoods().get(Resource.TOOL));
						consumptionFurniture = (int) (maxPopulationIncrease * location
								.getGrowingGoods().get(Resource.FURNITURE));
						consumptionStone = (int) (maxPopulationIncrease * location
								.getGrowingGoods().get(Resource.STONE));
						consumptionWood = (int) (maxPopulationIncrease * location
								.getGrowingGoods().get(Resource.WOOD));
						location.getInventory()
								.getResources()
								.put(Resource.TOOL,
										location.getInventory().getResources()
												.get(Resource.TOOL)
												- consumptionTools);
						location.getInventory()
								.getResources()
								.put(Resource.FURNITURE,
										location.getInventory().getResources()
												.get(Resource.FURNITURE)
												- consumptionFurniture);
						location.getInventory()
								.getResources()
								.put(Resource.STONE,
										location.getInventory().getResources()
												.get(Resource.STONE)
												- consumptionStone);
						location.getInventory()
								.getResources()
								.put(Resource.WOOD,
										location.getInventory().getResources()
												.get(Resource.WOOD)
												- consumptionWood);
						halfDayReport.append("Es haben sich "
								+ maxPopulationIncrease
								+ " Einwohner niedergelassen <br/>");
						location.setPopulation(location.getPopulation()
								+ maxPopulationIncrease);
					}
				}
			}
			else
			{
				location.getInventory().getResources().put(Resource.CLOTHES, 0);
				halfDayReport
						.append("Nicht genug Kleidung! Daher keine neuen Einwohner!<br/>");
			}
		}
		else
		{
			if (location.getPopulation() > 0)
			{
				Random randomPopulationChange = new Random();
				int decreaseFactor = 0;
				if ((int) (location.getPopulation() * 0.15) <= 0)
				{
					decreaseFactor = 1;
				}
				else
				{
					decreaseFactor = (int) (location.getPopulation() * 0.15);
				}
				int populationChange = randomPopulationChange
						.nextInt(decreaseFactor) + 5;
				if (populationChange > location.getPopulation())
				{
					populationChange = location.getPopulation();
				}
				location.setPopulation(location.getPopulation()
						- populationChange);
				halfDayReport
						.append("Das Volk hungert! Es haben "
								+ populationChange
								+ " Einwohner den Ort verlassen oder sind am Hunger gestorben!<br/>");
				location.getInventory().getResources().put(Resource.FOOD, 0);
			}
			
		}
		location.setLastHalfDayReport(halfDayReport.toString());
		location.getInventory().setCoins(
				location.getInventory().getCoins() + location.getPopulation()
						* 100);
		InventoryFactory.getInstance().updateInventory(location.getInventory());
		updatePopulationOfLocationWithId(location.getLocationId(),
				location.getPopulation());
		updateLocationLastHalfDayReport(location.getLocationId(),
				halfDayReport.toString());
	}
	
	public void doLocationTick(final Location location)
	{
		StringBuilder report = new StringBuilder();
		report.append(doButcherProduction(location));
		report.append(doBakerProduction(location));
		report.append(doSmithProduction(location));
		report.append(doCarpenterProduction(location));
		report.append(doTailorProduction(location));
		if (report.length() <= 0)
		{
			report.append("Alle Gebäude produzieren!");
		}
		location.setLastHourReport(report.toString());
		InventoryFactory.getInstance().updateInventory(location.getInventory());
		updateLocationLastHourReport(location.getLocationId(),
				location.getLastHourReport());
	}
	
	public void fillObjectsOfLocation(final Location location)
	{
		location.setOwnerAvatar(AvatarFactory.getInstance().getAvatarById(
				location.getOwnerAvatarId()));
		location.setInventory(InventoryFactory.getInstance().getInventorWithId(
				location.getInventoryId()));
		location.setLocationType(LocationFactory.getInstance()
				.getLocationTypeById(location.getLocationTypeId()));
		if (location.getStrugglingId() != 0)
		{
			location.setStruggling(StrugglingFactory.getInstance()
					.getStrugglingById(location.getStrugglingId()));
		}
		for (int currentAmount : location.getInventory().getResources()
				.values())
		{
			location.setCurrentStorage(location.getCurrentStorage()
					+ currentAmount);
		}
		location.setLocationMarket(MarketFactory.getInstance().getMarketWithId(
				location.getLocationMarketId()));
		location.setLocationFreeMarket(MarketFactory.getInstance()
				.getMarketWithId(location.getLocationFreeMarketId()));
		if (location.getStrugglingId() != 0)
		{
			location.setStruggling(StrugglingFactory.getInstance()
					.getStrugglingById(location.getStrugglingId()));
		}
	}
	
	public LinkedList<Integer> getAllLocationIds()
	{
		return LocationDataaccess.getInstance().getAllLocationIds();
	}
	
	public LinkedList<Location> getAllLocations()
	{
		LinkedList<Location> allLocations = LocationDataaccess.getInstance()
				.getAllLocations();
		for (Location currentLocation : allLocations)
		{
			fillObjectsOfLocation(currentLocation);
		}
		return allLocations;
	}
	
	public Location getLocationById(final int locationId)
	{
		Location location = LocationDataaccess.getInstance().getLocationById(
				locationId);
		fillObjectsOfLocation(location);
		return location;
	}
	
	public Location getLocationByPosition(final int positionX,
			final int positionY)
	{
		Location location = LocationDataaccess.getInstance()
				.getLocationByPosition(positionX, positionY);
		fillObjectsOfLocation(location);
		return location;
	}
	
	public LocationType getLocationTypeById(final int locationTypeId)
	{
		return LocationDataaccess.getInstance().getLocationTypeById(
				locationTypeId);
	}
	
	public void updateLocationLastHalfDayReport(final int locationId,
			final String lastHalfDayReport)
	{
		LocationDataaccess.getInstance().updateLocationLastHalfDayReport(
				locationId, lastHalfDayReport);
	}
	
	public void updateLocationLastHourReport(final int locationId,
			final String lastHourReport)
	{
		LocationDataaccess.getInstance().updateLocationLastHourReport(
				locationId, lastHourReport);
	}
	
	public void updateLocationName(final int locationId, final String name)
	{
		LocationDataaccess.getInstance().updateLocationName(locationId, name);
	}
	
	public void updateLocationOwner(final int locationId,
			final int ownerAvatarId)
	{
		LocationDataaccess.getInstance().updateLocationOwner(locationId,
				ownerAvatarId);
	}
	
	public void updateLocationPopulation(final int locationId,
			final int population)
	{
		LocationDataaccess.getInstance().updateLocationPopulation(locationId,
				population);
	}
	
	public void updateLocationStruggling(final int locationId,
			final int strugglingId)
	{
		LocationDataaccess.getInstance().updateLocationStruggling(locationId,
				strugglingId);
	}
	
	public void updateLocationUpgrades(final LocationUpgrades locationUpgrades)
	{
		LocationDataaccess.getInstance().updateLocationUpgrades(
				locationUpgrades);
	}
	
	public void updatePopulationOfLocationWithId(final int buildingId,
			final int population)
	{
		LocationDataaccess.getInstance().updatePopulationOfLocationWithId(
				buildingId, population);
	}
	
	private String doBakerProduction(final Location location)
	{
		int amountOfGrain = location.getInventory().getResources()
				.get(Resource.GRAIN) != null ? location.getInventory()
				.getResources().get(Resource.GRAIN) : 0;
		int amountOfFood = location.getInventory().getResources()
				.get(Resource.FOOD) != null ? location.getInventory()
				.getResources().get(Resource.FOOD) : 0;
		
		int enoughResourcenForBakerLvl = amountOfGrain / 2;
		String message = "";
		
		if (enoughResourcenForBakerLvl != 0)
		{
			if (enoughResourcenForBakerLvl >= location.getLocationUpgrades()
					.getBakerLvl())
			{
				enoughResourcenForBakerLvl = location.getLocationUpgrades()
						.getBakerLvl();
			}
			else
			{
				message = "Bäcker: Nicht alle Bäcker konnten produzieren, da nicht genug Getreide vorhanden war!<br/>";
			}
			location.getInventory()
					.getResources()
					.put(Resource.FOOD,
							amountOfFood + enoughResourcenForBakerLvl * 2);
			location.getInventory()
					.getResources()
					.put(Resource.GRAIN,
							amountOfGrain - enoughResourcenForBakerLvl * 2);
		}
		else
		{
			message = "Bäcker: Die Bäcker konnten nicht produzieren, da kein Getreide vorhanden war!<br/>";
		}
		return message;
	}
	
	private String doButcherProduction(final Location location)
	{
		int amountOfMeat = location.getInventory().getResources()
				.get(Resource.MEAT) != null ? location.getInventory()
				.getResources().get(Resource.MEAT) : 0;
		int amountOfFood = location.getInventory().getResources()
				.get(Resource.FOOD) != null ? location.getInventory()
				.getResources().get(Resource.FOOD) : 0;
		int enoughResourcenForButcherLvl = amountOfMeat / 2;
		String message = "";
		
		if (enoughResourcenForButcherLvl != 0)
		{
			if (enoughResourcenForButcherLvl >= location.getLocationUpgrades()
					.getButcherLvl())
			{
				enoughResourcenForButcherLvl = location.getLocationUpgrades()
						.getButcherLvl();
			}
			else
			{
				message = "Metzger: Nicht alle Metzger konnten produzieren, da nicht genug Fleisch vorhanden war!<br/>";
			}
			location.getInventory()
					.getResources()
					.put(Resource.FOOD,
							amountOfFood + enoughResourcenForButcherLvl * 2);
			location.getInventory()
					.getResources()
					.put(Resource.MEAT,
							amountOfMeat - enoughResourcenForButcherLvl * 2);
		}
		else
		{
			message = "Metzger: Die Metzger konnten nicht produzieren, da kein Fleisch vorhanden war!<br/>";
		}
		return message;
	}
	
	private String doCarpenterProduction(final Location location)
	{
		int amountOfWood = location.getInventory().getResources()
				.get(Resource.WOOD) != null ? location.getInventory()
				.getResources().get(Resource.WOOD) : 0;
		int amountOfFurniture = location.getInventory().getResources()
				.get(Resource.FURNITURE) != null ? location.getInventory()
				.getResources().get(Resource.FURNITURE) : 0;
		int enoughResourcenForCarpenterLvl = amountOfWood / 2;
		String message = "";
		
		if (enoughResourcenForCarpenterLvl != 0)
		{
			if (enoughResourcenForCarpenterLvl >= location
					.getLocationUpgrades().getCarpenterLvl())
			{
				enoughResourcenForCarpenterLvl = location.getLocationUpgrades()
						.getCarpenterLvl();
			}
			else
			{
				message = "Schreiner: Nicht alle Schreiner konnten produzieren, da nicht genug Holz vorhanden war!<br/>";
			}
			location.getInventory()
					.getResources()
					.put(Resource.FURNITURE,
							amountOfFurniture + enoughResourcenForCarpenterLvl
									* 2);
			location.getInventory()
					.getResources()
					.put(Resource.WOOD,
							amountOfWood - enoughResourcenForCarpenterLvl * 2);
		}
		else
		{
			message = "Schreiner: Die Schreiner konnten nicht produzieren, da kein Holz vorhanden war!<br/>";
		}
		return message;
	}
	
	private String doSmithProduction(final Location location)
	{
		int amountOfIron = location.getInventory().getResources()
				.get(Resource.IRON) != null ? location.getInventory()
				.getResources().get(Resource.IRON) : 0;
		int amountOfWood = location.getInventory().getResources()
				.get(Resource.WOOD) != null ? location.getInventory()
				.getResources().get(Resource.WOOD) : 0;
		int amountOfTool = location.getInventory().getResources()
				.get(Resource.TOOL) != null ? location.getInventory()
				.getResources().get(Resource.TOOL) : 0;
		int enoughResourcenForSmithLvl = amountOfIron < amountOfWood ? amountOfIron
				: amountOfWood;
		
		String message = "";
		
		if (enoughResourcenForSmithLvl != 0)
		{
			if (enoughResourcenForSmithLvl >= location.getLocationUpgrades()
					.getSmithLvl())
			{
				enoughResourcenForSmithLvl = location.getLocationUpgrades()
						.getSmithLvl();
			}
			else
			{
				message = "Schmieden: Nicht alle Schmieden konnten produzieren, da nicht genug Eisen oder Holz vorhanden war!<br/>";
			}
			location.getInventory()
					.getResources()
					.put(Resource.TOOL,
							enoughResourcenForSmithLvl * 2 + amountOfTool);
			location.getInventory()
					.getResources()
					.put(Resource.IRON,
							amountOfIron - enoughResourcenForSmithLvl);
			location.getInventory()
					.getResources()
					.put(Resource.WOOD,
							amountOfWood - enoughResourcenForSmithLvl);
		}
		else
		{
			message = "Schmieden: Die Schmieden konnten nicht produzieren, da kein Eisen oder Holz vorhanden war!<br/>";
		}
		return message;
	}
	
	private String doTailorProduction(final Location location)
	{
		int amountOfWool = location.getInventory().getResources()
				.get(Resource.WOOL) != null ? location.getInventory()
				.getResources().get(Resource.WOOL) : 0;
		int amountOfClothes = location.getInventory().getResources()
				.get(Resource.CLOTHES) != null ? location.getInventory()
				.getResources().get(Resource.CLOTHES) : 0;
		int enoughResourcenForTailorLvl = amountOfWool / 2;
		String message = "";
		if (enoughResourcenForTailorLvl != 0)
		{
			if (enoughResourcenForTailorLvl >= location.getLocationUpgrades()
					.getTailorLvl())
			{
				enoughResourcenForTailorLvl = location.getLocationUpgrades()
						.getTailorLvl();
			}
			else
			{
				message = "Schneider: Nicht alle Schneider konnten produzieren, da nicht genug Wolle vorhanden war!<br/>";
			}
			location.getInventory()
					.getResources()
					.put(Resource.CLOTHES,
							amountOfClothes + enoughResourcenForTailorLvl * 2);
			location.getInventory()
					.getResources()
					.put(Resource.WOOL,
							amountOfWool - enoughResourcenForTailorLvl * 2);
		}
		else
		{
			message = "Schneider: Die Schneider konnten nicht produzieren, da keine Wolle vorhanden war!<br/>";
		}
		return message;
	}
}

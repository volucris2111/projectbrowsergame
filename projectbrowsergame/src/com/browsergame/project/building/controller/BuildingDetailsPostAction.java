
package com.browsergame.project.building.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.building.factory.BuildingFactory;
import com.browsergame.project.buildingupgrade.factory.BuildingUpgradeFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.market.constants.MarketComeFromConstants;

public class BuildingDetailsPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward forward = mapping.findForward("success");
		
		if (request.getParameter("transfer") != null
				|| request.getParameter("transfer" + ".x") != null)
		{
			Inventory buildingInventory = adventureForm.getCurrentField()
					.getBuilding().getInventory();
			Inventory avatarInventory = adventureForm.getUserLoggedInHero()
					.getInventory();
			for (Resource currentResource : adventureForm.getAllResources())
			{
				if (request.getParameter("transferToAvatar"
						+ currentResource.name()) != null
						&& !request.getParameter(
								"transferToAvatar" + currentResource.name())
								.equals(0)
						&& !request.getParameter(
								"transferToAvatar" + currentResource.name())
								.isEmpty())
				{
					int transferValue = Integer.parseInt(request
							.getParameter("transferToAvatar"
									+ currentResource.name()));
					buildingInventory
							.getResources()
							.put(currentResource,
									(buildingInventory.getResources().get(
											currentResource) != null ? buildingInventory
											.getResources()
											.get(currentResource) : 0)
											- transferValue);
					avatarInventory
							.getResources()
							.put(currentResource,
									(avatarInventory.getResources().get(
											currentResource) != null ? avatarInventory
											.getResources()
											.get(currentResource) : 0)
											+ transferValue);
				}
				if (request.getParameter("transferToBuilding"
						+ currentResource.name()) != null
						&& !request.getParameter(
								"transferToBuilding" + currentResource.name())
								.equals(0)
						&& !request.getParameter(
								"transferToBuilding" + currentResource.name())
								.isEmpty())
				{
					int transferValue = Integer.parseInt(request
							.getParameter("transferToBuilding"
									+ currentResource.name()));
					buildingInventory
							.getResources()
							.put(currentResource,
									(buildingInventory.getResources().get(
											currentResource) != null ? buildingInventory
											.getResources()
											.get(currentResource) : 0)
											+ transferValue);
					avatarInventory
							.getResources()
							.put(currentResource,
									(avatarInventory.getResources().get(
											currentResource) != null ? avatarInventory
											.getResources()
											.get(currentResource) : 0)
											- transferValue);
				}
			}
			int transferCoins = 0;
			if (request.getParameter("transferToBuildingCoins") != null)
			{
				transferCoins = transferCoins
						+ Integer.parseInt(request
								.getParameter("transferToBuildingCoins"));
			}
			if (request.getParameter("transferToAvatarCoins") != null)
			{
				transferCoins = transferCoins
						- Integer.parseInt(request
								.getParameter("transferToAvatarCoins"));
			}
			buildingInventory.setCoins(buildingInventory.getCoins()
					+ transferCoins);
			avatarInventory.setCoins(avatarInventory.getCoins() + transferCoins
					* -1);
			InventoryFactory.getInstance().updateInventory(avatarInventory);
			InventoryFactory.getInstance().updateInventory(buildingInventory);
		}
		else if (request.getParameter("buildingMarket") != null
				|| request.getParameter("buildingMarket" + ".x") != null)
		{
			adventureForm
					.setComeFromPage(MarketComeFromConstants.BUILDINGDETAILS);
			forward = mapping.findForward("buildingMarket");
		}
		else if (request.getParameter("back") != null
				|| request.getParameter("back" + ".x") != null)
		{
			forward = mapping.findForward("back");
		}
		else if (request.getParameter("rename") != null
				|| request.getParameter("rename" + ".x") != null)
		{
			BuildingFactory.getInstance().updateBuildingName(
					adventureForm.getCurrentField().getBuilding()
							.getBuildingId(),
					adventureForm.getCurrentField().getBuilding().getName());
		}
		else if (request.getParameter("upgradeWorker") != null
				|| request.getParameter("upgradeWorker" + ".x") != null)
		{
			StringBuilder message = new StringBuilder();
			boolean enoughResources = BuildingUpgradeFactory
					.getInstance()
					.checkResourcesForBuildingUpgradeAndRemoveCostFromInventory(
							adventureForm.getCurrentField().getBuilding()
									.getInventory(),
							message,
							"worker",
							adventureForm.getCurrentField().getBuilding()
									.getBuildingUpgrades().getWorker());
			if (enoughResources)
			{
				adventureForm
						.getCurrentField()
						.getBuilding()
						.getBuildingUpgrades()
						.setWorker(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades().getWorker() + 1);
				InventoryFactory.getInstance().updateInventory(
						adventureForm.getCurrentField().getBuilding()
								.getInventory());
				BuildingUpgradeFactory.getInstance()
						.insertOrUpdateBuildingUpgrades(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades());
			}
			adventureForm.setBuildingUpgradeMessage(message.toString());
			adventureForm.setComeFromPost(true);
			
		}
		else if (request.getParameter("upgradeStorage") != null
				|| request.getParameter("upgradeStorage" + ".x") != null)
		{
			StringBuilder message = new StringBuilder();
			boolean enoughResources = BuildingUpgradeFactory
					.getInstance()
					.checkResourcesForBuildingUpgradeAndRemoveCostFromInventory(
							adventureForm.getCurrentField().getBuilding()
									.getInventory(),
							message,
							"storage",
							adventureForm.getCurrentField().getBuilding()
									.getBuildingUpgrades().getStorage());
			if (enoughResources)
			{
				adventureForm
						.getCurrentField()
						.getBuilding()
						.getBuildingUpgrades()
						.setStorage(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades().getStorage() + 1);
				InventoryFactory.getInstance().updateInventory(
						adventureForm.getCurrentField().getBuilding()
								.getInventory());
				BuildingUpgradeFactory.getInstance()
						.insertOrUpdateBuildingUpgrades(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades());
			}
			adventureForm.setBuildingUpgradeMessage(message.toString());
			adventureForm.setComeFromPost(true);
		}
		else if (request.getParameter("upgradeTools") != null
				|| request.getParameter("upgradeTools" + ".x") != null)
		{
			StringBuilder message = new StringBuilder();
			boolean enoughResources = BuildingUpgradeFactory
					.getInstance()
					.checkResourcesForBuildingUpgradeAndRemoveCostFromInventory(
							adventureForm.getCurrentField().getBuilding()
									.getInventory(),
							message,
							"tools",
							adventureForm.getCurrentField().getBuilding()
									.getBuildingUpgrades().getTools());
			if (enoughResources)
			{
				adventureForm
						.getCurrentField()
						.getBuilding()
						.getBuildingUpgrades()
						.setTools(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades().getTools() + 1);
				InventoryFactory.getInstance().updateInventory(
						adventureForm.getCurrentField().getBuilding()
								.getInventory());
				BuildingUpgradeFactory.getInstance()
						.insertOrUpdateBuildingUpgrades(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades());
			}
			adventureForm.setBuildingUpgradeMessage(message.toString());
			adventureForm.setComeFromPost(true);
		}
		else if (request.getParameter("upgradeGuards") != null
				|| request.getParameter("upgradeGuards" + ".x") != null)
		{
			StringBuilder message = new StringBuilder();
			boolean enoughResources = BuildingUpgradeFactory
					.getInstance()
					.checkResourcesForBuildingUpgradeAndRemoveCostFromInventory(
							adventureForm.getCurrentField().getBuilding()
									.getInventory(),
							message,
							"guards",
							adventureForm.getCurrentField().getBuilding()
									.getBuildingUpgrades().getGuards());
			if (enoughResources)
			{
				adventureForm
						.getCurrentField()
						.getBuilding()
						.getBuildingUpgrades()
						.setGuards(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades().getGuards() + 1);
				InventoryFactory.getInstance().updateInventory(
						adventureForm.getCurrentField().getBuilding()
								.getInventory());
				BuildingUpgradeFactory.getInstance()
						.insertOrUpdateBuildingUpgrades(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades());
			}
			adventureForm.setBuildingUpgradeMessage(message.toString());
			adventureForm.setComeFromPost(true);
		}
		else if (request.getParameter("upgradeHideout") != null
				|| request.getParameter("rename" + ".x") != null)
		{
			StringBuilder message = new StringBuilder();
			boolean enoughResources = BuildingUpgradeFactory
					.getInstance()
					.checkResourcesForBuildingUpgradeAndRemoveCostFromInventory(
							adventureForm.getCurrentField().getBuilding()
									.getInventory(),
							message,
							"hideout",
							adventureForm.getCurrentField().getBuilding()
									.getBuildingUpgrades().getHideout());
			if (enoughResources)
			{
				adventureForm
						.getCurrentField()
						.getBuilding()
						.getBuildingUpgrades()
						.setHideout(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades().getHideout() + 1);
				InventoryFactory.getInstance().updateInventory(
						adventureForm.getCurrentField().getBuilding()
								.getInventory());
				BuildingUpgradeFactory.getInstance()
						.insertOrUpdateBuildingUpgrades(
								adventureForm.getCurrentField().getBuilding()
										.getBuildingUpgrades());
			}
			adventureForm.setBuildingUpgradeMessage(message.toString());
			adventureForm.setComeFromPost(true);
		}
		else if (request.getParameter("leave") != null
				|| request.getParameter("leave" + ".x") != null)
		{
			adventureForm.getUserLoggedInHero().setInside(false);
			AvatarFactory.getInstance().setAvatarInside(
					adventureForm.getUserLoggedInHero());
			forward = mapping.findForward("leave");
		}
		else if (request.getParameter("map") != null
				|| request.getParameter("map" + ".x") != null)
		{
			forward = mapping.findForward("map");
		}
		else if (request.getParameter("produce") != null
				|| request.getParameter("produce" + ".x") != null)
		{
			adventureForm.setBuildingManualProductionMessage(BuildingFactory
					.getInstance().manualProducing(
							adventureForm.getCurrentField().getBuilding(),
							adventureForm.getUserLoggedInHero()));
			adventureForm.setComeFromPost(true);
		}
		else if (request.getParameter("startProduction") != null
				|| request.getParameter("startProduction" + ".x") != null)
		{
			Building currentBuilding = adventureForm.getCurrentField()
					.getBuilding();
			int productionHoursTotal = adventureForm
					.getSelectedProductionHours()
					+ (adventureForm.getSelectedProductionDays() * 24);
			BuildingFactory buildingFactory = BuildingFactory.getInstance();
			buildingFactory.setProductionFinishTime(adventureForm
					.getCurrentField().getBuilding(), productionHoursTotal);
			buildingFactory.updateBuildingsProductionAttributes(adventureForm
					.getCurrentField().getBuilding());
			buildingFactory.doConsumptionOfProduction(currentBuilding,
					productionHoursTotal);
		}
		else if (request.getParameter("cancelProduction") != null
				|| request.getParameter("cancelProduction" + ".x") != null)
		{
			adventureForm.getCurrentField().getBuilding()
					.setFinishingProduction(0);
			BuildingFactory.getInstance().updateBuildingsProductionAttributes(
					adventureForm.getCurrentField().getBuilding());
		}
		return forward;
	}
}


package com.browsergame.project.building.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.building.factory.BuildingFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.factory.LocationFactory;
import com.browsergame.project.market.constants.MarketTypeConstants;
import com.browsergame.project.market.datatransfer.Market;
import com.browsergame.project.market.factory.MarketFactory;

public class BuildingCreatePostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward forward = mapping.findForward("success");
		if (request.getParameter("build") != null
				|| request.getParameter("build" + ".x") != null)
		{
			InventoryFactory inventoryFactory = InventoryFactory.getInstance();
			Building building = new Building();
			building.setBuildingTypeId(adventureForm
					.getSelectedBuildingTypeId());
			building.setPositionX(adventureForm.getUserLoggedInHero()
					.getPositionX());
			building.setPositionY(adventureForm.getUserLoggedInHero()
					.getPositionY());
			building.setOwnerAvatarId(adventureForm.getUserLoggedInHero()
					.getAvatarId());
			building.setInventoryId(inventoryFactory
					.createNewInventoryAndReturnId());
			Market buildingMarket = new Market();
			buildingMarket.setInventoryId(building.getInventoryId());
			buildingMarket.setMarketType(MarketTypeConstants.BUILDINGMARKET);
			buildingMarket.setOwnerAvatarId(0);
			building.setBuildingMarketId(MarketFactory.getInstance()
					.createMarketAndReturnId(buildingMarket));
			adventureForm.getCurrentField().setBuildingId(
					BuildingFactory.getInstance().createBuilding(building));
			building.setBuildingId(adventureForm.getCurrentField()
					.getBuildingId());
			FieldFactory.getInstance().addBuildingAndLocationIdToField(
					adventureForm.getCurrentField());
			FieldFactory.getInstance().setBackgroundImageUrlOfFieldId(
					BuildingFactory.getInstance()
							.getBuildingTypeById(building.getBuildingTypeId())
							.getImageUrl(),
					adventureForm.getCurrentField().getFieldId());
			if (!adventureForm.getUserLoggedInHero().isFirstBuilding())
			{
				Inventory inventoryOfAvatar = adventureForm
						.getUserLoggedInHero().getInventory();
				for (Entry<Resource, Integer> currentResurceCosts : building
						.getBuildingType().getCosts().entrySet())
				{
					inventoryOfAvatar.getResources().put(
							currentResurceCosts.getKey(),
							inventoryOfAvatar.getResources().get(
									currentResurceCosts.getKey())
									- currentResurceCosts.getValue());
				}
				inventoryFactory.updateInventory(adventureForm
						.getUserLoggedInHero().getInventory());
			}
		}
		else if (request.getParameter("createCamp") != null
				|| request.getParameter("createCamp" + ".x") != null)
		{
			InventoryFactory inventoryFactory = InventoryFactory.getInstance();
			Location location = new Location();
			location.setLocationTypeId(1);
			location.setPositionX(adventureForm.getUserLoggedInHero()
					.getPositionX());
			location.setPositionY(adventureForm.getUserLoggedInHero()
					.getPositionY());
			location.setOwnerAvatarId(adventureForm.getUserLoggedInHero()
					.getAvatarId());
			location.setInventoryId(inventoryFactory
					.createNewInventoryAndReturnId());
			Market locationMarket = new Market();
			locationMarket.setInventoryId(location.getInventoryId());
			locationMarket.setOwnerAvatarId(adventureForm.getUserLoggedInHero()
					.getAvatarId());
			location.setLocationMarketId((MarketFactory.getInstance()
					.createMarketAndReturnId(locationMarket)));
			Market locationFreeMarket = new Market();
			locationFreeMarket.setInventoryId(0);
			locationFreeMarket.setOwnerAvatarId(0);
			location.setLocationFreeMarketId(MarketFactory.getInstance()
					.createMarketAndReturnId(locationFreeMarket));
			adventureForm.getCurrentField().setLocationId(
					LocationFactory.getInstance().createLocationAndReturnId(
							location));
			location.setLocationId(adventureForm.getCurrentField()
					.getLocationId());
			FieldFactory.getInstance().addBuildingAndLocationIdToField(
					adventureForm.getCurrentField());
			FieldFactory.getInstance().setBackgroundImageUrlOfFieldId(
					LocationFactory.getInstance()
							.getLocationTypeById(location.getLocationTypeId())
							.getImageUrl(),
					adventureForm.getCurrentField().getFieldId());
		}
		else if (request.getParameter("cancel") != null
				|| request.getParameter("cancel" + ".x") != null)
		{
			forward = mapping.findForward("back");
		}
		return forward;
	}
}

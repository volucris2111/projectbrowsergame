
package com.browsergame.project.location.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.location.datatransfer.Location;

public class LocationTownHallPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		Location currentLocation = adventureForm.getCurrentField()
				.getLocation();
		adventureForm.getDisplayProduction().clear();
		adventureForm.getDisplayBasicGoodsNeed().clear();
		adventureForm.getDisplayLuxuryGoodsNeed().clear();
		adventureForm.getDisplayProductionConsumption().clear();
		adventureForm
				.getDisplayProduction()
				.put(Resource.FOOD,
						(currentLocation.getLocationUpgrades().getButcherLvl() + currentLocation
								.getLocationUpgrades().getBakerLvl()) * 2);
		adventureForm.getDisplayProduction().put(Resource.CLOTHES,
				currentLocation.getLocationUpgrades().getTailorLvl() * 2);
		adventureForm.getDisplayProduction().put(Resource.FURNITURE,
				currentLocation.getLocationUpgrades().getCarpenterLvl() * 2);
		adventureForm.getDisplayProduction().put(Resource.TOOL,
				currentLocation.getLocationUpgrades().getSmithLvl() * 2);
		adventureForm.getDisplayProductionConsumption().put(Resource.GRAIN,
				currentLocation.getLocationUpgrades().getBakerLvl() * 2);
		adventureForm.getDisplayProductionConsumption().put(Resource.MEAT,
				currentLocation.getLocationUpgrades().getButcherLvl() * 2);
		adventureForm.getDisplayProductionConsumption().put(Resource.WOOL,
				currentLocation.getLocationUpgrades().getTailorLvl() * 2);
		adventureForm.getDisplayProductionConsumption().put(
				Resource.WOOD,
				currentLocation.getLocationUpgrades().getSmithLvl()
						+ currentLocation.getLocationUpgrades()
								.getCarpenterLvl() * 2);
		adventureForm.getDisplayProductionConsumption().put(Resource.IRON,
				currentLocation.getLocationUpgrades().getSmithLvl());
		for (Entry<Resource, Double> currentEntry : currentLocation
				.getBasicGoods().entrySet())
		{
			adventureForm.getDisplayBasicGoodsNeed().put(
					currentEntry.getKey(),
					(int) (currentEntry.getValue() * currentLocation
							.getPopulation()));
		}
		
		for (Entry<Resource, Double> currentEntry : currentLocation
				.getLuxuryGoods().entrySet())
		{
			adventureForm.getDisplayLuxuryGoodsNeed().put(
					currentEntry.getKey(),
					(int) (currentEntry.getValue() * currentLocation
							.getPopulation()));
		}
		return mapping.findForward("success");
	}
}

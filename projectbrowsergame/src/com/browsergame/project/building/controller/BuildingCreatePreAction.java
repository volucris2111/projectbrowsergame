
package com.browsergame.project.building.controller;

import java.util.LinkedList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.building.datatransfer.BuildingType;
import com.browsergame.project.building.factory.BuildingFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingCreatePreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		adventureForm
				.setBuildableBuildings(BuildingFactory.getInstance()
						.getBuildingTypesByTerrain(
								adventureForm.getCurrentField().getTerrain()
										.getType()));
		LinkedList<BuildingType> buildingTypesToRemove = new LinkedList<BuildingType>();
		for (BuildingType currentBuildingType : adventureForm
				.getBuildableBuildings())
		{
			if (!currentBuildingType.getAvatarClassNeededToBuild().equals(
					adventureForm.getUserLoggedInHero().getAvatarClass()
							.getName()))
			{
				buildingTypesToRemove.add(currentBuildingType);
			}
		}
		adventureForm.getBuildableBuildings().removeAll(buildingTypesToRemove);
		if (adventureForm.getCurrentField().getTerrain() != null)
		{
			for (BuildingType buildingType : adventureForm
					.getBuildableBuildings())
			{
				for (Entry<Resource, Integer> currentResurceCosts : buildingType
						.getCosts().entrySet())
				{
					if (adventureForm.getUserLoggedInHero().getInventory()
							.getResources().get(currentResurceCosts.getKey()) != null)
					{
						if (adventureForm.getUserLoggedInHero().getInventory()
								.getResources()
								.get(currentResurceCosts.getKey()) < currentResurceCosts
								.getValue())
						{
							buildingType.setEnoughResources(false);
							break;
						}
						else
						{
							buildingType.setEnoughResources(true);
						}
					}
					else
					{
						buildingType.setEnoughResources(false);
						break;
					}
				}
			}
		}
		adventureForm.getUserLoggedInHero().setFirstBuilding(
				BuildingFactory.getInstance().checkForFirstBuildingOfAvatarId(
						adventureForm.getUserLoggedInHero().getAvatarId()));
		return mapping.findForward("success");
	}
}
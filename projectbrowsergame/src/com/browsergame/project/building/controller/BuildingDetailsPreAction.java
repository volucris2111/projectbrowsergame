
package com.browsergame.project.building.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.building.factory.BuildingFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingDetailsPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		if (adventureForm.getSelctedBuildingId() == 0)
		{
			adventureForm
					.setCurrentField(FieldFactory.getInstance()
							.getFieldOfCoords(
									adventureForm.getUserLoggedInHero()
											.getPositionX(),
									adventureForm.getUserLoggedInHero()
											.getPositionY()));
		}
		else
		{
			Building building = BuildingFactory.getInstance().getBuildingById(
					adventureForm.getSelctedBuildingId());
			adventureForm.setCurrentField(FieldFactory.getInstance()
					.getFieldOfCoords(building.getPositionX(),
							building.getPositionY()));
		}
		for (Avatar currentAvatar : adventureForm.getCurrentField()
				.getAllAvatarsInside())
		{
			if (currentAvatar.getAvatarId() == adventureForm
					.getUserLoggedInHero().getAvatarId())
			{
				adventureForm.getCurrentField().getAllAvatarsInside()
						.remove(currentAvatar);
				break;
			}
		}
		adventureForm.getAllResources().clear();
		for (Entry<Resource, Integer> currentResource : adventureForm
				.getCurrentField().getBuilding().getInventory().getResources()
				.entrySet())
		{
			adventureForm.getAllResources().add(currentResource.getKey());
		}
		for (Entry<Resource, Integer> currentResource : adventureForm
				.getUserLoggedInHero().getInventory().getResources().entrySet())
		{
			adventureForm.getAllResources().add(currentResource.getKey());
		}
		if (!adventureForm.isComeFromPost())
		{
			adventureForm.setBuildingUpgradeMessage("");
			adventureForm.setBuildingManualProductionMessage("");
		}
		adventureForm.setComeFromPost(false);
		adventureForm.setBuildingOwner(false);
		if (adventureForm.getCurrentField().getBuilding().getOwnerAvatar()
				.getAvatarId() == adventureForm.getUserLoggedInHero()
				.getAvatarId())
		{
			adventureForm.setBuildingOwner(true);
		}
		return mapping.findForward("success");
	}
}

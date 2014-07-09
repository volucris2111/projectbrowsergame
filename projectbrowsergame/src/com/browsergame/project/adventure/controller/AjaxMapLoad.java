
package com.browsergame.project.adventure.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.map.factory.AdventureMapFactory;
import com.browsergame.project.terrain.factory.TerrainFactory;

public class AjaxMapLoad extends BasicAction
{
	@SuppressWarnings("unused")
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		int actionPoints = 10;
		AdventureForm adventureForm = (AdventureForm) form;
		if (request.getParameter("selectedPositionX") != null
				&& request.getParameter("selectedPositionY") != null)
		{
			adventureForm.getUserLoggedInHero().getPositionX();
			adventureForm.getUserLoggedInHero().getPositionY();
			if (true
					|| TerrainFactory.getInstance().walkLogics(
							TerrainFactory.getInstance().getTypOfFieldAt(
									request.getParameter("selectedPositionX"),
									request.getParameter("selectedPositionY")),
							actionPoints)
					|| adventureForm.getLoggedInUser().getName()
							.equals("editor")) // 2. parameter werden
												// später die
			// action points
			
			{
				if (adventureForm.getUserLoggedInHero().isInside())
				{
					adventureForm.getUserLoggedInHero().setInside(false);
					AvatarFactory.getInstance().setAvatarInside(
							adventureForm.getUserLoggedInHero());
				}
				adventureForm.getUserLoggedInHero().setPositionX(
						adventureForm.getSelectedPositionX());
				adventureForm.getUserLoggedInHero().setPositionY(
						adventureForm.getSelectedPositionY());
				
				AvatarFactory.getInstance().moveAvatar(
						adventureForm.getUserLoggedInHero());
				if (adventureForm.getCurrentField().getAllAvatarsOutside()
						.isEmpty()
						&& adventureForm.getCurrentField().getFieldId() != 0)
				{
					FieldFactory.getInstance().setFieldObjectImageUrlOfFieldId(
							"/images/worldobjects/worldobjectblank.gif",
							adventureForm.getCurrentField().getFieldId());
				}
				Field fieldToUpdate = FieldFactory.getInstance()
						.getFieldOfCoords(
								Integer.parseInt(request
										.getParameter("selectedPositionX")),
								Integer.parseInt(request
										.getParameter("selectedPositionY")));
				if (fieldToUpdate.getAllAvatarsOutside().size() >= 0
						&& fieldToUpdate.getFieldId() != 0)
				{
					FieldFactory.getInstance().setFieldObjectImageUrlOfFieldId(
							"/images/worldobjects/worldobjecthero.gif",
							fieldToUpdate.getFieldId());
				}
			}
		}
		if (request.getParameter("action") != null)
		{
			adventureForm.setMessage(adventureForm
					.getCurrentField()
					.getTerrain()
					.doAction(request.getParameter("action"),
							adventureForm.getUserLoggedInHero()));
			InventoryFactory.getInstance().updateInventory(
					adventureForm.getUserLoggedInHero().getInventory());
			
			adventureForm.getUserLoggedInHero().setExp(
					adventureForm.getUserLoggedInHero().getExp() + 1);
			AvatarFactory.getInstance().updateExpOfAvatarWithId(
					adventureForm.getUserLoggedInHero().getAvatarId(),
					adventureForm.getUserLoggedInHero().getExp());
			for (Avatar currentMaster : adventureForm.getUserLoggedInHero()
					.getMasters())
			{
				currentMaster.setExp(currentMaster.getExp() + 1);
				AvatarFactory.getInstance().updateExpOfAvatarWithId(
						currentMaster.getAvatarId(), currentMaster.getExp());
			}
			
		}
		adventureForm.setAdventureMap(AdventureMapFactory.getInstance()
				.getPartOfMapAsListWithCoordinatsAsMiddle(
						adventureForm.getUserLoggedInHero().getPositionX(),
						adventureForm.getUserLoggedInHero().getPositionY()));
		adventureForm.setCurrentField(FieldFactory.getInstance()
				.getFieldOfCoords(
						adventureForm.getUserLoggedInHero().getPositionX(),
						adventureForm.getUserLoggedInHero().getPositionY()));
		
		LinkedList<Avatar> avatarListToSort;
		if (adventureForm.getUserLoggedInHero().isInside())
		{
			avatarListToSort = adventureForm.getCurrentField()
					.getAllAvatarsInside();
		}
		else
		{
			avatarListToSort = adventureForm.getCurrentField()
					.getAllAvatarsOutside();
		}
		
		for (Avatar currentAvatar : avatarListToSort)
		{
			if (currentAvatar.getAvatarId() == adventureForm
					.getUserLoggedInHero().getAvatarId())
			{
				avatarListToSort.remove(currentAvatar);
				break;
			}
		}
		adventureForm.getCurrentField()
				.setBuildable(
						adventureForm.getCurrentField().getBuildingId() == 0
								&& adventureForm.getCurrentField()
										.getLocationId() == 0);
		// createJSPAsString(response, adventureForm);
		if (!adventureForm.getCurrentField().getTerrain().getActions()
				.isEmpty()
				|| adventureForm.getCurrentField().isBuildable()
				|| adventureForm.getCurrentField().getBuildingId() != 0
				|| adventureForm.getCurrentField().getLocationId() != 0)
		{
			adventureForm.setActionsAvailable(true);
			// LinkedList<TerrainActionsEnum> actionsOnCurrentField = new
			// LinkedList<TerrainActionsEnum>();
			// for (String currentActionString : adventureForm.getCurrentField()
			// .getTerrain().getActions())
			// {
			// TerrainActionsEnum currentAction = TerrainActionsEnum
			// .valueOf(currentActionString);
			// actionsOnCurrentField.add(currentAction);
			// }
			// adventureForm.setActionsOnCurrentfield(actionsOnCurrentField);
		}
		else
		{
			adventureForm.setActionsAvailable(false);
		}
		return mapping.findForward("success");
	}
	
	private String getButtonWithNewTableRow(final String name,
			final String value)
	{
		StringBuilder button = new StringBuilder();
		button.append("<tr><td><input name=\"");
		button.append(name);
		button.append("\" value=\"");
		button.append(value);
		button.append("\" type=\"submit\"></input></td></tr>");
		return button.toString();
	}
}


package com.browsergame.project.adventure.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.constants.AvatarInsideActionConstant;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.terrain.datatransfer.TerrainEnum;

public class AdventureWorldPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward forward = mapping.findForward("success");
		if (request.getParameter("avatarDetails") != null
				|| request.getParameter("avatarDetails" + ".x") != null)
		{
			request.setAttribute("avatarId",
					adventureForm.getSelectedAvatarId());
			forward = mapping.findForward("avatarDetails");
		}
		else if (request.getParameter("build") != null
				|| request.getParameter("build" + ".x") != null)
		{
			forward = mapping.findForward("buildingCreate");
		}
		else if (request.getParameter("buildingEnter") != null
				|| request.getParameter("buildingEnter" + ".x") != null)
		{
			moveAvatarInside(adventureForm);
			adventureForm.getCurrentField().getAllAvatarsOutside()
					.remove(adventureForm.getUserLoggedInHero());
			if (adventureForm.getCurrentField().getAllAvatarsOutside()
					.isEmpty()
					&& adventureForm.getCurrentField().getFieldId() != 0)
			{
				FieldFactory.getInstance().setFieldObjectImageUrlOfFieldId(
						"/images/worldobjects/worldobjectblank.gif",
						adventureForm.getCurrentField().getFieldId());
			}
			forward = mapping.findForward("buildingDetails");
		}
		else if (request.getParameter("buildingLeave") != null
				|| request.getParameter("buildingLeave" + ".x") != null)
		{
			moveAvatarOutside(adventureForm);
			FieldFactory.getInstance().setFieldObjectImageUrlOfFieldId(
					"/images/worldobjects/worldobjecthero.gif",
					adventureForm.getCurrentField().getFieldId());
		}
		else if (request.getParameter("buildingWatch") != null
				|| request.getParameter("buildingWatch" + ".x") != null)
		{
			forward = mapping.findForward("buildingOverview");
		}
		else if (request.getParameter("buildingDetails") != null
				|| request.getParameter("buildingDetails" + ".x") != null)
		{
			forward = mapping.findForward("buildingDetails");
		}
		
		else if (request.getParameter("locationManage") != null
				|| request.getParameter("locationManage" + ".x") != null)
		{
			forward = mapping.findForward("locationControl");
		}
		else if (request.getParameter("locationEnter") != null
				|| request.getParameter("locationEnter" + ".x") != null)
		{
			moveAvatarInside(adventureForm);
			adventureForm.getCurrentField().getAllAvatarsOutside()
					.remove(adventureForm.getUserLoggedInHero());
			if (adventureForm.getCurrentField().getAllAvatarsOutside()
					.isEmpty()
					&& adventureForm.getCurrentField().getFieldId() != 0)
			{
				FieldFactory.getInstance().setFieldObjectImageUrlOfFieldId(
						"/images/worldobjects/worldobjectblank.gif",
						adventureForm.getCurrentField().getFieldId());
			}
			forward = mapping.findForward("locationDetails");
		}
		else if (request.getParameter("locationLeave") != null
				|| request.getParameter("locationLeave" + ".x") != null)
		{
			moveAvatarOutside(adventureForm);
			FieldFactory.getInstance().setFieldObjectImageUrlOfFieldId(
					"/images/worldobjects/worldobjecthero.gif",
					adventureForm.getCurrentField().getFieldId());
		}
		else if (request.getParameter("locationWatch") != null
				|| request.getParameter("locationWatch" + ".x") != null)
		{
			forward = mapping.findForward("locationOverview");
		}
		else if (request.getParameter("locationDetails") != null
				|| request.getParameter("locationDetails" + ".x") != null)
		{
			forward = mapping.findForward("locationDetails");
		}
		else if (request.getParameter("changeTerrain") != null
				|| request.getParameter("changeTerrain" + ".x") != null)
		{
			if (!adventureForm.getCurrentField().getTerrainString()
					.equals(adventureForm.getSelectedTerrain()))
			{
				if (adventureForm.getCurrentField().getFieldId() == 0)
				{
					FieldFactory
							.getInstance()
							.createNewField(
									adventureForm.getSelectedTerrain(),
									adventureForm.getCurrentField()
											.getPositionX(),
									adventureForm.getCurrentField()
											.getPositionY(),
									InventoryFactory.getInstance()
											.createNewInventoryAndReturnId(),
									TerrainEnum
											.valueOf(
													adventureForm
															.getSelectedTerrain())
											.getTerrain().getImageUrl());
				}
				else
				{
					FieldFactory
							.getInstance()
							.changeTerrainOfFieldId(
									adventureForm.getCurrentField()
											.getFieldId(),
									adventureForm.getSelectedTerrain(),
									TerrainEnum
											.valueOf(
													adventureForm
															.getSelectedTerrain())
											.getTerrain().getImageUrl());
				}
			}
		}
		return forward;
	}
	
	private void moveAvatarInside(final AdventureForm adventureForm)
	{
		adventureForm.getUserLoggedInHero().setInsideAction(
				AvatarInsideActionConstant.VISIT.getAction());
		adventureForm.getUserLoggedInHero().setInside(true);
		AvatarFactory.getInstance().setAvatarInside(
				adventureForm.getUserLoggedInHero());
	}
	
	private void moveAvatarOutside(final AdventureForm adventureForm)
	{
		adventureForm.getUserLoggedInHero().setInside(false);
		adventureForm.getUserLoggedInHero().setInsideAction("");
		AvatarFactory.getInstance().setAvatarInside(
				adventureForm.getUserLoggedInHero());
	}
}

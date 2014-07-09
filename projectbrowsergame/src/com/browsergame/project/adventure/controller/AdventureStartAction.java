
package com.browsergame.project.adventure.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.user.factory.UserFactory;

public class AdventureStartAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward forward = mapping.findForward("success");
		adventureForm.setLoggedInUser(UserFactory.getInstance().getUserById(
				Integer.parseInt((String) request.getAttribute("userId"))));
		adventureForm.setUserLoggedInHero(AvatarFactory.getInstance()
				.getAvatarOfUserId(adventureForm.getLoggedInUser().getUserId(),
						"hero"));
		adventureForm.getUserLoggedInHero().setMasters(
				AvatarFactory.getInstance().getGroupOfAvatarIdAsLinkedList(
						adventureForm.getUserLoggedInHero().getAvatarId()));
		if (adventureForm.getUserLoggedInHero().getAvatarId() == 0)
		{
			forward = mapping.findForward("avatarCreate");
		}
		else if (adventureForm.getUserLoggedInHero().isInside())
		{
			adventureForm
					.setCurrentField(FieldFactory.getInstance()
							.getFieldOfCoords(
									adventureForm.getUserLoggedInHero()
											.getPositionX(),
									adventureForm.getUserLoggedInHero()
											.getPositionY()));
			
			if (adventureForm.getCurrentField().getBuildingId() != 0)
			{
				
				forward = mapping.findForward("buildingDetails");
			}
			else
			{
				forward = mapping.findForward("locationDetails");
			}
		}
		return forward;
	}
}

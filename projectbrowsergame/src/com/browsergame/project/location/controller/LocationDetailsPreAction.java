
package com.browsergame.project.location.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.factory.LocationFactory;

public class LocationDetailsPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		if (adventureForm.getSelctedLocationId() == 0)
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
			Location location = LocationFactory.getInstance().getLocationById(
					adventureForm.getSelctedLocationId());
			adventureForm.setCurrentField(FieldFactory.getInstance()
					.getFieldOfCoords(location.getPositionX(),
							location.getPositionY()));
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
		return mapping.findForward("success");
	}
}


package com.browsergame.project.location.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.location.datatransfer.Location;

public class LocationTavernPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		Location location = adventureForm.getCurrentField().getLocation();
		adventureForm.setLocation(location);
		if (!adventureForm.isComeFromPost())
		{
			location.getTavern().setTravelingMasters(
					AvatarFactory.getInstance().getMastersOfLocationId(
							location.getLocationId()));
			location.getTavern().setShownMaster(null);
			adventureForm.setMessage("");
		}
		adventureForm.setComeFromPost(false);
		return mapping.findForward("success");
	}
}

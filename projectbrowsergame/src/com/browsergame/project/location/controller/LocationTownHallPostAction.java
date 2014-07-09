
package com.browsergame.project.location.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.location.factory.LocationFactory;

public class LocationTownHallPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		if (request.getParameter("produce") != null
				|| request.getParameter("produce" + ".x") != null)
		{
			LocationFactory.getInstance().doLocationTick(
					adventureForm.getCurrentField().getLocation());
		}
		return mapping.findForward("success");
	}
}

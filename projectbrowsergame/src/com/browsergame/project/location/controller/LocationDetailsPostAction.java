
package com.browsergame.project.location.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.core.controller.BasicAction;

public class LocationDetailsPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward forward = mapping.findForward("success");
		if (request.getParameter("leave") != null
				|| request.getParameter("leave" + ".x") != null)
		{
			adventureForm.getUserLoggedInHero().setInside(false);
			AvatarFactory.getInstance().setAvatarInside(
					adventureForm.getUserLoggedInHero());
			forward = mapping.findForward("leave");
		}
		if (request.getParameter("map") != null
				|| request.getParameter("map" + ".x") != null)
		{
			forward = mapping.findForward("map");
		}
		if (request.getParameter("locationFreeMarket") != null
				|| request.getParameter("locationFreeMarket" + ".x") != null)
		{
			forward = mapping.findForward("locationFreeMarket");
		}
		if (request.getParameter("tavern") != null
				|| request.getParameter("tavern" + ".x") != null)
		{
			forward = mapping.findForward("tavern");
		}
		if (request.getParameter("townHall") != null
				|| request.getParameter("townHall" + ".x") != null)
		{
			forward = mapping.findForward("townHall");
		}
		return forward;
	}
}

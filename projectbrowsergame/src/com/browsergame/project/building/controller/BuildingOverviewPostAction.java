
package com.browsergame.project.building.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.core.controller.BasicAction;

public class BuildingOverviewPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		ActionForward forward = mapping.findForward("success");
		if (request.getParameter("back") != null
				|| request.getParameter("back" + ".x") != null)
		{
			forward = mapping.findForward("back");
		}
		return forward;
	}
}


package com.browsergame.project.location.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.core.controller.BasicAction;

public class LocationControlPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		if (request.getParameter("produce") != null
				|| request.getParameter("produce" + ".x") != null)
		{
		}
		return mapping.findForward("success");
	}
}

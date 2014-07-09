
package com.browsergame.project.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.home.view.HomeForm;
import com.browsergame.project.user.factory.UserFactory;

public class HeadPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		HomeForm homeForm = (HomeForm) form;
		homeForm.setCurrentUser(UserFactory.getInstance().getUserById(
				Integer.parseInt(request.getAttribute("userId").toString())));
		return mapping.findForward("success");
	}
}
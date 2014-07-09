
package com.browsergame.project.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.core.controller.BasicAction;

public class LogoutAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		Cookie[] cookies = request.getCookies();
		for (Cookie currentCookie : cookies)
		{
			currentCookie.setValue("");
			currentCookie.setMaxAge(0);
			response.addCookie(currentCookie);
		}
		return mapping.findForward("success");
	}
}

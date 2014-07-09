
package com.browsergame.project.core.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.user.factory.UserFactory;

public class BasicAction extends Action
{
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		return null;
	}
	
	@Override
	public ActionForward execute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		Boolean loggedIn = false;
		if (request.getCookies() != null && request.getCookies().length > 0)
		{
			String passwordCookie = "";
			Cookie[] cookiesFromBrowser = request.getCookies();
			for (Cookie currentCookie : cookiesFromBrowser)
			{
				if (currentCookie.getName().equals("browsergame"))
				{
					passwordCookie = currentCookie.getValue();
				}
			}
			if (passwordCookie != "")
			{
				
				for (Cookie currentCookie : cookiesFromBrowser)
				{
					if (currentCookie.getName().equals("userId"))
					{
						if (currentCookie.getValue() != null
								&& !currentCookie.getValue().isEmpty())
						{
							String password = UserFactory.getInstance()
									.getUserById(currentCookie.getValue())
									.getPassword();
							if (password.equals(passwordCookie))
							{
								loggedIn = true;
								request.setAttribute("userId",
								
								currentCookie.getValue());
								break;
							}
							
						}
					}
				}
			}
		}
		if (loggedIn)
		{
			return childExecute(mapping, form, request, response);
		}
		else
		{
			return mapping.findForward("notLoggedin");
		}
		
	}
}

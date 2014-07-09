package com.browsergame.project.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.login.factory.LoginFactory;
import com.browsergame.project.login.view.LoginForm;
import com.browsergame.project.user.datatransfer.User;
import com.browsergame.project.user.factory.UserFactory;

public class LoginPostAction extends Action
{
	@Override
	public ActionForward execute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		LoginForm loginForm = (LoginForm) form;
		loginForm.setMessage(null);
		ActionForward forward = null;
		LoginFactory loginFactory = LoginFactory.getInstance();
		if (request.getParameter("register") != null
				|| request.getParameter("register" + ".x") != null)
		{
			forward = mapping.findForward("register");
		}
		if (request.getParameter("login") != null
				|| request.getParameter("login" + ".x") != null)
		{
			loginForm.getCurrentUser().setPassword(
					loginFactory.convertPasswordToHash(loginForm
							.getCurrentUser().getPassword()));
			User userLogin = UserFactory.getInstance().checkUserLogin(
					loginForm.getCurrentUser());

			if (userLogin.getName() != null && userLogin.getPassword() != null)
			{
				Cookie cookie = new Cookie("userId", Integer.toString(userLogin
						.getUserId()));
				Cookie cookie2 = new Cookie("browsergame", loginForm
						.getCurrentUser().getPassword());
				cookie.setMaxAge(60 * 60 * 4);
				cookie2.setMaxAge(60 * 60 * 4);
				response.addCookie(cookie);
				response.addCookie(cookie2);
				forward = mapping.findForward("success");
			}
			else
			{
				loginForm.setMessage("Login fehlgeschlagen!");
				forward = mapping.findForward("failure");
			}
		}
		return forward;
	}
}

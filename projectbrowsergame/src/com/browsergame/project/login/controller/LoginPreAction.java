
package com.browsergame.project.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.login.view.LoginForm;
import com.browsergame.project.user.datatransfer.User;

public class LoginPreAction extends Action
{
	@Override
	public ActionForward execute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException
	{
		LoginForm loginForm = (LoginForm) form;
		
		if (loginForm.getMessage() != null)
		{
			loginForm.getCurrentUser().setPassword(null);
		}
		else
		{
			loginForm.setCurrentUser(new User());
		}
		ActionForward forward = mapping.findForward("success");
		return forward;
	}
}

package com.browsergame.project.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.login.view.LoginForm;
import com.browsergame.project.user.datatransfer.User;

public class UserCreateStartAction extends Action
{
	@Override
	public ActionForward execute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException
	{
		LoginForm loginForm = (LoginForm) form;
		loginForm.setCurrentUser(new User());
		return mapping.findForward("success");
	}
	
}
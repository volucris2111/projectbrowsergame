package com.browsergame.project.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.login.factory.LoginFactory;
import com.browsergame.project.login.view.LoginForm;
import com.browsergame.project.user.factory.UserFactory;

public class UserCreatePostAction extends Action
{
	@Override
	public ActionForward execute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception

	{
		LoginFactory loginFactory = LoginFactory.getInstance();
		ActionForward forward = mapping.findForward("back");
		LoginForm loginForm = (LoginForm) form;
		loginForm.setMessage(null);
		if (request.getParameter("register") != null
				|| request.getParameter("register" + ".x") != null)
		{
			if (loginForm.getCurrentUser().getPassword() != null
					&& !loginForm.getCurrentUser().getPassword().isEmpty()
					&& loginForm.getCurrentUser().getPasswordRepeat() != null
					&& !loginForm.getCurrentUser().getPasswordRepeat()
							.isEmpty()
					&& loginForm.getCurrentUser().getName() != null
					&& !loginForm.getCurrentUser().getName().isEmpty()
					&& loginForm.getCurrentUser().getMailaddress() != null
					&& !loginForm.getCurrentUser().getMailaddress().isEmpty())
			{
				if (loginForm.getCurrentUser().getPassword()
						.equals(loginForm.getCurrentUser().getPasswordRepeat()))
				{

					loginForm.getCurrentUser().setPassword(
							loginFactory.convertPasswordToHash(loginForm
									.getCurrentUser().getPassword()));

					String error = UserFactory.getInstance().saveUser(
							loginForm.getCurrentUser());
					if (error.equals("Duplicate entry '"
							+ loginForm.getCurrentUser().getName()
							+ "' for key 'name_UNIQUE'"))
					{
						forward = mapping.findForward("failure");
						loginForm.setMessage("Benutzername bereits vergeben!");
					}
					else
					{
						forward = mapping.findForward("success");
						loginForm
								.setMessage("Registrierung abgeschlossen. Bitte einloggen!");
					}
				}
				else
				{
					loginForm.setMessage("Passwörter stimmen nicht überein!");
					forward = mapping.findForward("failure");
				}
			}
			else
			{
				loginForm.setMessage("Es sind nicht alle Felder gefüllt!");
				forward = mapping.findForward("failure");
			}

		}
		return forward;
	}
}

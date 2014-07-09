
package com.browsergame.project.avatar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.avatar.view.AvatarForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.user.factory.UserFactory;

public class AvatarInventoryPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		
		AvatarForm avatarForm = (AvatarForm) form;
		if (request.getAttribute("avatarId") != null)
		{
			avatarForm.setCurrentAvatar(AvatarFactory.getInstance()
					.getAvatarById((int) request.getAttribute("avatarId")));
		}
		else
		{
			if (avatarForm.getLoggedInUser() != null
					&& avatarForm.getLoggedInUser().getUserId() != 0)
			{
				avatarForm.setLoggedInUser(UserFactory.getInstance()
						.getUserById(
								Integer.parseInt((String) request
										.getAttribute("userId"))));
			}
			avatarForm.setUserLoggedInHero(AvatarFactory.getInstance()
					.getAvatarOfUserId(
							avatarForm.getLoggedInUser().getUserId(), "hero"));
			avatarForm.setCurrentAvatar(avatarForm.getUserLoggedInHero());
		}
		return mapping.findForward("success");
	}
}

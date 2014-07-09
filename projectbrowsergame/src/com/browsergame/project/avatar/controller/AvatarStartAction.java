
package com.browsergame.project.avatar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.avatar.view.AvatarForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.user.factory.UserFactory;

public class AvatarStartAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AvatarForm avatarForm = (AvatarForm) form;
		ActionForward forward = mapping.findForward("success");
		AvatarFactory avatarFactory = AvatarFactory.getInstance();
		avatarForm.setLoggedInUser(UserFactory.getInstance().getUserById(
				Integer.parseInt((String) request.getAttribute("userId"))));
		Avatar userLoggedInHero = avatarFactory.getAvatarOfUserId(avatarForm
				.getLoggedInUser().getUserId(), "hero");
		userLoggedInHero.setUser(avatarForm.getLoggedInUser());
		avatarForm.setUserLoggedInHero(userLoggedInHero);
		avatarForm.setCurrentAvatar(avatarForm.getUserLoggedInHero());
		if (request.getAttribute("avatarId") != null)
		{
			avatarForm.setCurrentAvatar(avatarFactory
					.getAvatarById((int) request.getAttribute("avatarId")));
		}
		else if (avatarForm.getCurrentAvatar().getAvatarId() == 0)
		{
			forward = mapping.findForward("avatarCreate");
		}
		return forward;
	}
}

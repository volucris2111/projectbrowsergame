
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

public class AvatarCreatePreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AvatarForm avatarForm = (AvatarForm) form;
		if (!avatarForm.isComeFromPost())
		{
			avatarForm.setCurrentAvatar(new Avatar());
			avatarForm.getCurrentAvatar().setType("hero");
			avatarForm.getCurrentAvatar().setMale(true);
			if (avatarForm.getLoggedInUser() == null)
			{
				avatarForm.setLoggedInUser(UserFactory.getInstance()
						.getUserById(
								Integer.parseInt((String) request
										.getAttribute("userId"))));
			}
			avatarForm.setAvailableAvatarClasses(AvatarFactory.getInstance()
					.getAllAvatarClassesAsLinkedList());
		}
		else
		{
			avatarForm.setComeFromPost(false);
		}
		return mapping.findForward("success");
	}
}

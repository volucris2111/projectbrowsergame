
package com.browsergame.project.avatar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.datatransfer.AvatarClass;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.avatar.view.AvatarForm;
import com.browsergame.project.core.controller.BasicAction;

public class AvatarCreatePostAction extends BasicAction

{
	
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			
			final HttpServletResponse response) throws Exception
	{
		
		ActionForward forward = null;
		AvatarForm avatarForm = (AvatarForm) form;
		avatarForm.setErrorMessage("");
		Avatar avatar = avatarForm.getCurrentAvatar();
		if (request.getParameter("randomName") != null
				|| request.getParameter("randomName" + ".x") != null)
		{
			avatar.setName(AvatarFactory.getInstance().getRandomAvatarName(
					avatar.isMale()));
			avatarForm.setComeFromPost(true);
			forward = mapping.findForward("reload");
		}
		else if (request.getParameter("accept") != null
				|| request.getParameter("accept" + ".x") != null)
		{
			if (!avatar.getName().trim().isEmpty())
			{
				AvatarClass selectedAvatarClass = avatarForm
						.getAvailableAvatarClasses().get(
								avatarForm.getSelectedAvatarClassIndex());
				avatar.setAvatarClassId(selectedAvatarClass.getAvatarClassId());
				avatar.setAvatarClass(selectedAvatarClass);
				AvatarFactory.getInstance().createAvatar(
						avatarForm.getLoggedInUser(), avatar);
				forward = mapping.findForward("success");
			}
			else
			{
				avatarForm.setErrorMessage("Bitte wähle einen Namen!");
				avatarForm.setComeFromPost(true);
				forward = mapping.findForward("reload");
			}
		}
		return forward;
	}
}

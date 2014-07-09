
package com.browsergame.project.avatar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.attribute.datatransfer.Attribute;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.avatar.view.AvatarForm;
import com.browsergame.project.core.controller.BasicAction;

public class AvatarDetailsPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AvatarForm avatarForm = (AvatarForm) form;
		AvatarFactory avatarFactory = AvatarFactory.getInstance();
		if (request.getAttribute("avatarId") != null)
		{
			avatarForm.setSelectedAvatarId((Integer) request
					.getAttribute("avatarId"));
		}
		if (avatarForm.getSelectedAvatarId() != 0)
		{
			AvatarFactory.getInstance().getAvatarById(
					avatarForm.getSelectedAvatarId());
			avatarForm.setSelectedAvatarId(0);
			avatarForm.setOwnerOfAvatar(false);
		}
		else
		{
			avatarForm.setAttributePointsToSpend(0);
			avatarForm.setCurrentAvatar(avatarFactory.getAvatarOfUserId(
					avatarForm.getLoggedInUser().getUserId(), "hero"));
			avatarForm.setAttributePointsToSpend((avatarForm.getCurrentAvatar()
					.getLevel() - 1) * 3 + 7);
			for (Attribute currentAttribute : avatarForm.getCurrentAvatar()
					.getAttributes().values())
			{
				avatarForm.setAttributePointsToSpend(avatarForm
						.getAttributePointsToSpend()
						- currentAttribute.getLvl());
			}
			avatarForm.setOwnerOfAvatar(true);
		}
		avatarForm.getCurrentAvatar().setMasters(
				avatarFactory.getGroupOfAvatarIdAsLinkedList(avatarForm
						.getCurrentAvatar().getAvatarId()));
		return mapping.findForward("success");
	}
}
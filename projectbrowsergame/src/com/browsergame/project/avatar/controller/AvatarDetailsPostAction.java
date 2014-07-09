
package com.browsergame.project.avatar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.attribute.datatransfer.Attribute;
import com.browsergame.project.attribute.factory.AttributeFactory;
import com.browsergame.project.avatar.view.AvatarForm;
import com.browsergame.project.core.controller.BasicAction;

public class AvatarDetailsPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AvatarForm avatarForm = (AvatarForm) form;
		ActionForward forward = mapping.findForward("success");
		if (request.getParameter("save") != null
				|| request.getParameter("save" + ".x") != null)
		{
			for (Attribute currentAttribute : avatarForm.getCurrentAvatar()
					.getAttributes().values())
			{
				if (currentAttribute.getNewLvl() != 0)
				{
					currentAttribute.setLvl(currentAttribute.getNewLvl());
				}
			}
			AttributeFactory.getInstance()
					.updateAllAttributesAsHashMapOfAvatarId(
							avatarForm.getCurrentAvatar().getAvatarId(),
							avatarForm.getCurrentAvatar().getAttributes());
		}
		else if (request.getParameter("group") != null
				|| request.getParameter("group" + ".x") != null)
		{
			forward = mapping.findForward("viewGroup");
		}
		else if (request.getParameter("attack") != null
				|| request.getParameter("attack" + ".x") != null)
		{
			request.setAttribute("attackedAvatarId", avatarForm
					.getCurrentAvatar().getAvatarId());
			forward = mapping.findForward("battlePreparation");
		}
		return forward;
	}
}

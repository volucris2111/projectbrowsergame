
package com.browsergame.project.battle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.battle.view.BattleForm;
import com.browsergame.project.core.controller.BasicAction;

public class BattlePreparationPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		BattleForm battleForm = (BattleForm) form;
		battleForm.setAttackedAvatar(AvatarFactory.getInstance().getAvatarById(
				(int) request.getAttribute("attackedAvatarId")));
		return mapping.findForward("success");
	}
}

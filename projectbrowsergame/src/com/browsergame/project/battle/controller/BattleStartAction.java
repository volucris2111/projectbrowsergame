
package com.browsergame.project.battle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.battle.view.BattleForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.user.factory.UserFactory;

public class BattleStartAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		BattleForm battleForm = (BattleForm) form;
		ActionForward forward = mapping.findForward("success");
		battleForm.setLoggedInUser(UserFactory.getInstance().getUserById(
				Integer.parseInt((String) request.getAttribute("userId"))));
		battleForm.setUserLoggedInHero(AvatarFactory.getInstance()
				.getAvatarOfUserId(battleForm.getLoggedInUser().getUserId(),
						"hero"));
		battleForm.getUserLoggedInHero().setUser(battleForm.getLoggedInUser());
		return forward;
	}
}

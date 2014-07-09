
package com.browsergame.project.market.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.market.factory.MarketFactory;

public class MarketPreAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		adventureForm.setCurrentMarket(MarketFactory.getInstance()
				.getMarketWithId(adventureForm.getSelectedMarketId()));
		return mapping.findForward("success");
	}
}

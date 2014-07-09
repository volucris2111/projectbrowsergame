
package com.browsergame.project.market.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.market.datatransfer.MarketResource;
import com.browsergame.project.market.factory.MarketFactory;

public class MarketEditPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward forward = mapping.findForward("success");
		if (request.getParameter("save") != null
				|| request.getParameter("save" + ".x") != null)
		{
			Inventory marketInventory = adventureForm.getCurrentMarket()
					.getInventory();
			Inventory avatarInventory = adventureForm.getUserLoggedInHero()
					.getInventory();
			for (Resource currentResource : Resource.values())
			{
				MarketResource marketResource = adventureForm
						.getCurrentMarket().getMarketResources()
						.get(currentResource);
				marketResource.setMinAmount(Integer.parseInt(request
						.getParameter("minAmount"
								+ marketResource.getResource())));
				marketResource.setMaxAmount(Integer.parseInt(request
						.getParameter("maxAmount"
								+ marketResource.getResource())));
				marketResource
						.setPriceBuy(Integer.parseInt(request
								.getParameter("priceBuy"
										+ marketResource.getResource())));
				marketResource.setPriceSell(Integer.parseInt(request
						.getParameter("priceSell"
								+ marketResource.getResource())));
				MarketFactory.getInstance()
						.updateMarketResource(marketResource);
				if (request.getParameter("transferToAvatar"
						+ currentResource.name()) != null
						&& !request.getParameter(
								"transferToAvatar" + currentResource.name())
								.equals(0)
						&& !request.getParameter(
								"transferToAvatar" + currentResource.name())
								.isEmpty())
				{
					int transferValue = Integer.parseInt(request
							.getParameter("transferToAvatar"
									+ currentResource.name()));
					marketInventory
							.getResources()
							.put(currentResource,
									(marketInventory.getResources().get(
											currentResource) != null ? marketInventory
											.getResources()
											.get(currentResource) : 0)
											- transferValue);
					avatarInventory
							.getResources()
							.put(currentResource,
									(avatarInventory.getResources().get(
											currentResource) != null ? avatarInventory
											.getResources()
											.get(currentResource) : 0)
											+ transferValue);
				}
				if (request.getParameter("transferToBuilding"
						+ currentResource.name()) != null
						&& !request.getParameter(
								"transferToBuilding" + currentResource.name())
								.equals(0)
						&& !request.getParameter(
								"transferToBuilding" + currentResource.name())
								.isEmpty())
				{
					int transferValue = Integer.parseInt(request
							.getParameter("transferToBuilding"
									+ currentResource.name()));
					marketInventory
							.getResources()
							.put(currentResource,
									(marketInventory.getResources().get(
											currentResource) != null ? marketInventory
											.getResources()
											.get(currentResource) : 0)
											+ transferValue);
					avatarInventory
							.getResources()
							.put(currentResource,
									(avatarInventory.getResources().get(
											currentResource) != null ? avatarInventory
											.getResources()
											.get(currentResource) : 0)
											- transferValue);
				}
			}
			int transferCoins = 0;
			if (request.getParameter("transferToBuildingCoins") != null)
			{
				transferCoins = transferCoins
						+ Integer.parseInt(request
								.getParameter("transferToBuildingCoins"));
			}
			if (request.getParameter("transferToAvatarCoins") != null)
			{
				transferCoins = transferCoins
						- Integer.parseInt(request
								.getParameter("transferToAvatarCoins"));
			}
			marketInventory
					.setCoins(marketInventory.getCoins() + transferCoins);
			avatarInventory.setCoins(avatarInventory.getCoins() + transferCoins
					* -1);
			InventoryFactory.getInstance().updateInventory(avatarInventory);
			InventoryFactory.getInstance().updateInventory(marketInventory);
		}
		if (request.getParameter("back") != null
				|| request.getParameter("back" + ".x") != null)
		{
			forward = mapping.findForward("back");
		}
		return forward;
	}
}

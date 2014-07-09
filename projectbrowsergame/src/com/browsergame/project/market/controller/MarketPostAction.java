
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
import com.browsergame.project.market.datatransfer.Market;

public class MarketPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		ActionForward foward = mapping.findForward("success");
		if (request.getParameter("trade") != null
				|| request.getParameter("trade" + ".x") != null)
		{
			Inventory avatarInventory = adventureForm.getUserLoggedInHero()
					.getInventory();
			Inventory marketInventory = adventureForm.getCurrentMarket()
					.getInventory();
			Market buildingMarket = adventureForm.getCurrentMarket();
			
			for (Resource currentResource : Resource.values())
			{
				if (request.getParameter(currentResource.name() + "Buy") != null
						&& !(request.getParameter(currentResource.name()
								+ "Buy").equals("0")))
				{
					int purchasedAmount = Integer.parseInt(request
							.getParameter(currentResource.name() + "Buy"));
					avatarInventory
							.getResources()
							.put(currentResource,
									(avatarInventory.getResources().get(
											currentResource) != null ? avatarInventory
											.getResources()
											.get(currentResource) : 0)
											+ purchasedAmount);
					avatarInventory.setCoins(avatarInventory.getCoins()
							- purchasedAmount
							* buildingMarket.getMarketResources()
									.get(currentResource).getPriceSell());
					marketInventory
							.getResources()
							.put(currentResource,
									(marketInventory.getResources().get(
											currentResource) != null ? marketInventory
											.getResources()
											.get(currentResource) : 0)
											- purchasedAmount);
				}
				if (request.getParameter(currentResource.name() + "Sell") != null
						&& !(request.getParameter(currentResource.name()
								+ "Sell").equals("0")))
				{
					int selledAmount = Integer.parseInt(request
							.getParameter(currentResource.name() + "Sell"));
					avatarInventory
							.getResources()
							.put(currentResource,
									(avatarInventory.getResources().get(
											currentResource) != null ? avatarInventory
											.getResources()
											.get(currentResource) : 0)
											- selledAmount);
					avatarInventory.setCoins(avatarInventory.getCoins()
							+ selledAmount
							* buildingMarket.getMarketResources()
									.get(currentResource).getPriceBuy());
					marketInventory
							.getResources()
							.put(currentResource,
									(marketInventory.getResources().get(
											currentResource) != null ? marketInventory
											.getResources()
											.get(currentResource) : 0)
											+ selledAmount);
				}
			}
			InventoryFactory inventoryFactory = InventoryFactory.getInstance();
			inventoryFactory.updateInventory(marketInventory);
			inventoryFactory.updateInventory(avatarInventory);
		}
		else if (request.getParameter("back") != null
				|| request.getParameter("back" + ".x") != null)
		{
			foward = mapping.findForward("back");
			adventureForm.setComeFromPage("");
		}
		else if (request.getParameter("changeMarket") != null
				|| request.getParameter("changeMarket" + ".x") != null)
		{
			foward = mapping.findForward("changeMarket");
		}
		else if (request.getParameter("locationFreeMarket") != null
				|| request.getParameter("locationFreeMarket" + ".x") != null)
		{
			foward = mapping.findForward("locationFreeMarket");
		}
		else if (request.getParameter("edit") != null
				|| request.getParameter("edit" + ".x") != null)
		{
			foward = mapping.findForward("edit");
		}
		return foward;
	}
}

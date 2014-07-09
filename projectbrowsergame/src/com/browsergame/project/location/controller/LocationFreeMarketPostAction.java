
package com.browsergame.project.location.controller;

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
import com.browsergame.project.market.constants.MarketComeFromConstants;
import com.browsergame.project.market.datatransfer.Market;
import com.browsergame.project.market.factory.MarketFactory;

public class LocationFreeMarketPostAction extends BasicAction
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
			Market locationfreeMarket = adventureForm.getCurrentField()
					.getLocation().getLocationFreeMarket();
			for (Resource currentResource : Resource.values())
			{
				boolean marketResourceHasChanged = false;
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
							* locationfreeMarket.getMarketResources()
									.get(currentResource).getPriceSell());
					
					int priceChange = (purchasedAmount + locationfreeMarket
							.getMarketResources().get(currentResource)
							.getAmountCounterSell()) / 150;
					locationfreeMarket
							.getMarketResources()
							.get(currentResource)
							.setAmountCounterSell(
									(purchasedAmount + locationfreeMarket
											.getMarketResources()
											.get(currentResource)
											.getAmountCounterSell()) % 150);
					locationfreeMarket.getMarketResources()
							.get(currentResource).setLastSellCycle(0);
					locationfreeMarket
							.getMarketResources()
							.get(currentResource)
							.setPriceSell(
									locationfreeMarket.getMarketResources()
											.get(currentResource)
											.getPriceSell()
											+ priceChange);
					marketResourceHasChanged = true;
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
							* locationfreeMarket.getMarketResources()
									.get(currentResource).getPriceBuy());
					
					int priceChange = (selledAmount + locationfreeMarket
							.getMarketResources().get(currentResource)
							.getAmountCounterBuy()) / 150;
					locationfreeMarket
							.getMarketResources()
							.get(currentResource)
							.setAmountCounterBuy(
									(selledAmount + locationfreeMarket
											.getMarketResources()
											.get(currentResource)
											.getAmountCounterBuy()) % 150);
					locationfreeMarket.getMarketResources()
							.get(currentResource).setLastBuyCycle(0);
					if (locationfreeMarket.getMarketResources()
							.get(currentResource).getPriceBuy() > 1
							&& locationfreeMarket.getMarketResources()
									.get(currentResource).getPriceBuy()
									- priceChange > 1)
					{
						locationfreeMarket
								.getMarketResources()
								.get(currentResource)
								.setPriceBuy(
										locationfreeMarket.getMarketResources()
												.get(currentResource)
												.getPriceBuy()
												- priceChange);
					}
					else
					{
						locationfreeMarket.getMarketResources()
								.get(currentResource).setPriceBuy(1);
					}
					marketResourceHasChanged = true;
				}
				if (marketResourceHasChanged)
				{
					MarketFactory.getInstance().updateMarketResource(
							locationfreeMarket.getMarketResources().get(
									currentResource));
				}
			}
			InventoryFactory.getInstance().updateInventory(avatarInventory);
		}
		else if (request.getParameter("back") != null
				|| request.getParameter("back" + ".x") != null)
		{
			foward = mapping.findForward("back");
		}
		else if (request.getParameter("changeMarket") != null
				|| request.getParameter("changeMarket" + ".x") != null)
		{
			adventureForm
					.setComeFromPage(MarketComeFromConstants.LOCATIONMARKET);
			foward = mapping.findForward("changeMarket");
		}
		return foward;
	}
}


package com.browsergame.project.market.datatransfer;

import java.io.Serializable;

import com.browsergame.project.inventory.datatransfer.Resource;

public class MarketResource implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5529203358868045818L;
	
	private int amountCounterBuy;
	
	private int amountCounterSell;
	
	private int lastBuyCycle;
	
	private int lastSellCycle;
	
	private int marketId;
	
	private int marketResourceId;
	
	private int maxAmount;
	
	private int minAmount;
	
	private int priceBuy;
	
	private int priceSell;
	
	private Resource resource;
	
	public int getAmountCounterBuy()
	{
		return amountCounterBuy;
	}
	
	public int getAmountCounterSell()
	{
		return amountCounterSell;
	}
	
	public int getLastBuyCycle()
	{
		return lastBuyCycle;
	}
	
	public int getLastSellCycle()
	{
		return lastSellCycle;
	}
	
	public int getMarketId()
	{
		return marketId;
	}
	
	public int getMarketResourceId()
	{
		return marketResourceId;
	}
	
	public int getMaxAmount()
	{
		return maxAmount;
	}
	
	public int getMinAmount()
	{
		return minAmount;
	}
	
	public int getPriceBuy()
	{
		return priceBuy;
	}
	
	public int getPriceSell()
	{
		return priceSell;
	}
	
	public Resource getResource()
	{
		return resource;
	}
	
	public void setAmountCounterBuy(final int amountCounterBuy)
	{
		this.amountCounterBuy = amountCounterBuy;
	}
	
	public void setAmountCounterSell(final int amountCounterSell)
	{
		this.amountCounterSell = amountCounterSell;
	}
	
	public void setLastBuyCycle(final int lastBuyCycle)
	{
		this.lastBuyCycle = lastBuyCycle;
	}
	
	public void setLastSellCycle(final int lastSellCycle)
	{
		this.lastSellCycle = lastSellCycle;
	}
	
	public void setMarketId(final int marketId)
	{
		this.marketId = marketId;
	}
	
	public void setMarketResourceId(final int marketResourceId)
	{
		this.marketResourceId = marketResourceId;
	}
	
	public void setMaxAmount(final int maxAmount)
	{
		this.maxAmount = maxAmount;
	}
	
	public void setMinAmount(final int minAmount)
	{
		this.minAmount = minAmount;
	}
	
	public void setPriceBuy(final int priceBuy)
	{
		this.priceBuy = priceBuy;
	}
	
	public void setPriceSell(final int priceSell)
	{
		this.priceSell = priceSell;
	}
	
	public void setResource(final Resource resource)
	{
		this.resource = resource;
	}
	
}

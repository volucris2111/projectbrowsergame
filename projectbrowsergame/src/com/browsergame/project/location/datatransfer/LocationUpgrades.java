
package com.browsergame.project.location.datatransfer;

import java.io.Serializable;

public class LocationUpgrades implements Serializable
{
	private static final long serialVersionUID = -5345934912762726886L;
	
	private int bakerLvl;
	
	private int butcherLvl;
	
	private int carpenterLvl;
	
	private int housingLvl;
	
	private int smithLvl;
	
	private int tailorLvl;
	
	public int getBakerLvl()
	{
		return bakerLvl;
	}
	
	public int getButcherLvl()
	{
		return butcherLvl;
	}
	
	public int getCarpenterLvl()
	{
		return carpenterLvl;
	}
	
	public int getHousingLvl()
	{
		return housingLvl;
	}
	
	public int getSmithLvl()
	{
		return smithLvl;
	}
	
	public int getTailorLvl()
	{
		return tailorLvl;
	}
	
	public void setBakerLvl(final int bakerLvl)
	{
		this.bakerLvl = bakerLvl;
	}
	
	public void setButcherLvl(final int butcherLvl)
	{
		this.butcherLvl = butcherLvl;
	}
	
	public void setCarpenterLvl(final int carpenterLvl)
	{
		this.carpenterLvl = carpenterLvl;
	}
	
	public void setHousingLvl(final int housingLvl)
	{
		this.housingLvl = housingLvl;
	}
	
	public void setSmithLvl(final int smithLvl)
	{
		this.smithLvl = smithLvl;
	}
	
	public void setTailorLvl(final int tailorLvl)
	{
		this.tailorLvl = tailorLvl;
	}
}

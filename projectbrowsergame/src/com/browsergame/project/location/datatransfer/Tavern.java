
package com.browsergame.project.location.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.Master;

public class Tavern implements Serializable
{
	
	private static final long serialVersionUID = -938774296176748889L;
	
	private Master shownMaster = new Master();
	
	private LinkedList<Master> travelingMasters = new LinkedList<Master>();
	
	public int getAmountOfMasters()
	{
		return getTravelingMasters().size();
	}
	
	public Master getShownMaster()
	{
		return shownMaster;
	}
	
	public LinkedList<Master> getTravelingMasters()
	{
		return travelingMasters;
	}
	
	public void setShownMaster(final Master shownMaster)
	{
		this.shownMaster = shownMaster;
	}
	
	public void setTravelingMasters(final LinkedList<Master> travelingMasters)
	{
		this.travelingMasters = travelingMasters;
	}
	
}

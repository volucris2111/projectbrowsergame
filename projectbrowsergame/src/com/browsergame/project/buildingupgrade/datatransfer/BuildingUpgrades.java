
package com.browsergame.project.buildingupgrade.datatransfer;

import java.io.Serializable;

public class BuildingUpgrades implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2447725011775622060L;
	
	private int buildingId;
	
	private int guards;
	
	private int hideout;
	
	private int storage;
	
	private int tools;
	
	private int worker;
	
	public int getBuildingId()
	{
		return buildingId;
	}
	
	public int getGuards()
	{
		return guards;
	}
	
	public int getHideout()
	{
		return hideout;
	}
	
	public int getStorage()
	{
		return storage;
	}
	
	public int getTools()
	{
		return tools;
	}
	
	public int getWorker()
	{
		return worker;
	}
	
	public void setBuildingId(final int buildingId)
	{
		this.buildingId = buildingId;
	}
	
	public void setGuards(final int guards)
	{
		this.guards = guards;
	}
	
	public void setHideout(final int hideout)
	{
		this.hideout = hideout;
	}
	
	public void setStorage(final int storage)
	{
		this.storage = storage;
	}
	
	public void setTools(final int tools)
	{
		this.tools = tools;
	}
	
	public void setWorker(final int worker)
	{
		this.worker = worker;
	}
}


package com.browsergame.project.terrain.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.Avatar;

public abstract class Terrain implements Serializable, Cloneable
{
	private static final long serialVersionUID = 1806637237153304754L;
	
	private LinkedList<String> actions = new LinkedList<String>();
	
	private String imageUrl;
	
	private String terrainForDisplay;
	
	private String type;
	
	public String doAction(final String action, final Avatar avatar)
	{
		return "";
	}
	
	public LinkedList<String> getActions()
	{
		return actions;
	}
	
	public String getImageUrl()
	{
		return imageUrl;
	}
	
	public String getTerrainForDisplay()
	{
		return terrainForDisplay;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setActions(final LinkedList<String> actions)
	{
		this.actions = actions;
	}
	
	public void setImageUrl(final String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	
	public void setTerrainForDisplay(final String terrainForDisplay)
	{
		this.terrainForDisplay = terrainForDisplay;
	}
	
	public void setType(final String type)
	{
		this.type = type;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}

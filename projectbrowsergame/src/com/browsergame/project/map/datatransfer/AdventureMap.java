
package com.browsergame.project.map.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

public class AdventureMap implements Serializable
{
	private static final long serialVersionUID = 5696360497062958905L;
	
	private LinkedList<AdventureMapRow> adventureMapRows = new LinkedList<AdventureMapRow>();
	
	public LinkedList<AdventureMapRow> getAdventureMapRows()
	{
		return adventureMapRows;
	}
	
	public void setAdventureMapRows(
			final LinkedList<AdventureMapRow> adventureMapRows)
	{
		this.adventureMapRows = adventureMapRows;
	}
}


package com.browsergame.project.terrain.factory;

import com.browsergame.project.cache.factory.CacheFactory;
import com.browsergame.project.terrain.datatransfer.Forest;
import com.browsergame.project.terrain.datatransfer.Grass;
import com.browsergame.project.terrain.datatransfer.Mountain;
import com.browsergame.project.terrain.datatransfer.Sea;

public class TerrainFactory
{
	
	private static TerrainFactory INSTANCE = new TerrainFactory();
	
	public static TerrainFactory getInstance()
	{
		return INSTANCE;
	}
	
	public String getTypOfFieldAt(final String xKoords, final String yKoords)
	{
		String returnString;
		if (CacheFactory.getInstance().getElementAtFromCache(xKoords, yKoords) != null)
		{
			returnString = CacheFactory.getInstance()
					.getElementAtFromCache(xKoords, yKoords).getTerrainString();
			
		}
		else
		{
			returnString = "Sea";
		}
		return returnString;
		
	}
	
	public boolean walkLogics(final String typeOfField, final int actionPoints)
	{
		boolean fieldCanBeEntered = false;
		switch (typeOfField)
		{
		case "SEA":
			fieldCanBeEntered = Sea.walkLogics(actionPoints);
			break;
		case "FOREST":
			fieldCanBeEntered = Forest.walkLogics(actionPoints);
			break;
		case "GRASS":
			fieldCanBeEntered = Grass.walkLogics(actionPoints);
			break;
		case "MOUNTAIN":
			fieldCanBeEntered = Mountain.walkLogics(actionPoints);
			break;
		
		default:
			break;
		}
		
		return fieldCanBeEntered;
	}
	
}

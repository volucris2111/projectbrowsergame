
package com.browsergame.project.terrain.datatransfer;

import com.browsergame.project.terrain.constants.TerrainTypeConstants;

public class Sea extends Terrain
{
	private static final long serialVersionUID = 8730039915705402336L;
	
	public static boolean walkLogics(final int actionPoints)
	{
		
		// return actionPoints > TerrainNessesaryActionPoints.ActionPointsSea
		// .getWert();
		return false;
	}
	
	public Sea()
	{
		setTerrainForDisplay("Meer");
		setType(TerrainTypeConstants.SEA);
		setImageUrl("../images/terrain/terrainsea.gif");
	}
	
}

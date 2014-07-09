
package com.browsergame.project.terrain.datatransfer;

import com.browsergame.project.terrain.constants.TerrainNessesaryActionPoints;
import com.browsergame.project.terrain.constants.TerrainTypeConstants;

public class Grass extends Terrain
{
	private static final long serialVersionUID = -1971436597326020123L;
	
	public static boolean walkLogics(final int actionPoints)
	{
		return (actionPoints > TerrainNessesaryActionPoints.ActionPointsGras
				.getWert());
	}
	
	public Grass()
	{
		setType(TerrainTypeConstants.GRASS);
		setTerrainForDisplay("Weide");
		setImageUrl("../images/terrain/terraingrass.gif");
	}
}

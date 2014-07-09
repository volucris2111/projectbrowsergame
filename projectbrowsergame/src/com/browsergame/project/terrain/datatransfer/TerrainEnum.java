
package com.browsergame.project.terrain.datatransfer;

public enum TerrainEnum
{
	FOREST("forest", "Wald", new Forest()),
	GRASS("grass", "Weide", new Grass()),
	MOUNTAIN("mountain", "Berge", new Mountain()),
	SEA("sea", "Meer", new Sea());
	
	private Terrain terrain;
	
	private String terrainType;
	
	private String terrainTypeForJsp;
	
	TerrainEnum(final String terrainType, final String terrainTypeForJsp,
			final Terrain terrain)
	{
		setTerrain(terrain);
		setTerrainType(terrainTypeForJsp);
		setTerrainTypeForJsp(terrainTypeForJsp);
	}
	
	public Terrain getTerrain()
	{
		Terrain terrainClone = null;
		try
		{
			return (Terrain) terrain.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return terrainClone;
	}
	
	public String getTerrainType()
	{
		return terrainType;
	}
	
	public String getTerrainTypeForJsp()
	{
		return terrainTypeForJsp;
	}
	
	public void setTerrain(final Terrain terrain)
	{
		this.terrain = terrain;
	}
	
	public void setTerrainType(final String terrainType)
	{
		this.terrainType = terrainType;
	}
	
	public void setTerrainTypeForJsp(final String terrainTypeForJsp)
	{
		this.terrainTypeForJsp = terrainTypeForJsp;
	}
	
}

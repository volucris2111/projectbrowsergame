package com.browsergame.project.terrain.constants;

public enum TerrainNessesaryActionPoints
{

	ActionPointsGras(1), ActionPointsMountain(3), ActionPointsSea(1);
	int wert;

	private TerrainNessesaryActionPoints(int zuweisungswert)
	{
		this.wert = zuweisungswert;
	}

	public int getWert()
	{
		return this.wert;
	}

	public void setWert(int wert)
	{
		this.wert = wert;
	}
}

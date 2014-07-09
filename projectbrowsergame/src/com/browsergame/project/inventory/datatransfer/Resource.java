
package com.browsergame.project.inventory.datatransfer;

public enum Resource
{
	CLOTHES("clothes", "Kleidung"),
	FISH("fish", "Fisch"),
	FOOD("food", "Nahrung"),
	FURNITURE("furniture", "Möbel"),
	GRAIN("grain", "Getreide"),
	IRON("iron", "Eisen"),
	LEATHER("leather", "Leder"),
	MEAT("meat", "Fleisch"),
	STONE("stone", "Stein"),
	TOOL("tool", "Werkzeug"),
	WOOD("wood", "Holz"),
	WOOL("wool", "Wolle");
	
	private String resource;
	
	private String resourceForJsp;
	
	Resource(final String resource, final String resourceForJsp)
	{
		this.resource = resource;
		this.resourceForJsp = resourceForJsp;
	}
	
	public String getName()
	{
		return name();
	}
	
	public String getResource()
	{
		return resource;
	}
	
	public String getResourceForJsp()
	{
		return resourceForJsp;
	}
	
	public Resource[] getValues()
	{
		return values();
	}
	
	public void setResource(final String resource)
	{
		this.resource = resource;
	}
	
	public void setResourceForJsp(final String resourceForJsp)
	{
		this.resourceForJsp = resourceForJsp;
	}
}

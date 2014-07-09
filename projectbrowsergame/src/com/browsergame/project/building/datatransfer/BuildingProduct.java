
package com.browsergame.project.building.datatransfer;

import java.io.Serializable;

import com.browsergame.project.inventory.datatransfer.Resource;

public class BuildingProduct implements Serializable
{
	
	private static final long serialVersionUID = -6277592736052516818L;
	
	private int factor;
	
	private String requiredSkill;
	
	private Resource resource;
	
	public int getFactor()
	{
		return factor;
	}
	
	public String getRequiredSkill()
	{
		return requiredSkill;
	}
	
	public Resource getResource()
	{
		return resource;
	}
	
	public void setFactor(final int factor)
	{
		this.factor = factor;
	}
	
	public void setRequiredSkill(final String requiredSkill)
	{
		this.requiredSkill = requiredSkill;
	}
	
	public void setResource(final Resource resource)
	{
		this.resource = resource;
	}
	
}

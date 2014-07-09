
package com.browsergame.project.attribute.datatransfer;

import java.io.Serializable;

public class Attribute implements Serializable
{
	private static final long serialVersionUID = 5660610961763242539L;
	
	private int attributeId;
	
	private int lvl; // transient
	
	private String name;
	
	private String nameForJsp;
	
	private int newLvl; // transient
	
	public int getAttributeId()
	{
		return attributeId;
	}
	
	public int getLvl()
	{
		return lvl;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getNameForJsp()
	{
		return nameForJsp;
	}
	
	public int getNewLvl()
	{
		return newLvl;
	}
	
	public void setAttributeId(final int attributeId)
	{
		this.attributeId = attributeId;
	}
	
	public void setLvl(final int lvl)
	{
		this.lvl = lvl;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setNameForJsp(final String nameForJsp)
	{
		this.nameForJsp = nameForJsp;
	}
	
	public void setNewLvl(final int newLvl)
	{
		this.newLvl = newLvl;
	}
	
}

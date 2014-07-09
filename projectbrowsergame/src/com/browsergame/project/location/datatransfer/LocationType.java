
package com.browsergame.project.location.datatransfer;

import java.io.Serializable;

public class LocationType implements Serializable
{
	private static final long serialVersionUID = 7113357794420827535L;
	
	private String imageUrl;
	
	private String name;
	
	private String nameForJsp;
	
	public String getImageUrl()
	{
		return imageUrl;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getNameForJsp()
	{
		return nameForJsp;
	}
	
	public void setImageUrl(final String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setNameForJsp(final String nameForJsp)
	{
		this.nameForJsp = nameForJsp;
	}
}
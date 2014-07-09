
package com.browsergame.project.avatar.datatransfer;

import java.io.Serializable;

public class Master implements Serializable
{
	private static final long serialVersionUID = 778365647979069617L;
	
	private boolean available;
	
	private AvatarClass avatarClass;
	
	private int avatarClassId;
	
	private int costs;
	
	private int currentLocationId;
	
	private boolean male;
	
	private int masterId;
	
	private String name;
	
	public AvatarClass getAvatarClass()
	{
		return avatarClass;
	}
	
	public int getAvatarClassId()
	{
		return avatarClassId;
	}
	
	public int getCosts()
	{
		return costs;
	}
	
	public int getCurrentLocationId()
	{
		return currentLocationId;
	}
	
	public int getMasterId()
	{
		return masterId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isAvailable()
	{
		return available;
	}
	
	public boolean isMale()
	{
		return male;
	}
	
	public void setAvailable(final boolean available)
	{
		this.available = available;
	}
	
	public void setAvatarClass(final AvatarClass avatarClass)
	{
		this.avatarClass = avatarClass;
	}
	
	public void setAvatarClassId(final int avatarClassId)
	{
		this.avatarClassId = avatarClassId;
	}
	
	public void setCosts(final int costs)
	{
		this.costs = costs;
	}
	
	public void setCurrentLocationId(final int currentLocationId)
	{
		this.currentLocationId = currentLocationId;
	}
	
	public void setMale(final boolean male)
	{
		this.male = male;
	}
	
	public void setMasterId(final int masterId)
	{
		this.masterId = masterId;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
}

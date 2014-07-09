
package com.browsergame.project.struggling.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

public class Struggling implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3648039174665176999L;
	
	private LinkedList<Claimer> claimers = new LinkedList<Claimer>();
	
	private long endTimestamp;
	
	private int locationId;
	
	private int strugglingId;
	
	public LinkedList<Claimer> getClaimers()
	{
		return claimers;
	}
	
	public long getEndTimestamp()
	{
		return endTimestamp;
	}
	
	public int getLocationId()
	{
		return locationId;
	}
	
	public int getStrugglingId()
	{
		return strugglingId;
	}
	
	public void setClaimers(final LinkedList<Claimer> claimers)
	{
		this.claimers = claimers;
	}
	
	public void setEndTimestamp(final long endTimestamp)
	{
		this.endTimestamp = endTimestamp;
	}
	
	public void setLocationId(final int locationId)
	{
		this.locationId = locationId;
	}
	
	public void setStrugglingId(final int strugglingId)
	{
		this.strugglingId = strugglingId;
	}
}

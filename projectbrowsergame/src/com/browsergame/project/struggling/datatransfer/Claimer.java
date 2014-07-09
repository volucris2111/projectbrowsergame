
package com.browsergame.project.struggling.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.Avatar;

public class Claimer implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4380722980499932019L;
	
	private Avatar claimer;
	
	private LinkedList<Avatar> followers = new LinkedList<Avatar>();
	
	public Avatar getClaimer()
	{
		return claimer;
	}
	
	public LinkedList<Avatar> getFollowers()
	{
		return followers;
	}
	
	public void setClaimer(final Avatar claimer)
	{
		this.claimer = claimer;
	}
	
	public void setFollowers(final LinkedList<Avatar> followers)
	{
		this.followers = followers;
	}
	
}

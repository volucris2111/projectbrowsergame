
package com.browsergame.project.struggling.datatransfer;

import java.io.Serializable;

public class StrugglingAvatar implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4244212105197715901L;
	
	private int avatarId;
	
	private int supportedAvatarId;
	
	public int getAvatarId()
	{
		return avatarId;
	}
	
	public int getSupportedAvatarId()
	{
		return supportedAvatarId;
	}
	
	public void setAvatarId(final int avatarId)
	{
		this.avatarId = avatarId;
	}
	
	public void setSupportedAvatarId(final int supportedAvatarId)
	{
		this.supportedAvatarId = supportedAvatarId;
	}
}
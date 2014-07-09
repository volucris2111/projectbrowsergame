
package com.browsergame.project.avatar.constants;

public enum AvatarInsideActionConstant
{
	VISIT("visit"),
	WORK("work");
	
	private String action;
	
	private AvatarInsideActionConstant(final String action)
	{
		setAction(action);
	}
	
	public String getAction()
	{
		return action;
	}
	
	public void setAction(final String action)
	{
		this.action = action;
	}
}

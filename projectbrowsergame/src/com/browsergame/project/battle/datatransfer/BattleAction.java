
package com.browsergame.project.battle.datatransfer;

import java.io.Serializable;

public class BattleAction implements Serializable
{
	private static final long serialVersionUID = 4717825206449339329L;
	
	private String actionName;
	
	private String actionSpezification;
	
	public String getActionName()
	{
		return actionName;
	}
	
	public String getActionSpezification()
	{
		return actionSpezification;
	}
	
	public void setActionName(final String actionName)
	{
		this.actionName = actionName;
	}
	
	public void setActionSpezification(final String actionSpezification)
	{
		this.actionSpezification = actionSpezification;
	}
	
}

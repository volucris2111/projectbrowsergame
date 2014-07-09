
package com.browsergame.project.battle.constants;

public enum BattleActionsConstant
{
	ATTACK("attack");
	
	private String actionName;
	
	BattleActionsConstant(final String actionName)
	{
		this.actionName = actionName;
	}
	
	public String getActionName()
	{
		return actionName;
	}
}

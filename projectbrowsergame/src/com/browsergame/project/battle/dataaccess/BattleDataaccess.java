
package com.browsergame.project.battle.dataaccess;


public class BattleDataaccess
{
	private static BattleDataaccess INSTANCE = new BattleDataaccess();
	
	public static BattleDataaccess getInstance()
	{
		return BattleDataaccess.INSTANCE;
	}
	
	private BattleDataaccess()
	{
	}
}

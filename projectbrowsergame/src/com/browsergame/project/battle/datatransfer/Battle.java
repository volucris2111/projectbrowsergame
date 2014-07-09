
package com.browsergame.project.battle.datatransfer;

import java.io.Serializable;
import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.Avatar;

public class Battle implements Serializable
{
	
	private static final long serialVersionUID = 2742513059705286437L;
	
	private LinkedList<LinkedList<Avatar>> allGroups = new LinkedList<>();
	
	private int battleId;
	
	private String winner;
	
	public LinkedList<LinkedList<Avatar>> getAllGroups()
	{
		return allGroups;
	}
	
	public int getBattleId()
	{
		return battleId;
	}
	
	public String getWinner()
	{
		return winner;
	}
	
	public void setAllGroups(final LinkedList<LinkedList<Avatar>> allGroups)
	{
		this.allGroups = allGroups;
	}
	
	public void setBattleId(final int battleId)
	{
		this.battleId = battleId;
	}
	
	public void setWinner(final String winner)
	{
		this.winner = winner;
	}
	
}

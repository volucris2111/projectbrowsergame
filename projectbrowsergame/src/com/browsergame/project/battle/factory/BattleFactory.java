
package com.browsergame.project.battle.factory;

import java.util.LinkedList;
import java.util.Random;

import com.browsergame.project.attribute.constants.AttributeNameConstants;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.battle.constants.BattleActionsConstant;
import com.browsergame.project.battle.datatransfer.Battle;
import com.browsergame.project.battle.datatransfer.BattleAction;
import com.browsergame.project.skill.constants.SkillNameConstants;

public class BattleFactory
{
	private static BattleFactory INSTANCE = new BattleFactory();
	
	public static BattleFactory getInstance()
	{
		return BattleFactory.INSTANCE;
	}
	
	private BattleFactory()
	{
	}
	
	public boolean cheackHit(final Avatar attacker, final Avatar target)
	{
		boolean hit = false;
		if (attacker.getEquipment() != null
				&& attacker.getEquipment().getEquipmentId() != 0)
		{
			
		}
		else
		{
			Random random = new Random();
			hit = random.nextInt(attacker.getFightingSkills()
					.get(SkillNameConstants.unarmed).getLvl()
					+ attacker.getAttributes()
							.get(AttributeNameConstants.dexterity).getLvl()) > random
					.nextInt(target.getAttributes()
							.get(AttributeNameConstants.dexterity).getLvl());
		}
		return hit;
	}
	
	public void doAction(final Avatar avatar)
	{
		
	}
	
	public void doAttack(final Avatar attacker, final Avatar target)
	{
		boolean hit = cheackHit(attacker, target);
		if (hit)
		{
			getDamage(attacker);
			getDefence(target);
		}
	}
	
	public void doBattle(final Battle battle)
	{
		boolean fightOver = false;
		LinkedList<Avatar> actionOrder = new LinkedList<Avatar>();
		for (LinkedList<Avatar> currentGroup : battle.getAllGroups())
		{
			actionOrder.addAll(currentGroup);
		}
		
		while (!fightOver)
		{
			
			if (battle.getAllGroups().size() == 1)
			{
				fightOver = true;
			}
		}
	}
	
	public int getDamage(final Avatar attacker)
	{
		int damage = 0;
		if (attacker.getEquipment() != null
				&& attacker.getEquipment().getEquipmentId() != 0)
		{
			
		}
		else
		{
			Random random = new Random();
			damage = random.nextInt(2 + (attacker.getAttributes()
					.get(AttributeNameConstants.strength).getLvl() / 6));
		}
		return damage;
	}
	
	public int getDefence(final Avatar defender)
	{
		int defence = 0;
		
		return defence;
	}
	
	public void getNextTarget(final Avatar attacker, final Battle battle)
	{
		
	}
	
	public void setNextAction(final Avatar avatar)
	{
		BattleAction battleAction = new BattleAction();
		battleAction
				.setActionName(BattleActionsConstant.ATTACK.getActionName());
		avatar.setNextBattleAction(battleAction);
		avatar.setNextActionCounter(5 - avatar.getAttributes()
				.get(AttributeNameConstants.dexterity).getLvl() / 3);
	}
}

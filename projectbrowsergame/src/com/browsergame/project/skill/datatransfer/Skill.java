
package com.browsergame.project.skill.datatransfer;

import java.io.Serializable;

public class Skill implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013770817643412082L;
	
	private int exp;
	
	private int lvl; // transient
	
	private String name;
	
	private String nameForJsp;
	
	private int skillId;
	
	private boolean specialization;
	
	private String type;
	
	public int getExp()
	{
		return exp;
	}
	
	public int getLvl()
	{
		return lvl;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getNameForJsp()
	{
		return nameForJsp;
	}
	
	public int getSkillId()
	{
		return skillId;
	}
	
	public String getType()
	{
		return type;
	}
	
	public boolean isSpecialization()
	{
		return specialization;
	}
	
	public void setExp(final int exp)
	{
		this.exp = exp;
	}
	
	public void setLvl(final int lvl)
	{
		this.lvl = lvl;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setNameForJsp(final String nameForJsp)
	{
		this.nameForJsp = nameForJsp;
	}
	
	public void setSkillId(final int skillId)
	{
		this.skillId = skillId;
	}
	
	public void setSpecialization(final boolean specialization)
	{
		this.specialization = specialization;
	}
	
	public void setType(final String type)
	{
		this.type = type;
	}
}

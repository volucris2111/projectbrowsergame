
package com.browsergame.project.avatar.datatransfer;

import java.io.Serializable;
import java.util.HashMap;

import com.browsergame.project.skill.datatransfer.Skill;

public class AvatarClass implements Serializable
{
	private static final long serialVersionUID = -9171445400009908154L;
	
	private int avatarClassId;
	
	private String name;
	
	private String nameForJspFemale;
	
	private String nameForJspMale;
	
	private HashMap<String, Skill> skills = new HashMap<String, Skill>();
	
	public int getAvatarClassId()
	{
		return avatarClassId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getNameForJspFemale()
	{
		return nameForJspFemale;
	}
	
	public String getNameForJspMale()
	{
		return nameForJspMale;
	}
	
	public HashMap<String, Skill> getSkills()
	{
		return skills;
	}
	
	public void setAvatarClassId(final int avatarClassId)
	{
		this.avatarClassId = avatarClassId;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setNameForJspFemale(final String nameForJspFemale)
	{
		this.nameForJspFemale = nameForJspFemale;
	}
	
	public void setNameForJspMale(final String nameForJspMale)
	{
		this.nameForJspMale = nameForJspMale;
	}
	
	public void setSkills(final HashMap<String, Skill> skills)
	{
		this.skills = skills;
	}
}

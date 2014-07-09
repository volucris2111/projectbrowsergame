
package com.browsergame.project.skill.factory;

import java.util.HashMap;

import com.browsergame.project.avatar.datatransfer.AvatarClass;
import com.browsergame.project.skill.dataaccess.SkillDataaccess;
import com.browsergame.project.skill.datatransfer.Skill;

public class SkillFactory
{
	private static SkillFactory INSTANCE = new SkillFactory();
	
	public static SkillFactory getInstance()
	{
		return SkillFactory.INSTANCE;
	}
	
	private SkillFactory()
	{
	}
	
	public void createAllSkillsForAvatarId(final int avatarId)
	{
		SkillDataaccess.getInstance().createAllSkillsForAvatarId(avatarId);
	}
	
	public void fillSkillOfAvatarClass(final AvatarClass avatarClass)
	{
		SkillDataaccess.getInstance().fillSkillOfAvatarClass(avatarClass);
	}
	
	public int getLevelForSkillExp(final int skillExp)
	{
		return SkillDataaccess.getInstance().getLevelForSkillExp(skillExp);
	}
	
	public HashMap<String, Skill> getSkillsAsHashMapOfAvatarId(
			final int avatarId)
	{
		HashMap<String, Skill> skillsOfAvatar = SkillDataaccess.getInstance()
				.getSkillsAsHashMapOfAvatarId(avatarId);
		for (Skill currentSkill : skillsOfAvatar.values())
		{
			currentSkill.setLvl(getLevelForSkillExp(currentSkill.getExp()));
		}
		return skillsOfAvatar;
	}
	
	public void updateSkillSpecializationWithIdOfAvatarId(final int avatarId,
			final Skill skill)
	{
		SkillDataaccess.getInstance()
				.updateSkillSpecializationWithIdOfAvatarId(avatarId, skill);
	}
	
	public void updateSkillWithIdOfAvatarId(final int avatarId,
			final Skill skill)
	{
		SkillDataaccess.getInstance().updateSkillWithIdOfAvatarId(avatarId,
				skill);
	}
}

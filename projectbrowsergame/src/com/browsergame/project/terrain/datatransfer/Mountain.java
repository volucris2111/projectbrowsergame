
package com.browsergame.project.terrain.datatransfer;

import java.util.LinkedList;
import java.util.Random;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.inventory.datatransfer.Resource;
import com.browsergame.project.skill.constants.SkillNameConstants;
import com.browsergame.project.skill.factory.SkillFactory;
import com.browsergame.project.terrain.constants.TerrainActionsEnum;
import com.browsergame.project.terrain.constants.TerrainNessesaryActionPoints;
import com.browsergame.project.terrain.constants.TerrainTypeConstants;

public class Mountain extends Terrain
{
	private static final long serialVersionUID = -423995701915692608L;
	
	public static boolean walkLogics(final int actionPoints)
	{
		
		return (actionPoints > TerrainNessesaryActionPoints.ActionPointsMountain
				.getWert());
	}
	
	private LinkedList<String> actions = new LinkedList<String>();
	
	public Mountain()
	{
		actions.add(TerrainActionsEnum.MINING.getAction());
		setType(TerrainTypeConstants.MOUNTAIN);
		setTerrainForDisplay("Berge");
		setImageUrl("../images/terrain/terrainmountain.gif");
	}
	
	@Override
	public String doAction(final String action, final Avatar avatar)
	{
		String message = "";
		Random random = new Random();
		if (action.equals(TerrainActionsEnum.MINING.getAction()))
		{
			int totalSkill = avatar.getWorkingSkills()
					.get(SkillNameConstants.stonecutting.toString()).getLvl();
			for (Avatar currentMaster : avatar.getMasters())
			{
				totalSkill = totalSkill
						+ currentMaster
								.getWorkingSkills()
								.get(SkillNameConstants.stonecutting.toString())
								.getLvl();
				currentMaster
						.getWorkingSkills()
						.get(SkillNameConstants.stonecutting.toString())
						.setExp(currentMaster
								.getWorkingSkills()
								.get(SkillNameConstants.stonecutting.toString())
								.getExp()
								+ (currentMaster
										.getWorkingSkills()
										.get(SkillNameConstants.stonecutting
												.toString()).isSpecialization() ? 4
										: 2));
				SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
						currentMaster.getAvatarId(),
						currentMaster.getWorkingSkills().get(
								SkillNameConstants.stonecutting.toString()));
				
			}
			int gatheredStone = random.nextInt(totalSkill + 2);
			avatar.getInventory()
					.getResources()
					.put(Resource.STONE,
							(avatar.getInventory().getResources()
									.get(Resource.STONE) != null ? avatar
									.getInventory().getResources()
									.get(Resource.STONE) : 0)
									+ gatheredStone);
			avatar.getWorkingSkills()
					.get(SkillNameConstants.stonecutting.toString())
					.setExp(avatar.getWorkingSkills()
							.get(SkillNameConstants.stonecutting.toString())
							.getExp()
							+ (avatar
									.getWorkingSkills()
									.get(SkillNameConstants.stonecutting
											.toString()).isSpecialization() ? 4
									: 2));
			SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
					avatar.getAvatarId(),
					avatar.getWorkingSkills().get(
							SkillNameConstants.stonecutting.toString()));
			message = "Du hast " + gatheredStone + " Stein erhalten!";
		}
		return message;
		
	}
	
	@Override
	public LinkedList<String> getActions()
	{
		return actions;
	}
	
	@Override
	public void setActions(final LinkedList<String> actions)
	{
		this.actions = actions;
	}
}

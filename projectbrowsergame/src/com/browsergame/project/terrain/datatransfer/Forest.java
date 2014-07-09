
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

public class Forest extends Terrain
{
	
	private static final long serialVersionUID = 2284272388720129204L;
	
	public static boolean walkLogics(final int actionPoints)
	{
		return (actionPoints > TerrainNessesaryActionPoints.ActionPointsMountain
				.getWert());
	}
	
	private LinkedList<String> actions = new LinkedList<String>();
	
	public Forest()
	{
		actions.add(TerrainActionsEnum.LUMBER.getAction());
		actions.add(TerrainActionsEnum.HUNTING.getAction());
		setType(TerrainTypeConstants.FOREST);
		setTerrainForDisplay("Wald");
		setImageUrl("../images/terrain/terrainforest.gif");
	}
	
	@Override
	public String doAction(final String action, final Avatar avatar)
	{
		String message = "";
		Random random = new Random();
		if (action.equals(TerrainActionsEnum.LUMBER.getAction()))
		{
			int totalSkill = avatar.getWorkingSkills()
					.get(SkillNameConstants.lumbering.toString()).getLvl();
			for (Avatar currentMaster : avatar.getMasters())
			{
				totalSkill = totalSkill
						+ currentMaster.getWorkingSkills()
								.get(SkillNameConstants.lumbering.toString())
								.getLvl();
				currentMaster
						.getWorkingSkills()
						.get(SkillNameConstants.lumbering.toString())
						.setExp(currentMaster.getWorkingSkills()
								.get(SkillNameConstants.lumbering.toString())
								.getExp()
								+ (currentMaster
										.getWorkingSkills()
										.get(SkillNameConstants.lumbering
												.toString()).isSpecialization() ? 4
										: 2));
				SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
						currentMaster.getAvatarId(),
						currentMaster.getWorkingSkills().get(
								SkillNameConstants.lumbering.toString()));
				
			}
			random.nextInt(totalSkill + 2);
			int gatheredWood = random.nextInt(avatar.getWorkingSkills()
					.get(SkillNameConstants.lumbering.toString()).getLvl() + 2);
			avatar.getInventory()
					.getResources()
					.put(Resource.WOOD,
							(avatar.getInventory().getResources()
									.get(Resource.WOOD) != null ? avatar
									.getInventory().getResources()
									.get(Resource.WOOD) : 0)
									+ gatheredWood);
			avatar.getWorkingSkills()
					.get(SkillNameConstants.lumbering.toString())
					.setExp(avatar.getWorkingSkills()
							.get(SkillNameConstants.lumbering.toString())
							.getExp()
							+ (avatar
									.getWorkingSkills()
									.get(SkillNameConstants.lumbering
											.toString()).isSpecialization() ? 4
									: 2));
			SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
					avatar.getAvatarId(),
					avatar.getWorkingSkills().get(
							SkillNameConstants.lumbering.toString()));
			message = "Du hast " + gatheredWood + " Holz erhalten!";
		}
		else if (action.equals(TerrainActionsEnum.HUNTING.getAction()))
		{
			int totalHuntingSkill = avatar.getWorkingSkills()
					.get(SkillNameConstants.hunting.toString()).getLvl();
			int totalFurrierySkill = avatar.getWorkingSkills()
					.get(SkillNameConstants.furriery.toString()).getLvl();
			for (Avatar currentMaster : avatar.getMasters())
			{
				totalHuntingSkill = totalHuntingSkill
						+ currentMaster.getWorkingSkills()
								.get(SkillNameConstants.hunting.toString())
								.getLvl();
				currentMaster
						.getWorkingSkills()
						.get(SkillNameConstants.hunting.toString())
						.setExp(currentMaster.getWorkingSkills()
								.get(SkillNameConstants.hunting.toString())
								.getExp()
								+ (currentMaster
										.getWorkingSkills()
										.get(SkillNameConstants.hunting
												.toString()).isSpecialization() ? 4
										: 2));
				SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
						currentMaster.getAvatarId(),
						currentMaster.getWorkingSkills().get(
								SkillNameConstants.hunting.toString()));
				totalFurrierySkill = totalFurrierySkill
						+ currentMaster.getWorkingSkills()
								.get(SkillNameConstants.furriery.toString())
								.getLvl();
				currentMaster
						.getWorkingSkills()
						.get(SkillNameConstants.furriery.toString())
						.setExp(currentMaster.getWorkingSkills()
								.get(SkillNameConstants.furriery.toString())
								.getExp()
								+ (currentMaster
										.getWorkingSkills()
										.get(SkillNameConstants.furriery
												.toString()).isSpecialization() ? 4
										: 2));
				SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
						currentMaster.getAvatarId(),
						currentMaster.getWorkingSkills().get(
								SkillNameConstants.furriery.toString()));
				
			}
			int gatheredMeat = random.nextInt(totalHuntingSkill + 2);
			int gatheredLeather = random.nextInt(totalFurrierySkill + 2);
			avatar.getInventory()
					.getResources()
					.put(Resource.MEAT,
							(avatar.getInventory().getResources()
									.get(Resource.MEAT) != null ? avatar
									.getInventory().getResources()
									.get(Resource.MEAT) : 0)
									+ gatheredMeat);
			avatar.getInventory()
					.getResources()
					.put(Resource.LEATHER,
							(avatar.getInventory().getResources()
									.get(Resource.LEATHER) != null ? avatar
									.getInventory().getResources()
									.get(Resource.LEATHER) : 0)
									+ gatheredLeather);
			avatar.getWorkingSkills()
					.get(SkillNameConstants.hunting.toString())
					.setExp(avatar.getWorkingSkills()
							.get(SkillNameConstants.hunting.toString())
							.getExp()
							+ (avatar.getWorkingSkills()
									.get(SkillNameConstants.hunting.toString())
									.isSpecialization() ? 4 : 2));
			avatar.getWorkingSkills()
					.get(SkillNameConstants.furriery.toString())
					.setExp(avatar.getWorkingSkills()
							.get(SkillNameConstants.furriery.toString())
							.getExp()
							+ (avatar
									.getWorkingSkills()
									.get(SkillNameConstants.furriery.toString())
									.isSpecialization() ? 4 : 2));
			SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
					avatar.getAvatarId(),
					avatar.getWorkingSkills().get(
							SkillNameConstants.hunting.toString()));
			SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
					avatar.getAvatarId(),
					avatar.getWorkingSkills().get(
							SkillNameConstants.furriery.toString()));
			message = "Du hast " + gatheredLeather + " Leder und "
					+ gatheredMeat + " Fleisch erhalten!";
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

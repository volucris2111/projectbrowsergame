
package com.browsergame.project.avatar.factory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import com.browsergame.project.attribute.constants.AttributeNameConstants;
import com.browsergame.project.attribute.factory.AttributeFactory;
import com.browsergame.project.avatar.dataaccess.AvatarDataaccess;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.datatransfer.AvatarClass;
import com.browsergame.project.avatar.datatransfer.Master;
import com.browsergame.project.building.factory.BuildingFactory;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.location.factory.LocationFactory;
import com.browsergame.project.skill.constants.SkillTypeConstants;
import com.browsergame.project.skill.datatransfer.Skill;
import com.browsergame.project.skill.factory.SkillFactory;
import com.browsergame.project.user.datatransfer.User;
import com.browsergame.project.user.factory.UserFactory;

public class AvatarFactory
{
	private static AvatarFactory INSTANCE = new AvatarFactory();
	
	public static AvatarFactory getInstance()
	{
		return AvatarFactory.INSTANCE;
	}
	
	private AvatarFactory()
	{
	}
	
	public void activateAllMasters()
	{
		for (Integer currentMasterId : getAllUnavailableMasterIds())
		{
			AvatarDataaccess.getInstance().activateMaster(currentMasterId);
		}
	}
	
	public void addAvatarToGroup(final int masterId, final int leaderAvatarId)
	{
		AvatarDataaccess.getInstance().addAvatarToGroup(masterId,
				leaderAvatarId);
	}
	
	public Avatar convertMasterToAvatar(final Master master)
	{
		Avatar avatar = new Avatar();
		avatar.setMale(master.isMale());
		avatar.setAvatarClassId(master.getAvatarClassId());
		avatar.setAvatarClass(getAvatarClassWithId(master.getAvatarClassId()));
		SkillFactory.getInstance().fillSkillOfAvatarClass(
				avatar.getAvatarClass());
		avatar.setName(master.getName());
		avatar.setType("master");
		avatar.setInventoryId(0);
		return avatar;
	}
	
	public void createAttributesAndSkillsOfAvatar(final Avatar avatar)
	{
		SkillFactory.getInstance().createAllSkillsForAvatarId(
				avatar.getAvatarId());
		for (Skill currentSkill : avatar.getAvatarClass().getSkills().values())
		{
			SkillFactory.getInstance()
					.updateSkillSpecializationWithIdOfAvatarId(
							avatar.getAvatarId(), currentSkill);
			SkillFactory.getInstance().updateSkillWithIdOfAvatarId(
					avatar.getAvatarId(), currentSkill);
		}
		AttributeFactory.getInstance().createAllAttributesForAvatarId(
				avatar.getAvatarId());
	}
	
	public String createAvatar(final User user, final Avatar avatar)
	{
		avatar.setInventoryId(InventoryFactory.getInstance()
				.createNewInventoryAndReturnId());
		String error = AvatarDataaccess.getInstance()
				.createAvatar(user, avatar);
		avatar.setUser(user);
		AvatarFactory.getInstance().createAttributesAndSkillsOfAvatar(avatar);
		return error;
	}
	
	public void createMaster(final Master master)
	{
		AvatarDataaccess.getInstance().createMaster(master);
	}
	
	public void createNewMastersForLocation(final int locationId)
	{
		for (int masterCounter = 0; masterCounter < 4; masterCounter++)
		{
			Master master = new Master();
			master.setCurrentLocationId(locationId);
			randomizeMaster(master);
			createMaster(master);
		}
	}
	
	public void deactivateAndRandomizeMaster(final Master master)
	{
		randomizeMaster(master);
		master.setAvailable(false);
		LinkedList<Integer> allLocationIds = LocationFactory.getInstance()
				.getAllLocationIds();
		master.setCurrentLocationId(allLocationIds.get(new Random()
				.nextInt(allLocationIds.size())));
		updateMaster(master);
	}
	
	public LinkedList<AvatarClass> getAllAvatarClassesAsLinkedList()
	{
		LinkedList<AvatarClass> availableAvatarClasses = AvatarDataaccess
				.getInstance().getAllAvatarClassesAsLinkedList();
		for (AvatarClass currentAvatarClass : availableAvatarClasses)
		{
			SkillFactory.getInstance().fillSkillOfAvatarClass(
					currentAvatarClass);
			for (Skill currentSkill : currentAvatarClass.getSkills().values())
			{
				currentSkill.setLvl(SkillFactory.getInstance()
						.getLevelForSkillExp(currentSkill.getExp()));
			}
		}
		return availableAvatarClasses;
	}
	
	public LinkedList<Master> getAllMasters()
	{
		return AvatarDataaccess.getInstance().getAllMasters();
	}
	
	public LinkedList<Integer> getAllUnavailableMasterIds()
	{
		return AvatarDataaccess.getInstance().getAllUnavailableMasterIds();
	}
	
	public Avatar getAvatarById(final int avatarId)
	{
		Avatar avatar = AvatarDataaccess.getInstance().getAvatarById(avatarId);
		fillAvatarObjects(avatar);
		return avatar;
	}
	
	public AvatarClass getAvatarClassWithId(final int avatarClassId)
	{
		return AvatarDataaccess.getInstance().getAvatarClassWithId(
				avatarClassId);
	}
	
	public LinkedList<Integer> getAvatarIdsOfGroupOfHeroIdAsLinkedList(
			final int avatarId)
	{
		return AvatarDataaccess.getInstance()
				.getAvatarIdsOfGroupOfHeroIdAsLinkedList(avatarId);
	}
	
	public Avatar getAvatarOfUserId(final int userId, final String type)
	{
		Avatar avatar = AvatarDataaccess.getInstance().getAvatarOfUserID(
				userId, type);
		fillAvatarObjects(avatar);
		return avatar;
	}
	
	public LinkedList<Avatar> getGroupOfAvatarIdAsLinkedList(final int avatarId)
	{
		LinkedList<Avatar> allGroupAvatarsOfAvatarId = new LinkedList<Avatar>();
		for (int currentAvatarId : getAvatarIdsOfGroupOfHeroIdAsLinkedList(avatarId))
		{
			Avatar avatar = getAvatarById(currentAvatarId);
			avatar.setLeaderAvatarId(avatarId);
			allGroupAvatarsOfAvatarId.add(avatar);
			
		}
		return allGroupAvatarsOfAvatarId;
	}
	
	public int getLevelForAvatarExp(final int avatarExp)
	{
		return AvatarDataaccess.getInstance().getLevelForAvatarExp(avatarExp);
	}
	
	public LinkedList<Avatar> getListOfAvatarsOnPosition(final int positionX,
			final int positionY)
	{
		LinkedList<Avatar> allAvatarsOfField = AvatarDataaccess.getInstance()
				.getListOfAvatarsOnPosition(positionX, positionY);
		for (Avatar currentAvatar : allAvatarsOfField)
		{
			fillAvatarObjectsForMap(currentAvatar);
		}
		return allAvatarsOfField;
	}
	
	public LinkedList<Master> getMastersOfLocationId(final int locationId)
	{
		LinkedList<Master> travelingMastersOfLocation = AvatarDataaccess
				.getInstance().getMastersOfLocationId(locationId);
		for (Master currentMaster : travelingMastersOfLocation)
		{
			fillMasterObjects(currentMaster);
		}
		return travelingMastersOfLocation;
	}
	
	public String getRandomAvatarName(final boolean male)
	{
		AvatarDataaccess avatarDataaccess = AvatarDataaccess.getInstance();
		return avatarDataaccess.getRandomFirstName(male) + " "
				+ avatarDataaccess.getRandomLastName();
	}
	
	public void moveAllMastersToRandomLocation()
	{
		LinkedList<Integer> allLocationIds = LocationFactory.getInstance()
				.getAllLocationIds();
		for (Master currentMaster : getAllMasters())
		{
			currentMaster.setCurrentLocationId(allLocationIds.get(new Random()
					.nextInt(allLocationIds.size())));
			updateMasterLocationId(currentMaster);
		}
	}
	
	public void moveAvatar(final Avatar avatar)
	{
		AvatarDataaccess.getInstance().moveAvatar(avatar);
	}
	
	public void randomizeMaster(final Master master)
	{
		master.setMale(new Random().nextBoolean());
		master.setName(getRandomAvatarName(master.isMale()));
		master.setAvatarClassId(new Random().nextInt(4) + 1);
		master.setCosts(new Random().nextInt(12000) + 6000);
	}
	
	public void setAvatarInside(final Avatar avatar)
	{
		AvatarDataaccess.getInstance().setAvatarInside(avatar);
	}
	
	public void updateAvatarCombatPoints(final Avatar avatar)
	{
		AvatarDataaccess.getInstance().updateAvatarCombatPoints(avatar);
	}
	
	public void updateExpOfAvatarWithId(final int avatarId, final int exp)
	{
		AvatarDataaccess.getInstance().updateExpOfAvatarWithId(avatarId, exp);
	}
	
	public void updateMaster(final Master master)
	{
		AvatarDataaccess.getInstance().updateMaster(master);
	}
	
	public void updateMasterLocationId(final Master master)
	{
		AvatarDataaccess.getInstance().updateMasterLocationId(master);
	}
	
	private void fillAvatarObjects(final Avatar avatar)
	{
		if (avatar.getAvatarId() != 0)
		{
			avatar.setUser(UserFactory.getInstance().getUserById(
					avatar.getUserId()));
			if (avatar.getType().equals("hero"))
			{
				BuildingFactory.getInstance().checkForFirstBuildingOfAvatarId(
						avatar.getAvatarId());
				avatar.setInventory(InventoryFactory.getInstance()
						.getInventorWithId(avatar.getInventoryId()));
			}
			avatar.setAvatarClass(getAvatarClassWithId(avatar
					.getAvatarClassId()));
			HashMap<String, Skill> allSkills = SkillFactory.getInstance()
					.getSkillsAsHashMapOfAvatarId(avatar.getAvatarId());
			for (Skill currentSkill : allSkills.values())
			{
				if (currentSkill.getType().equals(
						SkillTypeConstants.fighting.name()))
				{
					avatar.getFightingSkills().put(currentSkill.getName(),
							currentSkill);
				}
				if (currentSkill.getType().equals(
						SkillTypeConstants.working.name()))
				{
					avatar.getWorkingSkills().put(currentSkill.getName(),
							currentSkill);
				}
			}
			avatar.setAttributes(AttributeFactory.getInstance()
					.getAttributesAsHashMapOfAvatarId(avatar.getAvatarId()));
			avatar.setLevel(getLevelForAvatarExp(avatar.getExp()));
			avatar.setLifePointsMax(avatar.getAttributes()
					.get(AttributeNameConstants.constitution.name()).getLvl() * 3);
			avatar.setStaminaPointsMax(avatar.getAttributes()
					.get(AttributeNameConstants.constitution.name()).getLvl()
					* 3
					+ avatar.getAttributes()
							.get(AttributeNameConstants.willpower.name())
							.getLvl() * 2);
			avatar.setSpiritPointsMax(avatar.getAttributes()
					.get(AttributeNameConstants.intelligence.name()).getLvl()
					+ avatar.getAttributes()
							.get(AttributeNameConstants.knowledge.name())
							.getLvl() * 2);
			avatar.setInventoryCapacity((avatar.getAttributes()
					.get(AttributeNameConstants.strength.name()).getLvl() + (avatar
					.getAttributes().get(
							AttributeNameConstants.constitution.name())
					.getLvl()) / 2));
			if (avatar.getStaminaPointsMax() < avatar.getStaminaPointsCurrent())
			{
				avatar.setStaminaPointsCurrent(avatar.getStaminaPointsMax());
			}
			if (avatar.getLifePointsMax() < avatar.getLifePointsCurrent())
			{
				avatar.setLifePointsCurrent(avatar.getLifePointsMax());
			}
			if (avatar.getSpiritPointsMax() < avatar.getSpiritPointsCurrent())
			{
				avatar.setSpiritPointsCurrent(avatar.getSpiritPointsMax());
			}
		}
		
	}
	
	private void fillAvatarObjectsForMap(final Avatar avatar)
	{
		if (avatar.getAvatarId() != 0)
		{
			
			avatar.setAvatarClass(getAvatarClassWithId(avatar
					.getAvatarClassId()));
		}
	}
	
	private void fillMasterObjects(final Master currentMaster)
	{
		currentMaster.setAvatarClass(getAvatarClassWithId(currentMaster
				.getAvatarClassId()));
	}
}

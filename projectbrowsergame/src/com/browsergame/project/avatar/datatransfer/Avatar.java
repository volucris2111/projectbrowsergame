
package com.browsergame.project.avatar.datatransfer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.attribute.datatransfer.Attribute;
import com.browsergame.project.battle.datatransfer.BattleAction;
import com.browsergame.project.inventory.datatransfer.Inventory;
import com.browsergame.project.skill.datatransfer.Skill;
import com.browsergame.project.user.datatransfer.User;

public class Avatar implements Serializable
{
	private static final long serialVersionUID = -5829033164333713158L;
	
	private HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
	
	private AvatarClass avatarClass;
	
	private int avatarClassId;
	
	private int avatarId;
	
	private Equipment equipment = new Equipment();
	
	private int exp;
	
	private HashMap<String, Skill> fightingSkills = new HashMap<String, Skill>();
	
	private boolean firstBuilding; // transient
	
	private boolean inside;
	
	private String insideAction;
	
	private Inventory inventory;
	
	private int inventoryCapacity; // transient
	
	private int inventoryId;
	
	private int leaderAvatarId;
	
	private int level;
	
	private int lifePointsCurrent;
	
	private int lifePointsMax; // transient
	
	private boolean male;
	
	private LinkedList<Avatar> masters = new LinkedList<Avatar>();
	
	private String name;
	
	private int nextActionCounter;
	
	private int nextActionTimer;
	
	private int positionX;
	
	private int positionY;
	
	private int spiritPointsCurrent;
	
	private int spiritPointsMax; // transient
	
	private int staminaPointsCurrent;
	
	private int staminaPointsMax; // transient
	
	private Avatar target;
	
	private String type;
	
	private User user;
	
	private int userId;
	
	private HashMap<String, Skill> workingSkills = new HashMap<String, Skill>();
	
	public HashMap<String, Attribute> getAttributes()
	{
		return attributes;
	}
	
	public AvatarClass getAvatarClass()
	{
		return avatarClass;
	}
	
	public int getAvatarClassId()
	{
		return avatarClassId;
	}
	
	public int getAvatarId()
	{
		return avatarId;
	}
	
	public Equipment getEquipment()
	{
		return equipment;
	}
	
	public int getExp()
	{
		return exp;
	}
	
	public HashMap<String, Skill> getFightingSkills()
	{
		return fightingSkills;
	}
	
	public String getInsideAction()
	{
		return insideAction;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public int getInventoryCapacity()
	{
		return inventoryCapacity;
	}
	
	public int getInventoryId()
	{
		return inventoryId;
	}
	
	public int getLeaderAvatarId()
	{
		return leaderAvatarId;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getLifePointsCurrent()
	{
		return lifePointsCurrent;
	}
	
	public int getLifePointsMax()
	{
		return lifePointsMax;
	}
	
	public LinkedList<Avatar> getMasters()
	{
		return masters;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getNextActionCounter()
	{
		return nextActionCounter;
	}
	
	public int getNextActionTimer()
	{
		return nextActionTimer;
	}
	
	public int getPositionX()
	{
		return positionX;
	}
	
	public int getPositionY()
	{
		return positionY;
	}
	
	public int getSpiritPointsCurrent()
	{
		return spiritPointsCurrent;
	}
	
	public int getSpiritPointsMax()
	{
		return spiritPointsMax;
	}
	
	public int getStaminaPointsCurrent()
	{
		return staminaPointsCurrent;
	}
	
	public int getStaminaPointsMax()
	{
		return staminaPointsMax;
	}
	
	public Avatar getTarget()
	{
		return target;
	}
	
	public String getType()
	{
		return type;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public HashMap<String, Skill> getWorkingSkills()
	{
		return workingSkills;
	}
	
	public boolean isFirstBuilding()
	{
		return firstBuilding;
	}
	
	public boolean isInside()
	{
		return inside;
	}
	
	public boolean isMale()
	{
		return male;
	}
	
	public void setAttributes(final HashMap<String, Attribute> attributes)
	{
		this.attributes = attributes;
	}
	
	public void setAvatarClass(final AvatarClass avatarClass)
	{
		this.avatarClass = avatarClass;
	}
	
	public void setAvatarClassId(final int avatarClassId)
	{
		this.avatarClassId = avatarClassId;
	}
	
	public void setAvatarId(final int avatarId)
	{
		this.avatarId = avatarId;
	}
	
	public void setEquipment(final Equipment equipment)
	{
		this.equipment = equipment;
	}
	
	public void setExp(final int exp)
	{
		this.exp = exp;
	}
	
	public void setFightingSkills(final HashMap<String, Skill> fightingSkills)
	{
		this.fightingSkills = fightingSkills;
	}
	
	public void setFirstBuilding(final boolean firstBuilding)
	{
		this.firstBuilding = firstBuilding;
	}
	
	public void setInside(final boolean inside)
	{
		this.inside = inside;
	}
	
	public void setInsideAction(final String insideAction)
	{
		this.insideAction = insideAction;
	}
	
	public void setInventory(final Inventory inventory)
	{
		this.inventory = inventory;
	}
	
	public void setInventoryCapacity(final int inventoryCapacity)
	{
		this.inventoryCapacity = inventoryCapacity;
	}
	
	public void setInventoryId(final int inventoryId)
	{
		this.inventoryId = inventoryId;
	}
	
	public void setLeaderAvatarId(final int leaderAvatarId)
	{
		this.leaderAvatarId = leaderAvatarId;
	}
	
	public void setLevel(final int level)
	{
		this.level = level;
	}
	
	public void setLifePointsCurrent(final int lifePointsCurrent)
	{
		this.lifePointsCurrent = lifePointsCurrent;
	}
	
	public void setLifePointsMax(final int lifePointsMax)
	{
		this.lifePointsMax = lifePointsMax;
	}
	
	public void setMale(final boolean male)
	{
		this.male = male;
	}
	
	public void setMasters(final LinkedList<Avatar> masters)
	{
		this.masters = masters;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setNextActionCounter(final int nextActionCounter)
	{
		this.nextActionCounter = nextActionCounter;
	}
	
	public void setNextActionTimer(final int nextActionTimer)
	{
		this.nextActionTimer = nextActionTimer;
	}
	
	public void setNextBattleAction(final BattleAction nextBattleAction)
	{
	}
	
	public void setPositionX(final int positionX)
	{
		this.positionX = positionX;
	}
	
	public void setPositionY(final int positionY)
	{
		this.positionY = positionY;
	}
	
	public void setSpiritPointsCurrent(final int spiritPointsCurrent)
	{
		this.spiritPointsCurrent = spiritPointsCurrent;
	}
	
	public void setSpiritPointsMax(final int spiritPointsMax)
	{
		this.spiritPointsMax = spiritPointsMax;
	}
	
	public void setStaminaPointsCurrent(final int staminaPointsCurrent)
	{
		this.staminaPointsCurrent = staminaPointsCurrent;
	}
	
	public void setStaminaPointsMax(final int staminaPointsMax)
	{
		this.staminaPointsMax = staminaPointsMax;
	}
	
	public void setTarget(final Avatar target)
	{
		this.target = target;
	}
	
	public void setType(final String type)
	{
		this.type = type;
	}
	
	public void setUser(final User user)
	{
		this.user = user;
	}
	
	public void setUserId(final int userId)
	{
		this.userId = userId;
	}
	
	public void setWorkingSkills(final HashMap<String, Skill> workingSkills)
	{
		this.workingSkills = workingSkills;
	}
}

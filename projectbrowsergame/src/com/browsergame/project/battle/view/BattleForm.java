
package com.browsergame.project.battle.view;

import org.apache.struts.action.ActionForm;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.user.datatransfer.User;

public class BattleForm extends ActionForm
{
	
	private static final long serialVersionUID = 1523584796165015856L;
	
	private Avatar attackedAvatar = new Avatar();
	
	private User attackedUser = new User();
	
	private User loggedInUser = new User();
	
	private Avatar userLoggedInHero = new Avatar();
	
	public Avatar getAttackedAvatar()
	{
		return attackedAvatar;
	}
	
	public User getAttackedUser()
	{
		return attackedUser;
	}
	
	public User getLoggedInUser()
	{
		return loggedInUser;
	}
	
	public Avatar getUserLoggedInHero()
	{
		return userLoggedInHero;
	}
	
	public void setAttackedAvatar(final Avatar attackedAvatar)
	{
		this.attackedAvatar = attackedAvatar;
	}
	
	public void setAttackedUser(final User attackedUser)
	{
		this.attackedUser = attackedUser;
	}
	
	public void setLoggedInUser(final User loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}
	
	public void setUserLoggedInHero(final Avatar userLoggedInHero)
	{
		this.userLoggedInHero = userLoggedInHero;
	}
	
}

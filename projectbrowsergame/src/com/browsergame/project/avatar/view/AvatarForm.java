
package com.browsergame.project.avatar.view;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import com.browsergame.project.attribute.datatransfer.Attribute;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.datatransfer.AvatarClass;
import com.browsergame.project.user.datatransfer.User;

public class AvatarForm extends ActionForm
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6840343994224248625L;
	
	private int attributePointsToSpend;
	
	private LinkedList<AvatarClass> availableAvatarClasses = new LinkedList<AvatarClass>();
	
	private HashMap<String, Attribute> changedAttributes = new HashMap<String, Attribute>();
	
	private boolean comeFromPost;
	
	private Avatar currentAvatar;
	
	private String errorMessage;
	
	private User loggedInUser;
	
	private boolean ownerOfAvatar;
	
	private int selectedAvatarClassIndex;
	
	private int selectedAvatarId;
	
	private Avatar userLoggedInHero;
	
	public int getAttributePointsToSpend()
	{
		return attributePointsToSpend;
	}
	
	public LinkedList<AvatarClass> getAvailableAvatarClasses()
	{
		return availableAvatarClasses;
	}
	
	public HashMap<String, Attribute> getChangedAttributes()
	{
		return changedAttributes;
	}
	
	public Avatar getCurrentAvatar()
	{
		return currentAvatar;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public User getLoggedInUser()
	{
		return loggedInUser;
	}
	
	public int getSelectedAvatarClassIndex()
	{
		return selectedAvatarClassIndex;
	}
	
	public int getSelectedAvatarId()
	{
		return selectedAvatarId;
	}
	
	public Avatar getUserLoggedInHero()
	{
		return userLoggedInHero;
	}
	
	public boolean isComeFromPost()
	{
		return comeFromPost;
	}
	
	public boolean isOwnerOfAvatar()
	{
		return ownerOfAvatar;
	}
	
	public void setAttributePointsToSpend(final int attributePointsToSpend)
	{
		this.attributePointsToSpend = attributePointsToSpend;
	}
	
	public void setAvailableAvatarClasses(
			final LinkedList<AvatarClass> availableAvatarClasses)
	{
		this.availableAvatarClasses = availableAvatarClasses;
	}
	
	public void setChangedAttributes(
			final HashMap<String, Attribute> changedAttributes)
	{
		this.changedAttributes = changedAttributes;
	}
	
	public void setComeFromPost(final boolean comeFromPost)
	{
		this.comeFromPost = comeFromPost;
	}
	
	public void setCurrentAvatar(final Avatar currentAvatar)
	{
		this.currentAvatar = currentAvatar;
	}
	
	public void setErrorMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	
	public void setLoggedInUser(final User loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}
	
	public void setOwnerOfAvatar(final boolean ownerOfAvatar)
	{
		this.ownerOfAvatar = ownerOfAvatar;
	}
	
	public void setSelectedAvatarClassIndex(final int selectedAvatarClassIndex)
	{
		this.selectedAvatarClassIndex = selectedAvatarClassIndex;
	}
	
	public void setSelectedAvatarId(final int selectedAvatarId)
	{
		this.selectedAvatarId = selectedAvatarId;
	}
	
	public void setUserLoggedInHero(final Avatar userLoggedInHero)
	{
		this.userLoggedInHero = userLoggedInHero;
	}
	
}

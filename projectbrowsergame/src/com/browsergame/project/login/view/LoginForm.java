
package com.browsergame.project.login.view;

import org.apache.struts.action.ActionForm;

import com.browsergame.project.user.datatransfer.User;

public class LoginForm extends ActionForm
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4024545541066502290L;
	
	private User currentUser;
	
	private String message;
	
	public User getCurrentUser()
	{
		return currentUser;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setCurrentUser(final User currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public void setMessage(final String message)
	{
		this.message = message;
	}
}

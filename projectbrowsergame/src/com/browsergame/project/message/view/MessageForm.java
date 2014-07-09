package com.browsergame.project.message.view;

import com.browsergame.project.user.datatransfer.User;

public class MessageForm 
{
	private User DestinationUser;
	
	private String message;
	
	public User getDestinationUser()
	{
		return DestinationUser;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setDestinationUser(final User DestinationUser)
	{
		this.DestinationUser = DestinationUser;
	}
	
	public void setMessage(final String message)
	{
		this.message = message;
	}
}

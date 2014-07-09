
package com.browsergame.project.user.datatransfer;

import java.io.Serializable;

public class User implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2810130590478031653L;
	
	private String mailaddress;
	
	private String name;
	
	private String password;
	
	private String passwordRepeat;
	
	private int userId;
	
	public String getMailaddress()
	{
		return mailaddress;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getPasswordRepeat()
	{
		return passwordRepeat;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public void setMailaddress(final String mailaddress)
	{
		this.mailaddress = mailaddress;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	public void setPassword(final String password)
	{
		this.password = password;
	}
	
	public void setPasswordRepeat(final String passwordRepeat)
	{
		this.passwordRepeat = passwordRepeat;
	}
	
	public void setUserId(final int userId)
	{
		this.userId = userId;
	}
	
}

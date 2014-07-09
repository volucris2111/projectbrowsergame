package com.browsergame.project.user.factory;

import java.util.List;

import com.browsergame.project.user.dataaccess.UserDataaccess;
import com.browsergame.project.user.datatransfer.User;

public class UserFactory
{
	private static final UserFactory USERFACTORY = new UserFactory();

	public static UserFactory getInstance()
	{
		return UserFactory.USERFACTORY;
	}

	public User checkUserLogin(final User user)
	{
		return UserDataaccess.getInstance().checkUserLogin(user);
	}

	public List<User> getAllUser()
	{
		return UserDataaccess.getInstance().getAllUser();
	}

	public User getUserById(final int userId)
	{
		return UserDataaccess.getInstance().getUserWithId(userId);
	}

	public User getUserById(String name)
	{

		return this.getUserById(Integer.parseInt(name));
	}

	public String saveUser(final User user)
	{
		return UserDataaccess.getInstance().createUser(user);
	}
}

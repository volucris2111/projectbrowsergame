package com.browsergame.project.login.factory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.browsergame.project.user.datatransfer.User;
import com.browsergame.project.user.factory.UserFactory;

public class LoginFactory
{
	private static LoginFactory LOGINFACTORY = new LoginFactory();

	public static LoginFactory getInstance()
	{
		return LoginFactory.LOGINFACTORY;
	}

	private LoginFactory()
	{

	}

	public User checkUserLogin(final User user)
	{
		return UserFactory.getInstance().checkUserLogin(user);
	}

	public String convertPasswordToHash(final String password)
	{
		String passwordAsMd5 = null;
		if (password != null)
		{
			try
			{
				MessageDigest digest = MessageDigest.getInstance("MD5");
				digest.update(password.getBytes(), 0, password.length());
				passwordAsMd5 = new BigInteger(1, digest.digest()).toString(16);
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
		return passwordAsMd5;
	}

}

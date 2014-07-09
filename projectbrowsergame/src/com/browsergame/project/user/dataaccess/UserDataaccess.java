package com.browsergame.project.user.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.browsergame.project.user.datatransfer.User;

public class UserDataaccess
{
	private static final UserDataaccess USERDATAACCESS = new UserDataaccess();

	public static UserDataaccess getInstance()
	{
		return UserDataaccess.USERDATAACCESS;
	}

	private UserDataaccess()
	{
	}

	public User checkUserLogin(final User user)
	{
		User userFound = new User();
		List<User> userList = new LinkedList<User>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM user WHERE name ='"
				+ user.getName() + "' and password COLLATE utf8_bin ='"
				+ user.getPassword() + "';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = this.openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				User newUser = new User();
				newUser.setName(result.getString("name"));
				newUser.setMailaddress(result.getString("mailAddress"));
				newUser.setPassword(result.getString("password"));
				newUser.setUserId(result.getInt("userId"));
				userList.add(newUser);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		if (userList.size() == 1)
		{
			userFound = userList.get(0);
		}
		return userFound;
	}

	public String createUser(final User user)
	{
		String error = "";
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO user (name, password, mailAddress) VALUES ('");
		sqlStatementAsString.append(user.getName().trim());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(user.getPassword().trim());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(user.getMailaddress().trim());
		sqlStatementAsString.append("')");
		Statement sqlStatment;
		try
		{
			sqlConnection = this.openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.execute(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			error = sqlEx.getMessage();
		}
		return error;
	}

	public List<User> getAllUser()
	{
		List<User> allUser = new LinkedList<User>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM user");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = this.openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				User user = new User();
				user.setName(result.getString("name"));
				user.setUserId(result.getInt("userId"));
				user.setMailaddress(result.getString("mailaddress"));
				allUser.add(user);
			}

			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return allUser;
	}

	public User getUserWithId(final int userId)
	{
		User user = new User();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM user WHERE userId ='"
				+ userId + "';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = this.openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			result.next();
			user.setName(result.getString("name"));
			user.setUserId(result.getInt("userId"));
			user.setMailaddress(result.getString("mailaddress"));
			user.setPassword(result.getString("password"));
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return user;
	}

	public Connection openConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection sqlConnection;
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		sqlConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/projectbrowsergame", "root", "root");
		return sqlConnection;
	}
}

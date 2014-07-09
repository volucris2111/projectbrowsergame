package com.browsergame.project.message.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.browsergame.project.message.datatransfer.Message;
import com.browsergame.project.user.datatransfer.User;


public class MessageDataaccess 
{
	private static final MessageDataaccess INSTANCE = new MessageDataaccess();

	public static MessageDataaccess getInstance()
	{
		return MessageDataaccess.INSTANCE;
	}

	private MessageDataaccess()
	{
	}
	public LinkedList<Message> getAllRecievedMessagesOfUserId(final int userId)
	{
		LinkedList<Message> allMessages = new LinkedList<Message>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM message WHERE recipientUserId ='"
				+ userId + "';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = this.openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while(result.next())
			{
				Message message = new Message();
				message.setRecipientUserId(result.getInt("recipientUserId"));
				message.setSendUserId(result.getInt("sendUserId"));
				message.setSubject(result.getString("subject"));
				message.setMessage(result.getString("message"));
				allMessages.add(message);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return allMessages;
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

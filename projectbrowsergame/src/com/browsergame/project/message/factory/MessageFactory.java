package com.browsergame.project.message.factory;

import java.util.LinkedList;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.message.dataaccess.MessageDataaccess;
import com.browsergame.project.message.datatransfer.Message;
import com.browsergame.project.user.factory.UserFactory;

public class MessageFactory 
{
	private static MessageFactory INSTANCE = new MessageFactory();
	
	public static MessageFactory getInstance()
	{
		return MessageFactory.INSTANCE;
	}
	
	private MessageFactory()
	{
	}
	
	public LinkedList<Message> getAllRecievedMessagesOfUserId(final int userId)
	{
		LinkedList<Message> allMessages = MessageDataaccess.getInstance().getAllRecievedMessagesOfUserId(userId);
		for(Message currentMessage : allMessages)
		{
			currentMessage.setSendUserName(AvatarFactory.getInstance().getAvatarOfUserId(currentMessage.getSendUserId(), "hero").getName());
		}
	return allMessages;
	}
}

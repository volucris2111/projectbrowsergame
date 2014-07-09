
package com.browsergame.project.home.view;

import java.util.LinkedList;

import org.apache.struts.action.ActionForm;

import com.browsergame.project.message.datatransfer.Message;
import com.browsergame.project.user.datatransfer.User;
import com.mysql.jdbc.Messages;

public class HomeForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8854769803937654087L;
	
	private LinkedList<Message> messageList = new LinkedList<Message>();
	
	public LinkedList<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(LinkedList<Message> messageList) {
		this.messageList = messageList;
	}

	private User currentUser = new User();
	
	public User getCurrentUser()
	{
		return currentUser;
	}
	
	public void setCurrentUser(final User currentUser)
	{
		this.currentUser = currentUser;
	}
}

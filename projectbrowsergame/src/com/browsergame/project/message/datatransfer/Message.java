package com.browsergame.project.message.datatransfer;

public class Message 
{
private int sendUserId;
private int recipientUserId;
private String subject;
private String message;
private String sendUserName;

public String getSendUserName() {
	return sendUserName;
}
public void setSendUserName(String sendUserName) {
	this.sendUserName = sendUserName;
}
public int getSendUserId()
{
	return sendUserId;
}
public void setSendUserId(int sendUserId) {
	this.sendUserId = sendUserId;
}
public int getRecipientUserId() {
	return recipientUserId;
}
public void setRecipientUserId(int recipientUserId) {
	this.recipientUserId = recipientUserId;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}

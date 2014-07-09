package com.browsergame.project.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.home.view.HomeForm;
import com.browsergame.project.message.factory.MessageFactory;

public class MessageListPreAction extends BasicAction
{
@Override
public ActionForward childExecute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	HomeForm homeForm = (HomeForm) form;
	homeForm.setMessageList(MessageFactory.getInstance().getAllRecievedMessagesOfUserId(homeForm.getCurrentUser().getUserId()));
	return mapping.findForward("success");
}
}

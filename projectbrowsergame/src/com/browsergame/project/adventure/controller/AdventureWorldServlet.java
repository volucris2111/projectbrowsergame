
package com.browsergame.project.adventure.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdventureWorldServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public AdventureWorldServlet()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException
	{
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(
				"<table><tr><td><b>test</b></td></tr></table>");
	}
	
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException
	{
		// TODO Auto-generated method stub
		
	}
	
}
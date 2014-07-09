
package com.browsergame.project.terrain.constants;

public enum TerrainActionsEnum
{
	HUNTING("Jagen", "HUNTING"),
	
	LUMBER("Holz fällen", "LUMBER"),
	
	MINING("Bergbau", "MINING");
	
	private String action;
	
	private String buttonText;
	
	private TerrainActionsEnum(final String buttonText, final String action)
	{
		setButtonText(buttonText);
		setAction(action);
	}
	
	public String getAction()
	{
		return action;
	}
	
	public String getButtonText()
	{
		return buttonText;
	}
	
	public String getJspMethodCall()
	{
		return "ajaxRefreshMapWithAction('" + getAction() + "');";
	}
	
	public void setAction(final String action)
	{
		this.action = action;
	}
	
	public void setButtonText(final String buttonText)
	{
		this.buttonText = buttonText;
	}
	
}

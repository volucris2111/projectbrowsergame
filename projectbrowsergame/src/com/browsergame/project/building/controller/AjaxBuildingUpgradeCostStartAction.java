
package com.browsergame.project.building.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.buildingupgrade.datatransfer.BuildingUpgradeType;
import com.browsergame.project.buildingupgrade.factory.BuildingUpgradeFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.inventory.datatransfer.Resource;

public class AjaxBuildingUpgradeCostStartAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		if (request.getParameter("upgradeName") != null)
		{
			String upgradeName = request.getParameter("upgradeName");
			BuildingUpgradeType buildingUpgradeType = BuildingUpgradeFactory
					.getInstance()
					.getBuildingUpgradeTypeByNameAndLvl(upgradeName,
							(Integer.parseInt(request.getParameter("lvl")) + 1));
			StringBuilder htmlAsString = new StringBuilder();
			htmlAsString.append("<table>");
			htmlAsString.append("<tr><td valign=\"top\">");
			htmlAsString.append("<table>");
			htmlAsString.append("<tr><td>");
			htmlAsString
					.append("<font style=\"text-decoration:underline\">Kosten für nächste Stufe:</font>");
			htmlAsString.append("</td></tr>");
			htmlAsString.append("<tr><td>");
			htmlAsString.append("Münzen:");
			htmlAsString.append("</td>");
			htmlAsString.append("<td>");
			htmlAsString.append(buildingUpgradeType.getCoins());
			htmlAsString.append("</td></tr>");
			for (Entry<Resource, Integer> currentResource : buildingUpgradeType
					.getCosts().entrySet())
			{
				htmlAsString.append("<tr><td>");
				htmlAsString.append(currentResource.getKey()
						.getResourceForJsp());
				htmlAsString.append(":</td>");
				htmlAsString.append("<td>");
				htmlAsString.append(currentResource.getValue());
				htmlAsString.append("</td></tr>");
			}
			htmlAsString.append("</table>");
			htmlAsString.append("</td>");
			htmlAsString.append("<td valign=\"top\">");
			htmlAsString
					.append("<table width=\"250px\"><tr><td><font style=\"text-decoration:underline\">");
			htmlAsString.append(buildingUpgradeType.getStringForJSP());
			htmlAsString.append("</font>");
			htmlAsString.append("</td></tr>");
			htmlAsString.append("<tr><td>");
			htmlAsString.append(buildingUpgradeType.getDescription());
			htmlAsString.append("</td></tr>");
			htmlAsString.append("</table>");
			htmlAsString.append("</td></tr>");
			htmlAsString.append("</table>");
			response.getWriter().write(htmlAsString.toString());
			response.getWriter().flush();
		}
		else
		{
			response.getWriter().write(" ");
			response.getWriter().flush();
		}
		return null;
	}
}

package com.browsergame.project.location.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.browsergame.project.adventure.view.AdventureForm;
import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.core.controller.BasicAction;
import com.browsergame.project.inventory.factory.InventoryFactory;
import com.browsergame.project.location.datatransfer.Location;

public class LocationTavernPostAction extends BasicAction
{
	@Override
	public ActionForward childExecute(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception
	{
		AdventureForm adventureForm = (AdventureForm) form;
		Location location = adventureForm.getCurrentField().getLocation();
		adventureForm.setComeFromPost(false);
		adventureForm.setMessage("");
		if (request.getParameter("findMaster") != null
				|| request.getParameter("findMaster" + ".x") != null)
		{
			if (adventureForm.getUserLoggedInHero().getInventory().getCoins() >= 500)
			{
				location.getTavern().setShownMaster(
						location.getTavern()
								.getTravelingMasters()
								.get(new Random().nextInt(location.getTavern()
										.getTravelingMasters().size())));
				adventureForm
						.getUserLoggedInHero()
						.getInventory()
						.setCoins(
								adventureForm.getUserLoggedInHero()
										.getInventory().getCoins() - 500);
				InventoryFactory.getInstance().updateInventory(
						adventureForm.getUserLoggedInHero().getInventory());
			}
			else
			{
				adventureForm.setMessage("Nicht genügend Münzen!");
			}
			adventureForm.setComeFromPost(true);
		}
		if (request.getParameter("recrute") != null
				|| request.getParameter("recrute" + ".x") != null)
		{
			if (adventureForm.getUserLoggedInHero().getInventory().getCoins() >= location
					.getTavern().getShownMaster().getCosts())
			{
				AvatarFactory avatarFactory = AvatarFactory.getInstance();
				Avatar avatar = avatarFactory.convertMasterToAvatar(location
						.getTavern().getShownMaster());
				avatarFactory.createAvatar(adventureForm.getLoggedInUser(),
						avatar);
				avatarFactory.deactivateAndRandomizeMaster(location.getTavern()
						.getShownMaster());
				location.getTavern().setShownMaster(null);
				avatarFactory.addAvatarToGroup(avatar.getAvatarId(),
						adventureForm.getUserLoggedInHero().getAvatarId());
			}
			else
			{
				adventureForm.setMessage("Nicht genügend Münzen!");
			}
		}
		return mapping.findForward("success");
	}
}

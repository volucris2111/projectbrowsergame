
package com.browsergame.project.struggling.factory;

import java.util.LinkedList;

import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.struggling.dataaccess.StrugglingDataaccess;
import com.browsergame.project.struggling.datatransfer.Claimer;
import com.browsergame.project.struggling.datatransfer.Struggling;
import com.browsergame.project.struggling.datatransfer.StrugglingAvatar;

public class StrugglingFactory
{
	
	private static StrugglingFactory INSTANCE = new StrugglingFactory();
	
	public static StrugglingFactory getInstance()
	{
		return StrugglingFactory.INSTANCE;
	}
	
	private StrugglingFactory()
	{
	}
	
	public int createStrugglingAndReturnId(final int locationId)
	{
		return StrugglingDataaccess.getInstance().createStrugglingAndReturnId(
				locationId);
	}
	
	public void createStrugglingAndSetToLocation(final Location location)
	{
		location.setStrugglingId(createStrugglingAndReturnId(location
				.getLocationId()));
		location.setStruggling(getStrugglingById(location.getStrugglingId()));
	}
	
	public Struggling getStrugglingById(final int strugglingId)
	{
		StrugglingDataaccess strugglingDataaccess = StrugglingDataaccess
				.getInstance();
		AvatarFactory avatarFactory = AvatarFactory.getInstance();
		Struggling struggling = strugglingDataaccess
				.getStrugglingById(strugglingId);
		LinkedList<StrugglingAvatar> allStrugglingAvatars = strugglingDataaccess
				.getAllStrugglingAvatarsOfStrugglingWithId(strugglingId);
		for (StrugglingAvatar currentStrugglingAvatar : allStrugglingAvatars)
		{
			if (currentStrugglingAvatar.getSupportedAvatarId() == 0)
			{
				Claimer claimer = new Claimer();
				claimer.setClaimer(avatarFactory
						.getAvatarById(currentStrugglingAvatar.getAvatarId()));
				struggling.getClaimers().add(claimer);
			}
		}
		for (StrugglingAvatar currentStrugglingAvatar : allStrugglingAvatars)
		{
			if (currentStrugglingAvatar.getSupportedAvatarId() != 0)
			{
				for (Claimer currentClaimer : struggling.getClaimers())
				{
					if (currentClaimer.getClaimer().getAvatarId() == currentStrugglingAvatar
							.getAvatarId())
					{
						currentClaimer.getFollowers().add(
								avatarFactory
										.getAvatarById(currentStrugglingAvatar
												.getAvatarId()));
					}
				}
			}
		}
		return struggling;
	}
	
	public Struggling getStrugglingByLocationId(final int locationId)
	{
		return null;
	}
	
	public void setAllAvatarsOfStruggling(final Struggling struggling)
	{
		
	}
	
	public void setStrugglingAvatar(final int avatarId, final int strugglingId,
			final int supportedAvatarId)
	{
		StrugglingDataaccess.getInstance().setStrugglingAvatar(avatarId,
				strugglingId, supportedAvatarId);
	}
	
	public void setStrugglingIdToLocationId(final int locationId,
			final int struggelindId)
	{
		StrugglingDataaccess.getInstance().setStrugglingIdToLocationId(
				locationId, struggelindId);
	}
}

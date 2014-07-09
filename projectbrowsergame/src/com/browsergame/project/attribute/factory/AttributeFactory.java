
package com.browsergame.project.attribute.factory;

import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.attribute.dataaccess.AttributeDataaccess;
import com.browsergame.project.attribute.datatransfer.Attribute;

public class AttributeFactory
{
	private static AttributeFactory INSTANCE = new AttributeFactory();
	
	public static AttributeFactory getInstance()
	{
		return AttributeFactory.INSTANCE;
	}
	
	private AttributeFactory()
	{
	}
	
	public void createAllAttributesForAvatarId(final int avatarId)
	{
		AttributeDataaccess.getInstance().createAllAttributesForAvatarId(
				avatarId);
	}
	
	public LinkedList<Integer> getAllAttributeIdAsList()
	{
		return AttributeDataaccess.getInstance().getAllAttributeIdAsList();
	}
	
	public HashMap<String, Attribute> getAttributesAsHashMapOfAvatarId(
			final int avatarId)
	{
		return AttributeDataaccess.getInstance()
				.getAttributesAsHashMapOfAvatarId(avatarId);
	}
	
	public void updateAllAttributesAsHashMapOfAvatarId(final int avatarId,
			final HashMap<String, Attribute> attributes)
	{
		for (Attribute currentAttribute : attributes.values())
		{
			updateAttributeWithIdOfAvatarId(avatarId, currentAttribute);
		}
	}
	
	public void updateAttributeWithIdOfAvatarId(final int avatarId,
			final Attribute attribute)
	{
		AttributeDataaccess.getInstance().updateAttributeWithIdOfAvatarId(
				avatarId, attribute);
	}
}

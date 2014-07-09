
package com.browsergame.project.field.factory;

import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.factory.AvatarFactory;
import com.browsergame.project.building.factory.BuildingFactory;
import com.browsergame.project.field.dataaccess.FieldDataaccess;
import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.location.factory.LocationFactory;

public class FieldFactory
{
	private static FieldFactory INSTANCE = new FieldFactory();
	
	public static FieldFactory getInstance()
	{
		return INSTANCE;
	}
	
	private FieldFactory()
	{
		
	}
	
	public void addBuildingAndLocationIdToField(final Field field)
	{
		FieldDataaccess.getInstance().addBuildingAndLocationIdToField(field);
	}
	
	public void addInventoryIdToField(final Field field)
	{
		FieldDataaccess.getInstance().addInventoryIdToField(field);
	}
	
	public void changeTerrainOfFieldId(final int fieldId, final String terrain,
			final String backgroundImageUrl)
	{
		FieldDataaccess.getInstance().changeTerrainOfFieldId(fieldId, terrain,
				backgroundImageUrl);
	}
	
	public void createNewField(final String type, final int positionX,
			final int positionY, final int inventoryId,
			final String backgroundImageUrl)
	{
		FieldDataaccess.getInstance().createNewField(type, positionX,
				positionY, inventoryId, backgroundImageUrl);
	}
	
	public void fillAvatarsOfField(final Field field)
	{
		LinkedList<Avatar> allAvatars = AvatarFactory.getInstance()
				.getListOfAvatarsOnPosition(field.getPositionX(),
						field.getPositionY());
		field.setAllAvatarsOutside(new LinkedList<Avatar>());
		field.setAllAvatarsInside(new LinkedList<Avatar>());
		for (Avatar currentAvatar : allAvatars)
		{
			if (!currentAvatar.getName().equals("editor"))
			{
				if (currentAvatar.isInside())
				{
					field.getAllAvatarsInside().add(currentAvatar);
				}
				else
				{
					field.getAllAvatarsOutside().add(currentAvatar);
				}
			}
		}
	}
	
	public Field getFieldOfCoords(final int positionX, final int positionY)
	{
		Field field = FieldDataaccess.getInstance().getFieldOfCoords(positionX,
				positionY);
		fillAvatarsOfField(field);
		if (field.getBuildingId() != 0)
		{
			field.setBuilding(BuildingFactory.getInstance().getBuildingById(
					field.getBuildingId()));
			field.setFieldObjectImageUrl(field.getBuilding().getBuildingType()
					.getImageUrl());
		}
		else if (field.getLocationId() != 0)
		{
			field.setLocation(LocationFactory.getInstance().getLocationById(
					field.getLocationId()));
			field.setFieldObjectImageUrl(field.getLocation().getLocationType()
					.getImageUrl());
		}
		else if (!field.getAllAvatarsOutside().isEmpty())
		{
			field.setFieldObjectImageUrl("images/worldobjects/worldobjecthero.gif");
		}
		else
		{
			field.setFieldObjectImageUrl("images/worldobjects/worldobjectblank.gif");
		}
		return field;
	}
	
	public LinkedList<Field> getPieceOfMapWithCoordsAsCenter(
			final int positionX, final int positionY)
	{
		return FieldDataaccess.getInstance().getPieceOfMapWithCoordsAsCenter(
				positionX, positionY);
	}
	
	public void setBackgroundImageUrlOfFieldId(final String backgroundImageUrl,
			final int fieldId)
	{
		FieldDataaccess.getInstance().setBackgroundImageUrlOfFieldId(
				backgroundImageUrl, fieldId);
	}
	
	public void setFieldObjectImageUrlOfFieldId(
			final String fieldObjectImageUrl, final int fieldId)
	{
		FieldDataaccess.getInstance().setFieldObjectImageUrlOfFieldId(
				fieldObjectImageUrl, fieldId);
	}
}

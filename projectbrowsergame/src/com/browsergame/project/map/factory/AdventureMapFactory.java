
package com.browsergame.project.map.factory;

import java.util.LinkedList;

import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.map.datatransfer.AdventureMapRow;
import com.browsergame.project.terrain.datatransfer.TerrainEnum;

public class AdventureMapFactory
{
	
	private static AdventureMapFactory INSTANCE = new AdventureMapFactory();
	
	public static AdventureMapFactory getInstance()
	{
		return INSTANCE;
	}
	
	private AdventureMapFactory()
	{
	}
	
	public AdventureMapRow[] getPartOfMapAsListWithCoordinatsAsMiddle(
			final int positionX, final int positionY)
	{
		AdventureMapRow[] adventureMap = new AdventureMapRow[5];
		LinkedList<Field> allFieldsBetweenCoords = FieldFactory.getInstance()
				.getPieceOfMapWithCoordsAsCenter(positionX, positionY);
		for (int counterY = 0; counterY < 5; counterY++)
		{
			AdventureMapRow adventureMapRow = new AdventureMapRow();
			Field[] fieldsOfRow = new Field[5];
			for (int counterX = 0; counterX < 5; counterX++)
			{
				Field fieldForPosition = null;
				fieldForPosition = new Field();
				fieldForPosition.setTerrain(TerrainEnum.SEA.getTerrain());
				fieldForPosition.setFieldId(0);
				fieldForPosition.setPositionX(positionX + counterX - 2);
				fieldForPosition.setPositionY(positionY + counterY - 2);
				fieldForPosition
						.setBackgroundImageUrl("../images/terrain/terrainsea.gif");
				fieldForPosition
						.setFieldObjectImageUrl("../projectbrowsergame/images/worldobjects/worldobjectblank.gif");
				fieldsOfRow[counterX] = fieldForPosition;
			}
			adventureMapRow.setFieldsOfRow(fieldsOfRow);
			adventureMap[counterY] = adventureMapRow;
		}
		for (Field currentField : allFieldsBetweenCoords)
		{
			Field[] currentFieldsOfRow = adventureMap[currentField
					.getPositionY() - positionY + 2].getFieldsOfRow();
			currentFieldsOfRow[currentField.getPositionX() - positionX + 2] = currentField;
		}
		return adventureMap;
	}
}

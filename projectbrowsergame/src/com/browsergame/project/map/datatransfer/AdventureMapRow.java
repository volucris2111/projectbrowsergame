
package com.browsergame.project.map.datatransfer;

import java.io.Serializable;

import com.browsergame.project.field.datatransfer.Field;

public class AdventureMapRow implements Serializable
{
	private static final long serialVersionUID = 6815931449122146077L;
	
	private Field[] fieldsOfRow = new Field[5];
	
	public Field[] getFieldsOfRow()
	{
		return fieldsOfRow;
	}
	
	public void setFieldsOfRow(final Field[] fieldsOfRow)
	{
		this.fieldsOfRow = fieldsOfRow;
	}
}

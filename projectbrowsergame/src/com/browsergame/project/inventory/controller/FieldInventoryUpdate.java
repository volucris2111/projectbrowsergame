
package com.browsergame.project.inventory.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.browsergame.project.field.datatransfer.Field;
import com.browsergame.project.field.factory.FieldFactory;
import com.browsergame.project.inventory.factory.InventoryFactory;

public class FieldInventoryUpdate
{
	
	public static void main(final String[] args)
	{
		LinkedList<Field> allField = new LinkedList<Field>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM field;");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				Field field = new Field();
				field.setFieldId(result.getInt("fieldId"));
				field.setInventoryId(result.getInt("inventoryId"));
				allField.add(field);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		for (Field currentField : allField)
		{
			if (currentField.getInventoryId() == 0)
			{
				currentField.setInventoryId(InventoryFactory.getInstance()
						.createNewInventoryAndReturnId());
				FieldFactory.getInstance().addInventoryIdToField(currentField);
				System.out.println("Updatet Field with Id: "
						+ currentField.getFieldId());
			}
		}
	}
	
	public static Connection openConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection sqlConnection;
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		sqlConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/projectbrowsergame", "root", "root");
		return sqlConnection;
	}
}


package com.browsergame.project.inventory.factory;

import com.browsergame.project.inventory.dataaccess.InventoryDataaccess;
import com.browsergame.project.inventory.datatransfer.Inventory;

public class InventoryFactory
{
	
	private static InventoryFactory INSTANCE = new InventoryFactory();
	
	public static InventoryFactory getInstance()
	{
		return INSTANCE;
	}
	
	private InventoryFactory()
	{
		
	}
	
	public int createNewInventoryAndReturnId()
	{
		return InventoryDataaccess.getInstance()
				.createNewInventoryAndReturnId();
	}
	
	public Inventory getInventorWithId(final int inventoryId)
	{
		Inventory inventory = InventoryDataaccess.getInstance()
				.getInventoryWithId(inventoryId);
		return inventory;
	}
	
	public void updateInventory(final Inventory inventory)
	{
		InventoryDataaccess.getInstance().updateInventory(inventory);
	}
	
}


package com.browsergame.project.core.controller;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimerTask;

import com.browsergame.project.building.datatransfer.Building;
import com.browsergame.project.building.factory.BuildingFactory;

public class BuildingProductionTickTimer extends TimerTask
{
	@Override
	public void run()
	{
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTimeInMillis(System.currentTimeMillis());
		currentTime.set(Calendar.SECOND, 0);
		currentTime.set(Calendar.MILLISECOND, 0);
		System.out.println("ProductionTick start at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
		BuildingFactory buildingFactory = BuildingFactory.getInstance();
		LinkedList<Building> allBuildings = buildingFactory.getAllBuildings();
		for (Building currentBuilding : allBuildings)
		{
			if (currentBuilding.getFinishingProduction() != 0)
			{
				Calendar finishingProductionCalendar = Calendar.getInstance();
				finishingProductionCalendar.setTimeInMillis(currentBuilding
						.getFinishingProduction());
				if (currentTime.get(Calendar.MINUTE) == finishingProductionCalendar
						.get(Calendar.MINUTE))
				{
					buildingFactory.doTickForBuilding(currentBuilding);
					buildingFactory.updateBuildingLastHourReport(
							currentBuilding.getBuildingId(),
							currentBuilding.getLastHourReport());
				}
				if (finishingProductionCalendar.getTimeInMillis() <= currentTime
						.getTimeInMillis())
				{
					currentBuilding.setFinishingProduction(0);
					currentBuilding.setSelectedTools(0);
					currentBuilding.setSelectedWorkers(0);
					buildingFactory
							.updateBuildingsProductionAttributes(currentBuilding);
				}
			}
		}
		currentTime.setTimeInMillis(System.currentTimeMillis());
		System.out.println("ProductionTick finished at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
		
	}
}

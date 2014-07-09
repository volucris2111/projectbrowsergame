
package com.browsergame.project.core.controller;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimerTask;

import com.browsergame.project.location.datatransfer.Location;
import com.browsergame.project.location.factory.LocationFactory;

public class LocationHalfDayTickTimer extends TimerTask
{
	@Override
	public void run()
	{
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTimeInMillis(System.currentTimeMillis());
		currentTime.set(Calendar.SECOND, 0);
		currentTime.set(Calendar.MILLISECOND, 0);
		System.out.println("HalfDayTickTimer start at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
		LocationFactory locationFactory = LocationFactory.getInstance();
		LinkedList<Location> allLocations = locationFactory.getAllLocations();
		for (Location currentLocation : allLocations)
		{
			locationFactory.doHalfDayTick(currentLocation);
		}
		currentTime.setTimeInMillis(System.currentTimeMillis());
		System.out.println("HalfDayTickTimer finished at:"
				+ currentTime.get(Calendar.HOUR) + ":"
				+ currentTime.get(Calendar.MINUTE) + ":"
				+ currentTime.get(Calendar.SECOND));
	}
}

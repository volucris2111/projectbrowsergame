
package com.browsergame.project.core.controller;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class TimerServlet extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException
	{
		initQuaderHourTimer();
		initHalfDayTimer();
		initHourTimer();
	}
	
	private void initHalfDayTimer()
	{
		Calendar calendarNextHalfDay = Calendar.getInstance();
		calendarNextHalfDay.setTimeInMillis(System.currentTimeMillis());
		if (calendarNextHalfDay.get(Calendar.HOUR_OF_DAY) > 12)
		{
			calendarNextHalfDay.add(Calendar.DATE, 1);
			calendarNextHalfDay.set(Calendar.HOUR_OF_DAY, 0);
			
		}
		else
		{
			calendarNextHalfDay.set(Calendar.HOUR_OF_DAY, 12);
		}
		calendarNextHalfDay.set(Calendar.MINUTE, 0);
		calendarNextHalfDay.set(Calendar.SECOND, 0);
		calendarNextHalfDay.set(Calendar.MILLISECOND, 0);
		System.out.println("Set HalfDayTimer to:"
				+ calendarNextHalfDay.get(Calendar.HOUR_OF_DAY));
		Timer halfDayTimer = new Timer();
		halfDayTimer.schedule(new LocationHalfDayTickTimer(),
				calendarNextHalfDay.getTime(), 1000 * 60 * 60 * 12);
	}
	
	private void initHourTimer()
	{
		Timer hourTimer = new Timer();
		Calendar calendarNextHour = Calendar.getInstance();
		
		calendarNextHour.add(Calendar.HOUR, 1);
		calendarNextHour.set(Calendar.MINUTE, 0);
		calendarNextHour.set(Calendar.SECOND, 0);
		calendarNextHour.set(Calendar.MILLISECOND, 0);
		System.out.println("Set HourTimer to:"
				+ calendarNextHour.get(Calendar.HOUR_OF_DAY));
		hourTimer.schedule(new LocationHourTickTimer(),
				calendarNextHour.getTime(), 1000 * 60 * 60);
		
	}
	
	private void initQuaderHourTimer()
	{
		Timer quaderHourTimer = new Timer();
		Calendar calendarNextQuaderHour = Calendar.getInstance();
		calendarNextQuaderHour.setTimeInMillis(System.currentTimeMillis());
		if (calendarNextQuaderHour.get(Calendar.MINUTE) > 0)
		{
			if (calendarNextQuaderHour.get(Calendar.MINUTE) > 15)
			{
				if (calendarNextQuaderHour.get(Calendar.MINUTE) > 30)
				{
					if (calendarNextQuaderHour.get(Calendar.MINUTE) > 45)
					{
						calendarNextQuaderHour.add(Calendar.HOUR, 1);
						calendarNextQuaderHour.set(Calendar.MINUTE, 0);
					}
					else
					{
						calendarNextQuaderHour.set(Calendar.MINUTE, 45);
					}
				}
				else
				{
					calendarNextQuaderHour.set(Calendar.MINUTE, 30);
				}
			}
			else
			{
				calendarNextQuaderHour.set(Calendar.MINUTE, 15);
			}
		}
		calendarNextQuaderHour.set(Calendar.SECOND, 0);
		calendarNextQuaderHour.set(Calendar.MILLISECOND, 0);
		quaderHourTimer.schedule(new BuildingProductionTickTimer(),
				calendarNextQuaderHour.getTime(), 1000 * 60 * 15);
	}
	
}

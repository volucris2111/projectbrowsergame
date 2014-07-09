
package com.browsergame.project.battle.utilities;

import java.util.Comparator;

import com.browsergame.project.avatar.datatransfer.Avatar;

public class BattleComparator implements Comparator<Avatar>
{
	
	@Override
	public int compare(final Avatar o1, final Avatar o2)
	{
		int copmpareValue = 0;
		if (o1 != o2)
		{
			copmpareValue = o1.getNextActionCounter() > o2
					.getNextActionCounter() ? 1 : -1;
		}
		return copmpareValue;
	}
	
}

function iniActionButton()
{
	$("#terrainActionButton").click("ajaxRefreshMapWithAction('"+ $("#terrainActionHidden").val() + "')");
}
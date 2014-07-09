<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script src="../core/javascript/ajax.js" type="text/javascript"></script>
<script src="../core/javascript/javascript.js" type="text/javascript"></script>
<script src="javasscript/adventureWorld.js.jsp" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<html>
	<body onload="ajaxLoadMap()">
		<div id="adventureMap"></div>
		<html:hidden name="adventureForm" property="selectedAction" styleId="selctedActionId"/>
	</body>
</html>
<script>
function ajaxRefreshMapWithAction(action)
{
	var ajaxUrl = "/adventure/AjaxMapLoad.do?action=" + action;
	sendManualAjaxRequest(ajaxUrl, "", "", document.getElementById("adventureMap"));
}
function setSelectedAvatarId(avatarId)
{
	document.getElementById("selctedAvatarId").value = avatarId;
}
function setSelectedTerrain(selectedTerrain)
{
	document.getElementById("selectedTerrainId").value = selectedTerrain;
}
function ajaxLoadMap()
{
	$("#adventureMap").load("/projectbrowsergame/adventure/AjaxMapLoad.do");
}
function ajaxRefreshMapWithMove(positionX, positionY)
{
	$("#adventureMap").load("/projectbrowsergame/adventure/AjaxMapLoad.do?selectedPositionY=" + positionY + "&selectedPositionX=" + positionX);
}
</script>
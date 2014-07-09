<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>
	<script src="../core/javascript/ajax.js" type="text/javascript"></script>
	<script src="../core/javascript/javascript.js" type="text/javascript"></script>
	<script type="text/javascript">
	function refreshProductionDetails()
	{
		var workers = getIntOfField(document.getElementById("workersForProductionText"));
		var tools = getIntOfField(document.getElementById("toolsForProductionText"));
		var hours = getIntOfField(document.getElementById("durationOfProductionHoursText"));
		var days = getIntOfField(document.getElementById("durationOfProductionDayText"));
		var productionHoursTotal = days * 24 + hours;
		var maxWorkers = getIntOfField(document.getElementById("maxWorkers"));
		var maxTools = getIntOfField(document.getElementById("maxTools"));
		if(workers > maxWorkers)
		{
			workers = maxWorkers;
			document.getElementById("workersForProductionText").value = workers;
		}
		if(tools > maxTools)
		{
			tools = maxTools;
			document.getElementById("toolsForProductionText").value = tools;
		}
		
		setConsumptionField(productionHoursTotal, tools, workers);
		
		var fieldCounter = 0;
		var productFactor = 0;
		var field = document.getElementById("product"+fieldCounter);
		while (field != null)
		{
			var resource = field.value.replace(/[^a-zA-Z]/g, "");
			productFactor = parseInt(field.value.replace(resource + "-", ""));
			
			document.getElementById("gainAmountOf"+resource).innerHTML = (productFactor * productionHoursTotal * workers * (tools + 1));
			fieldCounter++;
			field = document.getElementById("product"+fieldCounter);
		}
	}
	
	function setConsumptionField(productionHoursTotal, tools, workers)
	{
		var consumptionFood = 0;
		var consumptionTools = 0;
		var consumptionClothes = 0;
		var consumptionCoins = 0;
		var enoughResources = true;
		consumptionCoins = productionHoursTotal * 5;
		consumptionFood = parseInt(productionHoursTotal/12);
		
		consumptionTools = 2 * parseInt(productionHoursTotal/12);
		if(productionHoursTotal%12 > 0)
			{
			consumptionTools = consumptionTools + 2;
			consumptionFood++;
			}
		consumptionTools = consumptionTools * tools;
		consumptionTools = consumptionTools + (workers * parseInt(productionHoursTotal/24));
		consumptionFood = consumptionFood * workers;
		consumptionClothes = parseInt(productionHoursTotal/24);
		
		if(productionHoursTotal%24 > 0)
		{
			consumptionTools = consumptionTools + workers;
			consumptionClothes++;
		}
		
		consumptionClothes = consumptionClothes * workers;
		
		document.getElementById("needAmountOfCoins").innerHTML = consumptionCoins;
		document.getElementById("needAmountOfFood").innerHTML = consumptionFood;
		document.getElementById("needAmountOfClothes").innerHTML = consumptionClothes;
		document.getElementById("needAmountOfTools").innerHTML = consumptionTools;
		var buildingResourceField = document.getElementById("buildingValueCoins");
		if (buildingResourceField == null || buildingResourceField.value < consumptionCoins)
		{
			enoughResources = false;
		}
		buildingResourceField = document.getElementById("buildingValueTOOL");
		if (buildingResourceField == null || buildingResourceField.value < consumptionTools)
		{
			enoughResources = false;
		}
		buildingResourceField = document.getElementById("buildingValueFOOD");
		if (buildingResourceField == null || buildingResourceField.value < consumptionFood)
		{
			enoughResources = false;
		}
		buildingResourceField = document.getElementById("buildingValueCLOTHES");
		if (buildingResourceField == null || buildingResourceField.value < consumptionClothes)
		{
			enoughResources = false;
		}
		if(enoughResources && productionHoursTotal > 0 && workers > 0 )
		{
			document.getElementById("startProductionButton").disabled = false;
			document.getElementById("productionAlert").innerHTML = "";
		}
		else if (productionHoursTotal <= 0)
		{
			document.getElementById("productionAlert").innerHTML = "Bitte Zeit auswählen!";
			document.getElementById("startProductionButton").disabled = true;
		}
		else if (workers <= 0)
		{
			document.getElementById("productionAlert").innerHTML = "Bitte Anzahl der<br/>Arbeiter festlegen!";
			document.getElementById("startProductionButton").disabled = true;
		}
		else if (!enoughResources)
		{
			document.getElementById("productionAlert").innerHTML = "Nicht genug Rohstoffe!";
			document.getElementById("startProductionButton").disabled = true;
		}
	}
	
	function checkAmountOfAvatar(field, index)
	{
		var amount = getIntOfField(field);
		var resource = field.id.replace("transferToBuilding", "");
		var maxAmount = parseInt(document.getElementById("avatarValue"+resource).value);
		if(amount > maxAmount)
		{
			amount = maxAmount;
		}
		field.value = amount;
		document.getElementById("transferToBuildingField"+index).value = amount;
		refreshTransferStatistics();
	}
	
	function getIntOfField(field)
	{
		var entry = field.value;
		entry = entry.replace(/\D*/gi, "");
		var amount = 0;
		if(isNaN(parseInt(entry)) == false)
		{
			amount = parseInt(entry);
		}
		return amount;
	}
	
	function refreshTransferStatistics()
	{
		var fieldCounter = 0;
		var amountTransferToBuilding = 0;
		var field = document.getElementById("transferToBuildingField"+fieldCounter);
		
		while (field != null)
		{
			amountTransferToBuilding = amountTransferToBuilding + parseInt(field.value);
			fieldCounter++;
			field = document.getElementById("transferToBuildingField"+fieldCounter);
		}
		fieldCounter = 0;
		field = document.getElementById("transferToAvatarField"+fieldCounter);
		while (field != null)
		{
			amountTransferToBuilding = amountTransferToBuilding - parseInt(field.value);
			fieldCounter++;
			field = document.getElementById("transferToAvatarField"+fieldCounter);
		}
		document.getElementById("transferSumBuilding").innerHTML = amountTransferToBuilding;
		var buildingMax = parseInt(document.getElementById("buildingMaxStorage").innerHTML);
		var buildingCurrent = parseInt(document.getElementById("buildingCurrent").innerHTML);
		var buildingFree = buildingMax - buildingCurrent - amountTransferToBuilding;
		document.getElementById("buildingFree").innerHTML = buildingFree;
		if(buildingFree < 0)
		{
			document.getElementById("transferButton").disabled = true;
			document.getElementById("notEnoughSpace").innerHTML = "Nicht genug Platz!";
		}
		else
		{
			document.getElementById("transferButton").disabled = false;
			document.getElementById("notEnoughSpace").innerHTML = "&nbsp;";
		}
			
	}
	
	function checkAmountOfBuilding(field, index)
	{
		var amount = getIntOfField(field);
		var resource = field.id.replace("transferToAvatar", "");
		var maxAmount = parseInt(document.getElementById("buildingValue"+resource).value);
		if(amount > maxAmount)
		{
			amount = maxAmount;
		}
		field.value = amount;
		document.getElementById("transferToAvatarField"+index).value = amount;
		refreshTransferStatistics();
	}
	
	</script>
	<body>
		
			<table>
				<tr>
					<td align="center" colspan="2">
						<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
					 	<logic:equal name="adventureForm" property="buildingOwner" value="true">
					 		<font style="text-decoration:underline"><bean:write name="adventureForm" property="currentField.building.buildingType.nameForJsp"/></font>
					 		&nbsp;
						 	<html:text name="adventureForm" property="currentField.building.name"/>
						 	<html:submit value="Umbenennen" property="rename"/>
					 	</logic:equal>
					 	<logic:equal name="adventureForm" property="buildingOwner" value="false">
					 		<font style="text-decoration:underline">
					 		<bean:write name="adventureForm" property="currentField.building.buildingType.nameForJsp"/>
					 		<logic:notEmpty name="adventureForm" property="currentField.building.name">
					 		&nbsp;"<bean:write name="adventureForm" property="currentField.building.name"/>"
					 		</logic:notEmpty>
					 		von <bean:write name="adventureForm" property="currentField.building.ownerAvatar.name"/>
					 		</font>
					 	</logic:equal>
					 	</html:form>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
							<html:submit value="Arbeiten" property="produce"/>
							<html:submit value="Karte" property="map"/>
							<html:submit value="Verlassen" property="leave"/>
						</html:form>
					</td>
				</tr>
				<logic:notEmpty name="adventureForm" property="buildingManualProductionMessage">
					<tr>
						<td align="center" colspan="2" style="color:red">
							<bean:write name="adventureForm" property="buildingManualProductionMessage" filter="none"/>
						</td>
					</tr>
				</logic:notEmpty>
				<tr>
					<td valign="top">
						<table>
							<tr>
								<td>
									<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
									<table>
										<tr>
											<td colspan="5">
												 <font style="text-decoration:underline">Lager:</font>
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
											<td>
												Gebäude
											</td>
											<td>
												Charakter
											</td>
											<td>
												Einlagern
											</td>
											<td align="right">
												<logic:equal name="adventureForm" property="buildingOwner" value="true">
													Entnehmen
												</logic:equal>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												Münzen
											</td>
											<td>
												<bean:write name="adventureForm" property="currentField.building.inventory.coins" format="##"/><html:hidden name="adventureForm" property="currentField.building.inventory.coins" styleId="buildingValueCoins"/>
											</td>
											<td>
												<bean:write name="adventureForm" property="userLoggedInHero.inventory.coins" format="##"/><html:hidden name="adventureForm" property="userLoggedInHero.inventory.coins" styleId="avatarValueCoins"/>
											</td>
											<td align="center">
												<html:text name="currentResource" property="transferToBuildingCoins" value="0" size="2" onchange="checkAmountOfAvatar(this)" styleId="transferToBuildingCoins"/>
											</td>
											<td align="center">
												<logic:equal name="adventureForm" property="buildingOwner" value="true">
													<html:text name="currentResource" property="transferToAvatarCoins" value="0" size="2" onchange="checkAmountOfBuilding(this)" styleId="transferToAvatarCoins"/>
												</logic:equal>
												&nbsp;
											</td>
										</tr>
										<logic:iterate  name="adventureForm" property="allResources" id="currentResource" indexId="resourceIndex">
											<tr>
												<td>
													<bean:write name="currentResource" property="resourceForJsp"/>:
												</td>
												<td>
													<bean:write name="adventureForm" property="currentField.building.inventory.valueOfResource(${currentResource.name})" format="##"/><html:hidden name="adventureForm" property="currentField.building.inventory.valueOfResource(${currentResource.name})" styleId="buildingValue${currentResource.name}"/>
												</td>
												<td>
													<bean:write name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.name})" format="##"/><html:hidden name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.name})" styleId="avatarValue${currentResource.name}"/>
												</td>
												<td align="center">
													<html:text name="currentResource" property="transferToBuilding${currentResource.name}" value="0" size="2" onchange="checkAmountOfAvatar(this, ${resourceIndex})" styleId="transferToBuilding${currentResource.name}"/><html:hidden value="0" property="" styleId="transferToBuildingField${resourceIndex}"/>
												</td>
												<td align="center">
													<logic:equal name="adventureForm" property="buildingOwner" value="true">
														<html:text name="currentResource" property="transferToAvatar${currentResource.name}" value="0" size="2" onchange="checkAmountOfBuilding(this, ${resourceIndex})" styleId="transferToAvatar${currentResource.name}"/><html:hidden value="0" property="" styleId="transferToAvatarField${resourceIndex}"/>
													</logic:equal>
													&nbsp;
												</td>
											</tr>	
										</logic:iterate>
										<tr>
											<td style="color:red" id="notEnoughSpace" colspan="3">
												&nbsp;
											</td>
											<logic:equal name="adventureForm" property="buildingOwner" value="true">
												<td colspan="2" align="center">
													<html:submit value="Lagern/Entnehmen" property="transfer" styleId="transferButton"/>
												</td>
											</logic:equal>
											<logic:equal name="adventureForm" property="buildingOwner" value="false">
												<td>
													<html:submit value="Lagern" property="transfer" styleId="transferButton"/>
												</td>
												<td>
													&nbsp;
												</td>
											</logic:equal>
										</tr>
									</table>
									</html:form>
								</td>
							</tr>
							
							<tr>
								<td>
									<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
									<table>
										<tr>
											<td>
												Lager
											</td>
											<td>
												Maximal
											</td>
											<td>
												Aktuell
											</td>
											<td>
												Transfer
											</td>
											<td>
												Frei
											</td>
										</tr>
										<tr>
											<td>
												Gebäude
											</td>
											<td id="buildingMaxStorage">
												<bean:write name="adventureForm" property="currentField.building.maxStorage" format="##"/>
											</td>
											<td id="buildingCurrent">
												<bean:write name="adventureForm" property="currentField.building.currentStorage" format="##"/>
											</td>
											<td id="transferSumBuilding">
												0
											</td>
											<td id="buildingFree">
											</td>
										</tr>
									</table>
									</html:form>
								</td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table>
							<tr>
								<td>
									<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
									<table>
										<tr>
											<td colspan="5">
												 <font style="text-decoration:underline">Aufenthaltsraum:</font>
											</td>
										</tr>
										<logic:empty name="adventureForm" property="currentField.allAvatarsInside">
											<tr>
												<td colspan="5">
													Es befinden sich keine anderen Charakteren hier!
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="adventureForm" property="currentField.allAvatarsInside">
											<tr>
												<td colspan="5">
													Folgende Charakteren befinden sich hier:
												</td>
											</tr>
											<logic:iterate name="adventureForm" property="currentField.allAvatarsInside" id="currentAvatar">
												<tr>
													<td colspan="5">
														<bean:write name="currentAvatar" property="name"/>
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
									</html:form>
								</td>
							</tr>
							<tr>
								<td>
									<hr/>
								</td>
							</tr>
							<tr>
								<td>
									<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
									<table>
										<tr>
											<td colspan="4">
												 <font style="text-decoration:underline">Ausbauten:</font>
											</td>
										</tr>
										<tr>
											<td>
												Lager:
											</td>
											<td>
												<bean:write name="adventureForm" property="currentField.building.buildingUpgrades.storage" format="##"/>&nbsp;<html:button property="" onmouseout="ajaxClearBuildingUpgradeDescription()" onmouseover="ajaxLoadBuildingUpgradeDescription('storage', ${adventureForm.currentField.building.buildingUpgrades.storage})" value="?"/><logic:equal name="adventureForm" property="buildingOwner" value="true"><html:submit value="+" property="upgradeStorage"/></logic:equal>
											</td>
											<td>
												Versteck:
											</td>
											<td>
												<bean:write name="adventureForm" property="currentField.building.buildingUpgrades.hideout" format="##"/>&nbsp;<html:button property="" onmouseout="ajaxClearBuildingUpgradeDescription()" onmouseover="ajaxLoadBuildingUpgradeDescription('hideout', ${adventureForm.currentField.building.buildingUpgrades.hideout})" value="?"/><logic:equal name="adventureForm" property="buildingOwner" value="true"><html:submit value="+" property="upgradeHideout"/></logic:equal>
											</td>
										</tr>
										<tr>
											<td>
												Arbeiter:
											</td>
											<td>
												<bean:write name="adventureForm" property="currentField.building.buildingUpgrades.worker" format="##"/>&nbsp;<html:button property="" onmouseout="ajaxClearBuildingUpgradeDescription()" onmouseover="ajaxLoadBuildingUpgradeDescription('worker', ${adventureForm.currentField.building.buildingUpgrades.worker})" value="?"/><logic:equal name="adventureForm" property="buildingOwner" value="true"><html:submit value="+" property="upgradeWorker"/></logic:equal><html:hidden name="adventureForm" property="currentField.building.buildingUpgrades.worker" styleId="maxWorkers"/>
											</td>
											<td>
												Werkzeug:
											</td>
											<td>
												<bean:write name="adventureForm" property="currentField.building.buildingUpgrades.tools" format="##"/>&nbsp;<html:button property="" onmouseout="ajaxClearBuildingUpgradeDescription()" onmouseover="ajaxLoadBuildingUpgradeDescription('tools', ${adventureForm.currentField.building.buildingUpgrades.tools})" value="?"/><logic:equal name="adventureForm" property="buildingOwner" value="true"><html:submit value="+" property="upgradeTools"/></logic:equal><html:hidden name="adventureForm" property="currentField.building.buildingUpgrades.tools" styleId="maxTools"/>
											</td>
										</tr>
										<tr>
											<td>
												Wachen:
											</td>
											<td>
												<bean:write name="adventureForm" property="currentField.building.buildingUpgrades.guards" format="##"/>&nbsp;<html:button property="" onmouseout="ajaxClearBuildingUpgradeDescription()" onmouseover="ajaxLoadBuildingUpgradeDescription('guards', ${adventureForm.currentField.building.buildingUpgrades.guards})" value="?"/><logic:equal name="adventureForm" property="buildingOwner" value="true"><html:submit value="+" property="upgradeGuards"/></logic:equal>
											</td>
											<td colspan="2">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td colspan="4" style="color:red">
												<bean:write name="adventureForm" property="buildingUpgradeMessage" filter="none"/>
											</td>
										</tr>
									</table>
									</html:form>
								</td>
							</tr>
							<tr>
								<td>
									<hr/>
								</td>
							</tr>
							<tr>
								<td>
									<logic:equal name="adventureForm" property="buildingOwner" value="true">
										<html:form action="BuildingDetailsPostAction.do" enctype="multipart/form-data">
										<logic:equal name="adventureForm" property="currentField.building.finishingProduction" value="0">
											<table>
												<tr>
													<td colspan="4">
														<font style="text-decoration:underline">Produktionsverwaltung:</font>
													</td>
												</tr>
												<tr>
													<td>
														Anz. Arbeiter: 
													</td>
													<td>
														<html:text name="adventureForm" property="currentField.building.selectedWorkers" value="0" size="2" onchange="refreshProductionDetails()" styleId="workersForProductionText"/>
													</td>
													<td>
														Werkzeugqualität:
													</td>
													<td >
														<html:text name="adventureForm" property="currentField.building.selectedTools" value="0" size="2" onchange="refreshProductionDetails()" styleId="toolsForProductionText"/>
													</td>
												</tr>
												<tr>
													<td>
														Std:
													</td>
													<td>
														<html:text name="adventureForm" property="selectedProductionHours" value="0" size="2" onchange="refreshProductionDetails()" styleId="durationOfProductionHoursText"/>
													</td>
													<td>
														Tag(e):
													</td>
													<td>
														<html:text name="adventureForm" property="selectedProductionDays" value="0" size="2" onchange="refreshProductionDetails()" styleId="durationOfProductionDayText"/>
													</td>
												</tr>
												<tr>
													<td>
														
													</td>
												</tr>
												<tr>
													<td colspan="2" valign="top">
														<table>
															<tr>
																<td colspan="2">
																	Verbrauch:
																</td>
															</tr>
															<tr>
																<td>
																	Münzen:
																</td>
																<td id="needAmountOfCoins">
																	0
																</td>
															</tr>
															<tr>
																<td>
																	Nahrung:
																</td>
																<td id="needAmountOfFood">
																	0
																</td>
															</tr>
															<tr>
																<td>
																	Kleidung:
																</td>
																<td id="needAmountOfClothes">
																	0
																</td>	
															</tr>
															<tr>
																<td>
																	Werkzeug:
																</td>
																<td id="needAmountOfTools">
																	0
																</td>
															</tr>
														</table>
													</td>
													<td colspan="2" valign="top">
														<table>
															<tr>
																<td colspan="2">
																	Ertrag:
																</td>
															</tr>
															<logic:iterate name="adventureForm" property="currentField.building.buildingType.buildingProducts" id="currentProductResource" indexId="currentProductresourceId">
															<tr>
																<td>
																	<html:hidden property="" value="${currentProductResource.resource}-${currentProductResource.factor}" styleId="product${currentProductresourceId}"/><bean:write name="currentProductResource" property="resource.resourceForJsp"/>:
																</td>
																<td id="gainAmountOf${currentProductResource.resource}">
																	0
																</td>
															</tr>
															</logic:iterate>
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="2" id="productionAlert">
														&nbsp;
													</td>
													<td colspan="2" align="right">
														<html:submit disabled="true" value="Produktion starten" property="startProduction" styleId="startProductionButton"/>
													</td>
												</tr>
											</table>
										</logic:equal>
										<logic:notEqual name="adventureForm" property="currentField.building.finishingProduction" value="0">
											<table>
												<tr>
													<td>
														<font style="text-decoration:underline">Produktionsverwaltung:</font>
													</td>
												</tr>
												<tr>
													<td>
														Das Gebäude produziert!<br/>
														Ende: <bean:write name="adventureForm" property="currentField.building.finishingProductionAsDate" format="dd.MM.yy' um 'HH:mm' Uhr'"/>.<br/>
														<html:submit value="Produktion abbrechen" property="cancelProduction"/>
													</td>
												</tr>
											</table>
										</logic:notEqual>
										</html:form>
									</logic:equal>
									<logic:equal name="adventureForm" property="buildingOwner" value="false">
											<table>
												<tr>
													<td>
														Produktion:
													</td>
												</tr>
												<tr>
													<td>
														<logic:equal name="adventureForm" property="currentField.building.finishingProduction" value="0">
															Das Gebäude produziert nicht!
														</logic:equal>
														<logic:notEqual name="adventureForm" property="currentField.building.finishingProduction" value="0">
															Das Gebäude produziert.
														</logic:notEqual>
													</td>
												</tr>
											</table>
									</logic:equal>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<div id="upgradeDescription">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr/>
					</td>
				</tr>
			</table>
	</body>
</html>
<script>
function ajaxLoadBuildingUpgradeDescription(name, lvl)
{
	var ajaxUrl = "/adventure/AjaxBuildingUpgradeCostStartAction.do?upgradeName=" + name + "&lvl=" + lvl;
	sendManualAjaxRequest(ajaxUrl, "", "", document.getElementById("upgradeDescription"));
}
function ajaxClearBuildingUpgradeDescription()
{
	var ajaxUrl = "/adventure/AjaxBuildingUpgradeCostStartAction.do";
	sendManualAjaxRequest(ajaxUrl, "", "", document.getElementById("upgradeDescription"));
}
refreshTransferStatistics();
</script>
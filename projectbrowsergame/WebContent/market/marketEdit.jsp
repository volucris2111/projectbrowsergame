<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.browsergame.project.inventory.datatransfer.Resource" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<head>
		<script type="text/javascript">
			function changeValue(field)
			{
				var entry = field.value;
				
				entry = entry.replace(/\D*/gi, "");
				var amount = 0;
				
				if(isNaN(parseInt(entry)) == false)
				{
					amount = parseInt(entry);
				}
				field.value = amount;
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
	</head>
	<html:form action="MarketEditPostAction.do">
		<table>
				<tr>
					<td colspan="6">
						 Marktplatzeinstellungen:
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						Minimum
					</td>
					<td>
						Lager
					</td>
					<td>
						Maximum
					</td>
					<td>
						E.Preis
					</td>
					<td>
						V.Preis
					</td>
					<td>
						Charakter
					</td>
					<td>
						Einlagern
					</td>
					<td>
						Entnehmen
					</td>
				</tr>
				<tr>
					<td>
						Münzen
					</td>
					<td>
						&nbsp;
					</td>
					<td align="center">
						<bean:write name="adventureForm" property="currentMarket.inventory.coins" format="##"/>
						<bean:define name="adventureForm" property="currentMarket.inventory.coins" id="currentCoinsMarket"/>
						<html:hidden name="adventureForm" property="" value="${currentCoinsMarket}" styleId="buildingValueCoins"/>
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<bean:write name="adventureForm" property="userLoggedInHero.inventory.coins" format="##"/>
						<bean:define name="adventureForm" property="userLoggedInHero.inventory.coins" id="currentCoinsAvatar"/>
						<html:hidden name="adventureForm" property="" value="${currentCoinsAvatar}" styleId="avatarValueCoins"/>
					</td>
					<td align="center">
						<html:text name="currentResource" property="transferToBuildingCoins" value="0" size="2" onchange="checkAmountOfAvatar(this)" styleId="transferToBuildingCoins"/>
					</td>
					<td align="center">
						<html:text name="currentResource" property="transferToAvatarCoins" value="0" size="2" onchange="checkAmountOfBuilding(this)" styleId="transferToAvatarCoins"/>
					</td>
				</tr>
				<logic:iterate name="adventureForm" property="currentMarket.marketResources" id="currentResource">
				<tr>
					<td>
						<bean:write name="currentResource" property="key.resourceForJsp"/>:
					</td>
					<td align="center">
						<html:text name="currentResource" property="minAmount${currentResource.key}" size="2" onchange="changeValue(this)" value="${currentResource.value.minAmount}"/>
					</td>
					<td align="center">
						<bean:write name="adventureForm" property="currentMarket.inventory.valueOfResource(${currentResource.key})" format="##"/> 
					</td>
					<td align="center">
						<html:text name="currentResource" property="maxAmount${currentResource.key}" size="2" onchange="changeValue(this)" value="${currentResource.value.maxAmount}"/>
					</td>
					<td align="center">
						<html:text name="currentResource" property="priceBuy${currentResource.key}" size="2" onchange="changeValue(this)" value="${currentResource.value.priceBuy}"/>
					</td>
					<td align="center">
						<html:text name="currentResource" property="priceSell${currentResource.key}" size="2" onchange="changeValue(this)" value="${currentResource.value.priceSell}"/>
					</td>
					<td>
						<bean:write name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.key})" format="##"/><html:hidden name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.key})" styleId="avatarValue${currentResource.key}"/>
					</td>
					<td align="center">
						<html:text name="currentResource" property="transferToBuilding${currentResource.key}" value="0" size="2" onchange="checkAmountOfAvatar(this, ${resourceIndex})" styleId="transferToBuilding${currentResource.key}"/><html:hidden value="0" property="" styleId="transferToBuildingField${resourceIndex}"/>
					</td>
					<td align="center">
						<html:text name="currentResource" property="transferToAvatar${currentResource.key}" value="0" size="2" onchange="checkAmountOfBuilding(this, ${resourceIndex})" styleId="transferToAvatar${currentResource.key}"/><html:hidden value="0" property="" styleId="transferToAvatarField${resourceIndex}"/>
					</td>
				</tr>
				</logic:iterate>
				<tr>
					<td colspan="3" align="left">	
						<html:submit value="Zurück" property="back"/>
					</td>
					<td colspan="3" align="right">
						<html:submit value="Speichern" property="save"/>
					</td>
				</tr>
			</table>
	</html:form>
</html>
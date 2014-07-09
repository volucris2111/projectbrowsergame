<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.browsergame.project.inventory.datatransfer.Resource" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<head>
		<script type="text/javascript">
		function checkSellEntry(field)
		{
			var entry = field.value;
			
			entry = entry.replace(/\D*/gi, "");
			var amount = 0;
			
			if(isNaN(parseInt(entry)) == false)
			{
				amount = parseInt(entry);
			}
			var resource = field.id.replace("Sell", "");
			var maxAmount = parseInt(document.getElementById("Max"+resource).value);
			var currentAmount = parseInt(document.getElementById("Current"+resource).value);
			var inventoryValue = parseInt(document.getElementById("inventoryValue"+resource).value);
			
			
			if(amount > (maxAmount - currentAmount))
			{
				amount = (maxAmount - currentAmount);
			}
			if(amount > inventoryValue)
			{
				amount = inventoryValue;
			}
			if(amount < 0)
			{
				amount = 0;
			}
			field.value = amount;
			setWorthOfResource(resource);
		}
		
		function setWorthOfResource(resource)
		{
			var sellPrice = parseInt(document.getElementById("sellPrice"+resource).value);
			var buyAmount = parseInt(document.getElementById(resource+"Buy").value);
			var buyPrice = parseInt(document.getElementById("buyPrice"+resource).value);
			var sellAmount = parseInt(document.getElementById(resource+"Sell").value);
			var worthSumMarket = parseInt(document.getElementById("worthSumMarket").innerHTML);
			var worthSumAvatar = parseInt(document.getElementById("worthSumAvatar").innerHTML);
			var currentWorth;
			if(isNaN(parseInt(document.getElementById("worth"+resource).innerHTML)) == false)
			{
				currentWorth = parseInt(document.getElementById("worth"+resource).innerHTML);
			}
			else
			{
				currentWorth = 0;
			}
			var worth = (sellAmount * sellPrice) - (buyAmount * buyPrice);
			
			worthSumAvatar = worthSumAvatar + worth + (currentWorth * -1);
			worthSumMarket = worthSumMarket - worth + currentWorth;
			
			document.getElementById("worth"+resource).innerHTML = '' + worth;
			document.getElementById("worthSumAvatar").innerHTML = '' + worthSumAvatar;
			document.getElementById("worthSumMarket").innerHTML = '' + worthSumMarket;
			if(worthSumAvatar < 0 || worthSumMarket < 0)
			{
				document.getElementById("tradeButton").disabled = true;
			}
			else
			{
				document.getElementById("tradeButton").disabled = false;
			}
			
		}
		function checkBuyEntry(field)
		{
			var entry = field.value;
			
			entry = entry.replace(/\D*/gi, "");
			var amount = 0;
			if(isNaN(parseInt(entry)) == false)
			{
				amount = parseInt(entry);
			}
			var resource = field.id.replace("Buy", "");
			var minAmount = parseInt(document.getElementById("Min"+resource).value);
			var currentAmount = parseInt(document.getElementById("Current"+resource).value);
			if ((currentAmount - minAmount) <= 0)
			{
				amount = 0;
			}
			else if(amount > (currentAmount - minAmount))
			{
				amount = (currentAmount - minAmount);
			}
			field.value = amount;
			setWorthOfResource(resource);
		}
		</script>
	</head>
	<html:form action="MarketPostAction.do">
		<table>
			<tr>
				<td colspan="2">
					 Marktplatz:
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td colspan="9">
								&nbsp;
							</td>
							<td colspan="2" align="center">
								Vermögen
							</td>
						</tr>
						<tr>
							<td colspan="9">
								&nbsp;
							</td>
							<td>
								Eigenes
							</td>
							<td>
								Marktstand
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
								Inventar
							</td>
							<td>
								Kaufen
							</td>
							<td>
								Verkaufen
							</td>
							<td align="center">
								<bean:write name="adventureForm" property="userLoggedInHero.inventory.coins" format="##"/>
							</td>
							<td align="center">
								<bean:write name="adventureForm" property="currentMarket.inventory.coins" format="##"/>
							</td>
						</tr>
						<logic:iterate name="adventureForm" property="currentMarket.marketResources" id="currentResource">
						<tr>
							<td>
								<bean:write name="currentResource" property="key.resourceForJsp"/>:
							</td>
							<td align="center">
								<bean:write name="currentResource" property="value.minAmount" format="##"/> <html:hidden value="${currentResource.value.minAmount}" styleId="Min${currentResource.key}" property=""/>
							</td>
							<td align="center">
								<bean:write name="adventureForm" property="currentMarket.inventory.valueOfResource(${currentResource.key})" format="##"/> <html:hidden name="adventureForm" property="currentMarket.inventory.valueOfResource(${currentResource.key})" styleId="Current${currentResource.key}"/>
							</td>
							<td align="center">
								<bean:write name="currentResource" property="value.maxAmount" format="##"/> <html:hidden value="${currentResource.value.maxAmount}" styleId="Max${currentResource.key}" property=""/>
							</td>
							<td align="center">
								<bean:write name="currentResource" property="value.priceBuy" format="##"/> <html:hidden name="currentResource" property="value.priceBuy" styleId="sellPrice${currentResource.key}"/>
							</td>
							<td align="center">
								<bean:write name="currentResource" property="value.priceSell" format="##"/> <html:hidden name="currentResource" property="value.priceSell" styleId="buyPrice${currentResource.key}"/>
							</td>
							<td align="center">
								<bean:write name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.key})" format="##"/><html:hidden name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.key})" styleId="inventoryValue${currentResource.key}"/>
							<td align="center">
								<html:text name="currentResource" property="${currentResource.key}Buy" value="0" size="2" onchange="checkBuyEntry(this)" styleId="${currentResource.key}Buy"/>
							</td>
							<td align="right">
								<html:text name="currentResource" property="${currentResource.key}Sell" value="0" size="2" onchange="checkSellEntry(this)" styleId="${currentResource.key}Sell"/>
							</td>
							<td align="center" colspan="2">
								<div id="worth${currentResource.key}"></div>
							</td>
						</tr>
						</logic:iterate>
						<tr>
							<td colspan="1">
								<html:submit value="Zurück" property="back"/>
							</td>
							<td colspan="6" align="center">
								&nbsp;<logic:equal name="adventureForm" property="currentMarket.ownerAvatarId" value="${adventureForm.userLoggedInHero.avatarId}"><html:submit value="Verwalten" property="edit"/></logic:equal>
							</td>
							<td colspan="2" align="center">
								<html:submit value="Handeln" property="trade" styleId="tradeButton"/>
							</td>
							<td align="center">
								<div id="worthSumAvatar">${adventureForm.userLoggedInHero.inventory.coins}</div><html:hidden property="" value="${adventureForm.userLoggedInHero.inventory.coins}" styleId="worthSumAvatarHidden"/>
							</td>
							<td align="center">
								<div id="worthSumMarket">${adventureForm.currentMarket.inventory.coins}</div><html:hidden property="" value="${adventureForm.currentMarket.inventory.coins}" styleId="worthSumMarketHidden"/>
							</td>
						</tr>
					</table>
				</td>
				<logic:notEqual name="adventureForm" property="currentField.locationId" value="0">
					<td valign="top">
						<table>
							<tr>
								<td>
									Andere Märkte:
								</td>
							</tr>
							<tr>
								<td>
									<html:submit value="Freier Markt" property="locationFreeMarket"/>
								</td>
							</tr>
						</table>
					</td>
				</logic:notEqual>
			</tr>
		</table>
	</html:form>
</html>
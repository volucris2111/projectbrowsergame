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
			var inventoryValue = parseInt(document.getElementById("inventoryValue"+resource).value);
			
			
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
			var worthSum = parseInt(document.getElementById("worthSum").innerHTML);
			
			worthSum = worthSum + worth + (currentWorth * -1);
			
			document.getElementById("worth"+resource).innerHTML = '' + worth;
			document.getElementById("worthSum").innerHTML = '' + worthSum;
			
			if(worthSum < 0)
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
			field.value = amount;
			setWorthOfResource(resource);
		}
		</script>
	</head>
	<table>
		<tr>
			<td colspan="2">
				 Marktplatz:
			</td>
		</tr>
		<tr>
			<td>
				<html:form action="LocationFreeMarketPostAction.do">
				<table>
					<tr>
						<td colspan="6">
							&nbsp;
						</td>
						<td>
							Vermögen
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
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
					</tr>
					<logic:iterate name="adventureForm" property="currentField.location.locationFreeMarket.marketResources" id="currentResource">
					<tr>
						<td>
							<bean:write name="currentResource" property="key.resourceForJsp"/>:
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
						<td align="right">
							<div id="worth${currentResource.key}"></div>
						</td>
					</tr>
					</logic:iterate>
					<tr>
						<td colspan="1">
							<html:submit value="Zurück" property="back"/>
						</td>
						<td colspan="3">
							&nbsp;
						</td>
						<td colspan="2" align="center">
							<html:submit value="Handeln" property="trade" styleId="tradeButton"/>
						</td>
						<td align="center">
							<div id="worthSum">${adventureForm.userLoggedInHero.inventory.coins}</div><html:hidden property="" value="${adventureForm.userLoggedInHero.inventory.coins}" styleId="worthSumHidden"/>
						</td>
					</tr>
				</table>
				</html:form>
			</td>
			<td valign="top" align="left">
				<table>
					<tr>
						<td>
							Andere Marktstände:
						</td>
					</tr>
					<tr>
						<td>
							<html:form action="LocationFreeMarketPostAction.do">
								<html:submit value="Örtlicher Markstand" property="changeMarket"/><html:hidden property="selectedMarketId" value="${adventureForm.currentField.location.locationMarketId}"/>
							</html:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html>
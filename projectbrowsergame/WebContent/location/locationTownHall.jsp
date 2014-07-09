<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<html:form action="LocationTownHallPostAction.do">
		<table>
			<tr>
				<td colspan="2">
					<logic:equal name="adventureForm" property="currentField.location.ownerAvatarId" value="${adventureForm.userLoggedInHero.avatarId}">
						<html:submit value="Produzieren" property="produce"/>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<b>Produktionsbericht</b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<bean:write filter="none" name="adventureForm" property="currentField.location.lastHourReport"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<b>12 Stunden Bericht:</b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<bean:write filter="none" name="adventureForm" property="currentField.location.lastHalfDayReport"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table>
						<tr>
							<td colspan="2">
								<b>Stündliche Produktion:</b>
							</td>
						</tr>
						<logic:iterate name="adventureForm" property="displayProduction" id="currentProduct">
							<tr>
								<td>
									<bean:write name="currentProduct" property="key.resourceForJsp"/>:
								</td>
								<td>
									<bean:write name="currentProduct" property="value" format="##"/>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</td>
				<td valign="top">
					<table>
						<tr>
							<td colspan="2">
								<b>Stündlicher Verbrauch:</b>
							</td>
						</tr>
						<logic:iterate name="adventureForm" property="displayProductionConsumption" id="currentProductConsumption">
							<tr>
								<td>
									<bean:write name="currentProductConsumption" property="key.resourceForJsp"/>:
								</td>
								<td>
									<bean:write name="currentProductConsumption" property="value" format="##"/>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<b>12 Std. Verbrauch:</b>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<u>Grundbedarf:</u>
				</td>
			</tr>
			<logic:iterate name="adventureForm" property="displayBasicGoodsNeed" id="currentBasicGood">
			<tr>
				<td>
					<bean:write name="currentBasicGood" property="key.resourceForJsp"/>:
				</td>
				<td>
					<bean:write name="currentBasicGood" property="value" format="##"/>
				</td>
			</tr>
			</logic:iterate>
			<tr>
				<td colspan="2">
					<u>Luxusbedarf:</u>
				</td>
			</tr>
			<logic:iterate name="adventureForm" property="displayLuxuryGoodsNeed" id="currentLuxuryGood">
			<tr>
				<td>
					<bean:write name="currentLuxuryGood" property="key.resourceForJsp"/>:
				</td>
				<td>
					<bean:write name="currentLuxuryGood" property="value" format="##"/>
				</td>
			</tr>
			</logic:iterate>
		</table>
	</html:form>
</html>
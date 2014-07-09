<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<html:form action="BuildingOverviewPostAction.do">
		<table>
			<tr>
				<td colspan="4">
					<bean:write name="adventureForm" property="currentField.building.buildingType.nameForJsp"/><logic:notEmpty name="adventureForm" property="currentField.building.name"> "<bean:write name="adventureForm" property="currentField.building.name"/>"</logic:notEmpty> von <bean:write name="adventureForm" property="currentField.building.ownerAvatar.name"/>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					Wachen: Es scheint nicht bewacht zu sein.
				</td>
			</tr>
			<tr>
				<td colspan="4">
					Lager: Das Lager scheint leer zu sein.
				</td>
			</tr>
			<tr>
				<td>
					<html:button property="" value="Spionieren" onclick="alert('Denk nicht mal daran!')"/>
				</td>
				<td>
					<html:button property="" value="Angreifen" onclick="alert('Lass den Scheiß!')"/>
				</td>
				<td>
					<html:button property="" value="Stehlen" onclick="alert('Geh weg!')"/>
				</td>
				<td>
					<html:submit value="Zurück" property="back"/>
				</td>
			</tr>
		</table>
	</html:form>
</html>
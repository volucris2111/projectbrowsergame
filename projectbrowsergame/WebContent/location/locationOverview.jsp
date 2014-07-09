<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<html:form action="LocationOverviewPostAction.do">
		<table>
			<tr>
				<td colspan="1">
					Verwaltung:
				</td>
				<td colspan="3">
					Wird von <bean:write name="adventureForm" property="currentField.location.ownerAvatar.name"/> verwaltet.
				</td>
			</tr>
			<tr>
				<td colspan="1">
					Wachen: 
				</td>
				<td colspan="3">
					Es gibt hier nur sehr wenig Wachen.
				</td>
			</tr>
			<tr>
				<td colspan="1">
					Bewohner: 
				</td>
				<td colspan="3">
					Die Bewohnerzahl ist überschaubar.
				</td>
			</tr>
			<tr>
				<td colspan="1">
					Handel:
				</td>
				<td colspan="3">
					Der Marktplatz wirkt ausgestorben.
				</td>
			</tr>
			<tr>
				<td>
					<html:button property="" value="Spionieren" onclick="alert('Darfst du... später!')"/>
				</td>
				<td>
					<html:button property="" value="Einschleichen" onclick="alert('Darfst doch eh in die Stadt!')"/>
				</td>
				<td>
					<html:button property="" value="Wegelagerei" onclick="alert('Du kannst noch nicht mal Kampfen...')"/>
				</td>
				<td>
					<html:submit value="Zurück" property="back"/>
				</td>
			</tr>
		</table>
	</html:form>
</html>